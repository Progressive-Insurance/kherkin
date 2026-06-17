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
    val tag: String?

    constructor(@IdRes viewId: Int) {
        this.viewId = viewId
        text = null
        tag = null
    }

    constructor(text: String) {
        viewId = -1
        this.text = text
        tag = null
    }

    constructor(@IdRes viewId: Int, text: String) {
        this.viewId = viewId
        this.text = text
        tag = null
    }

    constructor(text: String, tag: String) {
        this.text = text
        this.tag = tag
        this.viewId = -1
    }

    companion object {
        @JvmStatic
        fun toolbarTitle(title: String): Trait {
            return Trait(title)
        }
    }
}