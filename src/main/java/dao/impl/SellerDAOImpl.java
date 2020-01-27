package dao.impl;

import dao.SellerDAO;
import domain.Seller;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import utils.JDBCUtils;

import java.util.List;

/**
 * @Author LOL_toulan
 * @Time 2020/1/26 12:56
 * @Message
 */
public class SellerDAOImpl implements SellerDAO {

    private JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public Seller findSellerBySid(int sid) {

        String sql = "select * from tab_seller where sid =?";

        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<Seller>(Seller.class), sid);
    }
}
