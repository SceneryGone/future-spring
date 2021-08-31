package com.future;

import com.future.db.User;
import com.future.factory.BeanFactory;
import com.future.factory.ProxyFactory;
import com.future.service.UserService;

import java.util.List;

/**
 * 功能描述:
 *
 * @author future
 * @date 2021-08-30 16:01
 */
public class Client {


    public static void main(String[] args) {

        final Client client = new Client();

        client.testProxyTransfer();

//        final Class<?> aClass = Class.forName("com.future.Client");
//        final Object client = aClass.newInstance();
//        final Method method = aClass.getDeclaredMethod("testTransfer", null);
//        method.invoke(client, null);

//        client.testTransfer();
//        client.selectAll();
    }

    private void testProxyTransfer() {
        final UserService userService = (UserService) BeanFactory.getInstance().getBean("userService");
        ProxyFactory proxyFactory = (ProxyFactory) BeanFactory.getInstance().getBean("proxyFactory");
        UserService proxy = (UserService) proxyFactory.wrap(userService);
        proxy.transferPoint(1, 2, 1);
    }

    private void selectAll() {
        final UserService userService = (UserService) BeanFactory.getInstance().getBean("userService");
        final List<User> users = userService.selectAll();
        users.forEach(System.out::println);

        System.out.println("-------");

        final List<User> users1 = userService.selectAll();
        users1.forEach(System.out::println);
    }

    private void testTransfer() {
        final UserService userService = (UserService) BeanFactory.getInstance().getBean("userService");
        userService.transferPoint(1, 2, 1);
    }

}
