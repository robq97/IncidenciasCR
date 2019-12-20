package com.project.incidenciascr;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Looper;
import android.provider.Settings;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.text.TextUtils;
import android.widget.TextView;
import android.widget.Toast;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import java.util.Arrays;
import java.util.List;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class NewIncidence extends AppCompatActivity {

    Integer REQUEST_CAMERA=1, SELECT_FILE=0;
    ImageView imagePreview;
    public double latitude, longitude;
    private EditText direccion, detalle;
    private Button button, btn_nva_inc;
    private ImageButton button_map;
    private Spinner spinner_categoria, spinner_entidad, spinner_provincias,
            spinner_cantones, spinner_distritos;
    private EditText txt_direccion, input_detalle;
    FusedLocationProviderClient gpsCliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gpsCliente = LocationServices.getFusedLocationProviderClient(this);

        setContentView(R.layout.activity_new_incidence);

        imagePreview = (ImageView) findViewById(R.id.img_preview);
        button = (Button) findViewById(R.id.btn_agregar_img);
        direccion = (EditText)findViewById(R.id.txt_direccion);
        detalle = (EditText)findViewById(R.id.input_detalle);
        btn_nva_inc = (Button) findViewById(R.id.btn_nva_inc);
        latTextView = findViewById(R.id.latTextView);
        lonTextView = findViewById(R.id.lonTextView);
        button_map = (ImageButton) findViewById(R.id.btn_map);

        spinner_categoria = (Spinner) findViewById(R.id.spinner_categoria);
        spinner_entidad = (Spinner) findViewById(R.id.spinner_entidad);
        spinner_provincias = (Spinner) findViewById(R.id.spinner_provincias);
        spinner_cantones = (Spinner) findViewById(R.id.spinner_cantones);
        spinner_distritos = (Spinner) findViewById(R.id.spinner_distritos);
        button_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMap();
            }
        });

        GoogleMap map;
        getLastLocation();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dir = direccion.getText().toString();
                String det = detalle.getText().toString();
                if(!dir.isEmpty()){
                    if(!det.isEmpty()){
                        showDialog();
                    }else{
                        Toast.makeText(getApplicationContext(), "Por favor ingrese el detalle del incidente", Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(getApplicationContext(), "Por favor ingrese la direccion del incidente", Toast.LENGTH_LONG).show();
                }
            }

        });

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
                if (itemSelect.equals("San José")){
                    ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(NewIncidence.this, R.layout.support_simple_spinner_dropdown_item, canton_san_jose);
                    spinner_cantones.setAdapter(adapter1);

                    spinner_cantones.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            final String itemSelect = spinner_cantones.getSelectedItem().toString();
                            if (itemSelect.equals("San José")){
                                ArrayAdapter<String> adapter = new ArrayAdapter<String>(NewIncidence.this, R.layout.support_simple_spinner_dropdown_item, distrito_san_jose);
                                spinner_distritos.setAdapter(adapter);
                            } else if (itemSelect.equals("Alajuelita")){
                                ArrayAdapter<String> adapter = new ArrayAdapter<String>(NewIncidence.this, R.layout.support_simple_spinner_dropdown_item, distrito_alajuelita);
                                spinner_distritos.setAdapter(adapter);
                            } else if (itemSelect.equals("Acosta")) {
                                ArrayAdapter<String> adapter = new ArrayAdapter<String>(NewIncidence.this, R.layout.support_simple_spinner_dropdown_item, distrito_acosta);
                                spinner_distritos.setAdapter(adapter);
                            }
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                }
                else if (itemSelect.equals("Alajuela")){
                    ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(NewIncidence.this, R.layout.support_simple_spinner_dropdown_item, canton_alajuela);
                    spinner_cantones.setAdapter(adapter2);
                }
                else if (itemSelect.equals("Heredia")){
                    ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(NewIncidence.this, R.layout.support_simple_spinner_dropdown_item, canton_heredia);
                    spinner_cantones.setAdapter(adapter3);
                }
                else if (itemSelect.equals("Cartago")){
                    ArrayAdapter<String> adapter4 = new ArrayAdapter<String>(NewIncidence.this, R.layout.support_simple_spinner_dropdown_item, canton_cartago);
                    spinner_cantones.setAdapter(adapter4);
                }
                else if (itemSelect.equals("Guanacaste")){
                    ArrayAdapter<String> adapter5 = new ArrayAdapter<String>(NewIncidence.this, R.layout.support_simple_spinner_dropdown_item, canton_guanacaste);
                    spinner_cantones.setAdapter(adapter5);
                }
                else if (itemSelect.equals("Puntarenas")){
                    ArrayAdapter<String> adapter6 = new ArrayAdapter<String>(NewIncidence.this, R.layout.support_simple_spinner_dropdown_item, canton_puntarenas);
                    spinner_cantones.setAdapter(adapter6);
                }
                else if (itemSelect.equals("Limón")){
                    ArrayAdapter<String> adapter7 = new ArrayAdapter<String>(NewIncidence.this, R.layout.support_simple_spinner_dropdown_item, canton_limon);
                    spinner_cantones.setAdapter(adapter7);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        ;

        btn_nva_inc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                agregarIncidencia(v);
            }
        });

        button_map.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                openMap();
            }
        });

        txt_direccion = (EditText) findViewById(R.id.txt_direccion);
        input_detalle = (EditText) findViewById(R.id.input_detalle);
    }

    public void openMap(){
        Intent intent = new Intent(this, MapsActivity.class);
        intent.putExtra("Latitude", latitude);
        intent.putExtra("Longitude", longitude);
        startActivity(intent);
    }

    private void showDialog(){
        AlertDialog.Builder pictureDialog = new AlertDialog.Builder(this);
        pictureDialog.setTitle("Eliga una opción");
        String[] pictureDialogItems = {
                "Foto de galería",
                "Foto de cámara" };
        pictureDialog.setItems(pictureDialogItems,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                fotoGaleria();
                                break;
                            case 1:
                                fotoCamara();
                                break;
                        }
                    }
                });
        pictureDialog.show();
    }

    public void fotoCamara(){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_CAMERA);
    }

    public void fotoGaleria() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent.createChooser(galleryIntent, "Seleccione una foto"), SELECT_FILE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {

            if (requestCode == REQUEST_CAMERA) {
                Bundle bundle = data.getExtras();
                final Bitmap bmp = (Bitmap) bundle.get("data");
                imagePreview.setImageBitmap(bmp);

            } else if (requestCode == SELECT_FILE) {
                Uri selectedImageUrl = data.getData();
                imagePreview.setImageURI(selectedImageUrl);
            }
        }
    }

    private void agregarIncidencia(View v) {
        BDConexion cnn = new BDConexion(this, "Admin", null, 1);
        SQLiteDatabase bd = cnn.getWritableDatabase();

        String cedula = ((Global) this.getApplication()).getCedula();

        try {

            ContentValues valores = new ContentValues();

            valores.put("categoria", spinner_categoria.getSelectedItem().toString());
            valores.put("entidad", spinner_entidad.getSelectedItem().toString());
            valores.put("provincia", spinner_provincias.getSelectedItem().toString());
            valores.put("canton", spinner_cantones.getSelectedItem().toString());
            valores.put("distrito", spinner_distritos.getSelectedItem().toString());
            valores.put("cedula", cedula);
            valores.put("estado", "Activo");

            if(!TextUtils.isEmpty(txt_direccion.getText().toString())) {
                valores.put("direccion", txt_direccion.getText().toString());
            }

            if(!TextUtils.isEmpty(input_detalle.getText().toString())) {
                valores.put("detalle", input_detalle.getText().toString());
            }

            if(!TextUtils.isEmpty(Double.toString(longitude))) {
                valores.put("longitud", Double.toString(longitude));
            }

            if(!TextUtils.isEmpty(Double.toString(latitude))) {
                valores.put("latitud", Double.toString(latitude));
            }

            bd.insert("Incidencia", null, valores);
            bd.close();

            txt_direccion.setText("");
            input_detalle.setText("");

            Toast.makeText(this, "Se agregó exitosamente.", Toast.LENGTH_LONG).show();

        }

        catch (Exception ex) {
            ex.getCause();
        }

    }

    private TextView latTextView, lonTextView;
    int PERMISSION_ID = 44;

    //chequea si ya autorizo permisos
    private boolean checkPermissions(){
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            return true;
        }
        return false;
    }

    @SuppressLint("MissingPermission")
    private void getLastLocation(){
        if (checkPermissions()) {
            if (isLocationEnabled()) {
                gpsCliente.getLastLocation().addOnCompleteListener(
                        new OnCompleteListener<Location>() {
                            @Override
                            public void onComplete(@NonNull Task<Location> task) {
                                Location location = task.getResult();
                                if (location == null) {
                                    nuevaLocalizacion();
                                } else {
                                    latTextView.setText(location.getLatitude()+"");
                                    latitude = location.getLatitude();
                                    lonTextView.setText(location.getLongitude()+"");
                                    longitude = location.getLongitude();
                                }
                            }
                        }
                );
            } else {
                Toast.makeText(this, "Turn on location", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);
            }
        } else {
            requestPermissions();
        }
    }

    @SuppressLint("MissingPermission")
    private void nuevaLocalizacion(){

        LocationRequest gpsPregunta = new LocationRequest();
        //para que la aplicacion cargue con la mayor certeza posible
        gpsPregunta.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        //estas dos lineas especifican cada cuanto se va a refresh la
        //localizacion del usuario
        gpsPregunta.setInterval(0);
        gpsPregunta.setFastestInterval(0);
        //esta linea inabilita la trazabilidad del usuario en tiempo real
        gpsPregunta.setNumUpdates(1);
        gpsCliente = LocationServices.getFusedLocationProviderClient(this);
        gpsCliente.requestLocationUpdates(
                gpsPregunta, gpsRespuesta,
                Looper.myLooper()
        );
    }

    //metodo que se invoca cuando se recibe un update en la localizacion
    private LocationCallback gpsRespuesta = new LocationCallback() {
        @Override
        public void onLocationResult(LocationResult locationResult) {
            Location ultimaLoclizacion = locationResult.getLastLocation();
            latTextView.setText(ultimaLoclizacion.getLatitude()+"");
            lonTextView.setText(ultimaLoclizacion.getLongitude()+"");
        }
    };

    //pide los persmisos si no estan abilitados
    private void requestPermissions(){
        ActivityCompat.requestPermissions(
                this,
                new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION},
                PERMISSION_ID
        );
    }

    private boolean isLocationEnabled(){
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
                LocationManager.NETWORK_PROVIDER
        );
    }

    //este metodo se encarga de recibir el permiso y determinar si sigue o no
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_ID) {
            if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                // Granted. Start getting the location information
            }
        }
    }

    @Override
    public void onResume(){
        super.onResume();
        if (checkPermissions()) {
            getLastLocation();
        }
    }
}
