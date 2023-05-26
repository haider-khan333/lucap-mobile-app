package com.fyp.lucapp.Helper;

import android.os.Build;
import com.android.volley.toolbox.HurlStack;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class GzipHttpStack extends HurlStack {

    @Override
    protected HttpURLConnection createConnection(URL url) throws IOException {
        HttpURLConnection connection = super.createConnection(url);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP_MR1) {
            connection.setRequestProperty("Accept-Encoding", "gzip");
        }
        return connection;
    }
}

