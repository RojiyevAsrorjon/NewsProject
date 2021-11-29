package uz.gita.newsproject.ui.viewModels.pages

import androidx.lifecycle.LiveData
import uz.gita.newsproject.data.models.responses.SourcesItem

interface EntertainmentScreenViewModel {
    val sourceNewsLiveData : LiveData<List<SourcesItem>>
    val showProgressBarLiveData : LiveData<Unit>
    val hideProgressBarLiveData: LiveData<Unit>

    val openWebScreenLiveData: LiveData<String>
    val errorMessageLiveData : LiveData<String>
    val connectionLiveData : LiveData<Boolean>
    fun loadData()
    fun insert(data : SourcesItem)
    fun openWebScreen(url : String)

}