package com.progressive.sampleapp.activities.xml

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.graphics.Insets
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.progressive.kherkin.sampleapp.R
import com.progressive.sampleapp.RecycleViewAdapter

class ListActivity : AppCompatActivity(R.layout.activity_list) {

    @Suppress("UNUSED_PARAMETER")
    fun navigateToFinalFromList(v: View) {
        intent = Intent(this, FinalActivity::class.java)
        startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = getString(R.string.list_activity_title)
        setStatusBarMargin()
        initDataset()
    }

    private fun initDataset() {
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val data = ArrayList<String>()
        for (i in 1..100) {
            data.add(getString(R.string.list_item_text, i))
        }

        val adapter = RecycleViewAdapter(data)

        recyclerView.adapter = adapter

        val divider = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        divider.setDrawable(ContextCompat.getDrawable(this, R.drawable.divider)!!)
        recyclerView.addItemDecoration(divider)
    }

    private fun setStatusBarMargin() {
        val view = findViewById<View>(R.id.listConstraint)
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