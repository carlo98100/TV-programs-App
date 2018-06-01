package com.carlo_projekt.tvprograms;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MoreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more);

        if ((getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_LARGE) {


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

    public void TwitchFunction(View view)
    {

        String twitchPackageName = new String("tv.twitch.android.app");
        OpenApps(twitchPackageName);

    }
    public void SvtPlayFunction(View view)
    {
        String svtPlayPackageName = new String("se.svt.android.svtplay");
        OpenApps(svtPlayPackageName);
    }

    public void ViaplayFunction(View view)
    {
        String viaplayPackageName = new String("com.viaplay.android");
        OpenApps(viaplayPackageName);

    }
    public void DPlayFunction(View view)
    {
        String dplayPackageName = new String("se.kanal5play");
        OpenApps(dplayPackageName);
    }

    public void NetflixFunction(View view)
    {

        String netflixPackageName = new String("com.netflix.mediaclient");
        OpenApps(netflixPackageName);
    }

    public void ViafreeFunction(View view)
    {
        String viafreePackageName = new String("se.viafree.android");
        OpenApps(viafreePackageName);
    }

    public void YoutubeFunction(View view)
    {
        String youtubePackageName = new String("com.google.android.youtube");
        OpenApps(youtubePackageName);
    }

    public void Tv4PlayFunction(View view)
    {
        String tv4playPackageName = new String("se.tv4.tv4playtab");
        OpenApps(tv4playPackageName);

    }

    public void MessageBox(final String uriIntent)
    {
        AlertDialog alertDialog = new AlertDialog.Builder(MoreActivity.this).create();
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
