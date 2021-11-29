package uz.gita.newsproject.domen.repositories.impl

import kotlinx.coroutines.flow.Flow
import uz.gita.newsproject.data.models.responses.SourcesItem
import uz.gita.newsproject.data.room.AppDatabase
import uz.gita.newsproject.domen.repositories.FavouriteRepository
import javax.inject.Inject

class FavouriteRepositoryImpl @Inject constructor() : FavouriteRepository {
    private val dao = AppDatabase.getDatabase().sourceDao()
    override suspend fun getFavouriteNews(): Flow<List<SourcesItem>> = dao.getAll()

    override suspend fun delete(data: SourcesItem) = dao.delete(data)

    override suspend fun insert(data: SourcesItem) = dao.insert(data)

}