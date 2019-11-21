package kirdmt.com.realcitizen.ui.content;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.material.navigation.NavigationView;

import kirdmt.com.realcitizen.R;
import kirdmt.com.realcitizen.ui.main.MainActivity;


public class ContentActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    TextView chapterNameTextView, chapterContentTextView;
    String chapterName, chapterContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        chapterNameTextView = (TextView) findViewById(R.id.textView_name);
        chapterContentTextView = (TextView) findViewById(R.id.textView_content);

        setDataFromBundle();
        setBanner();

    }


    void setDataFromBundle() {

        Bundle extras = getIntent().getExtras();

        chapterName = extras.getString("chapter_name");
        chapterContent = extras.getString("chapter_content");

        chapterNameTextView.setText(chapterName);
        chapterContentTextView.setText(chapterContent);

    }

    private void setBanner() {
        AdView mAdView;
        mAdView = (AdView) findViewById(R.id.banner_ad_real);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_about) {

            AlertDialog.Builder builder = new AlertDialog.Builder(ContentActivity.this, R.style.YDialog);
            builder.setTitle(getResources().getString(R.string.about_application))
                    .setMessage(getResources().getString(R.string.application_description))
                    .setCancelable(true)
                    .setNegativeButton(getResources().getString(R.string.ok),
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });
            AlertDialog alert = builder.create();
            alert.show();


        } else if (id == R.id.nav_vk) {
            String url = getResources().getString(R.string.link_vk_group);
            Intent intent_vk = new Intent(Intent.ACTION_VIEW);
            intent_vk.setData(Uri.parse(url));
            startActivity(intent_vk);

        } else if (id == R.id.nav_youtube) {
            String url = getResources().getString(R.string.link_youtube_chanel);
            Intent intent_youtube = new Intent(Intent.ACTION_VIEW);
            intent_youtube.setData(Uri.parse(url));
            startActivity(intent_youtube);

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
