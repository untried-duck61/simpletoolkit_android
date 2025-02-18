package ru.ivanalesh.simpletoolkit

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import ru.ivanalesh.simpletoolkit.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        var btnopencurconvert = findViewById<Button>(R.id.btnGoConverter)
        var btngoweather = findViewById<Button>(R.id.btnGoCurWeather)
        btnopencurconvert.setOnClickListener { onCurConvertOpenBtnClick() }
        btngoweather.setOnClickListener {goWeather()}
    }
    private fun onCurConvertOpenBtnClick(){
        var intent = Intent(this, CurrencyConverterActivity::class.java)
        this.startActivity(intent)
    }
    private fun goWeather(){
        var intent = Intent(this, CurrentWeatherActivity::class.java)
        this.startActivity(intent)
    }
}