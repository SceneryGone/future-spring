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

    private SqlSessionUtil sqlSessionUtil;

    public void setSqlSessionUtil(SqlSessionUtil sqlSessionUtil) {
        this.sqlSessionUtil = sqlSessionUtil;
    }

    @Override
    public List<User> selectAll() {
        SqlSession sqlSession = sqlSessionUtil.openSession();
        final UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        return mapper.selectAll();
    }

    @Override
    public User selectById(Integer id) {
        SqlSession sqlSession = sqlSessionUtil.openSession();
        final UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        return mapper.selectById(id);
    }

    @Override
    public int update(User user) {
        SqlSession sqlSession = sqlSessionUtil.openSession();
        final UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        return mapper.updateByPrimaryId(user);
    }

}
