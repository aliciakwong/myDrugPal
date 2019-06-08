package com.example.mydrugpal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void goToHeisenberg(View view) {
        Intent intent = new Intent(this, Heisenberg.class);
        startActivity(intent);
    }

    public void goToWhiskey(View view) {
        Intent intent = new Intent(this, Whiskey.class);
        startActivity(intent);
    }

    public void goToCannabis(View view) {
        Intent intent = new Intent(this, Cannabis.class);
        startActivity(intent);
    }

    public void goToAcid(View view) {
        Intent intent = new Intent(this, Acid.class);
        startActivity(intent);
    }

    public void goToCocaCola(View view) {
        Intent intent = new Intent(this, CocaCola.class);
        startActivity(intent);
    }
}
