package kashyap.genovatest.adaps

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import kashyap.genovatest.R
import kashyap.genovatest.cusfo.ProximaNovaText
import kashyap.genovatest.model.HomeModel
import java.util.*

/**
 * Created by VirtualDusk on 3/19/2018.
 */
class SeasonalListHorizBigAdap internal constructor(private val mContext: Context, private val itemsList: ArrayList<HomeModel>, private val fm: FragmentManager) : RecyclerView.Adapter<SeasonalListHorizBigAdap.SingleItemRowHolder>() {
    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): SingleItemRowHolder {
        val v = LayoutInflater.from(mContext).inflate(R.layout.adapter_seasonal, viewGroup, false)
        return SingleItemRowHolder(v)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: SingleItemRowHolder, @SuppressLint("RecyclerView") i: Int) {

        // holder.product_decsription.setText(itemsList.get(i).name);
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getItemCount(): Int {
        return itemsList.size
    }

    inner class SingleItemRowHolder(view: View?) : RecyclerView.ViewHolder(view!!) {
        var product_decsription: ProximaNovaText? = null
    }

}