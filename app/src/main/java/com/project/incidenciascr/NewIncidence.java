package com.project.incidenciascr;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.widget.ImageView;
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

    private Button button;
    Integer REQUEST_CAMERA=1, SELECT_FILE=0;
    ImageView imagePreview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_incidence);

        imagePreview = (ImageView) findViewById(R.id.img_preview);
        button = (Button) findViewById(R.id.btn_agregar_img);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }

        });
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
}
