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
import com.theresa.boutique.model.OrderDress;
import com.theresa.boutique.model.ProductData;
import com.theresa.boutique.model.ProductData2;
import com.theresa.boutique.util.Constants;

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
import static com.theresa.boutique.util.Constants.ORDERNO;
import static com.theresa.boutique.util.Constants.USERID;

public class SecondNewOrderActivity extends BaseActivity implements ProductListAdapter2.ClickListener {
    private Toolbar toolbar;
    private LiveButton btnProceed;
    private RecyclerView rvProductList;
    private GeometricProgressView progressView;
    private ProductListAdapter2 mAdapter;
    ArrayList<ProductData2> plist = new ArrayList<>();

    private String orderNo = null, custId, isAlter = "false";
    SwitchCompat switchAlter;
    TextView textorderid,textitem;


    private boolean order = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_new_order);
        Intent i=getIntent();

        generateOrderNum();


        initView();

        custId = getIntent().getStringExtra("CustId");
        String title=i.getStringExtra("id");
        textorderid=findViewById(R.id.textorderid);
        textorderid.setText(title);






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
                            if (order){}
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

        addData();




        mAdapter = new ProductListAdapter2(this, plist);
        rvProductList.setAdapter(mAdapter);

        btnProceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressView.setVisibility(View.VISIBLE);
                order = true;

               //OrderList();


                if (orderNo != null){
                    //saveOrder();
                    //OrderList();
                    }

                else
                    generateOrderNum();

            }
        });


    }

  /*  private void saveOrder() {
        String UserId = getSharedPreferenceHelper().getString(USERID, ""),
                CmpId = getSharedPreferenceHelper().getString(CMPID, ""),
                BrId = getSharedPreferenceHelper().getString(BRID, ""),
                FaId = getSharedPreferenceHelper().getString(FAID, ""),
                noChuridar = String.valueOf(plist.get(0).getItemCount()),
                noAnarkali = String.valueOf(plist.get(1).getItemCount()),
                noshawl = String.valueOf(plist.get(2).getItemCount()),
                noSaree = String.valueOf(plist.get(3).getItemCount()),
                noFrock = String.valueOf(plist.get(4).getItemCount()),
                noBlouse = String.valueOf(plist.get(5).getItemCount()),
                noTopSkirt = String.valueOf(plist.get(6).getItemCount()),
                noGown = String.valueOf(plist.get(7).getItemCount()),
                noOvercoat = String.valueOf(plist.get(8).getItemCount());
        String noSimpleChuridar = String.valueOf(plist.get(9).getItemCount());
        String noWeddingBlouse = String.valueOf(plist.get(10).getItemCount());
        String noWeddingNet = String.valueOf(plist.get(11).getItemCount());

        Call<JsonElement> call;
        call = AppApplication.getApiService().saveOrder(CmpId, BrId, FaId, UserId, custId, isAlter, orderNo, noChuridar, noAnarkali, noshawl, noSaree, noBlouse, noTopSkirt, noGown, noOvercoat, noFrock,"",noSimpleChuridar,noWeddingBlouse,noWeddingNet);

        call.enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                try {
                    if (response.body() != null) {
                        JSONObject jsonObject = new JSONObject(response.body().getAsJsonObject().toString());
                        if (jsonObject.getString("ErrorCode").equalsIgnoreCase("0")) {
                            Toast.makeText(SecondNewOrderActivity.this, jsonObject.getString("Message"), Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(SecondNewOrderActivity.this, OrderedItemActivity.class);
                            intent.putExtra("custId", custId);
                            intent.putExtra("isAlter", isAlter);
                            intent.putExtra("orderNo", orderNo);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(SecondNewOrderActivity.this, jsonObject.getString("Message"), Toast.LENGTH_SHORT).show();
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                progressView.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<JsonElement> call, Throwable t) {
                Toast.makeText(SecondNewOrderActivity.this, "Something went Wrong!", Toast.LENGTH_SHORT).show();
                progressView.setVisibility(View.GONE);
            }
        });
    }*/

    private void addData() {

        /*List item for title item*//*
        ProductData item0 = new ProductData();
        list.add(item0);*/

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

    }

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
               String OrderNo=getSharedPreferenceHelper().getString(ORDERNO, "");


        Call<JsonElement> call = AppApplication.getApiService().OrderList(CmpId, BrId, OrderNo);
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
                            od.setAutoId(temp.getInt("AutoID"));
                           od.setId(temp.getInt("Id"));
                           od.setTokenNo(temp.getInt("TokenNo"));
                           od.setRefNo(temp.getInt("RefNo"));
                           od.setTrDate(temp.getInt("TrDate"));
                           od.setAccId(temp.getInt("AcccId"));
                           od.setAccName(temp.getString("AccName"));
                           od.setAccCode(temp.getInt("AccCode"));
                           od.setIsAlteration(temp.getString("IsAlteration"));
                           od.setMobileNo(temp.getInt("MobileNo"));
                           od.setNoOfChurider(temp.getInt("NoOfChurider"));
                           od.setNoOfAnarkali(temp.getInt("NoOfAnarkali"));
                            od.setNoOfShawl(temp.getInt("NoOfShawl"));
                            od.setNoOfSaree(temp.getInt("NoOfSaree"));
                            od.setNoOfFrock(temp.getInt("NoOfFrock"));
                            od.setNoOfBlouse(temp.getInt("NoOfBlouse"));
                            od.setNoOfTopSkirt(temp.getInt("NoOfTopSkirt"));
                            od.setNoOfGown(temp.getInt("NoOfGown"));
                            od.setNoOfOverCoat(temp.getInt("NoOfOverCoat"));
                            od.setNoOfItem1(temp.getInt("NoOfItem1"));
                            od.setNoOfItem2(temp.getInt("NoOfItem2"));
                            od.setNoOfItem3(temp.getInt("NoOfItem3"));
                            od.setNoOfItem4(temp.getInt("NoOfItem4"));
                            od.setNoOfItem5(temp.getInt("NoOfItem5"));
                            od.setTotBags(temp.getInt("TotBags"));
                            od.setCompanyId(temp.getInt("CompanyId"));
                            od.setBranchId(temp.getInt("BranchId"));



                            plist.add(od);


                        }
                        mAdapter.notifyDataSetChanged();
                        progressView.setVisibility(View.GONE);
                    }
                    else {
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
