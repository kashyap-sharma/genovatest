package kashyap.genovatest.adaps;

import android.annotation.SuppressLint;
import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;

import kashyap.genovatest.R;
import kashyap.genovatest.cusfo.ProximaNovaText;
import kashyap.genovatest.model.HomeModel;

/**
 * Created by VirtualDusk on 3/19/2018.
 */

public class WhatYouMayAdap extends RecyclerView.Adapter<WhatYouMayAdap.SingleItemRowHolder> {

    private ArrayList<HomeModel> itemsList;
    private Context mContext;
    private FragmentManager fm;

    WhatYouMayAdap(Context context, ArrayList<HomeModel> itemsList, FragmentManager fm) {
        this.itemsList = itemsList;
        this.mContext = context;
        this.fm = fm;
    }
    @Override
    public SingleItemRowHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.adapter_what_you, viewGroup, false);
        return new SingleItemRowHolder(v);

    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(final SingleItemRowHolder holder, @SuppressLint("RecyclerView") int i) {

        //holder.product_decsription.setText(itemsList.get(i).name);
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

        ProximaNovaText product_decsription;


        public SingleItemRowHolder(View view) {
            super(view);
          //  this.product_decsription=  view.findViewById(R.id.product_desc);

        }

    }

}
