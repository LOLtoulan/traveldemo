package service.impl;

import dao.UserDAO;
import dao.impl.UserDAOImpl;
import domain.User;
import service.UserService;
import utils.MailUtils;
import utils.UuidUtil;

/**
 * @Author LOL_toulan
 * @Time 2020/1/22 0:11
 * @Message
 */
public class UserServiceImpl implements UserService {

    /**
     * 用户注册
     *
     * @param user
     */
    @Override
    public boolean register(User user) {

        //调用dao中的方法来将用户注册的数据写入数据库中
        UserDAO dao = new UserDAOImpl();

        User username = dao.findByUsername(user.getUsername());

        if (username != null) {
            return false;
        }
        //设置唯一验证激活
        user.setCode(UuidUtil.getUuid());
        user.setStatus("N");

        dao.save(user);

        String content="<a href='http://localhost:8080/travel/activeUserServlet?code="+user.getCode()+"'>点击激活【黑马旅游网】</a>";

        MailUtils.sendMail(user.getEmail(), content, "激活邮件");
        System.out.println(content);
        return true;
    }

    /**
     * 激活用户
     * @param code
     * @return
     */
    @Override
    public boolean active(String code) {
        UserDAO dao = new UserDAOImpl();
        User user = dao.findActiveCode(code);
        if (user != null) {
            dao.updateUserStatus(user);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public User login(User user) {
        UserDAO dao = new UserDAOImpl();

        return dao.findByUsernameAndPassword(user.getUsername(),user.getPassword());
    }
}
