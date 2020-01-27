package dao;

import domain.Favorite;

/**
 * @Author LOL_toulan
 * @Time 2020/1/27 0:41
 * @Message
 */
public interface FavoriteDAO {
    Favorite isFavorite(int rid, int uid);

    int findCountByRid(int rid);

    void addFavorite(int rid, int uid);
}
