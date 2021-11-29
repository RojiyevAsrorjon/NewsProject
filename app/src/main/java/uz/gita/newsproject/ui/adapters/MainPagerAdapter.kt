package uz.gita.newsproject.ui.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import uz.gita.newsproject.ui.screens.FavouriteScreen
import uz.gita.newsproject.ui.screens.SearchScreen
import uz.gita.newsproject.ui.screens.SourceScreen

class MainPagerAdapter(activity: FragmentManager, lifecycle: Lifecycle) : FragmentStateAdapter(activity, lifecycle) {
    override fun getItemCount() = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> SourceScreen()
            1 -> SearchScreen()
            else -> FavouriteScreen()
        }
    }
}