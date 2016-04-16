package com.vorgoron.artistslist.model.api;

import com.vorgoron.artistslist.model.api.response.Artist;

import java.util.List;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Сервис для получения списка исполнителей.
 */
public interface ArtistApi {

    /**
     * Получить список исполнителей
     *
     * @return список исполнителей
     */
    @GET("/mobilization-2016/artists.json")
    Observable<List<Artist>> getArtists();
}
