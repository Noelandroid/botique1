package com.theresa.boutique.adapter;



import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.theresa.boutique.R;
import com.theresa.boutique.model.ProductData2;

import java.text.DecimalFormat;
import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProductListAdapter2 extends RecyclerView.Adapter<ProductListAdapter2.ViewHolder> {

    ArrayList<ProductData2> oList = new ArrayList<>();
    ClickListener mListener;
    Context mContext;



    public interface ClickListener{
        void onClickIncrement(int position);

        void onClickDecrement(int position);
    }

    public ProductListAdapter2(Context context, ArrayList<ProductData2> plist){
        mContext = context;
        mListener = (ClickListener) context;
        oList = plist;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (i == 0)
            return new TitleView(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.product_item_title, viewGroup, false));
        else {
            return new ItemListView(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.product_item2, viewGroup, false));
        }


    }






    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {





        if (i != 0) {
            int a = 0;

            final DecimalFormat formatter = new DecimalFormat("00");
            final String aFormatted = formatter.format(a);
            final ItemListView itemListView = (ItemListView) viewHolder;





            itemListView.tvProductName.setText(oList.get(i-1).getItemName());
            itemListView.tvCount.setText(formatter.format(oList.get(i-1).getItemCount()));
            //itemListView.tvnewCount.setText(formatter.format(oList.get(i-1).getItemCount()));


            itemListView.tvnewCount.setText(formatter.format(oList.get(i-1).getNoOfItem1()));
            itemListView.tvnewCount.setText(formatter.format(oList.get(i-1).getNoOfAnarkali()));
            itemListView.tvnewCount.setText(formatter.format(oList.get(i-1).getNoOfShawl()));
            itemListView.tvnewCount.setText(formatter.format(oList.get(i-1).getNoOfSaree()));
            itemListView.tvnewCount.setText(formatter.format(oList.get(i-1).getNoOfFrock()));
            itemListView.tvnewCount.setText(formatter.format(oList.get(i-1).getNoOfBlouse()));
            itemListView.tvnewCount.setText(formatter.format(oList.get(i-1).getNoOfTopSkirt()));
            itemListView.tvnewCount.setText(formatter.format(oList.get(i-1).getNoOfGown()));
            itemListView.tvnewCount.setText(formatter.format(oList.get(i-1).getNoOfOverCoat()));
            itemListView.tvnewCount.setText(formatter.format(oList.get(i-1).getNoOfItem1()));
            itemListView.tvnewCount.setText(formatter.format(oList.get(i-1).getNoOfItem2()));
            itemListView.tvnewCount.setText(formatter.format(oList.get(i-1).getNoOfItem3()));



          /*  itemListView.tvnewCount.setText(oList.get(i-1).getNoOfAnarkali());
            itemListView.tvnewCount.setText(oList.get(i-1).getNoOfChurider());
            itemListView.tvnewCount.setText(oList.get(i-1).getNoOfShawl());
            itemListView.tvnewCount.setText(oList.get(i-1).getNoOfSaree());
            itemListView.tvnewCount.setText(oList.get(i-1).getNoOfFrock());
            itemListView.tvnewCount.setText(oList.get(i-1).getNoOfBlouse());
            itemListView.tvnewCount.setText(oList.get(i-1).getNoOfTopSkirt());
            itemListView.tvnewCount.setText(oList.get(i-1).getNoOfGown());
            itemListView.tvnewCount.setText(oList.get(i-1).getNoOfOverCoat());
            itemListView.tvnewCount.setText(oList.get(i-1).getNoOfItem1());
            itemListView.tvnewCount.setText(oList.get(i-1).getNoOfItem2());
            itemListView.tvnewCount.setText(oList.get(i-1).getNoOfItem3());*/









            itemListView.imgDecrement.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (getItemCount()<0){
                        Toast.makeText(mContext, "not valid", Toast.LENGTH_SHORT).show();
                    }

                }
            });








            Glide.with(mContext)
                    .load(oList.get(i-1).getImageDrawable())
                    .into(itemListView.civDress);

            if (oList.get(i-1).getItemCount()==0) {
                itemListView.imgDecrement.setImageResource(R.drawable.ic_minus_round);
                itemListView.tvCount.setTextColor(Color.parseColor("#bdbdbd"));
                itemListView.tvnewCount.setTextColor(Color.parseColor("#bdbdbd"));
            } else {
                itemListView.imgDecrement.setImageResource(R.drawable.selector_decrement);
                itemListView.tvCount.setTextColor(Color.parseColor("#757575"));
                itemListView.tvnewCount.setTextColor(Color.parseColor("#757575"));

            }
            if(i%2!=0)
                itemListView.llParent.setBackgroundResource(R.color.listBgColor);
            else
                itemListView.llParent.setBackgroundResource(R.color.transparent);



/*
itemListView.imgDecrement.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!itemListView.tvCount.getText().toString().equals("00")) {
                        int countInt = Integer.parseInt(itemListView.tvCount.getText().toString()) - 1;

                        itemListView.tvCount.setText(formatter.format(countInt));
                        notifyItemChanged(i);
                    }
                }
            });

            itemListView.imgIncrement.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int countInt = Integer.parseInt(itemListView.tvCount.getText().toString()) + 1;
                    itemListView.tvCount.setText(formatter.format(countInt));
                    notifyItemChanged(i);
                }
            });*/

        }





















    }

    @Override
    public int getItemViewType(int position) {
        return position != 0 ? 1 : 0;
    }

    @Override
    public int getItemCount() {
        return oList.size()+1;
    }



    public class ViewHolder extends RecyclerView.ViewHolder {




        public ViewHolder(@NonNull View itemView) {
            super(itemView);



        }
    }

    public class TitleView extends ViewHolder {

        public TitleView(@NonNull View itemView) {
            super(itemView);
        }
    }

    public class ItemListView extends ViewHolder {

        CircleImageView civDress;
        TextView tvProductName, tvCount,tvnewCount;
        ImageView imgDecrement, imgIncrement;
        LinearLayout llParent;

        public ItemListView(@NonNull View itemView) {
            super(itemView);

            llParent = (LinearLayout) itemView.findViewById(R.id.llParentsecond);
            civDress = (CircleImageView) itemView.findViewById(R.id.civDresssecond);
            tvCount = (TextView) itemView.findViewById(R.id.tvCountsecond);
            tvnewCount = (TextView) itemView.findViewById(R.id.tvnewCountsecond);
            tvProductName = (TextView) itemView.findViewById(R.id.tvProductNamesecond);
            imgDecrement = (ImageView) itemView.findViewById(R.id.imgDecrementsecond);
            imgIncrement = (ImageView) itemView.findViewById(R.id.imgIncrementsecond);


            imgDecrement.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (oList.get(getAdapterPosition()-1).getItemCount()>0) {

                        mListener.onClickDecrement(getAdapterPosition());




                    }
                }
            });

            imgIncrement.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onClickIncrement(getAdapterPosition());

                }
            });

        }
    }

}

