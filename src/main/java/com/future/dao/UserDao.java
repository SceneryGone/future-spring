package com.future.dao;

import com.future.db.User;

import java.util.List;

/**
 * 功能描述:
 *
 * @author future
 * @date 2021-08-30 16:01
 */
public interface UserDao {

    List<User> selectAll();

    Boolean transferPoint(Integer fromId, Integer toId, Integer points);
}
