package com.vorgoron.artistslist.model;

import android.content.Context;
import android.net.ConnectivityManager;

import javax.inject.Inject;

/**
 * Класс для проверки подключения к интернету
 */
public class ConnectionManager {

    private Context context;

    @Inject
    public ConnectionManager(Context context) {
        this.context = context;
    }

    /**
     * Проверка наличия подключения к интернету
     */
    public boolean isInternetConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnectedOrConnecting();
    }
}