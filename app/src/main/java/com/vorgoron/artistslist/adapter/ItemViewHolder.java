package com.vorgoron.artistslist.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.vorgoron.artistslist.R;
import com.vorgoron.artistslist.model.api.response.Artist;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ItemViewHolder extends RecyclerView.ViewHolder {

    public static final String DELIMITER = ", ";

    @Bind(R.id.cover)
    ImageView cover;
    @Bind(R.id.title)
    TextView title;
    @Bind(R.id.genre)
    TextView genre;
    @Bind(R.id.summary)
    TextView summary;

    public ItemViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bindView(Context context, Artist item,
                         ArtistAdapter.OnItemClickListener itemClickListener) {
        Glide.with(context)
                .load(item.getCover().getSmall())
                .into(cover);

        title.setText(item.getName());
        genre.setText(item.getGenres());
        String albumsString = context.getResources().getQuantityString(R.plurals.albums,
                item.getAlbums(), item.getAlbums());
        String tracksString = context.getResources().getQuantityString(R.plurals.tracks,
                item.getTracks(), item.getTracks());
        summary.setText(albumsString + DELIMITER + tracksString);
        itemView.setOnClickListener(v -> itemClickListener.onItemClick(item));
    }
}
