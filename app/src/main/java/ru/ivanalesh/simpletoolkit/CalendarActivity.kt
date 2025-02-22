package ru.ivanalesh.simpletoolkit

import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.widget.ImageButton
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class CalendarActivity : AppCompatActivity() {
    private val calendar = Calendar.getInstance()
    private val dateFormat = SimpleDateFormat("MMMM, yyyy", Locale.getDefault())
    private val firstDayOfWeek = Calendar.MONDAY
    private lateinit var tableLayout: TableLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar)

        tableLayout = findViewById(R.id.calendarTable)
        val backbtn4 = findViewById<ImageButton>(R.id.back_btn_4)
        backbtn4.setOnClickListener { finish() }
        updateCalendar()
    }

    private fun updateCalendar() {
        tableLayout.removeAllViews()
        calendar.set(Calendar.DAY_OF_MONTH, 1)
        calendar.firstDayOfWeek = firstDayOfWeek

        // Заголовок с месяцем и годом
        val titleRow = TableRow(this)
        val titleView = TextView(this).apply {
            text = dateFormat.format(calendar.time).capitalize(Locale.getDefault())
            textSize = 20f
            setTextColor(Color.BLACK)
            gravity = Gravity.CENTER
        }
        titleRow.addView(titleView)
        tableLayout.addView(titleRow)

        // Номера недель + Дни недели
        val daysOfWeekRow = TableRow(this)
        val weekNumberHeader = TextView(this).apply {
            text = "#"
            textSize = 16f
            setTextColor(Color.BLACK)
            gravity = Gravity.CENTER
        }
        daysOfWeekRow.addView(weekNumberHeader)

        val daysOfWeek = arrayOf(R.string.mon.toString(), R.string.tue.toString(), R.string.wed.toString(), R.string.thu.toString(), R.string.fri.toString(), R.string.sat.toString(), R.string.sun.toString())
        for (day in daysOfWeek) {
            val textView = TextView(this).apply {
                text = day
                textSize = 16f
                setTextColor(Color.BLACK)
                gravity = Gravity.CENTER
            }
            daysOfWeekRow.addView(textView)
        }
        tableLayout.addView(daysOfWeekRow)

        // Дни месяца с номерами недель
        calendar.add(Calendar.DAY_OF_MONTH, -calendar.get(Calendar.DAY_OF_WEEK) + firstDayOfWeek)
        for (i in 0 until 6) { // 6 строк
            val row = TableRow(this)
            val weekNumber = TextView(this).apply {
                text = calendar.get(Calendar.WEEK_OF_YEAR).toString()
                textSize = 14f
                setTextColor(Color.BLACK)
                gravity = Gravity.CENTER
            }
            row.addView(weekNumber)

            for (j in 0 until 7) { // 7 столбцов (дни недели)
                val dayView = TextView(this).apply {
                    text = calendar.get(Calendar.DAY_OF_MONTH).toString()
                    textSize = 18f
                    setPadding(20, 20, 20, 20)
                    gravity = Gravity.CENTER
                    setTextColor(if (isWeekend(calendar)) Color.BLUE else Color.BLACK)
                    if (isToday(calendar)) {
                        setBackgroundColor(Color.LTGRAY)
                    }
                }
                row.addView(dayView)
                calendar.add(Calendar.DAY_OF_MONTH, 1)
            }
            tableLayout.addView(row)
        }
    }

    private fun isWeekend(cal: Calendar): Boolean {
        return cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY
    }

    private fun isToday(cal: Calendar): Boolean {
        val today = Calendar.getInstance()
        return cal.get(Calendar.YEAR) == today.get(Calendar.YEAR) &&
                cal.get(Calendar.MONTH) == today.get(Calendar.MONTH) &&
                cal.get(Calendar.DAY_OF_MONTH) == today.get(Calendar.DAY_OF_MONTH)
    }

}

