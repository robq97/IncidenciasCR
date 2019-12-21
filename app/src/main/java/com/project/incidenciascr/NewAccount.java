package com.project.incidenciascr;

import android.net.Uri;
//import android.se.omapi.Session;
import android.util.Log;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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
import java.util.Arrays;
import java.util.Random;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.mail.MessagingException;


public class NewAccount extends AppCompatActivity {

    private Button button;
    private EditText input_nombre, input_primer_apellido, input_segundo_apellido, input_cedula, input_tel_cel, input_email,
            input_password_nueva_cuenta, input_password_nueva_cuenta_confirmacion, txt_direccion;
    private Spinner spinner_provincias, spinner_cantones, spinner_distritos;
    private String codigo_correo;

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

        final List<String> provincias = Arrays.asList(getResources().getStringArray(R.array.Provincia));
        final List<String> canton_san_jose = Arrays.asList(getResources().getStringArray(R.array.Canton_San_Jose));
        final List<String> canton_alajuela = Arrays.asList(getResources().getStringArray(R.array.Canton_Alajuela));
        final List<String> canton_heredia = Arrays.asList(getResources().getStringArray(R.array.Canton_Heredia));
        final List<String> canton_cartago = Arrays.asList(getResources().getStringArray(R.array.Canton_Cartago));
        final List<String> canton_puntarenas = Arrays.asList(getResources().getStringArray(R.array.Canton_Puntarenas));
        final List<String> canton_limon = Arrays.asList(getResources().getStringArray(R.array.Canton_Limon));
        final List<String> canton_guanacaste = Arrays.asList(getResources().getStringArray(R.array.Canton_Guanacaste));
        final List<String> distrito_acosta = Arrays.asList(getResources().getStringArray(R.array.Distrito_Acosta));
        final List<String> distrito_alajuelita = Arrays.asList(getResources().getStringArray(R.array.Distrito_Alajuelita));
        final List<String> distrito_san_jose = Arrays.asList(getResources().getStringArray(R.array.Distrito_San_Jose));

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, provincias);
        spinner_provincias.setAdapter(adapter);

        spinner_provincias.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                final String itemSelect = spinner_provincias.getSelectedItem().toString();
                if (itemSelect.equals("San José")) {
                    ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(NewAccount.this, R.layout.support_simple_spinner_dropdown_item, canton_san_jose);
                    spinner_cantones.setAdapter(adapter1);

                    spinner_cantones.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            final String itemSelect = spinner_cantones.getSelectedItem().toString();
                            if (itemSelect.equals("San José")) {
                                ArrayAdapter<String> adapter = new ArrayAdapter<String>(NewAccount.this, R.layout.support_simple_spinner_dropdown_item, distrito_san_jose);
                                spinner_distritos.setAdapter(adapter);
                            } else if (itemSelect.equals("Alajuelita")) {
                                ArrayAdapter<String> adapter = new ArrayAdapter<String>(NewAccount.this, R.layout.support_simple_spinner_dropdown_item, distrito_alajuelita);
                                spinner_distritos.setAdapter(adapter);
                            } else if (itemSelect.equals("Acosta")) {
                                ArrayAdapter<String> adapter = new ArrayAdapter<String>(NewAccount.this, R.layout.support_simple_spinner_dropdown_item, distrito_acosta);
                                spinner_distritos.setAdapter(adapter);
                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });

                } else if (itemSelect.equals("Alajuela")) {
                    ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(NewAccount.this, R.layout.support_simple_spinner_dropdown_item, canton_alajuela);
                    spinner_cantones.setAdapter(adapter2);
                } else if (itemSelect.equals("Heredia")) {
                    ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(NewAccount.this, R.layout.support_simple_spinner_dropdown_item, canton_heredia);
                    spinner_cantones.setAdapter(adapter3);
                } else if (itemSelect.equals("Cartago")) {
                    ArrayAdapter<String> adapter4 = new ArrayAdapter<String>(NewAccount.this, R.layout.support_simple_spinner_dropdown_item, canton_cartago);
                    spinner_cantones.setAdapter(adapter4);
                } else if (itemSelect.equals("Guanacaste")) {
                    ArrayAdapter<String> adapter5 = new ArrayAdapter<String>(NewAccount.this, R.layout.support_simple_spinner_dropdown_item, canton_guanacaste);
                    spinner_cantones.setAdapter(adapter5);
                } else if (itemSelect.equals("Puntarenas")) {
                    ArrayAdapter<String> adapter6 = new ArrayAdapter<String>(NewAccount.this, R.layout.support_simple_spinner_dropdown_item, canton_puntarenas);
                    spinner_cantones.setAdapter(adapter6);
                } else if (itemSelect.equals("Limón")) {
                    ArrayAdapter<String> adapter7 = new ArrayAdapter<String>(NewAccount.this, R.layout.support_simple_spinner_dropdown_item, canton_limon);
                    spinner_cantones.setAdapter(adapter7);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        button.setOnClickListener(new View.OnClickListener() {
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

    public void openMenu() {
        Intent intent = new Intent(this, Menu.class);
        startActivity(intent);
    }

    private boolean passwordMatch() {
        if (input_password_nueva_cuenta.getText().toString().equals(input_password_nueva_cuenta_confirmacion.getText().toString())) {
            return true;
        } else {
            return false;
        }
    }

    private boolean emailCheck() {
        String verificaEmail = "[a-zA-Z0-9\\+\\@\\-\\+]{1,256}" +
                "\\@" +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" + "(" + "\\." + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                ")+";
        Matcher verifica = Pattern.compile(verificaEmail).matcher(input_email.getText().toString());
        if (!verifica.matches()) {
            Toast.makeText(getApplicationContext(), "Por favor ingrese un correo electronico valido", Toast.LENGTH_LONG).show();
            input_email.setText("");
            return false;
        } else {
            return true;
        }
    }

    private boolean phoneCheck() {
        String phone = input_tel_cel.getText().toString();
        char first = phone.charAt(0);
        if (phone.length() == 8) {
            if (first == '2' || first == '6' || first == '7' || first == '8' || first == '5') {
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

    private boolean identificationCheck() {
        String cedula = input_cedula.getText().toString();
        if (cedula.length() == 9) {
            return true;
        } else {
            Toast.makeText(getApplicationContext(), "Por favor ingrese un número de cédula valido.", Toast.LENGTH_LONG).show();
            input_cedula.setText("");
            return false;
        }
    }

    private boolean passwordCheck() {
        String password = input_password_nueva_cuenta.getText().toString();
        String passwordConfirmation = input_password_nueva_cuenta_confirmacion.getText().toString();
        if (password.length() >= 8 && passwordConfirmation.length() >= 8) {
            return true;
        } else {
            Toast.makeText(this, "La contraseña debe de contener mínimo 8 caracteres.", Toast.LENGTH_LONG).show();
            input_password_nueva_cuenta.setText("");
            input_password_nueva_cuenta_confirmacion.setText("");
            return false;
        }
    }

    private void nuevaCuenta(View v) {
        BDConexion cnn = new BDConexion(this, "Admin", null, 1);
        SQLiteDatabase bd = cnn.getWritableDatabase();
        ContentValues valores = new ContentValues();

        try {
            //validar campos en blanco
            if (!TextUtils.isEmpty(input_nombre.getText().toString()) ||
                    !TextUtils.isEmpty(input_primer_apellido.getText().toString()) ||
                    !TextUtils.isEmpty(input_segundo_apellido.getText().toString()) ||
                    !TextUtils.isEmpty(input_cedula.getText().toString()) ||
                    !TextUtils.isEmpty(input_tel_cel.getText().toString())
            ) {
                //valida si el email es valido
                if (emailCheck()) {
                    //valida si el tel/cel es valido
                    if (phoneCheck()) {
                        //valida si se ingresa un numero de cedula valido
                        if (identificationCheck()) {
                            //validar si contraseñas en blanco, si son iguales, y minimo de 8 caracteres
                            if (passwordCheck()) {
                                if (!TextUtils.isEmpty(input_password_nueva_cuenta.getText().toString()) || !TextUtils.isEmpty(input_password_nueva_cuenta_confirmacion.getText().toString())) {
                                    if (passwordMatch()) {
                                        int codigo_correo_int = 10000 + new Random().nextInt(90000);
                                        codigo_correo = Integer.toString(codigo_correo_int);
                                        ((Global) this.getApplication()).setCodigoActivacion(codigo_correo);
                                        String estado = "inactivo";

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
                                        valores.put("codigo_correo", codigo_correo);
                                        valores.put("estado", estado);

                                        ((Global) this.getApplication()).setCedula(input_cedula.getText().toString());
                                        ((Global) this.getApplication()).setEstadoActivacion(estado);

                                        if (!TextUtils.isEmpty(txt_direccion.getText().toString())) {
                                            valores.put("direccion", txt_direccion.getText().toString());
                                        }
                                        bd.insert("Cuenta", null, valores);
                                        bd.close();
                                        sendMail();
                                        Toast.makeText(this, "Se agregó exitosamente.", Toast.LENGTH_LONG).show();
                                        dialogBox();
                                    } else {
                                        Toast.makeText(this, "Las contraseñas no coinciden.", Toast.LENGTH_LONG).show();
                                        input_password_nueva_cuenta.setText("");
                                        input_password_nueva_cuenta_confirmacion.setText("");
                                    }
                                }
                            }
                        }
                    }
                }
            } else {
                Toast.makeText(this, "Debe llenar todos los campos solicitados.", Toast.LENGTH_LONG).show();
            }
        } catch (Exception ex) {

        }
    }
    
    public void sendMail() throws MessagingException {
        String codigoActivacion = "Código de Activación - IncidenciasCR";
        String mensaje = "Gracias por registrarse con IncidenciasCR, su código de activación es: " + codigo_correo.toString()
                + ", este le será solicitado cuando intente agregar una incidencia.";
        Email email = new Email(this, input_email.getText().toString(), codigoActivacion, mensaje);
        email.execute();
    }
}






