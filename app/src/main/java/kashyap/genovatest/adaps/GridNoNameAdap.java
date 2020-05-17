package kashyap.genovatest.adaps;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import kashyap.genovatest.R;
import kashyap.genovatest.cusfo.SquareRelativeLayout;
import kashyap.genovatest.model.HomeModel;


/**
 * Created by VirtualDusk on 3/19/2018.
 */

public class GridNoNameAdap extends RecyclerView.Adapter<GridNoNameAdap.SingleItemRowHolder> {

    private ArrayList<HomeModel> itemsList;
    private Context mContext;
    private FragmentManager fm;


    GridNoNameAdap(Context context, ArrayList<HomeModel> itemsList, FragmentManager fm) {
        this.itemsList = itemsList;
        this.mContext = context;
        this.fm = fm;
    }

    @Override
    public SingleItemRowHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.adapter_square, viewGroup, false);
        return new SingleItemRowHolder(v);

    }

    @Override
    public void onBindViewHolder(SingleItemRowHolder holder, final int i) {



       // holder.text.setText(itemsList.get(i).name);

    }

    @Override
    public int getItemCount() {
        return itemsList.size();
    }

    public class SingleItemRowHolder extends RecyclerView.ViewHolder {


        private ImageView itemImage;
        private SquareRelativeLayout square;
        public SingleItemRowHolder(View view) {
            super(view);

            this.itemImage = view.findViewById(R.id.image);

        }

    }

}
