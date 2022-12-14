package com.example.tarea_2_2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class View_Query_Activity extends AppCompatActivity {

    TextView TVID, TVUID, TVtitle, TVbody;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_query);

        getObj();

        Bundle bundle = getIntent().getExtras();

        TVID.setText( "ID: " + bundle.getString("id"));
        TVUID.setText("ID de usuario:"+bundle.getString("uid"));
        TVtitle.setText("Titulo: "+bundle.getString("title"));
        TVbody.setText("Cuerpo: "+bundle.getString("body"));

    }

    private void getObj(){
        TVID = (TextView) findViewById(R.id.TVID);
        TVUID = (TextView) findViewById(R.id.TVUID);
        TVtitle = (TextView) findViewById(R.id.TVtitle);
        TVbody = (TextView) findViewById(R.id.TVbody);
    }
}