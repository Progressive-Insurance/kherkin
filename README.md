# Kherkin

Kherkin ([Kotlin](https://kotlinlang.org/) + [Gherkin](https://cucumber.io/docs/gherkin/)) is an instrumentation testing framework that simplifies writing UI tests for Android. The Gherkin-like syntax makes it easier to build the test scenarios as a collaboration between developers and non-developers. Predefined step definitions allow for writing tests without having to fiddle with the [Espresso testing framework](https://developer.android.com/training/testing/espresso) or the [Jetpack Compose testing APIs](https://developer.android.com/develop/ui/compose/testing). Tests can start from any activity without navigating the entire application.

Kherkin is written for both XML layouts and Jetpack Compose activities. It offers a dedicated library for each approach, allowing you the flexibility to include either one or both libraries in your project, based on your specific needs.

(Visit the sister project [Swerkin](https://github.com/Progressive-Insurance/Swerkin) for Swift/iOS!)

## Getting Started

(For a working example, check out the [sample app](sampleapp/src/main))

Add the Kherkin library or libraries to your project.

**Gradle:**
```gradle
androidTestImplementation "com.progressive.kherkin:kherkin-espresso:1.0.0"
androidTestImplementation "com.progressive.kherkin:kherkin-compose:1.0.0"
```

Extend the BaseIntegrationTestCase for setup and tear down. See [BaseIntegrationTestCase](#baseintegrationtestcase) below for details.

```kotlin
open class SampleBaseIntegrationTestCase : BaseIntegrationTestCase() {
    
    @get:Rule
    val composeTestRule = createEmptyComposeRule()
    
    @Before
    override fun setUp() {
        super.setUp()
        BaseIntegrationComponentHolder.component = BaseIntegrationComponent()
    }

    @After
    override fun tearDown() {
        val appContext: Context = ApplicationProvider.getApplicationContext()
        if (appContext is IntegrationTestCleanup) {
            appContext.cleanup()
        }
        super.tearDown()
    }
}
```

Extend the IntegrationSetupInterface to get the current screen.
```kotlin
class IntegrationSetup @Inject constructor() : IntegrationSetupInterface {
    override fun startActivity() {
        Screen.myCurrentScreen.startMyActivity()
    }

    override fun getStartScreen(): Screen {
        return Screen.myCurrentScreen
    }
}

class BaseIntegrationComponent : BaseIntegrationFrameworkComponent {
    override fun integrationSetup(): IntegrationSetupInterface {
        return IntegrationSetup()
    }
}

```

Create a screen object for your activity. See [Screen Objects](#screen-objects) below for details.
```kotlin
class MainScreen : Screen(), Navigable {

    override lateinit var activityScenario: ActivityScenario<MainActivity>
    override val trait: Trait = Trait("Main Activity")
    override fun screenActivityClass(): Class<out Activity> = MainActivity::class.java

    override fun startMyActivity() {
        activityScenario = ActivityScenario.launch(MainActivity::class.java)
    }

    override fun pathsToScreen(): List<PathSegment> = emptyList()
}
```

Write a test!

XML based activity:
```kotlin
class TestMain : SampleBaseIntegrationTestCase() {
    @Test
    fun testMainScreenDisplays() {
        Given.IRenderScreen(MainScreen())
        When.IWaitToSeeScreen(MainScreen())
        Then.IShouldSeeText("Hello world")
    }
}
```

Jetpack Compose activity:
```kotlin
class TestBasicComposeActivity : SampleBaseIntegrationTestCase() {
    @Test
    fun testComposeScreenDisplays() {
        Given.IRenderScreen(ComposeScreen(), composeTestRule)
        When.IWaitToSeeScreen(ComposeScreen(), composeTestRule)
        Then.IShouldSeeText("Hello user!", composeTestRule)
    }
}
```

Hybrid screens work too. Just use the espresso steps to assert or perform an action on XML views, and compose steps for compose nodes.

# Kherkin Functionality

There are 3 major components of the Kherkin framework: Base Test Case, Step Definitions, and Screen Objects.

## BaseIntegrationTestCase

The BaseIntegrationTestCase is the foundation of every test class created from this framework. It provides several features that can be utilized by the tests within each test class.

1. Timeout: Time before a test fails (default 60)
1. TestWatcher: Logs success or failure, records screenshot for failures
1. Setup: Required setup before each test, can be overridden
1. TearDown: Required tear down after each test, resets preconditions, can be overridden
1. Permissions: Not included in the base, but this is where permissions need to be granted before tests can run based on your app's permission requirements.

## Step Definitions

Kherkin step definitions are divided into three types:

1. Setup
1. Action
1. Assertion

**Note**: Espresso, the library for testing XML based layouts, is not compatible with screens made with Jetpack Compose. Some steps are duplicated, existing in both with the same wording but taking different parameters. Resource identifiers are unique to XML layouts, while test tags are a string unique to Compose code. Compose steps also must pass in the ComposeRule every single time. Espresso examples in code blocks will come first, followed by the Compose equivalent step which has the ComposeRule variable.

### Setup

Setup step definitions are used for setting up and rendering the screens being tested.

```kotlin
// Launch activity
Given.IRenderScreen(MainScreen())
Given.IRenderScreen(ComposeScreen(), composeTestRule)

// Navigate from starting activity to another via a set of step definitions
Given.INavigateToScreen(FinalScreen())
Given.INavigateToScreen(SecondComposeScreen(), composeTestRule)
```

### Action

Action step definitions are used to interact with views using their text content, resource IDs, tags, and other attributes.

```kotlin
// Touch button with matching resource ID and text
When.ITouchButtonWithText(R.id.button, "Button text")
// Touch node with click action and matching text
When.ITouchText("Click Me", composeTestRule)

// Touch view with matching tag
When.ITouchViewWithTag("tag")
When.ITouchNode("test tag", composeTestRule)

// Type a string into a text field with matching resource ID
When.IEnterTextIntoField(R.id.textfield, "text to enter")
// Type a string into a node with a set text action and a matching tag
When.IEnterTextIntoField("test tag", "text to enter", composeTestRule)
```

### Assertion

Assertion step definitions are used to verify view attributes by their content (text, resource IDs), status (enabled, visible), or type (EditText, Button).

```kotlin
// Assert view of type EditText has matching resource ID and hint
Then.IShouldSeeTextFieldWithHint(R.id.textfield, "Hint text")
// Assert node with click action and matching tag is displayed
Then.IShouldSeeNodeIsClickable("Compose Button", composeTestRule)

// Assert view of type RadioButton with matching text is disabled
Then.IShouldSeeRadioButtonWithTextIsDisabled("Disabled button")

// Assert view with matching resource ID is not displayed on the screen
Then.IShouldNotSeeView(R.id.view)
// Assert node with matching tag is not displayed on the screen
Then.IShouldNotSeeNode("Hidden Button", composeTestRule)
```

## Screen Objects

Each activity you want to test needs a corresponding class that inherits from Screen(). Optionally, if that screen will be part of an end to end test that utilizes the INavigateToScreen function, it must also inherit from Navigable.

Each screen file must have:

1. A way to start and drive your activity's lifecycle
1. A unique trait that is used to identify your activity
1. The name of your activity
1. A function to render the activity
1. A list of entry points to aid in navigation to the activity during flow testing

# Contributing

Please see the [contributing file](CONTRIBUTING.md) on how to contribute to this framework.

## Code of Conduct

Help us make this project open and inclusive. Please follow our [Code of Conduct](CODE_OF_CONDUCT.md).