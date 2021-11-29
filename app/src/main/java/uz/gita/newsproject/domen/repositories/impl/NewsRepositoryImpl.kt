package uz.gita.newsproject.domen.repositories.impl

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import uz.gita.newsproject.data.apiClients.EverythingApi
import uz.gita.newsproject.data.apiServer.ApiServer
import uz.gita.newsproject.data.models.responses.ArticlesItem
import uz.gita.newsproject.domen.repositories.NewsRepository
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor() : NewsRepository{
    private val retrofit = ApiServer.retrofit.create(EverythingApi::class.java)
    override fun getSearchNews(search : String): Flow<Result<List<ArticlesItem>>> = flow{
        val response = retrofit.getAllNews(search)
        if (response.isSuccessful) {
            emit(Result.success(response.body()!!.articles))
        } else {
            val st = response.message()
            emit(Result.failure(Throwable(st)))
        }
    }.flowOn(Dispatchers.IO)
}