package com.future.service;

import com.future.dao.UserDao;
import com.future.db.User;
import com.future.exception.BizException;
import com.future.util.TransactionManager;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * 功能描述:
 *
 * @author future
 * @date 2021-08-30 16:02
 */
@Slf4j
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
    public void transferPoint(Integer fromId, Integer toId, Integer points) {
        TransactionManager.getInstance().begin();
        User fromUser = userDao.selectById(fromId);
        if (fromUser.getPoints() <= 0) {
            throw new BizException("积分余额不足");
        }
        User toUser = userDao.selectById(toId);

        // 对积分进行增加-扣减
        fromUser.setPoints(fromUser.getPoints() - points);
        toUser.setPoints(toUser.getPoints() + points);

        // 更新积分
        userDao.update(fromUser);
        userDao.update(toUser);
        TransactionManager.getInstance().commit();
    }

}
