/**
 * Copyright(c) 2018 asura
 */
package org.asuraframework.commons.algorithm;

import org.asuraframework.commons.algorithm.loadbalance.IBalanceNode;

/**
 * <p></p>
 *
 *
 * @author liusq23
 * @since 1.0
 * @version 1.0
 * @Date 2018/3/18 下午6:49
 */
public class Node implements IBalanceNode{

    private String nodeName;

    private int weight;

    public Node(String nodeName, int weight) {
        this.nodeName = nodeName;
        this.weight = weight;
    }

    @Override
    public int getWeight() {
        return weight;
    }

    @Override
    public String getUniqNodeName() {
        return nodeName;
    }
}
