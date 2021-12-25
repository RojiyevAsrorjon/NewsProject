package uz.gita.newsproject.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import uz.gita.newsproject.data.shared.LocalDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class SharedPreferenceModel {

    @Provides
    @Singleton
    fun getSharedPreferences(@ApplicationContext context: Context) : LocalDatabase = LocalDatabase(context)
}