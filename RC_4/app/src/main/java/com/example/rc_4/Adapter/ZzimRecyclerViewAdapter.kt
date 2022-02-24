package com.example.rc_4.Adapter

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rc_4.Fragment.HomeFragment
import com.example.rc_4.Fragment.homeData
import com.example.rc_4.HomeActivity
import com.example.rc_4.ZZimData
import com.example.rc_4.ZzimActivity
import com.example.rc_4.databinding.RvZzimPageItemBinding

class ZzimRecyclerViewAdapter(private val context: Context, private val dataList: ArrayList<ZZimData>) : RecyclerView.Adapter<ZzimRecyclerViewAdapter.Holder>() {

    lateinit var binding: RvZzimPageItemBinding
    lateinit var homeFragment : HomeFragment

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        binding = RvZzimPageItemBinding.inflate(LayoutInflater.from(context), parent, false)
        homeFragment = HomeFragment()
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

//            // 삭제 버튼 누르면 리사이클러뷰에서 삭제
            binding.btnZzimDelete.setOnClickListener {

                // 삭제 버튼 누르면 리사이클러뷰에서 삭제
                Intent(context, ZzimActivity::class.java).apply {
                    putExtra("d_mall_name", data.mall_name)
                    putExtra("d_product_name", data.product_name)
                    putExtra("d_price", data.price)
                    putExtra("d_img", data.img)
                    putExtra("d_sale", data.sale)
                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                }
//                var bundle = Bundle()
//                bundle.putString("mall_name", data.mall_name)
//                bundle.putString("product_name", data.product_name)
//                bundle.putString("price", data.price)
//                bundle.putInt("img", data.img)
//                bundle.putString("sale", data.sale)
//                homeFragment.arguments=bundle
            }


        }
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.onBind(dataList[position])
//        holder.binding.btnZzimDelete.setOnClickListener {
//            val data = dataList[position]
//        }
    }


}