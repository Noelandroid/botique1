package com.theresa.boutique;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonElement;
import com.theresa.boutique.adapter.ExistingOrderAdapter;
import com.theresa.boutique.base.BaseActivity;
import com.theresa.boutique.model.CustomerData;
import com.theresa.boutique.model.OrderListData;

import net.bohush.geometricprogressview.GeometricProgressView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.theresa.boutique.util.Constants.BRID;
import static com.theresa.boutique.util.Constants.CMPID;

public class ExistingOrderActivity extends BaseActivity implements ExistingOrderAdapter.ClickListener {

    private Toolbar toolbar;
    Button buttontnaddmore;
    TextView tvCustomerName, tvCustomerCode, tvCustomerMobile;
    private CustomerData cd;
    private RecyclerView rvOrderList;
    private ExistingOrderAdapter mAdapter;
    private GeometricProgressView progressView;
    ArrayList<OrderListData> mList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_existing_order);
        cd = getIntent().getParcelableExtra("CustData");
        initView();



    }

    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        rvOrderList = (RecyclerView) findViewById(R.id.rvOrderList);
        progressView = (GeometricProgressView) findViewById(R.id.progressView);





        toolbar.setNavigationIcon(R.drawable.ic_arrow_back);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        tvCustomerName = (TextView) findViewById(R.id.tvCustomerName);
        tvCustomerCode = (TextView) findViewById(R.id.tvCustomerCode);
        tvCustomerMobile = (TextView) findViewById(R.id.tvCustomerMobile);


        tvCustomerName.setText(cd.getCustName());
        tvCustomerCode.setText(cd.getCode());
        tvCustomerMobile.setText(cd.getMobileNo());
        rvOrderList.setLayoutManager(new LinearLayoutManager(this));

        getData();


        mAdapter = new ExistingOrderAdapter(this, mList);
        rvOrderList.setAdapter(mAdapter);

    }

    private void getData() {
        progressView.setVisibility(View.VISIBLE);
        String CmpId = getSharedPreferenceHelper().getString(CMPID, ""),
                BrId = getSharedPreferenceHelper().getString(BRID, "");
        Call<JsonElement> call = AppApplication.getApiService().getExistingOrderList(CmpId, BrId, cd.getCustId());

        call.enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                try {
                    if (response.body() != null) {
                        JSONArray jsonArray = new JSONArray(response.body().getAsJsonArray().toString());
                        mList.clear();
                        for (int i = 0; i < jsonArray.length(); i++) {
                            OrderListData od = new OrderListData();
                            JSONObject temp = jsonArray.getJSONObject(i);
                            od.setOrderId(temp.getString("TokenNo"));
                            //od.setDueDate(parseDateToddMMyyyy(temp.getString("DueDate")));
                            od.setOrderDate(parseDateToddMMyyyy(temp.getString("TrDate")));
                            od.setIsAleration(temp.getString("IsAlteration"));
                            mList.add(od);
                        }
                        mAdapter.notifyDataSetChanged();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(ExistingOrderActivity.this, "Something went Wrong!", Toast.LENGTH_SHORT).show();
                    Log.e("Response", "try catch");
                }
                progressView.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<JsonElement> call, Throwable t) {
                Toast.makeText(ExistingOrderActivity.this, "Something went Wrong!", Toast.LENGTH_SHORT).show();
                progressView.setVisibility(View.GONE);
            }
        });
    }

    public String parseDateToddMMyyyy(String time) {
        String inputPattern = "MM/dd/yyyy hh:mm:ss a";
        String outputPattern = "dd MMM yyyy";
        SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);

        Date date = null;
        String str = null;

        try {
            date = inputFormat.parse(time);
            str = outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return str;
    }


    @Override
    public void onClickItem(int position) {
        Intent intent = new Intent(ExistingOrderActivity.this, OrderedItemActivity.class);
        intent.putExtra("custId", cd.getCustId());
        intent.putExtra("isAlter", mList.get(position).getIsAleration());
        intent.putExtra("orderNo", mList.get(position).getOrderId());
        /*TODO pass altertype
        *
        * */
        startActivity(intent);
    }
}
