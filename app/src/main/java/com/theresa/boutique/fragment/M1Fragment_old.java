package com.theresa.boutique.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.theresa.boutique.R;
import com.theresa.boutique.adapter.SpinnerDesignerAdapter;
import com.theresa.boutique.adapter.SpinnerFabricAdapter;
import com.theresa.boutique.adapter.SpinnerFunctionAdapter;
import com.theresa.boutique.adapter.SpinnerPriorityAdapter;
import com.theresa.boutique.model.DesignerItem;
import com.theresa.boutique.model.FabricItem;
import com.theresa.boutique.model.FunctionItem;
import com.theresa.boutique.model.M1Data;
import com.theresa.boutique.model.PriorityItem;
import com.theresa.boutique.util.Utils;

import java.util.ArrayList;

import ru.katso.livebutton.LiveButton;

public class M1Fragment_old extends Fragment {

    AppCompatSpinner spinPriority, spinFunction, spinFabric, spinDesigner;
    EditText etTop, etTopLining, etLace, etYoke, etYokeLining, etSleeve, etSleeveLining, etBottom, etBottomLining, etWorkingPiece, etDescription, etSpeakerNet, etSpeakerNetLining, etPiping, etPipingLining, etOthers, etSkirt, etSkirtLining, etSkirtSpeakerNet, etSkirtSpeakerNetLining, etSkirtOthers;
    SwitchCompat switchTop, switchTopLining, switchLace, switchYoke, switchYokeLining, switchSleeve, switchSleeveLining, switchBottom, switchBottomLining, switchWorkPiece, switchSpeakernet, switchSpeakernetLining, switchPiping, switchPipingLining, switchSkirt, switchSkirtLining, switchSkirtSpeakerNet, switchSkirtSpeakerNetLining;
    //    RadioGroup rgMeasurement;
    AppCompatCheckBox checkTop, checkBottom;
    LiveButton btnNext;
    SpinnerPriorityAdapter mSpinnerPriorityAdapter;
    SpinnerFabricAdapter mSpinnerFabricAdapter;
    SpinnerFunctionAdapter mSpinnerFunctionAdapter;
    SpinnerDesignerAdapter mSpinnerDesignerAdapter;
    ArrayList<PriorityItem> listPriority = new ArrayList<>();
    ArrayList<FabricItem> listFabric = new ArrayList<>();
    ArrayList<FunctionItem> listFunction = new ArrayList<>();
    ArrayList<DesignerItem> listDesigner = new ArrayList<>();
    InputMethodManager imm;

    String isTop = "0", isTopLining = "0", isLace = "0", isYoke = "0", isYokeLining = "0", isSleeve = "0",
            isSleeveLining = "0", isBottom = "0", isBottomLining = "0", isShawl = "0", isSpeakerNet="0", isSpeakerNetLining="0",
    isPiping = "0", isPipingLining="0", isSkirt="0", isSkirtLining="0", isSkirtSpeakerNet="0", isSkirtSpeakerNetLining="0", isWorkingPiece="0";

    String priority = "", fabric = "", function = "", designer = "";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return init(container);
    }

    private View init(ViewGroup container) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragmentm1, container, false);
        spinPriority = (AppCompatSpinner) view.findViewById(R.id.spinPriority);
        spinFunction = (AppCompatSpinner) view.findViewById(R.id.spinFunction);
        spinFabric = (AppCompatSpinner) view.findViewById(R.id.spinFabric);
        spinDesigner = (AppCompatSpinner) view.findViewById(R.id.spinDesigner);
        etTop = (EditText) view.findViewById(R.id.etTop);
        etTopLining = (EditText) view.findViewById(R.id.etTopLining);
        etLace = (EditText) view.findViewById(R.id.etLace);
        etYoke = (EditText) view.findViewById(R.id.etYoke);
        etYokeLining = (EditText) view.findViewById(R.id.etYokeLining);
        etSleeve = (EditText) view.findViewById(R.id.etSleeve);
        etSleeveLining = (EditText) view.findViewById(R.id.etSleeveLining);
        etBottom = (EditText) view.findViewById(R.id.etBottom);
        etBottomLining = (EditText) view.findViewById(R.id.etBottomLining);
        etWorkingPiece = (EditText) view.findViewById(R.id.etWorkPiece);
        etDescription = (EditText) view.findViewById(R.id.etDescription);

        etSpeakerNet= (EditText) view.findViewById(R.id.etSpeakerNet);
        etSpeakerNetLining= (EditText) view.findViewById(R.id.etSpeakerNetLining);
        etPiping= (EditText) view.findViewById(R.id.etPiping);
        etPipingLining= (EditText) view.findViewById(R.id.etPipingLining);
        etOthers= (EditText) view.findViewById(R.id.etOthers);
        etSkirt= (EditText) view.findViewById(R.id.etSkirt);
        etSkirtLining= (EditText) view.findViewById(R.id.etSkirtLining);
        etSkirtSpeakerNet= (EditText) view.findViewById(R.id.etSkirtSpeakertNet);
        etSkirtSpeakerNetLining= (EditText) view.findViewById(R.id.etSkirtSpeakerNetLining);
        etSkirtOthers= (EditText) view.findViewById(R.id.etSkirtOthers);

        switchTop = (SwitchCompat) view.findViewById(R.id.switchTop);
        switchTopLining = (SwitchCompat) view.findViewById(R.id.switchTopLining);
        switchLace = (SwitchCompat) view.findViewById(R.id.switchLace);
        switchYoke = (SwitchCompat) view.findViewById(R.id.switchYoke);
        switchYokeLining = (SwitchCompat) view.findViewById(R.id.switchYokeLining);
        switchSleeve = (SwitchCompat) view.findViewById(R.id.switchSleeve);
        switchSleeveLining = (SwitchCompat) view.findViewById(R.id.switchSleeveLining);
        switchBottom = (SwitchCompat) view.findViewById(R.id.switchBottom);
        switchBottomLining = (SwitchCompat) view.findViewById(R.id.switchBottomLining);
        switchWorkPiece = (SwitchCompat) view.findViewById(R.id.switchWorkPiece);

        switchSpeakernet= (SwitchCompat) view.findViewById(R.id.switchSpeakernet);
        switchSpeakernetLining= (SwitchCompat) view.findViewById(R.id.switchSpeakernetLining);
        switchPiping= (SwitchCompat) view.findViewById(R.id.switchPiping);
        switchPipingLining= (SwitchCompat) view.findViewById(R.id.switchPipingLining);
        switchSkirt= (SwitchCompat) view.findViewById(R.id.switchSkirt);
        switchSkirtLining= (SwitchCompat) view.findViewById(R.id.switchSkirtLining);
        switchSkirtSpeakerNet= (SwitchCompat) view.findViewById(R.id.switchSkirtSpeakerNet);
        switchSkirtSpeakerNetLining= (SwitchCompat) view.findViewById(R.id.switchSkirtSpeakerNetLining);

//        rgMeasurement = (RadioGroup) view.findViewById(R.id.rgMeasurment);
        checkTop = (AppCompatCheckBox) view.findViewById(R.id.checkTop);
        checkBottom = (AppCompatCheckBox) view.findViewById(R.id.checkBottom);
        btnNext = (LiveButton) view.findViewById(R.id.btnNext);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        businessLogic();
    }

    private void businessLogic() {
//        list = getPriorityData();


        spinPriority.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                priority = listPriority.get(position).getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                if (listPriority.size() > 0) {
                    priority = listPriority.get(0).getId();
                } else
                    priority = "0";
            }
        });

        spinFabric.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                fabric = listFabric.get(position).getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                if (listFabric.size() > 0) {
                    fabric = listFabric.get(0).getId();
                } else
                    fabric = "0";
            }
        });

        spinFunction.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                function = listFunction.get(position).getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                if (listFunction.size() > 0) {
                    function = listFunction.get(0).getId();
                } else
                    function = "0";
            }
        });

        spinDesigner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (listDesigner.size() > 0) {
                    designer = listDesigner.get(position).getId();
                } else {
                    designer = "0";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                if (listDesigner.size() > 0) {
                    designer = listDesigner.get(0).getId();
                } else
                    designer = "0";
            }
        });

        switchTop.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    etTop.requestFocus();
                    etTop.setEnabled(true);
                    Utils.showKeyboard(getActivity(), etTop);
                    isTop = "1";
                } else {
                    etTop.setFocusableInTouchMode(false);
                    etTop.setFocusable(false);
                    etTop.setFocusableInTouchMode(true);
                    etTop.setFocusable(true);
                    etTop.setText("");
                    Utils.hideKeyboard(getActivity(), etTop);
                    etTop.setEnabled(false);
                    isTop = "0";
                }
            }
        });

        switchTopLining.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    etTopLining.requestFocus();
                    etTopLining.setEnabled(true);
                    Utils.showKeyboard(getActivity(), etTopLining);
                    isTopLining = "1";
                } else {
                    etTopLining.setFocusableInTouchMode(false);
                    etTopLining.setFocusable(false);
                    etTopLining.setFocusableInTouchMode(true);
                    etTopLining.setFocusable(true);
                    etTopLining.setText("");
                    Utils.hideKeyboard(getActivity(), etTopLining);
                    etTopLining.setEnabled(false);
                    isTopLining = "0";
                }
            }
        });

        switchLace.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    etLace.requestFocus();
                    etLace.setEnabled(true);
                    Utils.showKeyboard(getActivity(), etLace);
                    isLace = "1";
                } else {
                    etLace.setFocusableInTouchMode(false);
                    etLace.setFocusable(false);
                    etLace.setFocusableInTouchMode(true);
                    etLace.setFocusable(true);
                    etLace.setText("");
                    Utils.hideKeyboard(getActivity(), etLace);
                    etLace.setEnabled(false);
                    isLace = "0";
                }
            }
        });

        switchYoke.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    etYoke.requestFocus();
                    etYoke.setEnabled(true);
                    Utils.showKeyboard(getActivity(), etYoke);
                    isYoke = "1";
                } else {
                    etYoke.setFocusableInTouchMode(false);
                    etYoke.setFocusable(false);
                    etYoke.setFocusableInTouchMode(true);
                    etYoke.setFocusable(true);
                    etYoke.setText("");
                    Utils.hideKeyboard(getActivity(), etYoke);
                    etYoke.setEnabled(false);
                    isYoke = "0";
                }
            }
        });

        switchYokeLining.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    etYokeLining.requestFocus();
                    etYokeLining.setEnabled(true);
                    Utils.showKeyboard(getActivity(), etYokeLining);
                    isYokeLining = "1";
                } else {
                    etYokeLining.setFocusableInTouchMode(false);
                    etYokeLining.setFocusable(false);
                    etYokeLining.setFocusableInTouchMode(true);
                    etYokeLining.setFocusable(true);
                    etYokeLining.setText("");
                    Utils.hideKeyboard(getActivity(), etYokeLining);
                    etYokeLining.setEnabled(false);
                    isYokeLining = "0";
                }
            }
        });

        switchSleeve.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    etSleeve.requestFocus();
                    etSleeve.setEnabled(true);
                    Utils.showKeyboard(getActivity(), etSleeve);
                    isSleeve = "1";
                } else {
                    etSleeve.setFocusableInTouchMode(false);
                    etSleeve.setFocusable(false);
                    etSleeve.setFocusableInTouchMode(true);
                    etSleeve.setFocusable(true);
                    etSleeve.setText("");
                    Utils.hideKeyboard(getActivity(), etSleeve);
                    etSleeve.setEnabled(false);
                    isSleeve = "0";
                }
            }
        });

        switchSleeveLining.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    etSleeveLining.requestFocus();
                    etSleeveLining.setEnabled(true);
                    Utils.showKeyboard(getActivity(), etSleeveLining);
                    isSleeveLining = "1";
                } else {
                    etSleeveLining.setFocusableInTouchMode(false);
                    etSleeveLining.setFocusable(false);
                    etSleeveLining.setFocusableInTouchMode(true);
                    etSleeveLining.setFocusable(true);
                    etSleeveLining.setText("");
                    Utils.hideKeyboard(getActivity(), etSleeveLining);
                    etSleeveLining.setEnabled(false);
                    isSleeveLining = "0";
                }
            }
        });

        switchBottom.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    etBottom.requestFocus();
                    etBottom.setEnabled(true);
                    Utils.showKeyboard(getActivity(), etBottom);
                    isBottom = "1";
                } else {
                    etBottom.setFocusableInTouchMode(false);
                    etBottom.setFocusable(false);
                    etBottom.setFocusableInTouchMode(true);
                    etBottom.setFocusable(true);
                    etBottom.setText("");
                    Utils.hideKeyboard(getActivity(), etBottom);
                    etBottom.setEnabled(false);
                    isBottom = "0";
                }
            }
        });

        switchBottomLining.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    etBottomLining.requestFocus();
                    etBottomLining.setEnabled(true);
                    Utils.showKeyboard(getActivity(), etBottomLining);
                    isBottomLining = "1";
                } else {
                    etBottomLining.setFocusableInTouchMode(false);
                    etBottomLining.setFocusable(false);
                    etBottomLining.setFocusableInTouchMode(true);
                    etBottomLining.setFocusable(true);
                    etBottomLining.setText("");
                    Utils.hideKeyboard(getActivity(), etBottomLining);
                    etBottomLining.setEnabled(false);
                    isBottomLining = "0";
                }
            }
        });
        switchSpeakernet.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    etSpeakerNet.requestFocus();
                    etSpeakerNet.setEnabled(true);
                    Utils.showKeyboard(getActivity(), etSpeakerNet);
                    isSpeakerNet = "1";
                } else {
                    etSpeakerNet.setFocusableInTouchMode(false);
                    etSpeakerNet.setFocusable(false);
                    etSpeakerNet.setFocusableInTouchMode(true);
                    etSpeakerNet.setFocusable(true);
                    etSpeakerNet.setText("");
                    Utils.hideKeyboard(getActivity(), etSpeakerNet);
                    etSpeakerNet.setEnabled(false);
                    isSpeakerNet = "0";
                }
            }
        });
        switchSpeakernetLining.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    etSpeakerNetLining.requestFocus();
                    etSpeakerNetLining.setEnabled(true);
                    Utils.showKeyboard(getActivity(), etSpeakerNetLining);
                    isSpeakerNetLining = "1";
                } else {
                    etSpeakerNetLining.setFocusableInTouchMode(false);
                    etSpeakerNetLining.setFocusable(false);
                    etSpeakerNetLining.setFocusableInTouchMode(true);
                    etSpeakerNetLining.setFocusable(true);
                    etSpeakerNetLining.setText("");
                    Utils.hideKeyboard(getActivity(), etSpeakerNetLining);
                    etSpeakerNetLining.setEnabled(false);
                    isSpeakerNet = "0";
                }
            }
        });
        switchPiping.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    etPiping.requestFocus();
                    etPiping.setEnabled(true);
                    Utils.showKeyboard(getActivity(), etPiping);
                    isPiping = "1";
                } else {
                    etPiping.setFocusableInTouchMode(false);
                    etPiping.setFocusable(false);
                    etPiping.setFocusableInTouchMode(true);
                    etPiping.setFocusable(true);
                    etPiping.setText("");
                    Utils.hideKeyboard(getActivity(), etPiping);
                    etPiping.setEnabled(false);
                    isPiping = "0";
                }
            }
        });
        switchPipingLining.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    etPipingLining.requestFocus();
                    etPipingLining.setEnabled(true);
                    Utils.showKeyboard(getActivity(), etPipingLining);
                    isPipingLining = "1";
                } else {
                    etPipingLining.setFocusableInTouchMode(false);
                    etPipingLining.setFocusable(false);
                    etPipingLining.setFocusableInTouchMode(true);
                    etPipingLining.setFocusable(true);
                    etPipingLining.setText("");
                    Utils.hideKeyboard(getActivity(), etPipingLining);
                    etPipingLining.setEnabled(false);
                    isPipingLining = "0";
                }
            }
        });
        switchSkirt.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    etSkirt.requestFocus();
                    etSkirt.setEnabled(true);
                    Utils.showKeyboard(getActivity(), etSkirt);
                    isSkirt = "1";
                } else {
                    etSkirt.setFocusableInTouchMode(false);
                    etSkirt.setFocusable(false);
                    etSkirt.setFocusableInTouchMode(true);
                    etSkirt.setFocusable(true);
                    etSkirt.setText("");
                    Utils.hideKeyboard(getActivity(), etSkirt);
                    etSkirt.setEnabled(false);
                    isSkirt = "0";
                }
            }
        });
        switchSkirtLining.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    etSkirtLining.requestFocus();
                    etSkirtLining.setEnabled(true);
                    Utils.showKeyboard(getActivity(), etSkirtLining);
                    isSkirtLining = "1";
                } else {
                    etSkirtLining.setFocusableInTouchMode(false);
                    etSkirtLining.setFocusable(false);
                    etSkirtLining.setFocusableInTouchMode(true);
                    etSkirtLining.setFocusable(true);
                    etSkirtLining.setText("");
                    Utils.hideKeyboard(getActivity(), etSkirtLining);
                    etSkirtLining.setEnabled(false);
                    isSkirtLining = "0";
                }
            }
        });
        switchSkirtSpeakerNet.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    etSkirtSpeakerNet.requestFocus();
                    etSkirtSpeakerNet.setEnabled(true);
                    Utils.showKeyboard(getActivity(), etSkirtSpeakerNet);
                    isSkirtSpeakerNet = "1";
                } else {
                    etSkirtSpeakerNet.setFocusableInTouchMode(false);
                    etSkirtSpeakerNet.setFocusable(false);
                    etSkirtSpeakerNet.setFocusableInTouchMode(true);
                    etSkirtSpeakerNet.setFocusable(true);
                    etSkirtSpeakerNet.setText("");
                    Utils.hideKeyboard(getActivity(), etSkirtSpeakerNet);
                    etSkirtSpeakerNet.setEnabled(false);
                    isSkirtSpeakerNet = "0";
                }
            }
        });
        switchSkirtSpeakerNetLining.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    etSkirtSpeakerNetLining.requestFocus();
                    etSkirtSpeakerNetLining.setEnabled(true);
                    Utils.showKeyboard(getActivity(), etSkirtSpeakerNetLining);
                    isSkirtSpeakerNetLining = "1";
                } else {
                    etSkirtSpeakerNetLining.setFocusableInTouchMode(false);
                    etSkirtSpeakerNetLining.setFocusable(false);
                    etSkirtSpeakerNetLining.setFocusableInTouchMode(true);
                    etSkirtSpeakerNetLining.setFocusable(true);
                    etSkirtSpeakerNetLining.setText("");
                    Utils.hideKeyboard(getActivity(), etSkirtSpeakerNetLining);
                    etSkirtSpeakerNetLining.setEnabled(false);
                    isSkirtSpeakerNetLining = "0";
                }
            }
        });

        switchWorkPiece.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    etWorkingPiece.requestFocus();
                    etWorkingPiece.setEnabled(true);
                    Utils.showKeyboard(getActivity(), etWorkingPiece);
                    isWorkingPiece = "1";
                } else {
                    etWorkingPiece.setFocusableInTouchMode(false);
                    etWorkingPiece.setFocusable(false);
                    etWorkingPiece.setFocusableInTouchMode(true);
                    etWorkingPiece.setFocusable(true);
                    etWorkingPiece.setText("");
                    Utils.hideKeyboard(getActivity(), etWorkingPiece);
                    etWorkingPiece.setEnabled(false);
                    isWorkingPiece = "0";
                }
            }
        });

        /*switchShawl.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    etShawl.requestFocus();
                    etShawl.setEnabled(true);
                    Utils.showKeyboard(getActivity(), etShawl);
                    isShawl = "1";
                } else {
                    etShawl.setFocusableInTouchMode(false);
                    etShawl.setFocusable(false);
                    etShawl.setFocusableInTouchMode(true);
                    etShawl.setFocusable(true);
                    etShawl.setText("");
                    Utils.hideKeyboard(getActivity(), etShawl);
                    etShawl.setEnabled(false);
                    isShawl = "0";
                }
            }
        });*/

    }

    public String isTop() {
        return isTop;
    }

    public String isTopLining() {
        return isTopLining;
    }

    public String isLace() {
        return isLace;
    }

    public String isYoke() {
        return isYoke;
    }

    public String isYokeLining() {
        return isYokeLining;
    }

    public String isSleeveLining() {
        return isSleeveLining;
    }

    public String isBottom() {
        return isBottom;
    }

    public String isBottomLining() {
        return isBottomLining;
    }

    public String isShawl() {
        return isShawl;
    }
  /*  public String getIsweddingnet(){
        return  isweddingnet;
    }
*/
    public String topVal() {
        return etTop.getText().toString();
    }

    public String topLiningVal() {
        return etTopLining.getText().toString();
    }

    public String laceVal() {
        return etLace.getText().toString();
    }

    public String yokeVal() {
        return etYoke.getText().toString();
    }

    public String yokeLiningVal() {
        return etYokeLining.getText().toString();
    }

    public String isSleeve() {
        return isSleeve;
    }

    public String sleeveVal() {
        return etSleeve.getText().toString();
    }

    public String sleeveLiningVal() {
        return etSleeveLining.getText().toString();
    }

    public String bottomVal() {
        return etBottom.getText().toString();
    }

    public String bottomLiningVal() {
        return etBottomLining.getText().toString();
    }

    public String shawlVal() {
        return /*etShawl.getText().toString()*/"";
    }

    public String remarksVal() {
        return etDescription.getText().toString();
    }

    public String measureVal() {
        String value;
        if (checkTop.isChecked()) {
            value = "top";
            if (checkBottom.isChecked()) {
                value = value + ",bottom";
            }
        } else if (checkBottom.isChecked()) {
            value = "bottom";
        } else {
            value = "";
        }
        return value;
    }

    public String isWorkingPiece(){
        return isWorkingPiece;
    }

    public String workPiece(){return etWorkingPiece.getText().toString();}

    public String isSpeakerNet() {
        return isSpeakerNet;
    }

    public String speakerNet(){return etSpeakerNet.getText().toString();}

    public String isSpeakerNetLining() {
        return isSpeakerNetLining;
    }

    public String speakerNetLining(){return etSpeakerNetLining.getText().toString();}

    public String isPiping() {
        return isPiping;
    }

    public String isPipingLining() {
        return isPipingLining;
    }

    public String pipingLining(){return etPipingLining.getText().toString();}

    public String isSkirt() {
        return isSkirt;
    }

    public String skirt(){return etSkirt.getText().toString();}

    public String isSkirtLining() {
        return isSkirtLining;
    }

    public String others(){
        return etOthers.getText().toString();
    }

    public String skirtLining(){return etSkirtLining.getText().toString();}

    public String isSkirtSpeakerNet() {
        return isSkirtSpeakerNet;
    }

    public String isSkirtSpeakerNetLining() {
        return isSkirtSpeakerNetLining;
    }

    public String priority() {
        return priority;
    }

    public String function() {
        return function;
    }

    public String fabricTypeId() {
        return fabric;
    }

    public String designerId() {
        Log.e("Designer", designer);
        return designer;
    }

    private ArrayList<PriorityItem> getPriorityData() {
        ArrayList<PriorityItem> list = new ArrayList<>();

        PriorityItem item1 = new PriorityItem();
        item1.setId("1");
        item1.setName("Normal");
        list.add(item1);

        PriorityItem item2 = new PriorityItem();
        item2.setId("2");
        item2.setName("Low");
        list.add(item2);

        PriorityItem item3 = new PriorityItem();
        item3.setId("3");
        item3.setName("High");
        list.add(item3);

        return list;
    }

    public void setPriorityData(ArrayList<PriorityItem> ls) {
        this.listPriority = ls;
        mSpinnerPriorityAdapter = new SpinnerPriorityAdapter(getContext(), R.layout.spinner_item, ls);
        mSpinnerPriorityAdapter.setDropDownViewResource(R.layout.spinner_drop_item);
        spinPriority.setAdapter(mSpinnerPriorityAdapter);
    }

    public void setFunctionData(ArrayList<FunctionItem> ls) {
        this.listFunction = ls;
        mSpinnerFunctionAdapter = new SpinnerFunctionAdapter(getContext(), R.layout.spinner_item, ls);
        mSpinnerFunctionAdapter.setDropDownViewResource(R.layout.spinner_drop_item);
        spinFunction.setAdapter(mSpinnerFunctionAdapter);
    }

    public void setFabricData(ArrayList<FabricItem> ls) {
        this.listFabric = ls;
        mSpinnerFabricAdapter = new SpinnerFabricAdapter(getContext(), R.layout.spinner_item, ls);
        mSpinnerFabricAdapter.setDropDownViewResource(R.layout.spinner_drop_item);
        spinFabric.setAdapter(mSpinnerFabricAdapter);
    }

    public void setDesignerData(ArrayList<DesignerItem> ls) {
        this.listDesigner = ls;
        mSpinnerDesignerAdapter = new SpinnerDesignerAdapter(getContext(), R.layout.spinner_item, ls);
        mSpinnerDesignerAdapter.setDropDownViewResource(R.layout.spinner_drop_item);
        spinDesigner.setAdapter(mSpinnerDesignerAdapter);
    }


    public void setData(M1Data md) {

        if(md.getIsTop().equalsIgnoreCase("0")){
            switchTop.setChecked(false);
        }else{
            switchTop.setChecked(true);
        }
        etTop.setText(md.getTopValue());

        if(md.getIsTopLining().equalsIgnoreCase("0")){
            switchTopLining.setChecked(false);
        }else{
            switchTopLining.setChecked(true);
        }
        etTopLining.setText(md.getTopLiningValue());

        if(md.getIsLace().equalsIgnoreCase("0")){
            switchLace.setChecked(false);
        }else{
            switchLace.setChecked(true);
        }
        etLace.setText(md.getLaceValue());

        if(md.getIsYoke().equalsIgnoreCase("0")){
            switchYoke.setChecked(false);
        }else{
            switchYoke.setChecked(true);
        }
        etYoke.setText(md.getYokeValue());

        if(md.getIsYokeLining().equalsIgnoreCase("0")){
            switchYokeLining.setChecked(false);
        }else{
            switchYokeLining.setChecked(true);
        }
        etYokeLining.setText(md.getYokeLiningValue());

        if(md.getIsSleeve().equalsIgnoreCase("0")){
            switchSleeve.setChecked(false);
        }else{
            switchSleeve.setChecked(true);
        }
        etSleeve.setText(md.getSleeveValue());

        if(md.getIsSleeveLin().equalsIgnoreCase("0")){
            switchSleeveLining.setChecked(false);
        }else{
            switchSleeveLining.setChecked(true);
        }
        etSleeveLining.setText(md.getSleeveLinValue());

        if(md.getIsBottom().equalsIgnoreCase("0")){
            switchBottom.setChecked(false);
        }else{
            switchBottom.setChecked(true);
        }
        etBottom.setText(md.getBottomValue());

        if(md.getIsBottomLining().equalsIgnoreCase("0")){
            switchBottomLining.setChecked(false);
        }else{
            switchBottomLining.setChecked(true);
        }
        etBottomLining.setText(md.getBottomLiningValue());

        /*if(md.getIsShawl().equalsIgnoreCase("0")){
            switchShawl.setChecked(false);
        }else{
            switchShawl.setChecked(true);
        }
        etShawl.setText(md.getShawlValue());*/

        if(md.getMeasurement().contains("top"))
            checkTop.setChecked(true);
        if(md.getMeasurement().contains("bottom"))
            checkBottom.setChecked(true);

        for(int i=0;i<listPriority.size();i++){
            if(md.getDesignerId().equalsIgnoreCase(listPriority.get(i).getId())){
                spinPriority.setSelection(i);
                break;
            }
        }

        for(int i=0;i<listFunction.size();i++){
            if(md.getFunction().equalsIgnoreCase(listFunction.get(i).getId())){
                spinFunction.setSelection(i);
                break;
            }
        }

        for(int i=0;i<listFabric.size();i++){
            if(md.getFabTypeId().equalsIgnoreCase(listFabric.get(i).getId())){
                spinFabric.setSelection(i);
                break;
            }
        }

        for(int i=0;i<listDesigner.size();i++){
            if(md.getDesignerId().equalsIgnoreCase(listDesigner.get(i).getId())){
                spinDesigner.setSelection(i);
                break;
            }
        }


        etDescription.setText(md.getRemarks());
    }
}
