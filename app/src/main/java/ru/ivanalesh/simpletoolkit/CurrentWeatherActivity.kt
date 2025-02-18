package ru.ivanalesh.simpletoolkit

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.Base64

class CurrentWeatherActivity : AppCompatActivity() {
    val secretKey1 = "NzM0NDI5M2UzN2VmOTAxOGQxMjkyNDBjMzE2MTU4YTk="
    @SuppressLint("NewApi")
    val secretKey2 = String(Base64.getDecoder().decode(secretKey1))
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_current_weather)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val backBtn2 = findViewById<ImageButton>(R.id.back_btn_2)
        backBtn2.setOnClickListener { goBack() }
        val city = findViewById<TextView>(R.id.textViewCity)
    }
    private fun goBack(){
        finish()
    }
}