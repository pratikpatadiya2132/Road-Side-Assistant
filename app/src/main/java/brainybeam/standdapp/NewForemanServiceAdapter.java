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
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

class NewForemanServiceAdapter extends RecyclerView.Adapter<NewForemanServiceAdapter.MyViewHolder> {

    Context context;
    ArrayList<NewForemanServiceList> vehicleLists;
    SharedPreferences sp;
    String sId, sStatus,sFcmId;

    public NewForemanServiceAdapter(FragmentActivity mainActivity, ArrayList<NewForemanServiceList> arrayList) {
        this.context = mainActivity;
        this.vehicleLists = arrayList;
        sp = context.getSharedPreferences(ConstantSp.PREF, Context.MODE_PRIVATE);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView address, id, no, date, service, name, charge;
        Button confirm, denied;

        public MyViewHolder(View view) {
            super(view);
            //iv = (ImageView) view.findViewById(R.id.custom_category_quiz_iv);
            address = (TextView) view.findViewById(R.id.custom_new_foreman_service_address);
            id = (TextView) view.findViewById(R.id.custom_new_foreman_service_id);
            no = (TextView) view.findViewById(R.id.custom_new_foreman_service_registration);
            date = (TextView) view.findViewById(R.id.custom_new_foreman_service_date);
            service = (TextView) view.findViewById(R.id.custom_new_foreman_service_service);
            name = (TextView) view.findViewById(R.id.custom_new_foreman_service_name);
            charge = (TextView) view.findViewById(R.id.custom_new_foreman_service_charge);
            confirm = (Button) view.findViewById(R.id.custom_new_foreman_service_confirm);
            denied = (Button) view.findViewById(R.id.custom_new_foreman_service_cancle);
        }
    }

    @Override
    public NewForemanServiceAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom_new_foreman_service, parent, false);
        return new NewForemanServiceAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(NewForemanServiceAdapter.MyViewHolder holder, final int position) {
        //Picasso.with(context).load(categoryQuizLists.get(position).getImage()).placeholder(R.drawable.ic_defalult).into(holder.iv);
        holder.address.setText(Html.fromHtml(vehicleLists.get(position).getAddress()));
        holder.id.setText(Html.fromHtml(vehicleLists.get(position).getId()));
        holder.no.setText(Html.fromHtml(vehicleLists.get(position).getRegNo()));
        holder.date.setText(Html.fromHtml(vehicleLists.get(position).getDate()));
        holder.name.setText(Html.fromHtml(vehicleLists.get(position).getName()));
        holder.charge.setText(Html.fromHtml("Tentitive Rs."+vehicleLists.get(position).getCharge()));
        holder.service.setText(Html.fromHtml(vehicleLists.get(position).getProblemType()) + "\n" + Html.fromHtml(vehicleLists.get(position).getProblemSubType()));

        holder.confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sId = vehicleLists.get(position).getId();
                sFcmId = vehicleLists.get(position).getFcmId();
                sStatus = "Accepted";
                context.startActivity(new Intent(context, ForemanDashboardActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));

            }
        });

        holder.denied.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sId = vehicleLists.get(position).getId();
                sFcmId = vehicleLists.get(position).getFcmId();
                sStatus = "Cancelled";
                context.startActivity(new Intent(context, ForemanDashboardActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));

            }
        });

    }

    @Override
    public int getItemCount() {
        return vehicleLists.size();
    }

}


