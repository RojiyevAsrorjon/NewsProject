package uz.gita.newsproject.domen.repositories.impl

import uz.gita.newsproject.data.models.LoginDataClass
import uz.gita.newsproject.data.models.RegisterDataClass
import uz.gita.newsproject.data.shared.LocalDatabase
import uz.gita.newsproject.domen.repositories.SharedPrefRepository
import javax.inject.Inject

class SharedPrefRepositoryImpl @Inject constructor(private val pref : LocalDatabase) : SharedPrefRepository{
    override fun register(registerData: RegisterDataClass) {
        pref.login = registerData.login
        pref.password = registerData.password
    }

    override fun login() = LoginDataClass(pref.login, pref.password)

}