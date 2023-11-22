package com.example.wishwallet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class Sign_Up extends AppCompatActivity {

    String uname;
    String email;
    String password;
    String cpassword;
    String name;

    ArrayList<String> usernames = new ArrayList<>();

    UserModel userData = new UserModel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        Button btn = findViewById(R.id.btn);
        Button button = findViewById(R.id.button);
        EditText unametb = findViewById(R.id.unametb);
        EditText emailtb = findViewById(R.id.emailtb);
        EditText passtb = findViewById(R.id.passwordtb);
        EditText cpasstb = findViewById(R.id.confirm_passwordtb);
        EditText nametb = findViewById(R.id.nametb);

        UserDB db = new UserDB(this);






        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Sign_Up.this, Sign_In.class);
                startActivity(intent);
                finish();
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                uname = unametb.getText().toString();
                email = emailtb.getText().toString();
                password = passtb.getText().toString();
                cpassword = cpasstb.getText().toString();
                name = nametb.getText().toString();


                userData.username = uname;
                userData.email = email;
                userData.password = password;
                userData.c_password = cpassword;
                userData.name = name;


                usernames = db.getUsername();



                Intent intent = new Intent(Sign_Up.this, Sign_In.class);



                if(uname.isEmpty() || email.isEmpty() || password.toString().isEmpty() || cpassword.toString().isEmpty())
                {
                    Toast.makeText(Sign_Up.this, "Fill All Fields", Toast.LENGTH_SHORT).show();
                }
                else if (!(password.toString().equals(cpassword.toString())))
                {
                    Toast.makeText(Sign_Up.this, "Passwords Don't Match", Toast.LENGTH_SHORT).show();

                }
                else{

                    if(usernames.contains(uname))
                    {
                        Toast.makeText(Sign_Up.this, "Username Already Exists", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(Sign_Up.this, "Sign Up Successful", Toast.LENGTH_SHORT).show();
                        db.addUser(userData);
                        startActivity(intent);
                        finish();
                    }
                }
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