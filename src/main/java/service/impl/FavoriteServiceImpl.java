package service.impl;

import dao.FavoriteDAO;
import dao.impl.FavoriteDAOImpl;
import domain.Favorite;
import service.FavoriteService;

/**
 * @Author LOL_toulan
 * @Time 2020/1/27 0:40
 * @Message
 */
public class FavoriteServiceImpl implements FavoriteService {

    private FavoriteDAO dao = new FavoriteDAOImpl();

    @Override
    public boolean isFavorite(int rid, int uid) {

        Favorite favorite = dao.isFavorite(rid, uid);

        return favorite != null;
    }

    @Override
    public void addFavorite(int rid, int uid) {
        dao.addFavorite(rid, uid);
    }
}
