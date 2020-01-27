package dao.impl;

import dao.UserDAO;
import domain.User;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import utils.JDBCUtils;

/**
 * @Author LOL_toulan
 * @Time 2020/1/22 0:11
 * @Message
 */
public class UserDAOImpl implements UserDAO {

   private JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());

    /**
     * 通过用户名查询用户
     * @param username
     * @return
     */
    @Override
    public User findByUsername(String username) {

        User user = null;
        try {
            String sql = "select * from tab_user where username = ?";

            user = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), username);
        } catch (DataAccessException e) {

        }

        return user;
    }

    /**
     * 保存，添加用户信息
     *
     * @param user
     * @return
     */
    @Override
    public void save(User user) {
        String sql = "insert into tab_user(username,password,name,birthday,sex,telephone,email,status,code) values(?,?,?,?,?,?,?,?,?)";
        jdbcTemplate.update(sql,user.getUsername(),
                user.getPassword(),
                user.getName(),
                user.getBirthday(),
                user.getSex(),
                user.getTelephone(),
                user.getEmail(),
                user.getStatus(),
                user.getCode()
        );
    }

    /**
     * 通过唯一标识code查询用户
     * @param code
     * @return
     */
    @Override
    public User findActiveCode(String code) {
        User user = null;
        try {
            String sql = "select * from tab_user where code = ?";
            user = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), code);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return user;
    }

    /**
     * 改变用户的状态
     * @param user
     */
    @Override
    public void updateUserStatus(User user) {
        String sql = "update tab_user set status = 'Y' where uid=?";
        System.out.println(user.getUid());
        jdbcTemplate.update(sql,user.getUid());
    }

    /**
     * 根据用户名密码来查询
     * @param username
     * @param password
     * @return
     */
    @Override
    public User findByUsernameAndPassword(String username, String password) {

        User user = null;
        try {
            String sql = "select * from tab_user where username = ? and password = ?";

            user = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), username,password);
        } catch (DataAccessException e) {

        }

        return user;
    }
}
