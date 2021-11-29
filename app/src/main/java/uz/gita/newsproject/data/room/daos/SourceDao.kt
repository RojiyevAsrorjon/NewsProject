package uz.gita.newsproject.data.room.daos

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import uz.gita.newsproject.data.models.responses.SourcesItem

@Dao
interface SourceDao {

    @Query("SELECT * FROM sources_table")
    fun getAll() : Flow<List<SourcesItem>>

    @Delete
    fun delete(sourcesItem: SourcesItem)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(sourcesItem: SourcesItem)
}