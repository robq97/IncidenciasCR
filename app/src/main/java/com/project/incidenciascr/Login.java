package com.project.incidenciascr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import android.database.Cursor;


public class Login extends AppCompatActivity {

    private Button btnNuevaCuenta, btnIngresar;
    private EditText email, password;

    private EditText input_email, input_password;

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

              //string que valida correo con expresiones regulares
              //espera un set de caracteres en las primeras llaves, luego un @
              //luego otro set de caracteres, luego un punto, y luego otro set de caracteres
              String verificaEmail = "[a-zA-Z0-9\\+\\@\\-\\+]{1,256}"+
                      "\\@" +
                      "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" + "(" + "\\." + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                      ")+";

              //se agarra lo que el usuario haya introducido en el campo y se convierte a un string
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

        input_email = (EditText) findViewById(R.id.input_email);
        input_password = (EditText) findViewById(R.id.input_password);

    }

    public void openNewAccount(){
        Intent intent = new Intent(this, NewAccount.class);
        startActivity(intent);
    }

    private boolean logInCheckBD(View v) {

        BDConexion cnn = new BDConexion(this, "Admin", null, 1);
        SQLiteDatabase bd = cnn.getWritableDatabase();

        try {
            //validar si campos están llenos
            if(!TextUtils.isEmpty(input_email.getText().toString()) || !TextUtils.isEmpty(input_password.getText().toString()) ) {

                Cursor fila = bd.rawQuery("SELECT * FROM Cuenta WHERE correo_electronico =" + input_email.getText().toString(), null);

                if(input_password.getText().toString() == fila.getString(10)) {
                    return true;
                } else {
                    return false;
                }

            } else {
                Toast.makeText(this, "Debe llenar todos los campos solicitados.", Toast.LENGTH_LONG).show();
                return false;
            }

        } catch (Exception ex) {
            return false;
        }

    }
}
