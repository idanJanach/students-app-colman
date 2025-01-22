package com.example.studentsappcolman.view

import android.app.Activity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.studentsappcolman.R
import com.example.studentsappcolman.model.Student
import com.example.studentsappcolman.model.StudentRepository

class NewStudentActivity : AppCompatActivity() {

    private lateinit var nameEditText: EditText
    private lateinit var idEditText: EditText
    private lateinit var phoneEditText: EditText
    private lateinit var addressEditText: EditText
    private lateinit var checkedCheckBox: CheckBox
    private lateinit var saveButton: Button
    private lateinit var cancelButton: Button
    private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_student)

        // Initialize UI components
        nameEditText = findViewById(R.id.nameEditText)
        idEditText = findViewById(R.id.idEditText)
        phoneEditText = findViewById(R.id.phoneEditText)
        addressEditText = findViewById(R.id.addressEditText)
        checkedCheckBox = findViewById(R.id.checkedCheckBox)
        saveButton = findViewById(R.id.saveButton)
        cancelButton = findViewById(R.id.cancelButton)
        toolbar = findViewById(R.id.toolbar)

        // Setup Toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getString(R.string.new_student)
        toolbar.setNavigationOnClickListener {
            finish()
        }
        // Save new student
        saveButton.setOnClickListener {
            saveNewStudent()
        }

        // Cancel and return to the student list
        cancelButton.setOnClickListener {
            finish()
        }
    }

    private fun saveNewStudent() {
        val name = nameEditText.text.toString().trim()
        val id = idEditText.text.toString().trim()
        val phone = phoneEditText.text.toString().trim()
        val address = addressEditText.text.toString().trim()
        val isChecked = checkedCheckBox.isChecked

        // Validate input
        if (name.isEmpty() || id.isEmpty() || phone.isEmpty() || address.isEmpty()) {
            nameEditText.error = "Required field"
            idEditText.error = "Required field"
            phoneEditText.error = "Required field"
            addressEditText.error = "Required field"
            return
        }

        // Add new student to the repository
        val newStudent = Student(name, id, phone, address, isChecked)
        StudentRepository.addStudent(newStudent)

        // Return to the previous screen and update the list
        setResult(Activity.RESULT_OK)
        finish()
    }
}
