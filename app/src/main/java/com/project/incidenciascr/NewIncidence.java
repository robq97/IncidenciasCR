package com.project.incidenciascr;

import androidx.appcompat.app.AppCompatActivity;
import android.content.ContentValues;
import android.widget.EditText;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.widget.Spinner;
import android.text.TextUtils;
import android.widget.Toast;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import java.io.IOException;
import android.widget.ImageView;
import android.widget.Gallery;

import com.google.android.gms.maps.GoogleMap;

import static android.media.MediaRecorder.VideoSource.CAMERA;



public class NewIncidence extends AppCompatActivity {

    private Button button, btn_nva_inc;
    private Spinner spinner_categoria, spinner_entidad, spinner_provincias,
            spinner_cantones, spinner_distritos;
    private EditText txt_direccion, input_detalle;
    GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_incidence);

        button = (Button) findViewById(R.id.btn_agregar_img);
        btn_nva_inc = (Button) findViewById(R.id.btn_nva_inc);

        GoogleMap map;

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }

        });

        btn_nva_inc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                agregarIncidencia(v);
            }
        });

        spinner_categoria = (Spinner) findViewById(R.id.spinner_categoria);
        spinner_entidad = (Spinner) findViewById(R.id.spinner_entidad);
        spinner_provincias = (Spinner) findViewById(R.id.spinner_provincias);
        spinner_cantones = (Spinner) findViewById(R.id.spinner_cantones);
        spinner_distritos = (Spinner) findViewById(R.id.spinner_distritos);

        txt_direccion = (EditText) findViewById(R.id.txt_direccion);
        input_detalle = (EditText) findViewById(R.id.input_detalle);
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

    public void fotoGaleria() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);


        //startActivityForResult(galleryIntent, GALLERY);
    }

    private void fotoCamara() {
        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, CAMERA);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        /*super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == this.RESULT_CANCELED) {
            return;
        }
        if (requestCode == GALLERY) {
            if (data != null) {
                Uri contentURI = data.getData();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), contentURI);
                    String path = saveImage(bitmap);
                    Toast.makeText(this, "Image Saved!", Toast.LENGTH_SHORT).show();
                    imageview.setImageBitmap(bitmap);

                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(this, "Failed!", Toast.LENGTH_SHORT).show();
                }
            }

        } else if (requestCode == CAMERA) {
            Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
            imageview.setImageBitmap(thumbnail);
            saveImage(thumbnail);
            Toast.makeText(this, "Image Saved!", Toast.LENGTH_SHORT).show();
        }*/
    }

    private void agregarIncidencia(View v) {
        BDConexion cnn = new BDConexion(this, "Admin", null, 1);
        SQLiteDatabase bd = cnn.getWritableDatabase();

        try {

            ContentValues valores = new ContentValues();

            valores.put("categoria", spinner_categoria.getSelectedItem().toString());
            valores.put("entidad", spinner_entidad.getSelectedItem().toString());
            valores.put("provincia", spinner_provincias.getSelectedItem().toString());
            valores.put("canton", spinner_cantones.getSelectedItem().toString());
            valores.put("distrito", spinner_distritos.getSelectedItem().toString());

            if(!TextUtils.isEmpty(txt_direccion.getText().toString())) {
                valores.put("direccion", txt_direccion.getText().toString());
            }

            if(!TextUtils.isEmpty(input_detalle.getText().toString())) {
                valores.put("detalle", input_detalle.getText().toString());
            }

            bd.insert("Incidencia", null, valores);

            bd.close();

            txt_direccion.setText("");
            input_detalle.setText("");

            Toast.makeText(this, "Se agregó exitosamente.", Toast.LENGTH_LONG).show();

        }

        catch (Exception ex) {
            Toast.makeText(this, "Please end my life.", Toast.LENGTH_LONG).show();
        }

    }
}
