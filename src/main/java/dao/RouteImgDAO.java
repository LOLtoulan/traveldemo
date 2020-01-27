package dao;

import domain.RouteImg;

import java.util.List;

/**
 * @Author LOL_toulan
 * @Time 2020/1/26 12:54
 * @Message
 */
public interface RouteImgDAO {

    List<RouteImg> findImgByRid(int rid);
}
