package com.future.mapper;

import com.future.db.User;

import java.util.List;

/**
 * 功能描述:
 *
 * @author future
 * @date 2021-08-30 22:25
 */
public interface UserMapper {

    List<User> selectAll();

}
