package uz.gita.newsproject.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.gita.newsproject.domen.repositories.FavouriteRepository
import uz.gita.newsproject.domen.repositories.NewsRepository
import uz.gita.newsproject.domen.repositories.SharedPrefRepository
import uz.gita.newsproject.domen.repositories.SourceRepository
import uz.gita.newsproject.domen.repositories.impl.FavouriteRepositoryImpl
import uz.gita.newsproject.domen.repositories.impl.NewsRepositoryImpl
import uz.gita.newsproject.domen.repositories.impl.SharedPrefRepositoryImpl
import uz.gita.newsproject.domen.repositories.impl.SourceRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModel {

    @Binds
    @Singleton
    abstract fun getNewsRepository(newsRepositoryImpl: NewsRepositoryImpl) : NewsRepository

    @Binds
    @Singleton
    abstract fun getSourceRepository(sourceRepositoryImpl: SourceRepositoryImpl) : SourceRepository

    @Binds
    @Singleton
    abstract fun getFavouriteRepository(favouriteRepositoryImpl: FavouriteRepositoryImpl) : FavouriteRepository

    @Binds
    @Singleton
    abstract fun getSharedRepository(sharedPrefRepositoryImpl: SharedPrefRepositoryImpl) : SharedPrefRepository
}