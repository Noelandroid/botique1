package com.theresa.boutique;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonElement;
import com.theresa.boutique.adapter.SearchAdapter;
import com.theresa.boutique.base.BaseActivity;
import com.theresa.boutique.model.CustomerData;

import net.bohush.geometricprogressview.GeometricProgressView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.theresa.boutique.util.Constants.CMPID;
import static com.theresa.boutique.util.Constants.INTENT_CUSTNAME;
import static com.theresa.boutique.util.Constants.INTENT_HOUSENAME;
import static com.theresa.boutique.util.Constants.INTENT_MOBNUM;

public class CustomerSearchActivity extends BaseActivity implements SearchAdapter.ClickListener {

    private Toolbar toolbar;
    private TextView tvTitle;
    private RecyclerView rvSearchList;
    private SearchAdapter mAdapter;
    private GeometricProgressView progressView;
    AlertDialog ad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_search);

        initView();
        initRecycler();
        initSearch();
    }

    private void initView() {
        progressView = (GeometricProgressView) findViewById(R.id.progressView);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        rvSearchList = (RecyclerView) findViewById(R.id.rvSearchList);
        tvTitle = (TextView) findViewById(R.id.tvTitle);

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
    }

    private void initSearch() {
        String houseName = "";
        String mobileNum = "";
        String customerName = "";
        if (getIntent().getStringExtra(INTENT_HOUSENAME) != null)
            houseName = getIntent().getStringExtra(INTENT_HOUSENAME);

        if (getIntent().getStringExtra(INTENT_MOBNUM) != null)
            mobileNum = getIntent().getStringExtra(INTENT_MOBNUM);

        if (getIntent().getStringExtra(INTENT_CUSTNAME) != null)
            customerName = getIntent().getStringExtra(INTENT_CUSTNAME);

        progressView.setVisibility(View.VISIBLE);
        Call<JsonElement> call = AppApplication.getApiService().searchCustomerList(getSharedPreferenceHelper().getString(CMPID, ""),
                customerName,
                "",
                mobileNum,
                houseName,
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "");

        call.enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                try {
                    if (response.body() != null) {
                        JSONArray jsonArray = new JSONArray(response.body().getAsJsonArray().toString());
                        tvTitle.setText(getResources().getQuantityString(R.plurals.showing_result, jsonArray.length(), jsonArray.length()));
                        Log.e("Response", "Data size" + jsonArray.length());
                        ArrayList<CustomerData> list = new ArrayList<>();

                        for (int i = 0; i < jsonArray.length(); i++) {
                            CustomerData cd = new CustomerData();
                            JSONObject temp = jsonArray.getJSONObject(i);
                            cd.setCustName(temp.getString("Name"));
                            cd.setCode(temp.getString("Code"));
                            cd.setMobileNo(temp.getString("MobNo"));
                            cd.setHouseName(temp.getString("HouseAdd"));
                            cd.setCO(temp.getString("FamilyName"));
                            cd.setPlace("");
                            cd.setPhoneNo(temp.getString("PhNo"));
                            cd.setDoB(temp.getString("Dob"));
                            cd.setFacebook(temp.getString("WebAdd"));
                            cd.setWhatsapp(temp.getString("FaxNo"));
                            cd.setContactAdd(temp.getString("ContactAdd"));
                            cd.setEmailId(temp.getString("Email"));
                            cd.setRemarks(temp.getString("Remarks"));
                            cd.setCustId(temp.getString("ID"));
                            Log.e("CustId from Api", temp.getString("ID"));
                            list.add(cd);
                        }
                        mAdapter.setList(list);
                        progressView.setVisibility(View.GONE);
                    } else {
                        Toast.makeText(CustomerSearchActivity.this, "No Data Found", Toast.LENGTH_SHORT).show();
                        progressView.setVisibility(View.GONE);
                        Log.e("Response", "No Data");
                        tvTitle.setText(getResources().getQuantityString(R.plurals.showing_result, 1));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(CustomerSearchActivity.this, "Something went Wrong!", Toast.LENGTH_SHORT).show();
                    Log.e("Response", "try catch");
                    progressView.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<JsonElement> call, Throwable t) {
                Toast.makeText(CustomerSearchActivity.this, "Something went Wrong!", Toast.LENGTH_SHORT).show();
                Log.e("Response", "failure");
                progressView.setVisibility(View.GONE);
            }
        });

    }

    private void initRecycler() {
        rvSearchList.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new SearchAdapter(this);
        rvSearchList.setAdapter(mAdapter);
    }


    @Override
    public void onClickItem(CustomerData cd) {
        Intent intent = new Intent();
        intent.putExtra("CustomerData", cd);
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public void onClickNewOrder(CustomerData cd) {
        ad = createValidateCodeDialog(cd);
        ad.show();
    }

    @Override
    public void onClickExistingOrder(CustomerData cd) {
        Intent intent = new Intent(CustomerSearchActivity.this, ExistingOrderActivity.class);
        intent.putExtra("CustData", cd);
        startActivity(intent);
        finish();
    }



    private AlertDialog createValidateCodeDialog(final CustomerData cd) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // Get the layout inflater
        LayoutInflater inflater = this.getLayoutInflater();
        View view = inflater.inflate(R.layout.validate_code_dialog, null);
        final EditText et = (EditText) view.findViewById(R.id.etCode);
        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(view)
                // Add action buttons
                .setPositiveButton(R.string.proceed, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        // sign in the user ...
                        if (et.getText().toString().equalsIgnoreCase(cd.getCode())) {
                            Intent intent = new Intent(CustomerSearchActivity.this, NewOrderActivity.class);
                            intent.putExtra("CustId", cd.getCustId());
                            startActivity(intent);
                            finish();
                        } else {
                            ad.cancel();
                            createMissDialog();
                        }
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        ad.cancel();
                    }
                });
        return builder.create();
    }

    private void createMissDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Error")
                .setMessage("Seems like you are trying to create new order for another Customer!")
                .setNegativeButton("Close", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ad.cancel();
                    }
                });
        ad = builder.create();
        ad.show();
    }
}
