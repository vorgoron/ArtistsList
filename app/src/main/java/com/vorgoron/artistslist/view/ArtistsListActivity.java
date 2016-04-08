package com.vorgoron.artistslist.view;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.vorgoron.artistslist.R;
import com.vorgoron.artistslist.adapter.ArtistAdapter;
import com.vorgoron.artistslist.model.api.response.Artist;
import com.vorgoron.artistslist.presenter.ArtistsListPresenter;

import java.util.List;

import butterknife.Bind;
import nucleus.factory.RequiresPresenter;

@RequiresPresenter(ArtistsListPresenter.class)
public class ArtistsListActivity extends BaseActivity<ArtistsListPresenter> {

    @Bind(R.id.list)
    RecyclerView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artists_list);
        getPresenter().injectPresenter(this);
        getPresenter().start(ArtistsListPresenter.GET_ARTISTS);
        showProgress();
    }

    public void setArtists(List<Artist> artists) {
        ArtistAdapter artistAdapter = new ArtistAdapter(this, artists);
        list.setLayoutManager(new LinearLayoutManager(this));
        list.setAdapter(artistAdapter);
        hideProgress();
    }
}
