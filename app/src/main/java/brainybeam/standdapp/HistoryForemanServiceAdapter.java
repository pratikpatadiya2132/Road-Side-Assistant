package brainybeam.standdapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

class HistoryForemanServiceAdapter extends RecyclerView.Adapter<HistoryForemanServiceAdapter.MyViewHolder> {

    Context context;
    ArrayList<HistoryForemanServiceList> vehicleLists;
    SharedPreferences sp;

    public HistoryForemanServiceAdapter(FragmentActivity mainActivity, ArrayList<HistoryForemanServiceList> arrayList) {
        this.context = mainActivity;
        this.vehicleLists = arrayList;
        sp = context.getSharedPreferences(ConstantSp.PREF, Context.MODE_PRIVATE);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView address, id, no, date, service, status,name,charge;
        ImageView iv;

        public MyViewHolder(View view) {
            super(view);
            iv = (ImageView) view.findViewById(R.id.custom_history_foreman_service_iv);
            address = (TextView) view.findViewById(R.id.custom_history_foreman_service_address);
            id = (TextView) view.findViewById(R.id.custom_history_foreman_service_id);
            no = (TextView) view.findViewById(R.id.custom_history_foreman_service_registration);
            date = (TextView) view.findViewById(R.id.custom_history_foreman_service_date);
            name = (TextView) view.findViewById(R.id.custom_history_foreman_service_name);
            charge = (TextView) view.findViewById(R.id.custom_history_foreman_service_charge);
            service = (TextView) view.findViewById(R.id.custom_history_foreman_service_service);
            status = (TextView) view.findViewById(R.id.custom_history_foreman_service_status);
        }
    }

    @Override
    public HistoryForemanServiceAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom_history_foreman_service, parent, false);
        return new HistoryForemanServiceAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(HistoryForemanServiceAdapter.MyViewHolder holder, final int position) {
        //Picasso.with(context).load(categoryQuizLists.get(position).getImage()).placeholder(R.drawable.ic_defalult).into(holder.iv);
        holder.address.setText(Html.fromHtml(vehicleLists.get(position).getAddress()));
        holder.id.setText(Html.fromHtml(vehicleLists.get(position).getId()));
        holder.no.setText(Html.fromHtml(vehicleLists.get(position).getRegNo()));
        holder.date.setText(Html.fromHtml(vehicleLists.get(position).getDate()));
        holder.status.setText(Html.fromHtml(vehicleLists.get(position).getStatus()));
        holder.name.setText(Html.fromHtml(vehicleLists.get(position).getName()));
        holder.charge.setText(Html.fromHtml("Tentitive Rs."+vehicleLists.get(position).getCharge()));
        holder.service.setText(Html.fromHtml(vehicleLists.get(position).getProblemType()) + "\n" + Html.fromHtml(vehicleLists.get(position).getProblemSubType()));

        holder.iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sp.edit().putString(ConstantSp.USER_LATITUDE,vehicleLists.get(position).getLatitude()).commit();
                sp.edit().putString(ConstantSp.USER_LONGITUDE,vehicleLists.get(position).getLongitude()).commit();
                context.startActivity(new Intent(context,UserMapActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
            }
        });

    }

    @Override
    public int getItemCount() {
        return vehicleLists.size();
    }

}



