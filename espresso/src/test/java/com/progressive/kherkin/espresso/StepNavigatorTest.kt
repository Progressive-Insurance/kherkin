package com.progressive.kherkin.espresso

import com.progressive.kherkin.espresso.testcore.StepNavigator
import com.progressive.kherkin.espresso.testcore.Navigable
import com.progressive.kherkin.espresso.testcore.PathSegment
import com.progressive.kherkin.common.testcore.PreconditionsData
import com.progressive.kherkin.common.testcore.TestLogger
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class StepNavigatorTest {

    private lateinit var stepNavigator: StepNavigator

    @Before
    fun setup() {
        stepNavigator = StepNavigator(TestLogger)
    }

    @After
    fun teardown() {
        PreconditionsData.reset()
    }

    @Test
    fun testSimplePath() {
        val fromScreen = Login
        val toScreen = Welcome
        val path = stepNavigator.findPathToScreen(fromScreen, toScreen)

        assert(path.size == 1)

        val segment = path.first()
        assert(segment.start == fromScreen)
        assert(segment.end == toScreen)
    }

    @Test
    fun testTwoStepPath() {
        val fromScreen = Login
        val toScreen = Hub
        val path = stepNavigator.findPathToScreen(fromScreen, toScreen)

        val segment = path.first()
        assertEquals(Login, segment.start)
        assertEquals(Welcome, segment.end)

        val secondSegment = path[1]
        assertEquals(Welcome, secondSegment.start)
        assertEquals(Hub, secondSegment.end)
    }

    @Test
    fun testPathTakesShortcutWhenPreconditionsMatch() {
        val fromScreen = Login
        val toScreen = Hub
        PreconditionsData.addCondition("loggedIn", false)

        val path = stepNavigator.findPathToScreen(fromScreen, toScreen)

        val segment = path.first()
        assertTrue(path.size == 1)
        assertEquals(segment.start, Login)
        assertEquals(segment.end, Hub)
    }

    @Test(expected = IllegalStateException::class)
    fun testImpossiblePathThrowsException() {
        stepNavigator.findPathToScreen(Login, DeadEnd)
    }

    @Test
    fun testPathWith3Steps() {
        val path = stepNavigator.findPathToScreen(Login, Summary)

        assertEquals(Login, path.first().start)
        assertEquals(Welcome, path[1].start)
        assertEquals(Hub, path[2].start)
        assertEquals(Summary, path[2].end)
        assertEquals(3, path.size)
    }

    @Test
    fun testChoosesPathWithBestPreconditionsMatchPreferringLongerPathsIfTheSameNumberOfPreconditionsMatch() {
        PreconditionsData.addCondition("loggedIn", false)
        val path = stepNavigator.findPathToScreen(Login, FeedScreen)

        assertEquals(2, path.size)
        val first = path.first()
        assertEquals(first.start, Login)
        assertEquals(first.end, Hub)

        val second = path[1]
        assertEquals(second.start, Hub)
        assertEquals(second.end, FeedScreen)
    }

    private object Login : Navigable {
        override fun pathsToScreen(): List<PathSegment> = emptyList()

        override fun toString(): String {
            return javaClass.simpleName
        }
    }

    private object Welcome : Navigable {
        override fun pathsToScreen(): List<PathSegment> {
            return listOf(PathSegment(
                start = Login,
                end = this,
                step = {}
            ))
        }

        override fun toString(): String {
            return javaClass.simpleName
        }
    }

    private object Hub : Navigable {
        override fun pathsToScreen(): List<PathSegment> {
            return listOf(
                PathSegment(
                    start = Welcome,
                    end = this,
                    step = {}
                ),
                PathSegment(
                    start = Login,
                    end = this,
                    step = {},
                    preconditions = mapOf("loggedIn" to false)
                )
            )
        }

        override fun toString(): String {
            return javaClass.simpleName
        }
    }

    private object Summary : Navigable {
        override fun pathsToScreen(): List<PathSegment> {
            return listOf(
                PathSegment(
                    start = Hub,
                    end = this,
                    step = {}
                )
            )
        }

        override fun toString(): String {
            return javaClass.simpleName
        }
    }

    private object FeedScreen : Navigable {
        override fun pathsToScreen(): List<PathSegment> {
            return listOf(
                PathSegment(
                    start = Hub,
                    end = this,
                    step = {}
                ),
                PathSegment(
                    start = Login,
                    end = this,
                    step = {},
                    preconditions = mapOf("loggedIn" to false)
                )
            )
        }

        override fun toString(): String {
            return javaClass.simpleName
        }

    }

    private object DeadEnd : Navigable {
        override fun pathsToScreen(): List<PathSegment> {
            return listOf(
                PathSegment(
                    start = Hub,
                    end = Login,
                    step = {})
            )
        }

        override fun toString(): String {
            return javaClass.simpleName
        }
    }
}