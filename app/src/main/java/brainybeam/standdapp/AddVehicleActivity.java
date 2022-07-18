package brainybeam.standdapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class AddVehicleActivity extends AppCompatActivity {

    AutoCompleteTextView autoCompleteTextView;
    Spinner modelSpinner, yearSpinner;

    //String[] year = {"2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020"};
    //String[] autoArray = {"Honda Activa 125", "Yamaha Fascino", "Yamaha Fz S Fi V 2.0"};
    String[] modelArray1 = {"Standard"};
    String[] modelArray2 = {"Standard"};
    String[] modelArray3 = {"FZ S FI (V 2.0) Dual Disc", "FZ S FI (V 2.0) STD"};
    ArrayAdapter modelAdapter;

    ImageView back;

    TextView cancel, add;

    SharedPreferences sp;
    RadioGroup rg;
    RadioButton addVehicle2WRb, addVehicle4WRb, addVehicleCVRb;

    EditText edtRegNo,edtColour,edtVin;

    ArrayList<String> year;
    ArrayList<String> autoArray;
    ArrayList<String> modelArray;

    String sType;
    String ADD_VEHICLE_MODEL_SP,ADD_VEHICLE_YEAR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_vehicle);
        sp = getSharedPreferences(ConstantSp.PREF, MODE_PRIVATE);
        edtRegNo = findViewById(R.id.add_vehicle_registration_no);
        edtColour = findViewById(R.id.add_vehicle_colour);
        edtVin = findViewById(R.id.add_vehicle_vin);

        cancel = findViewById(R.id.add_vehicle_cancel);
        add = findViewById(R.id.add_vehicle_add);
        back = findViewById(R.id.add_vehicle_back);
        autoCompleteTextView = findViewById(R.id.add_vehicle_make_model);
        modelSpinner = findViewById(R.id.add_vehicle_model_spinner);
        yearSpinner = findViewById(R.id.add_vehicle_year);

        rg = findViewById(R.id.add_vehicle_type_rg);
        addVehicle2WRb = findViewById(R.id.add_vehicle_type_2w);
        addVehicle4WRb = findViewById(R.id.add_vehicle_type_4w);
        addVehicleCVRb = findViewById(R.id.add_vehicle_type_cv);

        if (sp.getString(ConstantSp.ADD_VEHICLE_ADD_UPDATE, "").equals("Edit")) {
            add.setText("Update");
            String ADD_VEHICLE_TYPE = sp.getString(ConstantSp.ADD_VEHICLE_TYPE, "");
            String ADD_VEHICLE_REGISTRATION_NO = sp.getString(ConstantSp.ADD_VEHICLE_REGISTRATION_NO, "");
            String ADD_VEHICLE_MODEL = sp.getString(ConstantSp.ADD_VEHICLE_MODEL, "");
            ADD_VEHICLE_MODEL_SP = sp.getString(ConstantSp.ADD_VEHICLE_MODEL_SP, "");
            String ADD_VEHICLE_COLOUR = sp.getString(ConstantSp.ADD_VEHICLE_COLOUR, "");
            ADD_VEHICLE_YEAR = sp.getString(ConstantSp.ADD_VEHICLE_YEAR, "");
            String ADD_VEHICLE_VIN = sp.getString(ConstantSp.ADD_VEHICLE_VIN, "");

            edtRegNo.setText(ADD_VEHICLE_REGISTRATION_NO);
            edtColour.setText(ADD_VEHICLE_COLOUR);
            edtVin.setText(ADD_VEHICLE_VIN);

            if (ADD_VEHICLE_TYPE.equals("2W")) {
                addVehicle2WRb.setChecked(true);
                addVehicle4WRb.setChecked(false);
                addVehicleCVRb.setChecked(false);
            } else if (ADD_VEHICLE_TYPE.equals("4W")) {
                addVehicle2WRb.setChecked(false);
                addVehicle4WRb.setChecked(true);
                addVehicleCVRb.setChecked(false);
            } else if (ADD_VEHICLE_TYPE.equals("CV")) {
                addVehicle2WRb.setChecked(false);
                addVehicle4WRb.setChecked(false);
                addVehicleCVRb.setChecked(true);
            } else {
                Toast.makeText(this, ADD_VEHICLE_TYPE, Toast.LENGTH_SHORT).show();
                addVehicle2WRb.setChecked(false);
                addVehicle4WRb.setChecked(false);
                addVehicleCVRb.setChecked(false);
            }
            sType = ADD_VEHICLE_TYPE;
            /*if(new ConnectionDetector(AddVehicleActivity.this).isConnectingToInternet()){
                new getAutoArray().execute();
                new getModelArray().execute();
            }
            else{
                new ConnectionDetector(AddVehicleActivity.this).connectiondetect();
            }*/

            /*for(int i=0;i<year.size();i++){
                if(ADD_VEHICLE_YEAR.equals(year.get(i))){
                    yearSpinner.setSelection(i);
                }
            }*/

            rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int i) {
                    int id = rg.getCheckedRadioButtonId();
                    RadioButton rb = findViewById(id);
                    sType = rb.getText().toString();
                    /*if(new ConnectionDetector(AddVehicleActivity.this).isConnectingToInternet()){
                        new getAutoArray().execute();
                    }
                    else{
                        new ConnectionDetector(AddVehicleActivity.this).connectiondetect();
                    }*/
                }
            });

            autoCompleteTextView.setText(ADD_VEHICLE_MODEL);
            autoCompleteTextView.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void afterTextChanged(Editable editable) {
                        for(int i=0;i<autoArray.size();i++){
                            if(autoArray.get(i).equals(autoCompleteTextView.getText().toString())){
                                /*if(new ConnectionDetector(AddVehicleActivity.this).isConnectingToInternet()){
                                    new getModelArray().execute();
                                }
                                else{
                                    new ConnectionDetector(AddVehicleActivity.this).connectiondetect();
                                }*/
                            }
                            else{

                            }
                        }
                }
            });
            /*for(int i=0;i<autoArray.size();i++){
                if(autoArray.get(i).equals(autoCompleteTextView.getText().toString())){
                    if(new ConnectionDetector(AddVehicleActivity.this).isConnectingToInternet()){
                        new getModelArray().execute();
                    }
                    else{
                        new ConnectionDetector(AddVehicleActivity.this).connectiondetect();
                    }
                }
                else{

                }
            }*/

            /*if (ADD_VEHICLE_MODEL.equals("Honda Activa 125")) {
                modelAdapter = new ArrayAdapter(AddVehicleActivity.this, android.R.layout.simple_list_item_1, modelArray1);
                modelSpinner.setAdapter(modelAdapter);
            } else if (ADD_VEHICLE_MODEL.equals("Yamaha Fascino")) {
                modelAdapter = new ArrayAdapter(AddVehicleActivity.this, android.R.layout.simple_list_item_1, modelArray2);
                modelSpinner.setAdapter(modelAdapter);
            } else if (ADD_VEHICLE_MODEL.equals("Yamaha Fz S Fi V 2.0")) {
                modelAdapter = new ArrayAdapter(AddVehicleActivity.this, android.R.layout.simple_list_item_1, modelArray3);
                modelSpinner.setAdapter(modelAdapter);
            } else {
                modelSpinner.setAdapter(null);
            }*/

        }
        else{
            add.setText("Add");
            rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int i) {
                    int id = rg.getCheckedRadioButtonId();
                    RadioButton rb = findViewById(id);
                    sType = rb.getText().toString();
                    /*if(new ConnectionDetector(AddVehicleActivity.this).isConnectingToInternet()){
                        new getAutoArray().execute();
                    }
                    else{
                        new ConnectionDetector(AddVehicleActivity.this).connectiondetect();
                    }*/
                }
            });

            autoCompleteTextView.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void afterTextChanged(Editable editable) {
                    if(rg.getCheckedRadioButtonId()==-1){
                        Toast.makeText(AddVehicleActivity.this, "Please Select Vehicle Type", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        if(autoCompleteTextView.getText().toString().equals("")){
                            modelSpinner.setAdapter(null);
                        }
                        else{
                            for(int i=0;i<autoArray.size();i++){
                                if(autoArray.get(i).equals(autoCompleteTextView.getText().toString())){
                                    /*if(new ConnectionDetector(AddVehicleActivity.this).isConnectingToInternet()){
                                        new getModelArray().execute();
                                    }
                                    else{
                                        new ConnectionDetector(AddVehicleActivity.this).connectiondetect();
                                    }*/
                                }
                                else{

                                }
                            }
                        }
                    /*if (autoCompleteTextView.getText().toString().equals("Honda Activa 125")) {
                        modelAdapter = new ArrayAdapter(AddVehicleActivity.this, android.R.layout.simple_list_item_1, modelArray1);
                        modelSpinner.setAdapter(modelAdapter);
                    } else if (autoCompleteTextView.getText().toString().equals("Yamaha Fascino")) {
                        modelAdapter = new ArrayAdapter(AddVehicleActivity.this, android.R.layout.simple_list_item_1, modelArray2);
                        modelSpinner.setAdapter(modelAdapter);
                    } else if (autoCompleteTextView.getText().toString().equals("Yamaha Fz S Fi V 2.0")) {
                        modelAdapter = new ArrayAdapter(AddVehicleActivity.this, android.R.layout.simple_list_item_1, modelArray3);
                        modelSpinner.setAdapter(modelAdapter);
                    } else {
                        modelSpinner.setAdapter(null);
                    }*/
                    }
                }
            });
        }

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(rg.getCheckedRadioButtonId()==-1){
                    Toast.makeText(AddVehicleActivity.this, "Please Select Vehicle Type", Toast.LENGTH_SHORT).show();
                }
                else if(edtRegNo.getText().toString().equals("")){
                    edtRegNo.setError("Registration No. Required");
                }
                else if(autoCompleteTextView.getText().toString().equals("")){
                    autoCompleteTextView.setError("Make / Model Required");
                }
                else if(modelSpinner.getSelectedItemId()<0){
                    Toast.makeText(AddVehicleActivity.this, "Model Required", Toast.LENGTH_SHORT).show();
                }
                else if(edtColour.getText().toString().equals("")){
                    edtColour.setError("Colour Required");
                }
                else if(yearSpinner.getSelectedItemId()<0){
                    Toast.makeText(AddVehicleActivity.this, "Year Required", Toast.LENGTH_SHORT).show();
                }
                else if(edtVin.getText().toString().equals("")){
                    edtVin.setError("VIN Number Required");
                }
                else{
                    Toast.makeText(AddVehicleActivity.this, "Vehicle Added Successfully", Toast.LENGTH_SHORT).show();
                    onBackPressed();
                    /*if(sp.getString(ConstantSp.ADD_VEHICLE_ADD_UPDATE, "").equals("Edit")){
                        if (new ConnectionDetector(AddVehicleActivity.this).isConnectingToInternet()) {
                            new updateVehicle().execute();
                        } else {
                            new ConnectionDetector(AddVehicleActivity.this).connectiondetect();
                        }
                    }
                    else {
                        if (new ConnectionDetector(AddVehicleActivity.this).isConnectingToInternet()) {
                            new addVehicle().execute();
                        } else {
                            new ConnectionDetector(AddVehicleActivity.this).connectiondetect();
                        }
                    }*/
                }
                //onBackPressed();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        /*if(new ConnectionDetector(AddVehicleActivity.this).isConnectingToInternet()){
            new getYear().execute();
        }
        else{
            new ConnectionDetector(AddVehicleActivity.this).connectiondetect();
        }*/

    }

}
