package uz.gita.newsproject.ui.viewModels

import androidx.lifecycle.LiveData
import uz.gita.newsproject.data.models.RegisterDataClass

interface RegisterScreenViewModel {
    val openMainScreenLiveData : LiveData<Unit>
    val errorMessageLiveData : LiveData<String>

    fun register(data : RegisterDataClass)
    fun showErrorMessage(error : String)

}