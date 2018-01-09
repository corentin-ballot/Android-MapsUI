package ballot.corentin.mapsui;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnInfoWindowClickListener, GoogleMap.OnMarkerClickListener {

    private GoogleMap mMap;
    private final int POSITION = 3; // nombre de position
    private LatLng randomPositions[];

    TextView markerinfo;
    LinearLayout infolayout;
    private Marker selectedmarker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        markerinfo = findViewById(R.id.markerinfo);
        infolayout = findViewById(R.id.infolayout);
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
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

        // Add a marker in Sydney and move the camera
        /*LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        if(randomPositions == null) {
            randomPositions = new LatLng[POSITION];

            Random r = new Random();
            for (int i = 0; i < POSITION; i++) {
                double randomLat = r.nextDouble() * 170 - 85;
                double randomLng = r.nextDouble() * 360 - 180;
                randomPositions[i] = new LatLng(randomLat, randomLng);
            }
        }

        for (int i = 0; i < randomPositions.length; i++) {
            Marker m = mMap.addMarker(new MarkerOptions().position(randomPositions[i]).title("Random position " + (i+1)));
            m.setTag(R.drawable.paris);
        }*/

        Marker paris = mMap.addMarker(new MarkerOptions().position(new LatLng(48.864716, 2.349014)).title("Paris").snippet("Capital of France"));
        paris.setTag(R.drawable.paris);

        Marker londres = mMap.addMarker(new MarkerOptions().position(new LatLng(51.50853, -0.12574)).title("Londres").snippet("Capital of United Kingdom"));
        londres.setTag(R.drawable.londres);

        Marker rome = mMap.addMarker(new MarkerOptions().position(new LatLng(41.9, 12.4833333)).title("Rome").snippet("Capital of Italy"));
        rome.setTag(R.drawable.rome);

        mMap.setInfoWindowAdapter(new PhotoInfoWindowAdapter(this));
        mMap.setOnMarkerClickListener(this);
        mMap.setOnInfoWindowClickListener(this);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelableArray("positions", randomPositions);

        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        randomPositions = (LatLng[]) savedInstanceState.getParcelableArray("positions");
    }

    public void toggle(View view) {
        if(selectedmarker != null)
            selectedmarker.hideInfoWindow();
        markerinfo.setText("");
        infolayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0));
    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        markerinfo.setText(marker.getTitle() + " is the " + marker.getSnippet());
        infolayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        selectedmarker = marker;
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        markerinfo.setText(marker.getTitle() + " is the " + marker.getSnippet());
        selectedmarker = marker;
        infolayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        return false;
    }
}
