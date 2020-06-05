package com.theresa.boutique;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonElement;
import com.theresa.boutique.adapter.SpinnerAlterTypeAdapter;
import com.theresa.boutique.adapter.ViewPagerAdapter;
import com.theresa.boutique.base.BaseActivity;
import com.theresa.boutique.fragment.M1Fragment;
import com.theresa.boutique.fragment.M2Fragment;
import com.theresa.boutique.fragment.M3Fragment;
import com.theresa.boutique.interfaces.ViewPagerInterface;
import com.theresa.boutique.model.AlterItem;
import com.theresa.boutique.model.DesignerItem;
import com.theresa.boutique.model.FabricItem;
import com.theresa.boutique.model.FunctionItem;
import com.theresa.boutique.model.M1Data;
import com.theresa.boutique.model.M2Data;
import com.theresa.boutique.model.M3Data;
import com.theresa.boutique.model.PriorityItem;

import net.bohush.geometricprogressview.GeometricProgressView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.theresa.boutique.util.Constants.BRID;
import static com.theresa.boutique.util.Constants.CMPID;
import static com.theresa.boutique.util.Constants.FAID;
import static com.theresa.boutique.util.Constants.USERID;

public class MeasurementActivity extends BaseActivity implements ViewPagerInterface {

    private Toolbar toolbar;
    private TextView tvMeasureTitle;
    private TabLayout tabs;
    private ViewPager view_pager;
    static String itemName, itemId, orderNo, custId, isAlter, dressType, dressTypeNo, Id;
    private ViewPagerAdapter mViewPagerAdapter;
    private M1Fragment m1Frag;
    private M2Fragment m2Frag;
    private M3Fragment m3Frag;
    private boolean isUpdate = false;
    private boolean firsRun = true;
    int x = 0;
    private GeometricProgressView progressView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_measurement);
        itemName = getIntent().getStringExtra("ItemName");
        itemId = getIntent().getStringExtra("ItemId");
        orderNo = getIntent().getStringExtra("orderNo");
        custId = getIntent().getStringExtra("custId");
        isAlter = getIntent().getStringExtra("isAlter");
        isUpdate = getIntent().getBooleanExtra("isUpdate", false);
//        int index = itemName.indexOf("-");
        dressType = itemId;
        dressTypeNo = itemName;
        initView();

    }

    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tvMeasureTitle = (TextView) findViewById(R.id.tvMeasureTitle);
        tabs = (TabLayout) findViewById(R.id.tabs);
        view_pager = (ViewPager) findViewById(R.id.view_pager);
        progressView = (GeometricProgressView) findViewById(R.id.progressView);
        tvMeasureTitle.setText(itemName);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle(itemName);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        initViewPager();
    }

    @Override
    public void onBackPressed() {

        finish();
    }

    private void initViewPager() {
        view_pager.setOffscreenPageLimit(3);
        mViewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        view_pager.setAdapter(mViewPagerAdapter);
        tabs.setupWithViewPager(view_pager);
        tabs.getTabAt(0).setText("Measurement 1");
        tabs.getTabAt(1).setText("Measurement 2");
        tabs.getTabAt(2).setText("Measurement 3");
        getM1Fragment();
        getM2Fragment();
        getM3Fragment();
        getPriorityData();
        getFunctionData();
        getFabricData();
        getDesignerData();
        getAlterTypeData();
        m1Frag.setType(itemName);
        m2Frag.setType(itemName);
        m3Frag.setType(itemName);
        if (isAlter.equalsIgnoreCase("true"))
            m1Frag.hideAlterationType(View.VISIBLE);
        else
            m1Frag.hideAlterationType(View.INVISIBLE);

        if (isUpdate) {
            Id = getIntent().getStringExtra("autoId");
            getMeasureData();
        }


    }

    private void getAlterTypeData() {
        String CmpId = getSharedPreferenceHelper().getString(CMPID, ""),
                BrId = getSharedPreferenceHelper().getString(BRID, "");
        Call<JsonElement> call = AppApplication.getApiService().getAlterationTypeList(CmpId, BrId);
        call.enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                try {
                    if (response.body() != null) {
                        ArrayList<AlterItem> list = new ArrayList<>();
                        JSONArray jsonArray = new JSONArray(response.body().getAsJsonArray().toString());

                        for (int i = 0; i < jsonArray.length(); i++) {
                            AlterItem od = new AlterItem();
                            JSONObject temp = jsonArray.getJSONObject(i);
                            od.setId(temp.getString("ID"));
                            od.setName(temp.getString("NAME"));
                            od.setIds(temp.getString("IDS"));
                            od.setCode(temp.getString("Code"));
                            list.add(od);
                        }

                        m1Frag.setAlterTypeData(list);
                    } else {
                        Log.e("FunctionElse", "Something went wrong!");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e("Response", "try catch");

                }
            }

            @Override
            public void onFailure(Call<JsonElement> call, Throwable t) {

            }
        });
    }


    private void getM1Fragment() {
//        String tag = "android:switcher:" + R.id.view_pager + ":" + 0;
//        m1Frag = (M1Fragment) getSupportFragmentManager().findFragmentByTag(tag);
        m1Frag = (M1Fragment) mViewPagerAdapter.getFrag(0);
    }

    private void getM2Fragment() {
//        String tag = "android:switcher:" + R.id.view_pager + ":" + 1;
//        m2Frag = (M2Fragment) getSupportFragmentManager().findFragmentByTag(tag);
        m2Frag = (M2Fragment) mViewPagerAdapter.getFrag(1);
    }

    private void getM3Fragment() {
//        String tag = "android:switcher:" + R.id.view_pager + ":" + 2;
//        m3Frag = (M3Fragment) getSupportFragmentManager().findFragmentByTag(tag);
        m3Frag = (M3Fragment) mViewPagerAdapter.getFrag(2);
    }

    private void getPriorityData() {
        Call<JsonElement> call = AppApplication.getApiService().getPriorityList();
        call.enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                try {
                    if (response.body() != null) {
                        ArrayList<PriorityItem> list = new ArrayList<>();
                        JSONArray jsonArray = new JSONArray(response.body().getAsJsonArray().toString());

                        for (int i = 0; i < jsonArray.length(); i++) {
                            PriorityItem od = new PriorityItem();
                            JSONObject temp = jsonArray.getJSONObject(i);
                            od.setId(temp.getString("ID"));
                            od.setName(temp.getString("Name"));
                            list.add(od);
                        }

                        m1Frag.setPriorityData(list);
                    } else {
                        Log.e("PriorityElse", "Something went wrong!");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e("Response", "try catch");

                }
            }

            @Override
            public void onFailure(Call<JsonElement> call, Throwable t) {

            }
        });
    }

    private void getFunctionData() {
        Call<JsonElement> call = AppApplication.getApiService().getFunctionList();
        call.enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                try {
                    if (response.body() != null) {
                        ArrayList<FunctionItem> list = new ArrayList<>();
                        JSONArray jsonArray = new JSONArray(response.body().getAsJsonArray().toString());

                        for (int i = 0; i < jsonArray.length(); i++) {
                            FunctionItem od = new FunctionItem();
                            JSONObject temp = jsonArray.getJSONObject(i);
                            od.setId(temp.getString("ID"));
                            od.setName(temp.getString("Name"));
                            list.add(od);
                        }

                        m1Frag.setFunctionData(list);
                    } else {
                        Log.e("FunctionElse", "Something went wrong!");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e("Response", "try catch");

                }
            }

            @Override
            public void onFailure(Call<JsonElement> call, Throwable t) {

            }
        });
    }

    private void getFabricData() {
        String CmpId = getSharedPreferenceHelper().getString(CMPID, ""),
                BrId = getSharedPreferenceHelper().getString(BRID, "");
        Call<JsonElement> call = AppApplication.getApiService().getFabricTypeList(CmpId, BrId);
        call.enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                try {
                    if (response.body() != null) {
                        ArrayList<FabricItem> list = new ArrayList<>();
                        JSONArray jsonArray = new JSONArray(response.body().getAsJsonArray().toString());

                        for (int i = 0; i < jsonArray.length(); i++) {
                            FabricItem od = new FabricItem();
                            JSONObject temp = jsonArray.getJSONObject(i);
                            od.setId(temp.getString("ID"));
                            od.setName(temp.getString("NAME"));
                            od.setIds(temp.getString("IDS"));
                            od.setCode(temp.getString("Code"));
                            list.add(od);
                        }

                        m1Frag.setFabricData(list);
                    } else {
                        Log.e("FunctionElse", "Something went wrong!");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e("Response", "try catch");

                }
            }

            @Override
            public void onFailure(Call<JsonElement> call, Throwable t) {

            }
        });
    }

    private void getDesignerData() {
        String CmpId = getSharedPreferenceHelper().getString(CMPID, ""),
                BrId = getSharedPreferenceHelper().getString(BRID, "");
        Call<JsonElement> call = AppApplication.getApiService().getDesignerTypeList(CmpId, BrId);
        call.enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                try {
                    if (response.body() != null) {
                        ArrayList<DesignerItem> list = new ArrayList<>();
                        JSONArray jsonArray = new JSONArray(response.body().getAsJsonArray().toString());

                        for (int i = 0; i < jsonArray.length(); i++) {
                            DesignerItem od = new DesignerItem();
                            JSONObject temp = jsonArray.getJSONObject(i);
                            od.setId(temp.getString("ID"));
                            od.setName(temp.getString("NAME"));
                            od.setIds(temp.getString("IDS"));
                            od.setCode(temp.getString("Code"));
                            list.add(od);
                        }

                        m1Frag.setDesignerData(list);
                        m2Frag.setDesignerData(list);
                        m3Frag.setDesignerData(list);
                    } else {
                        Log.e("FunctionElse", "Something went wrong!");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e("Response", "try catch");

                }
            }

            @Override
            public void onFailure(Call<JsonElement> call, Throwable t) {

            }
        });
    }

    public void getDueDate() {
        String CmpId = getSharedPreferenceHelper().getString(CMPID, ""),
                BrId = getSharedPreferenceHelper().getString(BRID, "");

        progressView.setVisibility(View.VISIBLE);
        Call<JsonElement> call = AppApplication.getApiService().getDueDate(CmpId, BrId, m1Frag.modelVal(), orderNo, itemName);
        call.enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                progressView.setVisibility(View.GONE);
                try {
                    String s = response.body().getAsString();
                    Log.e("DueDate", s);
                    m1Frag.updateLabel(s);
                    /*
                    JSONArray jsonArray = new JSONArray(response.body().getAsJsonArray().toString());
                    JSONObject temp = jsonArray.getJSONObject(0);*/
//m3Frag.setDueDate();

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<JsonElement> call, Throwable t) {
//                Toast.makeText(MeasurementActivity.this, "Unable to retrieve old data!", Toast.LENGTH_SHORT).show();
                progressView.setVisibility(View.GONE);
            }
        });
    }

    private void getMeasureData() {
        String CmpId = getSharedPreferenceHelper().getString(CMPID, ""),
                BrId = getSharedPreferenceHelper().getString(BRID, "");

        progressView.setVisibility(View.VISIBLE);
        Call<JsonElement> call = AppApplication.getApiService().getOrderMeasureList(CmpId, BrId, Id, orderNo, itemId);
        call.enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                progressView.setVisibility(View.GONE);
                try {
                    JSONArray jsonArray = new JSONArray(response.body().getAsJsonArray().toString());
                    JSONObject temp = jsonArray.getJSONObject(0);
                    M1Data m1 = new M1Data();
                    m1.setFabTypeId(temp.getString("FabTypeId"));
                    m1.setFunction(temp.getString("Fucntion"));
                    m1.setPriority(temp.getString("Priority"));
                    m1.setIsTop(temp.getString("IsTop"));
                    m1.setTopValue(temp.getString("TopValue"));
                    m1.setIsTopLining(temp.getString("IsTopLining"));
                    m1.setTopLiningValue(temp.getString("TopLiningValue"));
                    m1.setIsYoke(temp.getString("IsYoke"));
                    m1.setYokeValue(temp.getString("YokeValue"));
                    m1.setIsYokeLining(temp.getString("IsYokeLining"));
                    m1.setYokeLiningValue(temp.getString("YokeLiningValue"));
                    m1.setIsLace(temp.getString("IsLace"));
                    m1.setLaceValue(temp.getString("LaceValue"));
                    m1.setIsSleeve(temp.getString("IsSleeve"));
                    m1.setSleeveValue(temp.getString("SleeveValue"));
                    m1.setIsSleeveLin(temp.getString("IsSleeveLin"));
                    m1.setSleeveLinValue(temp.getString("SleeveLinValue"));
                    m1.setIsBottom(temp.getString("IsBottom"));
                    m1.setBottomValue(temp.getString("BottomValue"));
                    m1.setIsBottomLining(temp.getString("IsBottomLining"));
                    m1.setBottomLiningValue(temp.getString("BottomLiningValue"));
                    m1.setMeasurement(temp.getString("Meassurement"));
                    m1.setDesignerId(temp.getString("DesignerId"));
                    m1.setRemarks(temp.getString("Remarks"));
                    m1.setOthers(temp.getString("Others"));
                    m1.setSpeakerNet(temp.getString("SpeakerNet"));
                    m1.setSpeakerNetLining(temp.getString("SpeakerNetLining"));
                    m1.setWorkPiece(temp.getString("WorkPiece"));
                    m1.setSkirt(temp.getString("Skirt"));
                    m1.setSkirtLining(temp.getString("SkirtLining"));
                    m1.setPiping(temp.getString("Piping"));
                    m1.setPipingLining(temp.getString("PipingLining"));
                    m1.setIsSpeakerNet(temp.getString("IsSpeakerNet"));
                    m1.setIsSpeakerNetLining(temp.getString("IsSpeakerNetLining"));
                    m1.setIsWorkPiece(temp.getString("IsWorkPiece"));
                    m1.setIsSkirtLining(temp.getString("IsSkirtLining"));
                    m1.setIsPiping(temp.getString("IsPipping"));
                    m1.setIsPipingLining(temp.getString("IsPippingLining"));
                    m1.setIsSkirtSpeakerNet(temp.getString("IsSkirtSpeakerNet"));
                    m1.setIsSkirtSpeakerNetLining(temp.getString("IsSkirtSpeakerNetLining"));
                    m1.setSkirtOthers(temp.getString("SkirtOthers"));
                    m1.setIsSkirt(temp.getString("IsSkirt"));
                    m1.setAlterationType(temp.getString("AlterationType"));

                    m1Frag.setData(m1);

                    M2Data m2 = new M2Data();
                    m2.setIsZip(temp.getString("IsZip"));
                    m2.setZipType(temp.getString("ZipType"));
                    m2.setIsTuck(temp.getString("IsTuck"));
                    m2.setTuckType(temp.getString("TuckType"));
                    m2.setIsSleeveLining(temp.getString("IsSleeveLining"));
                    m2.setSleeveLining(temp.getString("SleeveLiningType"));
                    m2.setIsSleevePiping(temp.getString("IsSleevePiping"));
                    m2.setSleevePipingType(temp.getString("SleevePipingType"));
                    m2.setIsNeck(temp.getString("IsNeck"));
                    m2.setNeckType(temp.getString("NeckType"));
                    m2.setNeckPiping(temp.getString("NeckPiping"));
                    m2.setIsFlair(temp.getString("IsFlair"));
                    m2.setFlairType(temp.getString("FlairType"));
                    m2.setFlairPiping(temp.getString("FlairPiping"));
                    m2.setIsFlairSlit(temp.getString("IsFlairSlit"));
                    m2.setSlitType(temp.getString("SlitType"));
                    m2.setIsFlairSide(temp.getString("IsFlairSide"));
                    m2.setSideType(temp.getString("SideType"));
                    m2.setIsFlairFront(temp.getString("IsFlairFront"));
                    m2.setFrontType(temp.getString("FrontType"));
                    m2.setBottomType(temp.getString("BottomType"));
                    m2.setPocketType(temp.getString("PocketType"));
                    m2.setM2DesignerId(temp.getString("M2DesignerId"));
                    m2.setM2Remarks(temp.getString("M2Remarks"));
                    m2.setYkkType(temp.getString("YkkType"));
                    m2.setYkkNoType(temp.getString("YkkNoType"));
                    m2.setIsSlitUmbrella(temp.getString("IsSlitUmbrella"));
                    m2.setIsSlitScws(temp.getString("IsSlitScws"));
                    m2.setIsSlitScwsFront(temp.getString("IsSlitScws"));
                    m2.setTemplateNo(temp.getString("TemplateId"));
                    m2.setPad(temp.getString("Pad"));
                    m2.setPleat(temp.getString("Pleat"));
                    m2.setTypeOfFrock(temp.getString("TypeofFrock"));
                    m2.setTungies(temp.getString("Tungies"));
                    m2.setTransparent(temp.getString("Transparent"));
                    m2.setPanel(temp.getString("Panel"));
                    m2.setFall(temp.getString("Fall"));
                    m2.setLock(temp.getString("Lock"));
                    m2.setLockPiping(temp.getString("LockPiping"));
                    m2.setSkirtUmbrella(temp.getString("SkirtUmbrella"));
                    m2.setHangings(temp.getString("Hangings"));
                    m2.setOpen(temp.getString("OpenVal"));
                    m2.setTassels(temp.getString("Tassels"));
                    m2.setStraight(temp.getString("IsStraight"));
                    m2.setHiding(temp.getString("ZipHidingVal"));
                    m2.setNormal(temp.getString("ZipNomalVal"));
                    m2Frag.setData(m2);


                    M3Data m3 = new M3Data();
                    m3.setModel(temp.getString("Model"));
                    if (m3.getModel().equalsIgnoreCase("")) {
                        firsRun = false;
                    }
                    m3.setTopMeasure(temp.getString("TopMeasure"));
                    m3.setYokeFront(temp.getString("YokeFront"));
                    m3.setYokeBack(temp.getString("YokeBack"));
                    m3.setIsLiningM3(temp.getString("IsLiningM3"));
                    m3.setIsWorkM3(temp.getString("IsWorkM3"));
                    m3.setM3DesignerId(temp.getString("M3DesignerId"));
                    m3.setM3Remarks(temp.getString("M3Remarks"));
                    m3.setDueDate(temp.getString("DueDate"));
                    m3.setSkirtMeasure(temp.getString("SkirtMeasure"));
                    m3Frag.setData(m3);

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<JsonElement> call, Throwable t) {
                Toast.makeText(MeasurementActivity.this, "Unable to retrieve old data!", Toast.LENGTH_SHORT).show();
                progressView.setVisibility(View.GONE);
            }
        });
    }

    private void saveOrder() {

        String UserId = getSharedPreferenceHelper().getString(USERID, ""),
                CmpId = getSharedPreferenceHelper().getString(CMPID, ""),
                BrId = getSharedPreferenceHelper().getString(BRID, ""),
                FaId = getSharedPreferenceHelper().getString(FAID, "");
        Call<JsonElement> call;
        progressView.setVisibility(View.VISIBLE);
        call = AppApplication.getApiService().saveOrderMeasure(CmpId, BrId, FaId,
                UserId, custId, isAlter,
                orderNo, dressTypeNo, dressType,
                m1Frag.isTop(), m1Frag.topVal(),
                m1Frag.isTopLining(), m1Frag.topLiningVal(),
                m1Frag.isYoke(), m1Frag.yokeVal(),
                m1Frag.isYokeLining(), m1Frag.yokeLiningVal(),
                m1Frag.isLace(), m1Frag.laceVal(),
                m1Frag.isSleeve(), m1Frag.sleeveVal(),
                m1Frag.isSleeveLining(), m1Frag.sleeveLiningVal(),
                m1Frag.isShawl(), m1Frag.shawlVal(),
                m1Frag.isWeddingnet(),m1Frag.weddingVal(),
                m1Frag.isBottom(), m1Frag.bottomVal(),
                m1Frag.isBottomLining(), m1Frag.bottomLiningVal(),
                m1Frag.priority(), m1Frag.function(), m1Frag.fabricTypeId(),
                m1Frag.measureVal(), m1Frag.designerId(), m1Frag.remarksVal(),
                m2Frag.isZip(), m2Frag.isTuck(), m2Frag.isSleeveLiningM2(),
                "0", m2Frag.isFlairFront(), m2Frag.isFlairSide(), m2Frag.isFlairSlit(),
                m2Frag.sleeveLiningValM2(), m2Frag.ykkVal(), m2Frag.ykkYesVal(), m2Frag.ykkNoVal(),
                m2Frag.isSlitUmbrella(), m2Frag.isSlitScws(), m2Frag.isSlitScwsFront(),
                m2Frag.tuckType(), m2Frag.sleeveLiningType(), m2Frag.sleevePipingType(),
                m2Frag.neckType(), m2Frag.neckPiping(), m2Frag.flairType(), m2Frag.flairPiping(),
                m2Frag.bottomType(), m2Frag.pocketType(), m2Frag.designerM2Id(), "",
                m1Frag.dueDateVal(), m1Frag.modelVal(), m3Frag.isWorkM3(), m3Frag.isLiningM3(),
                m3Frag.topMeasure(), m3Frag.yokeFront(), m3Frag.yokeBack(), m3Frag.designerM3Id(),
                m3Frag.remarksM3(), m1Frag.others(), m1Frag.speakerNet(), m1Frag.speakerNetLining(),
                m2Frag.pad(), m2Frag.pleat(), m2Frag.typeOfFrock(), m2Frag.tungies(),
                m2Frag.transparent(), m2Frag.panel(), m1Frag.workPiece(), "passels", m2Frag.fall(),
                m2Frag.lock(), m2Frag.lockPiping(), m1Frag.skirt(), m1Frag.skirtLining(), m2Frag.skirtUmbrella(),
                m2Frag.hangings(), m2Frag.open(), m1Frag.piping(), m1Frag.pipingLining(), m2Frag.tassels(), m1Frag.isSpeakerNet(),
                m1Frag.isSpeakerNetLining(), m1Frag.isWorkingPiece(), m1Frag.isSkirtLining(), m1Frag.isPiping(),
                m1Frag.isPipingLining(), m1Frag.isSkirtSpeakerNet(), m1Frag.isSkirtSpeakerNetLining(), m1Frag.skirtOthers(), m1Frag.isSkirt(), m2Frag.normal(), m2Frag.hiding(), m3Frag.skirtMeasure(), m2Frag.straight(), m1Frag.getAlterationType());


        call.enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                progressView.setVisibility(View.GONE);
                try {
                    if (response.body() != null) {
                        JSONObject jsonObject = new JSONObject(response.body().getAsJsonObject().toString());
                        Toast.makeText(MeasurementActivity.this, jsonObject.getString("Message"), Toast.LENGTH_SHORT).show();
                        setResult(RESULT_OK);
                        finish();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<JsonElement> call, Throwable t) {
                Toast.makeText(MeasurementActivity.this, "Something went Wrong!", Toast.LENGTH_SHORT).show();
                progressView.setVisibility(View.GONE);
            }
        });

    }

    private void updateOrder() {

        String UserId = getSharedPreferenceHelper().getString(USERID, ""),
                CmpId = getSharedPreferenceHelper().getString(CMPID, ""),
                BrId = getSharedPreferenceHelper().getString(BRID, ""),
                FaId = getSharedPreferenceHelper().getString(FAID, "");
        Call<JsonElement> call;
        progressView.setVisibility(View.VISIBLE);
        call = AppApplication.getApiService().updateOrderMeasure(CmpId, BrId, FaId,
                UserId, Id, custId, isAlter,
                orderNo, dressTypeNo, dressType,
                m1Frag.isTop(), m1Frag.topVal(),
                m1Frag.isTopLining(), m1Frag.topLiningVal(),
                m1Frag.isYoke(), m1Frag.yokeVal(),
                m1Frag.isYokeLining(), m1Frag.yokeLiningVal(),
                m1Frag.isLace(), m1Frag.laceVal(),
                m1Frag.isSleeve(), m1Frag.sleeveVal(),
                m1Frag.isSleeveLining(), m1Frag.sleeveLiningVal(),
                m1Frag.isShawl(), m1Frag.shawlVal(),
                m1Frag.isWeddingnet(),m1Frag.weddingVal(),
                m1Frag.isBottom(), m1Frag.bottomVal(),
                m1Frag.isBottomLining(), m1Frag.bottomLiningVal(),
                m1Frag.priority(), m1Frag.function(), m1Frag.fabricTypeId(),
                m1Frag.measureVal(), m1Frag.designerId(), m1Frag.remarksVal(),
                m2Frag.isZip(), m2Frag.isTuck(), m2Frag.isSleeveLiningM2(),
                "0", m2Frag.isFlairFront(), m2Frag.isFlairSide(), m2Frag.isFlairSlit(),
                m2Frag.sleeveLiningValM2(), m2Frag.ykkVal(), m2Frag.ykkYesVal(), m2Frag.ykkNoVal(), m2Frag.isSlitUmbrella(), m2Frag.isSlitScws(), m2Frag.isSlitScwsFront(),
                m2Frag.tuckType(), m2Frag.sleeveLiningType(), m2Frag.sleevePipingType(),
                m2Frag.neckType(), m2Frag.neckPiping(), m2Frag.flairType(), m2Frag.flairPiping(),
                m2Frag.bottomType(), m2Frag.pocketType(), m2Frag.designerM2Id(), "",
                m1Frag.dueDateVal(), m1Frag.modelVal(), m3Frag.isWorkM3(), m3Frag.isLiningM3(),
                m3Frag.topMeasure(), m3Frag.yokeFront(), m3Frag.yokeBack(), m3Frag.designerM3Id(),
                m3Frag.remarksM3(), m1Frag.others(), m1Frag.speakerNet(), m1Frag.speakerNetLining(), m2Frag.pad(), m2Frag.pleat(), m2Frag.typeOfFrock(), m2Frag.tungies(),
                m2Frag.transparent(), m2Frag.panel(), m1Frag.workPiece(), "passels", m2Frag.fall(),
                m2Frag.lock(), m2Frag.lockPiping(), m1Frag.skirt(), m1Frag.skirtLining(), m2Frag.skirtUmbrella(),
                m2Frag.hangings(), m2Frag.open(), m1Frag.piping(), m1Frag.pipingLining(), m2Frag.tassels(), m1Frag.isSpeakerNet(),
                m1Frag.isSpeakerNetLining(), m1Frag.workPiece(), m1Frag.isSkirtLining(), m1Frag.isPiping(),
                m1Frag.isPipingLining(), m1Frag.isSkirtSpeakerNet(), m1Frag.isSkirtSpeakerNetLining(), m1Frag.skirtOthers(), m1Frag.isSkirt(), m2Frag.normal(), m2Frag.hiding(), m3Frag.skirtMeasure(), m2Frag.straight(), m1Frag.getAlterationType());

        call.enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                progressView.setVisibility(View.GONE);
                try {
                    if (response.body() != null) {
                        JSONObject jsonObject = new JSONObject(response.body().getAsJsonObject().toString());
                        Toast.makeText(MeasurementActivity.this, jsonObject.getString("Message"), Toast.LENGTH_SHORT).show();
                        setResult(RESULT_OK);
                        finish();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<JsonElement> call, Throwable t) {
                Toast.makeText(MeasurementActivity.this, "Something went Wrong!", Toast.LENGTH_SHORT).show();
                progressView.setVisibility(View.GONE);
            }
        });

    }

    @Override
    public void onNextClicked() {
        view_pager.setCurrentItem(view_pager.getCurrentItem() + 1);
    }

    @Override
    public void onClickFinish() {
        if (validate())
            if (isUpdate)
                updateOrder();
            else
                saveOrder();
    }

    @Override
    public void onModelClick() {
        Log.e("onModelClick", "true");
        if (isUpdate && firsRun) {
            Log.e("onModelClick", "FirstRun");
            if (x == 1)
                firsRun = false;

            x++;
        } else {
            getDueDate();
            Log.e("onModelClick", "SecondRun");
        }
    }

    private boolean validate(){
        boolean valid = true;
        if(m1Frag.function().equalsIgnoreCase("0")){
            Toast.makeText(this, "Please select anyone of the Functions!", Toast.LENGTH_LONG).show();
            valid = false;
        }

        if(m1Frag.fabricTypeId().equalsIgnoreCase("0")){
            Toast.makeText(this, "Please select anyone of the Fabrics!", Toast.LENGTH_LONG).show();
            valid = false;
        }

        if(m1Frag.designerId().equalsIgnoreCase("0")){
            Toast.makeText(this, "Please select a Designer in M1!", Toast.LENGTH_LONG).show();
            valid = false;
        }

        if(m2Frag.designerM2Id().equalsIgnoreCase("0")){
            Toast.makeText(this, "Please select a Designer in M2!", Toast.LENGTH_LONG).show();
            valid = false;
        }

        if(m3Frag.designerM3Id().equalsIgnoreCase("0")){
            Toast.makeText(this, "Please select a Designer in M3!", Toast.LENGTH_LONG).show();
            valid = false;
        }

        if(m1Frag.modelVal().equalsIgnoreCase("")){
            Toast.makeText(this, "Please select anyone of Mode type!", Toast.LENGTH_LONG).show();
            valid = false;
        }

        if(m1Frag.dueDateVal().equalsIgnoreCase("")){
            Toast.makeText(this, "Please enter a Due Date!", Toast.LENGTH_LONG).show();
            valid = false;
        }

        return valid;

    }
}
