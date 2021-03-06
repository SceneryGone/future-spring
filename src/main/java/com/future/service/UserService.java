package com.future.service;

import com.future.db.User;

import java.util.List;

/**
 * 功能描述:
 *
 * @author future
 * @date 2021-08-30 16:01
 */
public interface UserService {

    List<User> selectAll();

    /**
     * 功能描述: 积分转移
     */
    void transferPoint(Integer fromId, Integer toId, Integer points);
}
