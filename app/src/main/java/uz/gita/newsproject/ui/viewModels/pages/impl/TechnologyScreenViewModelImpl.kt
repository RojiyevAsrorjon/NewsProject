package uz.gita.newsproject.ui.viewModels.pages.impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import uz.gita.newsproject.data.models.responses.SourcesItem
import uz.gita.newsproject.domen.usecases.SourceUseCase
import uz.gita.newsproject.ui.viewModels.pages.*
import uz.gita.newsproject.utils.isConnected
import javax.inject.Inject

@HiltViewModel
class TechnologyScreenViewModelImpl @Inject constructor(private val useCase : SourceUseCase) : ViewModel(), TechnologyScreenViewModel {
    override val sourceNewsLiveData = MutableLiveData<List<SourcesItem>>()
    override val showProgressBarLiveData = MutableLiveData<Unit>()
    override val hideProgressBarLiveData = MutableLiveData<Unit>()
    override val connectionLiveData = MutableLiveData<Boolean>()
    override val openWebScreenLiveData = MutableLiveData<String>()
    override val errorMessageLiveData = MutableLiveData<String>()
    init {
        if (!isConnected()) {
            connectionLiveData.value = false
        }
        else{
            showProgressBarLiveData.value = Unit
            connectionLiveData.value = true

            useCase.getSourceNewsByCategory("technology").onEach {
                hideProgressBarLiveData.value = Unit
                sourceNewsLiveData.value = it
            }.launchIn(viewModelScope)
        }
    }

    override fun loadData(){
        if (!isConnected()) {
            connectionLiveData.value = false
        } else {
            showProgressBarLiveData.value = Unit
            connectionLiveData.value = true

            useCase.getSourceNewsByCategory("technology").onEach {
                hideProgressBarLiveData.value = Unit
                sourceNewsLiveData.value = it
            }.launchIn(viewModelScope)
        }
    }

    override fun insert(data: SourcesItem) {
        viewModelScope.launch(Dispatchers.IO) {
            useCase.insert(data)
        }
    }
    override fun openWebScreen(url: String){
        if (!isConnected()) {
            errorMessageLiveData.value = "No connection"
        }
        else{
            openWebScreenLiveData.value = url
        }
    }

}