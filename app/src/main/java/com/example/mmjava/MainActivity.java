package com.example.mmjava;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private TextView tabCurrentOrderCount;
    private TextView tabCompleteOrderCount;
    private SharedViewModel sharedViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedViewModel = new ViewModelProvider(this).get(SharedViewModel.class);
        tabLayout = findViewById(R.id.tabLayout);

        // Inflate a custom view for the "Current Order" tab with the dynamic count
        View currentOrderTabView = LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tabCurrentOrderCount = currentOrderTabView.findViewById(R.id.tabText);
        tabCurrentOrderCount.setText("Current Order");
        tabCurrentOrderCount = currentOrderTabView.findViewById(R.id.txt_total_qty);

        // Observe current order count
        sharedViewModel.getCurrentOrderCount().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer count) {
                tabCurrentOrderCount.setText(String.valueOf(count));  // Update count dynamically for current order tab
            }
        });

        View completetOrderTabView = LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tabCompleteOrderCount = completetOrderTabView.findViewById(R.id.tabText);
        tabCompleteOrderCount.setText("Complete Order");
        tabCompleteOrderCount = completetOrderTabView.findViewById(R.id.txt_total_qty);


        // Observe complete order count
        sharedViewModel.getCompleteOrderCount().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer count) {
                tabCompleteOrderCount.setText(String.valueOf(count));  // Update count dynamically for complete order tab
            }
        });

        // Add the custom tab with dynamic count
        tabLayout.getTabAt(0).setCustomView(currentOrderTabView);
        // Add the default tab for "Complete Order"
        tabLayout.getTabAt(1).setCustomView(completetOrderTabView);

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, new CurrentOrderFragment())
                    .commit();
        }


        // Listen for tab selection
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Fragment fragment = null;
                if (tab != null) {
                    switch (tab.getPosition()) {
                        case 0:
                            fragment = new CurrentOrderFragment();
                            break;
                        case 1:
                            fragment = new CompleteOrderFragment();
                            break;
                    }
                    FragmentManager fm = getSupportFragmentManager();
                    FragmentTransaction ft = fm.beginTransaction();
                    ft.replace(R.id.fragment_container, fragment);
                    ft.commit();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}
