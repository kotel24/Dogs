package org.example.project


val databaseModule
    get() = platformDatabaseModule(fileName = "database.db")
        .apply {
            single<DogImageDao> { get<AppDatabase>().dogsDao() }
        }
class DatabaseModule {
}
