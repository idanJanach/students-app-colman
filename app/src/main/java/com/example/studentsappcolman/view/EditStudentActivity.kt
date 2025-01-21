package com.example.studentsappcolman.view

import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.studentsappcolman.R
import com.example.studentsappcolman.model.Student
import com.example.studentsappcolman.model.StudentRepository

class EditStudentActivity : AppCompatActivity() {

    private lateinit var nameEditText: EditText
    private lateinit var idEditText: EditText
    private lateinit var phoneEditText: EditText
    private lateinit var addressEditText: EditText
    private lateinit var checkedCheckBox: CheckBox
    private lateinit var saveButton: Button
    private lateinit var deleteButton: Button
    private lateinit var cancelButton: Button
    private lateinit var toolbar: Toolbar

    private var studentId: String? = null
    private var student: Student? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_student)

        toolbar = findViewById(R.id.toolbar)
        nameEditText = findViewById(R.id.nameEditText)
        idEditText = findViewById(R.id.idEditText)
        phoneEditText = findViewById(R.id.phoneEditText)
        addressEditText = findViewById(R.id.addressEditText)
        checkedCheckBox = findViewById(R.id.checkedCheckBox)
        saveButton = findViewById(R.id.saveButton)
        deleteButton = findViewById(R.id.deleteButton)
        cancelButton = findViewById(R.id.cancelButton)

        // Setup Toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getString(R.string.edit_student)
        toolbar.setNavigationOnClickListener {
            finish()
        }

        studentId = intent.getStringExtra("STUDENT_ID")
        if (studentId == null) {
            Toast.makeText(this, "Invalid student data", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        student = StudentRepository.findById(studentId!!)
        if (student == null) {
            Toast.makeText(this, "Student not found", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        loadStudentDetails(student!!)

        saveButton.setOnClickListener { saveStudentDetails() }
        deleteButton.setOnClickListener { deleteStudent() }
        cancelButton.setOnClickListener { finish() }
    }

    private fun loadStudentDetails(student: Student) {
        nameEditText.setText(student.name)
        idEditText.setText(student.id)
        phoneEditText.setText(student.phone)
        addressEditText.setText(student.address)
        checkedCheckBox.isChecked = student.isChecked
    }

    private fun saveStudentDetails() {
        val updatedName = nameEditText.text.toString().trim()
        val updatedId = idEditText.text.toString().trim()
        val updatedPhone = phoneEditText.text.toString().trim()
        val updatedAddress = addressEditText.text.toString().trim()
        val updatedIsChecked = checkedCheckBox.isChecked

        if (updatedName.isEmpty() || updatedId.isEmpty()) {
            Toast.makeText(this, "Name and ID cannot be empty", Toast.LENGTH_SHORT).show()
            return
        }

        student?.apply {
            name = updatedName
            id = updatedId
            phone = updatedPhone
            address = updatedAddress
            isChecked = updatedIsChecked
        }

        Toast.makeText(this, "Student updated successfully", Toast.LENGTH_SHORT).show()
        finish()
    }

    private fun deleteStudent() {
        StudentRepository.deleteById(studentId!!)
        Toast.makeText(this, "Student deleted successfully", Toast.LENGTH_SHORT).show()
        finish()
    }
}
