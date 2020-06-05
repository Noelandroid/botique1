package com.theresa.boutique;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonElement;
import com.theresa.boutique.adapter.OrderedListAdapter;
import com.theresa.boutique.base.BaseActivity;
import com.theresa.boutique.fragment.M1Fragment;
import com.theresa.boutique.fragment.M1Fragment_old;
import com.theresa.boutique.fragment.M2Fragment;
import com.theresa.boutique.model.OrderDress;
import com.theresa.boutique.util.Constants;

import net.bohush.geometricprogressview.GeometricProgressView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.theresa.boutique.util.Constants.BRID;
import static com.theresa.boutique.util.Constants.CMPID;
import static com.theresa.boutique.util.Constants.MEASUREREQUESTCODE;

public class OrderedItemActivity extends BaseActivity implements OrderedListAdapter.ClickListener {

    private Toolbar toolbar;
    private RecyclerView rvOrderedList;
    private OrderedListAdapter mAdapter;
    private GeometricProgressView progressView;
    TextView tvOrderId;
    ArrayList<OrderDress> list = new ArrayList<>();
    String orderNo, custId, isAlter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ordered_item);
        orderNo = getIntent().getStringExtra("orderNo");
        custId = getIntent().getStringExtra("custId");
        isAlter = getIntent().getStringExtra("isAlter");


        initView();
        getDressList();
    }

    private void getDressList() {
        progressView.setVisibility(View.VISIBLE);
        String CmpId = getSharedPreferenceHelper().getString(CMPID, ""),
                BrId = getSharedPreferenceHelper().getString(BRID, "");

        Call<JsonElement> call = AppApplication.getApiService().getOrderDressList(CmpId, BrId, orderNo);
        call.enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                try {
                    if (response.body() != null) {
                        list.clear();
                        JSONArray jsonArray = new JSONArray(response.body().getAsJsonArray().toString());

                        for (int i = 0; i < jsonArray.length(); i++) {
                            OrderDress od = new OrderDress();
                            int image;
                            JSONObject temp = jsonArray.getJSONObject(i);
                            od.setId(temp.getString("ID"));
                            od.setName(temp.getString("Name"));
                            od.setAccName(temp.getString("AccName"));
                            od.setAccCode(temp.getString("AccCode"));
                            od.setAutoId(temp.getString("AutoId"));
                            Log.e("Id", od.getId());
                            if (od.getId().contains("CH")) {
                                image = R.drawable.churidar;
                                od.setBodyMesurementType(Constants.BodyMeasurementType.NoUI);

                            } else if (od.getId().contains("AN")) {
                                image = R.drawable.anarkali;
                                od.setBodyMesurementType(Constants.BodyMeasurementType.NoUI);

                            }  else if (od.getId().contains("FR")) {
                                image = R.drawable.frock;
                                od.setBodyMesurementType(Constants.BodyMeasurementType.NoUI);

                            } else if (od.getId().contains("BL")) {
                                image = R.drawable.blouse;
                                od.setBodyMesurementType(Constants.BodyMeasurementType.NoUI);

                            } else if (od.getId().contains("SK")) {
                                image = R.drawable.top;
                                od.setBodyMesurementType(Constants.BodyMeasurementType.NoUI);

                            } else if (od.getId().contains("GN")) {
                                image = R.drawable.gown;
                                od.setBodyMesurementType(Constants.BodyMeasurementType.NoUI);

                            } else if (od.getId().contains("SH")) {
                                image = R.drawable.shawl;
                                od.setBodyMesurementType(Constants.BodyMeasurementType.NoUI);

                            } else if (od.getId().contains("SA")) {
                                image = R.drawable.saree;
                                od.setBodyMesurementType(Constants.BodyMeasurementType.NoUI);

                            } else if (od.getId().contains("OV")) {
                                image = R.drawable.overcoat;

                                od.setBodyMesurementType(Constants.BodyMeasurementType.NoUI);
                            }
                            else if (od.getId().contains("CS")) {
                                image = R.drawable.simplechuridar;

                                od.setBodyMesurementType(Constants.BodyMeasurementType.NoUI);
                            }
                            else if (od.getId().contains("WN")) {
                                image = R.drawable.weddingnet;

                                od.setBodyMesurementType(Constants.BodyMeasurementType.NoUI);
                            }
                            else if (od.getId().contains("WB")) {
                                image = R.drawable.wedingblouse;

                                od.setBodyMesurementType(Constants.BodyMeasurementType.NoUI);
                            }




                            else { // items if not present in our list
                                od.setBodyMesurementType(Constants.BodyMeasurementType.NoUI);
                                image = R.drawable.ic_no_image;
                            }
                            od.setImage(image);
                            list.add(od);

                        }
                        mAdapter.notifyDataSetChanged();
                        progressView.setVisibility(View.GONE);
                    } else {
                        Toast.makeText(OrderedItemActivity.this, "No Data Found", Toast.LENGTH_SHORT).show();
                        progressView.setVisibility(View.GONE);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(OrderedItemActivity.this, "Something went Wrong!", Toast.LENGTH_SHORT).show();
                    Log.e("Response", "try catch");
                    progressView.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<JsonElement> call, Throwable t) {
                Toast.makeText(OrderedItemActivity.this, "Something went Wrong!", Toast.LENGTH_SHORT).show();
                Log.e("Response", "failure");
                progressView.setVisibility(View.GONE);
            }
        });
    }

    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        rvOrderedList = (RecyclerView) findViewById(R.id.rvOrderedList);
        progressView = (GeometricProgressView) findViewById(R.id.progressView);
        tvOrderId = (TextView) findViewById(R.id.tvOrderId);
        tvOrderId.setText(orderNo);
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

        rvOrderedList.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new OrderedListAdapter(this, list);
        rvOrderedList.setAdapter(mAdapter);
    }

    @Override
    public void onClickItem(int position) {
        String name = list.get(position).getName();
        String id = list.get(position).getId();
        Intent i = new Intent(this, MeasurementActivity.class);
        i.putExtra("orderNo", orderNo);
        i.putExtra("custId", custId);
        i.putExtra("isAlter", isAlter);
        i.putExtra("ItemName", name);
        i.putExtra("ItemId", id);
        if (Integer.parseInt(list.get(position).getAutoId()) > 0) {
            i.putExtra("isUpdate", true);
            i.putExtra("autoId", list.get(position).getAutoId());
        } else {
            i.putExtra("isUpdate", false);
        }
        startActivityForResult(i, MEASUREREQUESTCODE);
    }

    @Override
    public void onBodyMeasurementClicked(int position, int measurementMode) {
        Intent intent = new Intent();
        switch (measurementMode) {
            case Constants.BodyMeasurementType.BLOUSE:
                intent = new Intent(OrderedItemActivity.this, CustomerMeasurementBlouseActivity.class);
                break;
            case Constants.BodyMeasurementType.TOP_SKIRT:
                intent = new Intent(OrderedItemActivity.this, CustomerMeasurementTopSkirtActivity.class);
                break;
            case Constants.BodyMeasurementType.ANARKALI_CHURIDAR:
                intent = new Intent(OrderedItemActivity.this, CustomerMeasurementAnarkaliOrChuridarActivity.class);
                if (list.get(position).getId().contains("AN")) {
                    intent.putExtra(Constants.Intent.IS_ANARKALI, true);
                } else
                    intent.putExtra(Constants.Intent.IS_ANARKALI, false);

                break;
            case Constants.BodyMeasurementType.FROCK_GOWN:
                intent = new Intent(OrderedItemActivity.this, CustomerMeasurementFrockNGownActivity.class);
                break;
            case Constants.BodyMeasurementType.OVERCOAT:
                intent = new Intent(OrderedItemActivity.this, CustomerMeasurementOverCoatActivity.class);
                break;
            case Constants.BodyMeasurementType.SIMPLECHURIDAR:
                intent = new Intent(OrderedItemActivity.this, CustomerMeasurementAnarkaliOrChuridarActivity.class);
                break;
            case Constants.BodyMeasurementType.WEDDINGNET:
                intent = new Intent(OrderedItemActivity.this, MeasurementActivity.class);
                break;
            case Constants.BodyMeasurementType.WEDDINGBLOUSE:
                intent = new Intent(OrderedItemActivity.this, CustomerMeasurementBlouseActivity.class);
                break;


        }
        intent.putExtra(Constants.Intent.CUSTOMER_ID, custId);
        startActivity(intent);
    }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == MEASUREREQUESTCODE) {
            if (resultCode == RESULT_OK) {
                getDressList();
            }
        }
    }
}
