package com.chehejia.example.utexamples.mvp;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.chehejia.example.utexamples.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
import butterknife.OnTextChanged;
import butterknife.Unbinder;

public class ViewImpl implements Contract.View {

    private Contract.Presenter mPresenter = null;


    private Unbinder mUnbinder = null;

    @BindView(R.id.tvCounter) TextView tvCounter;
    @BindView(R.id.tvWords) TextView tvWords;

    @BindView(R.id.etInput) EditText etInput;

    @OnClick(R.id.btnDoSomething) void onButtonPressed() {
        pressButton();
    }

    @OnCheckedChanged(R.id.swSwitch1) void onSwitchChanged(boolean checked) {
        changeSwitch(checked);
    }

    @OnTextChanged(R.id.etInput) void onTextsChanged(CharSequence txt, int a, int b, int c) {
        inputText(txt.toString());
    }



    public void bind(View rootView) {

        if (null != mUnbinder) {
            mUnbinder.unbind();
            mUnbinder = null;
        }

        if (null != rootView) {
            mUnbinder = ButterKnife.bind(this, rootView);
        }
    }

    @Override
    public Contract.Presenter presenter() {
        return mPresenter;
    }

    @Override
    public void presenter(Contract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void changeSwitch(boolean on) {
        presenter().changeCountingWay(on);
    }

    @Override
    public void pressButton() {
        presenter().changeCounter();
    }

    @Override
    public void inputText(String text) {
        presenter().saySomething(text);
    }

    @Override
    public void displayWords(String text) {
        tvWords.setText(text);
    }

    @Override
    public void displayCounter(int number) {
        tvCounter.setText("Counting: " + String.valueOf(number));
    }
}
