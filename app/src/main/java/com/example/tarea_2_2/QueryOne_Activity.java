package com.example.tarea_2_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.tarea_2_2.process.ApiMethods;
import com.example.tarea_2_2.process.Clase;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class QueryOne_Activity extends AppCompatActivity {

    EditText txtPost;
    Button btnSave;
    ListView listTwo;
    private ArrayList<Clase> clase;
    private ArrayList<String> classArray;
    private ArrayAdapter adp;
    private Clase claseo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query_one);

        getObj();

        getList();

        listTwo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(!clase.isEmpty()){
                    txtPost.setText(Integer.toString(clase.get(i).id));
                }
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!txtPost.getText().toString().isEmpty()){
                    getPost(txtPost.getText().toString());
                }
            }
        });

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
        listTwo.setAdapter(adp);
    }

    private void getPost(String id){
        RequestQueue req = Volley.newRequestQueue(this);

        String url = ApiMethods.getApi(id);

        JsonObjectRequest jObjReq = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try{
                    Clase claseD = new Clase(
                            response.getInt("userId"),
                            response.getInt("id"),
                            response.getString("title"),
                            response.getString("body")
                    );
                    claseo = claseD;
                    Intent intent = new Intent(getApplicationContext(), View_Query_Activity.class);
                    intent.putExtra("id", Integer.toString(claseo.id));
                    intent.putExtra("uid", Integer.toString(claseo.userId));
                    intent.putExtra("title", claseo.title);
                    intent.putExtra("body", claseo.body);
                    startActivity(intent);
                }catch (JSONException e){

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        req.add(jObjReq);
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
        req.add(arrReq);
    }

    private void getObj(){
        txtPost = (EditText) findViewById(R.id.txtPost);
        btnSave = (Button) findViewById(R.id.btnSave);
        listTwo = (ListView) findViewById(R.id.listTwo);
        clase = new ArrayList<Clase>();
        classArray = new ArrayList<String>();
        claseo = new Clase();
    }
}