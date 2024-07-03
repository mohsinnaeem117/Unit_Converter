package com.example.unitconverter

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    lateinit var btnDistanceConverter: Button
    lateinit var btnWeightConverter : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        btnDistanceConverter =  findViewById(R.id.btnDistance)
        btnWeightConverter = findViewById(R.id.btnWeight)

        btnDistanceConverter.setOnClickListener{
            val intent = Intent(this,DistanceConverter::class.java)
            startActivity(intent)
        }

        btnWeightConverter.setOnClickListener {
            val intent = Intent(this,WeightConverter::class.java)
            startActivity(intent)
        }

    }
}
