package com.vorgoron.artistslist.view;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.vorgoron.artistslist.R;
import com.vorgoron.artistslist.adapter.ArtistAdapter;
import com.vorgoron.artistslist.adapter.SimpleDividerItemDecoration;
import com.vorgoron.artistslist.model.api.response.Artist;
import com.vorgoron.artistslist.presenter.ArtistsListPresenter;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import nucleus.factory.RequiresPresenter;

/**
 * Активити, отображающая список исполнителей
 */
@RequiresPresenter(ArtistsListPresenter.class)
public class ArtistsListActivity extends BaseActivity<ArtistsListPresenter> {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    /**
     * Блок с кнопкой повторной загрузки исполнителей (отображается
     * при отсутствии подлкючения к сети)
     */
    @Bind(R.id.reattempt_group)
    View reattemptGroup;
    /**
     * Отображается если загружен пустой список
     */
    @Bind(R.id.empty_list)
    View emptyList;
    /**
     * View для отображения списка исполнителей
     */
    @Bind(R.id.list)
    RecyclerView list;
    @Bind(R.id.refresh_layout)
    SwipeRefreshLayout refreshLayout;
    @Bind(R.id.progress_bar)
    View progressBar;

    private ArtistAdapter artistAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artists_list);

        setSupportActionBar(toolbar);
        list.setLayoutManager(new LinearLayoutManager(this));
        list.addItemDecoration(new SimpleDividerItemDecoration(this));
        list.setHasFixedSize(true);

        refreshLayout.setOnRefreshListener(this::retryLoad);
        refreshLayout.setColorSchemeResources(R.color.colorAccent, R.color.colorPrimary);

        artistAdapter = new ArtistAdapter(this);
        artistAdapter.setOnItemClickListener(this::onClickArtist);
        list.setAdapter(artistAdapter);

        if (savedInstanceState == null) {
            getPresenter().loadArtists(this, false);
        }
    }

    /**
     * Повторить загрузку
     */
    @OnClick(R.id.btn_retry_to_load)
    public void retryLoad() {
        getPresenter().loadArtists(this, true);
    }

    /**
     * Заполнить список исполнителей
     *
     * @param artists список исполнителей
     */
    public void setArtists(List<Artist> artists) {
        artistAdapter.addAll(artists);
        refreshLayout.setRefreshing(false);
    }

    /**
     * При нажатии на позицию исполнителя открываем активити
     * с детальной информацией
     *
     * @param artist выбранный исполнитель
     */
    public void onClickArtist(Artist artist) {
        ArtistsDetailActivity.launch(this, artist.getArtistId());
    }

    /**
     * Реализация метода отображения индикатора загрузки
     *
     * @param show показать
     */
    @Override
    public void showProgress(boolean show) {
        if (show) {
            // если отображается индиактор обновления у refreshLayout,
            // тогда не отображаем дополнительно progressBar
            if (!refreshLayout.isRefreshing()) {
                progressBar.setVisibility(View.VISIBLE);
                emptyList.setVisibility(View.GONE);
                reattemptGroup.setVisibility(View.GONE);
                refreshLayout.setVisibility(View.GONE);
            }
        } else {
            refreshLayout.setRefreshing(false);
            progressBar.setVisibility(View.GONE);
        }
    }

    /**
     * Показать блок с кнопкой повторной загрузки исполнителей
     */
    public void showList() {
        refreshLayout.setVisibility(View.VISIBLE);
        emptyList.setVisibility(View.GONE);
        reattemptGroup.setVisibility(View.GONE);
    }

    /**
     * Показать блок с кнопкой повторной загрузки исполнителей
     */
    public void showReattemptGroup() {
        reattemptGroup.setVisibility(View.VISIBLE);
        emptyList.setVisibility(View.GONE);
        refreshLayout.setVisibility(View.GONE);
    }

    /**
     * Показать признак того, что список пустой
     */
    public void showEmptyList() {
        emptyList.setVisibility(View.VISIBLE);
        reattemptGroup.setVisibility(View.GONE);
        refreshLayout.setVisibility(View.GONE);
    }
}
