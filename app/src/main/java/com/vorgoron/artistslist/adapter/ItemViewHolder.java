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

/**
 * Класс, отвечающий за отображение информации об исполнителе в списке
 */
public class ItemViewHolder extends RecyclerView.ViewHolder {

    @Bind(R.id.cover)
    ImageView cover;
    @Bind(R.id.title)
    TextView title;
    @Bind(R.id.genre)
    TextView genre;
    @Bind(R.id.summary)
    TextView summary;

    /**
     * Инициализация ViewHolder
     *
     * @param itemView view позиции
     */
    public ItemViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    /**
     * Связывание данных с представлением
     *
     * @param context           context
     * @param artist            исполнитель
     * @param itemClickListener listener нажатий на позицию
     */
    public void bindView(Context context, Artist artist,
                         ArtistAdapter.OnItemClickListener itemClickListener) {
        Glide.with(context)
                .load(artist.getCover().getSmall())
                .into(cover);

        title.setText(artist.getName());
//        genre.setText(artist.getGenres());
        String albumsString = context.getResources().getQuantityString(R.plurals.albums,
                artist.getAlbums(), artist.getAlbums());
        String tracksString = context.getResources().getQuantityString(R.plurals.tracks,
                artist.getTracks(), artist.getTracks());
        summary.setText(context.getString(R.string.artists_list_summary, albumsString, tracksString));
        itemView.setOnClickListener(v -> itemClickListener.onItemClick(artist));
    }
}
