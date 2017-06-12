package com.example.rominakehl.bildung_4_0;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.estimote.sdk.Beacon;
import com.estimote.sdk.BeaconManager;
import com.estimote.sdk.Region;
import com.estimote.sdk.SystemRequirementsChecker;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {
    private BeaconManager beaconManager;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    private static final Map<String, String> PLACES_BY_BEACONS;
    private Region region;
    //widgets
    TextView tView = null;

    private String placesNearBeacon(Beacon beacon) {
        String beaconKey = String.format("%d:%d", beacon.getMajor(), beacon.getMinor());
        if (PLACES_BY_BEACONS.containsKey(beaconKey)) {
            return PLACES_BY_BEACONS.get(beaconKey);
        }
        return null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        final Activity activity=this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Generierung der Widgets
        beaconManager = new BeaconManager(this);
        beaconManager.setRangingListener(new BeaconManager.RangingListener() {
            String actualActivity = null;
            @Override
            public void onBeaconsDiscovered(Region region, List<Beacon> list) {
                if (!list.isEmpty()) {
                    for(Iterator<Beacon> i = list.iterator(); i.hasNext();) {
                        Beacon nearestBeacon = i.next();
                        if (placesNearBeacon(nearestBeacon) != null) {
                            String places = placesNearBeacon(nearestBeacon);
                            Log.d("Tour", "Nearest places: " + places);
                            //tView.setText(getString(R.string.textSchueler1));
                            if(places.equals("Beacon1")) {
                                if(actualActivity != "firstStop") {
                                    Intent intent1 = new Intent(activity, Einstieg_1.class);
                                    actualActivity = "firstStop";
                                    Log.i("Test", "Hallo1");
                                    startActivity(intent1);
                                }
                                break;
                            }
                            if(places.equals("Beacon2")) {
                                if(actualActivity != "secondStop") {
                                    Intent intent2 = new Intent(activity, Einstieg_2.class);
                                    Log.i("Test", "Hallo2");
                                    actualActivity = "secondStop";
                                    startActivity(intent2);
                                }
                                break;

                            }
                            if(places.equals("Beacon3")) {
                                if(actualActivity != "thirdStop") {
                                    Intent intent3 = new Intent(activity, WiButler.class);
                                    Log.i("Test", "Hallo3");
                                    actualActivity = "thirdStop";
                                    startActivity(intent3);
                                }
                                break;
                            }

                            if(places.equals("Beacon5")) {
                                if(actualActivity != "forthStop") {
                                    Intent intent4 = new Intent(activity, HomeActivity.class);
                                    Log.i("Test", "Hallo4");
                                    actualActivity = "forthStop";
                                    startActivity(intent4);
                                }
                                break;
                            }
                        }
                    }
                }


            }
        });
        region = new Region("ranged region",
                UUID.fromString("B9407F30-F5F8-466E-AFF9-25556B57FE6D"), null, null);
    }

    @Override
    protected void onResume() {
        super.onResume();

        SystemRequirementsChecker.checkWithDefaultDialogs(this);
        beaconManager.connect(new BeaconManager.ServiceReadyCallback() {
            @Override
            public void onServiceReady() {
                beaconManager.startRanging(region);
            }
        });
    }

    @Override
    protected void onPause() {
        //beaconManager.stopRanging(region);

        super.onPause();
    }

    static {
        Map<String, String> placesByBeacons = new HashMap<>();
        placesByBeacons.put("6389:16546", "Beacon1");
        placesByBeacons.put("25165:33721", "Beacon2");
        placesByBeacons.put("16049:43534", "Beacon3");
        placesByBeacons.put("9015:62082", "Beacon4");
        placesByBeacons.put("14943:36132", "Beacon5");
        placesByBeacons.put("22027:53129", "Beacon6");
        PLACES_BY_BEACONS = Collections.unmodifiableMap(placesByBeacons);
    }

}



