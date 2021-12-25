package uz.gita.newsproject.data.shared

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import uz.gita.newsproject.utils.StringPreference
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDatabase @Inject constructor(@ApplicationContext context: Context){
    private val sharedPref = context.getSharedPreferences("news_api", Context.MODE_PRIVATE)

    var login : String by StringPreference(sharedPref)
    var username : String by StringPreference(sharedPref)
    var password : String by StringPreference(sharedPref)
}