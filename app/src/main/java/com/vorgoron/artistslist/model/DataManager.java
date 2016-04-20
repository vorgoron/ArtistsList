package com.vorgoron.artistslist.model;

import android.content.Context;

import com.vorgoron.artistslist.R;
import com.vorgoron.artistslist.exception.NoInternetConnectionException;
import com.vorgoron.artistslist.model.api.ArtistApi;
import com.vorgoron.artistslist.model.api.response.Artist;

import java.util.List;

import rx.Observable;

/**
 * Менеджер, управлящий загрузкой списка исполнителей.
 * Если есть подключение к сети, то загружает из интернета,
 * иначе пытается загрузить из кэша. Если в кэше список пустой,
 * то вызывается исключение {@link NoInternetConnectionException}
 */
public class DataManager {

    private Context context;
    private ArtistApi artistApi;
    private Cache cache;
    private ConnectionManager connectionManager;

    public DataManager(Context context,
                       ArtistApi artistApi,
                       Cache cache,
                       ConnectionManager connectionManager) {
        this.context = context;
        this.artistApi = artistApi;
        this.cache = cache;
        this.connectionManager = connectionManager;
    }

    /**
     * Получить список исполнителей
     *
     * @return список исполнителей
     */
    public Observable<List<Artist>> getArtists(boolean forceLoad) {
        if (forceLoad) {
            if (connectionManager.isInternetConnected()) {
                return artistApi
                        .getArtists()
                        .flatMap(cache::saveArtists);
            } else {
                return cache
                        .getArtists()
                        .flatMap(list -> {
                            if (list.size() == 0) {
                                return Observable.error(new NoInternetConnectionException(context.getString(R.string.no_internet_connection_error)));
                            }
                            return Observable.just(list);
                        });
            }
        } else {
            return cache
                    .getArtists();
        }
    }

    /**
     * Получить детальную информацию об исполнителе
     *
     * @param artistId id исполнителя
     * @return Исполнитель
     */
    public Observable<Artist> getArtist(int artistId) {
        return cache.getArtist(artistId);
    }

}
