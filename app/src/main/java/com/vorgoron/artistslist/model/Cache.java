package com.vorgoron.artistslist.model;

import android.text.TextUtils;

import com.activeandroid.sebbia.ActiveAndroid;
import com.activeandroid.sebbia.query.Delete;
import com.activeandroid.sebbia.query.Select;
import com.vorgoron.artistslist.model.api.response.Artist;
import com.vorgoron.artistslist.model.api.response.Cover;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

/**
 * Класс, реализующий кэширование. Используется Active Android sebbia
 */
public class Cache {

    @Inject
    public Cache() {
    }

    /**
     * Сохранить список исполнителей
     *
     * @param artists список исполнителей
     */
    public void saveArtists(List<Artist> artists) {
        ActiveAndroid.beginTransaction();
        try {
            clearTables();
            for (Artist artist : artists) {
                artist.setGenres(TextUtils.join(", ", artist.getGenresList()));
                artist.getCover().save();
            }
            Artist.saveMultiple(artists);
            ActiveAndroid.setTransactionSuccessful();
        } finally {
            ActiveAndroid.endTransaction();
        }
    }

    /**
     * Очистить все таблицы
     */
    public void clearTables() {
        new Delete().from(Artist.class).execute();
        new Delete().from(Cover.class).execute();
    }

    /**
     * Получить список исполнителей
     *
     * @return список исполнителей
     */
    public Observable<List<Artist>> getArtists() {
        final List<Artist> artists = new Select()
                .from(Artist.class)
                .execute();

        return Observable
                .create((Observable.OnSubscribe<List<Artist>>) subscriber -> {
                    subscriber.onNext(artists);
                    subscriber.onCompleted();
                });
    }

    /**
     * Получить детальную информацию об исполнителе
     *
     * @param artistId id исполнителя
     * @return Исполнитель
     */
    public Observable<Artist> getArtist(int artistId) {
        final Artist artist = new Select()
                .from(Artist.class)
                .eq(Artist.ARTIST_ID, artistId)
                .executeSingle();

        return Observable
                .create((Observable.OnSubscribe<Artist>) subscriber -> {
                    subscriber.onNext(artist);
                    subscriber.onCompleted();
                });
    }
}
