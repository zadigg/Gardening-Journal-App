package com.example.gardenlog

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.gardenlog.model.Garden
import com.example.gardenlog.viewmodel.GardenViewModel
import com.example.gardenlog.viewmodel.GardenViewModelFactory

class GardenDetailActivity : AppCompatActivity() {

    private var gardenId: Int = 0

    val gardenViewModel: GardenViewModel by viewModels {
        GardenViewModelFactory((applicationContext as GardensApplication).gardenRepository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gardens_detail)

        gardenId = intent.getIntExtra("gardenId", 0) + 1
        println(gardenId)

        gardenViewModel.getGardenById(gardenId).observe(this, Observer { garden ->
            garden?.let { displayGardenDetails(it) }
        })
    }

    private fun displayGardenDetails(garden: Garden) {
        findViewById<TextView>(R.id.gardenNameTextView)?.text = garden.gardenName
        findViewById<TextView>(R.id.gardenTypeTextView)?.text = "Species: ${garden.gardenType}"
        findViewById<TextView>(R.id.wateringFrequencyTextView)?.text =
            "Watering Frequency: ${garden.wateringFrequency} days"
        findViewById<TextView>(R.id.plantingDateTextView)?.text =
            "Planting Date: ${garden.gardeningDate}"
    }
}