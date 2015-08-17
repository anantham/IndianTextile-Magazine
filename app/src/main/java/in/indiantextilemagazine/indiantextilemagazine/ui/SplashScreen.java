package in.indiantextilemagazine.indiantextilemagazine.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.ProgressBar;

import in.indiantextilemagazine.indiantextilemagazine.R;
import in.indiantextilemagazine.indiantextilemagazine.Utilities.CommonData;
import in.indiantextilemagazine.indiantextilemagazine.Utilities.ConnectionDetector;
import in.indiantextilemagazine.indiantextilemagazine.Utilities.TextileIndiaPreferences;


public class SplashScreen extends ActionBarActivity {

    public static final String TAG = "SplashScreen";
    public static ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        // Hide the ActionBar
        getSupportActionBar().hide();

        // UNCOMMENT The following to customize ActionBar
        //getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        //getSupportActionBar().setCustomView(R.layout.actionbar_layout);
        //getSupportActionBar().setIcon(R.drawable.icon_tentative);
        mProgressBar = (ProgressBar) findViewById(R.id.progressBarSplashScreen);

    }

    @Override
    protected void onStart() {
        super.onStart();

        // wait for SPLASH_SCREEN_DELAY milliseconds then launch the intent depending on registration status
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.i(TAG, "checking connection");
                ConnectionDetector connectionDetector = new ConnectionDetector(getApplicationContext());

                // Check if Internet present
                if (!connectionDetector.isConnectedInternet()) {
                    //Stop progressBar
                    SplashScreen.mProgressBar.setVisibility(ProgressBar.GONE);

                    // Internet Connection is not present, I make a special alert to get the user to access internet
                    // Here as I need the dialog to be a inner class to send the intent and call finish

                    new AlertDialog.Builder(SplashScreen.this)
                            .setTitle("Internet Connection Error")
                            .setMessage("Please connect to working Internet connection")
                            .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    Log.i(TAG, "ok has been clicked");
                                    Intent i = new Intent(Settings.ACTION_WIFI_SETTINGS);
                                    startActivity(i);
                                }
                            })
                            .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    Log.i(TAG, "Cancel has been clicked");
                                    finish();
                                }
                            })
                            .setIcon(R.drawable.alert)
                            .setCancelable(false)
                            .show();
                    return;
                }
                Log.i(TAG, "finished checking");

                SharedPreferences prefs = getSharedPreferences(TextileIndiaPreferences.GENERAL_DATA, MODE_PRIVATE);
                final Boolean GCMRegistered = prefs.getBoolean(TextileIndiaPreferences.GCM_REGISTRATION_STATUS, false);
                //TODO CHANGE TO
                if (true) {
                    Log.i(TAG, "GCM is registered, going to HOME activity");
                    Intent myIntent = new Intent(SplashScreen.this, MainMenu.class);
                    myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    SplashScreen.this.startActivity(myIntent);
                } else {
                    Log.i(TAG, "GCM is not registered, going to take this guy to get registered");
                    Intent myIntent = new Intent(SplashScreen.this, GCMData.class);
                    myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    SplashScreen.this.startActivity(myIntent);
                }
            }
        }, CommonData.SPLASH_SCREEN_DELAY);
    }



}
