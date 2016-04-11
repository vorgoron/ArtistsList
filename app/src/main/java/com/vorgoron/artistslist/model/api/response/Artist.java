package com.vorgoron.artistslist.model.api.response;

import com.activeandroid.sebbia.Model;
import com.activeandroid.sebbia.annotation.Column;
import com.activeandroid.sebbia.annotation.Table;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Table(name = Artist.TABLE_NAME)
public class Artist extends Model {

    public static final String TABLE_NAME = "Artists";
    public static final String ARTIST_ID = "artist_id";
    public static final String NAME = "name";
    public static final String GENRES = "genres";
    public static final String TRACKS = "tracks";
    public static final String ALBUMS = "albums";
    public static final String LINK = "link";
    public static final String DESCRIPTION = "description";
    public static final String COVER = "cover";

    @SerializedName("id")
    @Expose
    @Column(name = ARTIST_ID)
    int artistId;
    @SerializedName("name")
    @Expose
    @Column(name = NAME)
    String name;
    @SerializedName("genres")
    @Expose
    List<String> genresList = new ArrayList<>();
    @SerializedName("tracks")
    @Expose
    @Column(name = TRACKS)
    int tracks;
    @SerializedName("albums")
    @Expose
    @Column(name = ALBUMS)
    int albums;
    @SerializedName("link")
    @Expose
    @Column(name = LINK)
    String link;
    @SerializedName("description")
    @Expose
    @Column(name = DESCRIPTION)
    String description;
    @SerializedName("cover")
    @Expose
    @Column(name = COVER)
    Cover cover;

    @Setter
    @Column(name = GENRES)
    String genres;
}
