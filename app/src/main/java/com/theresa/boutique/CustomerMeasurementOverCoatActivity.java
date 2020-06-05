package com.theresa.boutique;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.theresa.boutique.model.CustomerMeasurementOverCoatData;
import com.theresa.boutique.util.Constants;

import net.bohush.geometricprogressview.GeometricProgressView;

import ru.katso.livebutton.LiveButton;

public class CustomerMeasurementOverCoatActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TextView toolbar_title;
    private String autoId = "0", customerName = "";
    //  private CustomerData cd;
    private String custId;
    private CustomerMeasurementOverCoatData measurementDataType = new CustomerMeasurementOverCoatData();
    private AppCompatCheckBox checkLooseFit, checkMediumFit, checkTightFit;
    private EditText etShort, etLong, etShoulder, etShoulderF1, etShoulderF2,
            etChest1, etChest2, etChest3, etPoint1, etPoint2,
            etShape, etSlit1, etSlit2, etSleeves1, etSleeves2,
            etSleevesFull, etSleevesQtr1, etSleevesQtr2, etSleevesQtr3, etSleevesQtr4,
            etSubSleeves1, etSubSleeves2, etSubSleeves3, etArmHole1, etArmHole2,
            etArmRound1, etArmRound2, etNeckF, etNeckB, etNeckW,
            etNeckSquare, etRemarks;
    TextView tvLabelSlit;

    private LiveButton btnSave;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_measurement_over_coat);
        if (getIntent() != null) {

            custId = getIntent().getStringExtra(Constants.Intent.CUSTOMER_ID);
        }
        initView();
    }


    private void initView() {
        toolbar = findViewById(R.id.toolbar);
        toolbar_title = findViewById(R.id.toolbar_title);
        btnSave = findViewById(R.id.btnSave);

        checkLooseFit = findViewById(R.id.checkLooseFit);
        checkMediumFit = findViewById(R.id.checkMedium);
        checkTightFit = findViewById(R.id.checkTightFit);

        etShort = findViewById(R.id.etShort);
        etLong = findViewById(R.id.etLong);
        etShoulder = findViewById(R.id.etShoulder);
        etShoulderF1 = findViewById(R.id.etF1);
        etShoulderF2 = findViewById(R.id.etF2);
        etChest1 = findViewById(R.id.etChest1);
        etChest2 = findViewById(R.id.etChest2);
        etChest3 = findViewById(R.id.etChest3);
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
//               /* if (autoId.equalsIgnoreCase("0"))
//                    saveMeasurement();
//                else
//                    updateMeasurement();*/tvLabelSlit

            }
        });


        // TODO: 15/02/19 getData() getting data wen loading itself... (if auto id is available)
        //        getData()

    }


    private void storeData() {
        measurementDataType.setLengthShort(etShort.getText().toString());
        measurementDataType.setLengthLong(etLong.getText().toString());
        measurementDataType.setShoulder(etShoulder.getText().toString());
        measurementDataType.setShoulderF1(etShoulderF1.getText().toString());
        measurementDataType.setShoulderF2(etShoulderF2.getText().toString());
        measurementDataType.setChest1(etChest1.getText().toString());
        measurementDataType.setChest2(etChest2.getText().toString());
        measurementDataType.setChest3(etChest3.getText().toString());
        measurementDataType.setPoint1(etPoint1.getText().toString());
        measurementDataType.setPoint2(etPoint2.getText().toString());
        measurementDataType.setShape(etShape.getText().toString());
        measurementDataType.setSlit1(etSlit1.getText().toString());
        measurementDataType.setSlit2(etSlit2.getText().toString());
        measurementDataType.setSleeves1(etSleeves1.getText().toString());
        measurementDataType.setSleeves2(etSleeves2.getText().toString());
        measurementDataType.setSleevesFull(etSleevesFull.getText().toString());
        measurementDataType.setQtr1(etSleevesQtr1.getText().toString());
        measurementDataType.setQtr2(etSleevesQtr2.getText().toString());
        measurementDataType.setQtr3(etSleevesQtr3.getText().toString());
        measurementDataType.setQtr4(etSleevesQtr4.getText().toString());
        measurementDataType.setSubSleeves1(etSubSleeves1.getText().toString());
        measurementDataType.setSubSleeves2(etSubSleeves2.getText().toString());
        measurementDataType.setSubSleeves3(etSubSleeves3.getText().toString());
        measurementDataType.setArmHole1(etArmHole1.getText().toString());
        measurementDataType.setArmHole2(etArmHole2.getText().toString());
        measurementDataType.setArmRound1(etArmRound1.getText().toString());
        measurementDataType.setArmRound2(etArmRound2.getText().toString());
        measurementDataType.setNeckF(etNeckF.getText().toString());
        measurementDataType.setNeckB(etNeckB.getText().toString());
        measurementDataType.setNeckW(etNeckW.getText().toString());
        measurementDataType.setNeckSquare(etNeckSquare.getText().toString());
        measurementDataType.setRemarks(etRemarks.getText().toString());

        if (checkTightFit.isChecked())
            measurementDataType.setTightFit("1");
        else
            measurementDataType.setTightFit("0");

        if (checkLooseFit.isChecked())
            measurementDataType.setLooseFit("1");
        else
            measurementDataType.setLooseFit("0");

        if (checkMediumFit.isChecked())
            measurementDataType.setMediumFit("1");
        else
            measurementDataType.setMediumFit("");

    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}
