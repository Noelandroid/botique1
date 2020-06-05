package com.theresa.boutique.fragment;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatDialogFragment;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.theresa.boutique.CustomerRegistrationActivity;
import com.theresa.boutique.R;
import com.theresa.boutique.adapter.SpinnerDesignerAdapter;
import com.theresa.boutique.interfaces.ViewPagerInterface;
import com.theresa.boutique.model.DesignerItem;
import com.theresa.boutique.model.M3Data;
import com.theresa.boutique.util.DisableSundayDecorator;

import org.threeten.bp.format.DateTimeFormatter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import ru.katso.livebutton.LiveButton;

public class M3Fragment extends Fragment {


    TextView tvItemName, etDoB, tvLabelTopMeasure, tvLabelWork, tvLabelLining, tvLabelFront, tvLabelBack, tvLabelYolkMeasure, tvLabelSkirtMeasure;
    LinearLayout llDoB;
    Calendar myCalendar = Calendar.getInstance();
    DatePickerDialog.OnDateSetListener date;
    EditText etTopMeasure, etSkirtMeasure, etFrontMeasure, etBackMeasure, etDescription;
    RadioGroup rgModel;
    SwitchCompat switchLining, switchWork;
    AppCompatSpinner spinDesigner;
    LiveButton btnFinish;
    ViewPagerInterface mListener;
    SpinnerDesignerAdapter mSpinnerDesignerAdapter;

    ArrayList<DesignerItem> listDesigner = new ArrayList<>();

    String isLiningM3 = "0", isWorkM3 = "0";
    String mType = "";

    /*public interface ClickListener {
        void onClickFinish();

        void onModelClick();
    }*/


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return init(container);
    }

    private View init(ViewGroup container) {
        isLiningM3 = "0";
        isWorkM3 = "0";
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragmentm3, container, false);
        mListener = (ViewPagerInterface) getActivity();
        tvItemName = (TextView) view.findViewById(R.id.tvItemName);
        tvLabelTopMeasure = (TextView) view.findViewById(R.id.tvLabelTopMeasure);
        tvLabelWork = (TextView) view.findViewById(R.id.tvLabelWork);
        tvLabelLining = (TextView) view.findViewById(R.id.tvLabelLining);
        tvLabelFront = (TextView) view.findViewById(R.id.tvLabelFront);
        tvLabelBack = (TextView) view.findViewById(R.id.tvLabelBack);
        tvLabelYolkMeasure = (TextView) view.findViewById(R.id.tvLabelYolkMeasure);
        tvLabelSkirtMeasure = (TextView) view.findViewById(R.id.tvLabelSkirtMeasure);

        etDoB = (TextView) view.findViewById(R.id.etDoB); //
        llDoB = (LinearLayout) view.findViewById(R.id.llDoB); //m1m1
        etTopMeasure = (EditText) view.findViewById(R.id.etTopMeasure); //m1
        etSkirtMeasure = (EditText) view.findViewById(R.id.etSkirtMeasure); //m1
        etFrontMeasure = (EditText) view.findViewById(R.id.etFrontMeasure);
        etBackMeasure = (EditText) view.findViewById(R.id.etBackMeasure);
        etDescription = (EditText) view.findViewById(R.id.etDescription);
        rgModel = (RadioGroup) view.findViewById(R.id.rgModel); //m1
        switchLining = (SwitchCompat) view.findViewById(R.id.switchLining);
        switchWork = (SwitchCompat) view.findViewById(R.id.switchWork);
        spinDesigner = (AppCompatSpinner) view.findViewById(R.id.spinDesigner);
        btnFinish = (LiveButton) view.findViewById(R.id.btnFinish);
        if (mType.toLowerCase().contains("churidar")) {
            try {

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (mType.toLowerCase().contains("anarkali")) {
            try {
                tvLabelTopMeasure.setText("Anarkali Measurement");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (mType.toLowerCase().contains("shawl")) {
            try {
                tvLabelTopMeasure.setVisibility(View.INVISIBLE);
                etTopMeasure.setVisibility(View.INVISIBLE);
                tvLabelYolkMeasure.setVisibility(View.GONE);
                tvLabelFront.setVisibility(View.GONE);
                etFrontMeasure.setVisibility(View.GONE);
                tvLabelBack.setVisibility(View.GONE);
                etBackMeasure.setVisibility(View.GONE);
                tvLabelLining.setVisibility(View.INVISIBLE);
                switchLining.setVisibility(View.INVISIBLE);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else if (mType.toLowerCase().contains("weddingnet")) {
            try {
                tvLabelTopMeasure.setVisibility(View.INVISIBLE);
                etTopMeasure.setVisibility(View.INVISIBLE);
                tvLabelYolkMeasure.setVisibility(View.GONE);
                tvLabelFront.setVisibility(View.GONE);
                etFrontMeasure.setVisibility(View.GONE);
                tvLabelBack.setVisibility(View.GONE);
                etBackMeasure.setVisibility(View.GONE);
                tvLabelLining.setVisibility(View.INVISIBLE);
                switchLining.setVisibility(View.INVISIBLE);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }







        else if (mType.toLowerCase().contains("saree")) {
            try {
                tvLabelTopMeasure.setVisibility(View.INVISIBLE);
                etTopMeasure.setVisibility(View.INVISIBLE);
                tvLabelYolkMeasure.setVisibility(View.GONE);
                tvLabelFront.setVisibility(View.GONE);
                etFrontMeasure.setVisibility(View.GONE);
                tvLabelBack.setVisibility(View.GONE);
                etBackMeasure.setVisibility(View.GONE);
                tvLabelLining.setVisibility(View.INVISIBLE);
                switchLining.setVisibility(View.INVISIBLE);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (mType.toLowerCase().contains("frock")) {
            try {
                tvLabelTopMeasure.setText("Frock Measurement");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (mType.toLowerCase().contains("blouse")) {
            try {
                tvLabelTopMeasure.setText("Blouse Measurement");
                tvLabelYolkMeasure.setVisibility(View.GONE);
                tvLabelFront.setVisibility(View.GONE);
                etFrontMeasure.setVisibility(View.GONE);
                tvLabelBack.setVisibility(View.GONE);
                etBackMeasure.setVisibility(View.GONE);
                tvLabelLining.setVisibility(View.INVISIBLE);
                switchLining.setVisibility(View.INVISIBLE);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else if (mType.toLowerCase().contains("weddingblouse")) {
            try {
                tvLabelTopMeasure.setText("Blouse Measurement");
                tvLabelYolkMeasure.setVisibility(View.GONE);
                tvLabelFront.setVisibility(View.GONE);
                etFrontMeasure.setVisibility(View.GONE);
                tvLabelBack.setVisibility(View.GONE);
                etBackMeasure.setVisibility(View.GONE);
                tvLabelLining.setVisibility(View.INVISIBLE);
                switchLining.setVisibility(View.INVISIBLE);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }




        else if (mType.toLowerCase().contains("top/skirt")) {
            try {
                tvLabelYolkMeasure.setVisibility(View.GONE);
                tvLabelFront.setVisibility(View.GONE);
                etFrontMeasure.setVisibility(View.GONE);
                tvLabelBack.setVisibility(View.GONE);
                etBackMeasure.setVisibility(View.GONE);
                tvLabelLining.setVisibility(View.INVISIBLE);
                switchLining.setVisibility(View.INVISIBLE);
                tvLabelSkirtMeasure.setVisibility(View.VISIBLE);
                etSkirtMeasure.setVisibility(View.VISIBLE);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (mType.toLowerCase().contains("gown")) {
            try {
                tvLabelTopMeasure.setText("Gown Measurement");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (mType.toLowerCase().contains("overcoat")) {
            try {
                tvLabelYolkMeasure.setVisibility(View.GONE);
                tvLabelFront.setVisibility(View.GONE);
                etFrontMeasure.setVisibility(View.GONE);
                tvLabelBack.setVisibility(View.GONE);
                etBackMeasure.setVisibility(View.GONE);
                tvLabelLining.setVisibility(View.INVISIBLE);
                switchLining.setVisibility(View.INVISIBLE);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        /*date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };*/
        llDoB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*new DatePickerDialog(getContext(), date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();*/
                SimpleCalendarDialogFragment s = new SimpleCalendarDialogFragment();
                s.setParent(M3Fragment.this);
                s.show(getChildFragmentManager(), "CalenderPicker");
            }
        });

        switchLining.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    isLiningM3 = "1";
                } else {
                    isLiningM3 = "0";
                }
            }
        });

        switchWork.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    isWorkM3 = "1";
                } else {
                    isWorkM3 = "0";
                }
            }
        });

        rgModel.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                Log.e("rgModel", "clicked");
                mListener.onModelClick();
            }
        });

        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onClickFinish();
            }
        });
    }

    /*private void createCalendarDialog(){
        String date = "";
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_calendar, null);
        MaterialCalendarView calendarView = view.findViewById(R.id.calendarView);

        builder.setCancelable(true);
        builder.setView(view)
                // Add action buttons
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        // sign in the user ...
                        activity.refresh();

                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }*/

    public static class SimpleCalendarDialogFragment extends AppCompatDialogFragment
            implements OnDateSelectedListener {

        private TextView textView;
        M3Fragment m3F;
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

            return new AlertDialog.Builder(getActivity())
                    .setTitle("Select Date")
                    .setView(view)
                    .setPositiveButton(android.R.string.ok, null)
                    .create();
        }

        public void setParent(M3Fragment m3){
            m3F = m3;
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


    public String isLiningM3() {
        return isLiningM3;
    }

    public String isWorkM3() {
        return isWorkM3;
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

    public String topMeasure() {
        return etTopMeasure.getText().toString();
    }

    public String skirtMeasure() {
        return etSkirtMeasure.getText().toString();
    }

    public String yokeFront() {
        return etFrontMeasure.getText().toString();
    }

    public String yokeBack() {
        return etBackMeasure.getText().toString();
    }

    public String designerM3Id() {
        return "1";
    }

    public String remarksM3() {
        return etDescription.getText().toString();
    }

    public void setDesignerData(ArrayList<DesignerItem> ls) {
        listDesigner = ls;
        mSpinnerDesignerAdapter = new SpinnerDesignerAdapter(getContext(), R.layout.spinner_item, ls);
        mSpinnerDesignerAdapter.setDropDownViewResource(R.layout.spinner_drop_item);
        spinDesigner.setAdapter(mSpinnerDesignerAdapter);
    }

    public void setData(M3Data md) {

        etTopMeasure.setText(md.getTopMeasure());

        etSkirtMeasure.setText(md.getSkirtMeasure());

        etFrontMeasure.setText(md.getYokeFront());

        etBackMeasure.setText(md.getYokeBack());

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

        if (md.getIsLiningM3().equalsIgnoreCase("1")) {
            switchLining.setChecked(true);
        } else {
            switchLining.setChecked(false);
        }

        if (md.getIsWorkM3().equalsIgnoreCase("1")) {
            switchWork.setChecked(true);
        } else {
            switchWork.setChecked(false);
        }

        for (int i = 0; i < listDesigner.size(); i++) {
            if (md.getM3DesignerId().equalsIgnoreCase(listDesigner.get(i).getId())) {
                spinDesigner.setSelection(i);
                break;
            }
        }
    }

    public void setDueDate(String date) {
        etDoB.setText(date);
    }

    public void setType(String type) {
        mType = type;
        if (type.toLowerCase().contains("churidar")) {
            try {

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (type.toLowerCase().contains("anarkali")) {
            try {
                tvLabelTopMeasure.setText("Anarkali Measurement");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (type.toLowerCase().contains("shawl")) {
            try {
                tvLabelTopMeasure.setVisibility(View.INVISIBLE);
                etTopMeasure.setVisibility(View.INVISIBLE);
                tvLabelYolkMeasure.setVisibility(View.GONE);
                tvLabelFront.setVisibility(View.GONE);
                etFrontMeasure.setVisibility(View.GONE);
                tvLabelBack.setVisibility(View.GONE);
                etBackMeasure.setVisibility(View.GONE);
                tvLabelLining.setVisibility(View.INVISIBLE);
                switchLining.setVisibility(View.INVISIBLE);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else if (type.toLowerCase().contains("weddingnet")) {
            try {
                tvLabelTopMeasure.setVisibility(View.INVISIBLE);
                etTopMeasure.setVisibility(View.INVISIBLE);
                tvLabelYolkMeasure.setVisibility(View.GONE);
                tvLabelFront.setVisibility(View.GONE);
                etFrontMeasure.setVisibility(View.GONE);
                tvLabelBack.setVisibility(View.GONE);
                etBackMeasure.setVisibility(View.GONE);
                tvLabelLining.setVisibility(View.INVISIBLE);
                switchLining.setVisibility(View.INVISIBLE);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }









        else if (type.toLowerCase().contains("saree")) {
            try {
                tvLabelTopMeasure.setVisibility(View.INVISIBLE);
                etTopMeasure.setVisibility(View.INVISIBLE);
                tvLabelYolkMeasure.setVisibility(View.GONE);
                tvLabelFront.setVisibility(View.GONE);
                etFrontMeasure.setVisibility(View.GONE);
                tvLabelBack.setVisibility(View.GONE);
                etBackMeasure.setVisibility(View.GONE);
                tvLabelLining.setVisibility(View.INVISIBLE);
                switchLining.setVisibility(View.INVISIBLE);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (type.toLowerCase().contains("frock")) {
            try {
                tvLabelTopMeasure.setText("Frock Measurement");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (type.toLowerCase().contains("blouse")) {
            try {
                tvLabelTopMeasure.setText("Blouse Measurement");
                tvLabelYolkMeasure.setVisibility(View.GONE);
                tvLabelFront.setVisibility(View.GONE);
                etFrontMeasure.setVisibility(View.GONE);
                tvLabelBack.setVisibility(View.GONE);
                etBackMeasure.setVisibility(View.GONE);
                tvLabelLining.setVisibility(View.INVISIBLE);
                switchLining.setVisibility(View.INVISIBLE);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else if (type.toLowerCase().contains("weddingblouse")) {
            try {
                tvLabelTopMeasure.setText("Blouse Measurement");
                tvLabelYolkMeasure.setVisibility(View.GONE);
                tvLabelFront.setVisibility(View.GONE);
                etFrontMeasure.setVisibility(View.GONE);
                tvLabelBack.setVisibility(View.GONE);
                etBackMeasure.setVisibility(View.GONE);
                tvLabelLining.setVisibility(View.INVISIBLE);
                switchLining.setVisibility(View.INVISIBLE);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }






        else if (type.toLowerCase().contains("top/skirt")) {
            try {
                tvLabelYolkMeasure.setVisibility(View.GONE);
                tvLabelFront.setVisibility(View.GONE);
                etFrontMeasure.setVisibility(View.GONE);
                tvLabelBack.setVisibility(View.GONE);
                etBackMeasure.setVisibility(View.GONE);
                tvLabelLining.setVisibility(View.INVISIBLE);
                switchLining.setVisibility(View.INVISIBLE);
                tvLabelSkirtMeasure.setVisibility(View.VISIBLE);
                etSkirtMeasure.setVisibility(View.VISIBLE);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (type.toLowerCase().contains("gown")) {
            try {
                tvLabelTopMeasure.setText("Gown Measurement");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (type.toLowerCase().contains("overcoat")) {
            try {
                tvLabelYolkMeasure.setVisibility(View.GONE);
                tvLabelFront.setVisibility(View.GONE);
                etFrontMeasure.setVisibility(View.GONE);
                tvLabelBack.setVisibility(View.GONE);
                etBackMeasure.setVisibility(View.GONE);
                tvLabelLining.setVisibility(View.INVISIBLE);
                switchLining.setVisibility(View.INVISIBLE);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
