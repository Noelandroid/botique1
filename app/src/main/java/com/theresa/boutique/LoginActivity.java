package com.theresa.boutique;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.JsonElement;
import com.theresa.boutique.base.BaseActivity;
import com.theresa.boutique.util.Constants;

import net.bohush.geometricprogressview.GeometricProgressView;

import org.json.JSONArray;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.katso.livebutton.LiveButton;

import static com.theresa.boutique.util.Constants.LOGGEDIN;

public class LoginActivity extends BaseActivity {

    EditText etUsername, etPassword;
    LiveButton btnLogin;
    GeometricProgressView progressView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }

    private void initView() {

        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);
        btnLogin = (LiveButton) findViewById(R.id.btnLogin);
        progressView = (GeometricProgressView) findViewById(R.id.progressView);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isValidate()) {
                    login();
                }
            }
        });
    }

    private boolean isValidate() {
        if (etUsername.getText().toString().equalsIgnoreCase("")) {
            Toast.makeText(this, "Username Cannot be empty", Toast.LENGTH_SHORT).show();
            return false;
        } else if (etPassword.getText().toString().equalsIgnoreCase("")) {
            Toast.makeText(this, "Password Cannot be empty", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void login() {
        progressView.setVisibility(View.VISIBLE);
        Call<JsonElement> call = AppApplication.getApiService().login(etUsername.getText().toString(), etPassword.getText().toString());
        call.enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                try {
                    if (response.body() != null) {
                        JSONObject jsonObject = new JSONObject(response.body().getAsJsonObject().toString());
                        if (jsonObject.getString("ErrorCode").equalsIgnoreCase("0")) {
                            JSONArray jsonArray = jsonObject.getJSONArray("Data");
                            JSONObject temp = jsonArray.getJSONObject(0);

                            getSharedPreferenceHelper().putString(Constants.USERID, temp.getString("ID"));
                            getSharedPreferenceHelper().putBoolean(LOGGEDIN, true);
                            getSharedPreferenceHelper().putString(Constants.CMPID, temp.getString("CmpId"));
                            getSharedPreferenceHelper().putString(Constants.BRID, temp.getString("BRID"));
                            getSharedPreferenceHelper().putString(Constants.FAID, temp.getString("FAID"));
                            startActivity(new Intent(LoginActivity.this, CustomerRegistrationActivity.class));
                            finish();
                        } else {
                            Toast.makeText(LoginActivity.this, jsonObject.getString("Message"), Toast.LENGTH_SHORT).show();
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                progressView.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<JsonElement> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Something went Wrong!", Toast.LENGTH_SHORT).show();
                progressView.setVisibility(View.GONE);
            }
        });
    }
}
