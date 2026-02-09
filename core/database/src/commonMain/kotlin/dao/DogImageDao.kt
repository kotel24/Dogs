package dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import entities.DogImageEntity

@Dao
interface DogImageDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(dogImageEntity: DogImageEntity)

    @Query("SELECT * FROM dog_images ORDER BY id DESC")
    suspend fun getAll(): List<DogImageEntity>

}