package com.projetandroid.touradvisor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import static com.projetandroid.touradvisor.WSUtils.test;

public class MainActivity extends AppCompatActivity {
    private final static int MENU_ID_MAPS = 1;
    private static final String URL_TEST = "http://192.168.10.85:8080/testPoint";
    private TextView textView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        textView.setText(test(URL_TEST));

    }


    // Ici on va créer chaque onglet du Menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, MENU_ID_MAPS, 0,"Maps");
        return super.onCreateOptionsMenu(menu);
    }

    // Ici on va rediriger l'user vers l'onglet souhaité
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == MENU_ID_MAPS) {
            Intent intent = new Intent(this, MapsActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

}