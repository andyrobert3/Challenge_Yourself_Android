package com.example.aaryam123.everyday_challenge_final;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.Image;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;
import java.util.Locale;

public class profile extends AppCompatActivity {

    private Levels levels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        ImageButton facebookBtn = (ImageButton) findViewById(R.id.facebookBtn);
        ImageButton whatsappBtn = (ImageButton) findViewById(R.id.whatsappBtn);
        TextView welcomeText = (TextView) findViewById(R.id.welcomeText);
        TextView facebookText = (TextView) findViewById(R.id.facebookText);
        TextView whatsappText = (TextView) findViewById(R.id.whatsappText);
        TextView challengeText = (TextView) findViewById(R.id.challengeText);
        TextView wantText = (TextView) findViewById(R.id.wantText);
        TextView profNameText = (TextView) findViewById(R.id.profNameText);
        TextView statsText = (TextView) findViewById(R.id.statsText);
        TextView levelText = (TextView) findViewById(R.id.levelText);
        TextView completeText = (TextView)findViewById(R.id.completeText);
        ImageButton locationBtn = (ImageButton) findViewById(R.id.locationBtn);
        ImageView image6 = (ImageView) findViewById(R.id.imageView6);
        ImageView image7 = (ImageView) findViewById(R.id.imageView7);
        ImageView image8 = (ImageView) findViewById(R.id.imageView8);
        ImageView image9 = (ImageView) findViewById(R.id.imageView9);

        final String nameInput = loadName();
        levels = new Levels(loadLevel(), loadNumChallenges());

        profNameText.setText(nameInput);

        Uri webpage = Uri.parse("http://www.facebook.com");
        Intent webIntent = new Intent(Intent.ACTION_VIEW, webpage);

        // Verify it resolves
        PackageManager packageManager = getPackageManager();
        List<ResolveInfo> activities = packageManager.queryIntentActivities(webIntent, 0);
        boolean isIntentSafe = activities.size() > 0;

        View.OnClickListener oclFb = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri webpage = Uri.parse("http:/m.facebook.com");
                Intent webIntent = new Intent(Intent.ACTION_VIEW, webpage);

                // Verify it resolves
                PackageManager packageManager = getPackageManager();
                List<ResolveInfo> activities = packageManager.queryIntentActivities(webIntent, 0);
                boolean isIntentSafe = activities.size() > 0;

                if (isIntentSafe) {
                    startActivity(webIntent);
                }
            }
        };

        facebookBtn.setOnClickListener(oclFb);

        View.OnClickListener openMaps = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(profile.this, MapsActivity.class);
                startActivity(i);
            }
        };

        locationBtn.setOnClickListener(openMaps);


        View.OnClickListener oclWa = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sendIntent = new Intent();
                sendIntent.setPackage("com.whatsapp");
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "I have completed the daily challenge! Come use the Everyday Challenge App!");
                sendIntent.setType("text/plain");
                PackageManager packageManager = getPackageManager();
                List<ResolveInfo> activities = packageManager.queryIntentActivities(sendIntent, 0);
                boolean isIntentSafe = activities.size() > 0;

                if (isIntentSafe) {
                    startActivity(sendIntent);
                }
            }
        };

        whatsappBtn.setOnClickListener(oclWa);

        completeText.setText("You have completed " + levels.getNumChallenges() + " challenges");
        levelText.setText("You are now at level " + levels.getCurrLevel());

    }
    // set nameField default
    public String loadName() {
        SharedPreferences sharedPreferences = getSharedPreferences("userInfo", Context.MODE_PRIVATE);

        String name = sharedPreferences.getString("username","");

        return name;
    }

    public int loadNumChallenges() {
        SharedPreferences sharedPreferences = getSharedPreferences("challenges", Context.MODE_PRIVATE);
        int numChallenges = sharedPreferences.getInt("numChallenges",1);

        return numChallenges;
    }

    public int loadLevel() {
        SharedPreferences sharedPreferences = getSharedPreferences("level", Context.MODE_PRIVATE);
        int level = sharedPreferences.getInt("currLevel",1);

        return level;
    }


}
