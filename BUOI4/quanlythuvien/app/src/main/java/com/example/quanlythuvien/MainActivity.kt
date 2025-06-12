package com.example.quanlythuvien

import android.os.Bundle
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.quanlythuvien.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    // Biến để truy cập các thành phần giao diện một cách an toàn
    private lateinit var binding: ActivityMainBinding

    // --- DỮ LIỆU CỦA ỨNG DỤNG ---
    // Dùng mutableListOf để có thể thêm/xóa phần tử
    private val allBooks = mutableListOf(
        Book(1, "Sách 01 - Lập trình Kotlin"),
        Book(2, "Sách 02 - Cấu trúc dữ liệu"),
        Book(3, "Sách 03 - Trí tuệ nhân tạo"),
        Book(4, "Sách 04 - Lịch sử Đảng")
    )

    private val students = mutableListOf(
        Student(101, "Nguyen Van A", mutableListOf(allBooks[0], allBooks[1])), // Mượn sách 01, 02
        Student(102, "Nguyen Thi B", mutableListOf(allBooks[0])), // Mượn sách 01
        Student(103, "Nguyen Van C", mutableListOf()) // Chưa mượn sách nào
    )
    // --- KẾT THÚC DỮ LIỆU ---

    // Biến để theo dõi sinh viên đang được chọn
    private var currentStudentIndex = 0
    // Biến cho Adapter của RecyclerView
    private lateinit var bookAdapter: BookAdapter

    /**
     * Hàm chính, được gọi khi Activity được tạo ra.
     * Đây là nơi khởi tạo giao diện và gán các sự kiện.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Thiết lập View Binding để làm việc với các thành phần giao diện
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Gọi các hàm khởi tạo ban đầu
        setupRecyclerView()
        updateUIForCurrentStudent()

        // --- GÁN SỰ KIỆN CLICK CHO CÁC NÚT ---

        // 1. Nút thay đổi sinh viên
        binding.buttonChange.setOnClickListener {
            changeStudent()
        }

        // 2. Nút thêm sinh viên mới (nút "Thêm SV")
        binding.buttonAddStudent.setOnClickListener {
            showAddStudentDialog()
        }

        // 3. Nút thêm sách mới (nút hình dấu cộng)
        binding.buttonAddBook.setOnClickListener {
            showAddBookDialog()
        }

        // 4. Nút xác nhận mượn sách (tên cũ là "Thêm", bây giờ là "Cập nhật mượn sách")
        binding.buttonConfirmBorrow.setOnClickListener {
            confirmBorrowedBooks()
        }
    }

    /**
     * Cài đặt ban đầu cho RecyclerView.
     */
    private fun setupRecyclerView() {
        bookAdapter = BookAdapter(allBooks)
        binding.recyclerViewBooks.apply {
            adapter = bookAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }

    /**
     * Cập nhật toàn bộ giao diện dựa trên sinh viên hiện tại.
     */
    private fun updateUIForCurrentStudent() {
        val currentStudent = students[currentStudentIndex]
        binding.textViewStudentName.text = currentStudent.name
        bookAdapter.setBorrowedBooks(currentStudent.borrowedBooks)
    }

    /**
     * Thay đổi sinh viên hiện tại (chuyển đến sinh viên tiếp theo).
     */
    private fun changeStudent() {
        currentStudentIndex = (currentStudentIndex + 1) % students.size
        updateUIForCurrentStudent()
        Toast.makeText(this, "Chuyển sang sinh viên: ${students[currentStudentIndex].name}", Toast.LENGTH_SHORT).show()
    }

    /**
     * Hiển thị dialog để thêm sinh viên mới.
     */
    private fun showAddStudentDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Thêm sinh viên mới")

        // Tạo layout đơn giản cho dialog
        val layout = LinearLayout(this)
        layout.orientation = LinearLayout.VERTICAL
        layout.setPadding(50, 40, 50, 10)

        val editTextId = EditText(this)
        editTextId.hint = "Nhập ID sinh viên (số)"
        editTextId.inputType = android.text.InputType.TYPE_CLASS_NUMBER
        layout.addView(editTextId)

        val editTextName = EditText(this)
        editTextName.hint = "Nhập tên sinh viên"
        layout.addView(editTextName)

        builder.setView(layout)

        builder.setPositiveButton("Thêm") { _, _ ->
            val id = editTextId.text.toString().toIntOrNull()
            val name = editTextName.text.toString().trim()

            if (id != null && name.isNotEmpty()) {
                // Kiểm tra xem ID đã tồn tại chưa
                if (students.any { it.id == id }) {
                    Toast.makeText(this, "ID sinh viên đã tồn tại!", Toast.LENGTH_SHORT).show()
                } else {
                    students.add(Student(id, name, mutableListOf()))
                    Toast.makeText(this, "Đã thêm sinh viên: $name", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin!", Toast.LENGTH_SHORT).show()
            }
        }

        builder.setNegativeButton("Hủy", null)
        builder.show()
    }

    /**
     * Hiển thị dialog để thêm sách mới.
     */
    private fun showAddBookDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Thêm sách mới")

        // Tạo layout đơn giản cho dialog
        val layout = LinearLayout(this)
        layout.orientation = LinearLayout.VERTICAL
        layout.setPadding(50, 40, 50, 10)

        val editTextId = EditText(this)
        editTextId.hint = "Nhập ID sách (số)"
        editTextId.inputType = android.text.InputType.TYPE_CLASS_NUMBER
        layout.addView(editTextId)

        val editTextTitle = EditText(this)
        editTextTitle.hint = "Nhập tên sách"
        layout.addView(editTextTitle)

        builder.setView(layout)

        builder.setPositiveButton("Thêm") { _, _ ->
            val id = editTextId.text.toString().toIntOrNull()
            val title = editTextTitle.text.toString().trim()

            if (id != null && title.isNotEmpty()) {
                // Kiểm tra xem ID đã tồn tại chưa
                if (allBooks.any { it.id == id }) {
                    Toast.makeText(this, "ID sách đã tồn tại!", Toast.LENGTH_SHORT).show()
                } else {
                    allBooks.add(Book(id, title))
                    bookAdapter.notifyDataSetChanged()
                    Toast.makeText(this, "Đã thêm sách: $title", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin!", Toast.LENGTH_SHORT).show()
            }
        }

        builder.setNegativeButton("Hủy", null)
        builder.show()
    }

    /**
     * Xác nhận và cập nhật danh sách sách mượn của sinh viên hiện tại.
     */
    private fun confirmBorrowedBooks() {
        val currentStudent = students[currentStudentIndex]
        val selectedBooks = bookAdapter.getSelectedBooks()

        currentStudent.borrowedBooks.clear()
        currentStudent.borrowedBooks.addAll(selectedBooks)

        Toast.makeText(
            this,
            "Đã cập nhật ${selectedBooks.size} sách cho ${currentStudent.name}",
            Toast.LENGTH_SHORT
        ).show()
    }
}