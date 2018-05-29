package com.chehejia.example.utexamples;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.chehejia.example.utexamples.mvp.Contract;
import com.chehejia.example.utexamples.mvp.ModelImpl;
import com.chehejia.example.utexamples.mvp.PresenterImpl;
import com.chehejia.example.utexamples.mvp.ViewImpl;

public class MainActivity extends AppCompatActivity {

    private Contract.View mView = null;
    private Contract.Presenter mPresenter = null;
    private Contract.Model mModel = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mView = new ViewImpl();

        mPresenter = new PresenterImpl();
        mPresenter.view(mView);

        mView.presenter(mPresenter);

        mModel = new ModelImpl();
        mModel.presenter(mPresenter);

        mPresenter.model(mModel);

        View root = findViewById(android.R.id.content);
        mView.bind(root);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mView.bind(null);
    }
}
