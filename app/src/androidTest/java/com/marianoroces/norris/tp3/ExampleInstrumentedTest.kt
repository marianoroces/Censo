package com.marianoroces.norris.tp3

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.marianoroces.norris.tp3.view.ReportActivity

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    @get:Rule
    var activityRule: ActivityTestRule<ReportActivity> = ActivityTestRule(ReportActivity::class.java)

    @Test
    fun testReportActivity(){

        Espresso.onView(ViewMatchers.withId(R.id.r_dni_selector)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.r_dni_selector)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.r_dni)).perform(ViewActions.typeText("33258741"), ViewActions.closeSoftKeyboard())
        Espresso.onView(ViewMatchers.withId(R.id.r_first_name)).perform(ViewActions.typeText("Juan"), ViewActions.closeSoftKeyboard())
        Espresso.onView(ViewMatchers.withId(R.id.r_last_name)).perform(ViewActions.typeText("Perez"), ViewActions.closeSoftKeyboard())
        Espresso.onView(ViewMatchers.withId(R.id.r_sex_selector)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.r_sex_selector)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.r_address)).perform(ViewActions.typeText("Cordoba 2430"), ViewActions.closeSoftKeyboard())
        Espresso.onView(ViewMatchers.withId(R.id.r_phone)).perform(ViewActions.typeText("3425741852"), ViewActions.closeSoftKeyboard())
        Espresso.onView(ViewMatchers.withId(R.id.r_income)).perform(ViewActions.typeText("30000"), ViewActions.closeSoftKeyboard())
        Espresso.onView(ViewMatchers.withId(R.id.r_occupation_selector)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.r_occupation_selector)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.r_generate)).check(matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.marianoroces.norris.tp3", appContext.packageName)
    }
}