package service;

/**
 * @Author LOL_toulan
 * @Time 2020/1/27 0:40
 * @Message
 */
public interface FavoriteService {
    boolean isFavorite(int rid,int uid);

    void addFavorite(int rid, int uid);
}
