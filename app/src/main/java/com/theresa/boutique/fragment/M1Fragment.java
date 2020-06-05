package com.theresa.boutique.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatDialogFragment;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonElement;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.theresa.boutique.AppApplication;
import com.theresa.boutique.MeasurementActivity;
import com.theresa.boutique.R;
import com.theresa.boutique.adapter.M1ListAdapter;
import com.theresa.boutique.adapter.SpinnerAlterTypeAdapter;
import com.theresa.boutique.adapter.SpinnerDesignerAdapter;
import com.theresa.boutique.adapter.SpinnerFabricAdapter;
import com.theresa.boutique.adapter.SpinnerFunctionAdapter;
import com.theresa.boutique.adapter.SpinnerPriorityAdapter;
import com.theresa.boutique.interfaces.ViewPagerInterface;
import com.theresa.boutique.model.AlterItem;
import com.theresa.boutique.model.DesignerItem;
import com.theresa.boutique.model.FabricItem;
import com.theresa.boutique.model.FunctionItem;
import com.theresa.boutique.model.M1Data;
import com.theresa.boutique.model.M1ListItem;
import com.theresa.boutique.model.M3Data;
import com.theresa.boutique.model.PriorityItem;
import com.theresa.boutique.util.Constants;
import com.theresa.boutique.util.DisablePastDaysDecorator;
import com.theresa.boutique.util.DisableSundayDecorator;
import com.theresa.boutique.util.Utils;

import org.json.JSONArray;
import org.json.JSONObject;
import org.threeten.bp.format.DateTimeFormatter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import javax.crypto.spec.DHGenParameterSpec;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.katso.livebutton.LiveButton;

import static com.theresa.boutique.util.Constants.BRID;
import static com.theresa.boutique.util.Constants.CMPID;

public class M1Fragment extends Fragment implements M1ListAdapter.ClickListener {
//from m3
Calendar myCalendar = Calendar.getInstance();
    DatePickerDialog.OnDateSetListener date;
    RadioGroup rgModel;

    AppCompatSpinner spinPriority, spinFunction, spinFabric, spinDesigner;
    private AppCompatSpinner spinAlter;
    SpinnerAlterTypeAdapter mSpinnerAlterTypeAdapter;
    ArrayList<AlterItem> listAlterType = new ArrayList<>();
    TextView tvLabelSkirt, tvLabelSkirtOthers, tvLabelMeasurement, tvLabelAlterType, etDoB;
    LinearLayout llSkirt, llSkirtLining, llSkirtSpeakerNet, llSkirtSpeakerNetLining, llAlterType, llDoB;
    EditText etDescription, etOthers, etSkirt, etSkirtLining, etSkirtSpeakerNet, etSkirtSpeakerNetLining, etSkirtOthers,  etTopMeasure,etSkirtMeasure;
    SwitchCompat switchSkirt, switchSkirtLining, switchSkirtSpeakerNet, switchSkirtSpeakerNetLining;
    //    RadioGroup rgMeasurement;
    AppCompatCheckBox checkTop, checkBottom;
    RecyclerView rvList;
    LiveButton btnNext;
    M1ListAdapter mAdapter;
    SpinnerPriorityAdapter mSpinnerPriorityAdapter;
    SpinnerFabricAdapter mSpinnerFabricAdapter;
    SpinnerFunctionAdapter mSpinnerFunctionAdapter;
    SpinnerDesignerAdapter mSpinnerDesignerAdapter;
    ViewPagerInterface mListener;
    ArrayList<PriorityItem> listPriority = new ArrayList<>();
    ArrayList<FabricItem> listFabric = new ArrayList<>();
    ArrayList<FunctionItem> listFunction = new ArrayList<>();
    ArrayList<DesignerItem> listDesigner = new ArrayList<>();
    ArrayList<M1ListItem> mList = new ArrayList<>();
    String mType = "", alterType = "";
    int showAlter = View.INVISIBLE;

    String isTop = "0", isTopLining = "0", isLace = "0", isYoke = "0", isYokeLining = "0", isSleeve = "0",
            isSleeveLining = "0", isBottom = "0", isBottomLining = "0", isShawl = "0",isWeddingnet="0", isSpeakerNet = "0", isSpeakerNetLining = "0",
            isPiping = "0", isPipingLining = "0", isSkirt = "0", isSkirtLining = "0", isSkirtSpeakerNet = "0", isSkirtSpeakerNetLining = "0", isWorkingPiece = "0";

    String priority = "", fabric = "", function = "", designer = "";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return init(container);
    }

    private View init(ViewGroup container) {
        isTop = "0";
        isTopLining = "0";
        isLace = "0";
        isYoke = "0";
        isYokeLining = "0";
        isSleeve = "0";
        isSleeveLining = "0";
        isBottom = "0";
        isBottomLining = "0";
        isShawl = "0";
        isWeddingnet="0";
        isSpeakerNet = "0";
        isSpeakerNetLining = "0";
        isPiping = "0";
        isPipingLining = "0";
        isSkirt = "0";
        isSkirtLining = "0";
        isSkirtSpeakerNet = "0";
        isSkirtSpeakerNetLining = "0";
        isWorkingPiece = "0";
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragmentm1, container, false);
        mListener = (ViewPagerInterface) getActivity();
        spinPriority = (AppCompatSpinner) view.findViewById(R.id.spinPriority);
        spinFunction = (AppCompatSpinner) view.findViewById(R.id.spinFunction);
        spinFabric = (AppCompatSpinner) view.findViewById(R.id.spinFabric);
        spinDesigner = (AppCompatSpinner) view.findViewById(R.id.spinDesigner);
        spinAlter = (AppCompatSpinner) view.findViewById(R.id.spinAlterType);
        tvLabelSkirt = (TextView) view.findViewById(R.id.tvLabelSkirt);
        tvLabelSkirtOthers = (TextView) view.findViewById(R.id.tvLabelSkirtOthers);
        tvLabelMeasurement = (TextView) view.findViewById(R.id.tvLabelMeasurement);
        tvLabelAlterType = (TextView) view.findViewById(R.id.tvLabelAlterType);
        llAlterType = (LinearLayout) view.findViewById(R.id.llSpinAlterType);
        llSkirt = (LinearLayout) view.findViewById(R.id.llSkirt);
        llSkirtLining = (LinearLayout) view.findViewById(R.id.llSkirtLining);
        llSkirtSpeakerNet = (LinearLayout) view.findViewById(R.id.llSkirtSpeakerNet);
        llSkirtSpeakerNetLining = (LinearLayout) view.findViewById(R.id.llSkirtSpeakerNetLining);
//from m3
        etDoB = (TextView) view.findViewById(R.id.etDoB); //m1
        llDoB = (LinearLayout) view.findViewById(R.id.llDoB); //m1
        etTopMeasure = (EditText) view.findViewById(R.id.etTopMeasure); //m1
        etSkirtMeasure = (EditText) view.findViewById(R.id.etSkirtMeasure); //m1
        rgModel = (RadioGroup) view.findViewById(R.id.rgModel); //m1


        rvList = (RecyclerView) view.findViewById(R.id.rvList);
        etOthers = (EditText) view.findViewById(R.id.etOthers);
        etSkirt = (EditText) view.findViewById(R.id.etSkirt);
        etSkirtLining = (EditText) view.findViewById(R.id.etSkirtLining);
        etSkirtSpeakerNet = (EditText) view.findViewById(R.id.etSkirtSpeakertNet);
        etSkirtSpeakerNetLining = (EditText) view.findViewById(R.id.etSkirtSpeakerNetLining);
        etSkirtOthers = (EditText) view.findViewById(R.id.etSkirtOthers);
        etDescription = (EditText) view.findViewById(R.id.etDescription);

        switchSkirt = (SwitchCompat) view.findViewById(R.id.switchSkirt);
        switchSkirtLining = (SwitchCompat) view.findViewById(R.id.switchSkirtLining);
        switchSkirtSpeakerNet = (SwitchCompat) view.findViewById(R.id.switchSkirtSpeakerNet);
        switchSkirtSpeakerNetLining = (SwitchCompat) view.findViewById(R.id.switchSkirtSpeakerNetLining);

//        rgMeasurement = (RadioGroup) view.findViewById(R.id.rgMeasurment);
        checkTop = (AppCompatCheckBox) view.findViewById(R.id.checkTop);
        checkBottom = (AppCompatCheckBox) view.findViewById(R.id.checkBottom);
        btnNext = (LiveButton) view.findViewById(R.id.btnNext);
        Log.e("Type", mType);
        if (mType.toLowerCase().contains("top/skirt")) {
            tvLabelSkirt.setVisibility(View.VISIBLE);
            tvLabelSkirtOthers.setVisibility(View.VISIBLE);
            llSkirt.setVisibility(View.VISIBLE);
            llSkirtLining.setVisibility(View.VISIBLE);
            llSkirtSpeakerNet.setVisibility(View.VISIBLE);
            llSkirtSpeakerNetLining.setVisibility(View.VISIBLE);
            etSkirtOthers.setVisibility(View.VISIBLE);
        } else if (mType.toLowerCase().contains("shawl")) {
            tvLabelMeasurement.setVisibility(View.INVISIBLE);
            checkBottom.setVisibility(View.INVISIBLE);
            checkTop.setVisibility(View.INVISIBLE);

        } else if (mType.toLowerCase().contains("weddingnet")) {
            tvLabelMeasurement.setVisibility(View.INVISIBLE);
            checkBottom.setVisibility(View.INVISIBLE);
            checkTop.setVisibility(View.INVISIBLE);





        }










        else if (mType.toLowerCase().contains("saree")) {
            tvLabelMeasurement.setVisibility(View.INVISIBLE);
            checkBottom.setVisibility(View.INVISIBLE);
            checkTop.setVisibility(View.INVISIBLE);
        } else if (mType.toLowerCase().contains("frock")) {
            checkBottom.setVisibility(View.INVISIBLE);
            checkTop.setText("Frock");
        } else if (mType.toLowerCase().contains("blouse")) {
            tvLabelMeasurement.setVisibility(View.INVISIBLE);
            checkBottom.setVisibility(View.INVISIBLE);
            checkTop.setVisibility(View.INVISIBLE);
        }
        else if (mType.toLowerCase().contains("weddingblouse")) {
            tvLabelMeasurement.setVisibility(View.INVISIBLE);
            checkBottom.setVisibility(View.INVISIBLE);
            checkTop.setVisibility(View.INVISIBLE);
        }



        else if (mType.toLowerCase().contains("gown")) {
            checkBottom.setVisibility(View.INVISIBLE);
            checkTop.setText("Gown");
        } else if (mType.toLowerCase().contains("overcoat")) {
            checkBottom.setVisibility(View.INVISIBLE);
            checkTop.setText("Overcoat");
        }

        tvLabelAlterType.setVisibility(showAlter);
        llAlterType.setVisibility(showAlter);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validate())
                mListener.onNextClicked();
            }
        });

        return view;
    }

    private boolean validate(){
        for(int i=0; i<mList.size();i++){
            if (mList.get(i).isSwitchOn()){
                if(mList.get(i).getValue().equalsIgnoreCase("")){
                    switch (mList.get(i).getId()){
                        case Constants.TOP:
                            Toast.makeText(getContext(), "Enter value to field Top", Toast.LENGTH_SHORT).show();
                            break;
                        case Constants.TOP_LINING:Toast.makeText(getContext(), "Enter value to field Top Lining", Toast.LENGTH_SHORT).show();
                            break;
                        case Constants.YOKE:Toast.makeText(getContext(), "Enter value to field Yoke", Toast.LENGTH_SHORT).show();
                            break;
                        case Constants.YOKE_LINING:Toast.makeText(getContext(), "Enter value to field Yoke Lining", Toast.LENGTH_SHORT).show();
                            break;
                        case Constants.SLEEVE:Toast.makeText(getContext(), "Enter value to field Sleeve", Toast.LENGTH_SHORT).show();
                            break;
                        case Constants.SLEEVE_LINING:Toast.makeText(getContext(), "Enter value to field Sleeve Lining", Toast.LENGTH_SHORT).show();
                            break;
                        case Constants.LACE:Toast.makeText(getContext(), "Enter value to field Lace", Toast.LENGTH_SHORT).show();
                            break;
                        case Constants.SPEAKERNET:Toast.makeText(getContext(), "Enter value to field SpeakerNet", Toast.LENGTH_SHORT).show();
                            break;
                        case Constants.SPEAKERNET_LINING:Toast.makeText(getContext(), "Enter value to field SpeakerNet Lining", Toast.LENGTH_SHORT).show();
                            break;
                        case Constants.BOTTOM:Toast.makeText(getContext(), "Enter value to field Bottom", Toast.LENGTH_SHORT).show();
                            break;
                        case Constants.BOTTOM_LINING:Toast.makeText(getContext(), "Enter value to field Bottom Lining", Toast.LENGTH_SHORT).show();
                            break;
                        case Constants.PIPING:Toast.makeText(getContext(), "Enter value to field Piping", Toast.LENGTH_SHORT).show();
                            break;
                        case Constants.PIPING_LINING:Toast.makeText(getContext(), "Enter value to field Piping Lining", Toast.LENGTH_SHORT).show();
                            break;
                        case Constants.WORK_PIECE:Toast.makeText(getContext(), "Enter value to field Work Piece", Toast.LENGTH_SHORT).show();
                            break;

                    }
                    return false;
                }
            }
        }

        return true;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        businessLogic();
        llDoB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*new DatePickerDialog(getContext(), date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();*/
                M1Fragment.SimpleCalendarDialogFragment s = new M1Fragment.SimpleCalendarDialogFragment();
                s.setParent(M1Fragment.this);
                s.show(getChildFragmentManager(), "CalenderPicker");
            }
        });

        rgModel.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                Log.e("rgModel", "clicked");
                mListener.onModelClick();
            }
        });
    }

    public static class SimpleCalendarDialogFragment extends AppCompatDialogFragment
            implements OnDateSelectedListener {

        private TextView textView;
        M1Fragment m3F;
        private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("EEE, d MMM yyyy");
        private static final DateTimeFormatter FORMATTER1 = DateTimeFormatter.ofPattern("dd/MMM/yyyy");

        @NonNull
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {

            LayoutInflater inflater = getActivity().getLayoutInflater();

            //inflate custom layout and get views
            //pass null as parent view because will be in dialog layout
            View view = inflater.inflate(R.layout.dialog_calendar, null);

            textView = view.findViewById(R.id.textView);

            MaterialCalendarView widget = view.findViewById(R.id.calendarView);

            widget.setOnDateChangedListener(this);
            widget.addDecorator(new DisableSundayDecorator());
            widget.addDecorator(new DisablePastDaysDecorator());
            return new AlertDialog.Builder(getActivity())
                    .setTitle("Select Date")
                    .setView(view)
                    .setPositiveButton(android.R.string.ok, null)
                    .create();
        }

        public void setParent(M1Fragment m1){
            m3F = m1;
        }

        @Override
        public void onDateSelected(
                @NonNull MaterialCalendarView widget,
                @NonNull CalendarDay date,
                boolean selected) {
            textView.setText(FORMATTER.format(date.getDate()));

            m3F.updateLabel(FORMATTER1.format(date.getDate()));
        }
    }

    private void updateLabel() {
        String myFormat = "dd/MMM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        etDoB.setText(sdf.format(myCalendar.getTime()));
    }

    public void updateLabel(String s) {
        /*String myFormat = "dd/MMM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
*/
        etDoB.setText(s);
    }

    private void businessLogic() {
//        list = getPriorityData();
        Log.e("List size", mList.size() + "");
        rvList.setLayoutManager(new GridLayoutManager(getContext(), 3));
        mAdapter = new M1ListAdapter(mList, getActivity(), this);
        rvList.setAdapter(mAdapter);

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

        spinAlter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                alterType = listAlterType.get(position).getName();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                if (listAlterType.size() > 0) {
                    alterType = listAlterType.get(0).getName();
                } else {
                    alterType = "";
                }
            }
        });


        switchSkirt.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Log.e("SkirtSwitch","onCheckListener");
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

    public void setAlterTypeData(ArrayList<AlterItem> ls) {
        this.listAlterType = ls;
        mSpinnerAlterTypeAdapter = new SpinnerAlterTypeAdapter(getContext(), R.layout.spinner_item, ls);
        mSpinnerAlterTypeAdapter.setDropDownViewResource(R.layout.spinner_drop_item);
        spinAlter.setAdapter(mSpinnerAlterTypeAdapter);
    }

    public String isTop() {
        for (int i = 0; i < mList.size(); i++) {
            if (mList.get(i).getId() == Constants.TOP)
                if (mList.get(i).isSwitchOn())
                    isTop = "1";
                else
                    isTop = "0";
        }
        return isTop;
    }

    public String isTopLining() {
        for (int i = 0; i < mList.size(); i++) {
            if (mList.get(i).getId() == Constants.TOP_LINING)
                if (mList.get(i).isSwitchOn())
                    isTopLining = "1";
                else
                    isTopLining = "0";
        }
        return isTopLining;
    }

    public String isLace() {
        for (int i = 0; i < mList.size(); i++) {
            if (mList.get(i).getId() == Constants.LACE)
                if (mList.get(i).isSwitchOn())
                    isLace = "1";
                else
                    isLace = "0";
        }
        return isLace;
    }

    public String isYoke() {
        for (int i = 0; i < mList.size(); i++) {
            if (mList.get(i).getId() == Constants.YOKE)
                if (mList.get(i).isSwitchOn())
                    isYoke = "1";
                else
                    isYoke = "0";
        }
        return isYoke;
    }

    public String getAlterationType() {
        return alterType;
    }

    public String isYokeLining() {
        for (int i = 0; i < mList.size(); i++) {
            if (mList.get(i).getId() == Constants.YOKE_LINING)
                if (mList.get(i).isSwitchOn())
                    isYokeLining = "1";
                else
                    isYokeLining = "0";
        }
        return isYokeLining;
    }

    public String isSleeveLining() {
        for (int i = 0; i < mList.size(); i++) {
            if (mList.get(i).getId() == Constants.SLEEVE_LINING)
                if (mList.get(i).isSwitchOn())
                    isSleeveLining = "1";
                else
                    isSleeveLining = "0";
        }
        return isSleeveLining;
    }

    public String isBottom() {
        for (int i = 0; i < mList.size(); i++) {
            if (mList.get(i).getId() == Constants.BOTTOM)
                if (mList.get(i).isSwitchOn())
                    isBottom = "1";
                else
                    isBottom = "0";
        }
        return isBottom;
    }

    public String isBottomLining() {
        for (int i = 0; i < mList.size(); i++) {
            if (mList.get(i).getId() == Constants.BOTTOM_LINING)
                if (mList.get(i).isSwitchOn())
                    isBottomLining = "1";
                else
                    isBottomLining = "0";
        }
        return isBottomLining;
    }

    public String isShawl() {
        return isShawl;
    }
    public String isWeddingnet() {
        return isWeddingnet;
    }

    public String topVal() {
        String val = "";
        for (int i = 0; i < mList.size(); i++) {
            if (mList.get(i).getId() == Constants.TOP)
                val = mList.get(i).getValue();
        }
        return val;
    }

    public String topLiningVal() {
        String val = "";
        for (int i = 0; i < mList.size(); i++) {
            if (mList.get(i).getId() == Constants.TOP_LINING)
                val = mList.get(i).getValue();
        }
        return val;
    }

    public String laceVal() {
        String val = "";
        for (int i = 0; i < mList.size(); i++) {
            if (mList.get(i).getId() == Constants.LACE)
                val = mList.get(i).getValue();
        }
        return val;
    }

    public String yokeVal() {
        String val = "";
        for (int i = 0; i < mList.size(); i++) {
            if (mList.get(i).getId() == Constants.YOKE)
                val = mList.get(i).getValue();
        }
        return val;
    }

    public String yokeLiningVal() {
        String val = "";
        for (int i = 0; i < mList.size(); i++) {
            if (mList.get(i).getId() == Constants.YOKE_LINING)
                val = mList.get(i).getValue();
        }
        return val;
    }

    public String isSleeve() {
        for (int i = 0; i < mList.size(); i++) {
            if (mList.get(i).getId() == Constants.SLEEVE)
                if (mList.get(i).isSwitchOn())
                    isSleeve = "1";
                else
                    isSleeve = "0";
        }
        return isSleeve;
    }

    public String sleeveVal() {
        String val = "";
        for (int i = 0; i < mList.size(); i++) {
            if (mList.get(i).getId() == Constants.SLEEVE)
                val = mList.get(i).getValue();
        }
        return val;
    }

    public String sleeveLiningVal() {
        String val = "";
        for (int i = 0; i < mList.size(); i++) {
            if (mList.get(i).getId() == Constants.SLEEVE_LINING)
                val = mList.get(i).getValue();
        }
        return val;
    }

    public String bottomVal() {
        String val = "";
        for (int i = 0; i < mList.size(); i++) {
            if (mList.get(i).getId() == Constants.BOTTOM)
                val = mList.get(i).getValue();
        }
        return val;
    }

    public String bottomLiningVal() {
        String val = "";
        for (int i = 0; i < mList.size(); i++) {
            if (mList.get(i).getId() == Constants.BOTTOM_LINING)
                val = mList.get(i).getValue();
        }
        return val;
    }

    public String shawlVal() {
        return /*etShawl.getText().toString()*/"";
    }
    public String weddingVal(){
        return "" ;
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

    public String isWorkingPiece() {
        for (int i = 0; i < mList.size(); i++) {
            if (mList.get(i).getId() == Constants.WORK_PIECE)
                if (mList.get(i).isSwitchOn())
                    isWorkingPiece = "1";
                else
                    isWorkingPiece = "0";
        }
        return isWorkingPiece;
    }

    public String workPiece() {
        String val = "";
        for (int i = 0; i < mList.size(); i++) {
            if (mList.get(i).getId() == Constants.WORK_PIECE)
                val = mList.get(i).getValue();
        }
        return val;
    }

    public String isSpeakerNet() {
        for (int i = 0; i < mList.size(); i++) {
            if (mList.get(i).getId() == Constants.SPEAKERNET)
                if (mList.get(i).isSwitchOn())
                    isSpeakerNet = "1";
                else
                    isSpeakerNet = "0";
        }
        return isSpeakerNet;
    }

    public String speakerNet() {
        String val = "";
        if (mType.toLowerCase().contains("top/skirt")) {
            val = etSkirtSpeakerNet.getText().toString();
        }
        else{
            for (int i = 0; i < mList.size(); i++) {
                if (mList.get(i).getId() == Constants.SPEAKERNET)
                    val = mList.get(i).getValue();
            }
        }
        return val;
    }

    public String isSpeakerNetLining() {
        for (int i = 0; i < mList.size(); i++) {
            if (mList.get(i).getId() == Constants.SPEAKERNET_LINING)
                if (mList.get(i).isSwitchOn())
                    isSpeakerNetLining = "1";
                else
                    isSpeakerNetLining = "0";
        }
        return isSpeakerNetLining;
    }

    public String speakerNetLining() {
        String val = "";
        if (mType.toLowerCase().contains("top/skirt")) {
            val = etSkirtSpeakerNetLining.getText().toString();
        }else {
            for (int i = 0; i < mList.size(); i++) {
                if (mList.get(i).getId() == Constants.SPEAKERNET_LINING)
                    val = mList.get(i).getValue();
            }
        }
        return val;
    }

    public String isPiping() {
        for (int i = 0; i < mList.size(); i++) {
            if (mList.get(i).getId() == Constants.PIPING)
                if (mList.get(i).isSwitchOn())
                    isPiping = "1";
                else
                    isPiping = "0";
        }
        return isPiping;
    }

    public String piping() {
        String val = "";
        for (int i = 0; i < mList.size(); i++) {
            if (mList.get(i).getId() == Constants.PIPING)
                val = mList.get(i).getValue();
        }
        return val;
    }

    public String isPipingLining() {
        for (int i = 0; i < mList.size(); i++) {
            if (mList.get(i).getId() == Constants.PIPING_LINING)
                if (mList.get(i).isSwitchOn())
                    isPipingLining = "1";
                else
                    isPipingLining = "0";
        }
        return isPipingLining;
    }

    public String pipingLining() {
        String val = "";
        for (int i = 0; i < mList.size(); i++) {
            if (mList.get(i).getId() == Constants.PIPING_LINING)
                val = mList.get(i).getValue();
        }
        return val;
    }
    public String modelVal() {
        if (rgModel.getCheckedRadioButtonId() != -1) {
            int id = rgModel.getCheckedRadioButtonId();
            View radioButton = rgModel.findViewById(id);
            int radioId = rgModel.indexOfChild(radioButton);
            RadioButton btn = (RadioButton) rgModel.getChildAt(radioId);
            String selection = (String) btn.getText();
            return selection;
        }
        return "";

    }

    public String dueDateVal() {
        return etDoB.getText().toString();
    }


    public String isSkirt() {
        return isSkirt;
    }

    public String skirt() {
        return etSkirt.getText().toString();
    }

    public String isSkirtLining() {
        return isSkirtLining;
    }

    public String others() {
        return etOthers.getText().toString();
    }

    public String skirtOthers() {
        return etSkirtOthers.getText().toString();
    }

    public String skirtLining() {
        return etSkirtLining.getText().toString();
    }

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


    public void setType(String type) {
        mType = type;
        mList.clear();
        Log.e("List type", type);
        if (type.toLowerCase().contains("churidar")) {
//            mList.addAll(createChuridarList());
            mList = createChuridarList();
        } else if (type.toLowerCase().contains("anarkali")) {
            mList = createAnarkaliList();
        } else if (type.toLowerCase().contains("shawl")) {
            mList = createShawlList();
            try {
                tvLabelMeasurement.setVisibility(View.INVISIBLE);
                checkBottom.setVisibility(View.INVISIBLE);
                checkTop.setVisibility(View.INVISIBLE);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else if (type.toLowerCase().contains("weddingnet")) {
            mList = createShawlList();
            try {
                tvLabelMeasurement.setVisibility(View.INVISIBLE);
                checkBottom.setVisibility(View.INVISIBLE);
                checkTop.setVisibility(View.INVISIBLE);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }







        else if (type.toLowerCase().contains("saree")) {
            mList = createSareeList();
            try {
                tvLabelMeasurement.setVisibility(View.INVISIBLE);
                checkBottom.setVisibility(View.INVISIBLE);
                checkTop.setVisibility(View.INVISIBLE);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (type.toLowerCase().contains("frock")) {
            mList = createFrockList();
            try {
                checkBottom.setVisibility(View.INVISIBLE);
                checkTop.setText("Frock");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (type.toLowerCase().contains("blouse")) {
            mList = createBlouseList();
            try {
                tvLabelMeasurement.setVisibility(View.INVISIBLE);
                checkBottom.setVisibility(View.INVISIBLE);
                checkTop.setVisibility(View.INVISIBLE);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (type.toLowerCase().contains("top/skirt")) {
            mList = createTopSkirtList();
            try {
                Log.e("Type", type);
                tvLabelSkirt.setVisibility(View.VISIBLE);
                tvLabelSkirtOthers.setVisibility(View.VISIBLE);
                llSkirt.setVisibility(View.VISIBLE);
                llSkirtLining.setVisibility(View.VISIBLE);
                llSkirtSpeakerNet.setVisibility(View.VISIBLE);
                llSkirtSpeakerNetLining.setVisibility(View.VISIBLE);
                etSkirtOthers.setVisibility(View.VISIBLE);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (type.toLowerCase().contains("gown")) {
            mList = createGownList();
            try {
                checkBottom.setVisibility(View.INVISIBLE);
                checkTop.setText("Gown");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (type.toLowerCase().contains("overcoat")) {
            mList = createOvercoatList();
            try {
                checkBottom.setVisibility(View.INVISIBLE);
                checkTop.setText("Overcoat");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

//        mAdapter.notifyDataSetChanged();
    }

    private ArrayList<M1ListItem> createBlouseList() {
        ArrayList<M1ListItem> list = new ArrayList<>();

        M1ListItem item = new M1ListItem();
        item.setId(Constants.TOP);
        item.setHintText("Blouse");
        list.add(item);

        M1ListItem item1 = new M1ListItem();
        item1.setId(Constants.TOP_LINING);
        item1.setHintText("Blouse Lining");
        list.add(item1);


        M1ListItem item4 = new M1ListItem();
        item4.setId(Constants.SLEEVE);
        item4.setHintText("Sleeve");
        list.add(item4);

        M1ListItem item5 = new M1ListItem();
        item5.setId(Constants.SLEEVE_LINING);
        item5.setHintText("Sleeve Lining");
        list.add(item5);

        M1ListItem item6 = new M1ListItem();
        item6.setId(Constants.LACE);
        item6.setHintText("Lace");
        list.add(item6);

        return list;
    }

    private ArrayList<M1ListItem> createTopSkirtList() {
        ArrayList<M1ListItem> list = new ArrayList<>();

        M1ListItem item = new M1ListItem();
        item.setId(Constants.TOP);
        item.setHintText("Top");
        list.add(item);

        M1ListItem item1 = new M1ListItem();
        item1.setId(Constants.TOP_LINING);
        item1.setHintText("Top Lining");
        list.add(item1);

        M1ListItem item2 = new M1ListItem();
        item2.setId(Constants.YOKE);
        item2.setHintText("Yoke");
        list.add(item2);

        M1ListItem item3 = new M1ListItem();
        item3.setId(Constants.YOKE_LINING);
        item3.setHintText("Yoke Lining");
        list.add(item3);

        M1ListItem item4 = new M1ListItem();
        item4.setId(Constants.SLEEVE);
        item4.setHintText("Sleeve");
        list.add(item4);

        M1ListItem item5 = new M1ListItem();
        item5.setId(Constants.SLEEVE_LINING);
        item5.setHintText("Sleeve Lining");
        list.add(item5);
        return list;

    }

    private ArrayList<M1ListItem> createSareeList() {
        ArrayList<M1ListItem> list = new ArrayList<>();

        M1ListItem item = new M1ListItem();
        item.setId(Constants.TOP);
        item.setHintText("Saree");
        list.add(item);

        M1ListItem item6 = new M1ListItem();
        item6.setId(Constants.LACE);
        item6.setHintText("Lace");
        list.add(item6);

        M1ListItem item11 = new M1ListItem();
        item11.setId(Constants.PIPING);
        item11.setHintText("Piping");
        list.add(item11);

        M1ListItem item12 = new M1ListItem();
        item12.setId(Constants.PIPING_LINING);
        item12.setHintText("Piping Lining");
        list.add(item12);

        M1ListItem item13 = new M1ListItem();
        item13.setId(Constants.WORK_PIECE);
        item13.setHintText("Work Piece");
        list.add(item13);
        return list;
    }

    private ArrayList<M1ListItem> createAnarkaliList() {
        ArrayList<M1ListItem> list = new ArrayList<>();

        M1ListItem item = new M1ListItem();
        item.setId(Constants.TOP);
        item.setHintText("Top");
        list.add(item);

        M1ListItem item1 = new M1ListItem();
        item1.setId(Constants.TOP_LINING);
        item1.setHintText("Top Lining");
        list.add(item1);

        M1ListItem item2 = new M1ListItem();
        item2.setId(Constants.YOKE);
        item2.setHintText("Yoke");
        list.add(item2);

        M1ListItem item3 = new M1ListItem();
        item3.setId(Constants.YOKE_LINING);
        item3.setHintText("Yoke Lining");
        list.add(item3);

        M1ListItem item4 = new M1ListItem();
        item4.setId(Constants.SLEEVE);
        item4.setHintText("Sleeve");
        list.add(item4);

        M1ListItem item5 = new M1ListItem();
        item5.setId(Constants.SLEEVE_LINING);
        item5.setHintText("Sleeve Lining");
        list.add(item5);

        M1ListItem item6 = new M1ListItem();
        item6.setId(Constants.LACE);
        item6.setHintText("Lace");
        list.add(item6);

        M1ListItem item9 = new M1ListItem();
        item9.setId(Constants.BOTTOM);
        item9.setHintText("Bottom");
        list.add(item9);

        M1ListItem item10 = new M1ListItem();
        item10.setId(Constants.BOTTOM_LINING);
        item10.setHintText("Bottom Lining");
        list.add(item10);
        return list;

    }

    private ArrayList<M1ListItem> createShawlList() {
        ArrayList<M1ListItem> list = new ArrayList<>();

        M1ListItem item = new M1ListItem();
        item.setId(Constants.TOP);
        item.setHintText("Shawl");
        list.add(item);

        M1ListItem item6 = new M1ListItem();
        item6.setId(Constants.LACE);
        item6.setHintText("Lace");
        list.add(item6);

        M1ListItem item11 = new M1ListItem();
        item11.setId(Constants.PIPING);
        item11.setHintText("Piping");
        list.add(item11);

        M1ListItem item12 = new M1ListItem();
        item12.setId(Constants.PIPING_LINING);
        item12.setHintText("Piping Lining");
        list.add(item12);

        M1ListItem item13 = new M1ListItem();
        item13.setId(Constants.WORK_PIECE);
        item13.setHintText("Work Piece");
        list.add(item13);
        return list;
    }















    private ArrayList<M1ListItem> createOvercoatList() {
        ArrayList<M1ListItem> list = new ArrayList<>();

        M1ListItem item = new M1ListItem();
        item.setId(Constants.TOP);
        item.setHintText("Overcoat");
        list.add(item);

        M1ListItem item1 = new M1ListItem();
        item1.setId(Constants.TOP_LINING);
        item1.setHintText("Overcoat Lining");
        list.add(item1);

        M1ListItem item4 = new M1ListItem();
        item4.setId(Constants.SLEEVE);
        item4.setHintText("Sleeve");
        list.add(item4);

        M1ListItem item5 = new M1ListItem();
        item5.setId(Constants.SLEEVE_LINING);
        item5.setHintText("Sleeve Lining");
        list.add(item5);

        M1ListItem item6 = new M1ListItem();
        item6.setId(Constants.LACE);
        item6.setHintText("Lace");
        list.add(item6);

        /*M1ListItem item7 = new M1ListItem();
        item7.setId(Constants.SPEAKERNET);
        item7.setHintText("SpeakerNet");
        list.add(item7);

        M1ListItem item8 = new M1ListItem();
        item8.setId(Constants.SPEAKERNET_LINING);
        item8.setHintText("SpeakerNet Lining");
        list.add(item8);

        M1ListItem item9 = new M1ListItem();
        item9.setId(Constants.BOTTOM);
        item9.setHintText("Bottom");
        list.add(item9);

        M1ListItem item10 = new M1ListItem();
        item10.setId(Constants.BOTTOM_LINING);
        item10.setHintText("Bottom Lining");
        list.add(item10);*/

        M1ListItem item11 = new M1ListItem();
        item11.setId(Constants.PIPING);
        item11.setHintText("Piping");
        list.add(item11);

        /*M1ListItem item12 = new M1ListItem();
        item12.setId(Constants.PIPING_LINING);
        item12.setHintText("Piping Lining");
        list.add(item12);*/
        return list;
    }

    private ArrayList<M1ListItem> createChuridarList() {
        ArrayList<M1ListItem> list = new ArrayList<>();

        M1ListItem item = new M1ListItem();
        item.setId(Constants.TOP);
        item.setHintText("Top");
        list.add(item);

        M1ListItem item1 = new M1ListItem();
        item1.setId(Constants.TOP_LINING);
        item1.setHintText("Top Lining");
        list.add(item1);

        M1ListItem item2 = new M1ListItem();
        item2.setId(Constants.YOKE);
        item2.setHintText("Yoke");
        list.add(item2);

        M1ListItem item3 = new M1ListItem();
        item3.setId(Constants.YOKE_LINING);
        item3.setHintText("Yoke Lining");
        list.add(item3);

        M1ListItem item4 = new M1ListItem();
        item4.setId(Constants.SLEEVE);
        item4.setHintText("Sleeve");
        list.add(item4);

        M1ListItem item5 = new M1ListItem();
        item5.setId(Constants.SLEEVE_LINING);
        item5.setHintText("Sleeve Lining");
        list.add(item5);

        M1ListItem item6 = new M1ListItem();
        item6.setId(Constants.LACE);
        item6.setHintText("Lace");
        list.add(item6);

        M1ListItem item9 = new M1ListItem();
        item9.setId(Constants.BOTTOM);
        item9.setHintText("Bottom");
        list.add(item9);

        M1ListItem item10 = new M1ListItem();
        item10.setId(Constants.BOTTOM_LINING);
        item10.setHintText("Bottom Lining");
        list.add(item10);
        return list;
    }

    private ArrayList<M1ListItem> createGownList() {
        ArrayList<M1ListItem> list = new ArrayList<>();

        M1ListItem item = new M1ListItem();
        item.setId(Constants.TOP);
        item.setHintText("Main Material");
        list.add(item);

        M1ListItem item1 = new M1ListItem();
        item1.setId(Constants.TOP_LINING);
        item1.setHintText("Material Lining");
        list.add(item1);

        M1ListItem item2 = new M1ListItem();
        item2.setId(Constants.YOKE);
        item2.setHintText("Yoke");
        list.add(item2);

        M1ListItem item3 = new M1ListItem();
        item3.setId(Constants.YOKE_LINING);
        item3.setHintText("Yoke Lining");
        list.add(item3);

        M1ListItem item4 = new M1ListItem();
        item4.setId(Constants.SLEEVE);
        item4.setHintText("Sleeve");
        list.add(item4);

        M1ListItem item5 = new M1ListItem();
        item5.setId(Constants.SLEEVE_LINING);
        item5.setHintText("Sleeve Lining");
        list.add(item5);

        M1ListItem item6 = new M1ListItem();
        item6.setId(Constants.LACE);
        item6.setHintText("Lace");
        list.add(item6);

        M1ListItem item7 = new M1ListItem();
        item7.setId(Constants.SPEAKERNET);
        item7.setHintText("SpeakerNet");
        list.add(item7);

        M1ListItem item8 = new M1ListItem();
        item8.setId(Constants.SPEAKERNET_LINING);
        item8.setHintText("SpeakerNet Lining");
        list.add(item8);

        return list;

    }

    private ArrayList<M1ListItem> createFrockList() {
        ArrayList<M1ListItem> list = new ArrayList<>();

        M1ListItem item = new M1ListItem();
        item.setId(Constants.TOP);
        item.setHintText("Frock");
        list.add(item);

        M1ListItem item1 = new M1ListItem();
        item1.setId(Constants.TOP_LINING);
        item1.setHintText("Frock Lining");
        list.add(item1);

        M1ListItem item2 = new M1ListItem();
        item2.setId(Constants.YOKE);
        item2.setHintText("Yoke");
        list.add(item2);

        M1ListItem item3 = new M1ListItem();
        item3.setId(Constants.YOKE_LINING);
        item3.setHintText("Yoke Lining");
        list.add(item3);

        M1ListItem item4 = new M1ListItem();
        item4.setId(Constants.SLEEVE);
        item4.setHintText("Sleeve");
        list.add(item4);

        M1ListItem item5 = new M1ListItem();
        item5.setId(Constants.SLEEVE_LINING);
        item5.setHintText("Sleeve Lining");
        list.add(item5);

        M1ListItem item6 = new M1ListItem();
        item6.setId(Constants.LACE);
        item6.setHintText("Lace");
        list.add(item6);

        M1ListItem item7 = new M1ListItem();
        item7.setId(Constants.SPEAKERNET);
        item7.setHintText("SpeakerNet");
        list.add(item7);

        M1ListItem item8 = new M1ListItem();
        item8.setId(Constants.SPEAKERNET_LINING);
        item8.setHintText("SpeakerNet Lining");
        list.add(item8);

        return list;
    }
public  void  setData(M3Data md){
    etDoB.setText(md.getDueDate());
    Log.e("onModelClick", "Data set");
    switch (md.getModel()) {

        case "SM":
            rgModel.check(R.id.rbSM);
            break;
        case "MM":
            rgModel.check(R.id.rbMM);
            break;
        case "HM":
            rgModel.check(R.id.rbHM);
            break;
        case "EXH":
            rgModel.check(R.id.rbEXH);
            break;
    }

}
    public void setDueDate(String date) {
        etDoB.setText(date);
    }

    public void setData(M1Data md) {


        for (int i = 0; i < mList.size(); i++) {
            switch (mList.get(i).getId()) {
                case Constants.TOP:
                    Log.e("IsTop", md.getIsTop());
                    if (md.getIsTop().equalsIgnoreCase("1")) {
                        mList.get(i).setSwitchOn(true);
                        mList.get(i).setValue(md.getTopValue());
                    } else
                        mList.get(i).setSwitchOn(false);

                    break;
                case Constants.TOP_LINING:
                    if (md.getIsTopLining().equalsIgnoreCase("1")) {
                        mList.get(i).setSwitchOn(true);
                        mList.get(i).setValue(md.getTopLiningValue());
                    } else
                        mList.get(i).setSwitchOn(false);

                    break;
                case Constants.YOKE:
                    if (md.getIsYoke().equalsIgnoreCase("1")) {
                        mList.get(i).setSwitchOn(true);
                        mList.get(i).setValue(md.getYokeValue());
                    } else
                        mList.get(i).setSwitchOn(false);

                    break;
                case Constants.YOKE_LINING:
                    if (md.getIsYokeLining().equalsIgnoreCase("1")) {
                        mList.get(i).setSwitchOn(true);
                        mList.get(i).setValue(md.getYokeLiningValue());
                    } else
                        mList.get(i).setSwitchOn(false);

                    break;
                case Constants.SLEEVE:
                    if (md.getIsSleeve().equalsIgnoreCase("1")) {
                        mList.get(i).setSwitchOn(true);
                        mList.get(i).setValue(md.getSleeveValue());
                    } else
                        mList.get(i).setSwitchOn(false);

                    break;
                case Constants.SLEEVE_LINING:
                    if (md.getIsSleeveLin().equalsIgnoreCase("1")) {
                        mList.get(i).setSwitchOn(true);
                        mList.get(i).setValue(md.getSleeveLinValue());
                    } else
                        mList.get(i).setSwitchOn(false);

                    break;
                case Constants.LACE:
                    if (md.getIsLace().equalsIgnoreCase("1")) {
                        mList.get(i).setSwitchOn(true);
                        mList.get(i).setValue(md.getLaceValue());
                    } else
                        mList.get(i).setSwitchOn(false);

                    break;
                case Constants.SPEAKERNET:
                    if (md.getIsSpeakerNet().equalsIgnoreCase("1")) {
                        mList.get(i).setSwitchOn(true);
                        mList.get(i).setValue(md.getSpeakerNet());
                    } else
                        mList.get(i).setSwitchOn(false);

                    break;
                case Constants.SPEAKERNET_LINING:
                    if (md.getIsSpeakerNetLining().equalsIgnoreCase("1")) {
                        mList.get(i).setSwitchOn(true);
                        mList.get(i).setValue(md.getSpeakerNetLining());
                    } else
                        mList.get(i).setSwitchOn(false);

                    break;
                case Constants.BOTTOM:
                    if (md.getIsBottom().equalsIgnoreCase("1")) {
                        mList.get(i).setSwitchOn(true);
                        mList.get(i).setValue(md.getBottomValue());
                    } else
                        mList.get(i).setSwitchOn(false);

                    break;
                case Constants.BOTTOM_LINING:
                    if (md.getIsBottomLining().equalsIgnoreCase("1")) {
                        mList.get(i).setSwitchOn(true);
                        mList.get(i).setValue(md.getBottomLiningValue());
                    } else
                        mList.get(i).setSwitchOn(false);

                    break;
                case Constants.PIPING:
                    if (md.getIsPiping().equalsIgnoreCase("1")) {
                        mList.get(i).setSwitchOn(true);
                        mList.get(i).setValue(md.getPiping());
                    } else
                        mList.get(i).setSwitchOn(false);

                    break;
                case Constants.PIPING_LINING:
                    if (md.getIsPipingLining().equalsIgnoreCase("1")) {
                        mList.get(i).setSwitchOn(true);
                        mList.get(i).setValue(md.getPipingLining());
                    } else
                        mList.get(i).setSwitchOn(false);

                    break;
                case Constants.WORK_PIECE:
                    if (md.getIsWorkPiece().equalsIgnoreCase("1")) {
                        mList.get(i).setSwitchOn(true);
                        mList.get(i).setValue(md.getWorkPiece());
                    } else
                        mList.get(i).setSwitchOn(false);

                    break;
            }
        }
        mAdapter.notifyDataSetChanged();

        etOthers.setText(md.getOthers());

        if (md.getIsSkirt().equalsIgnoreCase("1"))
            switchSkirt.setChecked(true);
        else
            switchSkirt.setChecked(false);

        etSkirt.setText(md.getSkirt());

        if (md.getIsSkirtLining().equalsIgnoreCase("1"))
            switchSkirtLining.setChecked(true);
        else
            switchSkirtLining.setChecked(false);

        etSkirtLining.setText(md.getSkirtLining());

        if (md.getIsSkirtSpeakerNet().equalsIgnoreCase("1"))
            switchSkirtSpeakerNet.setChecked(true);
        else
            switchSkirtSpeakerNet.setChecked(false);

        etSkirtSpeakerNet.setText(md.getSpeakerNet());

        if (md.getIsSkirtSpeakerNetLining().equalsIgnoreCase("1"))
            switchSkirtSpeakerNetLining.setChecked(true);
        else
            switchSkirtSpeakerNetLining.setChecked(false);

        etSkirtSpeakerNetLining.setText(md.getSpeakerNetLining());


        etSkirtOthers.setText(md.getSkirtOthers());

        if (md.getMeasurement().contains("top"))
            checkTop.setChecked(true);
        if (md.getMeasurement().contains("bottom"))
            checkBottom.setChecked(true);

        for (int i = 0; i < listPriority.size(); i++) {
            if (md.getPriority().equalsIgnoreCase(listPriority.get(i).getId())) {
                spinPriority.setSelection(i);
                break;
            }
        }

        for (int i = 0; i < listFunction.size(); i++) {
            if (md.getFunction().equalsIgnoreCase(listFunction.get(i).getId())) {

                spinFunction.setSelection(i);
                break;
            }
        }

        for (int i = 0; i < listFabric.size(); i++) {
            if (md.getFabTypeId().equalsIgnoreCase(listFabric.get(i).getId())) {
                spinFabric.setSelection(i);
                break;
            }
        }

        for (int i = 0; i < listDesigner.size(); i++) {
            if (md.getDesignerId().equalsIgnoreCase(listDesigner.get(i).getId())) {
                spinDesigner.setSelection(i);
                break;
            }
        }

        for (int i = 0; i < listAlterType.size(); i++) {
            if (md.getAlterationType().equalsIgnoreCase(listAlterType.get(i).getName())) {
                spinAlter.setSelection(i);
                break;
            }
        }


        etDescription.setText(md.getRemarks());
    }

    public void hideAlterationType(int x) {
        showAlter = x;
        try {
            tvLabelAlterType.setVisibility(x);
            llAlterType.setVisibility(x);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClickSwitch(int position, boolean switchOn) {
        mList.get(position).setSwitchOn(switchOn);
        mAdapter.notifyItemChanged(position);
    }

    @Override
    public void onValueChange(int position, String text) {
        mList.get(position).setValue(text);
//        mAdapter.notifyItemChanged(position);
    }
}
