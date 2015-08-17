package in.indiantextilemagazine.indiantextilemagazine.ui;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.util.Log;

import in.indiantextilemagazine.indiantextilemagazine.R;
import in.indiantextilemagazine.indiantextilemagazine.Utilities.TextileIndiaPreferences;


/**
 * A {@link PreferenceActivity} that presents a set of application settings. On
 * handset devices, settings are presented as a single list. On tablets,
 * settings are split by category, with category headers shown to the left of
 * the list of settings.
 * <p/>
 * See <a href="http://developer.android.com/design/patterns/settings.html">
 * Android Design: Settings</a> for design guidelines and the <a
 * href="http://developer.android.com/guide/topics/ui/settings.html">Settings
 * API Guide</a> for more information on developing a Settings UI.
 */
public class MySettings extends PreferenceActivity implements SharedPreferences.OnSharedPreferenceChangeListener{

    private static final String TAG = "Settings";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settings);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getPreferenceScreen().getSharedPreferences()
                .registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        getPreferenceScreen().getSharedPreferences()
                .unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if(key.equals(TextileIndiaPreferences.KEY_PREF_NOTIFICATION)){
            Log.i(TAG, "Notification settings have been changed to => " + sharedPreferences.getBoolean(key, Boolean.TRUE));
            SharedPreferences.Editor editor = getSharedPreferences(TextileIndiaPreferences.GENERAL_DATA, MODE_PRIVATE).edit();
            editor.putBoolean(TextileIndiaPreferences.SEND_NOTIFICATIONS, sharedPreferences.getBoolean(key,Boolean.TRUE));
            editor.apply();
        }
    }
}
