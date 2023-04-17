package com.fyp.lucapp.Helper;

import android.content.Context;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.fyp.lucapp.Interface.InterfaceApi;
import com.fyp.lucapp.Interface.LoginCallback;
import com.fyp.lucapp.Interface.OnClickSubmit;
import com.fyp.lucapp.Interface.RegisterCallback;
import com.fyp.lucapp.Schemas.AppointmentSchema;
import com.fyp.lucapp.Schemas.EditPasswordSchema;
import com.fyp.lucapp.Schemas.GetPatientSchema;
import com.fyp.lucapp.Schemas.LoginSchema;
import com.fyp.lucapp.Schemas.RegisterPatientSchema;
import com.fyp.lucapp.Schemas.SendMailSchema;
import com.fyp.lucapp.Schemas.UpdatePatientSchema;

import org.json.JSONArray;
import org.json.JSONObject;

import java.nio.charset.StandardCharsets;

public class URL {
    public static String LOGGED_IN_PATIENT_ID = "";
    private static final String IP = "http://192.168.1.65:8000";
    private final String GET_DOCTORS = IP + "/doctor/get-doctors";
    private final Context context;
    private LoginCallback loginCallback;
    private RegisterCallback registerCallback;

    private OnClickSubmit onClickSubmit;

    private InterfaceApi interfaceApi;


    public URL(Context context, LoginCallback loginCallback) {
        this.context = context;
        this.loginCallback = loginCallback;

    }

    public URL(Context context, RegisterCallback registerCallback) {
        this.context = context;
        this.registerCallback = registerCallback;
    }

    public URL(Context context, InterfaceApi interfaceApi) {
        this.context = context;
        this.interfaceApi = interfaceApi;
    }

    public URL(Context context, OnClickSubmit onClick) {
        this.context = context;
        this.onClickSubmit = onClick;
    }


    public void updatePassword(EditPasswordSchema editPasswordSchema) {

        RequestQueue requestQueue = Volley.
                newRequestQueue(context);
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("new_password", editPasswordSchema.getNewPassword());

        } catch
        (Exception e) {
            e.printStackTrace();
        }

        String EDIT_PASSWORD = IP + "/patient/update-password";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.PUT,
                EDIT_PASSWORD + "/" + LOGGED_IN_PATIENT_ID,
                jsonObject, response -> {
            try {
                int statusCode = response.optInt("status_code");
                if (statusCode == 200) {
                    interfaceApi.onSuccess("Password changed successfully");
                } else {
                    interfaceApi.onError("Error changing password");
                }

            } catch
            (Exception e) {
                e.printStackTrace();
                interfaceApi.onError(e.toString());
            }
        }, error -> {
            int statusCode = error.networkResponse != null ? error.networkResponse.statusCode : -1;
            if (statusCode == 404) {
                interfaceApi.onError("Error changing password");
            } else {
                String errorMessage = "Error: " + statusCode;
                if (error.networkResponse != null && error.networkResponse.data != null) {
                    try {
                        String responseBody = new String(error.networkResponse.data,
                                StandardCharsets.UTF_8);
                        JSONObject data = new JSONObject(responseBody);
                        errorMessage = data.getString("detail");

                    } catch
                    (Exception e) {
                        e.printStackTrace();
                    }
                }
                interfaceApi.onError(errorMessage);
            }
        });

        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(0,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(jsonObjectRequest);


    }


    public void sendMail(SendMailSchema sendMailSchema) {
        RequestQueue requestQueue = Volley.
                newRequestQueue(context);
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("from_email", sendMailSchema.getFromEmail());
            jsonObject.put("to_email", sendMailSchema.getToEmail());
        } catch
        (Exception e) {
            e.printStackTrace();
        }

        String SEND_MAIL = IP + "/mail/send-mail";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                SEND_MAIL,
                jsonObject, response -> {
            try {
                int statusCode = response.optInt("status_code");
                if (statusCode == 202) {
                    int code = response.optInt("code");
                    interfaceApi.onSuccess(code);
                } else {
                    interfaceApi.onError("Error sending mail");
                }

            } catch
            (Exception e) {
                e.printStackTrace();
                interfaceApi.onError(e.toString());
            }
        }, error -> {
            int statusCode = error.networkResponse != null ? error.networkResponse.statusCode : -1;
            if (statusCode == 404) {
                interfaceApi.onError("Error sending mail");
            } else {
                String errorMessage = "Error: " + statusCode;
                if (error.networkResponse != null && error.networkResponse.data != null) {
                    try {
                        String responseBody = new String(error.networkResponse.data,
                                StandardCharsets.UTF_8);
                        JSONObject data = new JSONObject(responseBody);
                        errorMessage = data.getString("detail");

                    } catch
                    (Exception e) {
                        e.printStackTrace();
                    }
                }
                interfaceApi.onError(errorMessage);
            }
        });

        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(0,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(jsonObjectRequest);

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

        String PATIENT_LOGIN = IP + "/patient/login";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                PATIENT_LOGIN,
                jsonObject, response -> {
            try {
                int statusCode = response.optInt("status_code");
                if (statusCode == 200) {
                    JSONObject patient = response.getJSONObject("patient");
                    URL.LOGGED_IN_PATIENT_ID = patient.getString("id");
                    interfaceApi.onSuccess(patient);

                } else {
                    interfaceApi.onError("Invalid email or password");
                }

            } catch
            (Exception e) {
                e.printStackTrace();
                interfaceApi.onError(e.toString());
            }
        }, error -> {
            if (error.networkResponse != null && error.networkResponse.statusCode == 404) {
                interfaceApi.onError("Invalid email or password");
            } else {
                interfaceApi.onError(error.toString());
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

        String PATIENT_REGISTER = IP + "/patient/signup";
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
                interfaceApi.onSuccess(doctorsJsonList);


            } catch
            (Exception e) {
                e.printStackTrace();
                interfaceApi.onError(e.toString());
            }
        }, error -> {
            interfaceApi.onError(error.toString());


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
                interfaceApi.onSuccess(doctorJson);

            } catch
            (Exception e) {
                e.printStackTrace();
                interfaceApi.onError(e.toString());
            }
        }, error -> {
            //get status code from error
            String statusCode = String.valueOf(error.networkResponse.statusCode);
            System.out.println("Error: " + statusCode);
            interfaceApi.onError(error);
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

        String GET_REPORTS = IP + "/report/get-report";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                GET_REPORTS + "/" + URL.LOGGED_IN_PATIENT_ID,
                null, response -> {
            try {
                JSONArray reportList = response.getJSONArray("report_list");
                interfaceApi.onSuccess(reportList);
            } catch
            (Exception e) {
                e.printStackTrace();
                interfaceApi.onError(e.toString());
            }
        }, error -> {
            //get status code from error
            int statusCode = error.networkResponse != null ? error.networkResponse.statusCode : -1;
            if (statusCode == 404) {
                interfaceApi.onError("No reports found");
            } else {
                String errorMessage = "Error: " + statusCode;
                if (error.networkResponse != null && error.networkResponse.data != null) {
                    try {
                        String responseBody = new String(error.networkResponse.data,
                                StandardCharsets.UTF_8);
                        JSONObject data = new JSONObject(responseBody);
                        errorMessage = data.getString("detail");

                    } catch
                    (Exception e) {
                        e.printStackTrace();
                    }
                }
                interfaceApi.onError(errorMessage);
            }

        });

        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(0,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(jsonObjectRequest);
    }


    public void getAppointments() {
        RequestQueue requestQueue = Volley.
                newRequestQueue(context);

        String GET_APPOINTMENTS = IP + "/patient/getappointments";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                GET_APPOINTMENTS + "/" + URL.LOGGED_IN_PATIENT_ID,
                null, response -> {
            try {
                JSONArray reportsJsonList = response.getJSONArray("appointments");
                interfaceApi.onSuccess(reportsJsonList);
            } catch
            (Exception e) {
                e.printStackTrace();
                interfaceApi.onError(e.toString());
            }
        }, error -> {
            int statusCode = error.networkResponse != null ? error.networkResponse.statusCode : -1;
            if (statusCode == 404) {
                interfaceApi.onError("No appointments found");
            } else {
                String errorMessage = "Error: " + statusCode;
                if (error.networkResponse != null && error.networkResponse.data != null) {
                    try {
                        String responseBody = new String(error.networkResponse.data,
                                StandardCharsets.UTF_8);
                        JSONObject data = new JSONObject(responseBody);
                        errorMessage = data.getString("detail");

                    } catch
                    (Exception e) {
                        e.printStackTrace();
                    }
                }
                interfaceApi.onError(errorMessage);
            }

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

        String ADD_APPOINTMENT = IP + "/doctors/addappointment";
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
            int statusCode = error.networkResponse != null ? error.networkResponse.statusCode : -1;
            if (statusCode == 404) {
                interfaceApi.onError(error);
            } else {
                String errorMessage = "Error: " + statusCode;
                if (error.networkResponse != null && error.networkResponse.data != null) {
                    try {
                        String responseBody = new String(error.networkResponse.data,
                                StandardCharsets.UTF_8);
                        JSONObject data = new JSONObject(responseBody);
                        errorMessage = data.getString("detail");

                    } catch
                    (Exception e) {
                        e.printStackTrace();
                    }
                }
                interfaceApi.onError(errorMessage);
            }

        });

        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(0,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(jsonObjectRequest);
    }


    public void getMedicines() {
        RequestQueue requestQueue = Volley.
                newRequestQueue(context);

        String GET_MEDICINES = IP + "/patient/get-medicines";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                GET_MEDICINES + "/" + URL.LOGGED_IN_PATIENT_ID,
                null, response -> {
            try {
                int statusCode = response.optInt("status_code");
                if (statusCode == 200) {
                    JSONArray medicinesJsonList = response.getJSONArray("medicine_list");
                    interfaceApi.onSuccess(medicinesJsonList);
                } else {
                    interfaceApi.onError("No medicines found");
                }
            } catch
            (Exception e) {
                e.printStackTrace();
                interfaceApi.onError(e.toString());
            }
        }, error -> {
            int statusCode = error.networkResponse != null ? error.networkResponse.statusCode : -1;
            if (statusCode == 404) {
                interfaceApi.onError("No medicine found");
            } else {
                String errorMessage = "Error: " + statusCode;
                if (error.networkResponse != null && error.networkResponse.data != null) {
                    try {
                        String responseBody = new String(error.networkResponse.data,
                                StandardCharsets.UTF_8);
                        JSONObject data = new JSONObject(responseBody);
                        errorMessage = data.getString("detail");

                    } catch
                    (Exception e) {
                        e.printStackTrace();
                    }
                }
                interfaceApi.onError(errorMessage);
            }

        });

        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(0,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(jsonObjectRequest);
    }

    public void getPatient(GetPatientSchema getPatientSchema) {
        RequestQueue requestQueue = Volley.
                newRequestQueue(context);


        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("patient_email", getPatientSchema.getEmail());
            System.out.println(jsonObject);

        } catch (Exception ex) {
            System.out.println(ex);
        }

        String GET_PATIENT = IP + "/patient/get-patient";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                GET_PATIENT,
                jsonObject, response -> {
            try {
                int statusCode = response.optInt("status_code");
                if (statusCode == 200) {
                    JSONObject patientObject = response.optJSONObject("patient");
                    interfaceApi.onSuccess(patientObject);
                } else {
                    interfaceApi.onError("Patient not found");
                }
            } catch
            (Exception e) {
                e.printStackTrace();
                interfaceApi.onError(e.toString());
            }
        }, error -> {
            int statusCode = error.networkResponse != null ? error.networkResponse.statusCode : -1;
            if (statusCode == 404) {
                interfaceApi.onError("Patient not found");
            } else {
                String errorMessage = "Error: " + statusCode;
                if (error.networkResponse != null && error.networkResponse.data != null) {
                    try {
                        String responseBody = new String(error.networkResponse.data,
                                StandardCharsets.UTF_8);
                        JSONObject data = new JSONObject(responseBody);
                        errorMessage = data.getString("detail");

                    } catch
                    (Exception e) {
                        e.printStackTrace();
                    }
                }
                interfaceApi.onError(errorMessage);
            }

        });

        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(0,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(jsonObjectRequest);

    }

    public void updatePatient(UpdatePatientSchema updatePatientSchema) {
        RequestQueue requestQueue = Volley.
                newRequestQueue(context);

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("patient_name", updatePatientSchema.getUserName());
            jsonObject.put("patient_phone", updatePatientSchema.getPhoneNumber());
            jsonObject.put("patient_age", updatePatientSchema.getAge());
            jsonObject.put("patient_image", updatePatientSchema.getImage());


        } catch (Exception ex) {
            System.out.println(ex);
        }

        String UPDATE_PATIENT = IP + "/patient/update-appointments";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.PUT,
                UPDATE_PATIENT + "/" + URL.LOGGED_IN_PATIENT_ID,
                jsonObject, response -> {
            try {
                int statusCode = response.optInt("status_code");
                if (statusCode == 200) {
                    JSONArray appointmentsJsonList = response.getJSONArray("appointment_list");
                    interfaceApi.onSuccess(appointmentsJsonList);
                } else {
                    interfaceApi.onError("patient not updated");
                }
            } catch
            (Exception e) {
                e.printStackTrace();
                interfaceApi.onError(e.toString());
            }
        }, error -> {
            int statusCode = error.networkResponse != null ? error.networkResponse.statusCode : -1;
            if (statusCode == 404) {
                interfaceApi.onError("Patient not updated");
            } else {
                String errorMessage = "Error: " + statusCode;
                if (error.networkResponse != null && error.networkResponse.data != null) {
                    try {
                        String responseBody = new String(error.networkResponse.data,
                                StandardCharsets.UTF_8);
                        JSONObject data = new JSONObject(responseBody);
                        errorMessage = data.getString("detail");

                    } catch
                    (Exception e) {
                        e.printStackTrace();
                    }
                }
                interfaceApi.onError(errorMessage);
            }

        });

        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(0,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(jsonObjectRequest);
    }


}
