package ru.ivanalesh.simpletoolkit

import android.os.Bundle
import android.os.Handler
import android.widget.ImageButton
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import ru.ivanalesh.simpletweaker.R

class CurrencyConverterActivity : AppCompatActivity() {

    private lateinit var timeZoneSpinner: Spinner
    private lateinit var handler: Handler
    private lateinit var runnable: Runnable
    private var selectedTimeZone = "GMT"
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
        var digitalClockTextView = findViewById<TextView>(R.id.digitalClock)

        backBtn1.setOnClickListener { goBack() }
    }
    private fun goBack(){
        finish()
    }
}