package com.progressive.sampleapp.activities.xml

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.Insets
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.commit
import com.progressive.kherkin.sampleapp.R
import com.progressive.sampleapp.fragments.ViewPagerFragment

class ViewPagerActivity : AppCompatActivity(R.layout.activity_view_pager) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = getString(R.string.view_pager_activity_title)

        setStatusBarMargin()

        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                setReorderingAllowed(true)

                val isVertical = intent.getBooleanExtra(ViewPagerFragment.IS_VERTICAL, false)

                val viewPagerFragment = ViewPagerFragment.newInstance(isVertical)
                supportFragmentManager.beginTransaction().add(R.id.fragmentContainerViewPager, viewPagerFragment).commit()
            }
        }
    }

    @Suppress("UNUSED_PARAMETER")
    fun navigateToFinalFromViewPager(v: View) {
        intent = Intent(this, FinalActivity::class.java)
        startActivity(intent)
    }

    private fun setStatusBarMargin() {
        val view = findViewById<View>(R.id.viewPagerConstraint)
        if (view != null) {
            val params = view.layoutParams
            if (params is ViewGroup.MarginLayoutParams) {
                ViewCompat.setOnApplyWindowInsetsListener(view) { _: View?, windowInsets: WindowInsetsCompat ->
                    val insets = windowInsets.getInsets(WindowInsetsCompat.Type.statusBars())
                    params.setMargins(0, insets.top, 0, 0)
                    view.requestLayout()
                    windowInsets
                }
            }
        }
    }
}