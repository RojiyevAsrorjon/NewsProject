package uz.gita.newsproject.ui.viewModels

import androidx.lifecycle.LiveData
import uz.gita.newsproject.data.models.LoginDataClass

interface LoginScreenViewModel {
    val openRegisterScreenLiveData: LiveData<Unit>
    val openMainScreenLiveData : LiveData<Unit>
    val errorMassageLiveData : LiveData<String>
    fun login(data : LoginDataClass)
    fun openRegisterScreen()
}