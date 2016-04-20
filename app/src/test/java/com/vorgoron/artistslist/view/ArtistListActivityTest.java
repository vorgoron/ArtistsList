package com.vorgoron.artistslist.view;

import android.content.Intent;
import android.view.View;

import com.vorgoron.artistslist.BaseTest;
import com.vorgoron.artistslist.R;
import com.vorgoron.artistslist.TestUtils;
import com.vorgoron.artistslist.adapter.ArtistAdapter;
import com.vorgoron.artistslist.model.DataManager;
import com.vorgoron.artistslist.model.api.response.Artist;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.robolectric.Robolectric;
import org.robolectric.Shadows;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

/**
 *  Тесты для проверки {@link ArtistsListActivity}
 *
 */
public class ArtistListActivityTest extends BaseTest {

    @Inject
    DataManager dataManager;

    private ArtistsListActivity artistListActivity;

    private List<Artist> testArtistList;

    @Before
    public void setUp() throws Exception {
        getTestComponent().inject(this);

        testArtistList = TestUtils.getTestArtistList();
        Mockito.when(dataManager.getArtists(true)).thenReturn(Observable.just(testArtistList));

        artistListActivity = Robolectric.setupActivity(ArtistsListActivity.class);
    }

    /**
     * При запуске приложения и при доступе к сети интернет должен отображаться
     * загруженный список исполнителей
     *
     * @throws Exception
     */
    @Test
    public void testLoadArtists() throws Exception {
        artistListActivity = Robolectric.setupActivity(ArtistsListActivity.class);

        View list = artistListActivity.findViewById(R.id.list);
        if (list != null) {
            Assert.assertEquals(list.getVisibility(), View.VISIBLE);
        }

        List<Artist> artistList = ((ArtistAdapter) artistListActivity.list.getAdapter()).getArtistList();
        Assert.assertArrayEquals(testArtistList.toArray(), artistList.toArray());
    }

    /**
     * Когда отсуствует подключение к сети должен отображаться
     * блок с кнопокой повторного подключения
     * @throws Exception
     */
    @Test
    public void testShowReattemptGroup() throws Exception {
        artistListActivity.showReattemptGroup();

        View reattemptGroup = artistListActivity.findViewById(R.id.reattempt_group);
        if (reattemptGroup != null) {
            Assert.assertEquals(reattemptGroup.getVisibility(), View.VISIBLE);
        }
    }

    /**
     * При загрузке пустого списка исполнителей отображается
     * соответствующая надпись
     *
     * @throws Exception
     */
    @Test
    public void testShowEmptyList() throws Exception {
        artistListActivity.showEmptyList();

        View emptyList = artistListActivity.findViewById(R.id.empty_list);
        if (emptyList != null) {
            Assert.assertEquals(emptyList.getVisibility(), View.VISIBLE);
        }
    }

    /**
     * При нажатии в списке на исполнителя открывается активити
     * с детальной информацией
     */
    @Test
    public void testOnClickArtist() {
        artistListActivity.onClickArtist(testArtistList.get(0));

        Intent intent = new Intent(artistListActivity, ArtistsDetailActivity.class);
        intent.putExtra(ArtistsDetailActivity.EXTRA_ARTIST_ID, testArtistList.get(0).getArtistId());
        Intent actualIntent = Shadows.shadowOf(artistListActivity).getNextStartedActivity();
        Assert.assertEquals(intent, actualIntent);
    }
}
