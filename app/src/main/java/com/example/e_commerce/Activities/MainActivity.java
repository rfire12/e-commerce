package com.example.e_commerce.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;

import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.e_commerce.Fragments.CategoryListFragment;
import com.example.e_commerce.Fragments.MainFragment;
import com.example.e_commerce.Fragments.ProductListFragment;
import com.example.e_commerce.Fragments.ProfileFragment;
import com.example.e_commerce.R;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar toolbar;
    private DrawerLayout drawer;
    private NavigationView navigationView;

    private FragmentManager fragmentManager;
    ImageView imgUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgUser = findViewById(R.id.img_user);

        loadDrawer();
        loadMainFragment();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar, menu);
        return true;
    }

    // It can also be called to close the drawer
    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    public void loadDrawer() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.nav);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.nav_open, R.string.nav_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
    }

    public void loadMainFragment() {
        fragmentManager = getSupportFragmentManager();
        fragmentManager
                .beginTransaction()
                .add(R.id.frame_container, new MainFragment())
                .commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        fragmentManager = getSupportFragmentManager();
        Fragment newFragment = new MainFragment(); // Default to home fragment

        switch (item.getItemId()) {
            case R.id.category_drawer:
                newFragment = new CategoryListFragment();
                break;
            case R.id.product_drawer:
                newFragment = new ProductListFragment();
                break;
            case R.id.profile_drawer:
                newFragment = new ProfileFragment();
                break;
            case R.id.logout_drawer:
                System.out.println("Close session");
                break;
        }

        fragmentManager
                .beginTransaction()
                .replace(R.id.frame_container, newFragment)
                .commit();

        // Close drawer after click on a drawer item
        onBackPressed();

        return false;
    }
}
