package uz.gita.newsproject.data.models.responses

import androidx.room.Entity
import androidx.room.PrimaryKey

data class SourceResponse(
	val sources: List<SourcesItem>,
	val status: String
)

@Entity(tableName = "sources_table")
data class SourcesItem(
	val country: String,
	val name: String,
	val description: String,
	val language: String,
	@PrimaryKey
	val id: String,
	val category: String,
	val url: String
)

