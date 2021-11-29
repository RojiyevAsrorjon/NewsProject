package uz.gita.newsproject.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.gita.newsproject.domen.usecases.SourceUseCase
import uz.gita.newsproject.domen.usecases.impl.SourceUseCaseImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseModel {

    @Binds
    @Singleton
    abstract fun getSourceUseCase(sourceUseCaseImpl: SourceUseCaseImpl) : SourceUseCase

}