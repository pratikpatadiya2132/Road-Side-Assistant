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

class HistoryRequestAdapter extends RecyclerView.Adapter<HistoryRequestAdapter.ViewHolder> {
    Context context;
    ArrayList<HistoryRequestList> historyRequestLists;

    public HistoryRequestAdapter(FragmentActivity activity, ArrayList<HistoryRequestList> historyRequestLists) {
        this.context = activity;
        this.historyRequestLists = historyRequestLists;
    }

    @NonNull
    @Override
    public HistoryRequestAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_history_request, parent, false);
        return new HistoryRequestAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryRequestAdapter.ViewHolder holder, final int position) {
        holder.name.setText(historyRequestLists.get(position).getName());
        holder.email.setText(historyRequestLists.get(position).getEmail());
        holder.address.setText(historyRequestLists.get(position).getAddress()+"\n"+historyRequestLists.get(position).getArea()+"\n"+historyRequestLists.get(position).getCity()+"\n"+historyRequestLists.get(position).getState());
        holder.status.setText(historyRequestLists.get(position).getStatus());
    }

    @Override
    public int getItemCount() {
        return historyRequestLists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, email, address,status;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.custom_history_request_name);
            email = itemView.findViewById(R.id.custom_history_request_email);
            address = itemView.findViewById(R.id.custom_history_request_address);
            status = itemView.findViewById(R.id.custom_history_request_status);
        }
    }
}
