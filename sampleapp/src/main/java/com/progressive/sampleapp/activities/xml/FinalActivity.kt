package com.progressive.sampleapp.activities.xml

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.progressive.kherkin.sampleapp.R
import com.progressive.kherkin.sampleapp.databinding.ActivityFinalBinding

class FinalActivity : AppCompatActivity(R.layout.activity_final) {

    private lateinit var binding: ActivityFinalBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()

        binding = ActivityFinalBinding.inflate(layoutInflater)
        setContentView(binding.root)

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
}