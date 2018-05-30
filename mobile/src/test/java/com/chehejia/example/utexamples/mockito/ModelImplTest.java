package com.chehejia.example.utexamples.mockito;

import com.chehejia.example.utexamples.mvp.Contract;
import com.chehejia.example.utexamples.mvp.ModelImpl;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mockito;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class ModelImplTest {

    ModelImpl target = null;

    @Before
    public void setup() {
        target = new ModelImpl();
    }

    @After
    public void teardown() {
        target = null;
    }

    @Test
    public void testPresenterSetterAndGetter() throws Exception {
        Contract.Presenter mock = Mockito.mock(Contract.Presenter.class);

        assertNull("初始化时 Model.presenter() 的值应为 null", target.presenter());
        target.presenter(mock);
        assertEquals("Model.presenter() 获取的实例应该与 Model.presenter(Presenter)设置的实例一致",
                mock, target.presenter());
    }

    @Test
    public void testCounterSetterAndGetter() throws Exception {

        assertEquals("初始化时 Model.counter() 的值应为 0", 0, target.counter());

        int MAX_TIMES = 99;
        for (int expect = 1; expect < MAX_TIMES; expect ++) {
            target.counter(expect);
            assertEquals("Model.counter() 获取的值应该与 Model.counter(int) 设置的值一致",
                    expect, target.counter());
        }
    }

    @Test
    public void testWordsSetterAndGetter() throws Exception {
        assertNull("初始化时 Model.words() 的值应为 null", target.words());
        String[] CASES = new String[] {
                "blah. blah. blah",
                "ABCDEFGHIJKLMNOPQRSTUVWXYZ|abcdefghijklmnopqrstuvwxyz"
        };

        for (String expect : CASES) {
            target.words(expect);
            assertEquals("Model.words() 获取的值应该与 Model.words(String) 设置的值一致",
                    expect, target.words());
        }
    }

    @Test
    public void testIsIncreasingSetterAndGetter() throws Exception {
        assertEquals("初始化时 Model.isIncreasing() 的值应为 false", false, target.isIncreasing());

        boolean[] CASES = new boolean[] {
                true, false, false, true, true, false, true, false, true, false
        };

        for (boolean expect : CASES) {
            target.isIncreasing(expect);
            assertEquals("Model.isIncreasing() 获取的值应该与 Model.isIncreasing(boolean) 设置的值一致",
                    expect, target.isIncreasing());
        }
    }

    @Test
    public void testDoSomeLongTimeWork() throws Exception {

        int EXPECT_TIMES = 10;

        final CountDownLatch signal = new CountDownLatch(EXPECT_TIMES);

        for (int time = 0; time < EXPECT_TIMES; time ++) {
            target.doSomeLongTimeWork(new Runnable() {
                @Override
                public void run() {
                    signal.countDown();
                }
            });
        }

        signal.await(EXPECT_TIMES * 1000, TimeUnit.MILLISECONDS);
        assertEquals("Model.doSomeLongTimeWork(Runnable) 的回调需要被调用" + EXPECT_TIMES + "次",
                EXPECT_TIMES, target.longTimeWorkTimes());
    }
}
