/**
 * Copyright(c) 2018 asura
 */
package org.asuraframework.commons.algorithm;

import org.asuraframework.commons.algorithm.loadbalance.IBalanceNode;
import org.asuraframework.commons.algorithm.loadbalance.LeastActive;
import org.asuraframework.commons.algorithm.loadbalance.RoundRobin;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *     最少使用算法
 * </p>
 *
 *
 * @author liusq23
 * @since 1.0
 * @version 1.0
 * @Date 2018/3/21 上午10:47
 */
public class LeastActiveTest {

    List<IBalanceNode> nodes = new ArrayList<>();
    List<IBalanceNode> nodes2 = new ArrayList<>();
    @Before
    public void before() {
        Node n0 = new Node("name1", 20);
        Node n1 = new Node("name2", 30);
        Node n2 = new Node("name3", 50);
        Node n3 = new Node("name4", 100);
        Node n4 = new Node("name5", 20);
        nodes.add(n0);
        nodes.add(n1);
        nodes.add(n2);
        nodes.add(n3);
        nodes2.add(n0);
        nodes2.add(n4);
    }

    @Test
    public void selectTest(){
        LeastActive leastActive = new LeastActive(nodes);
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < 100; i++) {
            IBalanceNode node = leastActive.select();
            Integer inte = map.get(node.getUniqNodeName());
            if (inte == null) {
                inte = 1;
            } else {
                inte = inte + 1;
            }
            map.put(node.getUniqNodeName(), inte);
        }
        int selectNum = 0;
        for (Map.Entry<String, Integer> mapes : map.entrySet()) {
            selectNum += mapes.getValue();
            if (mapes.getKey().equals("name1")) {
                Assert.assertEquals(mapes.getValue().intValue(), 25);
            }
            if (mapes.getKey().equals("name2")) {
                Assert.assertEquals(mapes.getValue().intValue(), 25);
            }
            if (mapes.getKey().equals("name3")) {
                Assert.assertEquals(mapes.getValue().intValue(), 25);
            }
            if (mapes.getKey().equals("name4")) {
                Assert.assertEquals(mapes.getValue().intValue(), 25);
            }
        }
        Assert.assertEquals(selectNum, 100);
    }

    @Test
    public void select2Test(){
        LeastActive leastActive = new LeastActive(nodes2);
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < 1000; i++) {
            IBalanceNode node = leastActive.select();
            Integer inte = map.get(node.getUniqNodeName());
            if (inte == null) {
                inte = 1;
            } else {
                inte = inte + 1;
            }
            map.put(node.getUniqNodeName(), inte);
        }
        int selectNum = 0;
        for (Map.Entry<String, Integer> mapes : map.entrySet()) {
            selectNum += mapes.getValue();
            if (mapes.getKey().equals("name1")) {
                Assert.assertEquals(mapes.getValue().intValue(), 500);
            }
            if (mapes.getKey().equals("name4")) {
                Assert.assertEquals(mapes.getValue().intValue(), 500);
            }
        }
        Assert.assertEquals(selectNum, 1000);
    }

    @Test(expected = IllegalArgumentException.class)
    public void initTest(){
        new LeastActive(new ArrayList<>());
    }


}
