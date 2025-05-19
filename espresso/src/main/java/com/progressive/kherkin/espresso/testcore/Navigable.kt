package com.progressive.kherkin.espresso.testcore

import com.progressive.kherkin.common.screen.Screen

/**
 * Interface that must be inherited for any [Screen] to be used in end-to-end tests.
 */
interface Navigable {
    fun pathsToScreen(): List<PathSegment>
}