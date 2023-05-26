package com.fyp.lucapp.Routes;

import com.fyp.lucapp.Helper.URL;

public class Url {

    public static String BASE_URL = "http://192.168.249.225:8000";

    public static String GET_APPOINTMENTS = BASE_URL + "/appointment/patient/get-appointments";

    public static String EDIT_PASSWORD = BASE_URL + "/patient/update-password";

    public static String SEND_MAIL = BASE_URL + "/mail/send-mail";

    public static String PATIENT_LOGIN = BASE_URL + "/patient/login";

    public static String PATIENT_REGISTER = BASE_URL + "/patient/signup";

    public static String GET_DOCTORS = BASE_URL + "/doctor/get-doctors";

    public static String CONFIRM_APPOINTMENT = BASE_URL + "/appointment/book-doctor";
    public static String GET_REPORTS = BASE_URL + "/report/get-report";

    public static String GET_MEDICINES = BASE_URL + "/patient/get-medicines";

    public static String GET_PATIENT = BASE_URL + "/patient/get-patient";

    public static String UPDATE_PATIENT = BASE_URL + "/patient/edit-patient";


}
