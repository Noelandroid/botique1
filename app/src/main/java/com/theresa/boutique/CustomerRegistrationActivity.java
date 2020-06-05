package com.theresa.boutique;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonElement;
import com.theresa.boutique.base.BaseActivity;
import com.theresa.boutique.model.CustomerData;

import net.bohush.geometricprogressview.GeometricProgressView;

import org.json.JSONObject;
import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.katso.livebutton.LiveButton;

import static com.theresa.boutique.util.Constants.BRID;
import static com.theresa.boutique.util.Constants.CMPID;
import static com.theresa.boutique.util.Constants.FAID;
import static com.theresa.boutique.util.Constants.INTENT_CUSTNAME;
import static com.theresa.boutique.util.Constants.INTENT_HOUSENAME;
import static com.theresa.boutique.util.Constants.INTENT_MOBNUM;
import static com.theresa.boutique.util.Constants.SEARCHREQUESTCODE;
import static com.theresa.boutique.util.Constants.USERID;

public class CustomerRegistrationActivity extends BaseActivity {

    LiveButton btnSubmit;
    TextView etDoB, tvClear;
    EditText etMobileNumber, etCustomerName, etHouseName, etCO, etPlace, etPhoneNumber, etFacebook, etWhatsapp, etContactAddress, etEmailId, etRemarks;
    LinearLayout llDoB;
    ImageView imgMobileSearch, imgCustomerNameSearch, imgHouseNameSearch;
    String code, custId;
    GeometricProgressView progressView;
    boolean update = false;
    Calendar myCalendar = Calendar.getInstance();
    DatePickerDialog.OnDateSetListener date;
    AlertDialog ad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_registration);
        initView();
    }

    private void initView() {
        tvClear = (TextView) findViewById(R.id.tvClear);
        progressView = (GeometricProgressView) findViewById(R.id.progressView);
        btnSubmit = (LiveButton) findViewById(R.id.btnSubmit);
        etMobileNumber = (EditText) findViewById(R.id.etMobileNumber);
        etCustomerName = (EditText) findViewById(R.id.etCustomerName);
        etHouseName = (EditText) findViewById(R.id.etHouseName);
        etCO = (EditText) findViewById(R.id.etCO);
        etPlace = (EditText) findViewById(R.id.etPlace);
        etPhoneNumber = (EditText) findViewById(R.id.etPhoneNumber);
        etDoB = (TextView) findViewById(R.id.etDoB);
        etFacebook = (EditText) findViewById(R.id.etFacebook);
        etWhatsapp = (EditText) findViewById(R.id.etWhatsapp);
        etContactAddress = (EditText) findViewById(R.id.etContactAddress);
        etEmailId = (EditText) findViewById(R.id.etEmailId);
        etRemarks = (EditText) findViewById(R.id.etRemarks);
        llDoB = (LinearLayout) findViewById(R.id.llDoB);
        imgMobileSearch = (ImageView) findViewById(R.id.imgMobileSearch);
        imgCustomerNameSearch = (ImageView) findViewById(R.id.imgCustomerNameSearch);
        imgHouseNameSearch = (ImageView) findViewById(R.id.imgHouseNameSearch);


        date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };

        llDoB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(CustomerRegistrationActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isValidate()) {

                    if (!update)
                        ad = createSaveDialog();
                    else
                        ad = createUpdateDialog();

                    ad.show();
                }
            }
        });

        tvClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear();
            }
        });

        imgHouseNameSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String houseName = etHouseName.getText().toString();
                Intent intent = new Intent(CustomerRegistrationActivity.this, CustomerSearchActivity.class);
                intent.putExtra(INTENT_HOUSENAME, houseName);
                startActivityForResult(intent, SEARCHREQUESTCODE);
            }
        });

        imgCustomerNameSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String customerName = etCustomerName.getText().toString();
                Intent intent = new Intent(CustomerRegistrationActivity.this, CustomerSearchActivity.class);
                intent.putExtra(INTENT_CUSTNAME, customerName);
                startActivityForResult(intent, SEARCHREQUESTCODE);
            }
        });

        imgMobileSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mobileNum = etMobileNumber.getText().toString();
                Intent intent = new Intent(CustomerRegistrationActivity.this, CustomerSearchActivity.class);
                intent.putExtra(INTENT_MOBNUM, mobileNum);
                startActivityForResult(intent, SEARCHREQUESTCODE);
            }
        });

    }

    private void updateLabel() {
        String myFormat = "dd/MMM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        etDoB.setText(sdf.format(myCalendar.getTime()));
    }

    private boolean isValidate() {
        if (etMobileNumber.getText().toString().equalsIgnoreCase("")) {
            Toast.makeText(this, "Mobile Number Cannot be empty", Toast.LENGTH_SHORT).show();
            return false;
        } else if (etCustomerName.getText().toString().equalsIgnoreCase("")) {
            Toast.makeText(this, "Customer Name Cannot be empty", Toast.LENGTH_SHORT).show();
            return false;
        } else if (etHouseName.getText().toString().equalsIgnoreCase("")) {
            Toast.makeText(this, "House Name Cannot be empty", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private AlertDialog createSaveDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Save!")
        .setMessage(R.string.save_dialog_msg)
                .setPositiveButton(R.string.save, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        getAutoCode();
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        ad.cancel();
                    }
                });
        return builder.create();
    }

    private AlertDialog createUpdateDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Update!")
                .setMessage(R.string.save_dialog_msg)
                .setPositiveButton(R.string.save, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        updateCustomer();
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        ad.cancel();
                    }
                });
        return builder.create();
    }

    private void getAutoCode() {
        progressView.setVisibility(View.VISIBLE);
        Call<JsonElement> call = AppApplication.getApiService().getAutoCode(getSharedPreferenceHelper().getString(CMPID, ""), getSharedPreferenceHelper().getString(BRID, ""), getSharedPreferenceHelper().getString(FAID, ""));
        call.enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                try {
                    if (response.body() != null) {
                        JSONObject jsonObject = new JSONObject(response.body().getAsJsonObject().toString());
                        if (jsonObject.getString("ErrorCode").equalsIgnoreCase("0")) {
                            code = jsonObject.getString("Data");
                            saveCustomer();
                        } else {
                            Toast.makeText(CustomerRegistrationActivity.this, jsonObject.getString("Message"), Toast.LENGTH_SHORT).show();
                            progressView.setVisibility(View.GONE);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(CustomerRegistrationActivity.this, "Something went Wrong!", Toast.LENGTH_SHORT).show();
                    progressView.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<JsonElement> call, Throwable t) {
                Toast.makeText(CustomerRegistrationActivity.this, "Something went Wrong!", Toast.LENGTH_SHORT).show();
                progressView.setVisibility(View.GONE);
            }
        });
    }

    private void saveCustomer() {
        String UserId = getSharedPreferenceHelper().getString(USERID, ""),
                CmpId = getSharedPreferenceHelper().getString(CMPID, ""),
                BrId = getSharedPreferenceHelper().getString(BRID, ""),
                FaId = getSharedPreferenceHelper().getString(FAID, ""),
                MobNo = etMobileNumber.getText().toString(),
                CustName = etCustomerName.getText().toString(),
                HouseName = etHouseName.getText().toString(),
                CO = etCO.getText().toString(),
                Place = etPlace.getText().toString(),
                PhnNo = etPhoneNumber.getText().toString(),
                DoB = etDoB.getText().toString(),
                Facebook = etFacebook.getText().toString(),
                Whatsapp = etWhatsapp.getText().toString(),
                ContactAddress = etContactAddress.getText().toString(),
                EmailId = etEmailId.getText().toString(),
                Remarks = etRemarks.getText().toString();
        Call<JsonElement> call = AppApplication.getApiService().saveCustomer(CmpId, BrId, FaId, UserId, CustName, code, DoB, MobNo, HouseName, CO, ContactAddress, Place, PhnNo, EmailId, Whatsapp, Facebook, Remarks);
        call.enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                try {
                    if (response.body() != null) {
                        JSONObject jsonObject = new JSONObject(response.body().getAsJsonObject().toString());
                        if (jsonObject.getString("ErrorCode").equalsIgnoreCase("0")) {
                            Toast.makeText(CustomerRegistrationActivity.this, jsonObject.getString("Message"), Toast.LENGTH_SHORT).show();
//                            startActivityForResult(new Intent(CustomerRegistrationActivity.this, NewOrderActivity.class), 0);
                        } else {
                            Toast.makeText(CustomerRegistrationActivity.this, jsonObject.getString("Message"), Toast.LENGTH_SHORT).show();
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                progressView.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<JsonElement> call, Throwable t) {
                Toast.makeText(CustomerRegistrationActivity.this, "Something went Wrong!", Toast.LENGTH_SHORT).show();
                progressView.setVisibility(View.GONE);
            }
        });
    }

    private void updateCustomer() {
        String UserId = getSharedPreferenceHelper().getString(USERID, ""),
                CmpId = getSharedPreferenceHelper().getString(CMPID, ""),
                BrId = getSharedPreferenceHelper().getString(BRID, ""),
                FaId = getSharedPreferenceHelper().getString(FAID, ""),
                MobNo = etMobileNumber.getText().toString(),
                CustName = etCustomerName.getText().toString(),
                HouseName = etHouseName.getText().toString(),
                CO = etCO.getText().toString(),
                Place = etPlace.getText().toString(),
                PhnNo = etPhoneNumber.getText().toString(),
                DoB = etDoB.getText().toString(),
                Facebook = etFacebook.getText().toString(),
                Whatsapp = etWhatsapp.getText().toString(),
                ContactAddress = etContactAddress.getText().toString(),
                EmailId = etEmailId.getText().toString(),
                Remarks = etRemarks.getText().toString();
        Call<JsonElement> call = AppApplication.getApiService().updateCustomer(CmpId, BrId, FaId, UserId, custId, CustName, code, DoB, MobNo, HouseName, CO, ContactAddress, Place, PhnNo, EmailId, Whatsapp, Facebook, Remarks);
        call.enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                try {
                    if (response.body() != null) {
                        JSONObject jsonObject = new JSONObject(response.body().getAsJsonObject().toString());
                        if (jsonObject.getString("ErrorCode").equalsIgnoreCase("0")) {
                            Toast.makeText(CustomerRegistrationActivity.this, jsonObject.getString("Message"), Toast.LENGTH_SHORT).show();
//                            startActivityForResult(new Intent(CustomerRegistrationActivity.this, NewOrderActivity.class),0);
                        } else {
                            Toast.makeText(CustomerRegistrationActivity.this, jsonObject.getString("Message"), Toast.LENGTH_SHORT).show();
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                progressView.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<JsonElement> call, Throwable t) {
                Toast.makeText(CustomerRegistrationActivity.this, "Something went Wrong!", Toast.LENGTH_SHORT).show();
                progressView.setVisibility(View.GONE);
            }
        });
    }

    private void clear() {
        update = false;
        custId = "";
        etCustomerName.setText("");
        etMobileNumber.setText("");
        etCO.setText("");
        etContactAddress.setText("");
        etDoB.setText("");
        etEmailId.setText("");
        etFacebook.setText("");
        etHouseName.setText("");
        etPhoneNumber.setText("");
        etPlace.setText("");
        etRemarks.setText("");
        etWhatsapp.setText("");
        btnSubmit.setText("Save");
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        etCustomerName.setText("");
        etMobileNumber.setText("");
        etCO.setText("");
        etContactAddress.setText("");
        etDoB.setText("");
        etEmailId.setText("");
        etFacebook.setText("");
        etHouseName.setText("");
        etPhoneNumber.setText("");
        etPlace.setText("");
        etRemarks.setText("");
        etWhatsapp.setText("");
        if (requestCode == SEARCHREQUESTCODE) {
            if (resultCode == RESULT_OK) {
                CustomerData cd = data.getParcelableExtra("CustomerData");
                code = cd.getCode();
                custId = cd.getCustId();
                Log.e("CustId", custId);
                etCustomerName.setText(cd.getCustName());
                etMobileNumber.setText(cd.getMobileNo());
                etCO.setText(cd.getCO());
                etContactAddress.setText(cd.getContactAdd());
                etDoB.setText(cd.getDoB());
                etEmailId.setText(cd.getEmailId());
                etFacebook.setText(cd.getFacebook());
                etHouseName.setText(cd.getHouseName());
                etPhoneNumber.setText(cd.getPhoneNo());
                etPlace.setText(cd.getPlace());
                etRemarks.setText(cd.getRemarks());
                etWhatsapp.setText(cd.getWhatsapp());
                update = true;
                btnSubmit.setText("Update");
            }
        }
    }

}
