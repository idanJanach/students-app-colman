package com.example.studentsappcolman.model

data class Student(
    var name: String,
    var id: String,
    var phone: String,
    var address: String,
    var isChecked: Boolean = false
)