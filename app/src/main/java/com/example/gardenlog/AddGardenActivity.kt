package com.example.gardenlog

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.gardenlog.model.Garden

class AddGardenActivity : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_garden)
        val gardenNameEditText = findViewById<EditText>(R.id.plant_name)
        val gardenTypeEditText = findViewById<EditText>(R.id.plant_type)
        val wateringFrequencyEditText = findViewById<EditText>(R.id.watering_frequency)
        val plantingDateEditText = findViewById<EditText>(R.id.planting_date)

        val saveButton = findViewById<Button>(R.id.button_save)
        saveButton.setOnClickListener {
            val replyIntent = Intent()

            if (TextUtils.isEmpty(gardenNameEditText.text) || TextUtils.isEmpty(gardenTypeEditText.text) ||
                TextUtils.isEmpty(wateringFrequencyEditText.text) || TextUtils.isEmpty(plantingDateEditText.text)
            ) {
                setResult(Activity.RESULT_CANCELED, replyIntent)
            } else {
                val garden = Garden(
                    0,
                    gardenNameEditText.text.toString(),
                    gardenTypeEditText.text.toString(),
                    wateringFrequencyEditText.text.toString(),
                    plantingDateEditText.text.toString()
                )

                replyIntent.putExtra(EXTRA_REPLY_NAME, garden)

                setResult(Activity.RESULT_OK, replyIntent)
            }

            finish()
        }
    }

    companion object {
        const val EXTRA_REPLY_NAME = "com.example.gardening.REPLY_NAME"

    }
}
