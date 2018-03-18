/**
 * Copyright(c) 2018 asura
 */
package org.asuraframework.commons.algorithm;

import org.asuraframework.commons.algorithm.loadbalance.IBalanceNode;
import org.asuraframework.commons.algorithm.loadbalance.RoundRobin;
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
 * @Date 2018/3/18 下午7:58
 * @since 1.0
 */
public class RoundRobinTest {

    List<IBalanceNode> nodes = new ArrayList<>();

    @Before
    public void before() {
        Node n = new Node("name1", 20);
        Node n1 = new Node("name2", 30);
        Node n2 = new Node("name3", 50);
        nodes.add(n);
        nodes.add(n1);
        nodes.add(n2);
    }

    @Test
    public void selectTest() {
        RoundRobin roundRobin = new RoundRobin(nodes);
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < 1000; i++) {
            IBalanceNode node = roundRobin.select();
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
                Assert.assertEquals(mapes.getValue().intValue(), 20);
            }
            if (mapes.getKey().equals("name2")) {
                Assert.assertEquals(mapes.getValue().intValue(), 30);
            }
            if (mapes.getKey().equals("name3")) {
                Assert.assertEquals(mapes.getValue().intValue(), 50);
            }
        }
        Assert.assertEquals(selectNum, 100);
    }


}
