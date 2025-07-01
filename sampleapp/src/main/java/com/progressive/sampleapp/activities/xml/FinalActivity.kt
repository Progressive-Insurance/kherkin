package com.progressive.sampleapp.activities.xml

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.Insets
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.progressive.kherkin.sampleapp.R
import com.progressive.kherkin.sampleapp.databinding.ActivityFinalBinding

class FinalActivity : AppCompatActivity(R.layout.activity_final) {

    private lateinit var binding: ActivityFinalBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()

        binding = ActivityFinalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setStatusBarMargin()

        setupClickListener()
    }

    @Suppress("UNUSED_PARAMETER")
    fun navigateToMain(v: View) {
        intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    private fun setupClickListener() {
        binding.buttonBottom.setOnClickListener {
            binding.buttonBottom.text = getString(R.string.button_clicked)
        }
    }

    private fun setStatusBarMargin() {
        val view = findViewById<View>(R.id.finalConstraint)
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