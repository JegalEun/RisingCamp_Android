package com.example.api_practice.src.main.search

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.api_practice.databinding.RvHomeTabNowItemBinding
import com.example.api_practice.databinding.RvSearchItemBinding
import com.example.api_practice.src.main.today.now.BookData
import com.example.api_practice.src.main.today.now.models.Book

//class NowRecyclerViewAdapter(private val dataList: ArrayList<BookData>) : ListAdapter<Book, NowRecyclerViewAdapter.BookItemViewHolder>(diffUtil) {
class NowRecyclerViewAdapter(private val dataList: ArrayList<BookData>) : RecyclerView.Adapter<NowRecyclerViewAdapter.BookItemViewHolder>() {

    lateinit var binding: RvHomeTabNowItemBinding

    inner class BookItemViewHolder(private val binding : RvHomeTabNowItemBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(data: BookData) {
            binding.tvBookName.text = data.booktitle
            Glide
                .with(binding.ivBook.context)
                .load(data.img)
                .into(binding.ivBook)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookItemViewHolder {
        // viewholder를 생성하는 부분
        binding = RvHomeTabNowItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BookItemViewHolder(binding)
    }

    // 데이터를 그려주게되는 함수
    override fun onBindViewHolder(holder: BookItemViewHolder, position: Int) {
        Log.d("ddddd","dddd")
        holder.bind(dataList[position])
    }

    override fun getItemCount(): Int {
        return dataList.size
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