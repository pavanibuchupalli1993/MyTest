package com.example.mytest

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.isEnabled
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.hamcrest.Matchers.not
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class MainActivityTest {
    @get : Rule
    var mActivityRule = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun setUp() {
        //initial setup code
    }

    @Test
    fun clickForAddData() {
        Espresso.onView(ViewMatchers.withId(R.id.saveButton)).perform(click())
      //  Espresso.onView(ViewMatchers.withId(R.id.userNameInput)).equals("tets")
     //   Espresso.onView(ViewMatchers.withId(R.id.emailInput)).check(matches(ViewMatchers.withText("abc@gmail.com")))
    }

    @After
    fun tearDown() {
        //clean up code
    }
}