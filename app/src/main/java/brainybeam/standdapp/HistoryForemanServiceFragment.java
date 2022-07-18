package brainybeam.standdapp;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * A simple {@link Fragment} subclass.
 */
public class HistoryForemanServiceFragment extends Fragment {

    RecyclerView recyclerView;
    ArrayList<HistoryForemanServiceList> arrayList;
    HistoryForemanServiceAdapter adapter;
    SharedPreferences sp;
    double currentLatitude, currentLongitude;

    public HistoryForemanServiceFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_history_foreman_service, container, false);
        sp = getActivity().getSharedPreferences(ConstantSp.PREF, Context.MODE_PRIVATE);

        recyclerView = view.findViewById(R.id.history_foreman_service_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        return view;
    }

}
