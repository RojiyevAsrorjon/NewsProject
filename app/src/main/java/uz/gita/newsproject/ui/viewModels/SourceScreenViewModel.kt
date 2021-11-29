package uz.gita.newsproject.ui.viewModels

import androidx.lifecycle.LiveData

interface SourceScreenViewModel {
    val connectionLiveData : LiveData<Boolean>
}