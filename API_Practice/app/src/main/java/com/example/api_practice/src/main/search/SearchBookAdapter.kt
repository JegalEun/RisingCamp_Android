package com.example.api_practice.src.main.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.api_practice.databinding.RvSearchItemBinding
import com.example.api_practice.src.main.today.now.models.Book

class SearchBookAdapter : ListAdapter<Book, SearchBookAdapter.BookItemViewHolder>(diffUtil) {

    inner class BookItemViewHolder(private val binding : RvSearchItemBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(bookModel : Book) {
            binding.tvBookTitle.text = bookModel.title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookItemViewHolder {
        return BookItemViewHolder(RvSearchItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    // 데이터를 그려주게되는 함수
    override fun onBindViewHolder(holder: BookItemViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<Book>() {
            override fun areItemsTheSame(oldItem: Book, newItem: Book): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Book, newItem: Book): Boolean {
                return oldItem.id == newItem.id
            }


        }
    }
}