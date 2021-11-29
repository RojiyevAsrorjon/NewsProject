package uz.gita.newsproject.ui.viewModels.impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.gita.newsproject.data.models.responses.ArticlesItem
import uz.gita.newsproject.domen.repositories.NewsRepository
import uz.gita.newsproject.ui.viewModels.SearchScreenViewModel
import uz.gita.newsproject.utils.isConnected
import javax.inject.Inject

@HiltViewModel
class SearchScreenViewModelImpl @Inject constructor(private val newsRepository: NewsRepository) : ViewModel(), SearchScreenViewModel {
    override val newsLiveData = MutableLiveData<List<ArticlesItem>>()
    override val errorLiveData = MutableLiveData<String>()
    override val showProgressBarLiveData = MutableLiveData<Unit>()
    override val hideProgressBarLiveData = MutableLiveData<Unit>()
    override val openNetworkScreenLiveData = MutableLiveData<Boolean>()


    override fun loadData(searchText : String) {
        if (!isConnected()) {
            openNetworkScreenLiveData.value = false
        }
        else{
            showProgressBarLiveData.value = Unit
            openNetworkScreenLiveData.value = true
            newsRepository.getSearchNews(searchText).onEach {
                it.onSuccess { list ->
                    hideProgressBarLiveData.value = Unit
                    newsLiveData.value = list
                }
                it.onFailure { throwable ->
                    hideProgressBarLiveData.value = Unit
                    errorLiveData.value = throwable.message
                }
            }.launchIn(viewModelScope)
        }
    }

    init {
        if (!isConnected()) {
            openNetworkScreenLiveData.value = false
        }
    }
}