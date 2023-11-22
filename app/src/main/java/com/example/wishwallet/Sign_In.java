package com.example.wishwallet;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Sign_In extends AppCompatActivity {

    ArrayList<UserModel> userData = new ArrayList<>();
    boolean flag;

    String uname_input;
    String password_input;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        Button btn = findViewById(R.id.button);
        Button switch_btn = findViewById(R.id.switch_btn);
        EditText unametb = findViewById(R.id.unametb);
        EditText passwordtb = findViewById(R.id.passwordtb);





        UserDB db = new UserDB(this);

       userData = db.getUser();



        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                uname_input = unametb.getText().toString();
                password_input = passwordtb.getText().toString();

                for (int i=0; i<(userData.size()); i++)

                {  if((uname_input.equals(userData.get(i).username)) && (password_input.equals(userData.get(i).password)))
                    {
                        Intent intent = new Intent(Sign_In.this, MainActivity.class);
                        intent.putExtra("username", userData.get(i).username);
                        startActivity(intent);
                        finish();
                    }

                    else {
                     //   Toast.makeText(Sign_In.this, "Invalid Username or Password", Toast.LENGTH_LONG).show();
                    }
                }



            }
        });


        switch_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Sign_In.this, Sign_Up.class);
                startActivity(intent);
                finish();
            }
        });


        statusbarColour();
    }

    private void statusbarColour(){

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        {
            getWindow().setStatusBarColor(getResources().getColor(R.color.main_theme, this.getTheme()));
        }
    }
}