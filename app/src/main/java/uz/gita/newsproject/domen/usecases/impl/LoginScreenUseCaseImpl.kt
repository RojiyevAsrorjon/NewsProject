package uz.gita.newsproject.domen.usecases.impl

import uz.gita.newsproject.data.models.LoginDataClass
import uz.gita.newsproject.domen.repositories.SharedPrefRepository
import uz.gita.newsproject.domen.usecases.LoginScreenUseCase
import javax.inject.Inject

class LoginScreenUseCaseImpl @Inject constructor(private val repository: SharedPrefRepository) : LoginScreenUseCase {
    override fun login(): LoginDataClass = repository.login()
    override fun isRegisteredBefore(): Boolean = repository.login().login.isEmpty() && repository.login().password.isEmpty()

}