package com.example.gardenlog

import android.app.Application
import com.example.gardenlog.database.GardenRoomDatabase
import com.example.gardenlog.repository.GardenRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class GardensApplication : Application() {
    val applicationScope = CoroutineScope(SupervisorJob())

    val gardenDatabase by lazy { GardenRoomDatabase.getDatabase(this, applicationScope) }
    val gardenRepository by lazy { GardenRepository(gardenDatabase.gardenDao()) }
}