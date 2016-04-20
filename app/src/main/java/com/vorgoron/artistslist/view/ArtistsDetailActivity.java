package com.vorgoron.artistslist.view;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.vorgoron.artistslist.R;
import com.vorgoron.artistslist.presenter.ArtistsDetailPresenter;

import butterknife.Bind;
import nucleus.factory.RequiresPresenter;

/**
 * Активити, отображающая детальную информацию об исполнителе
 */
@RequiresPresenter(ArtistsDetailPresenter.class)
public class ArtistsDetailActivity extends BaseActivity<ArtistsDetailPresenter> {

    // ключ для получения id исполнителя
    public static final String EXTRA_ARTIST_ID = "extra_artist_id";

    @Bind(R.id.toolbar_layout)
    CollapsingToolbarLayout toolbarLayout;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.fab)
    FloatingActionButton fab;
    @Bind(R.id.big_cover)
    ImageView bigCover;
    @Bind(R.id.genre)
    TextView genre;
    @Bind(R.id.summary)
    TextView summary;
    @Bind(R.id.description)
    TextView description;
    @Bind(R.id.progress_bar)
    View progressBar;

    /**
     * Запуск активити с детальной информацией из другого активити
     *
     * @param activity активити - родитель
     * @param artistId id исполнителя, детальную информацию которого надо отобразить
     */
    public static void launch(FragmentActivity activity, int artistId) {
        Intent intent = new Intent(activity, ArtistsDetailActivity.class);
        intent.putExtra(EXTRA_ARTIST_ID, artistId);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artists_detail);
        setSupportActionBar(toolbar);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }

        if (savedInstanceState == null) {
            // получение id исполнителя и запуск загрузки информации об исполнителе
            int artistId = getIntent().getIntExtra(EXTRA_ARTIST_ID, 0);
            getPresenter().loadArtist(artistId);
            showProgress(true);
        }

        fab.setOnClickListener(view -> {
            String link = getPresenter().getLink();
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(link)));
        });
    }

    /**
     * Установка имени исполнителя
     *
     * @param name имя
     */
    public void setArtistName(String name) {
        toolbarLayout.setTitle(name);
    }

    /**
     * Установка изображения обложки
     *
     * @param imageUrl url изображения
     */
    public void setImage(String imageUrl) {
        Glide.with(this)
                .load(imageUrl)
                .centerCrop()
                .into(bigCover);
    }

    /**
     * Установка жанра
     *
     * @param text текст с жанрами
     */
    public void setGenre(String text) {
        genre.setText(text);
    }

    /**
     * Установка информации о количестве альбомов, песен
     *
     * @param text текст
     */
    public void setSummary(String text) {
        summary.setText(text);
    }

    /**
     * Установка описания об исполнителе
     *
     * @param description описание
     */
    public void setDescription(String description) {
        this.description.setText(description);
    }

    /**
     * Установка видимости кнопки перехода к сайту исполнителя.
     *
     * @param visibility видимость
     */
    public void setFabVisibility(int visibility) {
        fab.setVisibility(visibility);
    }

    /**
     * Реализация метода отображения индикатора загрузки
     *
     * @param show
     */
    @Override
    public void showProgress(boolean show) {
        progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
    }
}
