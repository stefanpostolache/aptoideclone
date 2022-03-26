package dev.stefan.postolache.apptoideclone;

import static androidx.test.espresso.Espresso.*;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.*;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.*;

import static org.hamcrest.Matchers.*;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityScenarioRule activityScenarioRule =
            new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void launchApp_success_mainActivityIsDisplayed() {

        onView(withId(R.id.editors_choice_textview)).check(matches(isDisplayed()));
    }

    @Test
    public void launchApp_success_editorsChoiceRecyclerViewIsDisplayed() throws InterruptedException {

        onView(withId(R.id.editors_choice_list)).check(matches(isDisplayed()));

    }

    @Test
    public void fetchData_success_localTopAppsRecyclerViewIsDisplayed() throws InterruptedException {

        onView(withId(R.id.local_top_apps_list)).check(matches(isDisplayed()));

    }

    @Test
    public void fetchData_success_editorsChoiceRecyclerViewIsPopulated() throws InterruptedException {

        Thread.sleep(1000);

        onView(withId(R.id.editors_choice_list)).check(matches(hasMinimumChildCount(2)));

    }

    @Test
    public void fetchData_success_localTopAppsRecyclerViewIsPopulated() throws InterruptedException {

        Thread.sleep(1000);

        onView(withId(R.id.local_top_apps_list)).check(matches(hasMinimumChildCount(3)));

    }

    @Test
    public void tapEditorsChoiceItem_showAppDetailsFragment() throws InterruptedException {

        Thread.sleep(1000);

        ViewInteraction recyclerView = onView(
                allOf(withId(R.id.editors_choice_list),
                        childAtPosition(
                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                1)));
        recyclerView.perform(actionOnItemAtPosition(0, click()));

        Thread.sleep(1000);

        onView(withId(R.id.appNameTxt)).check(matches(isDisplayed()));

    }

    @Test
    public void tapEditorsChoiceItem_displayNavigateUpOnToolbar() throws InterruptedException {

        Thread.sleep(1000);

        ViewInteraction recyclerView = onView(
                allOf(withId(R.id.editors_choice_list),
                        childAtPosition(
                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                1)));
        recyclerView.perform(actionOnItemAtPosition(0, click()));

        Thread.sleep(1000);

        ViewInteraction appCompatImageButton = onView(
                allOf(withContentDescription("Navigate up"),
                        childAtPosition(
                                allOf(withId(R.id.custom_toolbar),
                                        childAtPosition(
                                                withClassName(is("com.google.android.material.appbar.AppBarLayout")),
                                                0)),
                                2),
                        isDisplayed()));

    }

    @Test
    public void tapEditorsChoiceItem_tapUpButton_showHomeScreen() throws InterruptedException {

        Thread.sleep(1000);

        ViewInteraction recyclerView = onView(
                allOf(withId(R.id.editors_choice_list),
                        childAtPosition(
                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                1)));
        recyclerView.perform(actionOnItemAtPosition(0, click()));

        Thread.sleep(1000);

        ViewInteraction appCompatImageButton = onView(
                allOf(withContentDescription("Navigate up"),
                        childAtPosition(
                                allOf(withId(R.id.custom_toolbar),
                                        childAtPosition(
                                                withClassName(is("com.google.android.material.appbar.AppBarLayout")),
                                                0)),
                                2),
                        isDisplayed()));
        appCompatImageButton.perform(click());

        Thread.sleep(500);

        onView(withId(R.id.editors_choice_textview)).check(matches(isDisplayed()));
    }

    @Test
    public void tapEditorsChoiceItem_tapUpButton_hideUpButton() throws InterruptedException {

        Thread.sleep(1000);

        ViewInteraction recyclerView = onView(
                allOf(withId(R.id.editors_choice_list),
                        childAtPosition(
                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                1)));
        recyclerView.perform(actionOnItemAtPosition(0, click()));

        Thread.sleep(1000);

        ViewInteraction appCompatImageButton = onView(
                allOf(withContentDescription("Navigate up"),
                        childAtPosition(
                                allOf(withId(R.id.custom_toolbar),
                                        childAtPosition(
                                                withClassName(is("com.google.android.material.appbar.AppBarLayout")),
                                                0)),
                                2),
                        isDisplayed()));
        appCompatImageButton.perform(click());

        Thread.sleep(500);

        appCompatImageButton.check(doesNotExist());

    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }

}