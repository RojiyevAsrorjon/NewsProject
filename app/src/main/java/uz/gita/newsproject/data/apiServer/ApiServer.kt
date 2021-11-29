package uz.gita.newsproject.data.apiServer

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.readystatesoftware.chuck.ChuckInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import uz.gita.newsproject.BuildConfig.BASE_URL
import uz.gita.newsproject.BuildConfig.LOGGING
import uz.gita.newsproject.app.App

object ApiServer {
    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .client(getHttpClient())
        .build()

    private fun getHttpClient() : OkHttpClient{
        return OkHttpClient.Builder()
            .addLogging()
            .build()
    }
}
fun OkHttpClient.Builder.addLogging() : OkHttpClient.Builder{
    HttpLoggingInterceptor.Level.BODY
    val logging = object :HttpLoggingInterceptor.Logger{
        override fun log(message: String) {
            Timber.d(message)
        }
    }
    if (LOGGING){
        addInterceptor(ChuckInterceptor(App.instance))
        addInterceptor(HttpLoggingInterceptor(logging))
    }
    return this
}