package com.example.mydrugpal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button validateButton = findViewById(R.id.button);
        validateButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                /*boolean validatePassword = Validator.validate(((EditText)findViewById(R.id.editText)).getText().toString());
                String passwordStrengthText;

                if(validatePassword)
                    passwordStrengthText = "Strong Enough Password";
                else
                    passwordStrengthText = "INVALID PASSWORD! (Not Strong Enough)";

                ((TextView)findViewById(R.id.textView)).setText(passwordStrengthText);*/
            }

        });

    }
}
