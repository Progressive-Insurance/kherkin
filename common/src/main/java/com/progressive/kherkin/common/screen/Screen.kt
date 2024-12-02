package com.progressive.kherkin.common.screen

import java.util.Objects

/**
 * Each activity's Screen class must inherit this class to be used in UI testing. Each class
 * inheriting Screen will require implementations of [IScreen]'s variables and functions. This
 * allows each activity to be launched and identified.
 */
abstract class Screen : IScreen {

    override fun equals(other: Any?): Boolean {
        val otherScreen = (other as? Screen) ?: return false
        return this::class.simpleName == otherScreen::class.simpleName
    }

    override fun hashCode(): Int {
        return Objects.hash(this::class.simpleName)
    }

    override fun toString(): String {
        return this::class.simpleName ?: ""
    }

    companion object {
        lateinit var myCurrentScreen: Screen
    }
}
