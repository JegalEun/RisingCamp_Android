package com.example.api_practice.src.main.search

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.api_practice.databinding.RvSearchItemBinding
import com.example.api_practice.src.main.today.now.models.Book

//class NowRecyclerViewAdapter(private val dataList: ArrayList<BookData>) : ListAdapter<Book, NowRecyclerViewAdapter.BookItemViewHolder>(diffUtil) {
class SearchBookAdapter(private val dataList: ArrayList<SearchBook>) : RecyclerView.Adapter<SearchBookAdapter.BookSearchViewHolder>() {

    lateinit var binding: RvSearchItemBinding

    inner class BookSearchViewHolder(private val binding : RvSearchItemBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(data: SearchBook) {
//            binding.tvBookTitle.text = data
//            Glide
//                .with(binding..context)
//                .load(data.img)
//                .into(binding.ivBook)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookSearchViewHolder {
        // viewholder를 생성하는 부분
        binding = RvSearchItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BookSearchViewHolder(binding)
    }

    // 데이터를 그려주게되는 함수
    override fun onBindViewHolder(holder: BookSearchViewHolder, position: Int) {
        Log.d("ddddd","dddd")
        holder.bind(dataList[position])
    }

    override fun getItemCount(): Int {
        return dataList.size
    }


//    companion object {
//        val diffUtil = object : DiffUtil.ItemCallback<Book>() {
//            override fun areItemsTheSame(oldItem: Book, newItem: Book): Boolean {
//                return oldItem == newItem
//            }
//
//            override fun areContentsTheSame(oldItem: Book, newItem: Book): Boolean {
//                return oldItem.id == newItem.id
//            }
//        }
//    }


}