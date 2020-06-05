package com.theresa.boutique.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.theresa.boutique.R;
import com.theresa.boutique.SecondNewOrderActivity;
import com.theresa.boutique.model.OrderDress;
import com.theresa.boutique.util.Constants;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class OrderedListAdapter extends RecyclerView.Adapter<OrderedListAdapter.ItemViewHolder> {

    ArrayList<OrderDress> mList = new ArrayList<>();
    Context mContext;
    ClickListener mListener;

    public interface ClickListener {
        void onClickItem(int position);

        void onBodyMeasurementClicked(int position, int measurementMode);
    }

    public OrderedListAdapter(Context context, ArrayList<OrderDress> list) {
        mContext = context;
        mListener = (ClickListener) context;
        mList = list;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ItemViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.ordered_item, viewGroup, false));

    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder itemViewHolder, int i) {
        int index = mList.get(i).getName().indexOf("-");
        String name = mList.get(i).getName().substring(0, index).trim();
        String count = mList.get(i).getName().substring(index + 1).trim();


        itemViewHolder.tvOrderItemName.setText(name);
        itemViewHolder.tvCount.setText(count);


        if (Integer.parseInt(mList.get(i).getAutoId()) > 0) {
            itemViewHolder.tvStatus.setText("Complete");
            Drawable dot = mContext.getResources().getDrawable(R.drawable.ic_dot_green);
            itemViewHolder.tvStatus.setCompoundDrawablesWithIntrinsicBounds(dot, null, null, null);
        } else {
            itemViewHolder.tvStatus.setText("Incomplete");
            Drawable dot = mContext.getResources().getDrawable(R.drawable.ic_dot_red);
            itemViewHolder.tvStatus.setCompoundDrawablesWithIntrinsicBounds(dot, null, null, null);
        }


        if (mList.get(i).getBodyMesurementType() == Constants.BodyMeasurementType.NoUI) {

            itemViewHolder.tvBodyMeasurement.setVisibility(View.GONE);
        }else
            itemViewHolder.tvBodyMeasurement.setVisibility(View.VISIBLE);



        Glide.with(mContext)
                .load(mList.get(i).getImage())
                .into(itemViewHolder.civDress);

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {

        TextView tvOrderItemName, tvCount, tvStatus, tvBodyMeasurement;
        CircleImageView civDress;

        public ItemViewHolder(@NonNull final View itemView) {
            super(itemView);

            tvOrderItemName = (TextView) itemView.findViewById(R.id.tvOrderedItemName);
            tvCount = (TextView) itemView.findViewById(R.id.tvCount);
            tvStatus = (TextView) itemView.findViewById(R.id.tvStatus);
            civDress = (CircleImageView) itemView.findViewById(R.id.civDress);
            tvBodyMeasurement = itemView.findViewById(R.id.cust_measurement_tv);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    mListener.onClickItem(getAdapterPosition());
                }
            });

            tvCount.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });


            tvBodyMeasurement.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    mListener.onBodyMeasurementClicked(getAdapterPosition(), mList.get(getAdapterPosition()).getBodyMesurementType());
                }
            });


        }
    }

}
