package com.chehejia.example.utexamples;

import android.view.View;

import com.chehejia.example.utexamples.mvp.Contract;
import com.chehejia.example.utexamples.mvp.ModelImpl;
import com.chehejia.example.utexamples.mvp.PresenterImpl;
import com.chehejia.example.utexamples.mvp.ViewImpl;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class PresenterImplTest {

    private PresenterImpl mTarget = null;

    @Before public void beforeTest() {
        mTarget = new PresenterImpl();
    }

    @After public void afterTest() {
        mTarget = null;
    }


    @Test
    public void testViewGetterAndSetter() throws Exception {

        Contract.View mExpect = new MockView();
        mTarget.view(mExpect);


        assertEquals("PresenterImpl.getView() 得到的实例应该与之前 PresenterImpl.setView() 设置的值一致",
                mExpect, mTarget.view());


        mTarget.view(null);
        assertNull(mTarget.view());
    }

    @Test
    public void testModelGetterAndSetter() throws Exception {
        Contract.Model mExpect = new MockModel();
        mTarget.model(mExpect);


        assertEquals("PresenterImpl.getModel() 得到的实例应该与之前 PresenterImpl.setModel() 设置的值一致",
                mExpect, mTarget.model());

        mTarget.model(null);
        assertNull(mTarget.model());
    }


    @Test
    public void testIncreaseCounter() throws Exception {
        MockModel mockModel = new MockModel();
        mTarget.model(mockModel);

        MockView mockView = new MockView();
        mTarget.view(mockView);

        // 缓存 Model.counter() 的值
        int expect = mockModel.counter();

        mTarget.changeCountingWay(true);
        mTarget.changeCounter();
        assertEquals("PresenterImpl.increaseCounter() 应该让 Model.counter() 的值 +1", ++ expect, mockModel.counter());
        assertEquals("PresenterImpl.increaseCounter() 应该给 View.displayCounter() 一样的值", expect, mockView.mDisplayCounter);

    }

    @Test
    public void testDecreaseCounter() throws Exception {
        MockModel mockModel = new MockModel();
        mTarget.model(mockModel);

        MockView mockView = new MockView();
        mTarget.view(mockView);

        // 缓存 Model.counter() 的值
        int expect = mockModel.counter();


        mTarget.changeCountingWay(false);
        mTarget.changeCounter();
        assertEquals("PresenterImpl.increaseCounter() 应该让 Model.counter()的值 -1", -- expect, mockModel.counter());
        assertEquals("PresenterImpl.increaseCounter() 应该给 View.displayCounter() 一样的值", expect, mockView.mDisplayCounter);
    }

    @Test
    public void testSaySomething() throws Exception {

        MockModel mockModel = new MockModel();
        mTarget.model(mockModel);

        MockView mockView = new MockView();
        mTarget.view(mockView);


        // 设置预期值
        String expect = "blah blah blah...";

        mTarget.saySomething(expect);

        // 验证预期值与真实值是否一致.
        assertEquals("PresenterImpl.saySomething() 传入的参数应该给到 Model.words()", expect, mockModel.words());
        assertEquals("PresenterImpl.saySomething() 传入的参数应该给到 View.displayWords()", expect, mockView.mDisplayWords);
    }


    static class MockModel implements Contract.Model {
        public Contract.Presenter mPresenter = null;
        public int mCounter = 0;
        public String mWord = null;
        public boolean mIsIncreasing = false;


        @Override
        public Contract.Presenter presenter() {
            return mPresenter;
        }

        @Override
        public void presenter(Contract.Presenter presenter) {
            mPresenter = presenter;
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
            return mWord;
        }

        @Override
        public void words(String words) {
            mWord = words;
        }

        @Override
        public int longTimeWorkTimes() {
            return 0;
        }

        @Override
        public void doSomeLongTimeWork(Runnable runnable) {

        }

        @Override
        public boolean isIncreasing() {
            return mIsIncreasing;
        }

        @Override
        public void isIncreasing(boolean value) {
            mIsIncreasing = value;
        }
    }

    static class MockView implements Contract.View {

        public Contract.Presenter mPresenter = null;
        public String mDisplayWords = null;
        public int mDisplayCounter = 0;

        @Override
        public void bind(View root) {

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
        public void onChangeSwitch(boolean on) {

        }

        @Override
        public void onPressButton() {

        }

        @Override
        public void onInputText(String text) {

        }

        @Override
        public void displayWords(String text) {
            mDisplayWords = text;
        }

        @Override
        public void displayCounter(int number) {
            mDisplayCounter = number;
        }
    }
}
