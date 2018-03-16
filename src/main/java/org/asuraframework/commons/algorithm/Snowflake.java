/**
 * Copyright 2018 asura
 */
package org.asuraframework.commons.algorithm;

/**
 * <p>
 * snowflake 雪花模型算法，参考twitter https://github.com/twitter/snowflake 实现
 * 核心思想：将一个64位的二进制数据，进行划分，例子如下
 * |---------------41位时间戳---------------|               |12bit序列号|
 * 0 00000000000000000000000000000000000000000   0000000000  000000000000
 * |                                           |10为机器编码|
 * 第一位弃用
 * 限制：按照上面例子
 * 由于时间戳位数有限，有容量限制，例如41位最多可以容纳69年的数据生成，2199023255551毫秒
 * 由于10位机器编码，最多支持1023个机器节点同时生成
 * 由于12位序列号，最多支持每毫秒生成4095个序列号
 * sonwflake 缺点：
 * 如果集群机器时间不统一，在单机上是递增的，但是由于涉及到分布式环境，
 * 每台机器上的时钟不可能完全同步，也许有时候也会出现不是全局递增的情况
 * </p>
 *
 * @author liusq23
 * @version 1.0
 * @since 1.0
 */
public class Snowflake {

    /**
     * 定位时间点 2016-01-01 00:00:00.000
     */
    public static final Long POSITION_TIME = 1451577600000L;

    public static final long MAX_BITS = 63L;
    /**
     * 机器id占4位  1111  最大 2^4-1 = 15 最多支持15台id生成机器集群
     */
    private int workerBits;
    /**
     * 序列号占6位 111111 最大 2^6-1 = 63 支持每个机器每毫秒63个订单
     */
    private int sequenceBits;
    /**
     * 序列号掩码
     * 000000000000 000000000000 000000000000 000000000000 0000000000 111111
     */
    private long sequenceMask;
    /**
     * 机器id最大数量 15
     */
    private long maxWorkerId;
    /**
     * 机器ID
     */
    private long maxTimestamp;

    /**
     * 时间截向左移sequenceBits+workerBits位
     */
    private long timestampLeftShift = workerBits + sequenceBits;
    /**
     * 初始化开始时间
     */
    private long lastTimestamp = -1L;
    /**
     * 初始化sequence
     */
    private long sequence = 0;
    /**
     * 工作机器ID
     */
    private int workerId;

    public Snowflake(int workerBits, int sequenceBits, int timestampBits, int workerId) {
        if (workerBits < 1 || sequenceBits < 1 || timestampBits < 1) {
            throw new IllegalArgumentException(String.format("workerBits, sequenceBits , timestampBits cannot less than 0"));
        }
        this.sequenceBits = sequenceBits;
        this.workerBits = workerBits;
        this.workerId = workerId;
        this.timestampLeftShift = sequenceBits + workerBits;
        int maxBits = workerBits + sequenceBits + timestampBits;
        if (maxBits > MAX_BITS) {
            throw new IllegalArgumentException(String.format("workerBits + sequenceBits + timestampBits greater than %d or less than 0", 63));
        }
        this.sequenceMask = -1L ^ (-1L << sequenceBits);
        this.maxWorkerId = -1L ^ (-1L << workerBits);
        this.maxTimestamp = -1L ^ (-1L << timestampBits);
        if (workerId < 0 || workerId > maxWorkerId) {
            throw new IllegalArgumentException(String.format("workId cannot be greater than %d or less than 0", maxWorkerId));
        }
    }

    public long nextId() {
        //获取到当前的时间
        Long current = timeGen();
        if ((current - POSITION_TIME) > maxTimestamp) {
            throw new RuntimeException(String.format("Time over flow. Refusing to generate id for time %d ", current));
        }
        //时钟倒置错误
        if (current < lastTimestamp) {
            throw new RuntimeException(String.format("Clock moved backwards. Refusing to generate id for %d milliseconds", lastTimestamp - current));
        }
        //如果当前时间相等，则获取下一个序列号
        if (current == lastTimestamp) {
            //判断sequence是否溢出，如果溢出，则等下一毫秒的到来
            sequence = (sequence + 1) & sequenceMask;
            if (sequence == 0) {
                current = tilNextMillis(lastTimestamp);
            }
        } else {
            sequence = 0L;
        }
        //检查当前时间是否溢出，一旦溢出
        if ((current - POSITION_TIME) > maxTimestamp) {
            throw new RuntimeException(String.format("Time over flow. Refusing to generate id for time %d ", current));
        }
        lastTimestamp = current;
        return ((current - POSITION_TIME) << timestampLeftShift) | (workerId << sequenceBits) | sequence;
    }

    /**
     * 等待到下一秒
     *
     * @param lastTimestamp
     * @return
     */
    private Long tilNextMillis(long lastTimestamp) {
        long current = timeGen();
        while (current <= lastTimestamp) {
            current = timeGen();
        }
        return current;
    }

    /**
     * @return
     */
    public long timeGen() {
        return System.currentTimeMillis();
    }

}
