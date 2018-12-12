//Team Name : Wild Rangers
package eco.find;

import android.app.AlertDialog;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;


public class Homepage extends AppCompatActivity {
    public ImageButton an1, an2, an3;
    Spinner spinner1;
    TextView tv;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ChangeBackground();
        SetOrientation();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                SetAccountDetails();
                int id = menuItem.getItemId();
                FragmentManager fragmentManager = getFragmentManager();

                if (id == R.id.map) {
                    Intent intent = new Intent(Homepage.this, MapsActivity.class);
                    startActivity(intent);
                } else if (id == R.id.status) {
                    Intent intent = new Intent(Homepage.this, Status.class);
                    startActivity(intent);
                }  else if (id == R.id.contactus) {
                    Intent intent = new Intent(Homepage.this, Contact.class);
                    startActivity(intent);

                } else if (id == R.id.logout) {
                    finish();
                    Intent intent = new Intent(Homepage.this, Login.class);
                    startActivity(intent);

                }

                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);
                return true;
            }
        });



    }

    private void SetAccountDetails() {
        tv = (TextView) findViewById(R.id.user_info);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String username =getString(R.string.hello)+preferences.getString("username", "User");
        tv.setText(username);
    }


    public void SetOrientation(){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        boolean orientation = preferences.getBoolean("portrait", true);
        if(orientation)
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        else
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
    }


    public void ChangeBackground()
    {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String bck = preferences.getString("background", "day");
        RelativeLayout r1 = (RelativeLayout) findViewById(R.id.homepage);
        CoordinatorLayout cl = (CoordinatorLayout) findViewById(R.id.app_bar);
        if(bck.equals("day"))
        {
            r1.setBackgroundColor(Color.parseColor("#fffaf0"));
            cl.setBackgroundColor(Color.parseColor("#fffaf0"));
        }
        if(bck.equals("night"))
        {
            r1.setBackgroundColor(Color.parseColor("#808080"));
            cl.setBackgroundColor(Color.parseColor("#808080"));
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection

        switch (item.getItemId()) {
            case R.id.settings:

                Intent intent = new Intent(Homepage.this, Settings.class);
                startActivity(intent);
                return true;

            case R.id.help:
                String url = "https://humber.ca/";
                Intent ihelp = new Intent(Intent.ACTION_VIEW);
                ihelp.setData(Uri.parse(url));
                startActivity(ihelp);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    public void onBackPressed(){

        AlertDialog.Builder alretDialog = new AlertDialog.Builder(Homepage.this);

        alretDialog.setTitle(R.string.app_name);                    //Set title
        alretDialog.setMessage(R.string.exitmessage);                  //set message
        alretDialog.setIcon(R.drawable.warning);                       // ICon

        // Yes Button
        alretDialog.setPositiveButton(R.string.exityes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish(); //Exit app
            }
        });

        // No Button
        alretDialog.setNegativeButton(R.string.exitno, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel(); //cancel dialog back to app
            }
        });

        alretDialog.show(); //show dialog
    }
}