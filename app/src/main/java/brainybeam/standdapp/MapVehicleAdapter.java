package brainybeam.standdapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

class MapVehicleAdapter extends RecyclerView.Adapter<MapVehicleAdapter.MyViewHolder> {

    Context context;
    ArrayList<MapVehicleList> vehicleLists;
    SharedPreferences sp;
    RadioGroup rg;
    EditText regNo, model, other;
    TextView submit;
    String sType = "";
    String sLatitude,sLongitude,sAddress,sRegNo,sModel,sOther;
    int count =0;

    public MapVehicleAdapter(FragmentActivity mainActivity, ArrayList<MapVehicleList> arrayList, RadioGroup rg, EditText regNo, EditText model, EditText other, TextView submit) {
        this.context = mainActivity;
        this.vehicleLists = arrayList;
        this.rg = rg;
        this.model = model;
        this.other = other;
        this.regNo = regNo;
        this.submit = submit;
        sp = context.getSharedPreferences(ConstantSp.PREF, Context.MODE_PRIVATE);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name, model;
        LinearLayout linearLayout;

        public MyViewHolder(View view) {
            super(view);
            //iv = (ImageView) view.findViewById(R.id.custom_category_quiz_iv);
            name = (TextView) view.findViewById(R.id.custom_map_vehicle_regn);
            model = (TextView) view.findViewById(R.id.custom_map_vehicle_model);
            linearLayout = view.findViewById(R.id.custom_map_vehicle_layout);
        }
    }

    @Override
    public MapVehicleAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom_map_vehicle, parent, false);
        return new MapVehicleAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MapVehicleAdapter.MyViewHolder holder, final int position) {
        //Picasso.with(context).load(categoryQuizLists.get(position).getImage()).placeholder(R.drawable.ic_defalult).into(holder.iv);
        holder.name.setText(Html.fromHtml(vehicleLists.get(position).getName()));
        holder.model.setText(Html.fromHtml(vehicleLists.get(position).getModel()));

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //holder.linearLayout.setBackground(context.getResources().getDrawable(R.drawable.custom_border_red));
                count=1;
                regNo.setText(vehicleLists.get(position).getName());
                model.setText(vehicleLists.get(position).getModel());
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(count==0) {
                    if(rg.getCheckedRadioButtonId()==-1){
                        Toast.makeText(context, "Please Select Vehicle Type", Toast.LENGTH_SHORT).show();
                    }
                    else if (regNo.getText().toString().equals("")) {
                        regNo.setError("Registration No. Required");
                    } else if (model.getText().toString().equals("")) {
                        model.setError("Make/Model Required");
                    } else {
                        context.startActivity(new Intent(context,MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                    }
                }
                else{
                    if (regNo.getText().toString().equals("")) {
                        regNo.setError("Registration No. Required");
                    } else if (model.getText().toString().equals("")) {
                        model.setError("Make/Model Required");
                    } else {
                        context.startActivity(new Intent(context,MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                    }
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return vehicleLists.size();
    }
}

