/**
 * Copyright 2018 asura
 */
package org.asuraframework.commons.algorithm.loadbalance;

import org.asuraframework.commons.util.Check;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * <p>
 * 最少使用算法，记录最少使用的链接，并命中最少使用的链接
 * {S1,S2,S3...Sn}n台机器，{W1,W2,W3...Wn}对应n台机器的使用次数
 * 算法核心：
 * 记录每台机器的使用次数，每次获取出最少使用的机器，进行权重计算，选择其中一台
 * </p>
 *
 * @author liusq23
 * @version 1.0
 * @since 1.0
 */
public class LeastActive {

    private static final Logger LOGGER = LoggerFactory.getLogger(LeastActive.class);
    /**
     * 记录节点活跃数
     */
    private final ConcurrentHashMap<String, AtomicInteger> activeMap = new ConcurrentHashMap<>();
    /**
     * 随机
     */
    private final Random random = new Random();
    /**
     * 参与负载节点数
     */
    private final List<IBalanceNode> nodes;

    public LeastActive(List<IBalanceNode> nodes) {
        if (Check.isNullOrEmpty(nodes)) {
            throw new IllegalArgumentException("nodes must not null or empty");
        }
        this.nodes = nodes;
        for (IBalanceNode node : nodes) {
            activeMap.put(node.getUniqNodeName(), new AtomicInteger(0));
        }
    }

    /**
     * 按照最小活跃数调度
     *
     * @return
     */
    public IBalanceNode select() {
        int leastActive = -1;
        int leastCount = 0;
        int totalWeight = 0;
        int firstWeight = 0;
        boolean sameWeight = true;
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("current map is {}", activeMap);
        }
        int[] leastIndex = new int[nodes.size()];
        for (int i = 0; i < nodes.size(); i++) {
            IBalanceNode node = nodes.get(i);
            int nodeWeight = node.getWeight();
            int active = activeMap.get(node.getUniqNodeName()).get();
            // 如果比当前活跃数更小
            if (leastActive == -1 || active < leastActive) {
                leastActive = active;
                leastCount = 1;
                totalWeight = nodeWeight;
                firstWeight = nodeWeight;
                leastIndex[0] = i;
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug("find new least active is {},index {},weight {}", leastActive, i, nodeWeight);
                }
            } else if (active == leastActive) {
                totalWeight += nodeWeight;
                leastIndex[leastCount++] = i;
                if (sameWeight && i > 0 && nodeWeight != firstWeight) {
                    sameWeight = false;
                }
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug("find more least active node weight {},index {},sameWeight {}", nodeWeight, i, sameWeight);
                }
            }
        }
        // 只有一个节点
        IBalanceNode selectNode = null;
        if (leastCount == 1) {
            selectNode = nodes.get(leastIndex[0]);
        }
        // 处理权重不同的负载
        if (!sameWeight && totalWeight > 0) {
            int offsetWeight = random.nextInt(totalWeight);
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("current offsetWeight after random is {}", offsetWeight);
            }
            for (int i = 0; i < leastCount; i++) {
                int index = leastIndex[i];
                int weight = nodes.get(index).getWeight();
                offsetWeight -= weight;
                if (offsetWeight <= 0) {
                    selectNode = nodes.get(index);
                    break;
                }
            }
        }
        // 权重相同，随机选一个
        if (selectNode == null) {
            selectNode = nodes.get(leastIndex[random.nextInt(leastCount)]);
        }
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("select node: {}", selectNode.getUniqNodeName());
        }
        activeMap.get(selectNode.getUniqNodeName()).incrementAndGet();
        return selectNode;
    }

}
