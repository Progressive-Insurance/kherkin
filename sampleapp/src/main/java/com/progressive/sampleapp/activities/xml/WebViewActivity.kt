package com.progressive.sampleapp.activities.xml

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.Insets
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.progressive.kherkin.sampleapp.R
import com.progressive.kherkin.sampleapp.databinding.ActivityWebviewBinding

class WebViewActivity : AppCompatActivity(R.layout.activity_webview) {

    private lateinit var binding: ActivityWebviewBinding

    private lateinit var webView: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = getString(R.string.web_view_title)

        binding = ActivityWebviewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setStatusBarMargin()

        webView = binding.webView
        webView.loadUrl(getString(R.string.web_url))
        webView.webViewClient = WebViewClient()
    }

    private fun setStatusBarMargin() {
        val view = findViewById<View>(R.id.webViewConstraint)
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