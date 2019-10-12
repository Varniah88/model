package com.example.model;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Home extends AppCompatActivity {
    EditText mname, mpswrd;
    Button mlogin, mreg;

    public static final String USERID = "userID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mname=(EditText)findViewById(R.id.name);
        mpswrd=(EditText)findViewById(R.id.pswrd);
        mlogin=(Button)findViewById(R.id.login);
        mreg=(Button)findViewById(R.id.reg);

        final DBHandler dbHandler = new DBHandler(Home.this);

        mreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this,ProfileManagement.class);
                startActivity(intent);
            }
        });

        mlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username = mname.getText().toString().trim();
                String password = mpswrd.getText().toString().trim();

                if (username == null){
                    Toast.makeText(Home.this,"Login unsuccessful",Toast.LENGTH_SHORT).show();
                }

                else {
                    UserProfile.Users users = dbHandler.readallinfo(username);
                    if(users == null){
                        Toast.makeText(Home.this,"invalid user name or password",Toast.LENGTH_SHORT).show();
                    }
                    else {
                        int userId = users.getId();
                        Intent intent = new Intent(Home.this,EditProfile.class);
                        intent.putExtra(USERID,Integer.toString(userId));
                        startActivity(intent);
                    }
                }

            }
        });
    }
}
