package com.progressive.kherkin.compose.steps.testcore

import androidx.compose.ui.test.junit4.ComposeTestRule
import com.progressive.kherkin.common.testcore.AndroidLogger
import com.progressive.kherkin.common.testcore.Logger
import com.progressive.kherkin.common.testcore.PreconditionsData
import javax.inject.Inject

class ComposeStepNavigator
@Inject
constructor(private val logger: Logger = AndroidLogger()) {

    fun findPathToScreen(startScreen: ComposeNavigable, endScreen: ComposeNavigable, composeTestRule: ComposeTestRule): List<ComposePathSegment> {
        val pathOptions = ArrayList<List<ComposePathSegment>>()
        endScreen.pathsToScreen(composeTestRule).forEach { segment ->
            val potentialPaths = findPath(endScreen, segment, startScreen, mutableListOf(segment), composeTestRule)

            pathOptions.addAll(potentialPaths.map { path -> path.reversed() })
        }

        if (pathOptions.isEmpty()) {
            throw IllegalStateException("No path from $startScreen -> $endScreen")
        }

        val optionsByWeight = pathOptions.map { option ->
            val weight = option.sumOf { PreconditionsData.conditionsMatch(it.preconditions) }
            Pair(weight, option)
        }.sortedByDescending { it.first }

        logPaths(optionsByWeight)

        return optionsByWeight.first().second
    }

    /**
     * This method recursively traverses backwards, from a destination until it exhausts all paths,
     * or finds the starting screen. It uses a Depth First Search that checks the first [ComposePathSegment]
     * that a destination has, and then continues searching backwards until it reaches a dead end,
     * or finds the start.
     */
    private fun findPath(
        start: ComposeNavigable,
        nextSegment: ComposePathSegment,
        destination: ComposeNavigable,
        path: List<ComposePathSegment>,
        composeTestRule: ComposeTestRule
    ): List<List<ComposePathSegment>> {
        val possiblePaths = ArrayList<List<ComposePathSegment>>()
        if (nextSegment.start == destination && PreconditionsData.conditionsMatch(nextSegment.preconditions) > 0) {
            possiblePaths.add(path)
        }

        for (segment in nextSegment.start.pathsToScreen(composeTestRule)) {
            if (PreconditionsData.conditionsMatch(segment.preconditions) == 0) {
                logger.debug(TAG, "Skipping path $segment. As the preconditions do not match.")
                continue
            }

            val pathCopy = ArrayList(path)
            pathCopy.add(segment)

            val potentialPaths = findPath(start, segment, destination, pathCopy, composeTestRule)
            potentialPaths.forEach { potentialPath ->
                if (potentialPath.isNotEmpty() && potentialPath.first().end == start && potentialPath.last().start == destination) {
                    possiblePaths.add(potentialPath)
                } else {
                    logger.debug(TAG, "Bad path: ${potentialPath.reversed().map { it.start }}")
                }
            }
        }

        return possiblePaths
    }

    private fun logPaths(paths: List<Pair<Int, List<ComposePathSegment>>>) {
        paths.forEachIndexed { index, path ->
            val pathBuilder = buildPathString(path.second)
            if (index == 0) {
                logger.info(TAG, "Taking path with weight (${path.first}): $pathBuilder")
            } else {
                logger.debug(
                    TAG,
                    "Skipping less ideal path with weight (${path.first}): $pathBuilder"
                )
            }
        }
    }

    private fun buildPathString(path: List<ComposePathSegment>): StringBuilder {
        val pathBuilder = StringBuilder()
        path.forEachIndexed { index, segment ->
            when {
                index < path.size - 1 -> {
                    pathBuilder.append(segment.start).append(" -> ")
                }
                index == path.size - 1 -> {
                    pathBuilder.append(segment.start).append(" -> ").append(segment.end)
                }
            }
        }
        return pathBuilder
    }

    companion object {
        val TAG = ComposeStepNavigator::class.simpleName!!
    }
}