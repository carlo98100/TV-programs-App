package com.carlo_projekt.tvprograms;

import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ImageButton speakBtn;
    Button CategoriesBtn;

    String viaplayPackageName;
    String viafreePackageName;
    String netflixPackageName;
    String tvfourPlayPackageName;
    String svtPlayPackageName;
    String youtubePackageName;
    String dplayPackageName;
    String twitchPackageName;

    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        speakBtn = (ImageButton) findViewById(R.id.SpeakImageBtn);
        CategoriesBtn = (Button) findViewById(R.id.CategoriesBtn);
        text = (TextView) findViewById(R.id.textView);

         viaplayPackageName = "com.viaplay.android";
         viafreePackageName = "se.viafree.android";
         netflixPackageName = "com.netflix.mediaclient";
         tvfourPlayPackageName = "se.tv4.tv4playtab";
         svtPlayPackageName = "se.svt.android.svtplay";
        youtubePackageName = "com.google.android.youtube";
         dplayPackageName = "se.kanal5play";
         twitchPackageName = "tv.twitch.android.app";

    }


    public void OpenGame(View view) {
        Intent intent = new Intent(this, MoreActivity.class);
        startActivity(intent);
    }

    public void Speak(View view)
    {
        Intent recognizeIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        recognizeIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        recognizeIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "en-US");
        recognizeIntent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Hi! Which app do you want to start?");

        try {
            startActivityForResult(recognizeIntent, 1);

        }
        catch (ActivityNotFoundException a)
        {
            Toast.makeText(MainActivity.this, "Sorry! Your device doesn't support this function!", Toast.LENGTH_LONG).show();
        }
    }

    public void onActivityResult(int request_Code, int result_Code, Intent recognizeIntent)
    {
        super.onActivityResult(request_Code, result_Code, recognizeIntent);
        switch (request_Code)
        {
            case 1: if(result_Code == RESULT_OK && recognizeIntent != null)
            {
                ArrayList<String> result = recognizeIntent.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                for(int i = 0; i<result.size(); i++)
                {
                    result.set(i, result.get(i).toLowerCase());

                    if(result.get(i).compareTo("start netflix") == 0)
                    {
                        OpenApps(netflixPackageName);
                    }

                    else if(result.get(i).compareTo("start youtube") == 0)
                    {
                        OpenApps(youtubePackageName);
                    }

                    else if(result.get(i).compareTo("start viafree") == 0)
                    {
                        OpenApps(viafreePackageName);
                    }

                    else if(result.get(i).compareTo("start tvfour") == 0)
                    {
                        OpenApps(tvfourPlayPackageName);
                    }

                    else if(result.get(i).compareTo("start dplay") == 0)
                    {
                        OpenApps(dplayPackageName);
                    }

                    else if(result.get(i).compareTo("start viaplay") == 0)
                    {
                        OpenApps(viaplayPackageName);
                    }

                    else if(result.get(i).compareTo("start svtplay") == 0)
                    {
                        OpenApps(svtPlayPackageName);
                    }
                    else if(result.get(i).compareTo("start twitch") == 0)
                    {
                        OpenApps(twitchPackageName);
                    }
                    /**text.setText(result.get(i));*/
                }
            }
        }

    }

    public void Swedish(View view) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(getAssets().open("language.txt")));
        String lines;

        while ((lines = reader.readLine()) != null)
        {
            if(lines.compareTo("SWEDISH:") == 0)
            {
                /**Det är 17 översättningar i varje språk, med mellanrum så är det 34 rader.*/
                reader.read();
                for(int i = 0; i < 34 && reader.read() < 34; i++)
                {
                    
                }


            }
            text.setText(lines);

        }


    }

    public void OpenApps(String packageName)
    {
        if(getPackageManager().getLaunchIntentForPackage(packageName) != null)
        {
            Intent tv4PlayIntent = getPackageManager().getLaunchIntentForPackage(packageName);
            startActivity(tv4PlayIntent);
        }
        else
        {

        String marketAppPackageName = "market://details?id="+ packageName;
        MessageBox(marketAppPackageName);

        }
    }

    public void NetflixFunction(View view)
    {
        OpenApps(netflixPackageName);
    }

    public void ViafreeFunction(View view)
    {
        OpenApps(viafreePackageName);
    }

    public void YoutubeFunction(View view)
    {
        OpenApps(youtubePackageName);
    }

    public void Tv4PlayFunction(View view)
    {
        OpenApps(tvfourPlayPackageName);
    }



    public void LayoutsShow(View view)
    {
        RelativeLayout layoutShow = findViewById(R.id.LayoutShow);

        if (layoutShow.getVisibility() == View.GONE) {
            layoutShow.setVisibility(View.VISIBLE);
        }

        else if (layoutShow.getVisibility() == View.VISIBLE) {
            layoutShow.setVisibility(View.GONE);
        }
    }

    public void MessageBox(final String uriIntent)
    {
        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
        alertDialog.setTitle("Couldn't find this app");
        String messageText = "We cant find this app, do you want to download it?";
        alertDialog.setMessage(messageText);

        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent MarketIntent = new Intent(Intent.ACTION_VIEW);
                        MarketIntent.setData(Uri.parse(uriIntent));
                        startActivity(MarketIntent);
                        dialog.dismiss();
                    }
                });

        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }
}
