package com.example.gardenlog.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.gardenlog.dao.GardenDao
import com.example.gardenlog.model.Garden
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Database(entities = [Garden::class], version = 1)
abstract class GardenRoomDatabase : RoomDatabase() {

    abstract fun gardenDao(): GardenDao

    companion object {
        @Volatile
        private var INSTANCE: GardenRoomDatabase? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): GardenRoomDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    GardenRoomDatabase::class.java,
                    "garden_database"
                )
                    .fallbackToDestructiveMigration()
                    .addCallback(GardenDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                instance
            }
        }

        private class GardenDatabaseCallback(
            private val scope: CoroutineScope
        ) : RoomDatabase.Callback() {

            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)

                INSTANCE?.let { database ->
                    scope.launch(Dispatchers.IO) {
                        populateDatabase(database.gardenDao())
                    }
                }
            }
        }

        suspend fun populateDatabase(gardenDao: GardenDao) {

            withContext(Dispatchers.IO) {
                gardenDao.deleteAll()

                val garden = Garden(
                    gardenName = "Garden",
                    gardenType = "Garden thing",
                    wateringFrequency = "sometimes",
                    gardeningDate = "23/34"
                )
                gardenDao.insert(garden)
            }
        }
    }
}
