package com.vorgoron.artistslist.view;

import android.support.annotation.StringRes;
import android.widget.Toast;

import com.vorgoron.artistslist.presenter.BasePresenter;

import butterknife.ButterKnife;
import nucleus.view.NucleusAppCompatActivity;

/**
 * Базовое представление для реализации шаблона MVP с библиотекой Nucleus
 *
 * @param <P> тип презентера
 */
public abstract class BaseActivity<P extends BasePresenter> extends NucleusAppCompatActivity<P> {

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    /**
     * Отображение тоаста
     *
     * @param message сообщение, которое необходимо отобразить
     */
    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    /**
     * Отображение тоаста
     *
     * @param resId Строковый ресурс, в котором хранится сообщение для отображения
     */
    public void showToast(@StringRes int resId) {
        Toast.makeText(this, resId, Toast.LENGTH_SHORT).show();
    }

    /**
     * Показать индикатор загрузки
     */
    public abstract void showProgress(boolean show);

    /**
     * Обработчик ошибок
     *
     * @param throwable объект исключения
     */
    public void onError(Throwable throwable) {
        throwable.printStackTrace();
        showProgress(false);
        showToast(throwable.getLocalizedMessage());
    }
}
