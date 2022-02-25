import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.rc_4.R
import com.example.rc_4.ZZimData
import com.example.rc_4.databinding.RvZzimPageItemBinding
import java.util.*
import kotlin.collections.ArrayList

 class DragAndDropAdapter(private val list: ArrayList<ZZimData>) :
    RecyclerView.Adapter<DragAndDropAdapter.ViewHolder>(),
    MyTouchHelperCallback.OnItemMoveListener {

    private lateinit var dragListener: OnStartDragListener
//    private lateinit var binding : ZzimActivity
    lateinit var binding: RvZzimPageItemBinding

    inner class ViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.rv_zzim_page_item, parent, false)
    ) {
        val mall_name: TextView = itemView.findViewById(R.id.tv_mall_name)
        val product_name: TextView = itemView.findViewById(R.id.tv_product_name)
        val sale: TextView = itemView.findViewById(R.id.tv_sale)
        val price: TextView = itemView.findViewById(R.id.tv_price)
        val ivMenu: ImageView = itemView.findViewById(R.id.iv_proudct_img)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = RvZzimPageItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(parent)
    }

//    inner class ViewHolder(val binding: RvZzimPageItemBinding) : RecyclerView.ViewHolder(binding.root)
//    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        list[position].let {
            with(holder) {
                mall_name.text = it.mall_name
                product_name.text = it.product_name
                price.text = it.price
                sale.text=it.sale
                ivMenu.setImageResource(it.img)
                ivMenu.setOnTouchListener { view, event ->
                    if (event.action == MotionEvent.ACTION_DOWN) {
                        dragListener.onStartDrag(this)
                    }
                    return@setOnTouchListener false
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    interface OnStartDragListener {
        fun onStartDrag(viewHolder: RecyclerView.ViewHolder)
    }

    fun startDrag(listener: OnStartDragListener) {
        this.dragListener = listener
    }

    fun onItemMoved(fromPosition: Int, toPosition: Int) {
        Collections.swap(list, fromPosition, toPosition)
        notifyItemMoved(fromPosition, toPosition)
    }

    override fun onItemMove(fromPosition: Int, toPosition: Int) {
        Collections.swap(list, fromPosition, toPosition)
        notifyItemMoved(fromPosition, toPosition)
    }

    override fun onItemSwiped(position: Int) {
        list.removeAt(position)
        notifyItemRemoved(position)
    }

}