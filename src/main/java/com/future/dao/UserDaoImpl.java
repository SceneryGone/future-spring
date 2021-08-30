package com.future.dao;

import com.future.db.User;
import com.google.common.collect.Lists;

import java.util.List;

/**
 * 功能描述:
 *
 * @author future
 * @date 2021-08-30 16:01
 */
public class UserDaoImpl implements UserDao {

    private static final List<User> USER_LIST = Lists.newArrayList();

    static {
        final User user1 = User.builder().id(1).name("1").age(1).build();
        final User user2 = User.builder().id(2).name("2").age(2).build();
        final User user3 = User.builder().id(3).name("3").age(3).build();
        final User user4 = User.builder().id(4).name("4").age(4).build();

        USER_LIST.add(user1);
        USER_LIST.add(user2);
        USER_LIST.add(user3);
        USER_LIST.add(user4);
    }


    @Override
    public List<User> selectAll() {
        return USER_LIST;
    }
}
