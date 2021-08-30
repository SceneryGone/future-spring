package com.future;

import com.future.factory.BeanFactory;
import com.future.service.UserService;

/**
 * 功能描述:
 *
 * @author future
 * @date 2021-08-30 16:01
 */
public class Client {

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        final UserService userService = (UserService) BeanFactory.getBean("userService");
        userService.selectAll().forEach(System.out::println);
//        final Class<?> aClass = Class.forName("com.future.service.UserServiceImpl");
//        UserService userService = (UserService) aClass.newInstance();
//        userService.selectAll().forEach(System.out::println);

    }
}
