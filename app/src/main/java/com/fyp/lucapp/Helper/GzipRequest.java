package com.fyp.lucapp.Helper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;

public class GzipRequest extends JsonObjectRequest {

    public GzipRequest(int method, String url, @Nullable JSONObject jsonRequest,
                       @NonNull Response.Listener<JSONObject> listener,
                       @Nullable Response.ErrorListener errorListener) {
        super(method, url, jsonRequest, listener, errorListener);
    }

    @Override
    protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
        String encoding = response.headers.get("Content-Encoding");
        if (encoding != null && encoding.equalsIgnoreCase("gzip")) {
            try {
                byte[] decompressedResponseData = decompressResponse(response.data);
                return Response.success(
                        new JSONObject(new String(decompressedResponseData)),
                        HttpHeaderParser.parseCacheHeaders(response));
            } catch (JSONException | IOException e) {
                return Response.error(new ParseError(e));
            }
        } else {
            return super.parseNetworkResponse(response);
        }
    }

    private byte[] decompressResponse(byte[] compressedData) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        InputStream in = new GZIPInputStream(new ByteArrayInputStream(compressedData));
        byte[] buffer = new byte[1024];
        int length;
        while ((length = in.read(buffer)) > 0) {
            out.write(buffer, 0, length);
        }
        return out.toByteArray();
    }
}

