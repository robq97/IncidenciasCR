package com.project.incidenciascr;

import android.widget.EditText;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Spinner;
import android.text.TextUtils;
import android.widget.Toast;
import android.content.ContentValues;
import androidx.appcompat.app.AppCompatActivity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NewAccount extends AppCompatActivity {

    private Button button;
    private EditText nombre,primerApellido, segundoApellido, cedula, telefono,
    correo, direccion, password, confirmaPassword;
    private EditText input_nombre, input_primer_apellido, input_segundo_apellido, input_cedula, input_tel_cel, input_email,
            input_password_nueva_cuenta, input_password_nueva_cuenta_confirmacion, txt_direccion;
    private Spinner spinner_provincias, spinner_cantones, spinner_distritos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_account);
        button = (Button) findViewById(R.id.btn_confirmar);
        input_nombre = (EditText) findViewById(R.id.input_nombre);
        input_primer_apellido = (EditText) findViewById(R.id.input_primer_apellido);
        input_segundo_apellido = (EditText) findViewById(R.id.input_segundo_apellido);
        input_cedula = (EditText) findViewById(R.id.input_cedula);
        input_tel_cel = (EditText) findViewById(R.id.input_tel_cel);
        input_email = (EditText) findViewById(R.id.input_email);
        input_password_nueva_cuenta = (EditText) findViewById(R.id.input_password_nueva_cuenta);
        input_password_nueva_cuenta_confirmacion = (EditText) findViewById(R.id.input_password_nueva_cuenta_confirmacion);
        txt_direccion = (EditText) findViewById(R.id.txt_direccion);

        spinner_provincias = (Spinner) findViewById(R.id.spinner_provincias);
        spinner_cantones = (Spinner) findViewById(R.id.spinner_cantones);
        spinner_distritos = (Spinner) findViewById(R.id.spinner_distritos);

        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                nuevaCuenta(v);
            }
        });
    }

    public void dialogBox() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Active su cuenta");
        alertDialogBuilder.setMessage("En su correo electrónico encontrara un código para la activación de su cuenta. Ingreselo cuando se le solicite.");
        alertDialogBuilder.setPositiveButton("Ok",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        openMenu();
                    }
                });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    public void openMenu(){
        Intent intent = new Intent(this, Menu.class);
        startActivity(intent);
    }

    private boolean passwordMatch() {
        if(input_password_nueva_cuenta.getText().toString().equals(input_password_nueva_cuenta_confirmacion.getText().toString())) {
            return true;
        } else {
            return false;
        }
    }

    private boolean emailCheck(){
            String verificaEmail = "[a-zA-Z0-9\\+\\@\\-\\+]{1,256}"+
                    "\\@" +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" + "(" + "\\." + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                    ")+";
            Matcher verifica = Pattern.compile(verificaEmail).matcher(input_email.getText().toString());
            if (!verifica.matches()){
                Toast.makeText(getApplicationContext(), "Por favor ingrese un correo electronico valido", Toast.LENGTH_LONG).show();
                input_email.setText("");
                return false;
            } else {
                return true;
            }
    }

    private boolean phoneCheck(){
        String phone = input_tel_cel.getText().toString();
        char first = phone.charAt(0);
        if (phone.length() == 8){
            if (first == '2' || first == '6' || first == '7'|| first == '8'|| first == '5'){
                return true;
            } else {
                Toast.makeText(getApplicationContext(), "Por favor ingrese un número de teléfono valido.", Toast.LENGTH_LONG).show();
                input_tel_cel.setText("");
                return false;
            }
        } else {
            Toast.makeText(getApplicationContext(), "Por favor ingrese un número de teléfono valido.", Toast.LENGTH_LONG).show();
            input_tel_cel.setText("");
            return false;
        }
    }

    private boolean identificationCheck(){
        String cedula =  input_cedula.getText().toString();
        if (cedula.length() == 9){
            return true;
        }else{
            Toast.makeText(getApplicationContext(), "Por favor ingrese un número de cédula valido.", Toast.LENGTH_LONG).show();
            input_cedula.setText("");
            return false;
        }
    }

    private void nuevaCuenta(View v) {
        BDConexion cnn = new BDConexion(this, "Admin", null, 1);
        SQLiteDatabase bd = cnn.getWritableDatabase();
        ContentValues valores = new ContentValues();

        try {
            //validar campos en blanco
            if(!TextUtils.isEmpty(input_nombre.getText().toString()) ||
                    !TextUtils.isEmpty(input_primer_apellido.getText().toString()) ||
                    !TextUtils.isEmpty(input_segundo_apellido.getText().toString()) ||
                    !TextUtils.isEmpty(input_cedula.getText().toString()) ||
                    !TextUtils.isEmpty(input_tel_cel.getText().toString())
            ) {
                //valida si el email es valido
                if (emailCheck()){
                    //valida si el tel/cel es valido
                    if (phoneCheck()){
                        //valida si se ingresa un numero de cedula valido
                        if (identificationCheck()){
                            //validar si contraseñas en blanco, y luego si son iguales
                            if(!TextUtils.isEmpty(input_password_nueva_cuenta.getText().toString()) || !TextUtils.isEmpty(input_password_nueva_cuenta_confirmacion.getText().toString())) {
                                if(passwordMatch()) {

                                    valores.put("nombre", input_nombre.getText().toString());
                                    valores.put("primer_apellido", input_primer_apellido.getText().toString());
                                    valores.put("segundo_apellido", input_segundo_apellido.getText().toString());
                                    valores.put("cedula", input_cedula.getText().toString());
                                    valores.put("tel_cel", input_tel_cel.getText().toString());
                                    valores.put("correo_electronico", input_email.getText().toString());
                                    valores.put("clave", input_password_nueva_cuenta.getText().toString());

                                    valores.put("provincia", spinner_provincias.getSelectedItem().toString());
                                    valores.put("canton", spinner_cantones.getSelectedItem().toString());
                                    valores.put("distrito", spinner_distritos.getSelectedItem().toString());

                                    if(!TextUtils.isEmpty(txt_direccion.getText().toString())) {
                                        valores.put("direccion", txt_direccion.getText().toString());
                                    }

                                    bd.insert("Cuenta", null, valores);

                                    bd.close();

                                    Toast.makeText(this, "Se agregó exitosamente.", Toast.LENGTH_LONG).show();
                                    dialogBox();
                                } else {
                                    Toast.makeText(this, "Contraseñas no coinciden.", Toast.LENGTH_LONG).show();
                                    input_password_nueva_cuenta.setText("");
                                    input_password_nueva_cuenta_confirmacion.setText("");
                                }
                            }
                        }
                    }
                }

            }else {
                Toast.makeText(this, "A excepción de su dirección, debe llenar todos los campos solicitados.", Toast.LENGTH_LONG).show();
            }
            /*
            input_nombre.setText("");
            input_primer_apellido.setText("");
            input_segundo_apellido.setText("");
            txt_direccion.setText("");

             */
        }
        catch (Exception ex) {

        }
    }
}
