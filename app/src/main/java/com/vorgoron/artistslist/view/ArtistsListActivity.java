package com.vorgoron.artistslist.view;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

@RequiresPresenter(ArtistsListPresenter.class)
public class ArtistsListActivity extends BaseActivity<ArtistsListPresenter> {

    @Bind(R.id.reattempt_group)
    View reattemptGroup;
    @Bind(R.id.list)
    RecyclerView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artists_list);
        getPresenter().injectPresenter(this);
        getPresenter().loadArtists(this);
    }

    @OnClick(R.id.btn_retry_to_connect)
    public void retryConnect() {
        getPresenter().loadArtists(this);
    }

    public void setArtists(List<Artist> artists) {
        ArtistAdapter artistAdapter = new ArtistAdapter(this, artists);
        list.setLayoutManager(new LinearLayoutManager(this));
        list.addItemDecoration(new SimpleDividerItemDecoration(this));
        list.setAdapter(artistAdapter);
    }

    public void showReattemptGroup(boolean show) {
        reattemptGroup.setVisibility(show ? View.VISIBLE : View.GONE);
    }
}
