package service;

import domain.User;

/**
 * @Author LOL_toulan
 * @Time 2020/1/22 0:10
 * @Message
 */
public interface UserService {

    /**
     * 用户注册
     * @param user
     */
    boolean register(User user);

    boolean active(String code);

    User login(User user);
}
