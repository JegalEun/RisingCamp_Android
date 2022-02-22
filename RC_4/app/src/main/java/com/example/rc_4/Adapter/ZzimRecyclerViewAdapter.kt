package com.example.rc_4.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rc_4.Fragment.homeData
import com.example.rc_4.ZZimData
import com.example.rc_4.databinding.RvZzimPageItemBinding

class ZzimRecyclerViewAdapter(private val context: Context, private val dataList: ArrayList<ZZimData>) : RecyclerView.Adapter<ZzimRecyclerViewAdapter.Holder>() {

    lateinit var binding: RvZzimPageItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        binding = RvZzimPageItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return Holder(binding)
    }

    override fun getItemCount(): Int = dataList.size

    inner class Holder(val binding: RvZzimPageItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: ZZimData){
            binding.tvMallName.text = data.mall_name
            binding.tvProductName.text = data.product_name
            binding.tvPrice.text = data.price
            binding.ivProudctImg.setImageResource(data.img)
            binding.tvSale.text = data.sale
        }
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.onBind(dataList[position])
    }


}