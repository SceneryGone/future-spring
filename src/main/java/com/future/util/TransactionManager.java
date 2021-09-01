package com.future.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;

/**
 * 功能描述: 事务管理器类
 *
 * @author future
 * @date 2021-08-31 14:59
 */
@Slf4j
public class TransactionManager {

    private SqlSessionUtil sqlSessionUtil;

    public void setSqlSessionUtil(SqlSessionUtil sqlSessionUtil) {
        this.sqlSessionUtil = sqlSessionUtil;
    }

    public void begin() {
        log.info("transaction begin !");
        sqlSessionUtil.txOpenSession();
    }

    public void commit() {
        try (SqlSession sqlSession = sqlSessionUtil.txOpenSession()) {
            log.info("transaction commit !");
            sqlSession.commit();
        } finally {
            sqlSessionUtil.remove();
        }
    }

    public void rollback() {
        try (SqlSession sqlSession = sqlSessionUtil.txOpenSession()) {
            log.info("transaction roolback !");
            sqlSession.rollback();
        } finally {
            sqlSessionUtil.remove();
        }
    }

}
