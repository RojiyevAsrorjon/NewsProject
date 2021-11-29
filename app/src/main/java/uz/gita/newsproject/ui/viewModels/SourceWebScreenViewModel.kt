package uz.gita.newsproject.ui.viewModels

import androidx.lifecycle.LiveData

interface SourceWebScreenViewModel {
    val showProgressBarLiveData : LiveData<Unit>
    val hideProgressBarLiveData : LiveData<Unit>

    val urlLiveData : LiveData<String>
    fun loadUrl(url : String)

    fun showProgressBar()
    fun hideProgressBar()
}