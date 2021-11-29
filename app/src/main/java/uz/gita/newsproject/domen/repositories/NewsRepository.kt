package uz.gita.newsproject.domen.repositories

import kotlinx.coroutines.flow.Flow
import uz.gita.newsproject.data.models.responses.ArticlesItem

interface NewsRepository {
    fun getSearchNews(search : String) : Flow<Result<List<ArticlesItem>>>
}