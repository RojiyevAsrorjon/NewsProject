package uz.gita.newsproject.domen.repositories

import kotlinx.coroutines.flow.Flow
import uz.gita.newsproject.data.models.responses.SourcesItem

interface FavouriteRepository {
    suspend fun getFavouriteNews(): Flow<List<SourcesItem>>
    suspend fun delete(data: SourcesItem)
    suspend fun insert(data: SourcesItem)
}