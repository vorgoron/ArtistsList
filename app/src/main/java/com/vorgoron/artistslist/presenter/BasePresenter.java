package com.vorgoron.artistslist.presenter;

import android.os.Bundle;

import icepick.Icepick;
import nucleus.presenter.RxPresenter;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Базовый презентер для реализации шаблона MVP с библиотекой Nucleus
 *
 * @param <V> тип представления
 */
public abstract class BasePresenter<V> extends RxPresenter<V> {

    /**
     * Восстанавливаем состяние презентера с помощью библиотеки Icepick
     *
     * @param savedState
     */
    @Override
    protected void onCreate(Bundle savedState) {
        super.onCreate(savedState);
        Icepick.restoreInstanceState(this, savedState);
    }

    /**
     * Сохраняем состяние презентера с помощью библиотеки Icepick
     *
     * @param state
     */
    @Override
    protected void onSave(Bundle state) {
        super.onSave(state);
        Icepick.saveInstanceState(this, state);
    }

    /**
     * С помощью этого метода подписываемся на выполнение в фоновом потоке
     *
     * @return Observable.Transformer
     */
    <T> Observable.Transformer<T, T> applySchedulers() {
        return observable -> observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

}
