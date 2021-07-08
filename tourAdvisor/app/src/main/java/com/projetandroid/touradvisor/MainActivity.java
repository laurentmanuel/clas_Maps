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

    private TextView textView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);


        callTest();

    }

    public void callTest(){
        new Thread(() -> {
            try {
                String resultat = WSUtils.test();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        textView.setText(resultat);
                    }
                });

            } catch (Exception e) {
                e.printStackTrace();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        textView.setText(e.getMessage());
                    }
                });
            }
        }).start();
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