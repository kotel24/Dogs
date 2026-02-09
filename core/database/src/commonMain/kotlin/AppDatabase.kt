import androidx.room.*
import dao.DogImageDao
import entities.DogImageEntity

@Database(entities = [DogImageEntity::class], version = 1, exportSchema = true)
abstract class AppDatabase:RoomDatabase() {
    abstract fun dogImageDao(): DogImageDao
}