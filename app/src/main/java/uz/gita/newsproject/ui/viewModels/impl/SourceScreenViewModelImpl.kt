package uz.gita.newsproject.ui.viewModels.impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import uz.gita.newsproject.ui.viewModels.SourceScreenViewModel
import uz.gita.newsproject.utils.isConnected
import javax.inject.Inject

@HiltViewModel
class SourceScreenViewModelImpl @Inject constructor() : ViewModel(), SourceScreenViewModel {
    override val connectionLiveData = MutableLiveData<Boolean>()

    init {
        connectionLiveData.value = isConnected()
    }
}