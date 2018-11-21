//Team Name : Wild Rangers
package eco.find;

import android.app.FragmentManager;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;

import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;


public class Homepage extends AppCompatActivity  {
    public ImageButton an1,an2,an3;
    Spinner spinner1;

    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener( new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();
                FragmentManager fragmentManager = getFragmentManager();

                if (id == R.id.map) {
                    Intent intent = new Intent(Homepage.this,MapActivity.class);
                    startActivity(intent);
                } else if (id == R.id.status) {
                    Intent intent = new Intent(Homepage.this,StatusActivity.class);
                    startActivity(intent);
                } else if (id == R.id.reports) {
                    Intent intent = new Intent(Homepage.this,ReportActivity.class);
                    startActivity(intent);

                } else if (id == R.id.contactus) {
                    Intent intent = new Intent(Homepage.this,Contact.class);
                    startActivity(intent);

                } else if (id == R.id.logout) {
                    finish();
                    Intent intent = new Intent(Homepage.this,Login.class);
                    startActivity(intent);

                }

                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);
                return true;
            }
        });

        an1=(ImageButton) findViewById(R.id.animal1);
        an1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Homepage.this,StatusActivity.class);
                intent.putExtra("message","data2.txt");
                startActivity(intent);
            }
        });

        an2=(ImageButton) findViewById(R.id.animal2);
        an2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Homepage.this,StatusActivity.class);
                intent.putExtra("message","data3.txt");
                startActivity(intent);
            }
        });

        an3=(ImageButton) findViewById(R.id.animal3);
        an3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Homepage.this,StatusActivity.class);
                intent.putExtra("message","data1.txt");
                startActivity(intent);
            }
        });

    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

}