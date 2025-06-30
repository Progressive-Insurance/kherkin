package com.progressive.kherkin.compose.steps.testcore

data class ComposePathSegment(
    val start: ComposeNavigable,
    val end: ComposeNavigable,
    val step: () -> Unit,
    val preconditions: Map<String, Any> = mutableMapOf())