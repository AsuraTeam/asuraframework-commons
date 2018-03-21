/**
 * Copyright(c) 2018 asura
 */
package org.asuraframework.commons.algorithm;

import org.asuraframework.commons.algorithm.loadbalance.ConsistentHash;
import org.asuraframework.commons.algorithm.loadbalance.IBalanceNode;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p></p>
 *
 * @author liusq23
 * @version 1.0
 * @Date 2018/3/18 下午6:49
 * @since 1.0
 */
public class ConsistentHashTest {

    List<IBalanceNode> nodes = new ArrayList<>();

    @Before
    public void before() {
        Node n = new Node("name1", 20);
        Node n1 = new Node("name2", 30);
        Node n2 = new Node("name3", 50);
        Node n3 = new Node("name4", 50);
        nodes.add(n);
        nodes.add(n1);
        nodes.add(n2);
        nodes.add(n3);
    }

    @Test
    public void selectTest() {
        ConsistentHash consistentHash = new ConsistentHash(nodes);
        IBalanceNode node = consistentHash.select("keyX");
        IBalanceNode node2 = consistentHash.select("keyX");
        IBalanceNode node3 = consistentHash.select("key1");
        IBalanceNode node4 = consistentHash.select("name1100");
        Assert.assertNotNull(node);
        Assert.assertEquals(node2, node);
        Assert.assertNotEquals(node, node3);
        Assert.assertNotNull(node4);
    }

    @Test(expected = IllegalArgumentException.class)
    public void select1Test() {
        new ConsistentHash(new ArrayList<>());
    }

    @Test
    public void select3Test() {
        ConsistentHash consistentHash = new ConsistentHash(nodes);
        Map<String, Integer> map = new HashMap<>();
        int totalSelectNum = 10000;
        for (int i = 0; i < totalSelectNum; i++) {
            IBalanceNode node = consistentHash.select("nodeect"+i+"z" + i);
            Integer inte = map.get(node.getUniqNodeName());
            if (inte == null) {
                inte = 1;
            } else {
                inte = inte + 1;
            }
            map.put(node.getUniqNodeName(),inte);
        }
        int selectNum = 0;
        for (Map.Entry<String,Integer> mapes :map.entrySet()){
            selectNum += mapes.getValue();
        }
        Assert.assertEquals(selectNum,totalSelectNum);
    }

}
