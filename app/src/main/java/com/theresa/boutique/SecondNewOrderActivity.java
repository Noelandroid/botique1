package com.theresa.boutique;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonElement;
import com.theresa.boutique.adapter.ProductListAdapter2;
import com.theresa.boutique.base.BaseActivity;
import com.theresa.boutique.model.ProductData2;

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
import static com.theresa.boutique.util.Constants.ORDERNO;

public class SecondNewOrderActivity extends BaseActivity implements ProductListAdapter2.ClickListener {
    private Toolbar toolbar;
    private LiveButton btnProceed;
    private RecyclerView rvProductList;
    private GeometricProgressView progressView;
    private ProductListAdapter2 mAdapter;
    ArrayList<ProductData2> plist = new ArrayList<>();

    private String orderNo = null;
    private String orderID = null;
    private String custId, isAlter = "false";
    SwitchCompat switchAlter;
    TextView textorderid, textitem;


    private boolean order = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_new_order);
        Intent i = getIntent();

//        generateOrderNum();


        initView();

        custId = getIntent().getStringExtra("CustId");
        orderID = i.getStringExtra("id");
        textorderid = findViewById(R.id.textorderid);
        textorderid.setText(orderID);


        OrderList();


    }


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
                            if (order) {
                            }
                            //saveOrder();
                        } else {
                            Toast.makeText(SecondNewOrderActivity.this, "OrderNumber Creation failed.", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(SecondNewOrderActivity.this, "OrderNumber Creation failed.", Toast.LENGTH_SHORT).show();
                progressView.setVisibility(View.GONE);
            }
        });

    }


    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbarsecond);
        rvProductList = (RecyclerView) findViewById(R.id.rvProductListsecond);
        btnProceed = (LiveButton) findViewById(R.id.btnProceedsecond);
        switchAlter = (SwitchCompat) findViewById(R.id.switchAltersecond);
        progressView = (GeometricProgressView) findViewById(R.id.progressViewsecond);

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

        //addData();


        mAdapter = new ProductListAdapter2(this, plist);
        rvProductList.setAdapter(mAdapter);

        btnProceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressView.setVisibility(View.VISIBLE);
                order = true;


                if (orderNo != null) {
                    //saveOrder();

                } else
                    generateOrderNum();

            }
        });


    }



    /* private void addData() {

     *//* List item for title item
        ProductData item0 = new ProductData();
        list.add(item0);*//*

        ProductData2 item1 = new ProductData2();
        item1.setItemName("No. of Churidar");
        item1.setItemCount(0);
        item1.setImageDrawable(R.drawable.churidar);
        plist.add(item1);
        ProductData2 item2 = new ProductData2();
        item2.setItemName("No. of Anarkali");
        item2.setItemCount(0);
        item2.setImageDrawable(R.drawable.anarkali);
        plist.add(item2);
        ProductData2 item3 = new ProductData2();
        item3.setItemName("No. of Shawl");
        item3.setItemCount(0);
        item3.setImageDrawable(R.drawable.shawl);
        plist.add(item3);
        ProductData2 item4 = new ProductData2();
        item4.setItemName("No. of Saree");
        item4.setItemCount(0);
        item4.setImageDrawable(R.drawable.saree);
        plist.add(item4);
        ProductData2 item5 = new ProductData2();
        item5.setItemName("No. of Frock");
        item5.setItemCount(0);
        item5.setImageDrawable(R.drawable.frock);
        plist.add(item5);
        ProductData2 item6 = new ProductData2();
        item6.setItemName("No. of Blouse");
        item6.setItemCount(0);
        item6.setImageDrawable(R.drawable.blouse);
        plist.add(item6);
        ProductData2 item7 = new ProductData2();
        item7.setItemName("No. of Top/skirt");
        item7.setItemCount(0);
        item7.setImageDrawable(R.drawable.top);
        plist.add(item7);
        ProductData2 item8 = new ProductData2();
        item8.setItemName("No. of Gown");
        item8.setItemCount(0);
        item8.setImageDrawable(R.drawable.gown);
        plist.add(item8);
        ProductData2 item9 = new ProductData2();
        item9.setItemName("No. of Overcoat");
        item9.setItemCount(0);
        item9.setImageDrawable(R.drawable.overcoat);
        plist.add(item9);
        ProductData2 item10 = new ProductData2();
        item10.setItemName("No. of Simple Churidar");
        item10.setItemCount(0);
        item10.setImageDrawable(R.drawable.simplechuridar);
        plist.add(item10);
        ProductData2 item11 = new ProductData2();
        item11.setItemName("No. of Wedding Blouse");
        item11.setItemCount(0);
        item11.setImageDrawable(R.drawable.wedingblouse);
        plist.add(item11);
        ProductData2 item12 = new ProductData2();
        item12.setItemName("No. of Wedding Net");
        item12.setItemCount(0);
        item12.setImageDrawable(R.drawable.weddingnet);
        plist.add(item12);

    }*/

    @Override
    public void onClickIncrement(int position) {
        plist.get(position - 1).setItemCount(plist.get(position - 1).getItemCount() + 1);
        mAdapter.notifyItemChanged(position);
    }

    @Override
    public void onClickDecrement(int position) {
        plist.get(position - 1).setItemCount(plist.get(position - 1).getItemCount() - 1);
        if (position != 0)
            Toast.makeText(this, "items less than one not valid!", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "add more items", Toast.LENGTH_SHORT).show();
        mAdapter.notifyItemChanged(position);

    }


    private void OrderList() {
        progressView.setVisibility(View.VISIBLE);
        String CmpId = getSharedPreferenceHelper().getString(CMPID, ""),
                BrId = getSharedPreferenceHelper().getString(BRID, "");
        String OrderNo = getSharedPreferenceHelper().getString(ORDERNO, "");


        Call<JsonElement> call = AppApplication.getApiService().OrderList(CmpId, BrId, orderID);
        call.enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                try {
                    if (response.body() != null) {
                        plist.clear();
                        JSONArray jsonArray = new JSONArray(response.body().getAsJsonArray().toString());

                        for (int i = 0; i < jsonArray.length(); i++) {
                            ProductData2 od = new ProductData2();
                            JSONObject temp = jsonArray.getJSONObject(i);
                            od.setAutoId(temp.getString("AutoId"));
                            od.setId(temp.getString("Id"));
                            od.setTokenNo(temp.getString("TokenNo"));
                            od.setRefNo(temp.getString("RefNo"));
                            od.setTrDate(temp.getString("TrDate"));
                            od.setAccId(temp.getString("AccId"));
                            od.setAccName(temp.getString("AccName"));
                            od.setAccCode(temp.getString("AccCode"));
                            od.setIsAlteration(temp.getString("IsAlteration"));
                            od.setMobileNo(temp.getString("MobileNo"));
                            od.setNoOfChurider(temp.getString("NoOfChurider"));
                            od.setNoOfAnarkali(temp.getString("NoOfAnarkali"));
                            od.setNoOfShawl(temp.getString("NoOfShawl"));
                            od.setNoOfSaree(temp.getString("NoOfSaree"));
                            od.setNoOfFrock(temp.getString("NoOfFrock"));
                            od.setNoOfBlouse(temp.getString("NoOfBlouse"));
                            od.setNoOfTopSkirt(temp.getString("NoOfTopSkirt"));
                            od.setNoOfGown(temp.getString("NoOfGown"));
                            od.setNoOfOverCoat(temp.getString("NoOfOverCoat"));
                            od.setNoOfItem1(temp.getString("NoOfItem1"));
                            od.setNoOfItem2(temp.getString("NoOfItem2"));
                            od.setNoOfItem3(temp.getString("NoOfItem3"));
                            od.setTotBags(temp.getString("TotBags"));
                            od.setCompanyId(temp.getString("CompanyId"));
                            od.setBranchId(temp.getString("BranchId"));


                            ProductData2 item1 = new ProductData2();
                            item1.setItemName("No. of Churidar");
                            item1.setItemCount(Integer.parseInt(od.getNoOfChurider()));
                            //item1.setNoOfChurider(od.getNoOfChurider());
                            item1.setImageDrawable(R.drawable.churidar);
                            plist.add(item1);

                            ProductData2 item2 = new ProductData2();
                            item2.setItemName("No. of Anarkali");
                            item2.setItemCount(Integer.parseInt(od.getNoOfAnarkali()));
                            // item2.setNoOfAnarkali(od.getNoOfAnarkali());
                            item2.setImageDrawable(R.drawable.anarkali);
                            plist.add(item2);

                            ProductData2 item3 = new ProductData2();
                            item3.setItemName("No. of Shawl");
                            item3.setItemCount(Integer.parseInt(od.getNoOfShawl()));
                            //item3.setNoOfShawl(od.getNoOfShawl());
                            item3.setImageDrawable(R.drawable.shawl);
                            plist.add(item3);

                            ProductData2 item4 = new ProductData2();
                            item4.setItemName("No. of Saree");
                            item4.setItemCount(Integer.parseInt(od.getNoOfSaree()));
                            //item4.setNoOfSaree(od.getNoOfSaree());
                            item4.setImageDrawable(R.drawable.saree);
                            plist.add(item4);

                            ProductData2 item5 = new ProductData2();
                            item5.setItemName("No. of Frock");
                            item5.setItemCount(Integer.parseInt(od.getNoOfFrock()));
                            // item5.setNoOfFrock(od.getNoOfFrock());
                            item5.setImageDrawable(R.drawable.frock);
                            plist.add(item5);

                            ProductData2 item6 = new ProductData2();
                            item6.setItemName("No. of Blouse");
                            item6.setItemCount(Integer.parseInt(od.getNoOfBlouse()));
                            //item6.setNoOfBlouse(od.getNoOfBlouse());
                            item6.setImageDrawable(R.drawable.blouse);
                            plist.add(item6);

                            ProductData2 item7 = new ProductData2();
                            item7.setItemName("No. of Top/skirt");
                            item7.setItemCount(Integer.parseInt(od.getNoOfTopSkirt()));
                            //item7.setNoOfTopSkirt(od.getNoOfTopSkirt());
                            item7.setImageDrawable(R.drawable.top);
                            plist.add(item7);

                            ProductData2 item8 = new ProductData2();
                            item8.setItemName("No. of Gown");
                            item8.setItemCount(Integer.parseInt(od.getNoOfGown()));
                            //item8.setNoOfGown(od.getNoOfGown());
                            item8.setImageDrawable(R.drawable.gown);
                            plist.add(item8);

                            ProductData2 item9 = new ProductData2();
                            item9.setItemName("No. of Overcoat");
                            item9.setItemCount(Integer.parseInt(od.getNoOfOverCoat()));
                            //item9.setNoOfOverCoat(od.getNoOfOverCoat());
                            item9.setImageDrawable(R.drawable.overcoat);
                            plist.add(item9);

                            ProductData2 item10 = new ProductData2();
                            item10.setItemName("No. of Simple Churidar");
                            item10.setItemCount(Integer.parseInt(od.getNoOfItem1()));
                            //item10.setNoOfItem1(od.getNoOfItem1());
                            item10.setImageDrawable(R.drawable.simplechuridar);
                            plist.add(item10);

                            ProductData2 item11 = new ProductData2();
                            item11.setItemName("No. of Wedding Blouse");
                            item11.setItemCount(Integer.parseInt(od.getNoOfItem2()));
                            //item11.setNoOfItem2(od.getNoOfItem2());
                            item11.setImageDrawable(R.drawable.wedingblouse);
                            plist.add(item11);

                            ProductData2 item12 = new ProductData2();
                            item12.setItemName("No. of Wedding Net");
                            item12.setItemCount(Integer.parseInt(od.getNoOfItem3()));
                            // item12.setNoOfItem3(od.getNoOfItem3());
                            item12.setImageDrawable(R.drawable.weddingnet);
                            plist.add(item12);


                            plist.add(od);


                        }
                        mAdapter.notifyDataSetChanged();
                        progressView.setVisibility(View.GONE);
                    } else {
                        Toast.makeText(SecondNewOrderActivity.this, "No Data Found", Toast.LENGTH_SHORT).show();
                        progressView.setVisibility(View.GONE);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(SecondNewOrderActivity.this, "Something went Wrong!", Toast.LENGTH_SHORT).show();
                    Log.e("Response", "try catch");
                    progressView.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<JsonElement> call, Throwable t) {
                Toast.makeText(SecondNewOrderActivity.this, "Something went Wrong!", Toast.LENGTH_SHORT).show();
                Log.e("Response", "failure");
                progressView.setVisibility(View.GONE);
            }
        });
    }


}
