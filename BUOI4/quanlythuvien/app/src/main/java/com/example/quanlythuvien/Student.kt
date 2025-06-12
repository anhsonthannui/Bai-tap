package com.example.quanlythuvien

// id: Mã định danh
// name: Tên sinh viên
// borrowedBooks: Danh sách các quyển sách mà sinh viên này đã mượn
data class Student(
    val id: Int,
    val name: String,
    val borrowedBooks: MutableList<Book>
)