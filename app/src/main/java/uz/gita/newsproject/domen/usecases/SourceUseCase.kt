package uz.gita.newsproject.domen.usecases

import kotlinx.coroutines.flow.Flow
import uz.gita.newsproject.data.models.responses.SourcesItem

interface SourceUseCase {
    fun getSourceNewsByCategory(category: String): Flow<List<SourcesItem>>
    suspend fun insert(data: SourcesItem)
}