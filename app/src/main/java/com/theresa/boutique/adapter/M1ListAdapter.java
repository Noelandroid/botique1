package com.theresa.boutique.adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SwitchCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.theresa.boutique.R;
import com.theresa.boutique.model.M1ListItem;
import com.theresa.boutique.util.Utils;

import java.util.ArrayList;

/**
 * Created by user on 11/5/2018.
 */

public class M1ListAdapter extends RecyclerView.Adapter<M1ListAdapter.ItemView> {

    private ArrayList<M1ListItem> mList = new ArrayList<>();
    private Activity mActivity;
    private ClickListener mListener;

    public interface ClickListener {
        void onClickSwitch(int position, boolean switchOn);

        void onValueChange(int position, String text);
    }

    public M1ListAdapter(ArrayList<M1ListItem> list, Activity activity, ClickListener listener) {
        mList = list;
        mActivity = activity;
        mListener = listener;
    }

    @NonNull
    @Override
    public ItemView onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ItemView(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.m1_item, viewGroup, false), new MyCustomEditTextListener());
    }

    @Override
    public void onBindViewHolder(@NonNull ItemView itemView, int i) {
        itemView.switchValue.setChecked(mList.get(i).isSwitchOn());
        itemView.tilValue.setHint(mList.get(i).getHintText());
        if (mList.get(i).isSwitchOn()) {
            itemView.etValue.requestFocus();
            itemView.etValue.setEnabled(true);
            Utils.showKeyboard(mActivity, itemView.etValue);
        } else {
            itemView.etValue.setFocusableInTouchMode(false);
            itemView.etValue.setFocusable(false);
            itemView.etValue.setFocusableInTouchMode(true);
            itemView.etValue.setFocusable(true);
            Utils.hideKeyboard(mActivity, itemView.etValue);
            itemView.etValue.setEnabled(false);
        }
        itemView.mMyCustomEditTextListener.updatePosition(itemView.getAdapterPosition());
        itemView.etValue.setText(mList.get(itemView.getAdapterPosition()).getValue());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ItemView extends RecyclerView.ViewHolder {

        TextInputLayout tilValue;
        EditText etValue;
        SwitchCompat switchValue;
        public MyCustomEditTextListener mMyCustomEditTextListener;

        public ItemView(@NonNull View itemView, MyCustomEditTextListener myCustomEditTextListener) {
            super(itemView);
            tilValue = (TextInputLayout) itemView.findViewById(R.id.tilValue);
            etValue = (EditText) itemView.findViewById(R.id.etValue);
            switchValue = (SwitchCompat) itemView.findViewById(R.id.switchValue);
            mMyCustomEditTextListener = myCustomEditTextListener;

            etValue.addTextChangedListener(mMyCustomEditTextListener);

            switchValue.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        if(!mList.get(getAdapterPosition()).isSwitchOn())
                        mListener.onClickSwitch(getAdapterPosition(), true);
                    } else {
                        if(mList.get(getAdapterPosition()).isSwitchOn())
                        mListener.onClickSwitch(getAdapterPosition(), false);
                    }
                }
            });
        }
    }

    private class MyCustomEditTextListener implements TextWatcher {
        private int position;

        public void updatePosition(int position) {
            this.position = position;
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            // no op
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            mList.get(position).setValue(charSequence.toString());
        }

        @Override
        public void afterTextChanged(Editable editable) {
            // no op
            mListener.onValueChange(position, mList.get(position).getValue());
        }
    }

}
