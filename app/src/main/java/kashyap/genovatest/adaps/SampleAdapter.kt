package kashyap.genovatest.adaps

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kashyap.genovatest.R
import kashyap.genovatest.cusfo.carous.CarouselAdapter
import kashyap.genovatest.model.EmptySampleModel
import kashyap.genovatest.model.SamModel
import kashyap.genovatest.model.SampleModel
import kotlinx.android.synthetic.main.adapter_carouse.view.*
import kotlinx.android.synthetic.main.item_empty_carousel.view.*

class SampleAdapter : CarouselAdapter() {

    private val EMPTY_ITEM = 0
    private val NORMAL_ITEM = 1

    private var vh: CarouselViewHolder? = null
    var onClick: OnClick? = null

    fun setOnClickListener(onClick: OnClick?) {
        this.onClick = onClick
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItems()[position]) {
            is EmptySampleModel -> EMPTY_ITEM
            else -> NORMAL_ITEM
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarouselViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return if (viewType == NORMAL_ITEM) {
            val v = inflater.inflate(R.layout.adapter_carouse, parent, false)
            vh = MyViewHolder(v)
            vh as MyViewHolder
        } else {
            val v = inflater.inflate(R.layout.adapter_carouse, parent, false)
            vh = EmptyMyViewHolder(v)
            vh as EmptyMyViewHolder
        }
    }

    override fun onBindViewHolder(holder: CarouselViewHolder, position: Int) {
        when (holder) {
            is MyViewHolder -> {
                vh = holder
                val model = getItems()[position] as SamModel
               // (vh as MyViewHolder).title.text = model.getId().toString()
            }
            else -> {
                vh = holder
                val model = getItems()[position] as EmptySampleModel
                (vh as EmptyMyViewHolder).titleEmpty.text = model.getText()
            }
        }
    }

    inner class MyViewHolder(itemView: View) : CarouselViewHolder(itemView) {

        var title: TextView = itemView.type

//        init {
//            title.setOnClickListener { onClick?.click(getItems()[adapterPosition] as SamModel) }
//        }

    }

    inner class EmptyMyViewHolder(itemView: View) : CarouselViewHolder(itemView) {
        var titleEmpty: TextView = itemView.item_empty_text
    }

    interface OnClick {
        fun click(model: SamModel)
    }
}