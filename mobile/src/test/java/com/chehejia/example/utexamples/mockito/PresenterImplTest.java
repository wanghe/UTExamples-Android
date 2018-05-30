package com.chehejia.example.utexamples.mockito;

import com.chehejia.example.utexamples.mvp.Contract;
import com.chehejia.example.utexamples.mvp.PresenterImpl;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mockito;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class PresenterImplTest {

    Contract.Presenter target = null;

    @Before
    public void setup() {
        target = new PresenterImpl();
    }

    @After
    public void teardown() {
        target = null;
    }

    @Test
    public void testModelViewGetterAndSetter() throws Exception {
        Contract.Model mockModel = Mockito.mock(Contract.Model.class);
        Contract.View mockView = Mockito.mock(Contract.View.class);

        assertNull("初始化 Presenter.model() 值应为 null", target.model());
        assertNull("初始化 Presenter.view() 值应为 null", target.view());

        target.model(mockModel);
        assertEquals("Presenter.model() 得到的实例应与 Presenter.model(Model) 设置的值一致",
                mockModel, target.model());

        target.view(mockView);
        assertEquals("Presenter.view() 得到的实例应与 Presenter.view(View) 设置的值一致",
                mockView, target.view());
    }

    @Test
    public void testChangeCountingWay() throws Exception {
        Contract.Model mock = Mockito.mock(Contract.Model.class);
        target.model(mock);

        // 断言 Presenter.changeCountingWay(boolean) 最终会调用 Model.isIncreasing(boolean);
        target.changeCountingWay(true);
        Mockito.verify(mock).isIncreasing(true);

        target.changeCountingWay(false);
        Mockito.verify(mock).isIncreasing(false);
    }

    @Test
    public void testChangeCounter() throws Exception {
        Contract.Model mockModel = Mockito.mock(Contract.Model.class);
        target.model(mockModel);

        Contract.View mockView = Mockito.mock(Contract.View.class);
        target.view(mockView);

        // CASE 1: Presenter.changeCounter() 会减小 counter
        Mockito.when(mockModel.isIncreasing()).thenReturn(false);
        Mockito.when(mockModel.counter()).thenReturn(0).thenReturn(-1);
        target.changeCounter();
        Mockito.verify(mockModel).counter(-1);
        Mockito.verify(mockView).displayCounter(-1);

        // CASE 2: Presenter.changeCounter() 会增加 counter
        Mockito.when(mockModel.isIncreasing()).thenReturn(true);
        Mockito.when(mockModel.counter()).thenReturn(0).thenReturn(1);
        target.changeCounter();
        Mockito.verify(mockModel).counter(1);
        Mockito.verify(mockView).displayCounter(1);
    }

    @Test
    public void testSaySomething() throws Exception {
        Contract.Model mockModel = Mockito.mock(Contract.Model.class);
        target.model(mockModel);

        Contract.View mockView = Mockito.mock(Contract.View.class);
        target.view(mockView);


        // CASE 1: Presenter.saySomething(String) 会更新 Model.words() 的值并且调用 View.displayWords(String);
        String words = "blah blah blah";
        Mockito.when(mockModel.words()).thenReturn(words);
        target.saySomething(words);
        Mockito.verify(mockModel).words(words);
        Mockito.verify(mockView).displayWords(words);
    }
}
