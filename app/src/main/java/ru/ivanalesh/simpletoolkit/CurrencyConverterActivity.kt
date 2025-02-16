package ru.ivanalesh.simpletoolkit

import android.content.Context
import android.icu.util.TimeZone
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ImageButton
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import ru.ivanalesh.simpletweaker.R

class CurrencyConverterActivity : AppCompatActivity() {

    private var timeZoneSpinner: Spinner = findViewById(R.id.timeZoneSpinner)
    private lateinit var handler: Handler
    private lateinit var runnable: Runnable
    private var selectedTimeZone = "GMT"
    private var digitalClockTextView = findViewById<TextView>(R.id.digitalClock)
    private val timeZoneKey = "selected_time_zone"
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_currency_converter)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.currency_converter)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        var backBtn1 = findViewById<ImageButton>(R.id.back_btn_1)
        //var digitalClockTextView = findViewById<TextView>(R.id.digitalClock)

        backBtn1.setOnClickListener { goBack() }
        val timeZones = TimeZone.getAvailableIDs().toList()
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, timeZones)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        val sharedPreferences = getSharedPreferences("AppPreferences", Context.MODE_PRIVATE)
        selectedTimeZone = sharedPreferences.getString(timeZoneKey, "GMT") ?: "GMT"

        val timeZoneIndex = timeZones.indexOf(selectedTimeZone)
        if (timeZoneIndex != -1){
            timeZoneSpinner.setSelection(timeZoneIndex)
        }
        timeZoneSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                selectedTimeZone = parent.getItemAtPosition(position) as String
                sharedPreferences.edit().putString(timeZoneKey, selectedTimeZone).apply()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {

            }
        }
        handler = Handler()
        runnable = object : Runnable {
            override fun run() {
                updateTime()
                handler.postDelayed(this, 1000)
            }
        }
        handler.post(runnable)
    }
    private fun updateTime() {

    }
    private fun goBack(){
        finish()
    }
}