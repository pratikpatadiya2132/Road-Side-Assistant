package brainybeam.standdapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

class ForemanServiceAdapter extends RecyclerView.Adapter<ForemanServiceAdapter.MyViewHolder> {

    Context context;
    ArrayList<ForemanServiceList> vehicleLists;
    SharedPreferences sp;

    public ForemanServiceAdapter(FragmentActivity mainActivity, ArrayList<ForemanServiceList> arrayList) {
        this.context = mainActivity;
        this.vehicleLists = arrayList;
        sp = context.getSharedPreferences(ConstantSp.PREF, Context.MODE_PRIVATE);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name, charge, address, distance,select;
        LinearLayout linearLayout;

        public MyViewHolder(View view) {
            super(view);
            //iv = (ImageView) view.findViewById(R.id.custom_category_quiz_iv);
            name = (TextView) view.findViewById(R.id.custom_foreman_service_name);
            charge = (TextView) view.findViewById(R.id.custom_foreman_service_charge);
            address = (TextView) view.findViewById(R.id.custom_foreman_service_address);
            distance = (TextView) view.findViewById(R.id.custom_foreman_service_distance);
            select = (TextView) view.findViewById(R.id.custom_foreman_service_select);
        }
    }

    @Override
    public ForemanServiceAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom_foreman_service, parent, false);
        return new ForemanServiceAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ForemanServiceAdapter.MyViewHolder holder, final int position) {
        //Picasso.with(context).load(categoryQuizLists.get(position).getImage()).placeholder(R.drawable.ic_defalult).into(holder.iv);
        holder.name.setText(Html.fromHtml(vehicleLists.get(position).getName()));
        holder.charge.setText(Html.fromHtml("Tentitive Charge : Rs."+vehicleLists.get(position).getCharge()));
        holder.address.setText(Html.fromHtml(vehicleLists.get(position).getAddress()+"\n"+vehicleLists.get(position).getArea()+"\n"+vehicleLists.get(position).getCity()+"\n"+vehicleLists.get(position).getState()));
        holder.distance.setText(Html.fromHtml(vehicleLists.get(position).getDistance()+" KM"));

        holder.select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sp.edit().putString(ConstantSp.SERVICE_PROVIDER_ID,vehicleLists.get(position).getId()).commit();
                sp.edit().putString(ConstantSp.SERVICE_CHARGE,vehicleLists.get(position).getCharge()).commit();
                context.startActivity(new Intent(context,MapsActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
            }
        });

    }

    @Override
    public int getItemCount() {
        return vehicleLists.size();
    }
}


