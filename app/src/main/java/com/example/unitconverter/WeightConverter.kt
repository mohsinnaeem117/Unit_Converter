package com.example.unitconverter

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class WeightConverter : AppCompatActivity() {

    lateinit var input: EditText
    lateinit var unit: Spinner
    lateinit var kg: TextView
    lateinit var g: TextView
    lateinit var lb: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_weight_converter)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        input = findViewById(R.id.ET)
        unit = findViewById(R.id.unit)
        kg = findViewById(R.id.kg)
        g = findViewById(R.id.g)
        lb = findViewById(R.id.lb)

        val arr = arrayOf("kg", "g", "lb")
        unit.adapter = ArrayAdapter(this@WeightConverter, R.layout.spinner_item, arr)

        unit.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                update()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Do nothing
            }
        }

        input.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence?, start: Int, count: Int, after: Int) {
                // Do nothing
            }

            override fun onTextChanged(charSequence: CharSequence?, start: Int, before: Int, count: Int) {
                // Do nothing
            }

            override fun afterTextChanged(editable: Editable?) {
                update()
            }
        })
    }

    private fun update() {
        if (!input.text.toString().isEmpty() && !unit.selectedItem.toString().isEmpty()) {
            val inputValue = input.text.toString().toDouble()
            when (unit.selectedItem.toString()) {
                "kg" -> setKg(inputValue)
                "g" -> setKg(inputValue / 1000)
                "lb" -> setKg(inputValue / 2.205)
            }
        }
    }

    private fun setKg(kgIn: Double) {
        kg.text = kgIn.toString()
        g.text = (kgIn * 1000).toString()
        lb.text = (kgIn * 2.205).toString()
    }

}
