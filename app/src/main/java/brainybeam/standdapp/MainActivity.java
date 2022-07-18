package brainybeam.standdapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    LinearLayout submit_layout, vehicle_layout, history_layout, logout_layout;
    ImageView home_menu, call_menu, submit_iv, vehicle_iv, history_iv, logout_iv;
    TextView header_title, title_menu, submit_text, vehicle_text, history_text, logout_text;

    SharedPreferences sp;

    TextView continueText;

    /*RecyclerView recyclerView;
    ArrayList<VehicleList> arrayList;
    VehicleAdapter adapter;

    String[] id = {"1","2","3","4","5","6","7","8"};
    String[] name = {"Accident","Battery Jump Start","Clutch/Break problem","Fuel Problem","Lost/Locked Key","Towing","Tyre Damage","Other"};
    int[] image = {R.drawable.car_crash,R.drawable.battery,R.drawable.break_tyre,R.drawable.oil,R.drawable.key,R.drawable.towing,R.drawable.tyre,R.drawable.other};*/

    LinearLayout accident_layout, battery_layout, clutch_layout, fuel_layout, fuel_sub_layout, key_layout, towing_layout, tyre_layout, tyre_sub_layout, other_layout;
    ImageView accident_iv, battery_iv, clutch_iv, fuel_iv, key_iv, towing_iv, tyre_iv, other_iv;
    TextView accident_text, battery_text, clutch_text, fuel_text, key_text, towing_text, tyre_text, other_text;
    private static final int STORAGE_PERMISSION_CODE = 123;

    RadioGroup fuel_rg, tyre_rg;
    String sFuel, sTyre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        requestStoragePermission();
        sp = getSharedPreferences(ConstantSp.PREF, MODE_PRIVATE);
        sp.edit().putString(ConstantSp.COUNT, "No").commit();
        home_menu = findViewById(R.id.content_main_menu);
        call_menu = findViewById(R.id.content_main_call);
        title_menu = findViewById(R.id.content_main_title);

        continueText = findViewById(R.id.content_main_continue);

        title_menu.setText("Incident");

        call_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Call Assistance Center?");
                builder.setPositiveButton("CALL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(Intent.ACTION_CALL);
                        intent.setData(Uri.parse("tel:+911204670700"));
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            if (checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                                // TODO: Consider calling
                                //    Activity#requestPermissions
                                // here to request the missing permissions, and then overriding
                                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                //                                          int[] grantResults)
                                // to handle the case where the user grants the permission. See the documentation
                                // for Activity#requestPermissions for more details.
                                return;
                            }
                        }
                        startActivity(intent);
                    }
                });
                builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                builder.show();
            }
        });

        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        home_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawer.openDrawer(GravityCompat.START);
            }
        });

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View header = navigationView.getHeaderView(0);
        header_title = header.findViewById(R.id.header_title);

        header_title.setText(sp.getString(ConstantSp.NAME, ""));

        submit_layout = header.findViewById(R.id.header_submit_layout);
        submit_iv = header.findViewById(R.id.header_submit_iv);
        submit_text = header.findViewById(R.id.header_submit_text);

        vehicle_layout = header.findViewById(R.id.header_vehicle_layout);
        vehicle_iv = header.findViewById(R.id.header_vehicle_iv);
        vehicle_text = header.findViewById(R.id.header_vehicle_text);

        history_layout = header.findViewById(R.id.header_history_layout);
        history_iv = header.findViewById(R.id.header_history_iv);
        history_text = header.findViewById(R.id.header_history_text);

        logout_layout = header.findViewById(R.id.header_logout_layout);
        logout_iv = header.findViewById(R.id.header_logout_iv);
        logout_text = header.findViewById(R.id.header_logout_text);

        submit_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
            }
        });

        submit_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
            }
        });

        submit_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
            }
        });

        vehicle_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                title_menu.setText("Vehicles");
                drawer.closeDrawer(GravityCompat.START);
                FragmentManager manager = getSupportFragmentManager();
                manager.beginTransaction().replace(R.id.content_main_layout, new VehicleFragment()).addToBackStack("").commit();
                //startActivity(new Intent(MainActivity.this, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
            }
        });

        vehicle_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                title_menu.setText("Vehicles");
                drawer.closeDrawer(GravityCompat.START);
                FragmentManager manager = getSupportFragmentManager();
                manager.beginTransaction().replace(R.id.content_main_layout, new VehicleFragment()).addToBackStack("").commit();
                //startActivity(new Intent(MainActivity.this, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
            }
        });

        vehicle_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                title_menu.setText("Vehicles");
                drawer.closeDrawer(GravityCompat.START);
                FragmentManager manager = getSupportFragmentManager();
                manager.beginTransaction().replace(R.id.content_main_layout, new VehicleFragment()).addToBackStack("").commit();
                //startActivity(new Intent(MainActivity.this, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
            }
        });

        history_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                title_menu.setText("History");
                drawer.closeDrawer(GravityCompat.START);
                FragmentManager manager = getSupportFragmentManager();
                manager.beginTransaction().replace(R.id.content_main_layout, new HistoryFragment()).addToBackStack("").commit();
            }
        });

        history_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                title_menu.setText("History");
                drawer.closeDrawer(GravityCompat.START);
                FragmentManager manager = getSupportFragmentManager();
                manager.beginTransaction().replace(R.id.content_main_layout, new HistoryFragment()).addToBackStack("").commit();
            }
        });

        history_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                title_menu.setText("History");
                drawer.closeDrawer(GravityCompat.START);
                FragmentManager manager = getSupportFragmentManager();
                manager.beginTransaction().replace(R.id.content_main_layout, new HistoryFragment()).addToBackStack("").commit();
            }
        });

        logout_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sp.edit().clear().commit();
                startActivity(new Intent(MainActivity.this, LoginActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
            }
        });

        logout_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sp.edit().clear().commit();
                startActivity(new Intent(MainActivity.this, LoginActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
            }
        });

        logout_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sp.edit().clear().commit();
                startActivity(new Intent(MainActivity.this, LoginActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
            }
        });

        /*recyclerView = findViewById(R.id.content_main_recycler);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        arrayList = new ArrayList<>();
        for (int i = 0; i < id.length; i++) {
            VehicleList list = new VehicleList();
            list.setId(id[i]);
            list.setName(name[i]);
            list.setImage(image[i]);
            arrayList.add(list);
        }
        adapter = new VehicleAdapter(MainActivity.this, arrayList);
        recyclerView.setAdapter(adapter);*/

        accident_layout = findViewById(R.id.content_main_accident_layout);
        accident_iv = findViewById(R.id.content_main_accident_iv);
        accident_text = findViewById(R.id.content_main_accident_text);

        battery_layout = findViewById(R.id.content_main_battery_layout);
        battery_iv = findViewById(R.id.content_main_battery_iv);
        battery_text = findViewById(R.id.content_main_battery_text);

        clutch_layout = findViewById(R.id.content_main_clutch_layout);
        clutch_iv = findViewById(R.id.content_main_clutch_iv);
        clutch_text = findViewById(R.id.content_main_clutch_text);

        fuel_layout = findViewById(R.id.content_main_fuel_layout);
        fuel_iv = findViewById(R.id.content_main_fuel_iv);
        fuel_text = findViewById(R.id.content_main_fuel_text);
        fuel_sub_layout = findViewById(R.id.content_main_fuel_sub_layout);

        fuel_rg = findViewById(R.id.content_main_fuel_rg);

        fuel_rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                int id = fuel_rg.getCheckedRadioButtonId();
                RadioButton rb = findViewById(id);
                sp.edit().putString(ConstantSp.SUBTYPE, rb.getText().toString()).commit();
            }
        });

        key_layout = findViewById(R.id.content_main_key_layout);
        key_iv = findViewById(R.id.content_main_key_iv);
        key_text = findViewById(R.id.content_main_key_text);

        towing_layout = findViewById(R.id.content_main_towing_layout);
        towing_iv = findViewById(R.id.content_main_towing_iv);
        towing_text = findViewById(R.id.content_main_towing_text);

        tyre_layout = findViewById(R.id.content_main_tyre_layout);
        tyre_iv = findViewById(R.id.content_main_tyre_iv);
        tyre_text = findViewById(R.id.content_main_tyre_text);
        tyre_sub_layout = findViewById(R.id.content_main_tyre_sub_layout);

        tyre_rg = findViewById(R.id.content_main_tyre_rg);

        tyre_rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                int id = tyre_rg.getCheckedRadioButtonId();
                RadioButton rb = findViewById(id);
                sp.edit().putString(ConstantSp.SUBTYPE, rb.getText().toString()).commit();
            }
        });

        other_layout = findViewById(R.id.content_main_other_layout);
        other_iv = findViewById(R.id.content_main_other_iv);
        other_text = findViewById(R.id.content_main_other_text);


        accident_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sp.edit().putString(ConstantSp.COUNT, "Yes").commit();
                sp.edit().putString(ConstantSp.TYPE, "Accident").commit();
                sp.edit().putString(ConstantSp.SUBTYPE, "").commit();
                fuel_sub_layout.setVisibility(View.GONE);
                tyre_sub_layout.setVisibility(View.GONE);
                accident_layout.setBackground(getResources().getDrawable(R.drawable.custom_border_red));
                battery_layout.setBackground(getResources().getDrawable(R.drawable.custom_border));
                clutch_layout.setBackground(getResources().getDrawable(R.drawable.custom_border));
                fuel_layout.setBackground(getResources().getDrawable(R.drawable.custom_border));
                key_layout.setBackground(getResources().getDrawable(R.drawable.custom_border));
                towing_layout.setBackground(getResources().getDrawable(R.drawable.custom_border));
                tyre_layout.setBackground(getResources().getDrawable(R.drawable.custom_border));
                other_layout.setBackground(getResources().getDrawable(R.drawable.custom_border));
            }
        });

        battery_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sp.edit().putString(ConstantSp.COUNT, "Yes").commit();
                sp.edit().putString(ConstantSp.TYPE, "Battery Jump Start").commit();
                sp.edit().putString(ConstantSp.SUBTYPE, "").commit();
                fuel_sub_layout.setVisibility(View.GONE);
                tyre_sub_layout.setVisibility(View.GONE);
                accident_layout.setBackground(getResources().getDrawable(R.drawable.custom_border));
                battery_layout.setBackground(getResources().getDrawable(R.drawable.custom_border_red));
                clutch_layout.setBackground(getResources().getDrawable(R.drawable.custom_border));
                fuel_layout.setBackground(getResources().getDrawable(R.drawable.custom_border));
                key_layout.setBackground(getResources().getDrawable(R.drawable.custom_border));
                towing_layout.setBackground(getResources().getDrawable(R.drawable.custom_border));
                tyre_layout.setBackground(getResources().getDrawable(R.drawable.custom_border));
                other_layout.setBackground(getResources().getDrawable(R.drawable.custom_border));
            }
        });

        clutch_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sp.edit().putString(ConstantSp.COUNT, "Yes").commit();
                sp.edit().putString(ConstantSp.TYPE, "Clutch/Break problem").commit();
                sp.edit().putString(ConstantSp.SUBTYPE, "").commit();
                fuel_sub_layout.setVisibility(View.GONE);
                tyre_sub_layout.setVisibility(View.GONE);
                accident_layout.setBackground(getResources().getDrawable(R.drawable.custom_border));
                battery_layout.setBackground(getResources().getDrawable(R.drawable.custom_border));
                clutch_layout.setBackground(getResources().getDrawable(R.drawable.custom_border_red));
                fuel_layout.setBackground(getResources().getDrawable(R.drawable.custom_border));
                key_layout.setBackground(getResources().getDrawable(R.drawable.custom_border));
                towing_layout.setBackground(getResources().getDrawable(R.drawable.custom_border));
                tyre_layout.setBackground(getResources().getDrawable(R.drawable.custom_border));
                other_layout.setBackground(getResources().getDrawable(R.drawable.custom_border));
            }
        });

        fuel_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sp.edit().putString(ConstantSp.COUNT, "Yes").commit();
                sp.edit().putString(ConstantSp.TYPE, "Fuel Problem").commit();
                fuel_sub_layout.setVisibility(View.VISIBLE);
                tyre_sub_layout.setVisibility(View.GONE);
                accident_layout.setBackground(getResources().getDrawable(R.drawable.custom_border));
                battery_layout.setBackground(getResources().getDrawable(R.drawable.custom_border));
                clutch_layout.setBackground(getResources().getDrawable(R.drawable.custom_border));
                fuel_layout.setBackground(getResources().getDrawable(R.drawable.custom_border_red));
                key_layout.setBackground(getResources().getDrawable(R.drawable.custom_border));
                towing_layout.setBackground(getResources().getDrawable(R.drawable.custom_border));
                tyre_layout.setBackground(getResources().getDrawable(R.drawable.custom_border));
                other_layout.setBackground(getResources().getDrawable(R.drawable.custom_border));
            }
        });

        key_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sp.edit().putString(ConstantSp.COUNT, "Yes").commit();
                sp.edit().putString(ConstantSp.TYPE, "Lost/Locked Key").commit();
                sp.edit().putString(ConstantSp.SUBTYPE, "").commit();
                fuel_sub_layout.setVisibility(View.GONE);
                tyre_sub_layout.setVisibility(View.GONE);
                accident_layout.setBackground(getResources().getDrawable(R.drawable.custom_border));
                battery_layout.setBackground(getResources().getDrawable(R.drawable.custom_border));
                clutch_layout.setBackground(getResources().getDrawable(R.drawable.custom_border));
                fuel_layout.setBackground(getResources().getDrawable(R.drawable.custom_border));
                key_layout.setBackground(getResources().getDrawable(R.drawable.custom_border_red));
                towing_layout.setBackground(getResources().getDrawable(R.drawable.custom_border));
                tyre_layout.setBackground(getResources().getDrawable(R.drawable.custom_border));
                other_layout.setBackground(getResources().getDrawable(R.drawable.custom_border));
            }
        });

        towing_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sp.edit().putString(ConstantSp.COUNT, "Yes").commit();
                sp.edit().putString(ConstantSp.TYPE, "Towing").commit();
                sp.edit().putString(ConstantSp.SUBTYPE, "").commit();
                fuel_sub_layout.setVisibility(View.GONE);
                tyre_sub_layout.setVisibility(View.GONE);
                accident_layout.setBackground(getResources().getDrawable(R.drawable.custom_border));
                battery_layout.setBackground(getResources().getDrawable(R.drawable.custom_border));
                clutch_layout.setBackground(getResources().getDrawable(R.drawable.custom_border));
                fuel_layout.setBackground(getResources().getDrawable(R.drawable.custom_border));
                key_layout.setBackground(getResources().getDrawable(R.drawable.custom_border));
                towing_layout.setBackground(getResources().getDrawable(R.drawable.custom_border_red));
                tyre_layout.setBackground(getResources().getDrawable(R.drawable.custom_border));
                other_layout.setBackground(getResources().getDrawable(R.drawable.custom_border));
            }
        });

        tyre_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sp.edit().putString(ConstantSp.COUNT, "Yes").commit();
                sp.edit().putString(ConstantSp.TYPE, "Tyre Damage").commit();
                fuel_sub_layout.setVisibility(View.GONE);
                tyre_sub_layout.setVisibility(View.VISIBLE);
                accident_layout.setBackground(getResources().getDrawable(R.drawable.custom_border));
                battery_layout.setBackground(getResources().getDrawable(R.drawable.custom_border));
                clutch_layout.setBackground(getResources().getDrawable(R.drawable.custom_border));
                fuel_layout.setBackground(getResources().getDrawable(R.drawable.custom_border));
                key_layout.setBackground(getResources().getDrawable(R.drawable.custom_border));
                towing_layout.setBackground(getResources().getDrawable(R.drawable.custom_border));
                tyre_layout.setBackground(getResources().getDrawable(R.drawable.custom_border_red));
                other_layout.setBackground(getResources().getDrawable(R.drawable.custom_border));
            }
        });

        other_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sp.edit().putString(ConstantSp.COUNT, "Yes").commit();
                sp.edit().putString(ConstantSp.TYPE, "Other").commit();
                sp.edit().putString(ConstantSp.SUBTYPE, "").commit();
                fuel_sub_layout.setVisibility(View.GONE);
                tyre_sub_layout.setVisibility(View.GONE);
                accident_layout.setBackground(getResources().getDrawable(R.drawable.custom_border));
                battery_layout.setBackground(getResources().getDrawable(R.drawable.custom_border));
                clutch_layout.setBackground(getResources().getDrawable(R.drawable.custom_border));
                fuel_layout.setBackground(getResources().getDrawable(R.drawable.custom_border));
                key_layout.setBackground(getResources().getDrawable(R.drawable.custom_border));
                towing_layout.setBackground(getResources().getDrawable(R.drawable.custom_border));
                tyre_layout.setBackground(getResources().getDrawable(R.drawable.custom_border));
                other_layout.setBackground(getResources().getDrawable(R.drawable.custom_border_red));
            }
        });

        continueText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (sp.getString(ConstantSp.COUNT, "").equals("Yes")) {
                    if (sp.getString(ConstantSp.TYPE, "").equals("Fuel Problem")) {
                        if (fuel_rg.getCheckedRadioButtonId() == -1) {
                            Toast.makeText(MainActivity.this, "Please Select Fuel Problem Type", Toast.LENGTH_SHORT).show();
                        } else {
                            startActivity(new Intent(MainActivity.this, ForemanServiceActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                        }
                    } else if (sp.getString(ConstantSp.TYPE, "").equals("Tyre Damage")) {
                        if (tyre_rg.getCheckedRadioButtonId() == -1) {
                            Toast.makeText(MainActivity.this, "Please Select Tyre Damage Type", Toast.LENGTH_SHORT).show();
                        } else {
                            startActivity(new Intent(MainActivity.this, ForemanServiceActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                        }
                    } else {
                        startActivity(new Intent(MainActivity.this, ForemanServiceActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Please Select Any One Option", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            //super.onBackPressed();
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle(getResources().getString(R.string.app_name));
            builder.setMessage("Are You Sure Want To Exit!");
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    finishAffinity();
                }
            });
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            });
            builder.show();
        }
    }

    private void requestStoragePermission() {
        if (ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.CALL_PHONE)
                != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale
                    (MainActivity.this, Manifest.permission.CALL_PHONE)) {

                Snackbar.make(MainActivity.this.findViewById(android.R.id.content),
                        "Please Grant Permissions to Download photo",
                        Snackbar.LENGTH_INDEFINITE).setAction("ENABLE",
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                    requestPermissions(
                                            new String[]{Manifest.permission
                                                    .CALL_PHONE},
                                            STORAGE_PERMISSION_CODE);
                                }
                            }
                        }).show();
            } else {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    requestPermissions(
                            new String[]{Manifest.permission
                                    .CALL_PHONE},
                            STORAGE_PERMISSION_CODE);
                }
            }
        } else {
            // write your logic code if permission already granted
        }
    }
}
