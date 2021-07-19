package com.example.plusambiente;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A fragment representing a list of Items.
 */
public class DesechosFragment extends Fragment {
    RecyclerView recyclerView;
    MyDesechosRecyclerViewAdapter adapterDesecho;
    List<desechosEntity> desechosEntityList;

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    private static final String ARG_PARAM1 = "sol_id";
    // TODO: Customize parameters
    private int mColumnCount = 2;
    private String sol_id;


    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public DesechosFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static DesechosFragment newInstance(int columnCount, String sol_id) {
        DesechosFragment fragment = new DesechosFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        args.putString(ARG_PARAM1, sol_id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
            sol_id = (String) getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            // Lista de elemento de desechos

            if(sol_id != null){
                obteneDesechos("http://192.168.100.25/plusambiete2/servicios/cliente/listaDesechos.php?sol_id="+sol_id);
            }
        }
        return view;
    }
    public void obteneDesechos(String URL) {
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        StringRequest stringRequest = new StringRequest(Request.Method.GET,URL,
                new Response.Listener   <String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("desecho");
                            desechosEntityList = new ArrayList<>();
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject1 = jsonArray.getJSONObject(i);

                                String dsol_id = jsonObject1.getString("dsol_id");
                                String auxSolicitud = jsonObject1.getString("sol_id");
                                String des_codigo = jsonObject1.getString("des_codigo");
                                String des_descripcion = jsonObject1.getString("des_descripcion");
                                String cat_unidad = jsonObject1.getString("cat_unidad");
                                String cat_peligroso = jsonObject1.getString("cat_peligroso");
                                String dsol_cantidad = jsonObject1.getString("dsol_cantidad");


                                desechosEntityList.add(new desechosEntity(dsol_id,auxSolicitud, des_codigo, des_descripcion, cat_unidad, cat_peligroso, dsol_cantidad));
                            }
                            adapterDesecho = new MyDesechosRecyclerViewAdapter (getActivity(), desechosEntityList);
                            recyclerView.setAdapter(adapterDesecho);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        requestQueue.add(stringRequest);
    }
    @Override
    public void onAttach(@NonNull @NotNull Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}