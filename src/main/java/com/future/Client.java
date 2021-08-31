package com.future;

import com.future.db.User;
import com.future.factory.BeanFactory;
import com.future.service.UserService;
import com.future.util.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import sun.jvm.hotspot.utilities.Assert;

import java.lang.reflect.Method;
import java.util.List;

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
//        client.selectAll();
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
