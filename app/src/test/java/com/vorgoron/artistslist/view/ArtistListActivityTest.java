package com.vorgoron.artistslist.view;

import com.vorgoron.artistslist.BaseTest;
import com.vorgoron.artistslist.R;
import com.vorgoron.artistslist.TestUtils;
import com.vorgoron.artistslist.adapter.ArtistAdapter;
import com.vorgoron.artistslist.model.Cache;
import com.vorgoron.artistslist.model.ConnectionManager;
import com.vorgoron.artistslist.model.DataManager;
import com.vorgoron.artistslist.model.api.response.Artist;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.robolectric.Robolectric;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import rx.Observable;

/**
 *  Тесты для проверки {@link ArtistsListActivity}
 *
 */
public class ArtistListActivityTest extends BaseTest {

    private static final int VISIBLE = 0;

    @Inject
    DataManager dataManager;
    @Inject
    Cache cache;
    @Inject
    ConnectionManager connectionManager;

    private ArtistsListActivity artistListActivity;

    private List<Artist> testArtistList;

    @Before
    public void setUp() throws Exception {
        getTestComponent().inject(this);

        testArtistList = TestUtils.getTestArtistList();
    }

    /**
     * Когда отсуствует подключение к сети должен отображаться
     * блок с кнопокой повторного подключения
     * @throws Exception
     */
    @Test
    public void testShowReattemptGroup() throws Exception {
        Mockito.when(dataManager.getArtists(true)).thenReturn(Observable.just(testArtistList));
        Mockito.when(cache.getArtists()).thenReturn(Observable.just(Collections.emptyList()));
        Mockito.when(connectionManager.isInternetConnected()).thenReturn(false);

        artistListActivity = Robolectric.setupActivity(ArtistsListActivity.class);

        int visibility = artistListActivity.findViewById(R.id.list).getVisibility();
        Assert.assertEquals(visibility, VISIBLE);
    }

    @Test
    public void testLoadArtists() throws Exception {
        Mockito.when(dataManager.getArtists(true)).thenReturn(Observable.just(testArtistList));

        artistListActivity = Robolectric.setupActivity(ArtistsListActivity.class);

        int visibility = artistListActivity.findViewById(R.id.list).getVisibility();
        Assert.assertEquals(visibility, VISIBLE);

        List<Artist> artistList = ((ArtistAdapter) artistListActivity.list.getAdapter()).getArtistList();
        Assert.assertArrayEquals(testArtistList.toArray(), artistList.toArray());
    }
}
