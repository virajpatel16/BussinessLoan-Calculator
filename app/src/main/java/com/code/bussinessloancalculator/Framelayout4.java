package com.code.bussinessloancalculator;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class Framelayout4 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_framelayout4);

        // Replace insets padding setup with EdgeToEdge library
        EdgeToEdge.enable(this);

        // Replace the fragment on activity creation
        replaceFragment(new Calculator_Fragment());
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame4, fragment);
        // Optional: Add transaction to back stack
        fragmentTransaction.commit();
    }
}
