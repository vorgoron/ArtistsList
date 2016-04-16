package com.vorgoron.artistslist.view;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.widget.Toast;

import com.vorgoron.artistslist.R;
import com.vorgoron.artistslist.presenter.BasePresenter;

import butterknife.ButterKnife;
import nucleus.view.NucleusAppCompatActivity;

/**
 * Базовое представление для реализации шаблона MVP с библиотекой Nucleus
 *
 * @param <P> тип презентера
 */
public class BaseActivity<P extends BasePresenter> extends NucleusAppCompatActivity<P> {

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // инициализируем диалог для отображения процесса загрузки
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getString(R.string.progress_dialog_message));
        progressDialog.setCancelable(false);
    }

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
     * Показать диалог загрузки
     */
    public void showProgress() {
        progressDialog.show();
    }

    /**
     * Спрятать диалог загрузки
     */
    public void hideProgress() {
        progressDialog.dismiss();
    }

    /**
     * Обработчик исключений
     *
     * @param throwable объект исключения
     */
    public void onError(Throwable throwable) {
        throwable.printStackTrace();
        hideProgress();
        showToast(throwable.getLocalizedMessage());
    }
}
