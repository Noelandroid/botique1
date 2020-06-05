package com.theresa.boutique;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.theresa.boutique.model.CustomerMeasurementFrockNGownData;

import ru.katso.livebutton.LiveButton;

public class CustomerMeasurementFrockNGownActivity extends AppCompatActivity {
    private String custId;
    private Toolbar toolbar;
    private TextView toolbar_title;
    private LiveButton buttonSave;
    private CustomerMeasurementFrockNGownData measurementDataType = new CustomerMeasurementFrockNGownData();

    private EditText edFrockLengthHalf, edFrockLengthLong, edLengthThreeFourth1, edShoulder, edShoulderF1, edShoulderF2, edYokeH, edYokeL,
            edChest1, edChest2, edChest3, edPoint1, edPoint2 , edShape, edSleeve1, edSleeve2, edFull1, edSubSleeve1, edSubSleeve2, edSubSleeve3,
            edQtr1, edQtr2, edQtr3, edQtr4, edArmHole1, edArmHole2, edArmRound1, edArmRound2, edNeckF, edNeckB, edNeckW, edNeckSquare,
            edFlair, edSkirtWaistR, edSkirtHip;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_measurement_frock_ngown);
        initView();
    }

    private void initView() {
        edFrockLengthHalf = findViewById(R.id.edFrockLengthHalf);
        edFrockLengthLong = findViewById(R.id.edFrockLengthLong );
        edLengthThreeFourth1 = findViewById(R.id.edLengthThreeFourth1);
        edShoulder = findViewById(R.id.edShoulder);
        edShoulderF1 = findViewById(R.id.edF1);
        edShoulderF2 = findViewById(R.id.edF2);
        edYokeH = findViewById(R.id.edYokeH);
        edYokeL = findViewById(R.id.edYokeL);
        edChest1 = findViewById(R.id.edChest1);
        edChest2 = findViewById(R.id.edChest2);
        edChest3 = findViewById(R.id.edChest3);
        edPoint1 = findViewById(R.id.edPoint1);
        edPoint2 = findViewById(R.id.etPoint2);
        edShape = findViewById(R.id.edShape);
        edSleeve1 = findViewById(R.id.edSleeve1);
        edSleeve2 = findViewById(R.id.edSleeve2);
        edFull1 = findViewById(R.id.edFull1);
        edSubSleeve1 = findViewById(R.id.edSubSleeve1);
        edSubSleeve2 = findViewById(R.id.edSubSleeve2);
        edSubSleeve3 = findViewById(R.id.edSubSleeve3);
        edQtr1 = findViewById(R.id.edQtr1);
        edQtr2 = findViewById(R.id.edQtr2);
        edQtr3 = findViewById(R.id.edQtr3);
        edQtr4 = findViewById(R.id.edQtr4);
        edArmHole1 = findViewById(R.id.edArmhole1);
        edArmHole2 = findViewById(R.id.edArmhole2);
        edArmRound1 = findViewById(R.id.edArmRound1);
        edArmRound2 = findViewById(R.id.edArmRound2);
        edNeckF = findViewById(R.id.edNeckF);
        edNeckB = findViewById(R.id.edNeckB);
        edNeckW = findViewById(R.id.edNeckW);
        edNeckSquare = findViewById(R.id.edNeckSquare);
        edFlair = findViewById(R.id.edFlair);
        edSkirtWaistR = findViewById(R.id.edSkirtWaistR);
        edSkirtHip = findViewById(R.id.edSkirtHip);


        buttonSave = findViewById(R.id.btnSave_f);
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

        measurementDataType = new CustomerMeasurementFrockNGownData();

        measurementDataType.setFrockLengthHalf(edFrockLengthHalf.getText().toString().trim());
        measurementDataType.setFrockLengthLong(edFrockLengthLong.getText().toString().trim());
        measurementDataType.setLengthThreeFourth1(edLengthThreeFourth1.getText().toString().trim());
        measurementDataType.setShoulder(edShoulder.getText().toString().trim());
        measurementDataType.setShoulderF1(edShoulderF1.getText().toString().trim());
        measurementDataType.setShoulderF2(edShoulderF2.getText().toString().trim());
        measurementDataType.setYokeH(edYokeH.getText().toString().trim());
        measurementDataType.setYokeL(edYokeL.getText().toString().trim());
        measurementDataType.setChest1(edChest1.getText().toString().trim());
        measurementDataType.setChest2(edChest2.getText().toString().trim());
        measurementDataType.setChest3(edChest3.getText().toString().trim());
        measurementDataType.setPoint1(edPoint1.getText().toString().trim());
        measurementDataType.setPoint2(edPoint2.getText().toString().trim());
        measurementDataType.setShape(edShape.getText().toString().trim());
        measurementDataType.setSleeve1(edSleeve1.getText().toString().trim());
        measurementDataType.setSleeve2(edSleeve2.getText().toString().trim());
        measurementDataType.setSleevesFull(edFull1.getText().toString().trim());
        measurementDataType.setSubSleeve1(edSubSleeve1.getText().toString().trim());
        measurementDataType.setSubSleeve2(edSubSleeve2.getText().toString().trim());
        measurementDataType.setSubSleeve3(edSubSleeve3.getText().toString().trim());
        measurementDataType.setQtr1(edQtr1.getText().toString().trim());
        measurementDataType.setQtr2(edQtr2.getText().toString().trim());
        measurementDataType.setQtr3(edQtr3.getText().toString().trim());
        measurementDataType.setQtr4(edQtr4.getText().toString().trim());
        measurementDataType.setArmHole1(edArmHole1.getText().toString().trim());
        measurementDataType.setArmHole2(edArmHole2.getText().toString().trim());
        measurementDataType.setArmRound1(edArmRound1.getText().toString().trim());
        measurementDataType.setArmRound2(edArmRound2.getText().toString().trim());
        measurementDataType.setNeckF(edNeckF.getText().toString().trim());
        measurementDataType.setNeckB(edNeckB.getText().toString().trim());
        measurementDataType.setNeckW(edNeckW.getText().toString().trim());
        measurementDataType.setNeckSquare(edNeckSquare.getText().toString().trim());
        measurementDataType.setFlair(edFlair.getText().toString().trim());
        measurementDataType.setSkirtWaistR(edSkirtWaistR.getText().toString().trim());
        measurementDataType.setSkirtHip(edSkirtHip.getText().toString().trim());

    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}
