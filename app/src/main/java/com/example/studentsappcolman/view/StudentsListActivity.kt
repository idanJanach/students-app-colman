package com.example.studentsappcolman.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.studentsappcolman.R
import com.example.studentsappcolman.adapter.StudentAdapter
import com.example.studentsappcolman.model.StudentRepository
import com.google.android.material.floatingactionbutton.FloatingActionButton

class StudentsListActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: StudentAdapter
    private lateinit var addButton: FloatingActionButton
    private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_students_list)

        recyclerView = findViewById(R.id.recyclerView)
        addButton = findViewById(R.id.addButton)
        toolbar = findViewById(R.id.toolbar)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(true)

        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = StudentAdapter(
            StudentRepository.getStudents(),
            onRowClick = { position -> openStudentDetails(position) },
        )
        recyclerView.adapter = adapter

        addButton.setOnClickListener {
            startActivity(Intent(this, NewStudentActivity::class.java))
        }
    }

    override fun onResume() {
        super.onResume()
        Log.d("wtfff", "onResume: " +StudentRepository.getStudents().size)
        // Refresh the list when returning to this Activity
        adapter.notifyDataSetChanged()
    }

    private fun openStudentDetails(position: Int) {
        val intent = Intent(this, StudentDetailsActivity::class.java)
        intent.putExtra("STUDENT_INDEX", position)
        startActivity(intent)
    }

    private fun toggleStudentCheckStatus(position: Int) {
        val student = StudentRepository.getStudents()[position]
        student.isChecked = !student.isChecked
        adapter.notifyItemChanged(position)
    }
}
