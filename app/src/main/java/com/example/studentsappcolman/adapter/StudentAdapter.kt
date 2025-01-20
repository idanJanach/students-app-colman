package com.example.studentsappcolman.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.studentsappcolman.R
import com.example.studentsappcolman.model.Student

class StudentAdapter(
    private var students: List<Student>,
    private val onRowClick: (Int) -> Unit,
//    private val onCheckChange: (Int) -> Unit
) : RecyclerView.Adapter<StudentAdapter.StudentViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.student_row, parent, false)
        return StudentViewHolder(view)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        val student = students[position]
        holder.bind(student, position, onRowClick)
    }

    override fun getItemCount(): Int = students.size

    fun updateStudents(newStudents: List<Student>) {
        Log.d("studentlist", "updateStudents: length " + newStudents.size)
        students = newStudents
        notifyDataSetChanged()
    }

    class StudentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameTextView: TextView = itemView.findViewById(R.id.nameTextView)
        private val idTextView: TextView = itemView.findViewById(R.id.idTextView)
        private val checkBox: CheckBox = itemView.findViewById(R.id.checkBox)
        private val profileImageView: ImageView = itemView.findViewById(R.id.studentImage)

        fun bind(student: Student, index: Int, onRowClick: (Int) -> Unit) {
            nameTextView.text = student.name
            idTextView.text = student.id
            checkBox.isChecked = student.isChecked
            profileImageView.setImageResource(R.drawable.ic_student) // Static image

            itemView.setOnClickListener { onRowClick(index) }
//            checkBox.setOnCheckedChangeListener { _, _ -> onCheckChange(index) }
        }
    }
}
