package uz.gita.newsproject.domen.repositories.impl

import android.annotation.SuppressLint
import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import uz.gita.newsproject.data.apiClients.SourcesApi
import uz.gita.newsproject.data.apiServer.ApiServer
import uz.gita.newsproject.data.models.responses.SourcesItem
import uz.gita.newsproject.domen.repositories.SourceRepository
import javax.inject.Inject

class SourceRepositoryImpl @Inject constructor() : SourceRepository {
    private val retrofit = ApiServer.retrofit.create(SourcesApi::class.java)
    @SuppressLint("LogNotTimber")
    override suspend fun getSourceNews(): Flow<List<SourcesItem>> = flow{
        Log.d("TTT", "repo")
        val response = retrofit.getSources()
        if (response.isSuccessful) {
            emit(response.body()!!.sources)
        }
    }
}