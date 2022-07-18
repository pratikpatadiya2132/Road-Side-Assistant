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

class VehicleAdapter extends RecyclerView.Adapter<VehicleAdapter.MyViewHolder> {

    Context context;
    ArrayList<VehicleList> vehicleLists;
    SharedPreferences sp;

    public VehicleAdapter(FragmentActivity mainActivity, ArrayList<VehicleList> arrayList) {
        this.context = mainActivity;
        this.vehicleLists = arrayList;
        sp = context.getSharedPreferences(ConstantSp.PREF,Context.MODE_PRIVATE);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView iv,edit;
        TextView name,model,color,year;
        LinearLayout linearLayout;

        public MyViewHolder(View view) {
            super(view);
            //iv = (ImageView) view.findViewById(R.id.custom_category_quiz_iv);
            name = (TextView) view.findViewById(R.id.custom_vehicle_name);
            model = (TextView) view.findViewById(R.id.custom_vehicle_model);
            color = (TextView) view.findViewById(R.id.custom_vehicle_color);
            year = (TextView) view.findViewById(R.id.custom_vehicle_year);
            iv = (ImageView) view.findViewById(R.id.custom_vehicle_image);
            edit = (ImageView) view.findViewById(R.id.custom_vehicle_edit);
        }
    }

    @Override
    public VehicleAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom_vehicle, parent, false);
        return new VehicleAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(VehicleAdapter.MyViewHolder holder, final int position) {
        //Picasso.with(context).load(categoryQuizLists.get(position).getImage()).placeholder(R.drawable.ic_defalult).into(holder.iv);
        holder.name.setText(Html.fromHtml(vehicleLists.get(position).getName()));
        holder.model.setText(Html.fromHtml(vehicleLists.get(position).getModel()));
        holder.color.setText(Html.fromHtml(vehicleLists.get(position).getColor()));
        holder.year.setText(Html.fromHtml(vehicleLists.get(position).getYear()));
        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sp.edit().putString(ConstantSp.ADD_VEHICLE_ADD_UPDATE,"Edit").commit();
                sp.edit().putString(ConstantSp.ADD_VEHICLE_TYPE,vehicleLists.get(position).getType()).commit();
                sp.edit().putString(ConstantSp.ADD_VEHICLE_ID,vehicleLists.get(position).getId()).commit();
                sp.edit().putString(ConstantSp.ADD_VEHICLE_REGISTRATION_NO,vehicleLists.get(position).getName()).commit();
                sp.edit().putString(ConstantSp.ADD_VEHICLE_MODEL,vehicleLists.get(position).getModel()).commit();
                sp.edit().putString(ConstantSp.ADD_VEHICLE_MODEL_SP,vehicleLists.get(position).getModelSP()).commit();
                sp.edit().putString(ConstantSp.ADD_VEHICLE_COLOUR,vehicleLists.get(position).getColor()).commit();
                sp.edit().putString(ConstantSp.ADD_VEHICLE_YEAR,vehicleLists.get(position).getYear()).commit();
                sp.edit().putString(ConstantSp.ADD_VEHICLE_VIN,vehicleLists.get(position).getVin()).commit();
                context.startActivity(new Intent(context,AddVehicleActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
            }
        });
    }

    @Override
    public int getItemCount() {
        return vehicleLists.size();
    }
}
