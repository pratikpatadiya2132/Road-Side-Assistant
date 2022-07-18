package brainybeam.standdapp;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ForemanServiceActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<ForemanServiceList> arrayList;
    ForemanServiceAdapter adapter;
    double currentLatitude,currentLongitude,driverLatitude,driverLongitude;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foreman_service);
        getSupportActionBar().setTitle("Foreman Lists");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        recyclerView = findViewById(R.id.foreman_service_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(ForemanServiceActivity.this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id==android.R.id.home){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    
}
