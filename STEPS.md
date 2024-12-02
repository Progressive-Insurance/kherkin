# Step Definitions

A Gherkin step is one that starts with Given, When, Then, or And, and reads like a sentence. In Kherkin, a step definition is a Kotlin function stylized as a Gherkin step, hiding the technical Espresso testing framework or the Jetpack Compose testing APIs code behind it. Proper use of the Kherkin library should use step definitions for every step in the test function, making the entire test (mostly) natural human-language readable. For example:

```kotlin
@Test
fun testWaitForAlertWithText() {
    Given.IRenderScreen(MainScreen())
    When.ITouchButton(R.id.buttonAlert)
    Then.IWaitToSeeTextInAlert("Test message")
}
```
This could be read as, "Given I render the \<Main Screen\>, When I touch the button with id \<buttonAlert\>, Then I wait to see text in alert \<Test message\>". Most of these functions are entirely Espresso/Compose UI testing code with no other logic. For example, the Espresso step definition ITouchButton:

```kotlin
fun Gherkin.ITouchButton(@IdRes viewId: Int) {
    onView(allOf(isAssignableFrom(Button::class.java), withId(viewId), isEnabled()))
            .perform(safeScrollTo(), click())
}
```

The "Gherkin." prefix allows (and requires) any of the four: Given, When, Then, And. The name of the function should be descriptive of the Espresso/Compose code it includes. The most common parameters are for matching a specific view by text content or resource id (R.id) for Espresso/test tag for Compose, or both.

In this case, the step will only work on a view that:
- inherits from or is of type "Button"
- matches the R.id passed as a parameter (in XML, that is this thing: android:id="@+id/buttonAlert")
- is enabled, because we do not want to try clicking on a disabled button

Once the view is found, the step scrolls to it if necessary, and performs a click action on it.

Here is the Compose equivalent:

```kotlin
fun Gherkin.ITouchNode(tag: String, composeTestRule: ComposeTestRule) {
    composeTestRule.onNodeWithTag(tag).assertHasClickAction().performClick()
}
```

The ITouchNode function is the Jetpack Compose equivalent. Because resource IDs do not exist for nodes in Compose as they do for views in XML layouts, the test tag is used instead. It looks for a single node with the test tag matching the string passed in as a parameter, asserts it has a click action (the closest Compose gets to finding a button, but any view can have a click action), and then performs a click on it.

There are three kinds of step definitions:

- Action steps
- Assertions steps
- Setup steps

These can be found at:

- /espresso/src/main/java/com/progressive/kherkin/espresso/steps
- /compose/src/main/java/com/progressive/kherkin/compose/steps
- /common/src/main/java/com/progressive/kherkin/common/steps

## Action Steps

Any step that interacts with the UI is an action step. These steps simulate the actions a user may take when using an app. Most commonly this includes touching buttons or entering text. Also included are complex steps to interact with date or time pickers, recycler views, scroll views, generic spinners, swiping, and simply waiting.

Action steps usually contain .perform() in their Espresso code and .performClick() in their Compose code, as in the ITouchButton() and ITouchNode() examples above.

## Assertions Steps

Steps that need to confirm a view/node is in the desired state without taking an action are assertion steps. Most commonly this is for checking a view/node is visible on the screen, and often with the right text. Another common use is to confirm the view/node is intentionally not visible.

Assertion steps usually contain .check() in their Espresso code, such as .check(matches(isDisplayed())) or .assertIsDisplayed() for Compose.

## Setup Steps

As the name implies, these steps are used for setting up the test. The Kherkin library only has a few of these for rendering the starting screen or navigating to a different one, and you likely need to create your own.

# Writing New Steps

There are many combinations of asserts or actions not covered by Kherkin. If you identify a gap in the library worth writing a new step for and wish to contribute, follow the guidelines below.

## General Guidelines

Each new step definition must be written as a function with the Gherkin prefix. The function name should be a sentence written with PascalCase where each word, including the first, starts with a capital letter. The sentence should start with "I" and describe the assertion or action. Every step must have a comment above describing what it does.

```kotlin
/** Swipes from right to left on the screen. */
fun Gherkin.ISwipeLeft()
```

Assertion steps must start with IShouldSee or IShouldNotSee. IShouldSee steps often must include a visibility check. IShouldNotSee steps require two parts: `isDisplayed()` in the matcher, and `doesNotExist()` in the check. Action steps should usually include `safeScrollTo()` before the actual action. Do your best to match the existing steps.

## Automated Tests for Steps

Each step must be included in an automated UI test in the SampleApp to demonstrate that it works as intended. If none of the sample screens have a view appropriate for the new step to test, they can be modified so the test can assert or perform an action on it.

## Kotlin

All code must be written in kotlin, not Java. Code should follow [kotlin coding conventions](https://kotlinlang.org/docs/coding-conventions.html). See also the [Android kotlin style guide](https://developer.android.com/kotlin/style-guide).