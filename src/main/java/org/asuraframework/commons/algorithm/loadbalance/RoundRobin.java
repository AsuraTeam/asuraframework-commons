/**
 * @FileName: RoundRobin.java
 * @Package: org.asuraframework.commons.algorithm
 * @author liusq23
 * @created 2018/3/12 上午12:05
 * <p>
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
 * 伪代码如下：
 * // i初始化为N-1 这能直接进入 i==0 使得CW初始化为Wmax
 * while(true){
 * i = (i+1) mod n
 * if(i == 0){
 * CW = CW-GCD(S)
 * if(CW <=0){
 * CW = Wmax
 * if (cw == 0)
 * return NULL;
 * }
 * }
 * if(Wi >= cw){
 * return Si
 * }
 * }
 * <p>
 * <p>
 * </p>
 * <p>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
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
    public AtomicInteger currentNode = new AtomicInteger(0);
    /**
     * 记录当前选择的权重
     */
    public AtomicInteger currentWeight = new AtomicInteger(0);

    /**
     * 参与负载均衡的node列表
     */
    public List<IBalanceNode> nodes;

    /**
     * @param currentNode
     * @param currentWeight
     * @param nodes
     */
    public RoundRobin(int currentNode, int currentWeight, @Nonnull List<IBalanceNode> nodes) {
        if (Check.isNullOrEmpty(nodes)) {
            throw new IllegalArgumentException("nodes must not null or empty");
        }
        this.currentNode = new AtomicInteger(currentNode);
        this.currentWeight = new AtomicInteger(currentWeight);
        this.nodes = nodes;
    }

    /**
     * round rodin
     */
    private RoundRobin(@Nonnull List<IBalanceNode> nodes) {
        this(0, 0, nodes);
    }

    /**
     * 需要保证多线程下的安全性
     *
     * @return
     */
    public IBalanceNode select() {
        int nodeIndex = currentNode.get() % nodes.size();

        return null;
    }

    public int getCurrentNode() {
        return currentNode.get();
    }


    public int getCurrentWeight() {
        return currentWeight.get();
    }

}
