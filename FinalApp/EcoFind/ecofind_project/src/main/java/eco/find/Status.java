//Team Name : Wild Rangers
package eco.find;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Message;
import android.preference.PreferenceManager;
import android.renderscript.Sampler;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

public class Status extends AppCompatActivity {

    private String value, animalId, temperature, readlocation, readlight;
    public String value_pass,animalId_pass, temperature_pass, readlocation_pass, readlight_pass;
    private TextView id;
    private TextView animalview;
    private TextView light;
    private TextView temp;
    private TextView location;





    DatabaseReference  mRootRef = FirebaseDatabase.getInstance().getReference().child("Animals");
    DatabaseReference AnimalNameRef = mRootRef.child("Name");
    DatabaseReference AnimalIdRef = mRootRef.child("Id");
    DatabaseReference AnimalLiRef = mRootRef.child("Light");
    DatabaseReference AnimalTempRef = mRootRef.child("Temperature");
    DatabaseReference AnimallocRef = mRootRef.child("Location");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);
        id = findViewById(R.id.Id);
        animalview = findViewById(R.id.aName);
        light = findViewById(R.id.lightValue);
        temp = findViewById(R.id.TemperatureValue);
        location = findViewById(R.id.LocationValue);





        final String[] animals = {"Hawk", "Lion", "Wolf"};
        Spinner spinner = (Spinner) findViewById(R.id.spinner1);
        Button getData = (Button) findViewById(R.id.button3);

        ArrayAdapter<String> adp2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, animals);


        adp2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adp2);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {

                value = animals[position].toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });


        getData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                assignID();
                postData();

            }
        });



    }


    public void assignID() {

        switch (value) {

            case "Lion":
                animalId = "L6564";
                 break;
            case "Wolf":
                animalId = "W4343";
                break;
            case "Hawk":
                animalId = "H4342";
                break;
            default:
                animalId = "XX";
        }

        Random rand = new Random();
        int t,lt;
        double l1,l2;

        t = rand.nextInt(35-25)+25;
        lt = rand.nextInt(10000-252)+255;
        l1 = 45 + (75-45) * rand.nextDouble();
        l2 = 25 + (75-25) * rand.nextDouble();

        temperature = t+" C";
        readlight= lt+" Lux";
        readlocation = l1+"\n -"+l2;



    }


    private void postData() {

        AnimalNameRef.setValue(value);
        AnimalIdRef.setValue(animalId);
        AnimalLiRef.setValue(readlight) ;
        AnimalTempRef.setValue(temperature) ;
        AnimallocRef.setValue(readlocation) ;



    }

    @Override
    protected void onStart(){
        super.onStart();



        AnimalIdRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String text = dataSnapshot.getValue(String.class);
                id.setText(text);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        AnimalNameRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String text = dataSnapshot.getValue(String.class);
                animalview.setText(text);



            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        AnimalLiRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String text = dataSnapshot.getValue(String.class);
                light.setText(text);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        AnimalTempRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String text = dataSnapshot.getValue(String.class);
                temp.setText(text);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        AnimallocRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String text = dataSnapshot.getValue(String.class);
                location.setText(text);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }



}




