package com.fyp.lucapp.Interface;

import org.json.JSONObject;

public interface InterfaceApi {
    void onSuccess(JSONObject response);

    void onError(Object message);

}
