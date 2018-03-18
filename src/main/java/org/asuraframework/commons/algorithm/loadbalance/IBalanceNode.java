/**
 * Copyright 2018 asura
 */
package org.asuraframework.commons.algorithm.loadbalance;

/**
 * <p>
 *     参与负载均衡node
 * </p>
 *
 *
 * @author liusq23
 * @since 1.0
 * @version 1.0
 */
public interface IBalanceNode {

    /**
     * 获取节点的权重，例如:轮询算法可以需要根据权重调度
     * @return
     */
    int getWeight();

    /**
     * 获取node节点的名称,作为Hash使用
     * 节点名称必须唯一
     * @return
     */
    String getUniqNodeName();

}
