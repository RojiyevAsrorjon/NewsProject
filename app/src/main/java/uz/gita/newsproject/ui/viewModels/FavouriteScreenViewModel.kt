package uz.gita.newsproject.ui.viewModels

import androidx.lifecycle.LiveData
import uz.gita.newsproject.data.models.responses.SourcesItem

interface FavouriteScreenViewModel {
    val newsListLiveData: LiveData<List<SourcesItem>>

    fun delete(data : SourcesItem)
    fun insert(data : SourcesItem)
}