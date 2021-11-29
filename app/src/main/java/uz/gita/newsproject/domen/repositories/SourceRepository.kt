package uz.gita.newsproject.domen.repositories

import kotlinx.coroutines.flow.Flow
import uz.gita.newsproject.data.models.responses.SourcesItem

interface SourceRepository {
    suspend fun getSourceNews() : Flow<List<SourcesItem>>
}