package com.example.localform;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Handler;
import android.widget.TextView;

import android.telephony.TelephonyManager;

/** Object exposed to JavaScript */
public class AndroidBridge {

    private static final String TAG = "AndroidBridge";
    private final Handler handler = new Handler();
    private Activity activity;

    public AndroidBridge(Activity activity) {
        this.activity = activity;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public void submit(final String json) { // must be final
        handler.post(new Runnable() {
            public void run() {
                try {
                    JSONObject jsonObject = new JSONObject(json.toString());
                    JSONObject jsonUser = (JSONObject) jsonObject.get("user");

                    TextView firstName = (TextView) activity.findViewById(R.id.first_name);
                    TextView lastName = (TextView) activity.findViewById(R.id.last_name);

                    firstName.setText(jsonUser.getString("firstname"));
                    lastName.setText(jsonUser.getString("lastname"));

                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });
    }
}
