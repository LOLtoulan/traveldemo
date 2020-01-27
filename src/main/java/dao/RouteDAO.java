package dao;

import domain.Category;
import domain.Route;

import java.util.List;

/**
 * @Author LOL_toulan
 * @Time 2020/1/23 20:11
 * @Message
 */
public interface RouteDAO {

    /**
     * 根据cid查询总记数
     * @param cid
     * @param rname
     * @return
     */
    int totalCount(int cid, String rname);

    /**
     * 分页查询
     * @param cid
     * @param start
     * @param pageSize
     * @param rname
     * @return
     */
    List<Route> findByPage(int cid, int start, int pageSize, String rname);

    /**
     *
     * @param rid
     * @return
     */
    Route findOne(int rid);

}
