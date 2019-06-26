package com.example.mydrugpal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

/**
 * Activity for logout button display on relevant activities
 * @author Emma Travers, Alicia Wong
 *
 */
public abstract class LogoutActivity extends AppCompatActivity {


    private static boolean loggedOut = false;
    /**
     * creates main display activity when called and add logout button listener and implementation
     * @param savedInstanceState current app instance
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logout);

        FloatingActionButton logoutButton = findViewById(R.id.button_logout);
        logoutButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){

                loggedOut = true;
                gotoLoginPage();
            }

        });

    }

    private void gotoLoginPage() {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

    /**
     * required method for abstract implementation of this class
     *
     * @return layoutResourceId
     */
    protected abstract int getLayoutResourceId();
}
