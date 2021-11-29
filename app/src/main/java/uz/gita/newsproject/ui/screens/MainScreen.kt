package uz.gita.newsproject.ui.screens

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.newsproject.R
import uz.gita.newsproject.data.models.ScreenState
import uz.gita.newsproject.databinding.ScreenMainBinding
import uz.gita.newsproject.ui.adapters.MainPagerAdapter
import uz.gita.newsproject.ui.viewModels.MainScreenViewModel
import uz.gita.newsproject.ui.viewModels.impl.MainScreenViewModelImpl

@AndroidEntryPoint
class MainScreen : Fragment(R.layout.screen_main) {
    private val binding by viewBinding(ScreenMainBinding::bind)
    private val viewModel: MainScreenViewModel by viewModels<MainScreenViewModelImpl>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val adapter = activity?.supportFragmentManager?.let { MainPagerAdapter(it, lifecycle) }
        binding.viewpager.adapter = adapter

        binding.viewpager.isUserInputEnabled = false

        binding.bottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.homeMenu -> {
                    viewModel.changePos(ScreenState.HOME.amount)
                }
                R.id.searchMenu -> viewModel.changePos(ScreenState.SEARCH.amount)
                else -> viewModel.changePos(ScreenState.FAVOURITE.amount)
            }
            return@setOnItemSelectedListener true
        }

        viewModel.homeScreenLiveData.observe(viewLifecycleOwner, homeScreenObserver)
        viewModel.searchScreenLiveData.observe(viewLifecycleOwner, searchScreenObserver)
        viewModel.favouriteScreenLiveData.observe(viewLifecycleOwner, favouriteScreenObserver)
        viewModel.currentPageLiveData.observe(viewLifecycleOwner, currentPageObserver)
    }

    private val homeScreenObserver = Observer<Unit> {
        binding.viewpager.currentItem = 0
        binding.bottomNavigation.selectedItemId = R.id.homeMenu
    }

    private val searchScreenObserver = Observer<Unit> {
        binding.viewpager.currentItem = 1
        binding.bottomNavigation.selectedItemId = R.id.searchMenu
    }

    private val favouriteScreenObserver = Observer<Unit> {
        binding.viewpager.currentItem = 2
        binding.bottomNavigation.selectedItemId = R.id.favouriteMenu
    }

    private val currentPageObserver = Observer<Int> {
        binding.viewpager.currentItem = it
    }
}