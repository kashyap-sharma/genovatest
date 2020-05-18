package kashyap.genovatest.adaps;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import kashyap.genovatest.DetailsP;
import kashyap.genovatest.R;
import kashyap.genovatest.cusfo.ProximaNovaText;
import kashyap.genovatest.model.HomeModel;

/**
 * Created by VirtualDusk on 3/19/2018.
 */

public class EmptycartAdap extends RecyclerView.Adapter<EmptycartAdap.SingleItemRowHolder> {

    private ArrayList<HomeModel> itemsList;
    private Context mContext;
    private FragmentManager fm;

    public EmptycartAdap(Context context, ArrayList<HomeModel> itemsList, FragmentManager fm) {
        this.itemsList = itemsList;
        this.mContext = context;
        this.fm = fm;
    }
    @Override
    public SingleItemRowHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.adapter_empty_cart, viewGroup, false);
        return new SingleItemRowHolder(v);

    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(final SingleItemRowHolder holder, @SuppressLint("RecyclerView") int i) {

        holder.tvTitle.setText(itemsList.get(i).name);
        holder.tvPrice.setText("AED"+itemsList.get(i).price);
        holder.mConstraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(mContext, DetailsP.class);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return itemsList.size();
    }

    public class SingleItemRowHolder extends RecyclerView.ViewHolder {

        ProximaNovaText tvPrice, tvTitle;


        ConstraintLayout mConstraintLayout;

        public SingleItemRowHolder(View view) {
            super(view);
            this.mConstraintLayout=  view.findViewById(R.id.con);
            this.tvPrice=  view.findViewById(R.id.tvPrice);
            this.tvTitle=  view.findViewById(R.id.tvTitle);

        }

    }

}
