package com.project.incidenciascr;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;

public class UpdateIncidence extends AppCompatActivity {

    public ListView list;
    public ImageView  image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_incidence);

         list = (ListView)findViewById(R.id.list_incidentes);

         CustomAdapter customAdapter = new CustomAdapter();
         list.setAdapter(customAdapter);

    }
}
