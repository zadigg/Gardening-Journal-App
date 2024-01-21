package com.example.gardenlog

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gardenlog.adapter.GardenListAdapter
import com.example.gardenlog.model.Garden
import com.example.gardenlog.viewmodel.GardenViewModel
import com.example.gardenlog.viewmodel.GardenViewModelFactory
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private val newGardenActivityRequestCode = 1
    val gardenViewModel: GardenViewModel by viewModels {
        GardenViewModelFactory((applicationContext as GardensApplication).gardenRepository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = GardenListAdapter { gardenId ->
            val intent = Intent(this@MainActivity, GardenDetailActivity::class.java)
            intent.putExtra("gardenId", gardenId)
            startActivity(intent)
        }

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this) // Set the LayoutManager here

        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            val intent = Intent(this@MainActivity, AddGardenActivity::class.java)
            startActivityForResult(intent, newGardenActivityRequestCode)
        }

        gardenViewModel.allGardens.observe(this) { gardens ->
            gardens?.let { adapter.submitList(it) }
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, intentData: Intent?) {
        super.onActivityResult(requestCode, resultCode, intentData)
        if (requestCode == newGardenActivityRequestCode && resultCode == Activity.RESULT_OK) {
            intentData?.getParcelableExtra<Garden>(AddGardenActivity.EXTRA_REPLY_NAME)?.let { garden ->
                gardenViewModel.insert(garden)
            } ?: run {
                Toast.makeText(
                    applicationContext,
                    R.string.app_name,
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }
}
