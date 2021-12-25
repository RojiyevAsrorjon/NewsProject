package uz.gita.newsproject.ui.viewModels.impl

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import uz.gita.newsproject.ui.viewModels.SplashScreenViewModel
import javax.inject.Inject

@HiltViewModel
class SplashScreenViewModelImpl @Inject constructor() : SplashScreenViewModel, ViewModel(){
    override val openLoginScreenLiveData = MutableLiveData<Unit>()
    init {
        viewModelScope.launch {
            delay(1500)
            openLoginScreenLiveData.value = Unit
        }
    }
}