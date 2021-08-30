package com.future.service;

import com.future.dao.UserDao;
import com.future.db.User;
import com.future.factory.BeanFactory;

import java.util.List;

/**
 * 功能描述:
 *
 * @author future
 * @date 2021-08-30 16:02
 */
public class UserServiceImpl implements UserService {

    private UserDao userDao = (UserDao) BeanFactory.getBean("userDao");

    @Override
    public List<User> selectAll() {
        return userDao.selectAll();
    }

}
