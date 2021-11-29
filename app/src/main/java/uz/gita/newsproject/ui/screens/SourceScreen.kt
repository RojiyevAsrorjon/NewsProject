package uz.gita.newsproject.ui.screens

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.newsproject.R
import uz.gita.newsproject.databinding.PageSourceBinding
import uz.gita.newsproject.ui.adapters.PagerAdapter
import uz.gita.newsproject.ui.viewModels.SourceScreenViewModel
import uz.gita.newsproject.ui.viewModels.impl.SourceScreenViewModelImpl

@AndroidEntryPoint
class SourceScreen : Fragment(R.layout.page_source) {
    private val binding by viewBinding(PageSourceBinding::bind)
    private lateinit var pagerAdapter: PagerAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        pagerAdapter = activity?.supportFragmentManager?.let { PagerAdapter(it, lifecycle) }!!
        setViews()
    }

    private fun setViews() {
        val categories = listOf<String>("General", "Business", "Sports", "Entertainment", "Health", "Science", "Technology")
        val viewPager = binding.viewpager
        val tabLayout = binding.tabLayout
        viewPager.adapter = pagerAdapter
        TabLayoutMediator(tabLayout, viewPager) { tab, pos ->
            tab.text = categories[pos]
        }.attach()
    }


}