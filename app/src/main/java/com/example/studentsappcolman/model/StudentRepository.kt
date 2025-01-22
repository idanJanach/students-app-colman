package com.example.studentsappcolman.model

object StudentRepository {
    private val students = mutableListOf<Student>()

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
}
