package com.future.service;

import com.future.dao.UserDao;
import com.future.db.User;

import java.util.List;

/**
 * 功能描述:
 *
 * @author future
 * @date 2021-08-30 16:02
 */
public class UserServiceImpl implements UserService {

    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public List<User> selectAll() {
        return userDao.selectAll();
    }

    @Override
    public Boolean transferPoint(Integer fromId, Integer toId, Integer points) {
        return userDao.transferPoint(fromId, toId, points);
    }

}
