package uz.gita.newsproject.ui.viewModels.pages

import androidx.lifecycle.LiveData
import uz.gita.newsproject.data.models.responses.SourcesItem

interface ScienceScreenViewModel {
    val sourceNewsLiveData : LiveData<List<SourcesItem>>
    val showProgressBarLiveData : LiveData<Unit>
    val hideProgressBarLiveData: LiveData<Unit>
    val openWebScreenLiveData: LiveData<String>
    val errorMessageLiveData : LiveData<String>
    fun openWebScreen(url : String)

    val connectionLiveData : LiveData<Boolean>
    fun loadData()
    fun insert(data : SourcesItem)

}