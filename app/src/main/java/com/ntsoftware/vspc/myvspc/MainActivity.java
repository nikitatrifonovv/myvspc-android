package com.ntsoftware.vspc.myvspc;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;

    private final Context context = this;

    @BindView(R.id.nav_view)
    NavigationView navigationView;

    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.Theme_MyVSPC_NoActionBar);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        setupNavigation();




    }

    private void setupNavigation() {
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_schedule)
                .setOpenableLayout(drawer)
                .build();

        navController = Navigation.findNavController(this, R.id.nav_host_fragment);

        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);

        navigationView.setNavigationItemSelectedListener(itemSelectedListener);

        navigationView.setCheckedItem(R.id.nav_home);
    }

    private void openWebSite(String url){
        try {
            new CustomTabsIntent.Builder().build().launchUrl(this, Uri.parse(url));
        } catch (Exception e){
            e.printStackTrace();
            Toast.makeText(this,getString(R.string.nav_open_site_error_toast) ,Toast.LENGTH_SHORT).show();
        }
    }


    NavigationView.OnNavigationItemSelectedListener itemSelectedListener = new NavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.nav_home:
                    navigationView.setCheckedItem(item);
                    navController.navigate(R.id.nav_home);
                    drawer.close();
                    break;
                case R.id.nav_schedule:
                    navigationView.setCheckedItem(item);
                    navController.navigate(R.id.nav_schedule);
                    drawer.close();
                    break;
                case R.id.nav_settings:
                    Toast.makeText(context, getString(R.string.nav_in_dev), Toast.LENGTH_SHORT).show();
                    break;
                case R.id.nav_web_home:
                    openWebSite("https://www.vspc34.ru/");
                    break;
                case R.id.nav_web_enrollee:
                    openWebSite("https://www.vspc34.ru/index.php?option=com_content&view=category&id=9&Itemid=22");
                    break;
                case R.id.nav_web_student:
                    openWebSite("https://www.vspc34.ru/index.php?option=com_content&view=category&id=50&Itemid=42");
                    break;
                case R.id.nav_web_about:
                    openWebSite("https://www.vspc34.ru/index.php?option=com_content&view=category&id=104&Itemid=152");
                    break;
                default:
                    break;
            }
            return false;
        }
    };

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }


}