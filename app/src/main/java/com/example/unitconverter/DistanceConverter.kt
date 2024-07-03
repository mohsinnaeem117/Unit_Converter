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

class DistanceConverter : AppCompatActivity() {

    lateinit var input: EditText
    lateinit var unit: Spinner
    lateinit var km: TextView
    lateinit var m: TextView
    lateinit var cm: TextView
    lateinit var mm: TextView
    lateinit var microm: TextView
    lateinit var nm: TextView
    lateinit var mile: TextView
    lateinit var yard: TextView
    lateinit var foot: TextView
    lateinit var inch: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_distance_converter)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        input = findViewById(R.id.ET)
        unit = findViewById(R.id.unit)
        km = findViewById(R.id.km)
        m = findViewById(R.id.m)
        cm = findViewById(R.id.cm)
        mm = findViewById(R.id.mm)
        microm = findViewById(R.id.microm)
        nm = findViewById(R.id.nm)
        mile = findViewById(R.id.mile)
        yard = findViewById(R.id.yard)
        foot = findViewById(R.id.foot)
        inch = findViewById(R.id.inch)

        val arr = arrayOf("km", "m", "cm", "mm", "microm", "nm", "mile", "yard", "foot", "inch")
        unit.adapter = ArrayAdapter(this@DistanceConverter, R.layout.spinner_item, arr)

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
                "km" -> setKm(inputValue)
                "m" -> setKm(inputValue / 1000)
                "cm" -> setKm(inputValue / 100000)
                "mm" -> setKm(inputValue / 1000000)
                "microm" -> setKm(inputValue / 1000000000)
                "nm" -> {
                    val d = 1000000 * 1000000
                    setKm(inputValue / d)
                }
                "mile" -> setKm(inputValue * 1.609)
                "yard" -> setKm(inputValue / 1094)
                "foot" -> setKm(inputValue / 3281)
                "inch" -> setKm(inputValue / 39370)
            }
        }
    }

    private fun setKm(kmIn: Double) {
        km.text = kmIn.toString()
        m.text = (kmIn * 1000).toString()
        cm.text = (kmIn * 100000).toString()
        mm.text = (kmIn * 1000000).toString()
        microm.text = (kmIn * 1000000000).toString()
        nm.text = (kmIn * 1000000 * 1000000).toString()
        mile.text = (kmIn / 1.609).toString()
        yard.text = (kmIn * 1094).toString()
        foot.text = (kmIn * 3281).toString()
        inch.text = (kmIn * 39370).toString()
    }


}