package uz.gita.newsproject.data.apiClients

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import uz.gita.newsproject.data.models.responses.SourceResponse

interface SourcesApi {
    @GET("top-headlines/sources")
    suspend fun getSources(@Query("apiKey") apiKey : String = "12ddc2beaa074b27b5ab55370fded6ed") : Response<SourceResponse>
}