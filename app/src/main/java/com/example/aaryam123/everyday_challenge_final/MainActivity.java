package com.example.aaryam123.everyday_challenge_final;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.aaryam123.everyday_challenge_final.db.ChallengesDataSource;

public class MainActivity extends AppCompatActivity {
    protected ChallengesDataSource mDataSource;

    private EditText nameField;
    private Button saveNameButton;
    private String nameOfUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //mDataSource = new ChallengesDataSource(MainActivity.this);
        //mDataSource.open(); // open database and connection

        saveNameButton = findViewById(R.id.saveName);
        nameField = findViewById(R.id.userName);
        //nameOfUser = "No Name";

        View.OnClickListener onClickListener;
        // if not default
        if (loadName() != "") {
            saveNameButton.setText("Skip to main Menu");
            //nameField.setText(loadName());
            onClickListener = new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //nameOfUser = nameField.getText().toString();
                    // saveName(nameField.getText().toString());
                    Intent i = new Intent(MainActivity.this, Ultimate_Activity.class);
                    startActivity(i);
                }
            };
        } else {
            // send intent to Ultimate Activity with nameField of user
            onClickListener = new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //nameOfUser = nameField.getText().toString();
                    saveName(nameField.getText().toString());
                    Intent i = new Intent(MainActivity.this, Ultimate_Activity.class);
                    startActivity(i);
                }
            };
        }
        saveNameButton.setOnClickListener(onClickListener);
    }

    // save user nameField
    public void saveName(String nameUser) {
        SharedPreferences sharedPreferences = getSharedPreferences("userInfo", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("username", nameUser);
        editor.apply();

        Toast.makeText(this, "Name has been saved!", Toast.LENGTH_LONG).show();
    }

    // set nameField default
    public String loadName() {
        SharedPreferences sharedPreferences = getSharedPreferences("userInfo", Context.MODE_PRIVATE);

        String name = sharedPreferences.getString("username","");

        return name;
    }


    /*@Override
    protected void onResume() {
        super.onResume();
        mDataSource.open();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mDataSource.close();
    }*/
}
