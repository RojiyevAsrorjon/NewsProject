package uz.gita.newsproject.ui.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import uz.gita.newsproject.ui.screens.pages.*

class PagerAdapter(activity: FragmentManager, lifecycle: Lifecycle) : FragmentStateAdapter(activity, lifecycle) {
    override fun getItemCount() = 7
    private val categories = listOf<String>("General", "Business", "Sports", "Entertainment","Health", "Science", "Technology")

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> GeneralScreen()
            1 -> BusinessScreen()
            2 -> SportsScreen()
            3 -> EntertainmentScreen()
            4 -> HealthScreen()
            5 -> ScienceScreen()
            else -> TechnologyScreen()
        }
    }

}