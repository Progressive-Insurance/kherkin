package com.progressive.kherkin.common.testcore

import com.progressive.kherkin.common.screen.Screen
import javax.inject.Inject

/**
 * Can be used to find the path to a destination [Screen], intended for use by end-to-end tests.
 */
class StepNavigator
@Inject
constructor(private val logger: Logger = AndroidLogger()) {

    fun findPathToScreen(startScreen: Navigable, endScreen: Navigable): List<PathSegment> {
        val pathOptions = ArrayList<List<PathSegment>>()
        endScreen.pathsToScreen().forEach { segment ->
            val potentialPaths = findPath(endScreen, segment, startScreen, mutableListOf(segment))

            pathOptions.addAll(potentialPaths.map { path -> path.reversed() })
        }

        if (pathOptions.isEmpty()) {
            throw IllegalStateException("No path from $startScreen -> $endScreen")
        }

        // We are calculating the 'weights' of each path and taking the highest weight, which means the most matching preconditions as well as preferring longer paths.
        val optionsByWeight = pathOptions.map { option ->
            val weight = option.sumOf { PreconditionsData.conditionsMatch(it.preconditions) }
            Pair(weight, option)
        }.sortedByDescending { it.first }

        logPaths(optionsByWeight)

        return optionsByWeight.first().second
    }

    /**
     * This method recursively traverses backwards, from a destination until it exhausts all paths,
     * or finds the starting screen. It uses a Depth First Search that checks the first PathSegment
     * that a destination has, and then continues searching backwards until it reaches a dead end,
     * or finds the start.
     */
    private fun findPath(
        start: Navigable,
        nextSegment: PathSegment,
        destination: Navigable,
        path: List<PathSegment>
    ): List<List<PathSegment>> {
        val possiblePaths = ArrayList<List<PathSegment>>()
        if (nextSegment.start == destination && PreconditionsData.conditionsMatch(nextSegment.preconditions) > 0) {
            possiblePaths.add(path)
        }

        for (segment in nextSegment.start.pathsToScreen()) {
            if (PreconditionsData.conditionsMatch(segment.preconditions) == 0) {
                logger.debug(TAG, "Skipping path $segment. As the preconditions do not match.")
                continue
            }

            val pathCopy = ArrayList(path)
            pathCopy.add(segment)

            val potentialPaths = findPath(start, segment, destination, pathCopy)
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

    private fun logPaths(paths: List<Pair<Int, List<PathSegment>>>) {
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

    private fun buildPathString(path: List<PathSegment>): StringBuilder {
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
        val TAG = StepNavigator::class.simpleName!!
    }
}