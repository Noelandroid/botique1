package com.theresa.boutique.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Parcel;
import android.support.annotation.NonNull;
import android.support.v4.content.LocalBroadcastManager;
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
import com.theresa.boutique.model.ProductData;

import java.text.DecimalFormat;
import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ViewHolder> {

    ArrayList<ProductData> mList = new ArrayList<>();
    ClickListener mListener;
    Context mContext;

    public interface ClickListener{
        void onClickIncrement(int position);

        void onClickDecrement(int position);
    }

    public ProductListAdapter(Context context, ArrayList<ProductData> list){
        mContext = context;
        mListener = (ClickListener) context;
        mList = list;
    }




    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (i == 0)
            return new TitleView(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.product_item_title, viewGroup, false));
        else {
            return new ItemListView(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.product_item, viewGroup, false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        if (i != 0) {
            int a = 0;

            final DecimalFormat formatter = new DecimalFormat("00");
            final String aFormatted = formatter.format(a);
            final ItemListView itemListView = (ItemListView) viewHolder;

           itemListView.tvProductName.setText(mList.get(i-1).getItemName());
            itemListView.tvCount.setText(formatter.format(mList.get(i-1).getItemCount()));

            Glide.with(mContext)
                    .load(mList.get(i-1).getImageDrawable())
                    .into(itemListView.civDress);

            if (mList.get(i-1).getItemCount()==0) {
                itemListView.imgDecrement.setImageResource(R.drawable.ic_minus_round);
                itemListView.tvCount.setTextColor(Color.parseColor("#bdbdbd"));
            } else {
                itemListView.imgDecrement.setImageResource(R.drawable.selector_decrement);
                itemListView.tvCount.setTextColor(Color.parseColor("#757575"));

            }
            if(i%2!=0)
                itemListView.llParent.setBackgroundResource(R.color.listBgColor);
            else
                itemListView.llParent.setBackgroundResource(R.color.transparent);


           /* itemListView.imgDecrement.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!itemListView.tvCount.getText().toString().equals("00")) {
                        int countInt = Integer.parseInt(itemListView.tvCount.getText().toString()) - 1;

                        itemListView.tvCount.setText(formatter.format(countInt));
                        notifyItemChanged(i);
                    }
                }
            });*/

            /*itemListView.imgIncrement.setOnClickListener(new View.OnClickListener() {
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
        return mList.size()+1;
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
        TextView tvProductName, tvCount;
        ImageView imgDecrement, imgIncrement;
        LinearLayout llParent;

        public ItemListView(@NonNull View itemView) {
            super(itemView);

            llParent = (LinearLayout) itemView.findViewById(R.id.llParent);
            civDress = (CircleImageView) itemView.findViewById(R.id.civDress);
            tvCount = (TextView) itemView.findViewById(R.id.tvCount);
            tvProductName = (TextView) itemView.findViewById(R.id.tvProductName);
            imgDecrement = (ImageView) itemView.findViewById(R.id.imgDecrement);
            imgIncrement = (ImageView) itemView.findViewById(R.id.imgIncrement);


            imgDecrement.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (mList.get(getAdapterPosition()-1).getItemCount()>0) {

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
