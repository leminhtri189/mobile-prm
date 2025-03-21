package com.datj.mobile.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.appcompat.widget.SearchView;
import com.datj.mobile.R;
import com.datj.mobile.ui.customview.ProductDetailActivity;
import com.datj.mobile.ui.fragment.HomeFragment;
import com.datj.mobile.ui.fragment.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);

        bottomNavigationView = findViewById(R.id.bottomNavigation);

        // Xử lý chọn item trong Bottom Navigation
        bottomNavigationView.setOnItemSelectedListener(item -> {
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
                        .replace(R.id.container, selectedFragment)
                        .commit();
            }
            return true;
        });

        // Mặc định hiển thị HomeFragment khi khởi chạy
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, new HomeFragment())
                    .commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setQueryHint("Search by Accessory ID...");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (!query.isEmpty() && query.matches("\\d+")) {
                    int accessoryId = Integer.parseInt(query);
                    Log.d("SEARCH", "Searching for accessoryId: " + accessoryId); // Thêm Log kiểm tra
                    Intent intent = new Intent(MainActivity.this, ProductDetailActivity.class);
                    intent.putExtra("ACCESSORY_ID", accessoryId);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "Invalid Accessory ID", Toast.LENGTH_SHORT).show();
                }
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                // Không làm gì cả, chỉ return false để tránh lỗi
                return false;
            }
        });


        return true;
    }
}
