package com.project.incidenciascr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Menu extends AppCompatActivity {

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        button = (Button) findViewById(R.id.btn_nueva_incidencia);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivation();
            }
        });

        button = (Button) findViewById(R.id.btn_actualizacion_incidencia);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openUpdateIncidence();
            }
        });
    }

    public void openActivation(){
        Intent intent = new Intent(this, AccountActivation.class);
        startActivity(intent);
    }

    public void openUpdateIncidence(){
        Intent intent = new Intent(this, UpdateIncidence.class);
        startActivity(intent);
    }
}
