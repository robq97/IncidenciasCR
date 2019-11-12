package com.project.incidenciascr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AccountActivation extends AppCompatActivity {

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_activation);

        button = (Button) findViewById(R.id.btn_confirmar);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNewIncidence();
            }
        });
    }

    public void openNewIncidence(){
        Intent intent = new Intent(this, NewIncidence.class);
        startActivity(intent);
    }
}
