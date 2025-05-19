package com.progressive.kherkin.espresso.custom

import com.progressive.kherkin.common.screen.Screen

/**
 * Used by [Navigable] for end-to-end navigation.
 * [Start][start] is the previous [Screen].
 * [End][end] is usually "this".
 * [Step][step] should include the step definitions required to move from [start] to [end].
 * Example:
 *  step = {
 *      And.IWaitToSeeScreen(MainScreen())
 *      When.ITouchButton(R.id.buttonNav)
 *  },
 * [Preconditions][preconditions] are needed when navigation is not linear.
 */
data class PathSegment(val start: Navigable,
                       val end: Navigable,
                       val step: () -> Unit,
                       val preconditions: Map<String, Any> = mutableMapOf())