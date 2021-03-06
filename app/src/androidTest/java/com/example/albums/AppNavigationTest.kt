package com.example.albums

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.example.albums.utils.DataBindingIdlingResource
import com.example.albums.utils.EspressoIdlingResource
import com.example.albums.utils.getToolbarNavigationContentDescription
import com.example.albums.utils.monitorActivity
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
@LargeTest
class AppNavigationTest {
    private val dataBindingIdlingResource = DataBindingIdlingResource()

    @Before
    fun registerIdlingResources() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.countingIdlingResource)
        IdlingRegistry.getInstance().register(dataBindingIdlingResource)
    }

    @After
    fun unregisterIdlingResources() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.countingIdlingResource)
        IdlingRegistry.getInstance().unregister(dataBindingIdlingResource)
    }

    @Test
    fun clickAlbum_navigateToDetail_andBack() {
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)
        dataBindingIdlingResource.monitorActivity(activityScenario)

        // Navigate to AlbumDetail
        onView(withId(R.id.album_list_list)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click())
        )
        onView(withId(R.id.album_detail_title)).check(matches(isDisplayed()))

        // Navigate to PhotoDetail
        onView(withId(R.id.album_detail_photo_list)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click())
        )
        onView(withId(R.id.photo_detail_title)).check(matches(isDisplayed()))

        // Go back to AlbumDetail
        onView(withContentDescription(activityScenario.getToolbarNavigationContentDescription())).perform(
            click()
        )
        onView(withId(R.id.album_detail_title)).check(matches(isDisplayed()))

        // Go back to AlbumList
        onView(withContentDescription(activityScenario.getToolbarNavigationContentDescription())).perform(
            click()
        )
        onView(withId(R.id.album_list_title)).check(matches(isDisplayed()))

        activityScenario.close()
    }
}
