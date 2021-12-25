package uz.gita.newsproject.domen.usecases.impl

import uz.gita.newsproject.data.models.RegisterDataClass
import uz.gita.newsproject.domen.repositories.SharedPrefRepository
import uz.gita.newsproject.domen.usecases.RegisterScreenUseCase
import javax.inject.Inject

class RegisterScreenUseCaseImpl @Inject constructor(private val repository : SharedPrefRepository) : RegisterScreenUseCase {
    override fun isRegisteredBefore(): Boolean = repository.login().login.isEmpty() && repository.login().password.isEmpty()

    override fun register(data: RegisterDataClass) {
        repository.register(data)
    }
}