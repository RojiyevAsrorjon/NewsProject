package uz.gita.newsproject.ui.viewModels

import androidx.lifecycle.LiveData

interface NetworkScreenViewModel {
    val backButtonLiveData : LiveData<Unit>

    fun backToStack()
}