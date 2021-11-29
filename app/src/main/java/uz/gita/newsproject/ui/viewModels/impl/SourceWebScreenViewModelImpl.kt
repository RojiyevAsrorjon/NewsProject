package uz.gita.newsproject.ui.viewModels.impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import uz.gita.newsproject.ui.viewModels.SourceWebScreenViewModel
import javax.inject.Inject

@HiltViewModel
class SourceWebScreenViewModelImpl @Inject constructor() : ViewModel(), SourceWebScreenViewModel {
    override val showProgressBarLiveData = MutableLiveData<Unit>()
    override val hideProgressBarLiveData = MutableLiveData<Unit>()
    override val urlLiveData = MutableLiveData<String>()
    override fun loadUrl(url: String) {
        urlLiveData.value = url
    }

    override fun showProgressBar() {
        showProgressBarLiveData.value = Unit
    }

    override fun hideProgressBar() {
        hideProgressBarLiveData.value = Unit
    }


}