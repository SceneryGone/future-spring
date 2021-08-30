package com.future.exception;

/**
 * 功能描述:
 *
 * @author future
 * @date 2021-08-30 16:42
 */
public class BizException extends RuntimeException {

    public BizException(String message) {
        super(message);
    }
}
