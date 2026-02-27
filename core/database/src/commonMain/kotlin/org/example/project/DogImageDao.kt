package org.example.project

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import org.example.project.DogImageEntity

@Dao
interface DogImageDao {

    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun insert(dogImageEntity: DogImageEntity)

    @Query("SELECT * FROM dog_images ORDER BY id DESC")
    suspend fun getAll(): List<DogImageEntity>

    @Query("SELECT * FROM dog_images")
    fun observe(): Flow<List<DogImageEntity>>
}
package org.example.project 

