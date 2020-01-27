package dao.impl;

import dao.CategoryDAO;
import domain.Category;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import utils.JDBCUtils;

import java.util.List;

/**
 * @Author LOL_toulan
 * @Time 2020/1/23 0:58
 * @Message
 */
public class CategoryDAOImpl implements CategoryDAO {

    private JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public List<Category> findAll() {
        String sql = "select * from tab_category";
        return jdbcTemplate.query(sql,new BeanPropertyRowMapper<Category>(Category.class));
    }

    @Override
    public Category findCategory(int cid) {

        String sql = "select * from tab_category where cid =?";
        return jdbcTemplate.queryForObject(sql,new BeanPropertyRowMapper<>(Category.class),cid);
    }
}
