package uz.gita.newsproject.domen.usecases

import uz.gita.newsproject.data.models.LoginDataClass

interface LoginScreenUseCase {
    fun login() : LoginDataClass
    fun isRegisteredBefore() :Boolean

}