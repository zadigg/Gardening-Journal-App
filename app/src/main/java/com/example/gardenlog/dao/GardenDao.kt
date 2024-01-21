package com.example.gardenlog.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.gardenlog.model.Garden
import kotlinx.coroutines.flow.Flow

@Dao
interface GardenDao {

    @Query("SELECT * FROM garden_table ORDER BY garden_id ASC")
    fun getGarden(): Flow<List<Garden>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(plant: Garden)

    @Query("DELETE FROM garden_table")
    suspend fun deleteAll()

    @Query("SELECT * FROM garden_table WHERE garden_id = :gardenId")
    fun getGardenById(gardenId: Int): LiveData<Garden>
}
