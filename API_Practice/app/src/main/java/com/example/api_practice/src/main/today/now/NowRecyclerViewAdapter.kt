package com.example.api_practice.src.main.today.now

import android.content.Context
import android.text.Layout
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.api_practice.databinding.RvHomeTabNowItemBinding

class NowRecyclerViewAdapter(private val context: Context, private val dataList : ArrayList<BookData>) :
    RecyclerView.Adapter<NowRecyclerViewAdapter.Holder>() {

    lateinit var binding : RvHomeTabNowItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        binding = RvHomeTabNowItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.onBind(dataList[position],position)

    }

    override fun getItemCount(): Int = dataList.size

    inner class Holder(val binding: RvHomeTabNowItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun onBind(data : BookData, num : Int){
            binding.tvBookName.text = data.booktitle
        }
    }


}