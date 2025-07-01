package com.progressive.sampleapp.activities.xml

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.Insets
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.progressive.kherkin.sampleapp.R
import com.progressive.kherkin.sampleapp.databinding.ActivityTextFieldBinding
import com.progressive.sampleapp.activities.compose.SecondComposeActivity

class TextFieldActivity : AppCompatActivity(R.layout.activity_text_field) {

    private lateinit var binding: ActivityTextFieldBinding

    @Suppress("UNUSED_PARAMETER")
    fun navigateToSecondComposeFromTextField(v: View) {
        intent = Intent(this, SecondComposeActivity::class.java)
        startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = getString(R.string.text_field_activity_title)

        binding = ActivityTextFieldBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setStatusBarMargin()

        binding.editTextEnabledLeft.addTextChangedListener(enabledTextWatcher)
        binding.editTextVisibleLeft.addTextChangedListener(visibilityTextWatcher)
        binding.editTextWithLabel.addTextChangedListener(numericTextWatcher)
    }

    private val enabledTextWatcher = object : TextWatcher {
        override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
        override fun afterTextChanged(s: Editable?) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            updateEnabledEditTextRight()
        }
    }

    private val visibilityTextWatcher = object : TextWatcher {
        override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
        override fun afterTextChanged(s: Editable?) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            updateVisibleEditTextRight()
        }
    }

    private val numericTextWatcher = object : TextWatcher {
        override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
        override fun afterTextChanged(s: Editable?) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            updateNumericText()
        }
    }

    private fun updateEnabledEditTextRight() {
        binding.editTextDisabledRight.isEnabled = binding.editTextEnabledLeft.text.isNotEmpty()
    }

    private fun updateVisibleEditTextRight() {
        if (binding.editTextVisibleLeft.text.isNotBlank()) {
            binding.editTextInvisibleRight.visibility = View.VISIBLE
        } else {
            binding.editTextInvisibleRight.visibility = View.INVISIBLE
        }
    }

    private fun updateNumericText() {
        val numbers = binding.editTextWithLabel.text
        binding.textViewNumberInput.text =
            if (numbers.isNotEmpty()) getString(R.string.masked_text, numbers) else getString(R.string.masked_text_empty)
        if (binding.editTextWithLabel.text.isNotEmpty()) {
            binding.editTextContainer2.visibility = View.INVISIBLE
            binding.editTextWithLabel2.visibility = View.INVISIBLE
        } else {
            binding.editTextContainer2.visibility = View.VISIBLE
            binding.editTextWithLabel2.visibility = View.VISIBLE
        }
    }

    private fun setStatusBarMargin() {
        val view = findViewById<View>(R.id.textConstraint)
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