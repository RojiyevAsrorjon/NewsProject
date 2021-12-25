package uz.gita.newsproject.ui.viewModels.impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import uz.gita.newsproject.data.models.LoginDataClass
import uz.gita.newsproject.domen.usecases.LoginScreenUseCase
import uz.gita.newsproject.ui.viewModels.LoginScreenViewModel
import javax.inject.Inject

@HiltViewModel
class LoginScreenViewModelImpl @Inject constructor(val useCase: LoginScreenUseCase) : LoginScreenViewModel, ViewModel() {
    override val openRegisterScreenLiveData = MutableLiveData<Unit>()
    override val openMainScreenLiveData = MutableLiveData<Unit>()
    override val errorMassageLiveData = MutableLiveData<String>()
    override fun login(data: LoginDataClass) {
        val savedData = useCase.login()
        if (savedData.login == data.login && savedData.password == savedData.password) {
            openMainScreenLiveData.value = Unit
        } else errorMassageLiveData.value = "Login yoki parol xato kiritildi"
    }

    override fun openRegisterScreen() {
        if (!useCase.isRegisteredBefore()) {
            errorMassageLiveData.value = "Siz avval ro`yxatdan o`tgansiz"
        }
        else openRegisterScreenLiveData.value = Unit
    }


}