package ru.ivanalesh.simpletoolkit

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SettingsActivity : AppCompatActivity() {
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
        val apiKeyEditText: EditText = findViewById(R.id.edt_api_key)
        val saveButton : Button = findViewById(R.id.btn_save_api_key)

        // Загружаем сохранённый API-ключ
        val sharedPreferences: SharedPreferences = getSharedPreferences("WeatherAppPrefs", Context.MODE_PRIVATE)
        apiKeyEditText.setText(sharedPreferences.getString("API_KEY", ""))
        //sharedPreferences = getSharedPreferences("WeatherAppPrefs", Context.MODE_PRIVATE)
        saveButton.setOnClickListener {
            val apiKey = apiKeyEditText.text.toString()
            sharedPreferences.edit().putString("API_KEY", apiKey).apply()
            Toast.makeText(this, R.string.api_key_succ_save, Toast.LENGTH_SHORT).show()
        }
    }
    private fun goBack(){
        finish()
    }
}
