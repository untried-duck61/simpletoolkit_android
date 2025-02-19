package ru.ivanalesh.simpletoolkit

import android.content.SharedPreferences
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SettingsActivity : AppCompatActivity() {
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var apiKeyEditText: EditText
    private lateinit var saveButton : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_settings)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val backBtn3 = findViewById<ImageButton>(R.id.back_btn_3)
        backBtn3.setOnClickListener { goBack() }
        apiKeyEditText = findViewById(R.id.apiKeyEditText)
        saveButton = findViewById(R.id.saveButton)

        // Загружаем сохранённый API-ключ
        apiKeyEditText.setText(sharedPreferences.getString("API_KEY", ""))
        sharedPreferences = getSharedPreferences("WeatherAppPrefs", Context.MODE_PRIVATE)
        saveButton.setOnClickListener {
            val apiKey = apiKeyEditText.text.toString()
            sharedPreferences.edit().putString("API_KEY", apiKey).apply()
        }
    }
    private fun goBack(){
        finish()
    }
}
