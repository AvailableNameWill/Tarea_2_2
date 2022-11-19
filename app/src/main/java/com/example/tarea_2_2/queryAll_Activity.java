package com.example.tarea_2_2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.tarea_2_2.process.ApiMethods;
import com.example.tarea_2_2.process.Clase;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class queryAll_Activity extends AppCompatActivity {

    private ArrayList<Clase> clase;
    private ArrayList<String> classArray;
    private ListView listOne;
    private ArrayAdapter adp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query_all);

        getObj();

        getList();
    }

    private ArrayList<String> getData(){
        ArrayList<String> arr = new ArrayList<String>();

        for(int i=0; i<clase.size(); i++){
            arr.add("ID: " + Integer.toString(clase.get(i).id) + " - Title: " + clase.get(i).title);
        }
        return arr;
    }

    private void setListAdp(){
        adp = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, classArray);
        listOne.setAdapter(adp);
    }

    private void getList(){
        RequestQueue req = Volley.newRequestQueue(this);

        JsonArrayRequest arrReq = new JsonArrayRequest(Request.Method.GET, ApiMethods.apiRead1, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for(int i=0; i<response.length(); i++){
                    try{
                        JSONObject jObject = response.getJSONObject(i);
                        Clase clas = new Clase(
                                jObject.getInt("userId"),
                                jObject.getInt("id"),
                                jObject.getString("title"),
                                jObject.getString("body")
                        );
                        clase.add(clas);
                    }catch (JSONException e){
                        e.printStackTrace();
                    }
                }
                classArray = getData();

                setListAdp();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(error.toString());
            }
        });

        /*StringRequest reqString = new StringRequest(Request.Method.GET, ApiMethods.apiRead2, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try{
                    JSONObject jObject = new JSONObject(response);
                    JSONArray pArray = jObject.getJSONArray("");

                    for(int i=0; i<pArray.length(); i++){
                        JSONObject rowC = pArray.getJSONObject(i);

                        Clase clas = new Clase(
                            rowC.getInt("userId"),
                            rowC.getInt("id"),
                            rowC.getString("title"),
                            rowC.getString("body")
                        );
                        clase.add(clas);
                    }
                    classArray = getData();

                    setListAdp();
                }catch (JSONException e){
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });*/
        req.add(arrReq);
    }

    private void getObj(){
        listOne = (ListView) findViewById(R.id.listOne);
        clase = new ArrayList<Clase>();
        classArray = new ArrayList<String>();
    }
}