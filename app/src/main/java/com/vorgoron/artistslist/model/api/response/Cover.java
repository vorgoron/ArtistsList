package com.vorgoron.artistslist.model.api.response;

import com.activeandroid.sebbia.Model;
import com.activeandroid.sebbia.annotation.Column;
import com.activeandroid.sebbia.annotation.Table;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

@Getter
@Table(name = Cover.TABLE_NAME)
public class Cover extends Model {

    public static final String TABLE_NAME = "Covers";
    public static final String ARTIST = "artist";
    public static final String SMALL = "small";
    public static final String BIG = "big";

    @Setter
    @Column(name = ARTIST)
    Artist artist;
    @SerializedName("small")
    @Expose
    @Column(name = SMALL)
    String small;
    @SerializedName("big")
    @Expose
    @Column(name = BIG)
    String big;
}
