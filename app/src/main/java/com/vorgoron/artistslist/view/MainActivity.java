package com.vorgoron.artistslist.view;

import android.os.Bundle;

import com.vorgoron.artistslist.R;
import com.vorgoron.artistslist.presenter.MainPresenter;

import nucleus.factory.RequiresPresenter;

@RequiresPresenter(MainPresenter.class)
public class MainActivity extends BaseActivity<MainPresenter> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
}
