package ru.ivanalesh.simpletoolkit

import android.graphics.Color
import android.os.Bundle
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*

class CalendarActivity : AppCompatActivity() {
    private val calendar = Calendar.getInstance()
    private val dateFormat = SimpleDateFormat("MMMM, yyyy", Locale.getDefault())
    private val firstDayOfWeek = Calendar.MONDAY
    private lateinit var tableLayout: TableLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar)

        tableLayout = findViewById(R.id.calendarTable)
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
            textSize = 18f
            setTextColor(Color.BLACK)
        }
        titleRow.addView(titleView)
        tableLayout.addView(titleRow)

        // Дни недели
        val daysOfWeekRow = TableRow(this)
        val daysOfWeek = arrayOf("Пн", "Вт", "Ср", "Чт", "Пт", "Сб", "Вс")
        for (day in daysOfWeek) {
            val textView = TextView(this).apply {
                text = day
                textSize = 16f
                setTextColor(Color.BLACK)
            }
            daysOfWeekRow.addView(textView)
        }
        tableLayout.addView(daysOfWeekRow)

        // Дни месяца
        calendar.add(Calendar.DAY_OF_MONTH, -calendar.get(Calendar.DAY_OF_WEEK) + firstDayOfWeek)
        for (i in 0 until 6) { // 6 строк
            val row = TableRow(this)
            for (j in 0 until 7) { // 7 столбцов
                val dayView = TextView(this).apply {
                    text = calendar.get(Calendar.DAY_OF_MONTH).toString()
                    textSize = 14f
                    setPadding(10, 10, 10, 10)
                    setTextColor(if (isWeekend(calendar)) Color.RED else Color.BLACK)
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
