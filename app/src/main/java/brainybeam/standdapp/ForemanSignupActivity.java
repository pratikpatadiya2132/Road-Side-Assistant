package brainybeam.standdapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Random;

public class ForemanSignupActivity extends AppCompatActivity {

    TextView submit,getOtp;
    EditText name,password,email,address,area,city,state,otp1,otp2,otp3,otp4,otp5,otp6;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    SharedPreferences sp;
    String sRendom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foreman_signup);
        getSupportActionBar().hide();
        sp = getSharedPreferences(ConstantSp.PREF,MODE_PRIVATE);
        name = findViewById(R.id.foreman_signup_name);
        email = findViewById(R.id.foreman_signup_mobile);
        password = findViewById(R.id.foreman_signup_password);
        address = findViewById(R.id.foreman_signup_address);
        area = findViewById(R.id.foreman_signup_area);
        city = findViewById(R.id.foreman_signup_city);
        state = findViewById(R.id.foreman_signup_state);
        otp1 = findViewById(R.id.foreman_signup_otp_1);
        otp2 = findViewById(R.id.foreman_signup_otp_2);
        otp3 = findViewById(R.id.foreman_signup_otp_3);
        otp4 = findViewById(R.id.foreman_signup_otp_4);
        otp5 = findViewById(R.id.foreman_signup_otp_5);
        otp6 = findViewById(R.id.foreman_signup_otp_6);
        submit = findViewById(R.id.foreman_signup_submit);
        getOtp = findViewById(R.id.foreman_signup_get_otp);

        getOtp.setVisibility(View.VISIBLE);
        submit.setVisibility(View.GONE);

        getOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (name.getText().toString().equals("")) {
                    name.setError("Name Required");
                }
                else if (email.getText().toString().equals("")) {
                    email.setError("Email Id Required");
                } else if (!email.getText().toString().matches(emailPattern)) {
                    email.setError("Valid Email Id Required");
                }
                else if (password.getText().toString().equals("")) {
                    password.setError("Password Required");
                }
                else {
                    Toast.makeText(ForemanSignupActivity.this, "Check Mail For OTP", Toast.LENGTH_SHORT).show();
                    getOtp.setVisibility(View.GONE);
                    submit.setVisibility(View.VISIBLE);
                    Random rnd = new Random();
                    int number = rnd.nextInt(999999);
                    sRendom = String.format("%06d", number);
                }
            }
        });

        otp1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(otp1.getText().toString().length()==1){
                    otp2.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        otp2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(otp2.getText().toString().length()==1){
                    otp3.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        otp3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(otp3.getText().toString().length()==1){
                    otp4.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        otp4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(otp4.getText().toString().length()==1){
                    otp5.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        otp5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(otp5.getText().toString().length()==1){
                    otp6.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });



        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(email.getText().toString().equals("")){
                    email.setError("Email Id Required");
                }
                else if(!email.getText().toString().matches(emailPattern)){
                    email.setError("Valid Email Id Required");
                }
                else{
                    String sOTP1 = otp1.getText().toString();
                    String sOTP2 = otp2.getText().toString();
                    String sOTP3 = otp3.getText().toString();
                    String sOTP4 = otp4.getText().toString();
                    String sOTP5 = otp5.getText().toString();
                    String sOTP6 = otp6.getText().toString();

                    String SOTP = sOTP1+sOTP2+sOTP3+sOTP4+sOTP5+sOTP6;
                    if(SOTP.equals("1234")) {
                        Toast.makeText(ForemanSignupActivity.this, "Signup Successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(ForemanSignupActivity.this, LoginActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                    }
                    else{
                        Toast.makeText(ForemanSignupActivity.this, "Invalid OTP Code", Toast.LENGTH_SHORT).show();
                    }
                }
                //startActivity(new Intent(ForemanSignupActivity.this, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
            }
        });

    }
}