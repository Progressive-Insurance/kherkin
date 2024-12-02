package com.progressive.sampleapp.activities.xml

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.SpannableString
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.progressive.kherkin.sampleapp.R
import com.progressive.kherkin.sampleapp.databinding.ActivityButtonBinding
import com.progressive.sampleapp.viewmodels.ButtonViewModel

class ButtonActivity : AppCompatActivity(R.layout.activity_button) {

    private lateinit var binding: ActivityButtonBinding

    private val viewModel: ButtonViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = getString(R.string.button_activity_title)

        binding = ActivityButtonBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupClickListeners()
        setupObservers()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main_view, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.toolbarItem1 -> {
                binding.radioButtonC.isEnabled = true
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setupClickListeners() {
        binding.buttonClickCounter.setOnClickListener {
            viewModel.setUpdatedClicks()
        }
        binding.buttonVisibilityLeft.setOnClickListener {
            binding.buttonVisibilityLeft.visibility = View.INVISIBLE
            binding.buttonVisibilityRight.visibility = View.VISIBLE
        }
        binding.buttonVisibilityRight.setOnClickListener {
            binding.buttonVisibilityRight.visibility = View.INVISIBLE
            binding.buttonVisibilityLeft.visibility = View.VISIBLE
        }
        binding.buttonEnabledLeft.setOnClickListener {
            binding.buttonEnabledLeft.isEnabled = false
            binding.buttonEnabledRight.isEnabled = true
        }
        binding.buttonEnabledRight.setOnClickListener {
            binding.buttonEnabledRight.isEnabled = false
            binding.buttonEnabledLeft.isEnabled = true
        }
        binding.checkboxEnabled.setOnClickListener {
            if (binding.checkboxEnabled.isChecked) {
                binding.checkboxInvisible.visibility = View.VISIBLE
            } else {
                binding.checkboxInvisible.visibility = View.INVISIBLE
            }
        }
        binding.checkboxLayout.setOnClickListener {
            binding.checkControl.isChecked = !binding.checkControl.isChecked
        }
        binding.checkControlText.setOnClickListener {
            binding.checkControl.isChecked = !binding.checkControl.isChecked
        }

        val span = SpannableString(binding.textViewWithLink.text)
        val clickableSpan: ClickableSpan = object : ClickableSpan() {
            override fun onClick(widget: View) {
                binding.radioButtonGroup.visibility = View.INVISIBLE
            }
        }
        span.setSpan(
            clickableSpan,
            0,
            binding.textViewWithLink.text.length,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        binding.textViewWithLink.setText(span, TextView.BufferType.SPANNABLE)
        binding.textViewWithLink.movementMethod = LinkMovementMethod.getInstance()

        val spanShort = SpannableString(binding.textViewWithClickableSpan.text)
        val clickableSpanShort: ClickableSpan = object : ClickableSpan() {
            override fun onClick(widget: View) {
                binding.radioButtonGroup.visibility = View.VISIBLE
            }
        }
        val start = spanShort.indexOf("this")
        spanShort.setSpan(
            clickableSpanShort,
            start,
            start + "this".length,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        binding.textViewWithClickableSpan.setText(spanShort, TextView.BufferType.SPANNABLE)
        binding.textViewWithClickableSpan.movementMethod = LinkMovementMethod.getInstance()

        binding.buttonWebView.setOnClickListener {
            intent = Intent(this, WebViewActivity::class.java)
            startActivity(intent)
        }

        binding.buttonVisibilityDelay.setOnClickListener {
            Handler(Looper.getMainLooper()).postDelayed({
                binding.textViewDelay.visibility = View.VISIBLE
            }, 100)
        }

        binding.buttonShare.setOnClickListener {
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, getString(R.string.share_message))
                type = "text/plain"
            }

            val shareIntent = Intent.createChooser(sendIntent, null)
            startActivity(shareIntent)
        }
    }

    private fun setupObservers() {
        viewModel.uiClicksLiveData.observe(this, Observer { clicks ->
            binding.buttonClickCounter.text = getString(R.string.button_click_counter_clicked, clicks + 1)
        })
    }
}