package brainybeam.standdapp;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
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

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * A simple {@link Fragment} subclass.
 */
public class VehicleFragment extends Fragment {

    FloatingActionButton add;

    RecyclerView recyclerView;
    ArrayList<VehicleList> arrayList;
    VehicleAdapter adapter;

    String[] id = {"1"};
    String[] name = {"GJ27BG1234"};
    String[] model = {"Yamaha Fz S Fi V 2.0"};
    String[] modelSP = {"FZ S FI (V 2.0) Dual Disc"};
    String[] color = {"Blue"};
    String[] year = {"2017"};
    String[] vin = {""};
    int[] image = {R.drawable.car};
    SharedPreferences sp;

    public VehicleFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_vehicle, container, false);
        sp = getActivity().getSharedPreferences(ConstantSp.PREF, Context.MODE_PRIVATE);
        add = view.findViewById(R.id.vehicle_add);

        recyclerView = view.findViewById(R.id.vehicle_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        /*if(new ConnectionDetector(getActivity()).isConnectingToInternet()){
            new getData().execute();
        }
        else{
            new ConnectionDetector(getActivity()).connectiondetect();
        }*/

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sp.edit().putString(ConstantSp.ADD_VEHICLE_ADD_UPDATE,"Add").commit();
                sp.edit().putString(ConstantSp.ADD_VEHICLE_TYPE,"").commit();
                sp.edit().putString(ConstantSp.ADD_VEHICLE_REGISTRATION_NO,"").commit();
                sp.edit().putString(ConstantSp.ADD_VEHICLE_MODEL,"").commit();
                sp.edit().putString(ConstantSp.ADD_VEHICLE_MODEL_SP,"").commit();
                sp.edit().putString(ConstantSp.ADD_VEHICLE_COLOUR,"").commit();
                sp.edit().putString(ConstantSp.ADD_VEHICLE_YEAR,"").commit();
                sp.edit().putString(ConstantSp.ADD_VEHICLE_VIN,"").commit();
                startActivity(new Intent(getActivity(),AddVehicleActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
            }
        });
        getData();
        return view;
    }

    private void getData() {
        arrayList = new ArrayList<>();
        for(int i=0;i<name.length;i++){
            VehicleList list = new VehicleList();
            list.setId(id[i]);
            list.setName(name[i]);
            list.setType("4W");
            list.setModel(model[i]);
            list.setModelSP(modelSP[i]);
            list.setColor(color[i]);
            list.setYear(year[i]);
            list.setVin(vin[i]);
            //list.setImage(image[i]);
            arrayList.add(list);
        }
        adapter = new VehicleAdapter(getActivity(), arrayList);
        recyclerView.setAdapter(adapter);
    }

    /*private class getData extends AsyncTask<String,String,String> {

        ProgressDialog pd;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd = new ProgressDialog(getActivity());
            pd.setMessage("Please Wait...");
            pd.setCancelable(false);
            pd.show();
        }

        @Override
        protected String doInBackground(String... strings) {
            HashMap<String,String> hashMap = new HashMap<>();
            hashMap.put("action","getVehicle");
            hashMap.put("userId",sp.getString(ConstantSp.ID,""));
            return new MakeServiceCall().MakeServiceCall(ConstantSp.URL+"user_management.php",MakeServiceCall.POST,hashMap);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            pd.dismiss();
            try {
                JSONObject object = new JSONObject(s);
                if(object.getString("Status").equals("True")){
                    arrayList = new ArrayList<>();
                    JSONArray array = object.getJSONArray("response");
                    for(int i=0;i<array.length();i++){
                        JSONObject jsonObject = array.getJSONObject(i);
                        VehicleList list = new VehicleList();
                        list.setId(jsonObject.getString("id"));
                        list.setName(jsonObject.getString("regNo"));
                        list.setType(jsonObject.getString("vehicleType"));
                        list.setModel(jsonObject.getString("vehicleModel"));
                        list.setModelSP(jsonObject.getString("vehicleSubModel"));
                        list.setColor(jsonObject.getString("colour"));
                        list.setYear(jsonObject.getString("year"));
                        list.setVin(jsonObject.getString("vin"));
                        //list.setImage(image[i]);
                        arrayList.add(list);
                    }
                    adapter = new VehicleAdapter(getActivity(), arrayList);
                    recyclerView.setAdapter(adapter);
                }
                else{
                    Toast.makeText(getActivity(), object.getString("Message"), Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }*/
}
