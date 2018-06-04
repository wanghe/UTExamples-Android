package com.chehejia.example.utexamples;

import com.chehejia.example.utexamples.mvp.Contract;
import com.chehejia.example.utexamples.mvp.ModelImpl;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class ModelImplTest {

    Contract.Model mTarget = null;

    @Before
    public void beforeTest() {
        mTarget = new ModelImpl();
    }

    @After
    public void afterTest() {
        mTarget = null;
    }

    @Test
    public void testPresenterSetterAndGetter() throws Exception {
            MockPresenter mock = new MockPresenter();

            assertNull("清理断言环境", mTarget.presenter());
            mTarget.presenter(mock);
            assertEquals("Model.presenter() 得到的实例应该与 Model.presenter(Presenter) 设置的值一致", mock, mTarget.presenter());
    }

    @Test
    public void testCounterSetterAndGetter() throws Exception {

        int[] expects = new int[] {0, 1, Integer.MIN_VALUE, Integer.MAX_VALUE};

        for (int expect : expects) {
            mTarget.counter(expect);
            assertEquals("mTarget.counter() 得到的值应该与之前设置的值一致", expect, mTarget.counter());
        }
    }

    @Test
    public void testWordsSetterAndGetter() throws Exception {
        String[] expects = new String[] {
                "a",
                "abcdefghijklmnopqrstuvwxyz"
        };

        for (String expect : expects) {
            mTarget.words(expect);
            assertEquals("mTarget.words() 得到的值应该与之前设置的值一致", expect, mTarget.words());
        }

    }

    @Test
    public void testIsIncreasing() throws Exception {
        assertEquals("清理一下断言环境", false, mTarget.isIncreasing());


        mTarget.isIncreasing(true);
        assertEquals("mTarget.isIncreasing() 得到的值应该与之前设置的值一致",true, mTarget.isIncreasing());
    }

    @Test
    public void testDoSomeLongTimeWork() throws Exception {

        int MAX_TIMES = 10;

        final CountDownLatch signal = new CountDownLatch(MAX_TIMES);
        Runnable callback = new Runnable() {
            @Override
            public void run() {
                signal.countDown();
            }
        };


        assertEquals("清理一下断言环境", 0, mTarget.longTimeWorkTimes());
        for (int expect = 0; expect < MAX_TIMES; expect ++) {
            mTarget.doSomeLongTimeWork(callback);
        }
        signal.await(10, TimeUnit.SECONDS);
        assertEquals("Model.doSomeLongTimeWorks() 运行次数需要与 Model.longTimeWorkTimes() 一致",
                MAX_TIMES, mTarget.longTimeWorkTimes());


    }

    static class MockPresenter implements Contract.Presenter {

        public Contract.View mView = null;
        public String words = null;

        @Override
        public Contract.View view() {
            return mView;
        }

        @Override
        public void view(Contract.View view) {
            mView = view;
        }

        @Override
        public Contract.Model model() {
            return null;
        }

        @Override
        public void model(Contract.Model model) {

        }

        @Override
        public void changeCounter() {
            if (model().isIncreasing()) {
                model().counter(model().counter() + 1);
            } else {
                model().counter(model().counter() - 1);
            }
        }

        @Override
        public void changeCountingWay(boolean isIncreasing) {
            model().isIncreasing(isIncreasing);
        }

        @Override
        public void saySomething(String text) {
            words = text;
        }
    }
}
