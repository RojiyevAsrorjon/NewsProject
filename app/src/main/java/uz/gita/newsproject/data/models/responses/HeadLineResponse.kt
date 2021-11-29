package uz.gita.newsproject.data.models.responses

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

data class HeadLineResponse(
	val totalResults: Int,
	val articles: List<ArticlesItem>,
	val status: String
)

data class Source(
	val name: String,
	val id: String
)

data class ArticlesItem(
	val publishedAt: String,
	val author: String,
	val urlToImage: String,
	val description: String,
	@Embedded val source: Source,
	@PrimaryKey
	val title: String,
	val url: String,
	val content: String
)

