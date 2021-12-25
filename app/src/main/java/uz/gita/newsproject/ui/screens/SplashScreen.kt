package uz.gita.newsproject.ui.screens

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.newsproject.R
import uz.gita.newsproject.ui.viewModels.SplashScreenViewModel
import uz.gita.newsproject.ui.viewModels.impl.SplashScreenViewModelImpl

@AndroidEntryPoint
class SplashScreen : Fragment(R.layout.screen_splash) {
    private val viewModel : SplashScreenViewModel by viewModels<SplashScreenViewModelImpl>()
    private val navController by lazy(LazyThreadSafetyMode.NONE) { findNavController() }
    @SuppressLint("FragmentLiveDataObserve")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.openLoginScreenLiveData.observe(this, openLoginScreenObserver)
    }

    private val openLoginScreenObserver = Observer<Unit>{
        navController.navigate(R.id.action_splashScreen_to_loginScreen)
    }
}