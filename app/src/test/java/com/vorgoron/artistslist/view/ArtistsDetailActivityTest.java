package com.vorgoron.artistslist.view;

import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;

import com.vorgoron.artistslist.BaseTest;
import com.vorgoron.artistslist.R;
import com.vorgoron.artistslist.TestUtils;
import com.vorgoron.artistslist.model.api.response.Artist;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.robolectric.Robolectric;
import org.robolectric.Shadows;

/**
 *  Тесты для проверки {@link ArtistsDetailActivity}
 *
 */
public class ArtistsDetailActivityTest extends BaseTest {

    private ArtistsDetailActivity artistsDetailActivity;
    private Artist artist;

    /**
     * Первоначальная настройка
     *
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        artist = TestUtils.getTestArtistList().get(0);
        // т.к. модели, получаемые из сети, перед сохранением в кэш модифицируются
        // необходимо преобразовать модель
        artist.setGenres(TextUtils.join(", ", artist.getGenresList()));
        artistsDetailActivity = Robolectric.setupActivity(ArtistsDetailActivity.class);
    }

    /**
     * Проверка установки имени исполнителя
     *
     * @throws Exception
     */
    @Test
    public void testSetTitle() throws Exception {
        artistsDetailActivity.setArtistName(artist.getName());
        Assert.assertEquals(artist.getName(), artistsDetailActivity.toolbarLayout.getTitle());
    }

    /**
     * Проверка установки списка жанров
     *
     * @throws Exception
     */
    @Test
    public void testSetGenres() throws Exception {
        artistsDetailActivity.setGenre(artist.getGenres());
        Assert.assertEquals(artist.getGenres(), artistsDetailActivity.genre.getText());
    }

    /**
     * Проверка установки информации о количестве альбомов, песен
     *
     * @throws Exception
     */
    @Test
    public void testSetSummary() throws Exception {
        Resources resources = artistsDetailActivity.getResources();
        String albumsString = resources
                .getQuantityString(R.plurals.albums, artist.getAlbums(), artist.getAlbums());
        String tracksString = resources
                .getQuantityString(R.plurals.tracks, artist.getTracks(), artist.getTracks());
        String expectedSummary = resources.getString(R.string.artists_detail_summary, albumsString, tracksString);

        artistsDetailActivity.setSummary(artist.getAlbums(), artist.getTracks());
        Assert.assertEquals(expectedSummary, artistsDetailActivity.summary.getText());
    }

    /**
     * Проверка установки описания об исполнителе
     *
     * @throws Exception
     */
    @Test
    public void testSetDescription() throws Exception {
        artistsDetailActivity.setDescription(artist.getDescription());
        Assert.assertEquals(artist.getDescription(), artistsDetailActivity.description.getText());
    }

    /**
     * Проверка установки видимости кнопки перехода к сайту исполнителя.
     *
     * @throws Exception
     */
    @Test
    public void testFabVisibility() throws Exception {
        int fabVisibility = artist.getLink() != null ? View.VISIBLE : View.GONE;
        artistsDetailActivity.setFabVisibility(fabVisibility);
        Assert.assertTrue(artistsDetailActivity.fab.getVisibility() == fabVisibility);
    }

    /**
     * Проверка установки видимости индикатора загрузки
     *
     * @throws Exception
     */
    @Test
    public void testShowProgress() throws Exception {
        artistsDetailActivity.showProgress(true);
        Assert.assertTrue(artistsDetailActivity.progressBar.getVisibility() == View.VISIBLE);
    }

    /**
     * Проверка открытия веб-страницы исполнителя
     *
     * @throws Exception
     */
    @Test
    public void testOpenLink() throws Exception {
        artistsDetailActivity.openLink(artist.getLink());

        Intent expectedIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(artist.getLink()));
        Intent actualIntent = Shadows.shadowOf(artistsDetailActivity).getNextStartedActivity();
        Assert.assertEquals(expectedIntent, actualIntent);
    }

}