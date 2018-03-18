/**
 * Copyright 2018 asura
 */
package org.asuraframework.commons.algorithm.loadbalance;

import com.google.common.annotations.Beta;
import org.asuraframework.commons.util.Check;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * <p>
 * round robin 加权轮询算法，核心思想如下
 * 存在{S1(W1),S2(W2),...Sn(Wn)}个节点，Si为服务节点，Wi为Si节点对应的权重，
 * 1、首先求得索引节点权重的最大公约数（GCD），
 * 2、初始化CW=Wmax，选择权重最大的Smax（Wmax）节点（可能多个）开始调度，当轮询完一轮多个节点后，
 * 3、CW（当前权重-GCD），进行第二轮调度，直到CW=0，再次进行第2步骤操作
 * </p>
 *
 * @author liusq23
 * @version 1.0
 * @since 1.0
 */
@Beta
public class RoundRobin {

    /**
     * 记录上一个选择的Node节点从0开始计数
     */
    public final AtomicInteger currentNode = new AtomicInteger(0);
    /**
     * 记录当前选择的权重
     */
    public final AtomicInteger currentWeight = new AtomicInteger(0);

    /**
     * 参与负载均衡的node列表
     */
    public final List<IBalanceNode> nodes;
    /**
     * 计算得出的最大公约数
     */
    private final int gcd;
    /**
     * 最大权重
     */
    private final int maxWeight;

    /**
     * @param nodes
     */
    public RoundRobin(@Nonnull List<IBalanceNode> nodes) {
        if (Check.isNullOrEmpty(nodes)) {
            throw new IllegalArgumentException("nodes must not null or empty");
        }
        this.nodes = nodes;
        this.gcd = greatestCommonDivisor(nodes.size());
        this.maxWeight = getMaxWeight();
    }

    /**
     * @return
     */
    public IBalanceNode select() {
        while (true) {
            int modNodeIndex = currentNode.getAndIncrement() % nodes.size();
            IBalanceNode node = nodes.get(modNodeIndex);
            // 一轮结束，降低权重，开始轮循
            if (modNodeIndex == 0 && maxWeight > 0) {
                int weight = currentWeight.addAndGet(-gcd);
                // 考虑weight为0情况
                if (weight < 0) {
                    currentWeight.compareAndSet(weight, maxWeight);
                }
            }
            if (node.getWeight() >= currentWeight.get()) {
                return node;
            }
        }
    }

    /**
     * 获取所有Node之间的最大公约数
     *
     * @return
     */
    private int greatestCommonDivisor(int index) {
        if (index == 0) {
            return nodes.get(0).getWeight();
        }
        int numa = nodes.get(index - 1).getWeight();
        int numb = greatestCommonDivisor(index - 1);
        return greatestCommonDivisor(numa, numb);
    }

    /**
     * 获取两个数字间最大公约数
     *
     * @return
     */
    private int greatestCommonDivisor(int numa, int numb) {
        int max = numa > numb ? numa : numb;
        int min = numa > numb ? numb : numa;
        while (min != 0 && max % min != 0) {
            int temp = max % min;
            max = min;
            min = temp;
        }
        return min;
    }


    public int getMaxWeight() {
        int maxWeight = 0;
        for (int i = 0; i < nodes.size(); i++) {
            int nodeWeight = nodes.get(i).getWeight();
            maxWeight = maxWeight > nodeWeight ? maxWeight : nodeWeight;
        }
        return maxWeight;
    }
}
