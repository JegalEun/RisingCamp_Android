package com.example.rc_4

import android.content.Context
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.rc_4.Fragment.ShoppingmallCard
import com.example.rc_4.databinding.ListviewBookMarkItemBinding

class CustomAdapter(context: Context, private val shoppingmallCardArraylist: ArrayList<ShoppingmallCard>) : BaseAdapter() {

    private val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    lateinit var binding:ListviewBookMarkItemBinding

    override fun getCount(): Int = shoppingmallCardArraylist.size

    override fun getItem(p0: Int): Any = shoppingmallCardArraylist[p0]

    override fun getItemId(p0: Int): Long = p0.toLong()

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View? {
        binding = ListviewBookMarkItemBinding.inflate(inflater,p2,false)
        binding.tvShoppingmallName.text = shoppingmallCardArraylist[p0].name
        binding.tvShoppingmallMaxSale.text = shoppingmallCardArraylist[p0].contents

        return binding.root
    }
}