package com.theresa.boutique;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonElement;
import com.theresa.boutique.base.BaseActivity;
import com.theresa.boutique.model.CustomerMeasurementChuridarOrAnarkaliData;
import com.theresa.boutique.retrofit.MyResponseHandler;
import com.theresa.boutique.retrofit.RetroClient;
import com.theresa.boutique.retrofit.WebServiceCallback;
import com.theresa.boutique.util.Constants;

import net.bohush.geometricprogressview.GeometricProgressView;

import org.json.JSONArray;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.katso.livebutton.LiveButton;

import static com.theresa.boutique.util.Constants.BRID;
import static com.theresa.boutique.util.Constants.CMPID;
import static com.theresa.boutique.util.Constants.FAID;
import static com.theresa.boutique.util.Constants.USERID;

public class CustomerMeasurementAnarkaliOrChuridarActivity extends BaseActivity {

    private Toolbar toolbar;
    private TextView toolbar_title;
    private String autoId = "0", customerName = "";
    //  private CustomerData cd;
    private String custId;
    private Boolean isAnarkali; // for hiding slits in the xml
    private CustomerMeasurementChuridarOrAnarkaliData measurementDataType = new CustomerMeasurementChuridarOrAnarkaliData();
    private AppCompatCheckBox checkLooseFit, checkMedium, checkTightFit;
    private EditText etShort, etLongCh, etLongAnr, etLongUm, etShoulder,
            etShoulderF1, etShoulderF2, etSHW, etYoke, etChest1, etChest2, etChest3,
            etReady1, etReady2, etReady3, etPoint1, etPoint2, etShape,
            etSlit1, etSlit2, etSleeves1, etSleeves2, etSleevesFull,
            etSleevesQtr1, etSleevesQtr2, etSleevesQtr3, etSleevesQtr4,
            etSubSleeves1, etSubSleeves2, etSubSleeves3,
            etArmHole1, etArmHole2, etArmRound1, etArmRound2,
            etNeckF, etNeckB, etNeckW, etNeckSquare, etFlair1,
            etBottomLength, etWaistR, etHip, etSalwar,
            etParallel1, etParallel2, etBell1, etBell2, etBell3,
            etChuri1, etChuri2, etChuri3, etRemarks, etPencilCutL, etFlyR;
    TextView tvLabelSlit;

    private LiveButton btnSave;
    private GeometricProgressView progressView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_measurement_anarkali_churidar);

        if (getIntent() != null) {
            isAnarkali = getIntent().getBooleanExtra(Constants.Intent.IS_ANARKALI, false);
            custId = getIntent().getStringExtra(Constants.Intent.CUSTOMER_ID);
        }
        initView();
    }

    private void initView() {
        toolbar = findViewById(R.id.toolbar);
        toolbar_title = findViewById(R.id.toolbar_title);
        progressView = findViewById(R.id.progressView);
        btnSave = findViewById(R.id.btnSave);

        checkLooseFit = findViewById(R.id.checkLooseFit);
        checkMedium = findViewById(R.id.checkMedium);
        checkTightFit = findViewById(R.id.checkTightFit);

        etShort = findViewById(R.id.etShort);
        etLongCh = findViewById(R.id.etLongCh);
        etLongAnr = findViewById(R.id.etLongAnr);
        etLongUm = findViewById(R.id.etLongUm);
        etShoulder = findViewById(R.id.etShoulder);
        etShoulderF1 = findViewById(R.id.etF1);
        etShoulderF2 = findViewById(R.id.etF2);
        etSHW = findViewById(R.id.etSHW);
        etYoke = findViewById(R.id.etYoke);
        etChest1 = findViewById(R.id.etChest1);
        etChest2 = findViewById(R.id.etChest2);
        etChest3 = findViewById(R.id.etChest3);
        etReady1 = findViewById(R.id.etReady1);
        etReady2 = findViewById(R.id.etReady2);
        etReady3 = findViewById(R.id.etReady3);
        etPoint1 = findViewById(R.id.etPoint1);
        etPoint2 = findViewById(R.id.etPoint2);
        etShape = findViewById(R.id.etShape);

        etSlit1 = findViewById(R.id.etSlit1);
        etSlit2 = findViewById(R.id.etSlit2);

        etSleeves1 = findViewById(R.id.etSleeves1);
        etSleeves2 = findViewById(R.id.etSleeves2);
        etSleevesFull = findViewById(R.id.etSleevesFull);
        etSleevesQtr1 = findViewById(R.id.etSleevesQtr1);
        etSleevesQtr2 = findViewById(R.id.etSleevesQtr2);
        etSleevesQtr3 = findViewById(R.id.etSleevesQtr3);
        etSleevesQtr4 = findViewById(R.id.etSleevesQtr4);
        etSubSleeves1 = findViewById(R.id.etSubSleeves1);
        etSubSleeves2 = findViewById(R.id.etSubSleeves2);
        etSubSleeves3 = findViewById(R.id.etSubSleeves3);
        etArmHole1 = findViewById(R.id.etArmHole1);
        etArmHole2 = findViewById(R.id.etArmHole2);
        etArmRound1 = findViewById(R.id.etArmRound1);
        etArmRound2 = findViewById(R.id.etArmRound2);
        etNeckF = findViewById(R.id.etNeckF);
        etNeckB = findViewById(R.id.etNeckB);
        etNeckW = findViewById(R.id.etNeckW);
        etNeckSquare = findViewById(R.id.etNeckSquare);
        etFlair1 = findViewById(R.id.etFlair1);
        etBottomLength = findViewById(R.id.etBottomLength);
        etWaistR = findViewById(R.id.etWaistR);
        etHip = findViewById(R.id.etHip);
        etSalwar = findViewById(R.id.etSalwar);
        etParallel1 = findViewById(R.id.etParallel1);
        etParallel2 = findViewById(R.id.etParallel2);
        etBell1 = findViewById(R.id.etBell1);
        etBell2 = findViewById(R.id.etBell2);
        etBell3 = findViewById(R.id.etBell3);
        etChuri1 = findViewById(R.id.etChuri1);
        etChuri2 = findViewById(R.id.etChuri2);
        etChuri3 = findViewById(R.id.etChuri3);
        etPencilCutL = findViewById(R.id.etPencilCutL);
        etFlyR = findViewById(R.id.etFlyR);
        etRemarks = findViewById(R.id.etRemarks);


        toolbar.setNavigationIcon(R.drawable.ic_arrow_back);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
//        toolbar_title.setText(cd.getCustName());
        //toolbar_title.setText("Body Measurements");


        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                storeData();
                if (autoId.equalsIgnoreCase("0"))
                    saveMeasurement();
                else
                    updateMeasurement();

            }
        });


        // if anarkali hide the slit option
        tvLabelSlit = findViewById(R.id.tv_label_slit);

        if (isAnarkali) {
            etSlit1.setVisibility(View.GONE);
            etSlit2.setVisibility(View.GONE);
            tvLabelSlit.setVisibility(View.GONE);
        }

           //getData();
        getDataAlreadyStoredAPI();

    }


    private void storeData() {
        measurementDataType.setTopShort(etShort.getText().toString());
        measurementDataType.setTopLongCh(etLongCh.getText().toString());
        measurementDataType.setTopLongAnr(etLongAnr.getText().toString());
        measurementDataType.setTopLongUm(etLongUm.getText().toString());
        measurementDataType.setTopShoulder(etShoulder.getText().toString());
        measurementDataType.setTopShoulderF1(etShoulderF1.getText().toString());
        measurementDataType.setTopShoulderF2(etShoulderF2.getText().toString());
        measurementDataType.setTopShw(etSHW.getText().toString());
        measurementDataType.setTopYoke(etYoke.getText().toString());
        measurementDataType.setTopChest1(etChest1.getText().toString());
        measurementDataType.setTopChest2(etChest2.getText().toString());
        measurementDataType.setTopChest3(etChest3.getText().toString());
        measurementDataType.setTopReady1(etReady1.getText().toString());
        measurementDataType.setTopReady2(etReady2.getText().toString());
        measurementDataType.setTopReady3(etReady3.getText().toString());
        measurementDataType.setTopPoint1(etPoint1.getText().toString());
        measurementDataType.setTopPoint2(etPoint2.getText().toString());
        measurementDataType.setTopShape(etShape.getText().toString());

        if (isAnarkali) { // if anarkali then  values for slit is null

            measurementDataType.setTopSlit1("");
            measurementDataType.setTopSlit2("");

        } else {
            measurementDataType.setTopSlit1(etSlit1.getText().toString());
            measurementDataType.setTopSlit2(etSlit2.getText().toString());
        }


        measurementDataType.setTopSleeves1(etSleeves1.getText().toString());
        measurementDataType.setTopSleeves2(etSleeves2.getText().toString());


        measurementDataType.setTopSleevesFull(etSleevesFull.getText().toString());
        measurementDataType.setTopQtr1(etSleevesQtr1.getText().toString());
        measurementDataType.setTopQtr2(etSleevesQtr2.getText().toString());
        measurementDataType.setTopQtr3(etSleevesQtr3.getText().toString());
        measurementDataType.setTopQtr4(etSleevesQtr4.getText().toString());
        measurementDataType.setTopSleevesSub1(etSubSleeves1.getText().toString());
        measurementDataType.setTopSleevesSub2(etSubSleeves2.getText().toString());
        measurementDataType.setTopSleevesSub3(etSubSleeves3.getText().toString());
        measurementDataType.setTopArmHole1(etArmHole1.getText().toString());
        measurementDataType.setTopArmHole2(etArmHole2.getText().toString());
        measurementDataType.setTopArmRound1(etArmRound1.getText().toString());
        measurementDataType.setTopArmRound2(etArmRound2.getText().toString());
        measurementDataType.setTopNeckF(etNeckF.getText().toString());
        measurementDataType.setTopNeckB(etNeckB.getText().toString());
        measurementDataType.setTopNeckW(etNeckW.getText().toString());
        measurementDataType.setTopNeckSquare(etNeckSquare.getText().toString());
        measurementDataType.setTopFlair1(etFlair1.getText().toString());
        measurementDataType.setBottomLength(etBottomLength.getText().toString());
        measurementDataType.setBottomWaistR(etWaistR.getText().toString());
        measurementDataType.setBottomHip(etHip.getText().toString());
        measurementDataType.setBottomSalwar(etSalwar.getText().toString());
        measurementDataType.setBottomParallel1(etParallel1.getText().toString());
        measurementDataType.setBottomParallel2(etParallel2.getText().toString());
        measurementDataType.setBottomBell1(etBell1.getText().toString());
        measurementDataType.setBottomBell2(etBell2.getText().toString());
        measurementDataType.setBottomBell3(etBell3.getText().toString());
        measurementDataType.setBottomChuri1(etChuri1.getText().toString());
        measurementDataType.setBottomChuri2(etChuri2.getText().toString());
        measurementDataType.setBottomChuri3(etChuri3.getText().toString());
        measurementDataType.setPencilCutL(etPencilCutL.getText().toString());
        measurementDataType.setFlyR(etFlyR.getText().toString());
        measurementDataType.setRemarks(etRemarks.getText().toString());

        if (checkTightFit.isChecked())
            measurementDataType.setTightFit("1");
        else
            measurementDataType.setTightFit("0");

        if (checkLooseFit.isChecked())
            measurementDataType.setLooseFit("1");
        else
            measurementDataType.setLooseFit("0");

        if (checkMedium.isChecked())
            measurementDataType.setMediumFit("1");
        else
            measurementDataType.setMediumFit("");

    }

    private void getDataAlreadyStoredAPI() {
        String CmpId = getSharedPreferenceHelper().getString(CMPID, ""),
                BrId = getSharedPreferenceHelper().getString(BRID, "");

        Call<JsonElement> call = RetroClient.getAPIs().getCustomerMeasurementList(CmpId, BrId, custId);


        call.enqueue(new MyResponseHandler(CustomerMeasurementAnarkaliOrChuridarActivity.this, call, true, new WebServiceCallback() {
            @Override
            public void getResponse(int code, JsonElement jsonObjectResponse) {
                try {
                    if (jsonObjectResponse != null) {
                        JSONArray jsonArray = new JSONArray(jsonObjectResponse.getAsJsonArray().toString());

                        JSONObject temp = jsonArray.getJSONObject(0);

                        customerName = temp.getString("AccName");
                        if (!customerName.trim().isEmpty()) {
                            toolbar_title.setText(customerName);
                        }


                        setTightFit(temp.getString("TightFit"));
                        autoId = temp.getString("AutoId");
                        setMedium(temp.getString("Medium"));
                        setLooseFit(temp.getString("LooseFit"));
                        setTopShort(temp.getString("TopShort"));
                        setTopLongCh(temp.getString("TopLongCh"));
                        setTopLongAnr(temp.getString("TopLongAnr"));
                        setTopLongUm(temp.getString("TopLongUm"));
                        setTopShoulder(temp.getString("TopShoulder"));
                        setTopFb1(temp.getString("TopFb1"));
                        setTopFb2(temp.getString("TopFb2"));
                        setTopShw(temp.getString("TopShw"));
                        setTopYoke(temp.getString("TopYoke"));
                        setTopChest1(temp.getString("TopChest1"));
                        setTopChest2(temp.getString("TopChest2"));
                        setTopChest3(temp.getString("TopChest3"));
                        setTopReady1(temp.getString("TopReady1"));
                        setTopReady2(temp.getString("TopReady2"));
                        setTopReady3(temp.getString("TopReady3"));
                        setTopPoint1(temp.getString("TopPoint1"));
                        setTopPoint2(temp.getString("TopPoint2"));
                        setTopShape(temp.getString("TopShape"));

                        if (!isAnarkali) { // if not anarkali set these values
                            setTopSlit1(temp.getString("TopSlit1"));
                            setTopSlit2(temp.getString("TopSlit2"));

                        }

                        setTopSleeves1(temp.getString("TopSleeves1"));
                        setTopSleeves2(temp.getString("TopSleeves2"));
                        setTopSleevesFull(temp.getString("TopSleevesFull"));
                        setTopQtr1(temp.getString("TopQtr1"));
                        setTopQtr2(temp.getString("TopQtr2"));
                        setTopQtr3(temp.getString("TopQtr3"));
                        setTopQtr4(temp.getString("TopQtr4"));
                        setTopSleevesSub1(temp.getString("TopSleevesSub1"));
                        setTopSleevesSub2(temp.getString("TopSleevesSub2"));
                        setTopSleevesSub3(temp.getString("TopSleevesSub3"));
                        setTopArmHole1(temp.getString("TopArmHole1"));
                        setTopArmHole2(temp.getString("TopArmHole2"));
                        setTopArmRound1(temp.getString("TopArmRound1"));
                        setTopArmRound2(temp.getString("TopArmRound2"));
                        setTopNeckF(temp.getString("TopNeckF"));
                        setTopNeckB(temp.getString("TopNeckB"));
                        setTopNeckW(temp.getString("TopNeckW"));
                        setTopNeckSquare(temp.getString("TopNeckSquare"));
                        setTopFlair1(temp.getString("TopFlair1"));
                        setBottomLength(temp.getString("BottomLength"));
                        setBottomWaistR(temp.getString("BottomWaistR"));
                        setBottomHip(temp.getString("BottomHip"));
                        setBottomSalwar(temp.getString("BottomSalwar"));
                        setBottomChuri1(temp.getString("BottomChuri1"));
                        setBottomChuri2(temp.getString("BottomChuri2"));
                        setBottomChuri3(temp.getString("BottomChuri3"));
                        setBottomParallel1(temp.getString("BottomParallel1"));
                        setBottomParallel2(temp.getString("BottomParallel2"));
                        setBottomBell1(temp.getString("BottomBell1"));
                        setBottomBell2(temp.getString("BottomBell2"));
                        setBottomBell3(temp.getString("BottomBell3"));
                        setRemarks(temp.getString("Remarks"));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e("Response", "try catch");
                }

            }

            @Override
            public void getError(Call<JsonElement> call, String message) {
                Log.d("XXXXC", "getError: " + message);
                Toast.makeText(CustomerMeasurementAnarkaliOrChuridarActivity.this, "Something went Wrong!", Toast.LENGTH_SHORT).show();
            }

        }));

    }


    private void getData() {
        progressView.setVisibility(View.VISIBLE);
        String CmpId = getSharedPreferenceHelper().getString(CMPID, ""),
                BrId = getSharedPreferenceHelper().getString(BRID, "");
        Call<JsonElement> call = AppApplication.getApiService().getCustomerMeasurementList(CmpId, BrId, custId);

        call.enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                try {
                    if (response.body() != null) {
                        JSONArray jsonArray = new JSONArray(response.body().getAsJsonArray().toString());

                        JSONObject temp = jsonArray.getJSONObject(0);

                        customerName = temp.getString("AccName");
                        if (!customerName.trim().isEmpty()) {
                            toolbar_title.setText(customerName);
                        }


                        setTightFit(temp.getString("TightFit"));
                        autoId = temp.getString("AutoId");
                        setMedium(temp.getString("Medium"));
                        setLooseFit(temp.getString("LooseFit"));
                        setTopShort(temp.getString("TopShort"));
                        setTopLongCh(temp.getString("TopLongCh"));
                        setTopLongAnr(temp.getString("TopLongAnr"));
                        setTopLongUm(temp.getString("TopLongUm"));
                        setTopShoulder(temp.getString("TopShoulder"));
                        setTopFb1(temp.getString("TopFb1"));
                        setTopFb2(temp.getString("TopFb2"));
                        setTopShw(temp.getString("TopShw"));
                        setTopYoke(temp.getString("TopYoke"));
                        setTopChest1(temp.getString("TopChest1"));
                        setTopChest2(temp.getString("TopChest2"));
                        setTopChest3(temp.getString("TopChest3"));
                        setTopReady1(temp.getString("TopReady1"));
                        setTopReady2(temp.getString("TopReady2"));
                        setTopReady3(temp.getString("TopReady3"));
                        setTopPoint1(temp.getString("TopPoint1"));
                        setTopPoint2(temp.getString("TopPoint2"));
                        setTopShape(temp.getString("TopShape"));

                        if (!isAnarkali) { // if not anarkali set these values
                            setTopSlit1(temp.getString("TopSlit1"));
                            setTopSlit2(temp.getString("TopSlit2"));
                        }

                        setTopSleeves1(temp.getString("TopSleeves1"));
                        setTopSleeves2(temp.getString("TopSleeves2"));
                        setTopSleevesFull(temp.getString("TopSleevesFull"));
                        setTopQtr1(temp.getString("TopQtr1"));
                        setTopQtr2(temp.getString("TopQtr2"));
                        setTopQtr3(temp.getString("TopQtr3"));
                        setTopQtr4(temp.getString("TopQtr4"));
                        setTopSleevesSub1(temp.getString("TopSleevesSub1"));
                        setTopSleevesSub2(temp.getString("TopSleevesSub2"));
                        setTopSleevesSub3(temp.getString("TopSleevesSub3"));
                        setTopArmHole1(temp.getString("TopArmHole1"));
                        setTopArmHole2(temp.getString("TopArmHole2"));
                        setTopArmRound1(temp.getString("TopArmRound1"));
                        setTopArmRound2(temp.getString("TopArmRound2"));
                        setTopNeckF(temp.getString("TopNeckF"));
                        setTopNeckB(temp.getString("TopNeckB"));
                        setTopNeckW(temp.getString("TopNeckW"));
                        setTopNeckSquare(temp.getString("TopNeckSquare"));
                        setTopFlair1(temp.getString("TopFlair1"));
                        setBottomLength(temp.getString("BottomLength"));
                        setBottomWaistR(temp.getString("BottomWaistR"));
                        setBottomHip(temp.getString("BottomHip"));
                        setBottomSalwar(temp.getString("BottomSalwar"));
                        setBottomChuri1(temp.getString("BottomChuri1"));
                        setBottomChuri2(temp.getString("BottomChuri2"));
                        setBottomChuri3(temp.getString("BottomChuri3"));
                        setBottomParallel1(temp.getString("BottomParallel1"));
                        setBottomParallel2(temp.getString("BottomParallel2"));
                        setBottomBell1(temp.getString("BottomBell1"));
                        setBottomBell2(temp.getString("BottomBell2"));
                        setBottomBell3(temp.getString("BottomBell3"));
                        setRemarks(temp.getString("Remarks"));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
//                    Toast.makeText(CustomerMeasurementAnarkaliOrChuridarActivity.this, "Something went Wrong!", Toast.LENGTH_SHORT).show();
                    Log.e("Response", "try catch");
                }
                progressView.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<JsonElement> call, Throwable t) {
                Toast.makeText(CustomerMeasurementAnarkaliOrChuridarActivity.this, "Something went Wrong!", Toast.LENGTH_SHORT).show();
                progressView.setVisibility(View.GONE);
            }
        });
    }

    private void updateMeasurement() {
        progressView.setVisibility(View.VISIBLE);
        String CmpId = getSharedPreferenceHelper().getString(CMPID, ""),
                BrId = getSharedPreferenceHelper().getString(BRID, ""),
                FaId = getSharedPreferenceHelper().getString(FAID, ""),
                UserId = getSharedPreferenceHelper().getString(USERID, "");
        Call<JsonElement> call = AppApplication.getApiService().updateCustomerMeasurement(CmpId, BrId, FaId, UserId, autoId,
                custId, measurementDataType.getTightFit(), measurementDataType.getMediumFit(), measurementDataType.getLooseFit(), measurementDataType.getTopShort(),
                measurementDataType.getTopLongCh(), measurementDataType.getTopLongAnr(), measurementDataType.getTopLongUm(), measurementDataType.getTopShoulder(), measurementDataType.getTopShoulderF1(),
                measurementDataType.getTopShoulderF2(), measurementDataType.getTopShw(), measurementDataType.getTopYoke(), measurementDataType.getTopChest1(), measurementDataType.getTopChest2(),
                measurementDataType.getTopChest3(), measurementDataType.getTopReady1(), measurementDataType.getTopReady2(), measurementDataType.getTopReady3(), measurementDataType.getTopPoint1(),
                measurementDataType.getTopPoint2(), measurementDataType.getTopShape(), measurementDataType.getTopSlit1(), measurementDataType.getTopSlit2(),
                "", measurementDataType.getTopSleeves1(), measurementDataType.getTopSleeves2(), measurementDataType.getTopSleevesFull(),
                measurementDataType.getTopQtr1(), measurementDataType.getTopQtr2(), measurementDataType.getTopQtr3(), measurementDataType.getTopQtr4(),
                measurementDataType.getTopSleevesSub1(), measurementDataType.getTopSleevesSub2(), measurementDataType.getTopSleevesSub3(),
                measurementDataType.getTopArmHole1(), measurementDataType.getTopArmHole2(), measurementDataType.getTopArmRound1(), measurementDataType.getTopArmRound2(),
                measurementDataType.getTopNeckF(), measurementDataType.getTopNeckB(), measurementDataType.getTopNeckW(), measurementDataType.getTopNeckSquare(),
                measurementDataType.getTopFlair1(), "", measurementDataType.getBottomLength(), measurementDataType.getBottomWaistR(),
                measurementDataType.getBottomHip(), measurementDataType.getBottomSalwar(), measurementDataType.getBottomChuri1(), measurementDataType.getBottomChuri2(),
                measurementDataType.getBottomChuri3(), "", measurementDataType.getBottomParallel1(), measurementDataType.getBottomParallel2(),
                measurementDataType.getBottomBell1(), measurementDataType.getBottomBell2(), measurementDataType.getBottomBell3(), measurementDataType.getRemarks());

        call.enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                try {
                    if (response.body() != null) {
                        /*JSONArray jsonArray = new JSONArray(response.body().getAsJsonArray().toString());
                        mList.clear();
                        for (int i = 0; i < jsonArray.length(); i++) {
                            OrderListData od = new OrderListData();
                            JSONObject temp = jsonArray.getJSONObject(i);
                            od.setOrderId(temp.getString("TokenNo"));
                            od.setDueDate(temp.getString("DueDate"));
                            od.setOrderDate(temp.getString("TrDate"));
                            od.setIsAleration(temp.getString("IsAlteration"));
                            mList.add(od);
                        }
                        mAdapter.notifyDataSetChanged();*/
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(CustomerMeasurementAnarkaliOrChuridarActivity.this, "Something went Wrong!", Toast.LENGTH_SHORT).show();
                    Log.e("Response", "try catch");
                }
                progressView.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<JsonElement> call, Throwable t) {
                Toast.makeText(CustomerMeasurementAnarkaliOrChuridarActivity.this, "Something went Wrong!", Toast.LENGTH_SHORT).show();
                progressView.setVisibility(View.GONE);
            }
        });
    }

    private void saveMeasurement() {
        progressView.setVisibility(View.VISIBLE);
        String CmpId = getSharedPreferenceHelper().getString(CMPID, ""),
                BrId = getSharedPreferenceHelper().getString(BRID, ""),
                FaId = getSharedPreferenceHelper().getString(FAID, ""),
                UserId = getSharedPreferenceHelper().getString(USERID, "");
        Call<JsonElement> call = AppApplication.getApiService().saveCustomerMeasurement(CmpId, BrId, FaId, UserId,
                custId, measurementDataType.getTightFit(), measurementDataType.getMediumFit(), measurementDataType.getLooseFit(), measurementDataType.getTopShort(),
                measurementDataType.getTopLongCh(), measurementDataType.getTopLongAnr(), measurementDataType.getTopLongUm(), measurementDataType.getTopShoulder(), measurementDataType.getTopShoulderF1(),
                measurementDataType.getTopShoulderF2(), measurementDataType.getTopShw(), measurementDataType.getTopYoke(), measurementDataType.getTopChest1(), measurementDataType.getTopChest2(),
                measurementDataType.getTopChest3(), measurementDataType.getTopReady1(), measurementDataType.getTopReady2(), measurementDataType.getTopReady3(), measurementDataType.getTopPoint1(),
                measurementDataType.getTopPoint2(), measurementDataType.getTopShape(), measurementDataType.getTopSlit1(), measurementDataType.getTopSlit2(),
                "", measurementDataType.getTopSleeves1(), measurementDataType.getTopSleeves2(), measurementDataType.getTopSleevesFull(),
                measurementDataType.getTopQtr1(), measurementDataType.getTopQtr2(), measurementDataType.getTopQtr3(), measurementDataType.getTopQtr4(),
                measurementDataType.getTopSleevesSub1(), measurementDataType.getTopSleevesSub2(), measurementDataType.getTopSleevesSub3(),
                measurementDataType.getTopArmHole1(), measurementDataType.getTopArmHole2(), measurementDataType.getTopArmRound1(), measurementDataType.getTopArmRound2(),
                measurementDataType.getTopNeckF(), measurementDataType.getTopNeckB(), measurementDataType.getTopNeckW(), measurementDataType.getTopNeckSquare(),
                measurementDataType.getTopFlair1(), "", measurementDataType.getBottomLength(), measurementDataType.getBottomWaistR(),
                measurementDataType.getBottomHip(), measurementDataType.getBottomSalwar(), measurementDataType.getBottomChuri1(), measurementDataType.getBottomChuri2(),
                measurementDataType.getBottomChuri3(), "", measurementDataType.getBottomParallel1(), measurementDataType.getBottomParallel2(),
                measurementDataType.getBottomBell1(), measurementDataType.getBottomBell2(), measurementDataType.getBottomBell3(), measurementDataType.getRemarks());

        call.enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                try {
                    if (response.body() != null) {
                        /*JSONArray jsonArray = new JSONArray(response.body().getAsJsonArray().toString());
                        mList.clear();
                        for (int i = 0; i < jsonArray.length(); i++) {
                            OrderListData od = new OrderListData();
                            JSONObject temp = jsonArray.getJSONObject(i);
                            od.setOrderId(temp.getString("TokenNo"));
                            od.setDueDate(temp.getString("DueDate"));
                            od.setOrderDate(temp.getString("TrDate"));
                            od.setIsAleration(temp.getString("IsAlteration"));
                            mList.add(od);
                        }
                        mAdapter.notifyDataSetChanged();*/
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(CustomerMeasurementAnarkaliOrChuridarActivity.this, "Something went Wrong!", Toast.LENGTH_SHORT).show();
                    Log.e("Response", "try catch");
                }
                progressView.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<JsonElement> call, Throwable t) {
                Toast.makeText(CustomerMeasurementAnarkaliOrChuridarActivity.this, "Something went Wrong!", Toast.LENGTH_SHORT).show();
                progressView.setVisibility(View.GONE);
            }
        });
    }

    public void setTightFit(String tightFit) {
        measurementDataType.setTightFit(tightFit);
        if (tightFit.equalsIgnoreCase("1"))
            checkTightFit.setChecked(true);
        else
            checkTightFit.setChecked(false);
    }

    public void setMedium(String medium) {
        measurementDataType.setMediumFit(medium);
        if (medium.equalsIgnoreCase("1"))
            checkMedium.setChecked(true);
        else
            checkMedium.setChecked(false);
    }

    public void setLooseFit(String looseFit) {
        measurementDataType.setLooseFit(looseFit);
        if (looseFit.equalsIgnoreCase("1"))
            checkLooseFit.setChecked(true);
        else
            checkLooseFit.setChecked(false);
    }

    public void setTopShort(String topShort) {
        measurementDataType.setTopShort(topShort);
        etShort.setText(topShort);
    }

    public void setTopLongCh(String topLongCh) {
        measurementDataType.setTopLongCh(topLongCh);
        etLongCh.setText(topLongCh);
    }

    public void setTopLongAnr(String topLongAnr) {
        measurementDataType.setTopLongAnr(topLongAnr);
        etLongAnr.setText(topLongAnr);
    }

    public void setTopLongUm(String topLongUm) {
        measurementDataType.setTopLongUm(topLongUm);
        etLongUm.setText(topLongUm);
    }

    public void setTopShoulder(String topShoulder) {
        measurementDataType.setTopShoulder(topShoulder);
        etShoulder.setText(topShoulder);
    }

    public void setTopFb1(String topFb1) {
        measurementDataType.setTopShoulderF1(topFb1);
        etShoulderF1.setText(topFb1);
    }

    public void setTopFb2(String topFb2) {
        measurementDataType.setTopShoulderF2(topFb2);
        etShoulderF2.setText(topFb2);
    }

    public void setTopShw(String topShw) {
        measurementDataType.setTopShw(topShw);
        etSHW.setText(topShw);
    }

    public void setTopYoke(String topYoke) {
        measurementDataType.setTopYoke(topYoke);
        etYoke.setText(topYoke);
    }

    public void setTopChest1(String topChest1) {
        measurementDataType.setTopChest1(topChest1);
        etChest1.setText(topChest1);
    }

    public void setTopChest2(String topChest2) {
        measurementDataType.setTopChest2(topChest2);
        etChest2.setText(topChest2);
    }

    public void setTopChest3(String topChest3) {
        measurementDataType.setTopChest3(topChest3);
        etChest3.setText(topChest3);
    }

    public void setTopReady1(String topReady1) {
        measurementDataType.setTopReady1(topReady1);
        etReady1.setText(topReady1);
    }

    public void setTopReady2(String topReady2) {
        measurementDataType.setTopReady2(topReady2);
        etReady2.setText(topReady2);
    }

    public void setTopReady3(String topReady3) {
        measurementDataType.setTopReady3(topReady3);
        etReady3.setText(topReady3);
    }

    public void setTopPoint1(String topPoint1) {
        measurementDataType.setTopPoint1(topPoint1);
        etPoint1.setText(topPoint1);
    }

    public void setTopPoint2(String topPoint2) {
        measurementDataType.setTopPoint2(topPoint2);
        etPoint2.setText(topPoint2);
    }

    public void setTopShape(String topShape) {
        measurementDataType.setTopShape(topShape);
        etShape.setText(topShape);
    }

    public void setTopSlit1(String topSlit1) {
        measurementDataType.setTopSlit1(topSlit1);
        etSlit1.setText(topSlit1);
    }

    public void setTopSlit2(String topSlit2) {
        measurementDataType.setTopSlit2(topSlit2);
        etSlit2.setText(topSlit2);
    }


    public void setTopSleeves1(String topSleeves1) {
        measurementDataType.setTopSleeves1(topSleeves1);
        etSleeves1.setText(topSleeves1);
    }

    public void setTopSleeves2(String topSleeves2) {
        measurementDataType.setTopSleeves2(topSleeves2);
        etSleeves2.setText(topSleeves2);
    }

    public void setTopSleevesFull(String topSleevesFull) {
        measurementDataType.setTopSleevesFull(topSleevesFull);
        etSleevesFull.setText(topSleevesFull);
    }

    public void setTopQtr1(String topQtr1) {
        measurementDataType.setTopQtr1(topQtr1);
        etSleevesQtr1.setText(topQtr1);
    }

    public void setTopQtr2(String topQtr2) {
        measurementDataType.setTopQtr2(topQtr2);
        etSleevesQtr2.setText(topQtr2);
    }

    public void setTopQtr3(String topQtr3) {
        measurementDataType.setTopQtr3(topQtr3);
        etSleevesQtr3.setText(topQtr3);
    }

    public void setTopQtr4(String topQtr4) {
        measurementDataType.setTopQtr4(topQtr4);
        etSleevesQtr4.setText(topQtr4);
    }

    public void setTopSleevesSub1(String topSleevesSub1) {
        measurementDataType.setTopSleevesSub1(topSleevesSub1);
        etSubSleeves1.setText(topSleevesSub1);
    }

    public void setTopSleevesSub2(String topSleevesSub2) {
        measurementDataType.setTopSleevesSub2(topSleevesSub2);
        etSubSleeves2.setText(topSleevesSub2);
    }

    public void setTopSleevesSub3(String topSleevesSub3) {
        measurementDataType.setTopSleevesSub3(topSleevesSub3);
        etSubSleeves3.setText(topSleevesSub3);
    }

    public void setTopArmHole1(String topArmHole1) {
        measurementDataType.setTopArmHole1(topArmHole1);
        etArmHole1.setText(topArmHole1);
    }

    public void setTopArmHole2(String topArmHole2) {
        measurementDataType.setTopArmHole2(topArmHole2);
        etArmHole2.setText(topArmHole2);
    }

    public void setTopArmRound1(String topArmRound1) {
        measurementDataType.setTopArmRound1(topArmRound1);
        etArmRound1.setText(topArmRound1);
    }

    public void setTopArmRound2(String topArmRound2) {
        measurementDataType.setTopArmRound2(topArmRound2);
        etArmRound2.setText(topArmRound2);
    }

    public void setTopNeckF(String topNeckF) {
        measurementDataType.setTopNeckF(topNeckF);
        etNeckF.setText(topNeckF);
    }

    public void setTopNeckB(String topNeckB) {
        measurementDataType.setTopNeckB(topNeckB);
        etNeckB.setText(topNeckB);
    }

    public void setTopNeckW(String topNeckW) {
        measurementDataType.setTopNeckW(topNeckW);
        etNeckW.setText(topNeckW);
    }

    public void setTopNeckSquare(String topNeckSquare) {
        measurementDataType.setTopNeckSquare(topNeckSquare);
        etNeckSquare.setText(topNeckSquare);
    }

    public void setTopFlair1(String topFlair1) {
        measurementDataType.setTopFlair1(topFlair1);
        etFlair1.setText(topFlair1);
    }

    public void setBottomLength(String bottomLength) {
        measurementDataType.setBottomLength(bottomLength);
        etBottomLength.setText(bottomLength);
    }

    public void setBottomWaistR(String bottomWaistR) {
        measurementDataType.setBottomWaistR(bottomWaistR);
        etWaistR.setText(bottomWaistR);
    }

    public void setBottomHip(String bottomHip) {
        measurementDataType.setBottomHip(bottomHip);
        etHip.setText(bottomHip);
    }

    public void setBottomSalwar(String bottomSalwar) {
        measurementDataType.setBottomSalwar(bottomSalwar);
        etSalwar.setText(bottomSalwar);
    }

    public void setBottomChuri1(String bottomChuri1) {
        measurementDataType.setBottomChuri1(bottomChuri1);
        etChuri1.setText(bottomChuri1);
    }

    public void setBottomChuri2(String bottomChuri2) {
        measurementDataType.setBottomChuri2(bottomChuri2);
        etChuri2.setText(bottomChuri2);
    }

    public void setBottomChuri3(String bottomChuri3) {
        measurementDataType.setBottomChuri3(bottomChuri3);
        etChuri3.setText(bottomChuri3);
    }


    public void setBottomParallel1(String bottomParallel1) {
        measurementDataType.setBottomParallel1(bottomParallel1);
        etParallel1.setText(bottomParallel1);
    }

    public void setBottomParallel2(String bottomParallel2) {
        measurementDataType.setBottomParallel2(bottomParallel2);
        etParallel2.setText(bottomParallel2);
    }

    public void setBottomBell1(String bottomBell1) {
        measurementDataType.setBottomBell1(bottomBell1);
        etBell1.setText(bottomBell1);
    }

    public void setBottomBell2(String bottomBell2) {
        measurementDataType.setBottomBell2(bottomBell2);
        etBell2.setText(bottomBell2);
    }

    public void setBottomBell3(String bottomBell3) {
        measurementDataType.setBottomBell3(bottomBell3);
        etBell3.setText(bottomBell3);
    }

    public void setRemarks(String remarks) {
        measurementDataType.setRemarks(remarks);
        etRemarks.setText(remarks);
    }


}
