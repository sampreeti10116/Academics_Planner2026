package com.example.studentplanner2026

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.Manifest
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private lateinit var dbHelper: DatabaseHelper

    private lateinit var tvSubjectCount: TextView
    private lateinit var tvTaskCount: TextView
    private lateinit var tvNextTask: TextView
    private lateinit var tvTodaySchedule: TextView
    private lateinit var tvUpcomingExam: TextView
    private lateinit var tvProgressText: TextView

    private lateinit var btnSubjects: MaterialButton
    private lateinit var btnTasks: MaterialButton
    private lateinit var btnExams: MaterialButton
    private lateinit var btnTimetable: MaterialButton
    private val notificationPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) {isGranted -> }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        NotificationHelper.createNotificationChannel(this)

        dbHelper = DatabaseHelper(this)

        initializeViews()
        setupNavigation()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU &&
            checkSelfPermission(Manifest.permission.POST_NOTIFICATIONS)
            != PackageManager.PERMISSION_GRANTED
        ) {
            notificationPermissionLauncher.launch(
                Manifest.permission.POST_NOTIFICATIONS
            )
        }


    }

    private fun initializeViews() {
        tvSubjectCount = findViewById(R.id.tvSubjectCount)
        tvTaskCount = findViewById(R.id.tvTaskCount)
        tvNextTask = findViewById(R.id.tvNextTask)
        tvTodaySchedule = findViewById(R.id.tvTodaySchedule)
        tvUpcomingExam = findViewById(R.id.tvUpcomingExam)
        tvProgressText = findViewById(R.id.tvProgressText)

        btnSubjects = findViewById(R.id.btnSubjects)
        btnTasks = findViewById(R.id.btnTasks)
        btnExams = findViewById(R.id.btnExams)
        btnTimetable = findViewById(R.id.btnTimetable)
    }

    private fun setupNavigation() {
        btnSubjects.setOnClickListener {
            startActivity(Intent(this, SubjectsActivity::class.java))
        }

        btnTasks.setOnClickListener {
            startActivity(Intent(this, TasksActivity::class.java))
        }

        btnExams.setOnClickListener {
            startActivity(Intent(this, ExamActivity::class.java))
        }

        btnTimetable.setOnClickListener {
            startActivity(Intent(this, TimetableActivity::class.java))
        }
    }

    override fun onResume() {
        super.onResume()
        loadDashboard()
    }

    private fun loadDashboard() {
        loadSubjectCount()
        loadTaskSummary()
        loadNextTask()
        loadTodaySchedule()
        loadUpcomingExam()
    }

    private fun loadSubjectCount() {
        val db = dbHelper.readableDatabase

        val cursor = db.rawQuery(
            "SELECT COUNT(*) FROM subjects",
            null
        )

        if (cursor.moveToFirst()) {
            tvSubjectCount.text = cursor.getInt(0).toString()
        }

        cursor.close()
    }

    private fun loadTaskSummary() {
        val db = dbHelper.readableDatabase

        val cursor = db.rawQuery(
            """
        SELECT
            COUNT(*) AS totalTasks,
            SUM(CASE WHEN isCompleted = 0 THEN 1 ELSE 0 END) AS pendingTasks,
            SUM(CASE WHEN isCompleted = 1 THEN 1 ELSE 0 END) AS completedTasks
        FROM tasks
        """.trimIndent(),
            null
        )

        if (cursor.moveToFirst()) {
            val totalTasks = cursor.getInt(cursor.getColumnIndexOrThrow("totalTasks"))
            val pendingTasks = cursor.getInt(cursor.getColumnIndexOrThrow("pendingTasks"))
            val completedTasks = cursor.getInt(cursor.getColumnIndexOrThrow("completedTasks"))

            // Display pending tasks, not total tasks
            tvTaskCount.text = pendingTasks.toString()

            val progress = if (totalTasks > 0) {
                (completedTasks * 100) / totalTasks
            } else {
                0
            }

            tvProgressText.text = "$progress% completed"
        }

        cursor.close()
    }

    private fun loadNextTask() {
        val db = dbHelper.readableDatabase

        val cursor = db.rawQuery(
            """
    SELECT title, dueDate, priority, dueTime
    FROM tasks
    WHERE isCompleted = 0
    ORDER BY id ASC
    LIMIT 1
    """.trimIndent(),
            null
        )

        if (cursor.moveToFirst()) {
            val title = cursor.getString(0)
            val dueDate = cursor.getString(1)
            val priority = cursor.getString(2)

            val dueTime = cursor.getString(3) ?: ""

            tvNextTask.text =
                if (dueTime.isNotEmpty()) {
                    "$title\nDue: $dueDate at $dueTime\nPriority: $priority"
                } else {
                    "$title\nDue: $dueDate\nPriority: $priority"
                }
        } else {
            tvNextTask.text = "No pending tasks"
        }

        cursor.close()
    }

    private fun loadTodaySchedule() {
        val db = dbHelper.readableDatabase

        val calendar = Calendar.getInstance()
        val dayFormat = SimpleDateFormat("EEEE", Locale.getDefault())
        val today = dayFormat.format(calendar.time)

        val cursor = db.rawQuery(
            """
            SELECT subject, startTime, endTime, venue
            FROM timetable
            WHERE day = ?
            ORDER BY startTime ASC
            """.trimIndent(),
            arrayOf(today)
        )

        val schedule = StringBuilder()

        while (cursor.moveToNext()) {
            val subject = cursor.getString(0)
            val startTime = cursor.getString(1)
            val endTime = cursor.getString(2)
            val venue = cursor.getString(3)

            schedule.append("$startTime - $endTime\n")
            schedule.append(subject)

            if (!venue.isNullOrEmpty()) {
                schedule.append(" ($venue)")
            }

            schedule.append("\n\n")
        }

        tvTodaySchedule.text = if (schedule.isNotEmpty()) {
            schedule.toString().trim()
        } else {
            "No classes scheduled today"
        }

        cursor.close()
    }

    private fun loadUpcomingExam() {
        val db = dbHelper.readableDatabase

        val cursor = db.rawQuery(
            """
        SELECT id, subject, examDate, examTime, venue
        FROM exams
        """.trimIndent(),
            null
        )

        val dateTimeFormat = SimpleDateFormat(
            "dd/MM/yyyy HH:mm",
            Locale.getDefault()
        )

        dateTimeFormat.isLenient = false

        val upcomingExams = mutableListOf<Pair<Exam, Date>>()
        val currentDate = Date()

        while (cursor.moveToNext()) {

            val exam = Exam(
                id = cursor.getInt(0),
                subject = cursor.getString(1),
                examDate = cursor.getString(2),
                examTime = cursor.getString(3),
                venue = cursor.getString(4)
            )

            val examDateTime = parseExamDateTime(
                exam.examDate,
                exam.examTime,
                dateTimeFormat
            )

            if (examDateTime != null && examDateTime.after(currentDate)) {
                upcomingExams.add(Pair(exam, examDateTime))
            }
        }

        cursor.close()
        db.close()

        val nearestExam = upcomingExams.minByOrNull { it.second }

        if (nearestExam != null) {

            val exam = nearestExam.first
            val examDateTime = nearestExam.second

            val countdown = calculateCountdown(examDateTime)

            tvUpcomingExam.text =
                "${exam.subject}\n" +
                        "${exam.examDate} at ${exam.examTime}\n" +
                        "${exam.venue}\n\n" +
                        countdown

        } else {
            tvUpcomingExam.text = "No upcoming exams"
        }
    }

    private fun calculateCountdown(examDateTime: Date): String {

        val currentTime = System.currentTimeMillis()
        val examTime = examDateTime.time

        val difference = examTime - currentTime

        if (difference <= 0) {
            return "Exam is happening now"
        }

        val totalMinutes = difference / (1000 * 60)

        val days = totalMinutes / (60 * 24)
        val hours = (totalMinutes % (60 * 24)) / 60
        val minutes = totalMinutes % 60

        return when {
            days > 0 -> {
                if (hours > 0) {
                    "⏳ $days Days, $hours Hours Remaining"
                } else {
                    "⏳ $days Days Remaining"
                }
            }

            hours > 0 -> {
                "⏳ $hours Hours, $minutes Minutes Remaining"
            }

            else -> {
                "⏳ $minutes Minutes Remaining"
            }
        }
    }

    private fun parseExamDateTime(
        date: String,
        time: String,
        format: SimpleDateFormat
    ): Date? {
        return try {
            format.parse("$date $time")
        } catch (e: Exception) {
            null
        }
    }
}