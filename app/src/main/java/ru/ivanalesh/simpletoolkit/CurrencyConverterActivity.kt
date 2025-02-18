package ru.ivanalesh.simpletoolkit

import android.content.Context
//import android.icu.util.Calendar
//import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ImageButton
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
//import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import ru.ivanalesh.simpletweaker.R
import java.util.*
import java.text.SimpleDateFormat
import java.time.LocalDate

class CurrencyConverterActivity : AppCompatActivity() {

    private lateinit var timeZoneSpinner : Spinner
    private lateinit var handler: Handler
    private lateinit var runnable1: Runnable
    private var selectedTimeZone = "GMT"
    private lateinit var digitalClockTextView : TextView
    private val timeZoneKey = "selected_time_zone"
    //@RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_currency_converter)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.currency_converter)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val backBtn1 = findViewById<ImageButton>(R.id.back_btn_1)
        //var digitalClockTextView = findViewById<TextView>(R.id.digitalClock)
        digitalClockTextView = findViewById(R.id.digitalClock)
        //timeZoneSpinner = findViewById(R.id.timeZoneSpinner)
        backBtn1.setOnClickListener { goBack() }
        val timeZones = TimeZone.getAvailableIDs().toList()
        //val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, getFormattedTimeZones())
        //adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        //val sharedPreferences = getSharedPreferences("AppPreferences", Context.MODE_PRIVATE)
        selectedTimeZone = TimeZone.getDefault().toString()//sharedPreferences.getString(timeZoneKey, "GMT") ?: "GMT"
        //timeZoneSpinner.adapter = adapter

        //val timeZoneIndex = timeZones.indexOf(selectedTimeZone)
        //if (timeZoneIndex != -1){
         //   timeZoneSpinner.setSelection(timeZoneIndex)
        //}
        //timeZoneSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
        //    override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
        //        selectedTimeZone = parent.getItemAtPosition(position) as String
         //       sharedPreferences.edit().putString(timeZoneKey, selectedTimeZone).apply()
         //   }

          //  override fun onNothingSelected(parent: AdapterView<*>) {

         //   }
        //}
        handler = Handler(Looper.getMainLooper())
        runnable1 = object : Runnable {
            override fun run() {
                updateTime()
                digitalClockTextView.postDelayed(this, 1000)
            }
        }
        digitalClockTextView.post(runnable1)
    }
    //@RequiresApi(Build.VERSION_CODES.N)
    private fun updateTime() {
        /*val timeZone = TimeZone.getTimeZone(selectedTimeZone)
        //val timeZone2 = java.util.TimeZone.getTimeZone(selectedTimeZone)
        val calendar = Calendar.getInstance()
        calendar.timeZone = timeZone
        val timeFormat = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
        timeFormat.timeZone = timeZone*/
        val time = Calendar.getInstance().time
        val formatter = SimpleDateFormat("HH:mm:ss")
        val current = formatter.format(time)
        digitalClockTextView.text = current
    }
    private fun goBack(){
        finish()
    }
    /*private fun getFormattedTimeZones(): List<String> {
        return TimeZone.getAvailableIDs()
            .map { id ->
                val timeZone = TimeZone.getTimeZone(id)
                val offsetHours = timeZone.rawOffset / 1000 / 60 / 60
                val offsetFormatted = if (offsetHours >= 0) "+$offsetHours" else "$offsetHours"
                "$id (GMT$offsetFormatted)" to offsetHours
            }
            .sortedBy { it.second } // Сортировка по смещению
            .map { it.first }
    }*/
    override fun onDestroy() {
        super.onDestroy()
        // Остановка обновления времени при закрытии приложения
        handler.removeCallbacks(runnable1)
    }
}
