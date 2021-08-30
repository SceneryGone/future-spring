package com.future.exception;

/**
 * 功能描述:
 *
 * @author future
 * @date 2021-08-30 16:21
 */
public class BeanNotFoundException extends RuntimeException {

    public BeanNotFoundException(String message) {
        super(message);
    }
}
