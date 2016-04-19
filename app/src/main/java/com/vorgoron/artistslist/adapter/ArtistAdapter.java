package com.vorgoron.artistslist.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vorgoron.artistslist.R;
import com.vorgoron.artistslist.model.api.response.Artist;

import java.util.List;

import lombok.Getter;

/**
 * Адаптер для отображения списка исполнителей в {@link RecyclerView} .
 */
public class ArtistAdapter extends RecyclerView.Adapter<ItemViewHolder> {

    /**
     * Интерфейс для обработки нажатий на позиции списка
     */
    public interface OnItemClickListener {
        /**
         * Обработка при нажатии на позицию
         *
         * @param artist Выбранный исполнитель
         */
        void onItemClick(Artist artist);
    }

    private Context context;
    @Getter
    private List<Artist> artistList;
    private OnItemClickListener itemClickListener;

    /**
     * Инициализация адаптера
     *
     * @param context    context
     * @param artistList список исполнителей
     */
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

    /**
     * Установка listener'а нажатий на список исполнителей
     *
     * @param itemClickListener listener нажатия на позицию
     */
    public void setOnItemClickListener(OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }
}
