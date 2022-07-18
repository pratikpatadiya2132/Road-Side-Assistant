package brainybeam.standdapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.MyViewHolder> {

    Context context;
    ArrayList<HistoryList> vehicleLists;
    SharedPreferences sp;

    public HistoryAdapter(FragmentActivity mainActivity, ArrayList<HistoryList> arrayList) {
        this.context = mainActivity;
        this.vehicleLists = arrayList;
        sp = context.getSharedPreferences(ConstantSp.PREF, Context.MODE_PRIVATE);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView address, id, no, date, service,name,charge,status;
        ImageView iv;

        public MyViewHolder(View view) {
            super(view);
            iv = (ImageView) view.findViewById(R.id.custom_history_map);
            address = (TextView) view.findViewById(R.id.custom_history_address);
            id = (TextView) view.findViewById(R.id.custom_history_id);
            no = (TextView) view.findViewById(R.id.custom_history_registration);
            date = (TextView) view.findViewById(R.id.custom_history_date);
            service = (TextView) view.findViewById(R.id.custom_history_service);
            name = (TextView) view.findViewById(R.id.custom_history_name);
            charge = (TextView) view.findViewById(R.id.custom_history_charge);
            status = (TextView) view.findViewById(R.id.custom_history_status);
        }
    }

    @Override
    public HistoryAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom_history, parent, false);
        return new HistoryAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(HistoryAdapter.MyViewHolder holder, final int position) {
        //Picasso.with(context).load(categoryQuizLists.get(position).getImage()).placeholder(R.drawable.ic_defalult).into(holder.iv);
        holder.address.setText(Html.fromHtml(vehicleLists.get(position).getAddress()));
        holder.id.setText(Html.fromHtml(vehicleLists.get(position).getId()));
        holder.no.setText(Html.fromHtml(vehicleLists.get(position).getRegNo()));
        holder.date.setText(Html.fromHtml(vehicleLists.get(position).getDate()));
        holder.service.setText(Html.fromHtml(vehicleLists.get(position).getProblemType())+"\n"+Html.fromHtml(vehicleLists.get(position).getProblemSubType()));
        holder.name.setText(Html.fromHtml(vehicleLists.get(position).getName()));
        holder.charge.setText(Html.fromHtml("Tentitive : Rs."+vehicleLists.get(position).getCharge()));
        holder.status.setText(Html.fromHtml(vehicleLists.get(position).getStatus()));
        holder.iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sp.edit().putString(ConstantSp.FOREMAN_ID,vehicleLists.get(position).getService_privider_id()).commit();
                context.startActivity(new Intent(context,UserMapActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
            }
        });
    }

    @Override
    public int getItemCount() {
        return vehicleLists.size();
    }
}

