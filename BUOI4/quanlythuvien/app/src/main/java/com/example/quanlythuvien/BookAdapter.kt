package com.example.quanlythuvien

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.quanlythuvien.databinding.ItemBookBinding

class BookAdapter(
    private val allBooks: List<Book>
) : RecyclerView.Adapter<BookAdapter.BookViewHolder>() {

    private val selectedBooks = mutableSetOf<Book>()

    inner class BookViewHolder(val binding: ItemBookBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val binding = ItemBookBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BookViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val book = allBooks[position]
        holder.binding.textViewBookName.text = book.name

        // Việc gán trạng thái isChecked vẫn giữ nguyên, nó sẽ đọc từ dữ liệu nguồn.
        holder.binding.checkboxBorrowed.isChecked = selectedBooks.contains(book)

        // ===== THAY ĐỔI QUAN TRỌNG NẰM Ở ĐÂY =====
        holder.itemView.setOnClickListener {
            // Logic đúng: Chỉ thay đổi dữ liệu nguồn (data source)
            if (selectedBooks.contains(book)) {
                selectedBooks.remove(book)
            } else {
                selectedBooks.add(book)
            }

            // Quan trọng: Báo cho RecyclerView biết CHỈ item ở vị trí `position` này
            // đã thay đổi để nó tự vẽ lại một cách hiệu quả.
            // Việc này sẽ gọi lại onBindViewHolder cho CHỈ item này.
            notifyItemChanged(position)
        }
    }

    override fun getItemCount(): Int {
        return allBooks.size
    }

    fun setBorrowedBooks(borrowedBooks: List<Book>) {
        selectedBooks.clear()
        selectedBooks.addAll(borrowedBooks)
        notifyDataSetChanged()
    }

    fun getSelectedBooks(): List<Book> {
        return selectedBooks.toList()
    }
}