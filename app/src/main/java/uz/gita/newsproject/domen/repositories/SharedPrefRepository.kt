package uz.gita.newsproject.domen.repositories

import uz.gita.newsproject.data.models.LoginDataClass
import uz.gita.newsproject.data.models.RegisterDataClass

interface SharedPrefRepository {
    fun register(registerData : RegisterDataClass)
    fun login() : LoginDataClass
}