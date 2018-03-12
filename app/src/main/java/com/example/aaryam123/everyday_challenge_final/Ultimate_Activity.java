package com.example.aaryam123.everyday_challenge_final;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;



import java.util.Random;

public class Ultimate_Activity extends AppCompatActivity {
    public final static int LIMIT = 3;
    public static final String MESSAGES_FIREBASE_KEY = "messages";
    // Create challenge book objects
    private ChallengeBook challengeBook = new ChallengeBook();
    private Levels levels;

    // set Views and their variables
    private TextView helloText;
    private TextView usernameText;
    private ImageButton changeChallengeBtn;
    private TextView currChallengeText;
    private ImageButton finish;

    /*
    // Get a reference to your FirebaseDatabase
    FirebaseDatabase firebase = FirebaseDatabase.getInstance();

    // Get a reference to the "messages" node of FIrebase
    DatabaseReference messages = firebase.getReference(MESSAGES_FIREBASE_KEY);
    DatabaseReference messages1 = firebase.getReference("messages1");
    DatabaseReference messages2 = firebase.getReference("messages2");
    DatabaseReference messages3 = firebase.getReference("messages3");
    DatabaseReference messages4 = firebase.getReference("messages4");
    DatabaseReference messages5 = firebase.getReference("messages5");
    DatabaseReference messages6 = firebase.getReference("messages6");
    DatabaseReference messages7 = firebase.getReference("messages7");
    DatabaseReference messages8 = firebase.getReference("messages8");
    DatabaseReference messages9 = firebase.getReference("messages9");
    // DatabaseReference messages0 = firebase.getReference("messages0");
    DatabaseReference messages10 = firebase.getReference("messages10");
    DatabaseReference messages11 = firebase.getReference("messages11");
    DatabaseReference messages12 = firebase.getReference("messages12");
    DatabaseReference messages13 = firebase.getReference("messages13");

    //DatabaseReference[] challengeArray = {messages, messages1, messages2, messages3, messages4, messages5, messages6, messages7,
      //      messages8 ,messages9, messages10, messages11, messages12, messages13};
      */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ultimate_);
        levels = new Levels(loadLevel(), loadNumChallenges());

        //pushAllChallengesToFirebase();

        helloText = (TextView) findViewById(R.id.helloText);
        changeChallengeBtn = (ImageButton) findViewById(R.id.changeChallengeBtn);
        currChallengeText = (TextView) findViewById(R.id.currChallengeText);
        usernameText = (TextView) findViewById(R.id.usernameText);
        finish = findViewById(R.id.finishBtn);

        ImageButton settingsBtn = (ImageButton) findViewById(R.id.settingsBtn);
        ImageButton profileBtn = (ImageButton) findViewById(R.id.profileBtn);

        // Read username from Main Activity
        final String newString = loadName();
        /*
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                newString = null;
            } else {
                newString = extras.getString("USERNAME");
            }
        } else {
            newString = (String) savedInstanceState.getSerializable("USERNAME");
        }*/

        currChallengeText.setText(challengeBook.getRandomChallenge());


        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (challengeBook.getNumberChallenge() < LIMIT) {
                    currChallengeText.setText(challengeBook.getRandomChallenge());
                } else {
                    Toast.makeText(Ultimate_Activity.this,
                            "Sorry, you've reached your daily limit for changing challenges!",
                            Toast.LENGTH_LONG).show();
                }
            }
        };

        View.OnClickListener finishChallenge = new View.OnClickListener() {
            @Override
            public void onClick(View view)   {
                levels.doneChallenge();
                saveNumChallenges(levels.getNumChallenges());
                saveLevel(levels.getCurrLevel());

                Toast.makeText(Ultimate_Activity.this, "Challenge completed!", Toast.LENGTH_LONG).show();
            }
        };

        usernameText.setText(newString);
        changeChallengeBtn.setOnClickListener(onClickListener);
        finish.setOnClickListener(finishChallenge);

        ImageButton progressBtn = (ImageButton) findViewById(R.id.progressBtn);

        View.OnClickListener ocl = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Ultimate_Activity.this, Progress_Activity.class);
                startActivity(intent);
            }
        };
        // send intent to Ultimate Activity with name of user

        progressBtn.setOnClickListener(ocl);

        View.OnClickListener callProfile = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent profInt = new Intent(Ultimate_Activity.this, profile.class);
                profInt.putExtra("USERNAME", newString);
                startActivity(profInt);
            }
        };
        // send intent to Ultimate Activity with name of user

        profileBtn.setOnClickListener(callProfile);

    }

    // set nameField default
    public String loadName() {
        SharedPreferences sharedPreferences = getSharedPreferences("userInfo", Context.MODE_PRIVATE);

        String name = sharedPreferences.getString("username","");

        return name;
    }

    /* method to push all messages to firebase
    private void pushAllChallengesToFirebase() {
        // hard coding the messages to database
        messages.setValue("Eat less sugar today");
        messages1.setValue("Open doors for strangers and smile");
        messages2.setValue("Cut back TV and social media (Yes, including this phone)");
        messages3.setValue("Eat a bowl of salad");
        messages4.setValue("Only drink water for the day");
        messages5.setValue("Start reading a book");
        messages6.setValue("Choose to walk instead of driving");
        messages7.setValue("Call Mom and Dad, they miss you.");
        messages8.setValue("Catch up with a long lost old friend");
        messages9.setValue("Start learning a new language");
        messages10.setValue("Run for 30 minutes around the neighbourhood");
        messages11.setValue("Learn a new skill such as programming or design");
        messages12.setValue("Ask that girl/guy out that you like");
        messages13.setValue("Travel to somewhere random that is not known to you");

    }
    */

    public void saveLevel(int level) {
        SharedPreferences sharedPreferences = getSharedPreferences("level", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("currLevel", level);
        editor.commit();
        currChallengeText.setText(challengeBook.getRandomChallenge());
        Toast.makeText(this, "Challenge Completed!", Toast.LENGTH_LONG).show();
    }

    public int loadLevel() {
        SharedPreferences sharedPreferences = getSharedPreferences("level", Context.MODE_PRIVATE);
        int level = sharedPreferences.getInt("currLevel",1);

        return level;
    }

    public void saveNumChallenges(int challenges) {
        SharedPreferences sharedPreferences = getSharedPreferences("challenges", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("numChallenges", challenges);
        editor.commit();
    }

    public int loadNumChallenges() {
        SharedPreferences sharedPreferences = getSharedPreferences("challenges", Context.MODE_PRIVATE);
        int numChallenges = sharedPreferences.getInt("numChallenges",1);

        return numChallenges;
    }



}
