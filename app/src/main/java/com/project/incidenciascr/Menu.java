package com.project.incidenciascr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Menu extends AppCompatActivity {

    private Button button;

    private long backPressedTime;
    private Toast backToast;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        button = (Button) findViewById(R.id.btn_nueva_incidencia);

        if (((Global) this.getApplication()).getEstadoActivacion().equals("inactivo")){
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openActivation();
                }
            });
        } else {
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openNewIncidence();
                }
            });
        }

        button = (Button) findViewById(R.id.btn_actualizacion_incidencia);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openUpdateIncidence();
            }
        });

        button = (Button) findViewById(R.id.btn_cerrar_sesion);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backToMain();
            }
        });
    }

    public void openActivation(){
        Intent intent = new Intent(this, AccountActivation.class);
        startActivity(intent);
    }

    public void openNewIncidence(){
        Intent intent = new Intent(this, NewIncidence.class);
        startActivity(intent);
    }

    public void openUpdateIncidence(){
        Intent intent = new Intent(this, UpdateIncidence.class);
        startActivity(intent);
    }

    public void backToMain(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
