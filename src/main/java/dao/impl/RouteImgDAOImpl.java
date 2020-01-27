package dao.impl;

import dao.RouteImgDAO;
import domain.RouteImg;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import utils.JDBCUtils;

import java.util.List;

/**
 * @Author LOL_toulan
 * @Time 2020/1/26 12:54
 * @Message
 */
public class RouteImgDAOImpl implements RouteImgDAO {

    private JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public List<RouteImg> findImgByRid(int rid) {

        String sql = "select * from tab_route_img where rid = ?";

        List<RouteImg> imgList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<RouteImg>(RouteImg.class), rid);

        return imgList;
    }
}
