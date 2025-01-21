package com.example.studentsappcolman.model

import android.util.Log

object StudentRepository {
    private val students = mutableListOf<Student>(Student("1","1","1","1",true))

    fun getStudents(): List<Student> = students

    fun addStudent(student: Student) {
        students.add(student)
    }

    fun findById(id: String): Student? {
        return students.find { it.id == id }
    }

    fun deleteById(id: String) {
        students.removeAll { it.id == id }
    }

    fun updateStudent(index: Int, updatedStudent: Student) {
        students[index] = updatedStudent
    }

    fun deleteStudent(index: Int) {
        students.removeAt(index)
    }
}
