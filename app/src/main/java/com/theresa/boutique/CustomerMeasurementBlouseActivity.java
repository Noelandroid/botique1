package com.theresa.boutique;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.theresa.boutique.base.BaseActivity;
import com.theresa.boutique.model.CustomerMeasurementBlouseData;

import ru.katso.livebutton.LiveButton;

public class CustomerMeasurementBlouseActivity extends BaseActivity {
    private String custId;
    private Toolbar toolbar;
    private TextView toolbar_title;
    LiveButton buttonSave;

    CustomerMeasurementBlouseData measurementDataType = new CustomerMeasurementBlouseData();

    EditText edLength, edShoulder, edF1, edF2, edChest1, edChest2, edChest3, edPoint1,edPoint2,
    edSleeve1, edSleeve2, edQtr1, edQtr2, edQtr3, edQtr4, edFull1, edFull2, edFull3, edFull4,
    edArmHole1, edArmHole2, edArmRound1, edNeckF, edNeckB, edNeckW;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_measurement_blouse);

        if (getIntent() != null) {
            custId = getIntent().getStringExtra("custId");
        }
        initView();
    }

    private void initView() {
        edLength = findViewById(R.id.edLength);
        edShoulder = findViewById(R.id.edShoulder);
        edF1 = findViewById(R.id.edF1);
        edF2 = findViewById(R.id.edF2);
        edChest1 = findViewById(R.id.edChest1);
        edChest2 = findViewById(R.id.edChest2);
        edChest3 = findViewById(R.id.edChest3);
        edPoint1 = findViewById(R.id.edPoint1);
        edPoint2 = findViewById(R.id.edPoint2);
        edSleeve1 = findViewById(R.id.edSleeve1);
        edSleeve2 = findViewById(R.id.edSleeve2);
        edQtr1 = findViewById(R.id.edQtr1);
        edQtr2 = findViewById(R.id.edQtr2);
        edQtr3 = findViewById(R.id.edQtr3);
        edQtr4 = findViewById(R.id.edQtr4);
        edFull1 = findViewById(R.id.edFull1);
        edFull2 = findViewById(R.id.edFull2);
        edFull3 = findViewById(R.id.edFull3);
        edFull4 = findViewById(R.id.edFull4);
        edFull4 = findViewById(R.id.edFull4);
        edArmHole1 = findViewById(R.id.edArmhole1);
        edArmHole2 = findViewById(R.id.edArmhole2);
        edArmRound1 = findViewById(R.id.edArmRound1);
        edNeckF = findViewById(R.id.edNeckF);
        edNeckB = findViewById(R.id.edNeckB);
        edNeckW = findViewById(R.id.edNeckW);


        buttonSave = findViewById(R.id.btnSave_b);
        toolbar = findViewById(R.id.toolbar);
        toolbar_title = findViewById(R.id.toolbar_title);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back);

        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        }


        // TODO: 15/02/19 getData() getting data wen loading itself... (if auto id is available)
        //        getData()

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                storeData();
//        if (autoId.equalsIgnoreCase("0"))
//            saveMeasurement();
//        else
//            updateMeasurement();

            }
        });
    }


    private void storeData() {
        measurementDataType = new CustomerMeasurementBlouseData();

        // Saving data from the form to the variable
        measurementDataType.setLength(edLength.getText().toString().trim());
        measurementDataType.setShoulder(edShoulder.getText().toString().trim());
        measurementDataType.setF1(edF1.getText().toString().trim());
        measurementDataType.setF2(edF2.getText().toString().trim());
        measurementDataType.setChest1(edChest1.getText().toString().trim());
        measurementDataType.setChest2(edChest2.getText().toString().trim());
        measurementDataType.setChest3(edChest3.getText().toString().trim());
        measurementDataType.setPoint1(edPoint1.getText().toString().trim());
        measurementDataType.setPoint2(edPoint2.getText().toString().trim());
        measurementDataType.setSleeve1(edSleeve1.getText().toString().trim());
        measurementDataType.setSleeve2(edSleeve2.getText().toString().trim());
        measurementDataType.setQtr1(edQtr1.getText().toString().trim());
        measurementDataType.setQtr2(edQtr2.getText().toString().trim());
        measurementDataType.setQtr3(edQtr3.getText().toString().trim());
        measurementDataType.setQtr4(edQtr4.getText().toString().trim());
        measurementDataType.setFull1(edFull1.getText().toString().trim());
        measurementDataType.setFull2(edFull2.getText().toString().trim());
        measurementDataType.setFull3(edFull3.getText().toString().trim());
        measurementDataType.setFull4(edFull4.getText().toString().trim());
        measurementDataType.setArmHole1(edArmHole1.getText().toString().trim());
        measurementDataType.setArmHole2(edArmHole2.getText().toString().trim());
        measurementDataType.setArmRound1(edArmRound1.getText().toString().trim());
        measurementDataType.setNeckF(edNeckF.getText().toString().trim());
        measurementDataType.setNeckB(edNeckB.getText().toString().trim());
        measurementDataType.setNeckW(edNeckW.getText().toString().trim());

    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}
