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
        try (SqlSession sqlSession = SqlSessionUtil.openSession()) {
            final UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            return mapper.selectAll();
        }
    }

    @Override
    public Boolean transferPoint(Integer fromId, Integer toId, Integer points) {
        try (SqlSession sqlSession = SqlSessionUtil.openSession()) {
            final UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            User fromUser = mapper.selectById(fromId);
            User toUser = mapper.selectById(toId);

            fromUser.setPoints(fromUser.getPoints() - points);
            toUser.setPoints(toUser.getPoints() + points);

            mapper.updateByPrimaryId(fromUser);
            mapper.updateByPrimaryId(toUser);
        }
        return Boolean.TRUE;
    }

}
