package com.project.incidenciascr;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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

        final BDConexion cnn = new BDConexion(this, "Admin", null, 1);
        final SQLiteDatabase bd = cnn.getWritableDatabase();
        final String codigoGuardado = ((Global) this.getApplication()).getCodigoActivacion();
        final String cedula = ((Global) this.getApplication()).getCedula();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String codigo = cod.getText().toString();
                if(!codigo.isEmpty()){
                    if (codigoGuardado.equals(codigo)){
                        try {
                            String query = ("UPDATE Cuenta SET estado = 'activo' WHERE cedula = "+cedula);
                            Cursor fila = bd.rawQuery(query, null);
                            fila.moveToFirst();
                            fila.close();
                            openNewIncidence();
                        } catch (Exception ex){
                            ex.getCause();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "Código invalido.", Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(getApplicationContext(), "Por favor ingrese el código enviado a su email", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void openNewIncidence(){
        ((Global) this.getApplication()).setEstadoActivacion("activo");
        Intent intent = new Intent(this, NewIncidence.class);
        startActivity(intent);
    }
}
