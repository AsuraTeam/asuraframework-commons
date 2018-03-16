/**
 * @FileName: HashCodeTest.java
 * @Package: org.asuraframework.commons
 * @author liusq23
 * @created 2018/3/12 下午10:15
 * <p>
 * Copyright 2018 asura
 */
package org.asuraframework.commons;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * <p></p>
 *
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author liusq23
 * @since 1.0
 * @version 1.0
 */
public class HashCodeTest {

    @Test
    public void hashCodeTest(){
        List<User> users = new ArrayList<>();
        users.add(new User("A"));
        users.add(new User("B"));
        int a = System.identityHashCode(users);
        List<User> usersa = new ArrayList<>();
        users.add(new User("A"));
        users.add(new User("B"));
        int b = System.identityHashCode(usersa);
        System.out.println(a == b);
    }

    private class User {
        String name;

        public User(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}
