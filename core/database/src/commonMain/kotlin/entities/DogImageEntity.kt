package entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dog_images")
data class DogImageEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val imageUrl: String,
)
