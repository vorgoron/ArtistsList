package com.vorgoron.artistslist.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.vorgoron.artistslist.R;
import com.vorgoron.artistslist.model.api.response.Artist;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ItemViewHolder extends RecyclerView.ViewHolder {

    @Bind(R.id.cover)
    ImageView cover;
    @Bind(R.id.title)
    TextView title;
    @Bind(R.id.genre)
    TextView genre;

    public ItemViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bindView(Context context, Artist item) {
        Glide.with(context)
                .load(item.getCover().getSmall())
                .into(cover);

        title.setText(item.getName());
        genre.setText(TextUtils.join(", ", item.getGenres()));
    }
}
