package com.chehejia.example.utexamples;

import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiSelector;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class MainActivityUIAutomatorTest {


    final String APP_NAME = "UTExamples";

    UiDevice device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());

    @Before
    public void setup() throws UiObjectNotFoundException {

        device.pressHome();
        UiObject ui = device.findObject(new UiSelector()
                .resourceId("com.android.launcher:id/hotseat")
                .childSelector(new UiSelector()
                        .className("android.widget.TextView")
                        .description("Apps")));

        assertTrue(ui.exists());
        ui.click();



        int times = 0;
        int MAX_TIMES = 3;
        boolean notFound = true;
        while (notFound) {

            ui = device.findObject(new UiSelector().text(APP_NAME));
            if (!ui.exists()) {
                if (times < MAX_TIMES) {
                    ui = device.findObject(new UiSelector().scrollable(true));
                    ui.swipeLeft(50);
                    times ++;
                } else {
                    throw new UiObjectNotFoundException("APP[" +APP_NAME + "] NOT FOUND.");
                }

            } else {
                notFound = false;
                ui.clickAndWaitForNewWindow();
            }
        }



        ui.swipeLeft(1);
    }

    @After
    public void teardown() {
        device.pressBack();
    }

    @Test
    public void testCountingNumbers() throws UiObjectNotFoundException {

        // assert View state: completely displayed.
        Espresso.onView(ViewMatchers.withId(R.id.btnDoSomething))
                .check(ViewAssertions.matches(ViewMatchers.isCompletelyDisplayed()))
                .check(ViewAssertions.matches(ViewMatchers.withText("DO SOMETHING")));
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
