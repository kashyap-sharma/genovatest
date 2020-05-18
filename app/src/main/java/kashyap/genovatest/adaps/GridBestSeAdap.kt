package kashyap.genovatest.adaps

import android.content.Context
import android.content.Intent
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import kashyap.genovatest.DetailsP
import kashyap.genovatest.R
import kashyap.genovatest.cusfo.ProximaNovaText
import kashyap.genovatest.model.HomeModel
import java.util.*

/**
 * Created by VirtualDusk on 3/19/2018.
 */
class GridBestSeAdap internal constructor(private val mContext: Context, private val itemsList: ArrayList<HomeModel>, private val fm: FragmentManager) : RecyclerView.Adapter<GridBestSeAdap.SingleItemRowHolder>() {
    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): SingleItemRowHolder {
        val v = LayoutInflater.from(mContext).inflate(R.layout.adapter_bestseller, viewGroup, false)
        return SingleItemRowHolder(v)
    }

    override fun onBindViewHolder(holder: SingleItemRowHolder, i: Int) {
        holder.tvBrand.text = itemsList[i].brand
        holder.tvTitle.text = itemsList[i].name
        holder.tvOff.text = itemsList[i].off + "% off"
        holder.tvPrice.text = "AED " + itemsList[i].price
        holder.tvOldPrice.text = "AED " + itemsList[i].old_price
        holder.tvOldPrice.paintFlags = holder.tvOldPrice.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        holder.mConstraintLayout.setOnClickListener {
            val intent = Intent(mContext, DetailsP::class.java)
            mContext.startActivity(intent)
            help()
        }
    }

    override fun getItemCount(): Int {
        return itemsList.size
    }

    inner class SingleItemRowHolder(view: View) : RecyclerView.ViewHolder(view) {
        var tvBrand: ProximaNovaText
        var tvTitle: ProximaNovaText
        var tvOff: ProximaNovaText
        var tvPrice: ProximaNovaText
        var tvOldPrice: ProximaNovaText
        var mConstraintLayout: CardView

        init {
            mConstraintLayout = view.findViewById(R.id.con)
            tvBrand = view.findViewById(R.id.tvBrand)
            tvTitle = view.findViewById(R.id.tvTitle)
            tvOff = view.findViewById(R.id.tvOff)
            tvPrice = view.findViewById(R.id.tvPrice)
            tvOldPrice = view.findViewById(R.id.tvOldPrice)
        }
    }

    companion object {
        fun help() {}
    }

}