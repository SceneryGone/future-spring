package com.future;

import com.future.factory.BeanFactory;
import com.future.service.UserService;
import sun.jvm.hotspot.utilities.Assert;

/**
 * 功能描述:
 *
 * @author future
 * @date 2021-08-30 16:01
 */
public class Client {

    public static void main(String[] args) {
        final UserService userService = (UserService) BeanFactory.getBean("userService");
        final Boolean transferPoint = userService.transferPoint(1, 2, 1);
        Assert.that(transferPoint.equals(Boolean.TRUE), "transferPoint error");
    }
}
