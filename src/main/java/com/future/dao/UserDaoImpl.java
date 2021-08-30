package com.future.dao;

import com.future.db.User;
import com.future.mapper.UserMapper;
import com.future.util.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

/**
 * 功能描述:
 *
 * @author future
 * @date 2021-08-30 16:01
 */
public class UserDaoImpl implements UserDao {

    @Override
    public List<User> selectAll() {
        final SqlSession sqlSession = SqlSessionUtil.openSession();
        final UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        return mapper.selectAll();
    }
}
