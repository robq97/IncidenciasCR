package com.project.incidenciascr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Login extends AppCompatActivity {

    private Button btnNuevaCuenta, btnIngresar;
    private EditText email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnNuevaCuenta = (Button)findViewById(R.id.btn_nueva_cuenta);
        email = (EditText)findViewById(R.id.input_email);
        password = (EditText)findViewById(R.id.input_password);
        btnIngresar = (Button)findViewById(R.id.btn_ingresar);


        btnNuevaCuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNewAccount();
            }
        });

        btnIngresar.setOnClickListener(new View.OnClickListener(){
          @Override
          public void onClick(View v) {
              String verificaEmail = "[a-zA-Z0-9\\+\\@\\-\\+]{1,256}"+
                      "\\@" +
                      "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" + "(" + "\\." + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                      ")+";
              String mail = email.getText().toString();
              Matcher verifica = Pattern.compile(verificaEmail).matcher(mail);
              if(verifica.matches()){
                  if(verificaPassword()){
                      openMenu();
                  }
              }else{
                  Toast.makeText(getApplicationContext(), "Por favor ingrese un email valido", Toast.LENGTH_LONG).show();
                  email.setText(null);
              }
          }
        });
    }

    public void openMenu(){
        Intent intent = new Intent(this, Menu.class);
        startActivity(intent);
    }

    private boolean verificaPassword(){
        String contra = password.getText().toString().trim();
        if(contra.isEmpty()){
            Toast.makeText(getApplicationContext(), "Por favor ingrese una contraseña", Toast.LENGTH_LONG).show();
            return false;
        }else{
            Toast.makeText(getApplicationContext(), "contraseña valida", Toast.LENGTH_LONG).show();

            return true;
        }
    }

    public void openNewAccount(){
        Intent intent = new Intent(this, NewAccount.class);
        startActivity(intent);
    }
}
