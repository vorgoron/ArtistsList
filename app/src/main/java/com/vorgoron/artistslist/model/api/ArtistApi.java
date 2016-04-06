package com.vorgoron.artistslist.model.api;

import com.vorgoron.artistslist.model.api.response.Artist;

import java.util.List;

import retrofit2.http.GET;
import rx.Observable;

public interface ArtistApi {

    @GET("/mobilization-2016/artists.json")
    Observable<List<Artist>> getArtists();
}
