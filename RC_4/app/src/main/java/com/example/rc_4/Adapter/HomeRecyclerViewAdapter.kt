package com.example.rc_4.Adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rc_4.Fragment.homeData
import com.example.rc_4.ZZimData
import com.example.rc_4.ZzimActivity
import com.example.rc_4.databinding.RvHomeItemBinding

class HomeRecyclerViewAdapter(private val context: Context, private val dataList: ArrayList<homeData>) : RecyclerView.Adapter<HomeRecyclerViewAdapter.Holder>() {

    lateinit var binding: RvHomeItemBinding
    private lateinit var recyclerViewAdapter: ZzimRecyclerViewAdapter
    var zzimList = ArrayList<ZZimData>()
//    private lateinit var itemClickListener : OnItemClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        // viewholder를 생성하는 부분
        binding = RvHomeItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        // 데이터 바인딩 될대마다 호출
        holder.onBind(dataList[position])
//        holder.itemView.setOnClickListener {
//            itemClickListener.onClick(it,position)
//        }
    }

//    interface OnItemClickListener {
//        fun onClick(v: View, position:Int)
//    }
//
//    fun setItemClickListener(onItemClickListener: OnItemClickListener){
//        this.itemClickListener = onItemClickListener
//    }

    override fun getItemCount(): Int = dataList.size

    inner class Holder(val binding: RvHomeItemBinding) : RecyclerView.ViewHolder(binding.root) {
        //clickListener 설정
        fun onBind(data: homeData){
            binding.tvMallName.text = data.mall_name
            binding.tvProductName.text = data.product_name
            binding.tvPrice.text = data.price
            binding.ivProudctImg.setImageResource(data.img)
            binding.tvSale.text = data.sale

//          체크박스 눌렀을 때
            binding.cbZzim.setOnClickListener {
//                zzimList.add(ZZimData(data.mall_name, data.product_name,data.sale,data.price,data.img))
                Intent(context, ZzimActivity::class.java).apply {
                    putExtra("mall_name", data.mall_name)
                    putExtra("product_name", data.product_name)
                    putExtra("price", data.price)
                    putExtra("img", data.img)
                    putExtra("sale", data.sale)
                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                }.run { context.startActivity(this) }
//                recyclerViewAdapter.notifyDataSetChanged()
            }
        }

    }
}
