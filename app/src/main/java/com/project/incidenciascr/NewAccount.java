package com.project.incidenciascr;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_account);
        button = (Button) findViewById(R.id.btn_confirmar);
        nombre = (EditText)findViewById(R.id.input_nombre);
        primerApellido = (EditText)findViewById(R.id.input_primer_apellido);
        segundoApellido = (EditText)findViewById(R.id.input_segundo_apellido);
        cedula = (EditText)findViewById(R.id.input_cedula);
        telefono = (EditText)findViewById(R.id.input_tel_cel);
        correo = (EditText)findViewById(R.id.input_email);
        direccion = (EditText)findViewById(R.id.txt_direccion);
        password = (EditText)findViewById(R.id.input_password_nueva_cuenta);
        confirmaPassword = (EditText)findViewById(R.id.input_password_nueva_cuenta_confirmacion);

        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                String nom = nombre.getText().toString();
                String lastName = primerApellido.getText().toString();
                String seconLastName = segundoApellido.getText().toString();
                String ced = cedula.getText().toString();
                String celular = telefono.getText().toString();
                String email = correo.getText().toString();
                String address = direccion.getText().toString();

                if(!nom.isEmpty()){
                    if(!lastName.isEmpty()){
                        if(!seconLastName.isEmpty()){
                            if(!ced.isEmpty()){
                                if(!celular.isEmpty()){
                                    String verificaEmail = "[a-zA-Z0-9\\+\\@\\-\\+]{1,256}"+
                                            "\\@" +
                                            "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" + "(" + "\\." + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                                            ")+";
                                    Matcher verifica = Pattern.compile(verificaEmail).matcher(email);
                                    if(verifica.matches()){
                                        if(!address.isEmpty()){
                                            if(verificaPassword()){
                                                dialogBox();
                                            }
                                        }else{
                                            Toast.makeText(getApplicationContext(), "Por favor ingrese su direccion", Toast.LENGTH_LONG).show();
                                        }
                                    }else{
                                        Toast.makeText(getApplicationContext(), "Por favor ingrese un correo electronico valido", Toast.LENGTH_LONG).show();
                                    }
                                }else{
                                    Toast.makeText(getApplicationContext(), "Por favor ingrese su numero telefonico", Toast.LENGTH_LONG).show();

                                }
                            }else{
                                Toast.makeText(getApplicationContext(), "Por favor ingrese su cedula", Toast.LENGTH_LONG).show();
                            }
                        }else{
                            Toast.makeText(getApplicationContext(), "Por favor ingrese su segundo apellido", Toast.LENGTH_LONG).show();
                        }
                    }else{
                        Toast.makeText(getApplicationContext(), "Por favor ingrese su primer apellido", Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(getApplicationContext(), "El nombre es requerido", Toast.LENGTH_LONG).show();
                }

            }
        });
    }


    private boolean verificaPassword(){
        String contra = password.getText().toString().trim();
        String confirmContra = confirmaPassword.getText().toString().trim();

        if(contra.isEmpty()){
            Toast.makeText(getApplicationContext(), "Por favor ingrese una contraseña", Toast.LENGTH_LONG).show();
            return false;
        }else if(!contra.matches(confirmContra)){
            Toast.makeText(getApplicationContext(), "las contraseñas no concuerdan", Toast.LENGTH_LONG).show();
            return false;
        }else{
            Toast.makeText(getApplicationContext(), "contraseña valida", Toast.LENGTH_LONG).show();

            return true;
        }
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
}
