package com.datj.mobile.ui.main;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.datj.mobile.R;
import com.datj.mobile.ui.fragment.HomeFragment;
import com.datj.mobile.ui.fragment.ProfileFragment;
import com.datj.mobile.util.CartManager;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.FirebaseApp;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onPause() {
        super.onPause();
        int currentCartCount = CartManager.getCartCount(this);
      //  updateCartBadge(this, currentCartCount); // Cập nhật badge khi ứng dụng vào background
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main_layout);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            Fragment selectedFragment = null;
            int itemId = item.getItemId();

            if (itemId == R.id.nav_home) {
                selectedFragment = new HomeFragment();
            } else if (itemId == R.id.nav_shop) {
              //  selectedFragment = new ShopFragment();
            } else if (itemId == R.id.nav_blog) {
               // selectedFragment = new BlogFragment();
            } else if (itemId == R.id.nav_notifications) {
               // selectedFragment = new NotificationFragment();
            } else if (itemId == R.id.nav_profile) {
                selectedFragment = new ProfileFragment();
            }

            if (selectedFragment != null) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container_view, selectedFragment)
                        .commit();
            }
            return true;
        });
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container_view, new HomeFragment())
                    .commit();
        }
        FirebaseApp.initializeApp(this);

    }
}