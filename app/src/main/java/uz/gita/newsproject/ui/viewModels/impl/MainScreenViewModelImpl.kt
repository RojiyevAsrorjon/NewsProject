package uz.gita.newsproject.ui.viewModels.impl

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import uz.gita.newsproject.ui.viewModels.MainScreenViewModel
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModelImpl @Inject constructor() : ViewModel(), MainScreenViewModel {
    override val homeScreenLiveData = MutableLiveData<Unit>()
    override val searchScreenLiveData = MutableLiveData<Unit>()
    override val favouriteScreenLiveData = MutableLiveData<Unit>()
    override val currentPageLiveData = MutableLiveData<Int>()
    private var pos = 0

    init {
        currentPageLiveData.value = pos
    }

    override fun changePos(position: Int) {
        if (position == pos) return

        if (position == 0) homeScreenLiveData.value = Unit
        else if (position ==1) searchScreenLiveData.value = Unit
        else favouriteScreenLiveData.value = Unit
        pos = position
        currentPageLiveData.value = pos
    }
}