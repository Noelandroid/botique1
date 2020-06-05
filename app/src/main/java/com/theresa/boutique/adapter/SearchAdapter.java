package com.theresa.boutique.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.theresa.boutique.R;
import com.theresa.boutique.model.CustomerData;

import java.util.ArrayList;
import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {

    private ArrayList<CustomerData> mList = new ArrayList<>();
    private ClickListener mListener;

    public SearchAdapter(ClickListener listener) {
        mListener = listener;
    }

    public interface ClickListener {
        void onClickItem(CustomerData cd);

        void onClickNewOrder(CustomerData cd);

        void onClickExistingOrder(CustomerData cd);

        //void onClickMeasurement(CustomerData cd);
    }

    public void setList(ArrayList<CustomerData> list) {
        mList.clear();
        mList = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        /*if (i == 0)
            return new TitleView(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.search_item_title, viewGroup, false));
        else {*/
        return new ItemListView(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.search_item, viewGroup, false));
//        }
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final ItemListView itemListView = (ItemListView) viewHolder;
        itemListView.tvCustomerName.setText(mList.get(i).getCustName());
        itemListView.tvHouseName.setText(mList.get(i).getHouseName());
        itemListView.tvMobileNumber.setText(mList.get(i).getMobileNo());
        itemListView.tvCO.setText(mList.get(i).getCO());
        itemListView.tvCodeId.setText(mList.get(i).getCode());

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position != 0 ? 1 : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    public class TitleView extends ViewHolder {

        TextView tvTitle;

        public TitleView(@NonNull View itemView) {
            super(itemView);

            tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);

        }
    }

    public class ItemListView extends ViewHolder {

        View mView;
        TextView tvCustomerName, tvMobileNumber, tvHouseName, tvCO, tvCodeId, tvOrderNew, tvOrderExisting, tvMeasurement;

        public ItemListView(@NonNull View itemView) {
            super(itemView);
            mView = itemView;
            tvCustomerName = (TextView) itemView.findViewById(R.id.tvCustomerName);
            tvMobileNumber = (TextView) itemView.findViewById(R.id.tvMobileNumber);
            tvHouseName = (TextView) itemView.findViewById(R.id.tvHouseName);
            tvCO = (TextView) itemView.findViewById(R.id.tvCO);
            tvCodeId = (TextView) itemView.findViewById(R.id.tvCodeId);
            tvOrderNew = (TextView) itemView.findViewById(R.id.tvOrderNew);
            tvOrderExisting = (TextView) itemView.findViewById(R.id.tvOrderExisting);
            tvMeasurement = (TextView) itemView.findViewById(R.id.tvMeasurement);
            mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onClickItem(mList.get(getAdapterPosition()));
                }
            });
            tvOrderNew.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onClickNewOrder(mList.get(getAdapterPosition()));
                }
            });
            tvOrderExisting.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onClickExistingOrder(mList.get(getAdapterPosition()));
                }
            });

//            tvMeasurement.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    mListener.onClickMeasurement(mList.get(getAdapterPosition()));
//                }
//            });


        }
    }

}
