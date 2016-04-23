package com.vorgoron.artistslist.model;

import android.content.Context;

import com.vorgoron.artistslist.BaseTest;
import com.vorgoron.artistslist.TestUtils;
import com.vorgoron.artistslist.exception.NoInternetConnectionException;
import com.vorgoron.artistslist.model.api.ArtistApi;
import com.vorgoron.artistslist.model.api.response.Artist;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import rx.Observable;

/**
 *  Тесты для проверки {@link DataManager}
 */
public class DataManagerTest extends BaseTest {

    @Inject
    Context context;
    @Inject
    ArtistApi artistApi;
    @Inject
    Cache cache;
    @Inject
    ConnectionManager connectionManager;

    private DataManager dataManager;
    private List<Artist> testArtistList;

    @Before
    public void setUp() throws Exception {
        getTestComponent().inject(this);
        testArtistList = TestUtils.getTestArtistList();
        dataManager = new DataManager(context, artistApi, cache, connectionManager);
    }

    /**
     * Тестируется случай, когда есть подключение к сети и загружается список
     *
     * @throws Exception
     */
    @Test
    public void testGetArtists() throws Exception {
        Mockito.when(connectionManager.isInternetConnected()).thenReturn(true);
        Mockito.when(artistApi.getArtists()).thenReturn(Observable.just(testArtistList));
        Mockito.when(cache.saveArtists(testArtistList)).thenReturn(Observable.just(testArtistList));

        Observable<List<Artist>> artists = dataManager.getArtists(true);

        Observable.zip(
                dataManager.getArtists(true),
                artists,
                (list, list2) -> Arrays.equals(list.toArray(), list2.toArray())
        ).subscribe(Assert::assertTrue);
    }

    /**
     * Тестируется случай, когда отсуствует подключение к сети, но список исполнителей
     * был уже закэширован
     *
     * @throws Exception
     */
    @Test
    public void testLoadNoInternetWithCache() throws Exception {
        Mockito.when(connectionManager.isInternetConnected()).thenReturn(false);
        Mockito.when(cache.getArtists()).thenReturn(Observable.just(testArtistList));

        Observable<List<Artist>> artists = dataManager.getArtists(true);

        Observable.zip(
                dataManager.getArtists(true),
                artists,
                (list, list2) -> Arrays.equals(list.toArray(), list2.toArray())
        ).subscribe(Assert::assertTrue);
    }

    /**
     * Тестируется случай, когда отсуствует подключение к сети и список исполнителей
     * еще не был закэширован
     *
     * @throws Exception
     */
    @Test
    public void testLoadNoInternetWithoutCache() throws Exception {
        Mockito.when(connectionManager.isInternetConnected()).thenReturn(false);
        Mockito.when(cache.getArtists()).thenReturn(Observable.just(Collections.emptyList()));

        dataManager
                .getArtists(true)
                .doOnError(throwable -> {
                    Assert.assertTrue(throwable instanceof NoInternetConnectionException);
                });
    }



    /**
     * Тестируется случай, когда есть подключение к сети и загружается список
     *
     * @throws Exception
     */
    @Test
    public void testGetArtist() throws Exception {
        Artist artist = testArtistList.get(0);
        Mockito.when(connectionManager.isInternetConnected()).thenReturn(true);
        Mockito.when(cache.getArtist(artist.getArtistId()))
                .thenReturn(Observable.just(artist));

        Observable<Artist> artistObservable = dataManager.getArtist(artist.getArtistId());

        Observable.zip(
                dataManager.getArtist(artist.getArtistId()),
                artistObservable,
                (artist1, artist2) -> artist1 == artist2
        ).subscribe(Assert::assertTrue);
    }
}