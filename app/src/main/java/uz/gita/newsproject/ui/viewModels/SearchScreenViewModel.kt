package uz.gita.newsproject.ui.viewModels

import androidx.lifecycle.LiveData
import uz.gita.newsproject.data.models.responses.ArticlesItem

interface SearchScreenViewModel {
    val newsLiveData : LiveData<List<ArticlesItem>>
    val errorLiveData : LiveData<String>
    val showProgressBarLiveData : LiveData<Unit>
    val hideProgressBarLiveData : LiveData<Unit>

    val openNetworkScreenLiveData: LiveData<Boolean>

    fun loadData(searchText : String)

}