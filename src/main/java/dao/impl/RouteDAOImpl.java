package dao.impl;

import dao.RouteDAO;
import domain.Category;
import domain.Route;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import utils.JDBCUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author LOL_toulan
 * @Time 2020/1/23 20:11
 * @Message
 */
public class RouteDAOImpl implements RouteDAO {

    private JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public int totalCount(int cid, String rname) {
        //String sql = "select count(*) from tab_route where cid = ?";

        String sql = "select count(*) from tab_route where 1=1 ";
        StringBuilder sb = new StringBuilder(sql);
        List params = new ArrayList();
        if (cid != 0) {
            sb.append(" and cid = ?");
            params.add(cid);
        }
        if (rname != null && rname.length() > 0) {
            sb.append(" and rname like ?");
            params.add("%" + rname + "%");
        }
        sql = sb.toString();
        System.out.println("totalCount : "+sql);
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, params.toArray());
        System.out.println("count:"+count);
        return count;
    }

    @Override
    public List<Route> findByPage(int cid, int start, int pageSize, String rname) {
        //String sql = "select * from tab_route where cid = ? limit ?,?";
        String sql = "select * from tab_route where 1=1 ";
        StringBuilder sb = new StringBuilder(sql);
        List params = new ArrayList();
        if (cid != 0) {
            sb.append(" and cid = ? ");
            params.add(cid);
        }
        if (rname != null && rname.length() > 0) {
            sb.append(" and rname like ? ");
            params.add("%" + rname + "%");
        }
        sb.append(" limit ? ,? ");
        sql = sb.toString();
        params.add(start);
        params.add(pageSize);
        System.out.println("findByPage : "+sql);
        List<Route> routeList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<Route>(Route.class), params.toArray());
        System.out.println("routeList : "+routeList);
        return routeList;
    }

    @Override
    public Route findOne(int rid) {

        String sql = "select * from tab_route where rid = ? ";

        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<Route>(Route.class), rid);
    }


}
