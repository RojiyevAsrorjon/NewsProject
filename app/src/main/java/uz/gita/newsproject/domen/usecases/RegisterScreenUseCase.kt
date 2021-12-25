package uz.gita.newsproject.domen.usecases

import uz.gita.newsproject.data.models.LoginDataClass
import uz.gita.newsproject.data.models.RegisterDataClass

interface RegisterScreenUseCase {

    fun isRegisteredBefore() : Boolean
    fun register(data : RegisterDataClass)
}