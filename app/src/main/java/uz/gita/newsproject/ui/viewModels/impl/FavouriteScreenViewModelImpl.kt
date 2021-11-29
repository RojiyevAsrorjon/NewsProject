package uz.gita.newsproject.ui.viewModels.impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import uz.gita.newsproject.data.models.responses.SourcesItem
import uz.gita.newsproject.domen.repositories.FavouriteRepository
import uz.gita.newsproject.ui.viewModels.FavouriteScreenViewModel
import javax.inject.Inject


@HiltViewModel
class FavouriteScreenViewModelImpl @Inject constructor(private val repository : FavouriteRepository) : ViewModel(), FavouriteScreenViewModel {
    override val newsListLiveData = MutableLiveData<List<SourcesItem>>()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getFavouriteNews().collect { list ->
                newsListLiveData.postValue(list)
            }
        }
    }

    override fun delete(data: SourcesItem) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.delete(data)
        }
    }

    override fun insert(data: SourcesItem) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insert(data)
        }
    }
}