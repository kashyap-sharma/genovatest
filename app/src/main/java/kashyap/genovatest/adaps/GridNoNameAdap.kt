package kashyap.genovatest.adaps

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import kashyap.genovatest.DetailsP
import kashyap.genovatest.R
import kashyap.genovatest.cusfo.ProximaNovaText
import kashyap.genovatest.cusfo.SquareRelativeLayout
import kashyap.genovatest.model.HomeModel
import java.util.*

/**
 * Created by VirtualDusk on 3/19/2018.
 */
class GridNoNameAdap internal constructor(private val mContext: Context, private val itemsList: ArrayList<HomeModel>, private val fm: FragmentManager) : RecyclerView.Adapter<GridNoNameAdap.SingleItemRowHolder>() {
    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): SingleItemRowHolder {
        val v = LayoutInflater.from(mContext).inflate(R.layout.adapter_square, viewGroup, false)
        return SingleItemRowHolder(v)
    }

    override fun onBindViewHolder(holder: SingleItemRowHolder, i: Int) {
        holder.text.text = itemsList[i].category_name
        holder.square.setOnClickListener {
            val intent = Intent(mContext, DetailsP::class.java)
            mContext.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return itemsList.size
    }

    inner class SingleItemRowHolder(view: View) : RecyclerView.ViewHolder(view) {
        var text: ProximaNovaText

        val square: SquareRelativeLayout

        init {
            text = view.findViewById(R.id.text)
            square = view.findViewById(R.id.con)
        }
    }

}