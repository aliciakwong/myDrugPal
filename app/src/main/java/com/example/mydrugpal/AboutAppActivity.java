package com.example.mydrugpal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

/**
 * activity with info about the app and the dev team
 * @author Alicia Wong, Richard Purcell
 */

public class AboutAppActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_app);

        //TextView gitLabLink = (TextView) findViewById(R.id.gitLabLink);
        //gitLabLink.setMovementMethod(LinkMovementMethod.getInstance());
    }

}
