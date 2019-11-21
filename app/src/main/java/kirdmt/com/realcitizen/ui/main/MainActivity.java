package kirdmt.com.realcitizen.ui.main;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import android.view.View;

import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;


import kirdmt.com.realcitizen.R;


//TODO: ! Выложить в Git. Основное приложение для портфолио.
//TODO: 0. Design (нет придела совершенству). Улучшить внешний вид AlertDialog.
//TODO: 1. realise MVP. (В общем готово, но можно еще попробовать усовершенствовать).
//TODO: 2. Заполнять youtube chanel and vk group.
//TODO: 3. Убрать фокус с элемента выбранного в drawerLayout.
//TODO: 4. Отделить статьи друг от друга. Сделать список со статьями в списке с главами.
//TODO: 5. Поиск по словам везде.
//TODO: 6. Алгоритм поведения с полицейскими в разных ситуациях. (Другой подобный уникальный контент).
//TODO: 7. Реализовать работу в ландшафтном режиме. Убедиться, что нет проблем с переворотом экрана, уничтожением и остановкой View.
//TODO: 8. Очистить проект от мусора(цвета и стринги).
//TODO: 9. Разбор аргументов НОДа.
//TODO: 10. Добавить возможность приближать текст(контент).

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, MainActivityContract.View {

    Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        presenter = new Presenter(this);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this, R.style.YDialog);
                builder.setTitle(getResources().getString(R.string.communicate_with_us))
                        .setMessage(getResources().getString(R.string.glad_will_see_mail))
                        // .setIcon(R.drawable.ic_android_cat)
                        .setCancelable(true)
                        .setNegativeButton(getResources().getString(R.string.write_mail),
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        //dialog.cancel();
                                        presenter.sendMail();
                                    }
                                });
                AlertDialog alert = builder.create();
                alert.show();

            }
        });

        //Attach the SectionsPagerAdapter to the ViewPager
        SectionsPagerAdapter pagerAdapter =
                new SectionsPagerAdapter(getSupportFragmentManager(), this);
        ViewPager pager = (ViewPager) findViewById(R.id.pager);
        pager.setOffscreenPageLimit(2);
        //pager.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        //pager.setBackgroundColor(Color.BLACK);
        pager.setAdapter(pagerAdapter);

        //Attach the ViewPager to the TabLayout
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(pager);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_about) {

            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this, R.style.YDialog);
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

    @Override
    public void callMailIntent() {

        Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
        emailIntent.setData(Uri.parse(getResources().getString(R.string.add_mail)));
        startActivity(emailIntent);

    }


    @Override
    public Context getContext() {
        return null;
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
        if (isFinishing()) {
            presenter.destroy();
        }
    }
}
