package com.chehejia.example.utexamples;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.chehejia.example.utexamples.mvp.Contract;
import com.chehejia.example.utexamples.mvp.ViewImpl;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import static org.junit.Assert.*;

@RunWith(RobolectricTestRunner.class)
public class ViewImplTest {

    ViewImpl mTarget = null;

    @Before
    public void beforeTest() {
        mTarget = new ViewImpl();
    }

    @After
    public void afterTest() {
        mTarget = null;
    }

    @Test
    public void testPresenterGetterAndSetter() throws Exception {
        MockPresenter mock = new MockPresenter();
        mTarget.presenter(mock);

        assertEquals("View.presenter() 得到的实例应该与 View.presenter(Presenter) 设置的是同一个 ", mock, mTarget.presenter());
    }

    @Test
    public void testInputTest() throws Exception {
        MockPresenter mock = new MockPresenter();
        mTarget.presenter(mock);

        assertEquals("保持断言环境是干净的", null, mock.words);

        String expect = "blah blah blah";
        mTarget.onInputText(expect);

        assertEquals("View.inputText() 设置的内容应该传递给引用的Presenter", expect, mock.words);
    }

    @Test
    public void testChangeSwitch () throws Exception {
        MockPresenter mock = new MockPresenter();
        mTarget.presenter(mock);

        mTarget.onChangeSwitch(false);
        assertEquals(false, mock.mCountingWay);

        mTarget.onChangeSwitch(true);
        assertEquals(true, mock.mCountingWay);
    }

    @Test
    public void testPressButton() throws Exception {
        MockPresenter mock = new MockPresenter();
        mTarget.presenter(mock);


        int expect = 0;
        assertEquals(expect, mock.counter);
        mock.changeCountingWay(true);
        mTarget.onPressButton();
        assertEquals(expect + 1, mock.counter);

        mock.changeCountingWay(false);
        mTarget.onPressButton();
        assertEquals(expect, mock.counter);

    }

    @Test
    public void testBindAndDisplayWords() throws Exception {
        MockActivity activity = Robolectric.setupActivity(MockActivity.class);

        // setup activity
        mTarget.bind(activity.findViewById(android.R.id.content));

        TextView tv = activity.findViewById(R.id.tvWords);

        assertEquals("", tv.getText().toString());
        String expect = "blah. blah. blah";
        mTarget.displayWords(expect);
        assertEquals(expect, tv.getText().toString());
    }

    @Test
    public void testBindAndDisplayCounter() throws Exception {
        MockActivity activity = Robolectric.setupActivity(MockActivity.class);

        // setup activity
        mTarget.bind(activity.findViewById(android.R.id.content));

        TextView tv = activity.findViewById(R.id.tvCounter);

        assertEquals("Counting: 0", tv.getText().toString());
        int expect = 1;
        mTarget.displayCounter(expect);
        assertEquals("Counting: " + String.valueOf(expect), tv.getText().toString());
    }


    static class MockActivity extends AppCompatActivity {
        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
        }
    }


    static class MockPresenter implements Contract.Presenter {

        public Contract.View mView = null;
        public int counter = 0;
        public String words = null;
        public boolean mCountingWay = false;

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
            if (mCountingWay) {
                counter ++;
            } else {
                counter --;
            }
        }

        @Override
        public void changeCountingWay(boolean isIncreasing) {
            mCountingWay = isIncreasing;
        }

        @Override
        public void saySomething(String text) {
            words = text;
        }
    }
}
