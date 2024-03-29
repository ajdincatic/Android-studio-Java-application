package ba.fit.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.core.app.ShareCompat;
import androidx.core.view.GravityCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.io.IOException;

import ba.fit.android.fragments.PosiljkaList;
import ba.fit.android.helper.MyFragmentUtils;
import ba.fit.android.helper.MySession;

public class GlavniActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glavni);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //FloatingActionButton fab = findViewById(R.id.fab);
        //fab.setOnClickListener(new View.OnClickListener() {
        //    @Override
        //    public void onClick(View view) {
        //        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
        //                .setAction("Action", null).show();
        //    }
        //});
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        // na glavni activity dodajemo fragmente, parametri activity na koji se dodaje, gdje se dodaje i šta dodajemo
        MyFragmentUtils.openAsReplace(this,R.id.placeToLoad, PosiljkaList.newInstance());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.glavni, menu);
        return true;
    }

    private void do_btnLogoutClick() {
        MySession.setKorisnik(null);
        startActivity(new Intent(this, LoginActivity.class));
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);

        findViewById(R.id.action_logout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                do_btnLogoutClick();
            }
        });

        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    //private void setupDrawerContent(NavigationView navigationView) {
    //    navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
    //        @Override
    //        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
    //            int id = item.getItemId();
//
    //            if (id == R.id.nav_home) {
    //                // Handle the home action
    //            } else if (id == R.id.nav_gallery) {
//
    //            } else if (id == R.id.nav_slideshow) {
//
    //            }
    //            else if (id == R.id.nav_logout) {
    //                MySession.setKorisnik(null);
    //                //startActivity(new Intent(this, LoginActivity.class));
    //            }
//
    //            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    //            drawer.closeDrawer(GravityCompat.START);
    //            return true;
    //        }
    //    });
    //}

}
