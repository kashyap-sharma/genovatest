package kashyap.genovatest.adaps

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
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
class EmptycartAdap(private val mContext: Context, private val itemsList: ArrayList<HomeModel>, private val fm: FragmentManager) : RecyclerView.Adapter<EmptycartAdap.SingleItemRowHolder>() {
    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): SingleItemRowHolder {
        val v = LayoutInflater.from(mContext).inflate(R.layout.adapter_empty_cart, viewGroup, false)
        return SingleItemRowHolder(v)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: SingleItemRowHolder, @SuppressLint("RecyclerView") i: Int) {
        holder.tvTitle.text = itemsList[i].name
        holder.tvPrice.text = "AED" + itemsList[i].price
        holder.mConstraintLayout.setOnClickListener {
            val intent = Intent(mContext, DetailsP::class.java)
            mContext.startActivity(intent)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getItemCount(): Int {
        return itemsList.size
    }

    inner class SingleItemRowHolder(view: View) : RecyclerView.ViewHolder(view) {
        var tvPrice: ProximaNovaText
        var tvTitle: ProximaNovaText
        var mConstraintLayout: ConstraintLayout

        init {
            mConstraintLayout = view.findViewById(R.id.con)
            tvPrice = view.findViewById(R.id.tvPrice)
            tvTitle = view.findViewById(R.id.tvTitle)
        }
    }

}