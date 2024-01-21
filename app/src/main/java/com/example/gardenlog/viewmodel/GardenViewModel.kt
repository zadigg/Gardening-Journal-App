package com.example.gardenlog.viewmodel

import androidx.lifecycle.*
import com.example.gardenlog.model.Garden
import com.example.gardenlog.repository.GardenRepository
import kotlinx.coroutines.launch

class GardenViewModel(private val repository: GardenRepository) : ViewModel() {

    val allGardens: LiveData<List<Garden>> = repository.allGarden.asLiveData()

    fun insert(garden: Garden) = viewModelScope.launch {
        repository.insert(garden)
    }

    fun getGardenById(gardenId: Int): LiveData<Garden> {
        return repository.getGardenById(gardenId)
    }
}

class GardenViewModelFactory(private val repository: GardenRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GardenViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return GardenViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
