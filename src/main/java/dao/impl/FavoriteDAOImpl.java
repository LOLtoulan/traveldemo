package dao.impl;

import dao.FavoriteDAO;
import domain.Favorite;
import domain.Route;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import utils.JDBCUtils;

import java.util.Date;


/**
 * @Author LOL_toulan
 * @Time 2020/1/27 0:41
 * @Message
 */
public class FavoriteDAOImpl implements FavoriteDAO {

    private JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public Favorite isFavorite(int rid, int uid) {

        Favorite favorite = null;
        try {
            String sql = "select * from tab_favorite where rid =? and uid = ?";

            favorite = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<Favorite>(Favorite.class),rid,uid);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }

        return favorite;
    }

    @Override
    public int findCountByRid(int rid) {

        String sql = "select count(*) from tab_favorite where rid =?";

        return jdbcTemplate.queryForObject(sql, Integer.class, rid);
    }

    @Override
    public void addFavorite(int rid, int uid) {
        String sql = "insert into tab_favorite values(?,?,?)";

        jdbcTemplate.update(sql, rid, new Date(), uid);
    }
}
