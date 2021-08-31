package com.future.util;

import org.apache.ibatis.session.SqlSession;

/**
 * 功能描述: 事务管理器类
 *
 * @author future
 * @date 2021-08-31 14:59
 */
public class TransactionManager {

    private static final TransactionManager INSTANCE = new TransactionManager();

    private TransactionManager() {

    }

    public static TransactionManager getInstance() {
        return INSTANCE;
    }


    public void begin() {
        SqlSessionUtil.getInstance().openSession();
    }

    public void commit() {
        try (SqlSession sqlSession = SqlSessionUtil.getInstance().openSession()) {
            sqlSession.commit();
        } finally {
            SqlSessionUtil.getInstance().remove();
        }
    }

    public void rollback() {
        try (SqlSession sqlSession = SqlSessionUtil.getInstance().openSession()) {
            sqlSession.rollback();
        } finally {
            SqlSessionUtil.getInstance().remove();
        }
    }
}
