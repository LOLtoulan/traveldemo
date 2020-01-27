package dao;

import domain.User;

/**
 * @Author LOL_toulan
 * @Time 2020/1/22 0:11
 * @Message
 */
public interface UserDAO {

    /**
     * 根具用户名查询用户数据
     * @param username
     * @return
     */
    User findByUsername(String username);

    /**
     * 保存用户信息
     * @param user
     * @return
     */
    void save(User user);

    /**
     * 根据激活码查询用户对象
     * @param code
     * @return
     */
    User findActiveCode(String code);

    /**
     * 修改指定用户机会状态
     * @param user
     */
    void updateUserStatus(User user);

    /**
     * 通过用户名密码来登录用户
     * @param username
     * @param password
     * @return
     */
    User findByUsernameAndPassword(String username, String password);

}
