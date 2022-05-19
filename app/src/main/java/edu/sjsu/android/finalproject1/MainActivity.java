package edu.sjsu.android.finalproject1;


import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.Navigation;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_option, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.info) {
            Navigation.findNavController(findViewById(R.id.fragmentContainerView)).navigate(R.id.infoFragment2);
        } else if (item.getItemId() == R.id.uninstall) {
            Intent delete = new Intent(Intent.ACTION_DELETE, Uri.parse("package:" + getPackageName()));
            startActivity(delete);
        }
        return true;
    }
}