package com.projetandroid.touradvisor;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.projetandroid.touradvisor.databinding.ActivityMapsBinding;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.projetandroid.touradvisor.beans.PointBean;

import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.InfoWindowAdapter {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;

    private TextView tvPopup;

    //data : liste des points (attention, bien garder le 'final')
    private final ArrayList<PointBean> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        //Etape 1 : Est ce qu'on a déjà la permission ?
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
        //Etape 2 : On affiche la fenêtre de demande de permission
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 0);
        }

        //A supprimer : ceci est un test pour l'affichage d'un point sur la carte
        PointBean pointBean = new PointBean(1.23, 0.56);
        data.add(pointBean);
        PointBean pointBean1 = new PointBean(1.29, 1.56);
        data.add(pointBean1);

        //afficher le.s point.s sur la carte
        loadPoint();

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] gr) {
        super.onRequestPermissionsResult(requestCode, permissions, gr);
        refreshMap();
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        //On indique qu'on souhaite customiser les popups des markers
        //à utiliser avec getInfoWindow et getInfosContents
        mMap.setInfoWindowAdapter(this);

        refreshMap();
    }

    //Affichera la vue retournée, si null appelera getInfoContents
    @Override
    public View getInfoWindow(@NonNull Marker marker) {
        return null;
    }

    //Affichera la vue retournée à l'interieur de l'infowindow.
    @Override
    public View getInfoContents(@NonNull Marker marker) {
        //Layout de la fenêtre
        //ajout d'un fichier marker_city.xml pour gérer la popup
        View view = LayoutInflater.from(this).inflate(R.layout.marker_city, null);
        tvPopup = (TextView) view.findViewById(R.id.tvPopup);
        PointBean maPosition = (PointBean) marker.getTag();
        tvPopup.setText(maPosition.toString());
        return view;
    }




    private Location getLastKnownLocation() {
        //Contrôle de la permission
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) ==
                PackageManager.PERMISSION_DENIED) {
            return null;
        }
        LocationManager lm = (LocationManager) getSystemService(LOCATION_SERVICE);
        Location bestLocation = null;
        //on teste chaque provider(réseau, GPS...) et on garde la localisation avec la meilleurs précision
        for (String provider : lm.getProviders(true)) {
            Location l = lm.getLastKnownLocation(provider);
            if (l != null && (bestLocation == null || l.getAccuracy() < bestLocation.getAccuracy())) {
                bestLocation = l;
            }
        }
        return bestLocation;
    }

    private void refreshMap(){
        if(mMap == null) {
            return;
        }

        //Dans runOnUiThread ??
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
        }

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mMap.clear();

                LatLngBounds.Builder latLngBounds = new LatLngBounds.Builder();
                //Parcours de la liste data qui contient les points
                for (int i = 0; i < data.size(); i++) {
                    LatLng nvxPoint = new LatLng(data.get(i).getLat_point(), data.get(i).getLon_point());
                    //Ajout du 'marker' sur la carte + popup
                    mMap.addMarker(new MarkerOptions().position(nvxPoint).title("Vous êtes ici : " + i)).setTag(data.get(i));
                    latLngBounds.include(nvxPoint);
                }
                //Zoom sur les points de la carte (p.224 cours Android)
                mMap.animateCamera(CameraUpdateFactory.newLatLngBounds(latLngBounds.build(), 100));
            }
        });
    }

    //Afficher un point sur la carte
    private void loadPoint(){

        new Thread(() -> {
                try {
                    //Appeler WSUtils pour afficher les points sur la carte

                    //Mettre à jour l'IHM
                    refreshMap();
                } catch (Exception e) {
                    //Affiche le detail de l'erreur dans la console
                    e.printStackTrace();
                }
        }).start();

    }

}