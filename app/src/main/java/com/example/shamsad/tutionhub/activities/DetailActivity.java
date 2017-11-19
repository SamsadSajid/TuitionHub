package com.example.shamsad.tutionhub.activities;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.shamsad.tutionhub.models.GeoPoint;
import com.example.shamsad.tutionhub.utilities.DrawerUtil;
import com.example.shamsad.tutionhub.models.InfiniteFeedInfo;
import com.example.shamsad.tutionhub.views.ItemView;
import com.example.shamsad.tutionhub.R;
import com.mindorks.placeholderview.InfinitePlaceHolderView;
import com.mindorks.placeholderview.PlaceHolderView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "position";

    private InfinitePlaceHolderView mLoadMoreView;

    private TextView submission_request;
    private TextView provider;
    private TextView num_of_students;
    private TextView institution;
    private TextView student_class;
    private TextView subjects;
    private TextView salary;
    private TextView days_per_week;
    private TextView duration;
    private TextView address;
    private TextView special_requirements;

    private ProgressDialog progressDialog;

    private Double longitude;
    private Double latitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Toolbar toolbar = findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //toolbar.setTitle(R.string.detail);
        // Set Collapsing Toolbar layout to the screen
        CollapsingToolbarLayout collapsingToolbar = findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle("Detail");

		// Adding Floating Action Button to bottom right of main view
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        submission_request = findViewById(R.id.submission_request);
        provider = findViewById(R.id.provider);
        num_of_students = findViewById(R.id.num_of_students);
        institution = findViewById(R.id.institution);
        student_class = findViewById(R.id.student_class);
        subjects = findViewById(R.id.subjects);
        salary = findViewById(R.id.salary);
        days_per_week = findViewById(R.id.days_per_week);
        duration = findViewById(R.id.duration);
        address = findViewById(R.id.address);
        special_requirements = findViewById(R.id.special_requirements);


        // Navigation Drawer
        //DrawerUtil.getDrawer(this,toolbar);

//        mLoadMoreView = (InfinitePlaceHolderView)findViewById(R.id.loadMoreView);
        setupView();

        final Intent intent = new Intent(this, MapsActivity.class);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog = new ProgressDialog(DetailActivity.this);

                progressDialog.setMessage("Please wait...");
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.show();

                String mapAddress;
                mapAddress = address.getText().toString();
                mapAddress = mapAddress.replace(" ","");
                mapAddress = mapAddress.replace("#", "");
                Log.d("mymapaddress", ""+mapAddress);
                System.out.println("mymapaddress "+mapAddress);
                try {
                    GeoPoint geoPoint;
                    geoPoint = getLatLongByURL(mapAddress);
                    latitude = geoPoint.getLatitude();
                    longitude = geoPoint.getLongitude();

                    System.out.println("latitude "+latitude+" longitude "+longitude);

                }catch (Exception e){
                    e.printStackTrace();
                }

                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }

                Bundle bundle = new Bundle();
                bundle.putDouble("_latitude", latitude);
                bundle.putDouble("_longitude", longitude);

                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

    }

    @SuppressLint("SetTextI18n")
    private void setupView(){
        submission_request.setText("3");
        provider.setText("Jane Doe");
        num_of_students.setText("1");
        institution.setText("XYZ College");
        student_class.setText("11");
        subjects.setText("Physics, Math");
        salary.setText("8000");
        days_per_week.setText("3 days per week");
        duration.setText("Negotiable");
        address.setText("Road # 11, Block # C, Banani, Dhaka-1213");
        special_requirements.setText("null");
    }

    public GeoPoint getLatLongByURL(String strAddress) {
        Geocoder coder = new Geocoder(this);
        List<Address> address;
        GeoPoint p1 = null;
        try {
            address = coder.getFromLocationName(strAddress,1);
            if (address==null) {
                return null;
            }
            Address location=address.get(0);
            latitude = location.getLatitude();
            longitude = location.getLongitude();

            p1 = new GeoPoint(latitude, longitude);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return p1;
    }
}
