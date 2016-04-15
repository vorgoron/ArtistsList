package com.vorgoron.artistslist.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.vorgoron.artistslist.R;
import com.vorgoron.artistslist.presenter.ArtistsDetailPresenter;

import butterknife.Bind;
import nucleus.factory.RequiresPresenter;

@RequiresPresenter(ArtistsDetailPresenter.class)
public class ArtistsDetailActivity extends BaseActivity<ArtistsDetailPresenter> {

    private static final String EXTRA_ARTIST_ID = "extra_artist_id";

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

    public static void launch(FragmentActivity activity, int artistId) {
        Intent intent = new Intent(activity, ArtistsDetailActivity.class);
        intent.putExtra(EXTRA_ARTIST_ID, artistId);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getPresenter().injectPresenter(this);
        setContentView(R.layout.activity_artists_detail);
        setSupportActionBar(toolbar);

        int artistId = getIntent().getIntExtra(EXTRA_ARTIST_ID, 0);
        getPresenter().loadArtist(artistId);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public void setArtistName(String name) {
        toolbarLayout.setTitle(name);
    }

    public void setImage(String imageUrl) {
        Glide.with(this)
                .load(imageUrl)
                .centerCrop()
                .into(bigCover);
    }

    public void setGenre(String text) {
        genre.setText(text);
    }

    public void setSumary(String text) {
        summary.setText(text);
    }

    public void setDescription(String description) {
        this.description.setText(description);
    }
}
