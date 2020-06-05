package com.theresa.boutique.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.theresa.boutique.R;
import com.theresa.boutique.model.FabricItem;
import com.theresa.boutique.model.FunctionItem;

import java.util.ArrayList;

public class SpinnerFabricAdapter extends ArrayAdapter implements SpinnerAdapter {

    ArrayList<FabricItem> mList;

    public SpinnerFabricAdapter(Context context, int resource, ArrayList<FabricItem> list){
        super(context, resource, list);
        mList = list;

    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.spinner_item, parent, false);
        TextView mTextView = (TextView) view.findViewById(R.id.text1);

        mTextView.setText(mList.get(position).getName());
        return view;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.spinner_drop_item, parent, false);
        TextView mTextView = (TextView) view.findViewById(R.id.text1);
        mTextView.setText(mList.get(position).getName());
        return view;
    }
}
