package uz.gita.newsproject.ui.viewModels

import androidx.lifecycle.LiveData

interface MainScreenViewModel {
    val homeScreenLiveData : LiveData<Unit>
    val searchScreenLiveData : LiveData<Unit>
    val favouriteScreenLiveData : LiveData<Unit>
    fun changePos(position: Int)

    val currentPageLiveData : LiveData<Int>
}