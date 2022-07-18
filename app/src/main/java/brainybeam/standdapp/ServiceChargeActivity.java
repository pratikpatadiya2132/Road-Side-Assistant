package brainybeam.standdapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class ServiceChargeActivity extends AppCompatActivity {

    EditText accident_text, battery_text, clutch_text, fuel_text, key_text, towing_text, tyre_text, other_text;
    TextView submit;
    SharedPreferences sp;
    String sChargeStatus;
    double currentLatitude, currentLongitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_charge);
        getSupportActionBar().setTitle("Service Charges");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        sp = getSharedPreferences(ConstantSp.PREF,MODE_PRIVATE);
        accident_text = findViewById(R.id.service_charge_accident_charge);
        battery_text = findViewById(R.id.service_charge_bettery_jump_charge);
        clutch_text = findViewById(R.id.service_charge_clutch_break_charge);
        fuel_text = findViewById(R.id.service_charge_fuel_problem_charge);
        key_text = findViewById(R.id.service_charge_lost_key_charge);
        towing_text = findViewById(R.id.service_charge_towing_charge);
        tyre_text = findViewById(R.id.service_charge_tyre_damage_charge);
        other_text = findViewById(R.id.service_charge_other_charge);
        submit = findViewById(R.id.service_charge_submit);


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(accident_text.getText().toString().equals("")){
                    accident_text.setError("Accident Charge Required");
                }
                else if(battery_text.getText().toString().equals("")){
                    battery_text.setError("Battery Jump Start Charge Required");
                }
                else if(clutch_text.getText().toString().equals("")){
                    clutch_text.setError("Clutch/Break Problem Charge Required");
                }
                else if(fuel_text.getText().toString().equals("")){
                    fuel_text.setError("Fuel Problem Charge Required");
                }
                else if(key_text.getText().toString().equals("")){
                    key_text.setError("Lost/Locked Key Charge Required");
                }
                else if(towing_text.getText().toString().equals("")){
                    towing_text.setError("Towing Charge Required");
                }
                else if(tyre_text.getText().toString().equals("")){
                    tyre_text.setError("Tyre Damage Charge Required");
                }
                else if(other_text.getText().toString().equals("")){
                    other_text.setError("Other Charge Required");
                }
                else{
                    startActivity(new Intent(ServiceChargeActivity.this,ForemanDashboardActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                }
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id==android.R.id.home){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
