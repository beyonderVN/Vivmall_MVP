package com.vinhsang.vivmall.presentation.ui.activity.shotdetail;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.customtabs.CustomTabsIntent;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.vinhsang.vivmall.presentation.R;

import static com.vinhsang.vivmall.data.datamanager.HTTP.TAG;


public class CustomTabUtil {

    public static void open(Context context, Uri uri) {
        int color = ContextCompat.getColor(context, R.color.colorPrimary);
        CustomTabsIntent customTabsIntent = new CustomTabsIntent.Builder()
                .setToolbarColor(color)
                .setShowTitle(true)
                .build();

        Intent intent = customTabsIntent.intent;
        intent.setData(uri);
        try {
            context.startActivity(intent);
        } catch (ActivityNotFoundException error) {
            Log.e(TAG, "There was a problem start the browser intent", error);

        }
    }

}