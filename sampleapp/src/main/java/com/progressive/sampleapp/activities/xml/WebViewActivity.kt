package com.progressive.sampleapp.activities.xml

import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
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

        webView = binding.webView
        webView.loadUrl(getString(R.string.web_url))
        webView.webViewClient = WebViewClient()
    }
}