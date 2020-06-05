package com.theresa.boutique.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.CardView;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.WithHint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.theresa.boutique.R;
import com.theresa.boutique.adapter.SpinnerDesignerAdapter;
import com.theresa.boutique.interfaces.ViewPagerInterface;
import com.theresa.boutique.model.DesignerItem;
import com.theresa.boutique.model.M2Data;

import java.util.ArrayList;

import ru.katso.livebutton.LiveButton;

public class M2Fragment extends Fragment {

    EditText etTemplateNo/*, etTassel, etLock, etFall, etPiping*/;
    TextView tvLabelPad, tvLabelFlair, tvLabelZip, tvLabelTuck, tvLabelPleat, tvLabelTransparent, /*tvLabelTassel, tvLabelLock, tvLabelFall, tvLabelEditPiping,*/
            tvLabelFlairPiping;
    AppCompatSpinner spinDesigner;
    SwitchCompat switchPad, switchZip, switchTuck, switchLining, switchSlit, switchSide, switchFront, switchOpen, switchPanel, switchUmbrella, switchHanging, switchTunics, switchTassels, switchFall, switchLock, switchEditPiping, switchStraight;
    LiveButton btnNext;
    RadioGroup rgSleevePiping, rgSleeveUnKnown, rgNeckPiping, rgNeckUnknown, /*rgFlairUnknown,*/
            rgFlairPiping, rgBottomPiping, rgBottomUnknown, /*rgPocket,*/
            rgZipYes, rgYKKNo, rgUmbrella, rgSCWS, rgFrontSlit, rgFrockType, rgPleat;
    RadioButton rbSpp, rbDpp;
    LinearLayout llTuckYes, llYKKYes, llHidingYes, llOpenYes, llFlairUnknown, llSlit, llSide, llFront, llOpen, llPanel, llUmbrella, llHanging, llTunic, llTransparent, llTassels, llFall, llLock,llBorder, llEditPiping, llStraight;
    AppCompatCheckBox checkYKKBack, checkYKKFront, checkHidingBack, checkHidingFront, checkHidingSide, checkYKKSide, checkTuckFront, checkTuckArmHole, checkBorder, checkWorks, checkPiping, checkLace, checkUmbrella, checkPanel, /*checkStraight,*/ checkPocket, checkElastic, checkPressButton, checkOpenFront, checkOpenBack, checkTransFront, checkTransBack;
    SpinnerDesignerAdapter mSpinnerDesignerAdapter;
    CardView cvTemplate, cvSleeve, cvNeck, cvFlair, cvBottom, cvExtra;

    ViewPagerInterface mListener;
    ArrayList<DesignerItem> listDesigner = new ArrayList<>();

    String isPad = "0", isZip = "0", isTuck = "0", isLining = "0", isSlit = "0", isSide = "0", isFront = "0", liningValM2 = "", isOpen = "0", isPanel = "0", isSkirtUmbrella = "0", isHanging = "0", isTunic = "0", isFall = "0", isLock = "0", isTassel = "0", isEditPiping = "0", isStraight = "0";
    String mType = "";

    int flairPipeId = 0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return init(container);
    }

    private View init(ViewGroup container) {
        isPad = "0";
        isZip = "0";
        isTuck = "0";
        isLining = "0";
        isSlit = "0";
        isSide = "0";
        isFront = "0";
        liningValM2 = "";
        isOpen = "0";
        isPanel = "0";
        isSkirtUmbrella = "0";
        isHanging = "0";
        isTunic = "0";
        isFall = "0";
        isLock = "0";
        isTassel = "0";
        isEditPiping = "0";
        isStraight = "0";
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragmentm2, container, false);
        mListener = (ViewPagerInterface) getActivity();
        tvLabelPad = (TextView) view.findViewById(R.id.tvLabelPad);
        tvLabelZip = (TextView) view.findViewById(R.id.tvLabelZip);
        tvLabelFlair = (TextView) view.findViewById(R.id.tvLabelFlair);
        tvLabelFlairPiping = (TextView) view.findViewById(R.id.tvLabelFlairPiping);
        tvLabelTuck = (TextView) view.findViewById(R.id.tvLabelTuck);
        tvLabelPleat = (TextView) view.findViewById(R.id.tvLabelPleat);
        tvLabelTransparent = (TextView) view.findViewById(R.id.tvLabelTransparent);
        llTassels = (LinearLayout) view.findViewById(R.id.llTassels);
        llLock = (LinearLayout) view.findViewById(R.id.llLock);
        llFall = (LinearLayout) view.findViewById(R.id.llFall);
        llEditPiping = (LinearLayout) view.findViewById(R.id.llEditPiping);
        llStraight = (LinearLayout) view.findViewById(R.id.llStraight);
        llBorder = (LinearLayout) view.findViewById(R.id.llBorder);
        etTemplateNo = (EditText) view.findViewById(R.id.etTemplateNo);
        switchTassels = (SwitchCompat) view.findViewById(R.id.switchTassels);
        switchLock = (SwitchCompat) view.findViewById(R.id.switchLock);
        switchFall = (SwitchCompat) view.findViewById(R.id.switchFall);
        switchEditPiping = (SwitchCompat) view.findViewById(R.id.switchEditPiping);
        switchStraight = (SwitchCompat) view.findViewById(R.id.switchStraight);
        cvBottom = (CardView) view.findViewById(R.id.cvBottom);
        cvTemplate = (CardView) view.findViewById(R.id.cvTemplate);
        cvSleeve = (CardView) view.findViewById(R.id.cvSleeve);
        cvNeck = (CardView) view.findViewById(R.id.cvNeck);
        cvFlair = (CardView) view.findViewById(R.id.cvFlair);
        cvExtra = (CardView) view.findViewById(R.id.cvExtra);


        spinDesigner = (AppCompatSpinner) view.findViewById(R.id.spinDesigner);
        btnNext = (LiveButton) view.findViewById(R.id.btnNext);
        switchZip = (SwitchCompat) view.findViewById(R.id.switchZip);
        switchTuck = (SwitchCompat) view.findViewById(R.id.switchTuck);
        switchLining = (SwitchCompat) view.findViewById(R.id.switchLining);
        switchSlit = (SwitchCompat) view.findViewById(R.id.switchSlit);
        switchSide = (SwitchCompat) view.findViewById(R.id.switchSide);
        switchFront = (SwitchCompat) view.findViewById(R.id.switchFront);

        switchOpen = (SwitchCompat) view.findViewById(R.id.switchOpen);
        switchPanel = (SwitchCompat) view.findViewById(R.id.switchPanel);
        switchUmbrella = (SwitchCompat) view.findViewById(R.id.switchUmbrella);
        switchHanging = (SwitchCompat) view.findViewById(R.id.switchHanging);
        switchTunics = (SwitchCompat) view.findViewById(R.id.switchTunic);
        switchPad = (SwitchCompat) view.findViewById(R.id.switchPad);

        llOpenYes = (LinearLayout) view.findViewById(R.id.llOpenYes);
        llFlairUnknown = (LinearLayout) view.findViewById(R.id.llFlairUnknown);
        llSlit = (LinearLayout) view.findViewById(R.id.llSlit);
        llSide = (LinearLayout) view.findViewById(R.id.llSide);
        llFront = (LinearLayout) view.findViewById(R.id.llFront);
        llOpen = (LinearLayout) view.findViewById(R.id.llOpen);
        llPanel = (LinearLayout) view.findViewById(R.id.llPanel);
        llUmbrella = (LinearLayout) view.findViewById(R.id.llUmbrella);
        llHanging = (LinearLayout) view.findViewById(R.id.llHanging);
        llTunic = (LinearLayout) view.findViewById(R.id.llTunic);
        llTransparent = (LinearLayout) view.findViewById(R.id.llTransparent);

        rgSleevePiping = (RadioGroup) view.findViewById(R.id.rgSleevePiping);
        rgSleeveUnKnown = (RadioGroup) view.findViewById(R.id.rgSleeveUnknown);
        rgNeckPiping = (RadioGroup) view.findViewById(R.id.rgNeckPiping);
        rgNeckUnknown = (RadioGroup) view.findViewById(R.id.rgNeckUnknown);
//        rgFlairUnknown = (RadioGroup) view.findViewById(R.id.rgFlairUnknown);
        rgFlairPiping = (RadioGroup) view.findViewById(R.id.rgFlairPiping);
        rbSpp = (RadioButton) view.findViewById(R.id.rbFlairSPP);
        rbDpp = (RadioButton) view.findViewById(R.id.rbFlairDPP);
        rgBottomPiping = (RadioGroup) view.findViewById(R.id.rgBottomPiping);
        rgBottomUnknown = (RadioGroup) view.findViewById(R.id.rgBottomUnknown);

        rgPleat = (RadioGroup) view.findViewById(R.id.rgPleat);
        rgFrockType = (RadioGroup) view.findViewById(R.id.rgFrockType);
        /*rgPocket = (RadioGroup) view.findViewById(R.id.rgPocket);*/

        rgZipYes = (RadioGroup) view.findViewById(R.id.rgZipYes);
        llTuckYes = (LinearLayout) view.findViewById(R.id.llTuckYes);
        llYKKYes = (LinearLayout) view.findViewById(R.id.llYKKYes);
        llHidingYes = (LinearLayout) view.findViewById(R.id.llHidingYes);
        rgYKKNo = (RadioGroup) view.findViewById(R.id.rgYKKNo);


        checkTuckArmHole = (AppCompatCheckBox) view.findViewById(R.id.checkTuckArmHole);
        checkTuckFront = (AppCompatCheckBox) view.findViewById(R.id.checkTuckFront);
        checkYKKBack = (AppCompatCheckBox) view.findViewById(R.id.checkYKKBack);
        checkYKKFront = (AppCompatCheckBox) view.findViewById(R.id.checkYKKFront);
        checkYKKSide = (AppCompatCheckBox) view.findViewById(R.id.checkYKKSide);
        checkHidingBack = (AppCompatCheckBox) view.findViewById(R.id.checkHidingBack);
        checkHidingFront = (AppCompatCheckBox) view.findViewById(R.id.checkHidingFront);
        checkHidingSide = (AppCompatCheckBox) view.findViewById(R.id.checkHidingSide);

        checkPocket = (AppCompatCheckBox) view.findViewById(R.id.checkPocket);
        checkElastic = (AppCompatCheckBox) view.findViewById(R.id.checkElastic);
        checkPressButton = (AppCompatCheckBox) view.findViewById(R.id.checkPressButton);

        checkBorder = (AppCompatCheckBox) view.findViewById(R.id.checkBorder);
        checkWorks = (AppCompatCheckBox) view.findViewById(R.id.checkWorks);
        checkPiping = (AppCompatCheckBox) view.findViewById(R.id.checkPiping);
        checkLace = (AppCompatCheckBox) view.findViewById(R.id.checkLace);
        checkUmbrella = (AppCompatCheckBox) view.findViewById(R.id.checkUmbrella);
        checkPanel = (AppCompatCheckBox) view.findViewById(R.id.checkPanel);
//        checkStraight = (AppCompatCheckBox) view.findViewById(R.id.checkStraight);

        checkOpenFront = (AppCompatCheckBox) view.findViewById(R.id.checkOpenFront);
        checkOpenBack = (AppCompatCheckBox) view.findViewById(R.id.checkOpenBack);
        checkTransFront = (AppCompatCheckBox) view.findViewById(R.id.checkTransFront);
        checkTransBack = (AppCompatCheckBox) view.findViewById(R.id.checkTransBack);

        rgUmbrella = (RadioGroup) view.findViewById(R.id.rgUmbrella);
        rgSCWS = (RadioGroup) view.findViewById(R.id.rgSCWS);
        rgFrontSlit = (RadioGroup) view.findViewById(R.id.rgFrontSlit);

        if (mType.toLowerCase().contains("churidar")) {
            tvLabelPad.setVisibility(View.GONE);
            switchPad.setVisibility(View.GONE);
//            cvExtra.setVisibility(View.GONE);
            tvLabelPleat.setVisibility(View.GONE);
            rgPleat.setVisibility(View.GONE);
            llOpen.setVisibility(View.GONE);
            llUmbrella.setVisibility(View.GONE);
            llHanging.setVisibility(View.GONE);
            llTunic.setVisibility(View.GONE);
            rgFrockType.setVisibility(View.GONE);
            llTassels.setVisibility(View.GONE);
            llFall.setVisibility(View.GONE);
            llLock.setVisibility(View.GONE);
            llEditPiping.setVisibility(View.GONE);
            llBorder.setVisibility(View.GONE);
        } else if (mType.toLowerCase().contains("anarkali")) {
            tvLabelFlair.setVisibility(View.GONE);
            llFlairUnknown.setVisibility(View.GONE);
            tvLabelFlairPiping.setVisibility(View.GONE);
            rgFlairPiping.setVisibility(View.GONE);
            llSide.setVisibility(View.GONE);
            llFront.setVisibility(View.GONE);

            tvLabelPleat.setVisibility(View.GONE);
            rgPleat.setVisibility(View.GONE);
            llOpen.setVisibility(View.GONE);
            llUmbrella.setVisibility(View.GONE);
            llHanging.setVisibility(View.GONE);
            llTunic.setVisibility(View.GONE);

            rgFrockType.setVisibility(View.GONE);
            tvLabelTransparent.setVisibility(View.GONE);
            llTransparent.setVisibility(View.GONE);
            llTassels.setVisibility(View.GONE);
            llFall.setVisibility(View.GONE);
            llLock.setVisibility(View.GONE);
            llEditPiping.setVisibility(View.GONE);
//            cvNeck.setVisibility(View.GONE);
            cvBottom.setVisibility(View.GONE);
        } else if (mType.toLowerCase().contains("blouse")) {
            tvLabelTuck.setVisibility(View.GONE);
            switchTuck.setVisibility(View.GONE);
            tvLabelPleat.setVisibility(View.GONE);
            rgPleat.setVisibility(View.GONE);
            llPanel.setVisibility(View.GONE);
            llUmbrella.setVisibility(View.GONE);
//            llHanging.setVisibility(View.GONE);
            llTunic.setVisibility(View.GONE);
            rgFrockType.setVisibility(View.GONE);
            tvLabelTransparent.setVisibility(View.GONE);
            llTransparent.setVisibility(View.GONE);
            llTassels.setVisibility(View.GONE);
            llFall.setVisibility(View.GONE);
            llLock.setVisibility(View.GONE);
            llEditPiping.setVisibility(View.GONE);
            cvFlair.setVisibility(View.GONE);
            cvBottom.setVisibility(View.GONE);
        }
        else if (mType.toLowerCase().contains("weddingblouse")) {
            tvLabelTuck.setVisibility(View.GONE);
            switchTuck.setVisibility(View.GONE);
            tvLabelPleat.setVisibility(View.GONE);
            rgPleat.setVisibility(View.GONE);
            llPanel.setVisibility(View.GONE);
            llUmbrella.setVisibility(View.GONE);
//            llHanging.setVisibility(View.GONE);
            llTunic.setVisibility(View.GONE);
            rgFrockType.setVisibility(View.GONE);
            tvLabelTransparent.setVisibility(View.GONE);
            llTransparent.setVisibility(View.GONE);
            llTassels.setVisibility(View.GONE);
            llFall.setVisibility(View.GONE);
            llLock.setVisibility(View.GONE);
            llEditPiping.setVisibility(View.GONE);
            cvFlair.setVisibility(View.GONE);
            cvBottom.setVisibility(View.GONE);
        }




        else if (mType.toLowerCase().contains("shawl")) {
            cvBottom.setVisibility(View.GONE);
            cvFlair.setVisibility(View.GONE);
            cvNeck.setVisibility(View.GONE);
            cvSleeve.setVisibility(View.GONE);
            cvTemplate.setVisibility(View.GONE);
            tvLabelPleat.setVisibility(View.GONE);
            rgPleat.setVisibility(View.GONE);
            llOpen.setVisibility(View.GONE);
//            llPanel.setVisibility(View.GONE);
            llUmbrella.setVisibility(View.GONE);
            llHanging.setVisibility(View.GONE);
            llTunic.setVisibility(View.GONE);
            llFall.setVisibility(View.GONE);
            /*llLock.setVisibility(View.GONE);
            llEditPiping.setVisibility(View.GONE);*/
            rgFrockType.setVisibility(View.GONE);
            tvLabelTransparent.setVisibility(View.GONE);
            llTransparent.setVisibility(View.GONE);
        }
        else if (mType.toLowerCase().contains("weddingnet")) {
            cvBottom.setVisibility(View.GONE);
            cvFlair.setVisibility(View.GONE);
            cvNeck.setVisibility(View.GONE);
            cvSleeve.setVisibility(View.GONE);
            cvTemplate.setVisibility(View.GONE);
            tvLabelPleat.setVisibility(View.GONE);
            rgPleat.setVisibility(View.GONE);
            llOpen.setVisibility(View.GONE);
//            llPanel.setVisibility(View.GONE);
            llUmbrella.setVisibility(View.GONE);
            llHanging.setVisibility(View.GONE);
            llTunic.setVisibility(View.GONE);
            llFall.setVisibility(View.GONE);
            /*llLock.setVisibility(View.GONE);
            llEditPiping.setVisibility(View.GONE);*/
            rgFrockType.setVisibility(View.GONE);
            tvLabelTransparent.setVisibility(View.GONE);
            llTransparent.setVisibility(View.GONE);
        }









        else if (mType.toLowerCase().contains("saree")) {
            cvBottom.setVisibility(View.GONE);
            cvFlair.setVisibility(View.GONE);
            cvNeck.setVisibility(View.GONE);
            cvSleeve.setVisibility(View.GONE);
            tvLabelTuck.setVisibility(View.GONE);
            tvLabelZip.setVisibility(View.GONE);
            tvLabelPad.setVisibility(View.GONE);
            switchTuck.setVisibility(View.GONE);
            switchZip.setVisibility(View.GONE);
            switchPad.setVisibility(View.GONE);
            tvLabelPleat.setVisibility(View.GONE);
            rgPleat.setVisibility(View.GONE);
            llPanel.setVisibility(View.GONE);
            llOpen.setVisibility(View.GONE);
            llUmbrella.setVisibility(View.GONE);
            llHanging.setVisibility(View.GONE);
            llTunic.setVisibility(View.GONE);
//            llLock.setVisibility(View.GONE);
            rgFrockType.setVisibility(View.GONE);
            tvLabelTransparent.setVisibility(View.GONE);
            llTransparent.setVisibility(View.GONE);
        } else if (mType.toLowerCase().contains("frock")) {
            tvLabelTuck.setVisibility(View.GONE);
            switchTuck.setVisibility(View.GONE);
            cvFlair.setVisibility(View.GONE);
            cvBottom.setVisibility(View.GONE);
            llOpen.setVisibility(View.GONE);
            llPanel.setVisibility(View.GONE);
            llUmbrella.setVisibility(View.GONE);
            llHanging.setVisibility(View.GONE);
            llTassels.setVisibility(View.GONE);
            llFall.setVisibility(View.GONE);
            llLock.setVisibility(View.GONE);
            llEditPiping.setVisibility(View.GONE);
        } else if (mType.toLowerCase().contains("top/skirt")) {
            tvLabelFlairPiping.setVisibility(View.GONE);
            rgFlairPiping.setVisibility(View.GONE);
            tvLabelTuck.setVisibility(View.GONE);
            switchTuck.setVisibility(View.GONE);
            cvBottom.setVisibility(View.GONE);
            tvLabelPleat.setVisibility(View.GONE);
            rgPleat.setVisibility(View.GONE);
            llTunic.setVisibility(View.GONE);
            rgFrockType.setVisibility(View.GONE);
            tvLabelTransparent.setVisibility(View.GONE);
            llTransparent.setVisibility(View.GONE);
            llTassels.setVisibility(View.GONE);
            llFall.setVisibility(View.GONE);
            llLock.setVisibility(View.GONE);
            llEditPiping.setVisibility(View.GONE);
            llStraight.setVisibility(View.VISIBLE);
            llOpen.setVisibility(View.GONE);
            rgUmbrella.setVisibility(View.GONE);
            rgUmbrella.clearCheck();
            rgSCWS.setVisibility(View.GONE);
            rgSCWS.clearCheck();
            rgFrontSlit.setVisibility(View.GONE);
            rgFrontSlit.clearCheck();
            llSlit.setVisibility(View.GONE);
            llSide.setVisibility(View.GONE);
            llFront.setVisibility(View.GONE);
        } else if (mType.toLowerCase().contains("gown")) {
            cvBottom.setVisibility(View.GONE);

            llOpen.setVisibility(View.GONE);
            llUmbrella.setVisibility(View.GONE);
            llHanging.setVisibility(View.GONE);
            llTunic.setVisibility(View.GONE);
            rgFrockType.setVisibility(View.GONE);
            tvLabelTransparent.setVisibility(View.GONE);
            llTransparent.setVisibility(View.GONE);
            llTassels.setVisibility(View.GONE);
            llFall.setVisibility(View.GONE);
            llLock.setVisibility(View.GONE);
            llEditPiping.setVisibility(View.GONE);
            tvLabelTuck.setVisibility(View.GONE);
            switchTuck.setVisibility(View.GONE);
        } else if (mType.toLowerCase().contains("overcoat")) {
            cvBottom.setVisibility(View.GONE);
            cvFlair.setVisibility(View.GONE);
            tvLabelTuck.setVisibility(View.GONE);
            tvLabelZip.setVisibility(View.GONE);
            tvLabelPad.setVisibility(View.GONE);
            switchTuck.setVisibility(View.GONE);
            switchZip.setVisibility(View.GONE);
            switchPad.setVisibility(View.GONE);
            tvLabelPleat.setVisibility(View.GONE);
            rgPleat.setVisibility(View.GONE);
            llPanel.setVisibility(View.GONE);
            llUmbrella.setVisibility(View.GONE);
            llHanging.setVisibility(View.GONE);
            llTunic.setVisibility(View.GONE);
            rgFrockType.setVisibility(View.GONE);
            tvLabelTransparent.setVisibility(View.GONE);
            llTransparent.setVisibility(View.GONE);
            llTassels.setVisibility(View.GONE);
            llFall.setVisibility(View.GONE);
            llLock.setVisibility(View.GONE);
            llEditPiping.setVisibility(View.GONE);
        }

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onNextClicked();
            }
        });


        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        switchPad.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    isPad = "1";
                } else {
                    isPad = "0";
                }
            }
        });

        switchZip.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Log.e("SwitchZip", "onCheckListener");
                if (isChecked) {
                    isZip = "1";
                    rgZipYes.setVisibility(View.VISIBLE);
                } else {
                    isZip = "0";
                    rgZipYes.setVisibility(View.GONE);
                    rgZipYes.clearCheck();
                    llYKKYes.setVisibility(View.GONE);
                    checkYKKBack.setChecked(false);
                    checkYKKFront.setChecked(false);
                    checkYKKSide.setChecked(false);
                    rgYKKNo.setVisibility(View.GONE);
                    rgYKKNo.clearCheck();
                    llHidingYes.setVisibility(View.GONE);
                    checkHidingBack.setChecked(false);
                    checkHidingFront.setChecked(false);
                    checkHidingSide.setChecked(false);
                }
            }
        });

        switchTuck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    isTuck = "1";
                    llTuckYes.setVisibility(View.VISIBLE);
                } else {
                    isTuck = "0";
                    llTuckYes.setVisibility(View.GONE);
                }
            }
        });

        switchOpen.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    isOpen = "1";
                    llOpenYes.setVisibility(View.VISIBLE);
                } else {
                    isOpen = "0";
                    llOpenYes.setVisibility(View.GONE);
                }
            }
        });

        switchPanel.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    isPanel = "1";
                } else {
                    isPanel = "0";
                }
            }
        });
        switchUmbrella.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    isSkirtUmbrella = "1";
                } else {
                    isSkirtUmbrella = "0";
                }
            }
        });
        switchHanging.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    isHanging = "1";
                } else {
                    isHanging = "0";
                }
            }
        });
        switchTunics.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    isTunic = "1";
                } else {
                    isTunic = "0";
                }
            }
        });

        switchLining.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    isLining = "1";
                    liningValM2 = "Yes";
                } else {
                    isLining = "0";
                    liningValM2 = "No";
                }
            }
        });

        switchSlit.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    isSlit = "1";
                    rgUmbrella.setVisibility(View.GONE);
                    rgUmbrella.clearCheck();
                    rgSCWS.setVisibility(View.GONE);
                    rgSCWS.clearCheck();
                    rgFrontSlit.setVisibility(View.GONE);
                    rgFrontSlit.clearCheck();
                } else {
                    isSlit = "0";
                    rgUmbrella.setVisibility(View.VISIBLE);
                    rgSCWS.setVisibility(View.VISIBLE);
                }
            }
        });

        switchSide.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    isSide = "1";
                } else {
                    isSide = "0";
                }
            }
        });

        switchFront.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    isFront = "1";
                } else {
                    isFront = "0";
                }
            }
        });

        switchTassels.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    isTassel = "1";
                } else {
                    isTassel = "0";
                }
            }
        });

        switchLock.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    isLock = "1";
                } else {
                    isLock = "0";
                }
            }
        });

        switchFall.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    isFall = "1";
                } else {
                    isFall = "0";
                }
            }
        });

        switchEditPiping.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    isEditPiping = "1";
                } else {
                    isEditPiping = "0";
                }
            }
        });

        switchStraight.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    isStraight = "1";
                } else {
                    isStraight = "0";
                }
            }
        });

        rgZipYes.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rbZipYKK:
                        llYKKYes.setVisibility(View.VISIBLE);
                        rgYKKNo.setVisibility(View.GONE);
                        llHidingYes.setVisibility(View.GONE);
                        checkHidingBack.setChecked(false);
                        checkHidingFront.setChecked(false);
                        checkHidingSide.setChecked(false);
                        break;
                    case R.id.rbZipNo:
                        llYKKYes.setVisibility(View.GONE);
                        rgYKKNo.setVisibility(View.VISIBLE);
                        break;
                }
            }
        });

        rgYKKNo.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rbZipHiding:
                    case R.id.rbZipNormal:
                        llHidingYes.setVisibility(View.VISIBLE);
                        checkHidingBack.setChecked(false);
                        checkHidingFront.setChecked(false);
                        checkHidingSide.setChecked(false);
                        break;

                }
            }
        });


        rgSCWS.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rbSCWSYes:
                        rgFrontSlit.setVisibility(View.VISIBLE);
                        break;
                    case R.id.rbSCWSNo:
                        rgFrontSlit.setVisibility(View.GONE);
                        break;
                }
            }
        });

    }

    public String pad() {
        return isPad;
    }

    public String pleat() {
        if (rgPleat.getCheckedRadioButtonId() != -1) {
            int id = rgPleat.getCheckedRadioButtonId();
            View radioButton = rgPleat.findViewById(id);
            int radioId = rgPleat.indexOfChild(radioButton);
            RadioButton btn = (RadioButton) rgPleat.getChildAt(radioId);
            String selection = (String) btn.getText();
            if (selection.equalsIgnoreCase("none"))
                selection = "";
            return selection;
        }
        return "";
    }

    public String typeOfFrock() {
        if (rgFrockType.getCheckedRadioButtonId() != -1) {
            int id = rgFrockType.getCheckedRadioButtonId();
            View radioButton = rgFrockType.findViewById(id);
            int radioId = rgFrockType.indexOfChild(radioButton);
            RadioButton btn = (RadioButton) rgFrockType.getChildAt(radioId);
            String selection = (String) btn.getText();
            if (selection.equalsIgnoreCase("none"))
                selection = "";
            return selection;
        }
        return "";
    }

    public String tungies() {
        return isTunic;
    }

    public String transparent() {
        String value = "";

        if (checkTransFront.isChecked()) {
            value = "front";
            if (checkTransBack.isChecked()) {
                value = value + ",back";
            }
        } else if (checkTransBack.isChecked()) {
            value = "back";
        } else {
            value = "";
        }

        return value;
    }

    public String panel() {
        return isPanel;
    }

    public String fall() {
        return isFall;
    }

    public String lock() {
        return isLock;
    }

    public String lockPiping() {
        return isEditPiping;
    }

    public String straight() {
        return isStraight;
    }

    public String skirtUmbrella() {
        return isSkirtUmbrella;
    }

    public String hangings() {
        return isHanging;
    }

    public String open() {
        String value = "";
        if (llOpenYes.getVisibility() == View.VISIBLE) {
            if (checkOpenFront.isChecked()) {
                value = "front";
                if (checkOpenBack.isChecked()) {
                    value = value + ",back";
                }
            } else if (checkOpenBack.isChecked()) {
                value = "back";
            } else {
                value = "";
            }
        }
        return value;
//        return isOpen;
    }

    public String hiding() {
        String value = "";
        if (llHidingYes.getVisibility() == View.VISIBLE && ykkNoVal().equalsIgnoreCase("hiding")) {
            if (checkHidingFront.isChecked()) {
                value = "front";
                if (checkHidingBack.isChecked()) {
                    value = value + ",back";
                    if (checkHidingSide.isChecked()) {
                        value = value + ",side";
                    }
                } else if (checkHidingSide.isChecked()) {
                    value = value + ",side";
                }
            } else if (checkHidingBack.isChecked()) {
                value = "back";
                if (checkHidingSide.isChecked()) {
                    value = value + ",side";
                }
            } else if (checkHidingSide.isChecked()) {
                value = "side";
            } else {
                value = "";
            }
        }
        return value;
    }

    public String normal() {
        String value = "";
        if (llHidingYes.getVisibility() == View.VISIBLE && ykkNoVal().equalsIgnoreCase("normal")) {
            if (checkHidingFront.isChecked()) {
                value = "front";
                if (checkHidingBack.isChecked()) {
                    value = value + ",back";
                    if (checkHidingSide.isChecked()) {
                        value = value + ",side";
                    }
                } else if (checkHidingSide.isChecked()) {
                    value = value + ",side";
                }
            } else if (checkHidingBack.isChecked()) {
                value = "back";
                if (checkHidingSide.isChecked()) {
                    value = value + ",side";
                }
            } else if (checkHidingSide.isChecked()) {
                value = "side";
            } else {
                value = "";
            }
        }
        return value;
    }

    public String tassels() {
        return isTassel;
    }

    public String isZip() {
        Log.e("isZip", isZip);
        return isZip;
    }

    public String ykkVal() {
        if (rgZipYes.getVisibility() == View.VISIBLE)
            if (rgZipYes.getCheckedRadioButtonId() != -1) {
                int id = rgZipYes.getCheckedRadioButtonId();
                View radioButton = rgZipYes.findViewById(id);
                int radioId = rgZipYes.indexOfChild(radioButton);
                RadioButton btn = (RadioButton) rgZipYes.getChildAt(radioId);
                String selection = (String) btn.getText();
                return selection;
            }
        return "";
    }

    public String ykkYesVal() {
        String value = "";
        if (llYKKYes.getVisibility() == View.VISIBLE) {
            if (checkYKKFront.isChecked()) {
                value = "front";
                if (checkYKKBack.isChecked()) {
                    value = value + ",back";
                    if (checkYKKSide.isChecked()) {
                        value = value + ",side";
                    }
                } else if (checkYKKSide.isChecked()) {
                    value = value + ",side";
                }
            } else if (checkYKKBack.isChecked()) {
                value = "back";
                if (checkYKKSide.isChecked()) {
                    value = value + ",side";
                }
            } else if (checkYKKSide.isChecked()) {
                value = "side";
            } else {
                value = "";
            }
        }
        return value;
    }

    public String ykkNoVal() {
        if (rgYKKNo.getVisibility() == View.VISIBLE) {
            if (rgYKKNo.getCheckedRadioButtonId() != -1) {
                int id = rgYKKNo.getCheckedRadioButtonId();
                View radioButton = rgYKKNo.findViewById(id);
                int radioId = rgYKKNo.indexOfChild(radioButton);
                RadioButton btn = (RadioButton) rgYKKNo.getChildAt(radioId);
                String selection = (String) btn.getText();
                return selection;
            }
        }
        return "";
    }

    public String isSlitUmbrella() {
        if (rgUmbrella.getVisibility() == View.VISIBLE) {
            if (rgUmbrella.getCheckedRadioButtonId() != -1) {
                int id = rgUmbrella.getCheckedRadioButtonId();
                View radioButton = rgUmbrella.findViewById(id);
                int radioId = rgUmbrella.indexOfChild(radioButton);
                RadioButton btn = (RadioButton) rgUmbrella.getChildAt(radioId);
                String selection = (String) btn.getText();
                switch (selection) {
                    case "No":
                        return "0";
                    case "Umbrella":
                        return "1";

                }
            }
        }

        return "";
    }

    public String isSlitScws() {
        if (rgSCWS.getVisibility() == View.VISIBLE) {
            if (rgSCWS.getCheckedRadioButtonId() != -1) {
                int id = rgSCWS.getCheckedRadioButtonId();
                View radioButton = rgSCWS.findViewById(id);
                int radioId = rgSCWS.indexOfChild(radioButton);
                RadioButton btn = (RadioButton) rgSCWS.getChildAt(radioId);
                String selection = (String) btn.getText();
                switch (selection) {
                    case "No":
                        return "0";
                    case "SC WS":
                        return "1";

                }
            }
        }

        return "";
    }

    public String isSlitScwsFront() {
        if (rgFrontSlit.getVisibility() == View.VISIBLE) {
            if (rgFrontSlit.getCheckedRadioButtonId() != -1) {
                int id = rgFrontSlit.getCheckedRadioButtonId();
                View radioButton = rgFrontSlit.findViewById(id);
                int radioId = rgFrontSlit.indexOfChild(radioButton);
                RadioButton btn = (RadioButton) rgFrontSlit.getChildAt(radioId);
                String selection = (String) btn.getText();
                switch (selection) {
                    case "No":
                        return "0";
                    case "Front Slit":
                        return "1";

                }
            }
        }

        return "";
    }


    public String designerM2Id() {
        return "1";
    }


    public String isTuck() {
        return isTuck;
    }

    public String isSleeveLiningM2() {
        return isLining;
    }

    public String sleeveLiningValM2() {
        return liningValM2;
    }

    public String sleeveLiningType() {
        if (rgSleeveUnKnown.getCheckedRadioButtonId() != -1) {
            int id = rgSleeveUnKnown.getCheckedRadioButtonId();
            View radioButton = rgSleeveUnKnown.findViewById(id);
            int radioId = rgSleeveUnKnown.indexOfChild(radioButton);
            RadioButton btn = (RadioButton) rgSleeveUnKnown.getChildAt(radioId);
            String selection = (String) btn.getText();
            if (selection.equalsIgnoreCase("none"))
                selection = "";
            return selection;
        }
        return "";
    }

    public String sleevePipingType() {
        if (rgSleevePiping.getCheckedRadioButtonId() != -1) {
            int id = rgSleevePiping.getCheckedRadioButtonId();
            View radioButton = rgSleevePiping.findViewById(id);
            int radioId = rgSleevePiping.indexOfChild(radioButton);
            RadioButton btn = (RadioButton) rgSleevePiping.getChildAt(radioId);
            String selection = (String) btn.getText();
            if (selection.equalsIgnoreCase("none")) {
                selection = "";
            }
            return selection;
        }
        return "";
    }

    public String tuckType() {
        String value = "";
        if (llTuckYes.getVisibility() == View.VISIBLE) {
            if (checkTuckFront.isChecked()) {
                value = "front";
                if (checkTuckArmHole.isChecked()) {
                    value = value + ",arm_hole";
                }
            } else if (checkTuckArmHole.isChecked()) {
                value = "arm_hole";
            } else {
                value = "";
            }
        }
        return value;
    }

    public String neckType() {
        if (rgNeckUnknown.getCheckedRadioButtonId() != -1) {
            int id = rgNeckUnknown.getCheckedRadioButtonId();
            View radioButton = rgNeckUnknown.findViewById(id);
            int radioId = rgNeckUnknown.indexOfChild(radioButton);
            RadioButton btn = (RadioButton) rgNeckUnknown.getChildAt(radioId);
            String selection = (String) btn.getText();
            if (selection.equalsIgnoreCase("none"))
                selection = "";
            return selection;
        }
        return "";
    }

    public String neckPiping() {
        if (rgNeckPiping.getCheckedRadioButtonId() != -1) {
            int id = rgNeckPiping.getCheckedRadioButtonId();
            View radioButton = rgNeckPiping.findViewById(id);
            int radioId = rgNeckPiping.indexOfChild(radioButton);
            RadioButton btn = (RadioButton) rgNeckPiping.getChildAt(radioId);
            String selection = (String) btn.getText();
            if (selection.equalsIgnoreCase("none")) {
                selection = "";
            }
            return selection;
        }
        return "";
    }

    public String flairType() {
        String value;
        if (checkBorder.isChecked()) {
            value = "border";
            if (checkWorks.isChecked()) {
                value = value + ",works";
            }
            if (checkPiping.isChecked()) {
                value = value + ",piping";
            }
            if (checkLace.isChecked()) {
                value = value + ",lace";
            }
            if (checkUmbrella.isChecked()) {
                value = value + ",umbrella";
            }
            if (checkPanel.isChecked()) {
                value = value + ",panel";
            }
            /*if (checkStraight.isChecked()) {
                value = value + ",straight";
            }*/
        } else if (checkWorks.isChecked()) {
            value = "works";
            if (checkPiping.isChecked()) {
                value = value + ",piping";
            }
            if (checkLace.isChecked()) {
                value = value + ",lace";
            }
            if (checkUmbrella.isChecked()) {
                value = value + ",umbrella";
            }
            if (checkPanel.isChecked()) {
                value = value + ",panel";
            }
            /*if (checkStraight.isChecked()) {
                value = value + ",straight";
            }*/
        } else if (checkPiping.isChecked()) {
            value = "piping";
            if (checkLace.isChecked()) {
                value = value + ",lace";
            }
            if (checkUmbrella.isChecked()) {
                value = value + ",umbrella";
            }
            if (checkPanel.isChecked()) {
                value = value + ",panel";
            }
            /*if (checkStraight.isChecked()) {
                value = value + ",straight";
            }*/
        } else if (checkLace.isChecked()) {
            value = "lace";
            if (checkUmbrella.isChecked()) {
                value = value + ",umbrella";
            }
            if (checkPanel.isChecked()) {
                value = value + ",panel";
            }
            /*if (checkStraight.isChecked()) {
                value = value + ",straight";
            }*/
        } else if (checkUmbrella.isChecked()) {
            value = "umbrella";
            if (checkPanel.isChecked()) {
                value = value + ",panel";
            }
            /*if (checkStraight.isChecked()) {
                value = value + ",straight";
            }*/
        } else if (checkPanel.isChecked()) {
            value = "panel";
            /*if (checkStraight.isChecked()) {
                value = value + ",straight";
            }*/
        }/* else if (checkStraight.isChecked()) {
            value = "straight";
        }*/ else {
            value = "";
        }
        return value;
    }

    public String flairPiping() {
        if (rgFlairPiping.getCheckedRadioButtonId() != -1) {
            int id = rgFlairPiping.getCheckedRadioButtonId();
            View radioButton = rgFlairPiping.findViewById(id);
            int radioId = rgFlairPiping.indexOfChild(radioButton);
            RadioButton btn = (RadioButton) rgFlairPiping.getChildAt(radioId);
            String selection = (String) btn.getText();
            if (selection.equalsIgnoreCase("none")) {
                selection = "";
            }
            return selection;
        }
        return "";
    }

    public String bottomType() {
        if (rgBottomUnknown.getCheckedRadioButtonId() != -1) {
            int id = rgBottomUnknown.getCheckedRadioButtonId();
            View radioButton = rgBottomUnknown.findViewById(id);
            int radioId = rgBottomUnknown.indexOfChild(radioButton);
            RadioButton btn = (RadioButton) rgBottomUnknown.getChildAt(radioId);
            String selection = (String) btn.getText();
            if (selection.equalsIgnoreCase("none"))
                selection = "";
            return selection;
        }
        return "";
    }

    public String pocketType() {
        String value = "";
        if (checkPocket.isChecked()) {
            value = "pocket";
            if (checkElastic.isChecked()) {
                value = value + ",elastic";
                if (checkPressButton.isChecked()) {
                    value = value + ",press_button";
                }
            } else if (checkPressButton.isChecked()) {
                value = value + ",press_button";
            }
        } else if (checkElastic.isChecked()) {
            value = "elastic";
            if (checkPressButton.isChecked()) {
                value = value + ",press_button";
            }
        } else if (checkPressButton.isChecked()) {
            value = "press_button";
        } else {
            value = "";
        }

        return value;
    }

    public String isFlairSlit() {
        return isSlit;
    }

    public String isFlairSide() {
        return isSide;
    }

    public String isFlairFront() {
        return isFront;
    }


    public void setDesignerData(ArrayList<DesignerItem> ls) {
        listDesigner = ls;
        mSpinnerDesignerAdapter = new SpinnerDesignerAdapter(getContext(), R.layout.spinner_item, ls);
        mSpinnerDesignerAdapter.setDropDownViewResource(R.layout.spinner_drop_item);
        spinDesigner.setAdapter(mSpinnerDesignerAdapter);
    }

    public void setData(M2Data md) {

        etTemplateNo.setText(md.getTemplateNo());

        if (md.getPad().equalsIgnoreCase("1")) {
            switchPad.setChecked(true);
        } else {
            switchPad.setChecked(false);
        }

        switch (md.getPleat()) {
            case "Straight":
                rgPleat.check(R.id.rbPleatStraight);
                break;
            case "Umbrella":
                rgPleat.check(R.id.rbPleatUmbrella);
                break;
        }

        switch (md.getTypeOfFrock()) {
            case "Normal":
                rgFrockType.check(R.id.rbFrockNormal);
                break;
            case "Umbrella":
                rgFrockType.check(R.id.rbFrockUmbrella);
                break;
            case "A-Line":
                rgFrockType.check(R.id.rbFrockALine);
                break;
            case "Tube Frock":
                rgFrockType.check(R.id.rbFrockTube);
                break;
            case "Balloon Box":
                rgFrockType.check(R.id.rbFrockBalloon);
                break;
            case "Umbrella Box":
                rgFrockType.check(R.id.rbFrockUmbrBox);
                break;
        }

        if (md.getTungies().equalsIgnoreCase("1"))
            switchTunics.setChecked(true);
        else
            switchTunics.setChecked(false);

        if (md.getTransparent().contains("front")) {
            checkTransFront.setChecked(true);
        }

        if (md.getTransparent().contains("back")) {
            checkTransBack.setChecked(true);
        }

        if (md.getPanel().equalsIgnoreCase("1"))
            switchPanel.setChecked(true);
        else
            switchPanel.setChecked(false);

        if (md.getFall().equalsIgnoreCase("1"))
            switchFall.setChecked(true);
        else
            switchFall.setChecked(false);

        if (md.getTassels().equalsIgnoreCase("1"))
            switchTassels.setChecked(true);
        else
            switchTassels.setChecked(false);

        if (md.getLock().equalsIgnoreCase("1"))
            switchLock.setChecked(true);
        else
            switchLock.setChecked(false);

        if (md.getLockPiping().equalsIgnoreCase("1"))
            switchEditPiping.setChecked(true);
        else
            switchEditPiping.setChecked(false);

        if (md.getStraight().equalsIgnoreCase("1"))
            switchStraight.setChecked(true);
        else
            switchStraight.setChecked(false);


        if (md.getSkirtUmbrella().equalsIgnoreCase("1"))
            switchUmbrella.setChecked(true);
        else
            switchUmbrella.setChecked(false);

        if (md.getHangings().equalsIgnoreCase("1"))
            switchHanging.setChecked(true);
        else
            switchHanging.setChecked(false);

        if (md.getSkirtUmbrella().equalsIgnoreCase("1"))
            switchUmbrella.setChecked(true);
        else
            switchUmbrella.setChecked(false);

        if (!md.getOpen().equalsIgnoreCase("")) {
            switchOpen.setChecked(true);
            if (md.getOpen().toLowerCase().contains("front"))
                checkOpenFront.setChecked(true);
            if (md.getOpen().toLowerCase().contains("back"))
                checkOpenBack.setChecked(true);
        }

        if (md.getIsZip().equalsIgnoreCase("1")) {
            switchZip.setChecked(true);
            rgZipYes.setVisibility(View.VISIBLE);
        } else {
            switchZip.setChecked(false);
        }

        if (md.getIsTuck().equalsIgnoreCase("1")) {
            switchTuck.setChecked(true);
            llTuckYes.setVisibility(View.VISIBLE);
        } else {
            switchTuck.setChecked(false);
        }

        if (md.getIsSleeveLining().equalsIgnoreCase("1")) {
            switchLining.setChecked(true);
        } else {
            switchLining.setChecked(false);
        }

        if (md.getIsFlairSlit().equalsIgnoreCase("1")) {
            switchSlit.setChecked(true);
            rgUmbrella.setVisibility(View.GONE);
            rgUmbrella.clearCheck();
            rgSCWS.setVisibility(View.GONE);
            rgSCWS.clearCheck();
            rgFrontSlit.setVisibility(View.GONE);
            rgFrontSlit.clearCheck();
        } else {
            switchSlit.setChecked(false);
        }

        if (md.getIsFlairSide().equalsIgnoreCase("1")) {
            switchSide.setChecked(true);
        } else {
            switchSide.setChecked(false);
        }

        if (md.getIsFlairFront().equalsIgnoreCase("1")) {
            switchFront.setChecked(true);
        } else {
            switchFront.setChecked(false);
        }

        switch (md.getNeckPiping()) {
            case "SPP":
                rgNeckPiping.check(R.id.rbNeckSPP);
                break;
            case "DPP":
                rgNeckPiping.check(R.id.rbNeckDPP);
                break;
        }

        switch (md.getNeckType()) {
            case "Boat":
                rgNeckUnknown.check(R.id.rbBoat);
                break;
            case "Collar":
                rgNeckUnknown.check(R.id.rbCollar);
                break;
            case "Wide":
                rgNeckUnknown.check(R.id.rbWide);
                break;
            case "Normal":
                rgNeckUnknown.check(R.id.rbNormal);
                break;
            case "Square":
                rgNeckUnknown.check(R.id.rbSquare);
                break;
            case "High Neck":
                rgNeckUnknown.check(R.id.rbHighNeck);
                break;
        }

        switch (md.getSleevePipingType()) {
            case "SPP":
                rgSleevePiping.check(R.id.rbSleeveSPP);
                break;
            case "DPP":
                rgSleevePiping.check(R.id.rbSleeveDPP);
                break;
        }

        switch (md.getSleeveLining()) {
            case "SS":
                rgSleeveUnKnown.check(R.id.rbSS);
                break;
            case "FS":
                rgSleeveUnKnown.check(R.id.rbFS);
                break;
            case "CS":
                rgSleeveUnKnown.check(R.id.rbCS);
                break;
            case "3/4":
                rgSleeveUnKnown.check(R.id.rb34);
                break;
            case "KL":
                rgSleeveUnKnown.check(R.id.rbKL);
                break;
        }

        switch (md.getIsSlitScwsFront()) {
            case "0":
                rgFrontSlit.check(R.id.rbFrontSlitNo);
                break;
            case "1":
                rgFrontSlit.check(R.id.rbFrontSlitYes);
                break;
        }

        switch (md.getIsSlitScws()) {
            case "0":
                rgSCWS.check(R.id.rbSCWSNo);
                break;
            case "1":
                rgSCWS.check(R.id.rbSCWSYes);
                rgFrontSlit.setVisibility(View.VISIBLE);
                break;
        }

        switch (md.getZipType()) {
            case "Normal":
                rgZipYes.check(R.id.rbZipNo);
                llYKKYes.setVisibility(View.GONE);
                rgYKKNo.setVisibility(View.VISIBLE);
                break;
            case "Ykk":
                rgZipYes.check(R.id.rbZipYKK);
                llYKKYes.setVisibility(View.VISIBLE);
                rgYKKNo.setVisibility(View.GONE);
                break;
        }

        if (md.getYkkType().contains("front")) {
            checkYKKFront.setChecked(true);
        }

        if (md.getYkkType().contains("side")) {
            checkYKKSide.setChecked(true);
        }

        if (md.getYkkType().contains("back")) {
            checkYKKBack.setChecked(true);
        }

        switch (md.getYkkNoType()) {
            case "Hiding":
                rgYKKNo.check(R.id.rbZipHiding);
                break;
            case "Normal":
                rgYKKNo.check(R.id.rbZipNormal);
                break;
        }

        if (md.getHiding().contains("back")) {
            checkHidingBack.setChecked(true);
        }

        if (md.getHiding().contains("front")) {
            checkHidingFront.setChecked(true);
        }

        if (md.getHiding().contains("side")) {
            checkHidingSide.setChecked(true);
        }

        if (md.getNormal().contains("back")) {
            checkHidingBack.setChecked(true);
        }

        if (md.getNormal().contains("front")) {
            checkHidingFront.setChecked(true);
        }

        if (md.getNormal().contains("side")) {
            checkHidingSide.setChecked(true);
        }


        if (md.getTuckType().contains("front")) {
            checkTuckFront.setChecked(true);
        }

        if (md.getTuckType().contains("arm_hole")) {
            checkTuckArmHole.setChecked(true);
        }

        switch (md.getFlairPiping()) {
            case "SPP":
                rgFlairPiping.check(R.id.rbFlairSPP);
                break;
            case "DPP":
                rgFlairPiping.check(R.id.rbFlairDPP);
                break;
        }

        switch (md.getIsSlitUmbrella()) {
            case "0":
                rgUmbrella.check(R.id.rbUmbrellaNo);
                break;
            case "1":
                rgUmbrella.check(R.id.rbUmbrellaYes);
                break;
        }

        if (md.getPocketType().contains("pocket")) {
            checkPocket.setChecked(true);
        }

        if (md.getPocketType().contains("elastic")) {
            checkElastic.setChecked(true);
        }

        if (md.getPocketType().contains("press_button")) {
            checkPressButton.setChecked(true);
        }

        switch (md.getBottomType()) {
            case "Churi":
                rgBottomUnknown.check(R.id.rbChuri);
                break;
            case "Loose":
                rgBottomUnknown.check(R.id.rbLoose);
                break;
            case "Pencil":
                rgBottomUnknown.check(R.id.rbPencil);
                break;
            case "Parallel":
                rgBottomUnknown.check(R.id.rbParallel);
                break;
            case "Pattiyala":
                rgBottomUnknown.check(R.id.rbPattiyala);
                break;
            case "Pallaso":
                rgBottomUnknown.check(R.id.rbPallaso);
                break;
        }

        if (md.getFlairType().contains("border")) {
            checkBorder.setChecked(true);
        }

        if (md.getFlairType().contains("work")) {
            checkWorks.setChecked(true);
        }

        if (md.getFlairType().contains("piping")) {
            checkPiping.setChecked(true);
        }

        if (md.getFlairType().contains("lace")) {
            checkLace.setChecked(true);
        }

        if (md.getFlairType().contains("umbrella")) {
            checkUmbrella.setChecked(true);
        }

        if (md.getFlairType().contains("panel")) {
            checkPanel.setChecked(true);
        }

        /*if (md.getFlairType().contains("straight")) {
            checkStraight.setChecked(true);
        }*/

        for (int i = 0; i < listDesigner.size(); i++) {
            if (md.getM2DesignerId().equalsIgnoreCase(listDesigner.get(i).getId())) {
                spinDesigner.setSelection(i);
                break;
            }
        }

    }

    public void setType(String type) {
        mType = type;
        if (type.toLowerCase().contains("churidar")) {
            try {
                tvLabelPad.setVisibility(View.GONE);
                switchPad.setVisibility(View.GONE);
//                cvExtra.setVisibility(View.GONE);
                tvLabelPleat.setVisibility(View.GONE);
                rgPleat.setVisibility(View.GONE);
                llOpen.setVisibility(View.GONE);
                llUmbrella.setVisibility(View.GONE);
                llHanging.setVisibility(View.GONE);
                llTunic.setVisibility(View.GONE);
                checkPiping.setVisibility(View.GONE);
                checkLace.setVisibility(View.GONE);
                checkUmbrella.setVisibility(View.GONE);
                checkPanel.setVisibility(View.GONE);
                rgFrockType.setVisibility(View.GONE);
                llTassels.setVisibility(View.GONE);
                llFall.setVisibility(View.GONE);
                llLock.setVisibility(View.GONE);
                llEditPiping.setVisibility(View.GONE);
//                checkStraight.setVisibility(View.GONE);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (type.toLowerCase().contains("anarkali")) {
            try {
                tvLabelFlair.setVisibility(View.GONE);
                llFlairUnknown.setVisibility(View.GONE);
                tvLabelFlairPiping.setVisibility(View.GONE);
                rgFlairPiping.setVisibility(View.GONE);
                llSide.setVisibility(View.GONE);
                llFront.setVisibility(View.GONE);
                tvLabelPleat.setVisibility(View.GONE);
                rgPleat.setVisibility(View.GONE);
                llOpen.setVisibility(View.GONE);
                llUmbrella.setVisibility(View.GONE);
                llHanging.setVisibility(View.GONE);
                llTunic.setVisibility(View.GONE);
                rgFrockType.setVisibility(View.GONE);
                tvLabelTransparent.setVisibility(View.GONE);
                llTransparent.setVisibility(View.GONE);
                llTassels.setVisibility(View.GONE);
                llFall.setVisibility(View.GONE);
                llLock.setVisibility(View.GONE);
                llEditPiping.setVisibility(View.GONE);
//                cvNeck.setVisibility(View.GONE);
                cvBottom.setVisibility(View.GONE);
                checkPiping.setVisibility(View.GONE);
                checkLace.setVisibility(View.GONE);
                checkUmbrella.setVisibility(View.GONE);
                checkPanel.setVisibility(View.GONE);
//                checkStraight.setVisibility(View.GONE);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (type.toLowerCase().contains("shawl")) {
            try {
                cvBottom.setVisibility(View.GONE);
                cvFlair.setVisibility(View.GONE);
                cvNeck.setVisibility(View.GONE);
                cvSleeve.setVisibility(View.GONE);
                cvTemplate.setVisibility(View.GONE);
                tvLabelPleat.setVisibility(View.GONE);
                rgPleat.setVisibility(View.GONE);
                llOpen.setVisibility(View.GONE);
//                llPanel.setVisibility(View.GONE);
                llUmbrella.setVisibility(View.GONE);
                llHanging.setVisibility(View.GONE);
                llTunic.setVisibility(View.GONE);
                llFall.setVisibility(View.GONE);
                /*llLock.setVisibility(View.GONE);
                llEditPiping.setVisibility(View.GONE);*/
                rgFrockType.setVisibility(View.GONE);
                tvLabelTransparent.setVisibility(View.GONE);
                llTransparent.setVisibility(View.GONE);
                checkPiping.setVisibility(View.GONE);
                checkLace.setVisibility(View.GONE);
                checkUmbrella.setVisibility(View.GONE);
                checkPanel.setVisibility(View.GONE);
//                checkStraight.setVisibility(View.GONE);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else if (type.toLowerCase().contains("weddingnet")) {
            try {
                cvBottom.setVisibility(View.GONE);
                cvFlair.setVisibility(View.GONE);
                cvNeck.setVisibility(View.GONE);
                cvSleeve.setVisibility(View.GONE);
                cvTemplate.setVisibility(View.GONE);
                tvLabelPleat.setVisibility(View.GONE);
                rgPleat.setVisibility(View.GONE);
                llOpen.setVisibility(View.GONE);
//                llPanel.setVisibility(View.GONE);
                llUmbrella.setVisibility(View.GONE);
                llHanging.setVisibility(View.GONE);
                llTunic.setVisibility(View.GONE);
                llFall.setVisibility(View.GONE);
                /*llLock.setVisibility(View.GONE);
                llEditPiping.setVisibility(View.GONE);*/
                rgFrockType.setVisibility(View.GONE);
                tvLabelTransparent.setVisibility(View.GONE);
                llTransparent.setVisibility(View.GONE);
                checkPiping.setVisibility(View.GONE);
                checkLace.setVisibility(View.GONE);
                checkUmbrella.setVisibility(View.GONE);
                checkPanel.setVisibility(View.GONE);
//                checkStraight.setVisibility(View.GONE);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }












        else if (type.toLowerCase().contains("saree")) {
            try {
                cvBottom.setVisibility(View.GONE);
                cvFlair.setVisibility(View.GONE);
                cvNeck.setVisibility(View.GONE);
                cvSleeve.setVisibility(View.GONE);
                tvLabelTuck.setVisibility(View.GONE);
                tvLabelZip.setVisibility(View.GONE);
                tvLabelPad.setVisibility(View.GONE);
                switchTuck.setVisibility(View.GONE);
                switchZip.setVisibility(View.GONE);
                switchPad.setVisibility(View.GONE);
                tvLabelPleat.setVisibility(View.GONE);
                rgPleat.setVisibility(View.GONE);
                llOpen.setVisibility(View.GONE);
                llPanel.setVisibility(View.GONE);
                llUmbrella.setVisibility(View.GONE);
                llHanging.setVisibility(View.GONE);
                llTunic.setVisibility(View.GONE);
//                llLock.setVisibility(View.GONE);
                rgFrockType.setVisibility(View.GONE);
                tvLabelTransparent.setVisibility(View.GONE);
                llTransparent.setVisibility(View.GONE);
                checkPiping.setVisibility(View.GONE);
                checkLace.setVisibility(View.GONE);
                checkUmbrella.setVisibility(View.GONE);
                checkPanel.setVisibility(View.GONE);
//                checkStraight.setVisibility(View.GONE);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (type.toLowerCase().contains("frock")) {
            try {
                tvLabelTuck.setVisibility(View.GONE);
                switchTuck.setVisibility(View.GONE);
                cvFlair.setVisibility(View.GONE);
                cvBottom.setVisibility(View.GONE);
                llOpen.setVisibility(View.GONE);
                llPanel.setVisibility(View.GONE);
                llUmbrella.setVisibility(View.GONE);
                llHanging.setVisibility(View.GONE);
                llTassels.setVisibility(View.GONE);
                llFall.setVisibility(View.GONE);
                llLock.setVisibility(View.GONE);
                llEditPiping.setVisibility(View.GONE);
                checkPiping.setVisibility(View.GONE);
                checkLace.setVisibility(View.GONE);
                checkUmbrella.setVisibility(View.GONE);
                checkPanel.setVisibility(View.GONE);
//                checkStraight.setVisibility(View.GONE);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (type.toLowerCase().contains("blouse")) {
            try {
                tvLabelTuck.setVisibility(View.GONE);
                switchTuck.setVisibility(View.GONE);
                tvLabelPleat.setVisibility(View.GONE);
                rgPleat.setVisibility(View.GONE);
                llPanel.setVisibility(View.GONE);
                llUmbrella.setVisibility(View.GONE);
//                llHanging.setVisibility(View.GONE);
                llTunic.setVisibility(View.GONE);
                rgFrockType.setVisibility(View.GONE);
                tvLabelTransparent.setVisibility(View.GONE);
                llTransparent.setVisibility(View.GONE);
                llTassels.setVisibility(View.GONE);
                llFall.setVisibility(View.GONE);
                llLock.setVisibility(View.GONE);
                llEditPiping.setVisibility(View.GONE);
                cvFlair.setVisibility(View.GONE);
                cvBottom.setVisibility(View.GONE);
                checkPiping.setVisibility(View.GONE);
                checkLace.setVisibility(View.GONE);
                checkUmbrella.setVisibility(View.GONE);
                checkPanel.setVisibility(View.GONE);
//                checkStraight.setVisibility(View.GONE);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else if (type.toLowerCase().contains("weddingblouse")) {
            try {
                tvLabelTuck.setVisibility(View.GONE);
                switchTuck.setVisibility(View.GONE);
                tvLabelPleat.setVisibility(View.GONE);
                rgPleat.setVisibility(View.GONE);
                llPanel.setVisibility(View.GONE);
                llUmbrella.setVisibility(View.GONE);
//                llHanging.setVisibility(View.GONE);
                llTunic.setVisibility(View.GONE);
                rgFrockType.setVisibility(View.GONE);
                tvLabelTransparent.setVisibility(View.GONE);
                llTransparent.setVisibility(View.GONE);
                llTassels.setVisibility(View.GONE);
                llFall.setVisibility(View.GONE);
                llLock.setVisibility(View.GONE);
                llEditPiping.setVisibility(View.GONE);
                cvFlair.setVisibility(View.GONE);
                cvBottom.setVisibility(View.GONE);
                checkPiping.setVisibility(View.GONE);
                checkLace.setVisibility(View.GONE);
                checkUmbrella.setVisibility(View.GONE);
                checkPanel.setVisibility(View.GONE);
//                checkStraight.setVisibility(View.GONE);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }







        else if (type.toLowerCase().contains("top/skirt")) {
            try {
                tvLabelFlairPiping.setVisibility(View.GONE);
                rgFlairPiping.setVisibility(View.GONE);
                tvLabelTuck.setVisibility(View.GONE);
                switchTuck.setVisibility(View.GONE);
                cvBottom.setVisibility(View.GONE);
                tvLabelPleat.setVisibility(View.GONE);
                rgPleat.setVisibility(View.GONE);
                llTunic.setVisibility(View.GONE);
                rgFrockType.setVisibility(View.GONE);
                tvLabelTransparent.setVisibility(View.GONE);
                llTransparent.setVisibility(View.GONE);
                llTassels.setVisibility(View.GONE);
                llFall.setVisibility(View.GONE);
                llLock.setVisibility(View.GONE);
                llEditPiping.setVisibility(View.GONE);
                llStraight.setVisibility(View.VISIBLE);
                llOpen.setVisibility(View.GONE);
                rgUmbrella.setVisibility(View.GONE);
                rgUmbrella.clearCheck();
                rgSCWS.setVisibility(View.GONE);
                rgSCWS.clearCheck();
                rgFrontSlit.setVisibility(View.GONE);
                rgFrontSlit.clearCheck();
                llSlit.setVisibility(View.GONE);
                llSide.setVisibility(View.GONE);
                llFront.setVisibility(View.GONE);
                checkUmbrella.setVisibility(View.GONE);
                checkPanel.setVisibility(View.GONE);
//                checkStraight.setVisibility(View.GONE);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (type.toLowerCase().contains("gown")) {
            try {
                cvBottom.setVisibility(View.GONE);

                llOpen.setVisibility(View.GONE);
                llUmbrella.setVisibility(View.GONE);
                llHanging.setVisibility(View.GONE);
                llTunic.setVisibility(View.GONE);
                rgFrockType.setVisibility(View.GONE);
                tvLabelTransparent.setVisibility(View.GONE);
                llTransparent.setVisibility(View.GONE);
                llTassels.setVisibility(View.GONE);
                llFall.setVisibility(View.GONE);
                llLock.setVisibility(View.GONE);
                llEditPiping.setVisibility(View.GONE);
                tvLabelTuck.setVisibility(View.GONE);
                switchTuck.setVisibility(View.GONE);
                checkPiping.setVisibility(View.GONE);
                checkLace.setVisibility(View.GONE);
                checkBorder.setVisibility(View.GONE);
                checkWorks.setVisibility(View.GONE);
//                checkStraight.setVisibility(View.GONE);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (type.toLowerCase().contains("overcoat")) {
            try {
                cvBottom.setVisibility(View.GONE);
                cvFlair.setVisibility(View.GONE);
                tvLabelTuck.setVisibility(View.GONE);
                tvLabelZip.setVisibility(View.GONE);
                tvLabelPad.setVisibility(View.GONE);
                switchTuck.setVisibility(View.GONE);
                switchZip.setVisibility(View.GONE);
                switchPad.setVisibility(View.GONE);
                tvLabelPleat.setVisibility(View.GONE);
                rgPleat.setVisibility(View.GONE);
                llPanel.setVisibility(View.GONE);
                llUmbrella.setVisibility(View.GONE);
                llHanging.setVisibility(View.GONE);
                llTunic.setVisibility(View.GONE);
                rgFrockType.setVisibility(View.GONE);
                tvLabelTransparent.setVisibility(View.GONE);
                llTransparent.setVisibility(View.GONE);
                llTassels.setVisibility(View.GONE);
                llFall.setVisibility(View.GONE);
                llLock.setVisibility(View.GONE);
                llEditPiping.setVisibility(View.GONE);
                checkPiping.setVisibility(View.GONE);
                checkLace.setVisibility(View.GONE);
                checkUmbrella.setVisibility(View.GONE);
                checkPanel.setVisibility(View.GONE);
//                checkStraight.setVisibility(View.GONE);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
