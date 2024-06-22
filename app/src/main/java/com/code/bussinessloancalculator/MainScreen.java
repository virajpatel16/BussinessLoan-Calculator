package com.code.bussinessloancalculator;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainScreen extends AppCompatActivity {

    LinearLayout card1;
    LinearLayout card2;
    LinearLayout card3;
    LinearLayout card4;
    LinearLayout card5;

    LinearLayout card6;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_screen);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        card1 = findViewById(R.id.card1);
        card2 = findViewById(R.id.card2);
        card3 = findViewById(R.id.card3);
        card4 = findViewById(R.id.card4);
        card5 = findViewById(R.id.card5);
        card6 = findViewById(R.id.card6);

        ImageView imageButton = findViewById(R.id.menu);


        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupMenu(v);
            }
        });

        card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainScreen.this, FragmentActivity.class);
                startActivity(intent);

            }
        });

        card2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainScreen.this, Framlayout1.class);
                startActivity(intent);


            }
        });
        card3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainScreen.this, Framlayout2.class);
                startActivity(intent);

            }
        });
        card4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainScreen.this, Framelayout3.class);
                startActivity(intent);

            }
        });
        card5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainScreen.this, Framelayout4.class);
                startActivity(intent);

            }
        });
        card6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainScreen.this, Framelayout5.class);
                startActivity(intent);

            }
        });

    }



    @SuppressLint("MissingSuperCall")
    @Override
    public void onBackPressed() {

        final AlertDialog.Builder alert = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.exitdialog, null);
        alert.setView(view);
        final AlertDialog dialog = alert.create();
        dialog.setCancelable(false);
        view.findViewById(R.id.btnyes).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                finishAffinity();
                // finishAndRemoveTask();
            }
        });
        view.findViewById(R.id.btnno).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

    private void showPopupMenu(View view) {
        PopupMenu popupMenu = new PopupMenu(this, view);
        popupMenu.getMenuInflater().inflate(R.menu.popupmenu, popupMenu.getMenu());

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {


            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                return handleMenuItemClick(menuItem);
            }
        });

        popupMenu.show();
    }

    @SuppressLint("NonConstantResourceId")
    private boolean handleMenuItemClick(MenuItem menuItem) {
        int itemId = menuItem.getItemId();
        if (itemId == R.id.menu_item1) {// Handle Item 1 click
            shareAppLink();
            return true;
        } else if (itemId == R.id.menu_item2) {// Handle Item 2 click
            openPlayStore();
            return true;
        } else if (itemId == R.id.menu_item3) {
            startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://www.freeprivacypolicy.com/live/cb1225b8-eb72-4959-83dc-e3e3e0e1a640")));

            return true;
        }
        return false;
    }

    private void openPlayStore() {
        // Replace "your_app_package_name" with your actual app package name

        // Replace "your_app_package_name" with your actual app package name
        String appPackageName = "com.code.bussinessloancalculator";

        try {
            // Create intent to open the Google Play Store
            Intent playStoreIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName));
            playStoreIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(playStoreIntent);
        } catch (android.content.ActivityNotFoundException e) {
            // If Google Play Store app is not installed, open Play Store website in browser
            Intent playStoreWebIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName));
            startActivity(playStoreWebIntent);
        }
    }


    private void shareAppLink() {
        // Replace "your_app_link" with your actual app link
        String appLink = "https://play.google.com/store/apps/details?id=your_app_package_name";

        // Create an intent to share the app link
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, appLink);

        // Start the activity to share the app link
        startActivity(Intent.createChooser(shareIntent, "Share App Link"));
    }
}