package uz.gita.newsproject.ui.viewModels.impl

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import uz.gita.newsproject.ui.viewModels.NetworkScreenViewModel
import javax.inject.Inject

@HiltViewModel
class NetworkScreenViewModelImpl @Inject constructor() : ViewModel(), NetworkScreenViewModel {
    override val backButtonLiveData = MutableLiveData<Unit>()
    override fun backToStack() {
        backButtonLiveData.value = Unit
    }
}