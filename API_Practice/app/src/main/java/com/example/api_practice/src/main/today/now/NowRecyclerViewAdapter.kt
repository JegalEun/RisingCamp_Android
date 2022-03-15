package com.example.api_practice.src.main.today.now

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.api_practice.R

class NowRecyclerViewAdapter(private val context: Context, private val dataList : ArrayList<BookData>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    private val TYPE_ITEM : Int = 1
    private val TYPE_FOOTER : Int = 2

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType) {
            TYPE_FOOTER -> {
                val view = LayoutInflater.from(context).inflate(R.layout.rv_home_tab_now_footer, parent, false)
                FooterViewHolder(view)
            }
            else -> {
                val view =  LayoutInflater.from(context).inflate(R.layout.rv_home_tab_now_item, parent, false)
                ListViewHolder(view)
            }

        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder) {
            is FooterViewHolder -> {
                holder.itemView.setOnClickListener {
                    Log.d("푸터 눌렸을 때","눌렸을때")
                }
            }
            else -> {
                val item = dataList[position]
                holder.itemView.apply {
                    val itemViewHolder: ListViewHolder = holder as ListViewHolder
                    itemViewHolder.bind(item)
                }
            }
        }
    }


    // 아이템의 타입을 반환 (position은 0 기반이므로 (전체 갯수 - 1) 일 경우에 Footer 타입 반환)
    override fun getItemViewType(position: Int): Int {
        return when (position) {
            itemCount -1 -> TYPE_FOOTER
            else -> TYPE_ITEM } }


    // 아이템의 전체 갯수 + 헤더(1) + 풋터(1) 지금은 풋터만 사용하므로 +1만 해줌.
    override fun getItemCount(): Int = dataList.size+1

    // footer에 따른 viewHolder class 추가
    class FooterViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView)


    inner class ListViewHolder(layout: View): RecyclerView.ViewHolder(layout) {
        private val txtName: TextView = itemView.findViewById(R.id.tv_book_name)

        fun bind(item: BookData) {
            txtName.text = item.booktitle
        }
    }

}