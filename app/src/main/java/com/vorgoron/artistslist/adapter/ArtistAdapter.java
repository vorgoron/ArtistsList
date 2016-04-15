package com.vorgoron.artistslist.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vorgoron.artistslist.R;
import com.vorgoron.artistslist.model.api.response.Artist;

import java.util.List;

public class ArtistAdapter extends RecyclerView.Adapter<ItemViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(Artist artist);
    }

    private Context context;
    private List<Artist> artistList;
    private OnItemClickListener itemClickListener;

    public ArtistAdapter(Context context, List<Artist> artistList) {
        this.context = context;
        this.artistList = artistList;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view_holder,
                parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder viewHolder, int position) {
        viewHolder.bindView(context, artistList.get(position), itemClickListener);
    }

    @Override
    public int getItemCount() {
        return artistList == null ? 0 : artistList.size();
    }

    public void setOnItemClickListener(OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }
}
