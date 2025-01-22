package com.example.studentsappcolman.model

class StudentRepository {

    private val students = mutableListOf<Student>()

    companion object{
        val shared = StudentRepository()
    }

    fun getStudents(): List<Student> = shared.students

    fun addStudent(student: Student) {
        shared.students.add(student)
    }

    fun findById(id: String): Student? {
        return shared.students.find { it.id == id }
    }

    fun deleteById(id: String) {
        shared.students.removeAll { it.id == id }
    }
}
