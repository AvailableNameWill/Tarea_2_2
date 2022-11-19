package com.example.tarea_2_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btnConAll, btnConOne;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getObj();

        btnConAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), queryAll_Activity.class);
                startActivity(intent);
            }
        });

        btnConOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), QueryOne_Activity.class);
                startActivity(intent);
            }
        });
    }

    private void getObj(){
        btnConAll = (Button) findViewById(R.id.btnConAll);
        btnConOne = (Button) findViewById(R.id.btnConOne);
    }
}