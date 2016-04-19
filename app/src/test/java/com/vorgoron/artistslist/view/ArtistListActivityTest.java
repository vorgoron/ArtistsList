package com.vorgoron.artistslist.view;

import com.vorgoron.artistslist.BaseTest;
import com.vorgoron.artistslist.TestUtils;
import com.vorgoron.artistslist.adapter.ArtistAdapter;
import com.vorgoron.artistslist.model.Cache;
import com.vorgoron.artistslist.model.api.response.Artist;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.robolectric.Robolectric;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

public class ArtistListActivityTest extends BaseTest {

    @Inject
    Cache cacheMock;

    private ArtistsListActivity artistListActivity;

    private List<Artist> testArtistList;

    @Before
    public void setUp() throws Exception {
        getTestComponent().inject(this);

        testArtistList = TestUtils.getTestArtistList();

        Mockito.when(cacheMock.getArtists()).thenReturn(Observable.just(testArtistList));

        artistListActivity = Robolectric.setupActivity(ArtistsListActivity.class);
    }

    @Test
    public void testSetArtists() throws Exception {
        artistListActivity.setArtists(testArtistList);

        List<Artist> artistList = ((ArtistAdapter) artistListActivity.list.getAdapter()).getArtistList();
        Assert.assertArrayEquals(testArtistList.toArray(), artistList.toArray());
    }

    @Test
    public void testLoadArtists() throws Exception {
        List<Artist> artistList = ((ArtistAdapter) artistListActivity.list.getAdapter()).getArtistList();
        Assert.assertArrayEquals(testArtistList.toArray(), artistList.toArray());
    }
}
