package org.example.project

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [
        DogImageEntity::class,
    ],
    version = DATABASE_VERSION
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun dogsDao(): DogImageDao
}

private const val DATABASE_VERSION = 1
package org.example.project 

