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
    private lateinit var studentRepo: StudentRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_students_list)

        recyclerView = findViewById(R.id.recyclerView)
        addButton = findViewById(R.id.addButton)
        toolbar = findViewById(R.id.toolbar)
        studentRepo = StudentRepository.shared

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(true)

        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = StudentAdapter(
            studentRepo.getStudents(),
            onRowClick = { position -> openStudentDetails(position) },
        )
        recyclerView.adapter = adapter

        addButton.setOnClickListener {
            startActivity(Intent(this, NewStudentActivity::class.java))
        }
    }

    override fun onResume() {
        super.onResume()
        Log.d("wtfff", "onResume: " +studentRepo.getStudents().size)
        // Refresh the list when returning to this Activity
        adapter.notifyDataSetChanged()
    }

    private fun openStudentDetails(position: Int) {
        val intent = Intent(this, StudentDetailsActivity::class.java)
        intent.putExtra("STUDENT_INDEX", position)
        startActivity(intent)
    }
}
