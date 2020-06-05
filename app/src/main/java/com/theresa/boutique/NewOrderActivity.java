package com.theresa.boutique;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;
import com.google.gson.JsonElement;
import com.theresa.boutique.adapter.ProductListAdapter;
import com.theresa.boutique.adapter.ProductListAdapter2;
import com.theresa.boutique.adapter.SpinnerAlterTypeAdapter;
import com.theresa.boutique.base.BaseActivity;
import com.theresa.boutique.model.AlterItem;
import com.theresa.boutique.model.ProductData;
import net.bohush.geometricprogressview.GeometricProgressView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.katso.livebutton.LiveButton;

import static com.theresa.boutique.util.Constants.BRID;
import static com.theresa.boutique.util.Constants.CMPID;
import static com.theresa.boutique.util.Constants.FAID;

import static com.theresa.boutique.util.Constants.USERID;

public class NewOrderActivity extends BaseActivity implements  ProductListAdapter.ClickListener {
    private Toolbar toolbar;
    private LiveButton btnProceed;
    private RecyclerView rvProductList;
    private GeometricProgressView progressView;
    private ProductListAdapter mAdapter;
    ArrayList<ProductData> list = new ArrayList<>();
    private String orderNo = null, custId, isAlter = "false",itemcount;
    SwitchCompat switchAlter;


    private boolean order = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_order);
        generateOrderNum();





        initView();



        custId = getIntent().getStringExtra("CustId");


    }

    public BroadcastReceiver mMessageReciever = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            String itemcount = intent.getStringExtra("count");
            String item = intent.getStringExtra("item");
            Toast.makeText(NewOrderActivity.this, item  +" "+itemcount, Toast.LENGTH_SHORT).show();

        }
    };

    private void generateOrderNum() {
        Call<JsonElement> call = AppApplication.getApiService().createOrderNo();
        call.enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                try {
                    if (response.body() != null) {
                        JSONObject jsonObject = new JSONObject(response.body().getAsJsonObject().toString());
                        if (jsonObject.getString("ErrorCode").equalsIgnoreCase("0")) {
                            orderNo = jsonObject.getString("Data");
                            if (order)
                                saveOrder();
                        } else {
                            Toast.makeText(NewOrderActivity.this, "OrderNumber Creation failed.", Toast.LENGTH_SHORT).show();
                            progressView.setVisibility(View.GONE);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    progressView.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<JsonElement> call, Throwable t) {
                Toast.makeText(NewOrderActivity.this, "OrderNumber Creation failed.", Toast.LENGTH_SHORT).show();
                progressView.setVisibility(View.GONE);
            }
        });

    }



    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        rvProductList = (RecyclerView) findViewById(R.id.rvProductList);
        btnProceed = (LiveButton) findViewById(R.id.btnProceed);
        switchAlter = (SwitchCompat) findViewById(R.id.switchAlter);
        progressView = (GeometricProgressView) findViewById(R.id.progressView);


        switchAlter.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    isAlter = "true";
                } else {
                    isAlter = "false";
                }
            }
        });

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

        rvProductList.setLayoutManager(new LinearLayoutManager(this));

        addData();


        mAdapter = new ProductListAdapter(this, list);
        rvProductList.setAdapter(mAdapter);

        btnProceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressView.setVisibility(View.VISIBLE);
                order = true;
                //orderlist();
               // LocalBroadcastManager.getInstance(NewOrderActivity.this).registerReceiver(mMessageReciever,new IntentFilter("custom"));

                if (orderNo != null){
                    saveOrder();
                    orderlist();
                }
                else
                    generateOrderNum();

            }
        });


    }

    private void saveOrder() {
        String UserId = getSharedPreferenceHelper().getString(USERID, ""),
                CmpId = getSharedPreferenceHelper().getString(CMPID, ""),
                BrId = getSharedPreferenceHelper().getString(BRID, ""),
                FaId = getSharedPreferenceHelper().getString(FAID, ""),
                noChuridar = String.valueOf(list.get(0).getItemCount()),
                noAnarkali = String.valueOf(list.get(1).getItemCount()),
                noshawl = String.valueOf(list.get(2).getItemCount()),
                noSaree = String.valueOf(list.get(3).getItemCount()),
                noFrock = String.valueOf(list.get(4).getItemCount()),
                noBlouse = String.valueOf(list.get(5).getItemCount()),
                noTopSkirt = String.valueOf(list.get(6).getItemCount()),
                noGown = String.valueOf(list.get(7).getItemCount()),
                noOvercoat = String.valueOf(list.get(8).getItemCount());
               String noSimpleChuridar = String.valueOf(list.get(9).getItemCount());
               String noWeddingBlouse = String.valueOf(list.get(10).getItemCount());
               String noWeddingNet = String.valueOf(list.get(11).getItemCount());

        Call<JsonElement> call;
        call = AppApplication.getApiService().saveOrder(CmpId, BrId, FaId, UserId, custId, isAlter, orderNo, noChuridar, noAnarkali, noshawl, noSaree, noBlouse, noTopSkirt, noGown, noOvercoat, noFrock,"",noSimpleChuridar,noWeddingBlouse,noWeddingNet);

        call.enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                try {
                    if (response.body() != null) {
                        JSONObject jsonObject = new JSONObject(response.body().getAsJsonObject().toString());
                        if (jsonObject.getString("ErrorCode").equalsIgnoreCase("0")) {
                            Toast.makeText(NewOrderActivity.this, jsonObject.getString("Message"), Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(NewOrderActivity.this, OrderedItemActivity.class);
                            intent.putExtra("custId", custId);
                            intent.putExtra("isAlter", isAlter);
                            intent.putExtra("orderNo", orderNo);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(NewOrderActivity.this, jsonObject.getString("Message"), Toast.LENGTH_SHORT).show();
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                progressView.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<JsonElement> call, Throwable t) {
                Toast.makeText(NewOrderActivity.this, "Something went Wrong!", Toast.LENGTH_SHORT).show();
                progressView.setVisibility(View.GONE);
            }
        });
    }

    private void addData() {

        /*List item for title item*//*
        ProductData item0 = new ProductData();
        list.add(item0);*/

        ProductData item1 = new ProductData();
        item1.setItemName("No. of Churidar");
        item1.setItemCount(0);
        item1.setImageDrawable(R.drawable.churidar);
        list.add(item1);
        ProductData item2 = new ProductData();
        item2.setItemName("No. of Anarkali");
        item2.setItemCount(0);
        item2.setImageDrawable(R.drawable.anarkali);
        list.add(item2);
        ProductData item3 = new ProductData();
        item3.setItemName("No. of Shawl");
        item3.setItemCount(0);
        item3.setImageDrawable(R.drawable.shawl);
        list.add(item3);
        ProductData item4 = new ProductData();
        item4.setItemName("No. of Saree");
        item4.setItemCount(0);
        item4.setImageDrawable(R.drawable.saree);
        list.add(item4);
        ProductData item5 = new ProductData();
        item5.setItemName("No. of Frock");
        item5.setItemCount(0);
        item5.setImageDrawable(R.drawable.frock);
        list.add(item5);
        ProductData item6 = new ProductData();
        item6.setItemName("No. of Blouse");
        item6.setItemCount(0);
        item6.setImageDrawable(R.drawable.blouse);
        list.add(item6);
        ProductData item7 = new ProductData();
        item7.setItemName("No. of Top/skirt");
        item7.setItemCount(0);
        item7.setImageDrawable(R.drawable.top);
        list.add(item7);
        ProductData item8 = new ProductData();
        item8.setItemName("No. of Gown");
        item8.setItemCount(0);
        item8.setImageDrawable(R.drawable.gown);
        list.add(item8);
        ProductData item9 = new ProductData();
        item9.setItemName("No. of Overcoat");
        item9.setItemCount(0);
        item9.setImageDrawable(R.drawable.overcoat);
        list.add(item9);
        ProductData item10 = new ProductData();
        item10.setItemName("No. of Simple Churidar");
        item10.setItemCount(0);
        item10.setImageDrawable(R.drawable.simplechuridar);
        list.add(item10);
        ProductData item11 = new ProductData();
        item11.setItemName("No. of Wedding Blouse");
        item11.setItemCount(0);
        item11.setImageDrawable(R.drawable.wedingblouse);
        list.add(item11);
        ProductData item12 = new ProductData();
        item12.setItemName("No. of Wedding Net");
        item12.setItemCount(0);
        item12.setImageDrawable(R.drawable.weddingnet);
        list.add(item12);

    }

    @Override
    public void onClickIncrement(int position) {
        list.get(position - 1).setItemCount(list.get(position - 1).getItemCount() + 1);
        mAdapter.notifyItemChanged(position);
    }

    @Override
    public void onClickDecrement(int position) {
        list.get(position - 1).setItemCount(list.get(position - 1).getItemCount() - 1);
        mAdapter.notifyItemChanged(position);
    }

    private void orderlist() {
        String CmpId = getSharedPreferenceHelper().getString(CMPID, ""),
                BrId = getSharedPreferenceHelper().getString(BRID, "");


        Call<JsonElement> call;
        call = AppApplication.getApiService().OrderList(CmpId, BrId,orderNo);

        call.enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                try {
                    if (response.body() != null) {
                        JSONObject jsonObject = new JSONObject(response.body().getAsJsonObject().toString());
                        if (jsonObject.getString("ErrorCode").equalsIgnoreCase("0")) {
                            Toast.makeText(NewOrderActivity.this, jsonObject.getString("Message"), Toast.LENGTH_SHORT).show();

                            /*Intent intent = new Intent(NewOrderActivity.this, ProductListAdapter2.class);
                            intent.putExtra("custId", custId);
                            intent.putExtra("orderNo", orderNo);
                            intent.putExtra("itemcount",itemcount);

                            startActivity(intent);*/
                            finish();
                        } else {
                            Toast.makeText(NewOrderActivity.this, jsonObject.getString("Message"), Toast.LENGTH_SHORT).show();
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                progressView.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<JsonElement> call, Throwable t) {
                Toast.makeText(NewOrderActivity.this, "Something went Wrong!", Toast.LENGTH_SHORT).show();
                progressView.setVisibility(View.GONE);
            }
        });
    }
}
