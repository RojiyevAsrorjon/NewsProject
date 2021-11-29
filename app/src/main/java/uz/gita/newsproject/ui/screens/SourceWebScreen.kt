package uz.gita.newsproject.ui.screens

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.newsproject.R
import uz.gita.newsproject.databinding.ScreenWebBinding
import uz.gita.newsproject.ui.viewModels.SourceWebScreenViewModel
import uz.gita.newsproject.ui.viewModels.impl.SourceWebScreenViewModelImpl

@AndroidEntryPoint
class SourceWebScreen : Fragment(R.layout.screen_web) {
    private val binding by viewBinding(ScreenWebBinding::bind)
    private val viewModel: SourceWebScreenViewModel by viewModels<SourceWebScreenViewModelImpl>()
    private lateinit var webView: WebView

    @SuppressLint("SetJavaScriptEnabled")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        webView = binding.webView
        webView.settings.javaScriptEnabled = true
        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                return false
            }

            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                viewModel.showProgressBar()
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                viewModel.hideProgressBar()
            }
        }
        arguments?.let {
            val url = it.get("base_url").toString()
            viewModel.loadUrl(url)
        }
        viewModel.urlLiveData.observe(viewLifecycleOwner, urlObserver)
        viewModel.hideProgressBarLiveData.observe(viewLifecycleOwner, hideProgressBarObserver)
        viewModel.showProgressBarLiveData.observe(viewLifecycleOwner, showProgressBarObserver)
    }

    private val urlObserver = Observer<String> {
        binding.webView.loadUrl(it)
    }

    private val showProgressBarObserver = Observer<Unit> {
        binding.progressBar.show()
    }
    private val hideProgressBarObserver = Observer<Unit> {
        binding.progressBar.hide()
    }
}