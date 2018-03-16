/**
 * Copyright 2018 asura
 */
package org.asuraframework.commons.algorithm.loadbalance;

import com.google.common.annotations.Beta;
import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;
import org.asuraframework.commons.util.Check;

import javax.annotation.Nonnull;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Objects;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * <p>
 * 一致性Hash算法，负载均衡中常用的算法，核心原理如下
 * 将节点虚拟化，并映射到一个环状结构上，每次请求时候，计算请求Hash映射到环上，从环上选取下一个最近节点
 * 一致性Hash优点:
 * 冗余少
 * 负载均衡
 * 过渡平滑
 * 存储均衡
 * 关键词单调
 * </p>
 *
 * @author liusq23
 * @version 1.0
 * @since 1.0
 */
@Beta
public class ConsistentHash {

    /**
     * 默认160个
     */
    public static final int DEFAULT_REPLICA_NUMBER = 160;

    /**
     * 参与负载均衡的虚拟节点
     */
    private TreeMap<Long, IBalanceNode> virtualNodes;

    /**
     * 初始化Hash算法murmurhash
     */
    private HashFunction hf = Hashing.murmur3_128();

    /**
     * 默认放大160倍
     *
     * @param nodes
     */
    public ConsistentHash(@Nonnull List<IBalanceNode> nodes) {
        this(nodes, DEFAULT_REPLICA_NUMBER);
    }

    /**
     * @param nodes
     * @param virtualTimes
     */
    public ConsistentHash(@Nonnull List<IBalanceNode> nodes, int virtualTimes) {
        if (Check.isNullOrEmpty(nodes)) {
            throw new IllegalArgumentException("nodes must not null or empty");
        }
        virtualNodes = new TreeMap<>();
        // 虚拟化节点
        for (IBalanceNode node : nodes) {
            for (int i = 0; i < virtualTimes; i++) {
                String nodeName = node.getNodeName() + i;
                virtualNodes.put(getHash(nodeName), node);
            }
        }
    }

    /**
     * 根据指定的Key 选择一个Key
     *
     * @param key
     * @return
     */
    public IBalanceNode select(@Nonnull String key) {
        Objects.requireNonNull(key, "select key must not null");
        long hash = getHash(key);
        long selectKey;
        // 存在直接获取
        if (virtualNodes.containsKey(hash)) {
            return virtualNodes.get(hash);
        }
        // 否则从子Map获取下一个节点
        SortedMap<Long, IBalanceNode> sortedMap = virtualNodes.tailMap(hash);
        if (Check.isNullOrEmpty(sortedMap)) {
            selectKey = virtualNodes.firstKey();
        } else {
            selectKey = sortedMap.firstKey();
        }
        return virtualNodes.get(selectKey);
    }

    /**
     * Murmurhash 获取Hash值
     *
     * @param name
     * @return
     */
    private long getHash(String name) {
        HashCode hashCode = hf.newHasher().putString(name, Charset.forName("UTF-8")).hash();
        return hashCode.asLong();
    }

}
