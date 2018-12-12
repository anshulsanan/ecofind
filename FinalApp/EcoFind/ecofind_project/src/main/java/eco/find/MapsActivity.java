//Team Name : Wild Rangers
package eco.find;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private GoogleMap mMap;

    private static final LatLng Hamiltion = new LatLng(43.200146, -79.819455);
    private static final LatLng toronto = new LatLng(43.719840, -79.359198);
    private static final LatLng california = new LatLng(38.612641, -122.429224);

    private Marker mHamiltion;
    private Marker mtoronto;
    private Marker mcalifornia;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(MapsActivity.this);
        /* flag to indicate google maps is loaded */

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

        List<Marker> markerList = new ArrayList<>();

        mHamiltion = mMap.addMarker(new MarkerOptions()
                .position(Hamiltion)
                .title("Wolf").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)));
        mHamiltion.setTag(0);
        markerList.add(mHamiltion);


        mtoronto = mMap.addMarker(new MarkerOptions()
                .position(toronto)
                .title("Lion").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
        mtoronto.setTag(0);
        markerList.add(mtoronto);

        mcalifornia = mMap.addMarker(new MarkerOptions()
                .position(california)
                .title("Hawk").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW)));
        mcalifornia.setTag(0);
        markerList.add(mcalifornia);

        mMap.setOnMarkerClickListener(this); // this will register the clicks


        for (Marker m : markerList){
            LatLng latLng = new LatLng(m.getPosition().latitude,m.getPosition().longitude);
            mMap.addMarker(new MarkerOptions().position(latLng));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng ,10));
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,2));



        }
    }


    // Add a marker in Sydney and move the camera
//        LatLng mississauga = new LatLng(43.636815, -79.618315);
//        mMap.addMarker(new MarkerOptions().position(mississauga).title("Marker on animal1").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(mississauga));
//    }
    @Override
    public boolean onMarkerClick(Marker marker) {

        Integer clickedCount = (Integer)marker.getTag();
        if(clickedCount != null){
            clickedCount = clickedCount + 1;

            marker.setTag(clickedCount);
            Toast.makeText(this,marker.getTitle() + "has been looked for" + clickedCount  + "times", Toast.LENGTH_LONG).show();
        }
        return false;
    }
}



