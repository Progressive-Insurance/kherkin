package com.progressive.kherkin.common.screen

import androidx.annotation.IdRes

/**
 * A trait is a string or view ID on the screen. Compose screens can only look for a string. Traits
 * must be set on each [Screen].
 * Each trait should be unique to the screen it is defined on. If not, the verification of the trait
 * cannot be guaranteed to identify that specific screen.
 */
class Trait {
    @IdRes
    val viewId: Int
    val text: String?

    constructor(@IdRes viewId: Int) {
        this.viewId = viewId
        text = null
    }

    constructor(text: String) {
        viewId = -1
        this.text = text
    }

    constructor(@IdRes viewId: Int, text: String) {
        this.viewId = viewId
        this.text = text
    }

    companion object {
        @JvmStatic
        fun toolbarTitle(title: String): Trait {
            return Trait(title)
        }
    }
}