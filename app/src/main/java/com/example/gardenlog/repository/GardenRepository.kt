package com.example.gardenlog.repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.gardenlog.dao.GardenDao
import com.example.gardenlog.model.Garden
import kotlinx.coroutines.flow.Flow

class GardenRepository(private val gardenDao: GardenDao) {

    val allGarden: Flow<List<Garden>> = gardenDao.getGarden()
//check
    @Suppress("RedundantSuspendModifier")
    @WorkerThread

    fun getGardenById(gardenId: Int): LiveData<Garden> {
        return gardenDao.getGardenById(gardenId)
    }
    suspend fun insert(garden: Garden) {
        gardenDao.insert(garden)
    }


}
