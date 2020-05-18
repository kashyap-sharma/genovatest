package kashyap.genovatest.adaps;

import android.annotation.SuppressLint;
import android.content.Context;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentManager;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import kashyap.genovatest.DetailsP;
import kashyap.genovatest.R;
import kashyap.genovatest.cusfo.ProximaNovaText;
import kashyap.genovatest.cusfo.carous.Carousel;
import kashyap.genovatest.cusfo.carous.CarouselLazyLoadListener;
import kashyap.genovatest.cusfo.carous.CarouselView;
import kashyap.genovatest.model.Model;
import kashyap.genovatest.model.SamModel;
import kashyap.genovatest.model.SampleModel;

public class MultiViewTypeAdapter extends RecyclerView.Adapter {

    private ArrayList<Model> dataSet;
    Context mContext;
    private FragmentManager fm;

    public static class BannerViewHolder extends RecyclerView.ViewHolder {
        ProximaNovaText title;
        ProximaNovaText subheading;
        AppCompatImageView image;
        ConstraintLayout con;

        BannerViewHolder(View itemView) {
            super(itemView);
            this.title=  itemView.findViewById(R.id.title);
            this.subheading=  itemView.findViewById(R.id.sub_heading);
            this.image=  itemView.findViewById(R.id.image);
            this.con=  itemView.findViewById(R.id.con);


        }
    }

    public static class BannerViewHolder1 extends RecyclerView.ViewHolder {
        ProximaNovaText title;
        ProximaNovaText subheading;
        AppCompatImageView image;

        BannerViewHolder1(View itemView) {
            super(itemView);
            this.title=  itemView.findViewById(R.id.title);
            this.subheading=  itemView.findViewById(R.id.sub_heading);
            this.image=  itemView.findViewById(R.id.image);
        }
    }


    public static class WhatViewHolder extends RecyclerView.ViewHolder {
        RecyclerView mt_recycler2;
        ImageView image;
        ProximaNovaText text;
        WhatViewHolder(View itemView) {
            super(itemView);
            this.text=  itemView.findViewById(R.id.text);
            this.mt_recycler2=  itemView.findViewById(R.id.mt_recycler1);
        }
    }
    public static class SeasonalListViewHolder extends RecyclerView.ViewHolder {
        RecyclerView mt_recycler2;
        ImageView image;
        ProximaNovaText text;
        SeasonalListViewHolder(View itemView) {
            super(itemView);
            this.text=  itemView.findViewById(R.id.text);
            this.mt_recycler2=  itemView.findViewById(R.id.mt_recycler1);
        }
    }

    public static class GridSquareViewHolder extends RecyclerView.ViewHolder {
        RecyclerView mt_recycler1;
        ProximaNovaText text;

        GridSquareViewHolder(View itemView) {
            super(itemView);
            this.mt_recycler1=  itemView.findViewById(R.id.mt_recycler1);
            this.text=  itemView.findViewById(R.id.text);
        }
    }


    public static class List2itemgridViewHolder extends RecyclerView.ViewHolder {
        RecyclerView mt_recycler1;
        public ProximaNovaText text;
        List2itemgridViewHolder(View itemView) {
            super(itemView);
            this.mt_recycler1=  itemView.findViewById(R.id.mt_recycler1);
            this.text=  itemView.findViewById(R.id.text);
        }
    }
    public static class CarousalViewHolder extends RecyclerView.ViewHolder {
        CarouselView mCarouselView;
        public ProximaNovaText text;
        CarousalViewHolder(View itemView) {
            super(itemView);
            this.mCarouselView=  itemView.findViewById(R.id.carousel_view);
            this.text=  itemView.findViewById(R.id.text);
        }
    }




    public MultiViewTypeAdapter(ArrayList<Model>data, Context context, FragmentManager fm) {
        this.dataSet = data;
        this.mContext = context;
        this.fm = fm;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view;
        switch (viewType) {
            case Model.banner:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.banner, parent, false);
                return new BannerViewHolder(view);
            case Model.ListHorizSquare:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adaptergrid1recycler, parent, false);
                return new WhatViewHolder(view);
            case Model.banner1:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.banner_1, parent, false);
                return new BannerViewHolder1(view);
            case Model.GridSquare:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adaptergrid1recycler, parent, false);
                return new GridSquareViewHolder(view);
            case Model.ListHorizBig:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adaptergrid1recycler, parent, false);
                return new SeasonalListViewHolder(view);
            case Model.ListCarous:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_caroma, parent, false);
                return new CarousalViewHolder(view);
            case Model.List2itemgrid:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adaptergrid1recycler, parent, false);
                return new List2itemgridViewHolder(view);
        }
        return null;
    }

    @Override
    public int getItemViewType(int position) {

        switch (dataSet.get(position).type) {
            case 0:
                return Model.banner;
            case 1:
                return Model.ListHorizSquare;
            case 2:
                return Model.banner1;
            case 3:
                return Model.GridSquare;
            case 4:
                return Model.ListHorizBig;
            case 5:
                return Model.ListCarous;
            case 6:
                return Model.List2itemgrid;
            default:
                return -1;
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, @SuppressLint("RecyclerView") final int listPosition) {

        Model object = dataSet.get(listPosition);
        if (object != null) {
            switch (object.type) {
                case Model.banner:
                    ((BannerViewHolder) holder).title.setText(object.datas.get(0).name);
                    ((BannerViewHolder) holder).subheading.setText(object.datas.get(0).sub_heading);
                    ((BannerViewHolder) holder).con.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent=new Intent(mContext, DetailsP.class);
                            mContext.startActivity(intent);
                        }
                    });
                    break;
                case Model.banner1:
                    ((BannerViewHolder1) holder).title.setText(object.datas.get(0).name);
                    ((BannerViewHolder1) holder).subheading.setText(object.datas.get(0).sub_heading);
                    break;
                case Model.GridSquare:
                    GridNoNameAdap itemListDataAdapter1 = new GridNoNameAdap (mContext,dataSet.get(listPosition).datas,fm);
                    ((GridSquareViewHolder) holder).text.setText(object.title);
                    ((GridSquareViewHolder) holder).mt_recycler1.setHasFixedSize(true);
                    ((GridSquareViewHolder) holder).mt_recycler1.setLayoutManager(new GridLayoutManager(mContext, 2));
                    ((GridSquareViewHolder) holder).mt_recycler1.setAdapter(itemListDataAdapter1);
                    break;


                case Model.ListHorizSquare:

                    WhatYouMayAdap itemListDataAdapter2 = new WhatYouMayAdap(mContext,dataSet.get(listPosition).datas,fm);
                    ((WhatViewHolder) holder).mt_recycler2.setHasFixedSize(true);
                    ((WhatViewHolder) holder).text.setText(object.title);
                    ((WhatViewHolder) holder).mt_recycler2.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
                    ((WhatViewHolder) holder).mt_recycler2.setAdapter(itemListDataAdapter2);

                    break;
                case Model.ListHorizBig:

                    SeasonalListHorizBigAdap seasonalListHorizBigAdap = new SeasonalListHorizBigAdap(mContext,dataSet.get(listPosition).datas,fm);
                    ((SeasonalListViewHolder) holder).mt_recycler2.setHasFixedSize(true);
                    ((SeasonalListViewHolder) holder).text.setText(object.title);
                    ((SeasonalListViewHolder) holder).mt_recycler2.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
                    ((SeasonalListViewHolder) holder).mt_recycler2.setAdapter(seasonalListHorizBigAdap);

                    break;
                case Model.ListCarous:

                    SampleAdapter sampleAdapter = new SampleAdapter(mContext);
                    Carousel carousel= new Carousel((AppCompatActivity) mContext, ((CarousalViewHolder) holder).mCarouselView,sampleAdapter);
                    carousel.setOrientation(CarouselView.HORIZONTAL, false,true);
                    carousel.autoScroll(true, 5000, true);
                    carousel.scaleView(true);

                    for (int i = 0; i <dataSet.get(listPosition).datas.size() ; i++) {
                        carousel.add(new SamModel(dataSet.get(listPosition).datas.get(i).name,dataSet.get(listPosition).datas.get(i).image,dataSet.get(listPosition).datas.get(i).product_id,dataSet.get(listPosition).datas.get(i).brand,dataSet.get(listPosition).datas.get(i).price,dataSet.get(listPosition).datas.get(i).off,dataSet.get(listPosition).datas.get(i).old_price));
                    }

//                    carousel.lazyLoad(true, new CarouselLazyLoadListener() {
//                        @Override
//                        public void onLoadMore(int page, int totalItemsCount, @NotNull CarouselView view) {
//                            if (hasNextPage){
//
//                            }
//                        }
//                    });



                    break;
                case Model.List2itemgrid:

                    GridBestSeAdap gridBestSeAdap = new GridBestSeAdap(mContext,dataSet.get(listPosition).datas,fm);
                    ((List2itemgridViewHolder) holder).mt_recycler1.setHasFixedSize(true);
                    ((List2itemgridViewHolder) holder).text.setText(object.title);
                    ((List2itemgridViewHolder) holder).mt_recycler1.setLayoutManager(new GridLayoutManager(mContext, 2));
                    ((List2itemgridViewHolder) holder).mt_recycler1.setAdapter(gridBestSeAdap);

                    break;
            }
        }
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }




}
