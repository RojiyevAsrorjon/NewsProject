package uz.gita.newsproject.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.gita.newsproject.domen.usecases.LoginScreenUseCase
import uz.gita.newsproject.domen.usecases.RegisterScreenUseCase
import uz.gita.newsproject.domen.usecases.SourceUseCase
import uz.gita.newsproject.domen.usecases.impl.LoginScreenUseCaseImpl
import uz.gita.newsproject.domen.usecases.impl.RegisterScreenUseCaseImpl
import uz.gita.newsproject.domen.usecases.impl.SourceUseCaseImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseModel {

    @Binds
    @Singleton
    abstract fun getSourceUseCase(sourceUseCaseImpl: SourceUseCaseImpl) : SourceUseCase

    @Binds
    @Singleton
    abstract fun getLoginScreenUseCase(loginScreenUseCaseImpl: LoginScreenUseCaseImpl) : LoginScreenUseCase

    @Binds
    @Singleton
    abstract fun getRegisterScreenUseCase(registerScreenUseCaseImpl: RegisterScreenUseCaseImpl) : RegisterScreenUseCase
}