package com.fyp.lucapp.Helper;

import android.content.Context;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.fyp.lucapp.Interface.ApiCallBack;
import com.fyp.lucapp.Interface.LoginCallback;
import com.fyp.lucapp.Interface.OnClickSubmit;
import com.fyp.lucapp.Interface.RegisterCallback;
import com.fyp.lucapp.Schemas.AppointmentSchema;
import com.fyp.lucapp.Schemas.LoginSchema;
import com.fyp.lucapp.Schemas.RegisterPatientSchema;

import org.json.JSONArray;
import org.json.JSONObject;

public class URL {
    public static String LOGGED_IN_PATIENT_ID = "";
    public static String IP = "192.168.1.65:8000";
    public final String PATIENT_LOGIN = "http://" + IP + "/patient/login";
    public final String PATIENT_REGISTER = "http://" + IP + "/patient/signup";
    public final String GET_DOCTORS = "http://" + IP + "/doctor/get-doctors";
    public final String GET_REPORTS = "http://" + IP + "/patient/get-reports";

    public final String GET_APPOINTMENTS = "http://" + IP + "/patient/getappointments";

    public final String ADD_APPOINTMENT = "http://" + IP + "/doctors/addappointment";
    public Context context;
    public LoginCallback loginCallback;
    public RegisterCallback registerCallback;

    public OnClickSubmit onClickSubmit;

    public ApiCallBack apiCallBack;


    public URL(Context context, LoginCallback loginCallback) {
        this.context = context;
        this.loginCallback = loginCallback;

    }

    public URL(Context context, RegisterCallback registerCallback) {
        this.context = context;
        this.registerCallback = registerCallback;
    }

    public URL(Context context, ApiCallBack apiCallBack) {
        this.context = context;
        this.apiCallBack = apiCallBack;
    }

    public URL(Context context, OnClickSubmit onClick) {
        this.context = context;
        this.onClickSubmit = onClick;
    }


    public void loginPatient(LoginSchema login) {

        RequestQueue requestQueue = Volley.
                newRequestQueue(context);
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("email", login.getEmail());
            jsonObject.put("password", login.getPassword());
        } catch
        (Exception e) {
            e.printStackTrace();
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                PATIENT_LOGIN,
                jsonObject, response -> {
            try {
                URL.LOGGED_IN_PATIENT_ID = response.getString("patient_id");
                loginCallback.onSuccess();

            } catch
            (Exception e) {
                e.printStackTrace();
                loginCallback.onError(e.toString());
            }
        }, error -> {
            System.out.println("Error: " + error);

            if (error.networkResponse != null && error.networkResponse.statusCode == 404) {
                loginCallback.onError("Invalid email or password");
            } else {
                loginCallback.onError(error.toString());
            }
        });


        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(0,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(jsonObjectRequest);


    }

    public void registerPatient(RegisterPatientSchema register) {

        RequestQueue requestQueue = Volley.
                newRequestQueue(context);
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("email", register.getPatientEmail());
            jsonObject.put("password", register.getPatientPassword());
            jsonObject.put("username", register.getPatientName());
            jsonObject.put("phone", register.getPatientContact());
            jsonObject.put("image", register.getPatientImage());
            jsonObject.put("age", register.getPatientAge());
            jsonObject.put("gender", register.getPatientGender());

        } catch
        (Exception e) {
            e.printStackTrace();
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                PATIENT_REGISTER,
                jsonObject, response -> {
            try {
                String statusCode = response.getString("status_code");
                if (statusCode.equals("200")) {
                    registerCallback.onSuccess();
                }

            } catch
            (Exception e) {
                e.printStackTrace();
                registerCallback.onError(e.toString());
            }
        }, error -> {
            System.out.println("Error: " + error);
            if (error.networkResponse != null && error.networkResponse.statusCode == 404) {
                registerCallback.onError("User already exists");
            } else {
                registerCallback.onError(error.toString());
            }
        });


        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(0,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(jsonObjectRequest);


    }


    public void getDoctors() {
        RequestQueue requestQueue = Volley.
                newRequestQueue(context);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                GET_DOCTORS,
                null, response -> {
            try {
                JSONArray doctorsJsonList = response.getJSONArray("doctors_list");
                apiCallBack.onSuccess(doctorsJsonList);


            } catch
            (Exception e) {
                e.printStackTrace();
                apiCallBack.onError(e.toString());
            }
        }, error -> {
            //get status code from error
//            String statusCode = String.valueOf(error.networkResponse.statusCode);
//            System.out.println("Error: " + statusCode);
            apiCallBack.onError(error);


        });

        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(0,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(jsonObjectRequest);

    }

    public void getSpecificDoctor(int doctorId) {
        RequestQueue requestQueue = Volley.
                newRequestQueue(context);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                GET_DOCTORS + "/" + doctorId,
                null, response -> {
            try {
                JSONObject doctorJson = response.getJSONObject("doctors_list");
                apiCallBack.onSuccess(doctorJson);

            } catch
            (Exception e) {
                e.printStackTrace();
                apiCallBack.onError(e.toString());
            }
        }, error -> {
            //get status code from error
            String statusCode = String.valueOf(error.networkResponse.statusCode);
            System.out.println("Error: " + statusCode);
            apiCallBack.onError(error);
        }
        );

        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(0,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(jsonObjectRequest);
    }


    public void getReports() {
        RequestQueue requestQueue = Volley.
                newRequestQueue(context);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                GET_REPORTS + "/" + URL.LOGGED_IN_PATIENT_ID,
                null, response -> {
            try {
                JSONArray reportsJsonList = response.getJSONArray("reports_list");
                apiCallBack.onSuccess(reportsJsonList);
            } catch
            (Exception e) {
                e.printStackTrace();
                apiCallBack.onError(e.toString());
            }
        }, error -> {
            //get status code from error
            String statusCode = String.valueOf(error.networkResponse.statusCode);
            System.out.println("Error: " + statusCode);
            apiCallBack.onError(error);

        });

        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(0,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(jsonObjectRequest);
    }


    public void getAppointments() {
        RequestQueue requestQueue = Volley.
                newRequestQueue(context);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                GET_APPOINTMENTS + "/" + URL.LOGGED_IN_PATIENT_ID,
                null, response -> {
            try {
                JSONArray reportsJsonList = response.getJSONArray("appointments");
                apiCallBack.onSuccess(reportsJsonList);
            } catch
            (Exception e) {
                e.printStackTrace();
                apiCallBack.onError(e.toString());
            }
        }, error -> {
            //get status code from error
            String statusCode = String.valueOf(error.networkResponse.statusCode);
            System.out.println("Error: " + statusCode);
            apiCallBack.onError(error);

        });

        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(0,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(jsonObjectRequest);
    }


    public void addAppointments(AppointmentSchema appointmentSchema) {
        RequestQueue requestQueue = Volley.
                newRequestQueue(context);

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("doctor_id", appointmentSchema.getDoctorId());
            jsonObject.put("patient_id", appointmentSchema.getPatientId());
            jsonObject.put("day", appointmentSchema.getDate());
            jsonObject.put("start_time", appointmentSchema.getStart_time());
            jsonObject.put("end_time", appointmentSchema.getEnd_time());
            System.out.println(jsonObject);

        } catch (Exception ex) {
            System.out.println(ex);
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                ADD_APPOINTMENT,
                jsonObject, response -> {
            try {
                String message = response.getString("message");
                onClickSubmit.onSuccessSubmit(message);
            } catch
            (Exception e) {
                e.printStackTrace();
                onClickSubmit.onFailure(e.toString());
            }
        }, error -> {
            //get status code from error
            String statusCode = String.valueOf(error.networkResponse.statusCode);
            System.out.println("Error: " + statusCode);
            System.out.println("Error: " + error);
            onClickSubmit.onFailure(error);

        });

        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(0,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(jsonObjectRequest);
    }
}
