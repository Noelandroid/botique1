package com.theresa.boutique.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.theresa.boutique.R;
import com.theresa.boutique.SecondNewOrderActivity;
import com.theresa.boutique.model.OrderListData;

import java.util.ArrayList;

public class ExistingOrderAdapter extends RecyclerView.Adapter<ExistingOrderAdapter.ItemViewHolder> {
    ClickListener mListener;
    ArrayList<OrderListData> mList = new ArrayList<>();

    public interface ClickListener {
        void onClickItem(int position);
    }

    public ExistingOrderAdapter(Context context, ArrayList<OrderListData> list) {
        mListener = (ClickListener) context;
        mList = list;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ItemViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.existing_order_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder itemViewHolder, int i) {
        itemViewHolder.tvOrderId.setText(mList.get(i).getOrderId());
        itemViewHolder.tvOrderDate.setText(mList.get(i).getOrderDate());
        itemViewHolder.tvDueDate.setText(mList.get(i).getDueDate());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    public class ItemViewHolder extends RecyclerView.ViewHolder {

        TextView tvOrderId, tvOrderDate, tvDueDate;
        Button addmore;

        public ItemViewHolder(final View itemView) {
            super(itemView);

            tvOrderId = (TextView) itemView.findViewById(R.id.tvOrderId);
            tvOrderDate = (TextView) itemView.findViewById(R.id.tvOrderDate);
            tvDueDate = (TextView) itemView.findViewById(R.id.tvDueDate);
            addmore=(Button) itemView.findViewById(R.id.buttonaddmore);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onClickItem(getAdapterPosition());
                   /* Intent a=new Intent( v.getContext(),SecondNewOrderActivity.class);
                    itemView.getContext().startActivity(a);*/
                }
            });
            addmore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent a=new Intent( view.getContext(),SecondNewOrderActivity.class);
                    a.putExtra("id",mList.get(getAdapterPosition()).getOrderId() );
                    //a.putExtra("date",mList.get(getAdapterPosition()).getOrderDate() );
                    itemView.getContext().startActivity(a);
                }
            });



        }

    }

}
