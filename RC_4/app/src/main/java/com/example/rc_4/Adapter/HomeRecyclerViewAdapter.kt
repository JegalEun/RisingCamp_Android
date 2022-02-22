package com.example.rc_4.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rc_4.Fragment.homeData
import com.example.rc_4.databinding.RvHomeItemBinding

class HomeRecyclerViewAdapter(private val context: Context, private val dataList: ArrayList<homeData>) : RecyclerView.Adapter<HomeRecyclerViewAdapter.Holder>() {

    lateinit var binding: RvHomeItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        binding = RvHomeItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.onBind(dataList[position])
    }

    override fun getItemCount(): Int = dataList.size

    inner class Holder(val binding: RvHomeItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: homeData){
            binding.tvMallName.text = data.mall_name
            binding.tvProductName.text = data.product_name
            binding.tvPrice.text = data.price
            binding.ivProudctImg.setImageResource(data.img)
            binding.tvSale.text = data.sale
        }
    }
}