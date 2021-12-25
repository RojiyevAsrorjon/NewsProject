package uz.gita.newsproject.ui.viewModels.impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import uz.gita.newsproject.data.models.RegisterDataClass
import uz.gita.newsproject.domen.usecases.RegisterScreenUseCase
import uz.gita.newsproject.ui.viewModels.RegisterScreenViewModel
import javax.inject.Inject

@HiltViewModel
class RegisterScreenViewModelImpl @Inject constructor(private val useCase: RegisterScreenUseCase) : ViewModel(), RegisterScreenViewModel{
    override val openMainScreenLiveData = MutableLiveData<Unit>()
    override val errorMessageLiveData = MutableLiveData<String>()

    override fun register(data: RegisterDataClass) {
        useCase.register(data)
        openMainScreenLiveData.value = Unit
    }

    override fun showErrorMessage(error: String) {
        errorMessageLiveData.value = error
    }
}