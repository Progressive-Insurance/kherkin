package com.progressive.kherkin.espresso.custom

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import androidx.core.content.res.ResourcesCompat
import org.hamcrest.Description
import org.hamcrest.TypeSafeMatcher

/**
 * A custom matcher that checks for an image on a view.
 * It checks the target view's src and background elements for a drawable. This should work for any
 * [ImageView] as well as any view with a drawable set as the background.
 */
class DrawableMatcher internal constructor(private val expectedId: Int) : TypeSafeMatcher<View?>(View::class.java) {
    private var resourceName: String? = null
    override fun matchesSafely(target: View?): Boolean {
        if (target !is ImageView && target !is View) {
            return false
        }
        if (expectedId == EMPTY) {
            return if (target is ImageView) {
                target.drawable == null || target.background == null
            } else {
                target.background == null
            }
        }
        if (expectedId == ANY) {
            return if (target is ImageView) {
                target.drawable != null || target.background != null
            } else {
                target.background == null
            }
        }
        val resources = target.context.resources

        val expectedDrawable = ResourcesCompat.getDrawable(resources, expectedId, null)

        resourceName = resources.getResourceEntryName(expectedId)
        if (expectedDrawable == null) {
            return false
        }
        val otherBitmap = getBitmap(expectedDrawable)

        return if (target is ImageView && target.drawable != null) {
            getBitmap(target.drawable).sameAs(otherBitmap)
        } else if (target.background != null) {
            getBitmap(target.background).sameAs(otherBitmap)
        } else {
            false
        }
    }

    private fun getBitmap(drawable: Drawable): Bitmap {
        val bitmap = Bitmap.createBitmap(drawable.intrinsicWidth, drawable.intrinsicHeight, Bitmap.Config.ALPHA_8)
        val canvas = Canvas(bitmap)
        drawable.setBounds(0, 0, canvas.width, canvas.height)
        drawable.draw(canvas)
        return bitmap
    }

    override fun describeTo(description: Description) {
        description.appendText("with drawable from resource id: ")
        description.appendValue(expectedId)
        if (resourceName != null) {
            description.appendText("[")
            description.appendText(resourceName)
            description.appendText("]")
        }
    }

    companion object {
        const val EMPTY = -1
        const val ANY = -2
    }
}