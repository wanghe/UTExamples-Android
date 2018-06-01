package com.chehejia.example.utexamples;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> rule = new ActivityTestRule<>(MainActivity.class, true, true);

    @Before
    public void setup() {}

    @After
    public void teardown() {}

    @Test
    public void testCountingNumbers() throws Exception {

        // assert View state: completely displayed.
        Espresso.onView(ViewMatchers.withId(R.id.btnDoSomething))
                .check(ViewAssertions.matches(ViewMatchers.isCompletelyDisplayed()));
        Espresso.onView(ViewMatchers.withId(R.id.tvCounter))
                .check(ViewAssertions.matches(ViewMatchers.isCompletelyDisplayed()));
        Espresso.onView(ViewMatchers.withId(R.id.swSwitch1))
                .check(ViewAssertions.matches(ViewMatchers.isCompletelyDisplayed()));

        // assert TextView state: Default
        Espresso.onView(ViewMatchers.withId(R.id.tvCounter))
                .check(ViewAssertions.matches(ViewMatchers.withText("Counting: 0")));

        // assert Switch state: Default
        Espresso.onView(ViewMatchers.withId(R.id.swSwitch1))
                .check(ViewAssertions.matches(ViewMatchers.isNotChecked()));

        // press button
        Espresso.onView(ViewMatchers.withId(R.id.btnDoSomething))
                .perform(ViewActions.click());

        // assert text results.
        Espresso.onView(ViewMatchers.withId(R.id.tvCounter))
                .check(ViewAssertions.matches(ViewMatchers.withText("Counting: -1")));

        // change switch
        Espresso.onView(ViewMatchers.withId(R.id.swSwitch1))
                .perform(ViewActions.click());

        // assert Switch state: Checked
        Espresso.onView(ViewMatchers.withId(R.id.swSwitch1))
                .check(ViewAssertions.matches(ViewMatchers.isChecked()));

        // press button
        Espresso.onView(ViewMatchers.withId(R.id.btnDoSomething))
                .perform(ViewActions.click());

        // assert text result
        Espresso.onView(ViewMatchers.withId(R.id.tvCounter))
                .check(ViewAssertions.matches(ViewMatchers.withText("Counting: 0")));

        // press button
        Espresso.onView(ViewMatchers.withId(R.id.btnDoSomething))
                .perform(ViewActions.click());

        // assert text result
        Espresso.onView(ViewMatchers.withId(R.id.tvCounter))
                .check(ViewAssertions.matches(ViewMatchers.withText("Counting: 1")));
    }

    @Test
    public void testSaySomething() throws Exception {

        // assert View state: completely displayed.
        Espresso.onView(ViewMatchers.withId(R.id.etInput))
                .check(ViewAssertions.matches(ViewMatchers.isCompletelyDisplayed()))
                .check(ViewAssertions.matches(ViewMatchers.withHint(R.string.etInput_hint)))
                .check(ViewAssertions.matches(ViewMatchers.withText("")));

        Espresso.onView(ViewMatchers.withId(R.id.tvWords))
                .check(ViewAssertions.matches(ViewMatchers.isCompletelyDisplayed()))
                .check(ViewAssertions.matches(ViewMatchers.withText("")));


        String expect = "blah blah blah.";

        // input something.
        Espresso.onView(ViewMatchers.withId(R.id.etInput))
                .perform(ViewActions.typeText(expect), ViewActions.closeSoftKeyboard());

        // display it.
        Espresso.onView(ViewMatchers.withId(R.id.tvWords))
                .check(ViewAssertions.matches(ViewMatchers.withText(expect)));

    }
}
