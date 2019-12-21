package com.project.incidenciascr;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import android.database.Cursor;
import java.util.concurrent.ThreadLocalRandom;


public class Login extends AppCompatActivity {

    private Button btnNuevaCuenta, btn_ingresar;
    private EditText input_email, input_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnNuevaCuenta = (Button)findViewById(R.id.btn_nueva_cuenta);
        input_email = (EditText) findViewById(R.id.input_email);
        input_password = (EditText) findViewById(R.id.input_password);
        btn_ingresar = (Button) findViewById(R.id.btn_ingresar);

        btn_ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logInCheckBD(view);
            }
        });

        btnNuevaCuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNewAccount();
            }
        });
    }

    private boolean emailCheck(){
        String verificaEmail = "[a-zA-Z0-9\\+\\@\\-\\+]{1,256}"+
                "\\@" +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" + "(" + "\\." + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                ")+";
        Matcher verifica = Pattern.compile(verificaEmail).matcher(input_email.getText().toString());
        if (!verifica.matches()){
            Toast.makeText(getApplicationContext(), "Por favor ingrese un correo electronico valido", Toast.LENGTH_LONG).show();
            return false;
        } else {
            return true;
        }
    }

    public void openMenu(){
        Intent intent = new Intent(this, Menu.class);
        startActivity(intent);
    }

    private boolean logInCheckBD(View v) {

        BDConexion cnn = new BDConexion(this, "Admin", null, 1);
        SQLiteDatabase bd = cnn.getWritableDatabase();

        try {
            //validar si campos están llenos
            if(!TextUtils.isEmpty(input_email.getText().toString()) || !TextUtils.isEmpty(input_password.getText().toString()) ) {
                //valido email valido
                if (emailCheck()){
                    String correo = input_email.getText().toString();
                    String password = input_password.getText().toString();
                    Cursor fila = bd.rawQuery("SELECT * FROM Cuenta WHERE correo_electronico = ? AND clave = ? ", new String[] {correo, password});
                    if (fila.getCount() > 0){
                        fila.moveToFirst();
                        ((Global) this.getApplication()).setCedula(fila.getString(0));
                        ((Global) this.getApplication()).setEstadoActivacion(fila.getString(12));
                        ((Global) this.getApplication()).setCodigoActivacion(fila.getString(11));

                        Toast.makeText(this, "Bienvenido.", Toast.LENGTH_LONG).show();
                        openMenu();
                        input_email.setText("");
                        input_password.setText("");
                        return true;
                    } else {
                        Toast.makeText(this, "Contraseña y/o correo invalido.", Toast.LENGTH_LONG).show();
                        return false;
                    }
                }
            }else {
                Toast.makeText(this, "Debe llenar todos los campos solicitados.", Toast.LENGTH_LONG).show();
                return false;
            }

        } catch (Exception ex) {
            return false;
        }
        return false;
    }

    public void openNewAccount(){
        Intent intent = new Intent(this, NewAccount.class);
        startActivity(intent);
    }
}
