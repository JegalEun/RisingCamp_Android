package com.example.rc_4.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.rc_4.CustomAdapter
import com.example.rc_4.R
import com.example.rc_4.databinding.FragmentBookmarkBinding

data class ShoppingmallCard(val name: String, val contents:String, val img: Int)
class BookMarkFragment : Fragment() {

    private lateinit var binding : FragmentBookmarkBinding
    private lateinit var customAdapter: CustomAdapter
    var shoppingmallCardList = ArrayList<ShoppingmallCard>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBookmarkBinding.inflate(inflater, container, false)

        for(x in 0..10){
            shoppingmallCardList.add(ShoppingmallCard("아텀","최대 1,000원 할인", R.drawable.shopping_preview1))
            shoppingmallCardList.add(ShoppingmallCard("원로그","최대 50,000원 할인", R.drawable.shopping_preview4))
            shoppingmallCardList.add(ShoppingmallCard("페미니크","", R.drawable.shopping_preview2))
            shoppingmallCardList.add(ShoppingmallCard("트렌디어패럴","최대 2,500원 할인", R.drawable.shopping_preview3))
        }
        customAdapter = CustomAdapter(this.requireActivity(), shoppingmallCardList)
        binding.listBookMark.adapter = customAdapter

        return binding.root


    }

}