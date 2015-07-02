package in.indiantextilemagazine.indiantextilemagazine.gcm;


// This class is not for us to edit, it deals with cases were the the Token is no longer valid,
// A good example would be if you restore a backup of a app which was uninstalled.
// So use Canonical IDs to solve such EDGE CASES. This might happen because you registered
// with several IDs for the same device, or because the GCM server didn't get the unregister()
// call when the app was uninstalled.

import android.content.Intent;
import android.util.Log;

import com.google.android.gms.iid.InstanceIDListenerService;

public class MyInstanceIDListenerService extends InstanceIDListenerService {

    private static final String TAG = "MyInstanceIDLS";

    /**
     * Called if InstanceID token is updated. This may occur if the security of
     * the previous token had been compromised. This call is initiated by the
     * InstanceID provider.
     */
    // [START refresh_token]
    @Override
    public void onTokenRefresh() {
        // Fetch updated Instance ID token and notify our app's server of any changes (if applicable).
        Intent intent = new Intent(this, RegistrationIntentService.class);
        startService(intent);
        Log.i(TAG, "Fetched Updated ID token");
    }
    // [END refresh_token]
}