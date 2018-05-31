package com.chehejia.example.utexamples.mockito;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.chehejia.example.utexamples.R;
import com.chehejia.example.utexamples.mvp.Contract;
import com.chehejia.example.utexamples.mvp.ViewImpl;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import static org.junit.Assert.*;

@RunWith(RobolectricTestRunner.class)
public class ViewImplTest {

    Contract.View target = null;

    @Before
    public void setup() {
        target = new ViewImpl();
    }

    @After
    public void teardown() {
        target = null;
    }

    @Test
    public void testPresenterGetterAndSetter() throws Exception {
        Contract.Presenter mock = Mockito.mock(Contract.Presenter.class);

        assertNull("初始化 View.presenter() 值应为 null.", target.presenter());
        target.presenter(mock);
        assertEquals("View.presenter() 获取的实例应与 View.presenter(Presenter) 设置的实例一致",
                mock, target.presenter());
    }

    @Test
    public void testPressButton() throws Exception {
        Contract.Presenter mock = Mockito.mock(Contract.Presenter.class);
        target.presenter(mock);


        // CASE 1: View.pressButton() 会调用 Presenter.changeCounter()
        target.onPressButton();
        Mockito.verify(mock).changeCounter();
    }

    @Test
    public void testChangeSwitch() throws Exception {
        Contract.Presenter mock = Mockito.mock(Contract.Presenter.class);
        target.presenter(mock);

        // CASE 1: View.changeSwitch(boolean) 会调用 Presenter.changeCountingWay(boolean) 接口
        target.onChangeSwitch(true);
        Mockito.verify(mock).changeCountingWay(true);

        // CASE 2: View.changeSwitch(boolean) 会调用 Presenter.changeCountingWay(boolean) 接口
        target.onChangeSwitch(false);
        Mockito.verify(mock).changeCountingWay(false);
    }

    @Test
    public void testInputText() throws Exception {
        Contract.Presenter mock = Mockito.mock(Contract.Presenter.class);
        target.presenter(mock);

        // CASE: View.inputText(String) 会调用 Presenter.saySomething(String) 接口
        String EXPECT = "blah baba blah blah";
        target.onInputText(EXPECT);
        Mockito.verify(mock).saySomething(EXPECT);
    }

    @Test
    public void testDisplayWordsWithBind() throws Exception {


        try {
            String EXPECT = "blah blah blah";
            target.displayWords(EXPECT);
            fail("调用 View.displayWords(String) 之前未调用 View.bind(View) 应该抛出 NullPointerException");
        } catch (NullPointerException e) {

        }

        MockActivity activity = Robolectric.setupActivity(MockActivity.class);
        View root = activity.findViewById(android.R.id.content);
        target.bind(root);
        try {
            String EXPECT = "alalalala alalala alala";
            target.displayWords(EXPECT);
            TextView tv = root.findViewById(R.id.tvWords);
            assertEquals("调用 View.displayWords(String) 设置的值应该与 R.id.tvWords 对应的 TextView.getText() 值一致",
                    EXPECT, tv.getText().toString());
        } catch (NullPointerException e) {
            fail("调用 View.displayWords(String) 之前调用 View.bind(View) 不应该抛出 NullPointerException");
        }
    }

    @Test
    public void testDisplayCounterWithBind() throws Exception {
        try {
            int EXPECT = 999;
            target.displayCounter(EXPECT);
            fail("调用 View.displayCounter(String) 之前未调用 View.bind(View) 应该抛出 NullPointerException");
        } catch (NullPointerException e) {

        }

        MockActivity activity = Robolectric.setupActivity(MockActivity.class);
        View root = activity.findViewById(android.R.id.content);
        target.bind(root);
        try {
            int Counter = 3;
            String EXPECT = "Counting: " + Counter;

            target.displayCounter(Counter);
            TextView tv = root.findViewById(R.id.tvCounter);
            assertEquals("调用 View.displayCounter(int) 设置的值应该与 R.id.tvCounter 对应的 TextView.getText() 值一致",
                    EXPECT, tv.getText().toString());
        } catch (NullPointerException e) {
            fail("调用 View.displayCounter(int) 之前调用 View.bind(View) 不应该抛出 NullPointerException");
        }
    }

    static class MockActivity extends AppCompatActivity {
        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
        }
    }

}
