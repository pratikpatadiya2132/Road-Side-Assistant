package brainybeam.standdapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

class NewRequestAdapter extends RecyclerView.Adapter<NewRequestAdapter.ViewHolder> {
    Context context;
    ArrayList<NewRequestList> newRequestLists;
    String sId,sStatus;

    public NewRequestAdapter(FragmentActivity activity, ArrayList<NewRequestList> newRequestLists) {
        this.context = activity;
        this.newRequestLists = newRequestLists;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_new_request, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.name.setText(newRequestLists.get(position).getName());
        holder.email.setText(newRequestLists.get(position).getEmail());
        holder.address.setText(newRequestLists.get(position).getAddress()+"\n"+newRequestLists.get(position).getArea()+"\n"+newRequestLists.get(position).getCity()+"\n"+newRequestLists.get(position).getState());

        holder.accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sId = newRequestLists.get(position).getId();
                sStatus ="Accepted";
                context.startActivity(new Intent(context,AdminDashboardActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));

            }
        });

        holder.denied.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sId = newRequestLists.get(position).getId();
                sStatus ="Cancelled";
                context.startActivity(new Intent(context,AdminDashboardActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));

            }
        });

    }

    @Override
    public int getItemCount() {
        return newRequestLists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, email, address;
        Button accept, denied;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.custom_new_request_name);
            email = itemView.findViewById(R.id.custom_new_request_email);
            address = itemView.findViewById(R.id.custom_new_request_address);
            accept = itemView.findViewById(R.id.custom_new_request_accept);
            denied = itemView.findViewById(R.id.custom_new_request_denied);
        }
    }
}
