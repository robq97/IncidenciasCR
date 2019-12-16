package com.project.incidenciascr;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AccountActivation extends AppCompatActivity {

    private Button button;
    private EditText cod;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_activation);

        button = (Button) findViewById(R.id.btn_confirmar);
        cod = (EditText)findViewById(R.id.input_codigo);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String pass = cod.getText().toString();
                if(!pass.isEmpty()){
                    openNewIncidence();
                }else{
                    Toast.makeText(getApplicationContext(), "Por favor ingrese el código enviado a su email", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void openNewIncidence(){
        Intent intent = new Intent(this, NewIncidence.class);
        startActivity(intent);
    }
}
