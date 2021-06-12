package com.petestapp.app.util;

import android.content.Context;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class AppUtils {

    public static String loadJSONFromAsset(final Context context, String assetFileName)
            throws IOException {
        String json;
        InputStream is = context.getAssets().open(assetFileName);
        int size = is.available();
        byte[] buffer = new byte[size];
        is.read(buffer);
        is.close();
        json = new String(buffer, StandardCharsets.UTF_8);
        return json;
    }
}
