package com.future;

import com.future.factory.BeanFactory;
import com.future.service.UserService;
import sun.jvm.hotspot.utilities.Assert;

import java.lang.reflect.Method;

/**
 * 功能描述:
 *
 * @author future
 * @date 2021-08-30 16:01
 */
public class Client {


    public static void main(String[] args) throws Exception {
//        final Class<?> aClass = Class.forName("com.future.Client");
//        final Object client = aClass.newInstance();
//        final Method method = aClass.getDeclaredMethod("testTransfer", null);
//        method.invoke(client, null);

        final Client client = new Client();
        client.testTransfer();
    }


    private void testTransfer() {
        final UserService userService = (UserService) BeanFactory.getBean("userService");
        userService.transferPoint(1, 2, 1);
    }

}
