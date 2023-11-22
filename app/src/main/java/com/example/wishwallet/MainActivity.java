package com.example.wishwallet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView navigationView;
    TextView all_transactions;

    public static String existing_username;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navigationView = findViewById(R.id.navigation_view);

        Intent get = getIntent();
        existing_username = get.getStringExtra("username");




       navigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
           @Override
           public boolean onNavigationItemSelected(@NonNull MenuItem item) {

               int id = item.getItemId();

               loadFragment(new HomeFragment(), 0);

               if(id == R.id.nav_home)
               {
                  loadFragment(new HomeFragment(), 1);
                //  navigationView.setItemIconTintList(new ColorStateList(new int[][]{new int[]{0}}, new int[]{R.color.main_theme}));
               }
               else if (id == R.id.nav_transactions) {
                    loadFragment(new TransactionFragment(), 1);
               }
               else if (id == R.id.nav_add) {
                    loadFragment(new AddFragment(), 1);
               }
               else if (id == R.id.nav_budget) {
                    loadFragment(new BudgetFragment(), 1);
               }
               else if (id == R.id.nav_wishlist) {
                    loadFragment(new WishlistFragment(), 1);
               }

               return true;
           }
       });

       navigationView.setSelectedItemId(R.id.nav_home);



        statusbarColour();
    }
    private void statusbarColour(){

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        {
            getWindow().setStatusBarColor(getResources().getColor(R.color.main_theme, this.getTheme()));
        }
    }

    public void loadFragment(Fragment fragment, int flag)
    {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        if(flag == 0)
        {
            transaction.add(R.id.main_container, fragment);
        }

        else if (flag == 1){
            transaction.replace(R.id.main_container, fragment);
        }

        transaction.commit();

    }
}