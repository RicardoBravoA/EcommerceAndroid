package com.rba.ecommerce.activity;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.rba.ecommerce.R;
import com.rba.ecommerce.fragment.BrandFragment;
import com.rba.ecommerce.fragment.CategoryFragment;
import com.rba.ecommerce.fragment.HomeFragment;
import com.rba.ecommerce.fragment.NearMeFragment;
import com.rba.ecommerce.fragment.SearchFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        Fragment fragment = null;
        String title = getString(R.string.app_name);

        switch (item.getItemId()){
            case R.id.nav_home:
                fragment = new HomeFragment();
                title = getString(R.string.app_name);
                break;
            case R.id.nav_search:
                fragment = new SearchFragment();
                title = getString(R.string.nav_search);
                break;
            case R.id.nav_brand:
                fragment = new BrandFragment();
                title = getString(R.string.nav_brand);
                break;
            case R.id.nav_category:
                fragment = new CategoryFragment();
                title = getString(R.string.nav_category);
                break;
            case R.id.nav_near_me:
                fragment = new NearMeFragment();
                title = getString(R.string.nav_near_me);
                break;
            default:
                break;
        }

        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.frmContainer, fragment);
            fragmentTransaction.commit();

            getSupportActionBar().setTitle(title);
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
