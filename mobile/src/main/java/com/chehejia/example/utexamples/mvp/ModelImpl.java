package com.chehejia.example.utexamples.mvp;

import android.os.AsyncTask;
import android.os.HandlerThread;

public class ModelImpl implements Contract.Model {

    private Contract.Presenter mPresenter = null;

    private int mCounter = 0;
    private String mWords = null;
    private boolean mIsIncreasing = false;

    private int mLongTimeWorkTimes = 0;

    @Override
    public Contract.Presenter presenter() {
        return mPresenter;
    }

    @Override
    public void presenter(Contract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public boolean isIncreasing() {
        return mIsIncreasing;
    }

    @Override
    public void isIncreasing(boolean value) {
        mIsIncreasing = value;
    }

    @Override
    public int counter() {
        return mCounter;
    }

    @Override
    public void counter(int value) {
        mCounter = value;
    }

    @Override
    public String words() {
        return mWords;
    }

    @Override
    public void words(String words) {
        mWords = words;
    }

    @Override
    public int longTimeWorkTimes() {
        return mLongTimeWorkTimes;
    }

    synchronized public void increaseLongTimeWorkTimes() {
        mLongTimeWorkTimes ++;
    }

    @Override
    public void doSomeLongTimeWork(final Runnable runnable) {

        Thread thread = new Thread(){
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                    increaseLongTimeWorkTimes();
                    runnable.run();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        };

        thread.start();
    }
}
