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
import android.database.Cursor;

public class Login extends AppCompatActivity {

    private Button button;

    private EditText input_email, input_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        button = (Button) findViewById(R.id.btn_nueva_cuenta);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNewAccount();
            }
        });

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
