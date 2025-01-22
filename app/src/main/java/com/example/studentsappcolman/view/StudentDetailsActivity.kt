package com.example.studentsappcolman.view

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.studentsappcolman.R
import com.example.studentsappcolman.model.Student
import com.example.studentsappcolman.model.StudentRepository


class StudentDetailsActivity : AppCompatActivity() {

    private lateinit var nameTextView: TextView
    private lateinit var idTextView: TextView
    private lateinit var phoneTextView: TextView
    private lateinit var addressTextView: TextView
    private lateinit var checkedTextView: TextView
    private lateinit var editButton: Button
    private lateinit var toolbar: Toolbar

    private var studentIndex: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_details)

        nameTextView = findViewById(R.id.nameTextView)
        idTextView = findViewById(R.id.idTextView)
        phoneTextView = findViewById(R.id.phoneTextView)
        addressTextView = findViewById(R.id.addressTextView)
        checkedTextView = findViewById(R.id.checkedTextView)
        editButton = findViewById(R.id.editButton)
        toolbar = findViewById(R.id.toolbar)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getString(R.string.student_details)
        toolbar.setNavigationOnClickListener {
            finish()
        }

        studentIndex = intent.getIntExtra("STUDENT_INDEX", -1)

        if (studentIndex != -1) {
            displayStudentDetails()
        }

        editButton.setOnClickListener {
            val student: Student = StudentRepository.getStudents()[studentIndex]
            val intent = Intent(this, EditStudentActivity::class.java)
            intent.putExtra("STUDENT_ID", student.id)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        // Refresh details in case the student has been edited
        if (studentIndex != -1) {
            displayStudentDetails()
        }
    }

    private fun displayStudentDetails() {
        val student: Student = StudentRepository.getStudents()[studentIndex]
        nameTextView.text = student.name
        idTextView.text = student.id
        phoneTextView.text = student.phone
        addressTextView.text = student.address
        checkedTextView.text = if (student.isChecked) "Checked" else "Not Checked"
    }
}