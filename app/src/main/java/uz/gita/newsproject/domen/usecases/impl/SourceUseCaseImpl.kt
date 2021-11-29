package uz.gita.newsproject.domen.usecases.impl

import android.annotation.SuppressLint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import uz.gita.newsproject.data.models.responses.SourcesItem
import uz.gita.newsproject.domen.repositories.FavouriteRepository
import uz.gita.newsproject.domen.repositories.SourceRepository
import uz.gita.newsproject.domen.usecases.SourceUseCase
import javax.inject.Inject

class SourceUseCaseImpl @Inject constructor(private val sourceRepository: SourceRepository, private val favouriteRepository: FavouriteRepository) : SourceUseCase {
    @SuppressLint("LogNotTimber")
    override fun getSourceNewsByCategory(category: String): Flow<List<SourcesItem>> = flow {
        sourceRepository.getSourceNews().collect {
            val list = it.filter { it.category == category && it.language == "en" }
            emit(list)
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun insert(data: SourcesItem) = favouriteRepository.insert(data)

}