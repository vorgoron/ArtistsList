package com.vorgoron.artistslist.model;

import com.vorgoron.artistslist.BaseTest;
import com.vorgoron.artistslist.model.api.ArtistApi;
import com.vorgoron.artistslist.model.api.response.Artist;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.inject.Inject;

import rx.Observable;

public class ArtistApiTest extends BaseTest {

//    @Inject
//    ArtistApi artistApi;
//
//    private Observable<Artist> artistObservable;
//
//    @Before
//    public void setUp() {
//        getTestComponent().inject(ArtistApiTest.this);
//
//        artistObservable = artistApi.getArtists().flatMap(Observable::from);
//    }
//
//    @Test
//    public void testNames() {
//        Observable.zip(
//                artistObservable.map(Artist::getName),
//                Observable.just("Led Zeppelin", "Кипелов", "Король и Шут"),
//                String::equals
//        ).subscribe(Assert::assertTrue);
//    }
//
//    @Test
//    public void testTracks() {
//        Observable.zip(
//                artistObservable.map(Artist::getTracks),
//                Observable.just(316, 97, 59),
//                Integer::equals
//        ).subscribe(Assert::assertTrue);
//    }
}
