package com.fyp.lucapp.BasicModels;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Arrays;

public class Data {
    public static ArrayList<DoctorsData> doctors;
    public static ArrayList<Report> reportList;
    public static Person loggedInPerson;
    //    public static ArrayList<Medications> medicineList;
    //create an arraylist of Time and add three values to it
    public static ArrayList<Time> time = new ArrayList<>(Arrays.asList(
            Time.valueOf("09:00:00"),
            Time.valueOf("12:00:00"),
            Time.valueOf("15:00:00")
    ));

    public static ArrayList<Appointment> appointments = new ArrayList<>();

    public static final String BASE64 = "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AAECBAgQIECAQCggyEN80wQIECBAgAABAgQEuRsgQIAAAQIECBAgEAoI8hDfNAECBAgQIECAAAFB7gYIECBAgAABAgQIhAKCPMQ3TYAAAQIECBAgQECQuwECBAgQIECAAAECoYAgD/FNEyBAgAABAgQIEBDkboAAAQIECBAgQIBAKCDIQ3zTBAgQIECAAAECBAS5GyBAgAABAgQIECAQCgjyEN80AQIECBAgQIAAAUHuBggQIECAAAECBAiEAoI8xDdNgAABAgQIECBAQJC7AQIECBAgQIAAAQKhgCAP8U0TIECAAAECBAgQEORugAABAgQIECBAgEAoIMhDfNMECBAgQIAAAQIEBLkbIECAAAECBAgQIBAKCPIQ3zQBAgQIECBAgAABQe4GCBAgQIAAAQIECIQCgjzEN02AAAECBAgQIEBAkLsBAgQIECBAgAABAqGAIA/xTRMgQIAAAQIECBAQ5G6AAAECBAgQIECAQCggyEN80wQIECBAgAABAgQEuRsgQIAAAQIECBAgEAoI8hDfNAECBAgQIECAAAFB7gYIECBAgAABAgQIhAKCPMQ3TYAAAQIECBAgQECQuwECBAgQIECAAAECoYAgD/FNEyBAgAABAgQIEBDkboAAAQIECBAgQIBAKCDIQ3zTBAgQIECAAAECBAS5GyBAgAABAgQIECAQCgjyEN80AQIECBAgQIAAAUHuBggQIECAAAECBAiEAoI8xDdNgAABAgQIECBAQJC7AQIECBAgQIAAAQKhgCAP8U0TIECAAAECBAgQEORugAABAgQIECBAgEAoIMhDfNMECBAgQIAAAQIEBLkbIECAAAECBAgQIBAKCPIQ3zQBAgQIECBAgAABQe4GCBAgQIAAAQIECIQCgjzEN02AAAECBAgQIEBAkLsBAgQIECBAgAABAqGAIA/xTRMgQIAAAQIECBAQ5G6AAAECBAgQIECAQCggyEN80wQIECBAgAABAgQEuRsgQIAAAQIECBAgEAoI8hDfNAECBAgQIECAAAFB7gYIECBAgAABAgQIhAKCPMQ3TYAAAQIECBAgQECQuwECBAgQIECAAAECoYAgD/FNEyBAgAABAgQIEBDkboAAAQIECBAgQIBAKCDIQ3zTBAgQIECAAAECBAS5GyBAgAABAgQIECAQCgjyEN80AQIECBAgQIAAAUHuBggQIECAAAECBAiEAoI8xDdNgAABAgQIECBAQJC7AQIECBAgQIAAAQKhgCAP8U0TIECAAAECBAgQEORugAABAgQIECBAgEAoIMhDfNMECBAgQIAAAQIEBLkbIECAAAECBAgQIBAKCPIQ3zQBAgQIECBAgAABQe4GCBAgQIAAAQIECIQCgjzEN02AAAECBAgQIEBAkLsBAgQIECBAgAABAqGAIA/xTRMgQIAAAQIECBAQ5G6AAAECBAgQIECAQCggyEN80wQIECBAgAABAgQEuRsgQIAAAQIECBAgEAoI8hDfNAECBAgQIECAAAFB7gYIECBAgAABAgQIhAKCPMQ3TYAAAQIECBAgQECQuwECBAgQIECAAAECoYAgD/FNEyBAgAABAgQIEBDkboAAAQIECBAgQIBAKCDIQ3zTBAgQIECAAAECBAS5GyBAgAABAgQIECAQCgjyEN80AQIECBAgQIAAAUHuBggQIECAAAECBAiEAoI8xDdNgAABAgQIECBAQJC7AQIECBAgQIAAAQKhgCAP8U0TIECAAAECBAgQEORugAABAgQIECBAgEAoIMhDfNMECBAgQIAAAQIEBLkbIECAAAECBAgQIBAKCPIQ3zQBAgQIECBAgAABQe4GCBAgQIAAAQIECIQCgjzEN02AAAECBAgQIEBAkLsBAgQIECBAgAABAqGAIA/xTRMgQIAAAQIECBAQ5G6AAAECBAgQIECAQCggyEN80wQIECBAgAABAgQEuRsgQIAAAQIECBAgEAoI8hDfNAECBAgQIECAAAFB7gYIECBAgAABAgQIhAKCPMQ3TYAAAQIECBAgQECQuwECBAgQIECAAAECoYAgD/FNEyBAgAABAgQIEBDkboAAAQIECBAgQIBAKCDIQ3zTBAgQIECAAAECBAS5GyBAgAABAgQIECAQCgjyEN80AQIECBAgQIAAAUHuBggQIECAAAECBAiEAoI8xDdNgAABAgQIECBAQJC7AQIECBAgQIAAAQKhgCAP8U0TIECAAAECBAgQEORugAABAgQIECBAgEAoIMhDfNMECBAgQIAAAQIEBLkbIECAAAECBAgQIBAKCPIQ3zQBAgQIECBAgAABQe4GCBAgQIAAAQIECIQCgjzEN02AAAECBAgQIEBAkLsBAgQIECBAgAABAqGAIA/xTRMgQIAAAQIECBAQ5G6AAAECBAgQIECAQCggyEN80wQIECBAgAABAgQEuRsgQIAAAQIECBAgEAoI8hDfNAECBAgQIECAAAFB7gYIECBAgAABAgQIhAKCPMQ3TYAAAQIECBAgQECQuwECBAgQIECAAAECoYAgD/FNEyBAgAABAgQIEBDkboAAAQIECBAgQIBAKCDIQ3zTBAgQIECAAAECBAS5GyBAgAABAgQIECAQCgjyEN80AQIECBAgQIAAAUHuBggQIECAAAECBAiEAoI8xDdNgAABAgQIECBAQJC7AQIECBAgQIAAAQKhgCAP8U0TIECAAAECBAgQEORugAABAgQIECBAgEAoIMhDfNMECBAgQIAAAQIEBLkbIECAAAECBAgQIBAKCPIQ3zQBAgQIECBAgAABQe4GCBAgQIAAAQIECIQCgjzEN02AAAECBAgQIEBAkLsBAgQIECBAgAABAqGAIA/xTRMgQIAAAQIECBAQ5G6AAAECBAgQIECAQCggyEN80wQIECBAgAABAgQEuRsgQIAAAQIECBAgEAoI8hDfNAECBAgQIECAAAFB7gYIECBAgAABAgQIhAKCPMQ3TYAAAQIECBAgQECQuwECBAgQIECAAAECoYAgD/FNEyBAgAABAgQIEBDkboAAAQIECBAgQIBAKCDIQ3zTBAgQIECAAAECBAS5GyBAgAABAgQIECAQCgjyEN80AQIECBAgQIAAAUHuBggQIECAAAECBAiEAoI8xDdNgAABAgQIECBAQJC7AQIECBAgQIAAAQKhgCAP8U0TIECAAAECBAgQEORugAABAgQIECBAgEAoIMhDfNMECBAgQIAAAQIEBLkbIECAAAECBAgQIBAKCPIQ3zQBAgQIECBAgAABQe4GCBAgQIAAAQIECIQCgjzEN02AAAECBAgQIEBAkLsBAgQIECBAgAABAqGAIA/xTRMgQIAAAQIECBAQ5G6AAAECBAgQIECAQCggyEN80wQIECBAgAABAgQEuRsgQIAAAQIECBAgEAoI8hDfNAECBAgQIECAAAFB7gYIECBAgAABAgQIhAKCPMQ3TYAAAQIECBAgQECQuwECBAgQIECAAAECoYAgD/FNEyBAgAABAgQIEBDkboAAAQIECBAgQIBAKCDIQ3zTBAgQIECAAAECBAS5GyBAgAABAgQIECAQCgjyEN80AQIECBAgQIAAAUHuBggQIECAAAECBAiEAoI8xDdNgAABAgQIECBAQJC7AQIECBAgQIAAAQKhgCAP8U0TIECAAAECBAgQEORugAABAgQIECBAgEAoIMhDfNMECBAgQIAAAQIEBLkbIECAAAECBAgQIBAKCPIQ3zQBAgQIECBAgAABQe4GCBAgQIAAAQIECIQCgjzEN02AAAECBAgQIEBAkLsBAgQIECBAgAABAqGAIA/xTRMgQIAAAQIECBAQ5G6AAAECBAgQIECAQCggyEN80wQIECBAgAABAgQEuRsgQIAAAQIECBAgEAoI8hDfNAECBAgQIECAAAFB7gYIECBAgAABAgQIhAKCPMQ3TYAAAQIECBAgQECQuwECBAgQIECAAAECoYAgD/FNEyBAgAABAgQIEBDkboAAAQIECBAgQIBAKCDIQ3zTBAgQIECAAAECBAS5GyBAgAABAgQIECAQCgjyEN80AQIECBAgQIAAAUHuBggQIECAAAECBAiEAoI8xDdNgAABAgQIECBAQJC7AQIECBAgQIAAAQKhgCAP8U0TIECAAAECBAgQEORugAABAgQIECBAgEAoIMhDfNMECBAgQIAAAQIEBLkbIECAAAECBAgQIBAKCPIQ3zQBAgQIECBAgAABQe4GCBAgQIAAAQIECIQCgjzEN02AAAECBAgQIEBAkLsBAgQIECBAgAABAqGAIA/xTRMgQIAAAQIECBAQ5G6AAAECBAgQIECAQCggyEN80wQIECBAgAABAgQuAAAA///EvWH3AAASm0lEQVTt1rkJADAMBEGp/6L9VLHJGBwfDAp2Z+a87xEgQIAAAQIECBAgEAjs2xTkAbxJAgQIECBAgAABAl9AkLsDAgQIECBAgAABAqGAIA/xTRMgQIAAAQIECBAQ5G6AAAECBAgQIECAQCggyEN80wQIECBAgAABAgQEuRsgQIAAAQIECBAgEAoI8hDfNAECBAgQIECAAAFB7gYIECBAgAABAgQIhAKCPMQ3TYAAAQIECBAgQECQuwECBAgQIECAAAECoYAgD/FNEyBAgAABAgQIEBDkboAAAQIECBAgQIBAKCDIQ3zTBAgQIECAAAECBAS5GyBAgAABAgQIECAQCgjyEN80AQIECBAgQIAAAUHuBggQIECAAAECBAiEAoI8xDdNgAABAgQIECBAQJC7AQIECBAgQIAAAQKhgCAP8U0TIECAAAECBAgQEORugAABAgQIECBAgEAoIMhDfNMECBAgQIAAAQIEBLkbIECAAAECBAgQIBAKCPIQ3zQBAgQIECBAgAABQe4GCBAgQIAAAQIECIQCgjzEN02AAAECBAgQIEBAkLsBAgQIECBAgAABAqGAIA/xTRMgQIAAAQIECBAQ5G6AAAECBAgQIECAQCggyEN80wQIECBAgAABAgQEuRsgQIAAAQIECBAgEAoI8hDfNAECBAgQIECAAAFB7gYIECBAgAABAgQIhAKCPMQ3TYAAAQIECBAgQECQuwECBAgQIECAAAECoYAgD/FNEyBAgAABAgQIEBDkboAAAQIECBAgQIBAKCDIQ3zTBAgQIECAAAECBAS5GyBAgAABAgQIECAQCgjyEN80AQIECBAgQIAAAUHuBggQIECAAAECBAiEAoI8xDdNgAABAgQIECBAQJC7AQIECBAgQIAAAQKhgCAP8U0TIECAAAECBAgQEORugAABAgQIECBAgEAoIMhDfNMECBAgQIAAAQIEBLkbIECAAAECBAgQIBAKCPIQ3zQBAgQIECBAgAABQe4GCBAgQIAAAQIECIQCgjzEN02AAAECBAgQIEBAkLsBAgQIECBAgAABAqGAIA/xTRMgQIAAAQIECBAQ5G6AAAECBAgQIECAQCggyEN80wQIECBAgAABAgQEuRsgQIAAAQIECBAgEAoI8hDfNAECBAgQIECAAAFB7gYIECBAgAABAgQIhAKCPMQ3TYAAAQIECBAgQECQuwECBAgQIECAAAECoYAgD/FNEyBAgAABAgQIEBDkboAAAQIECBAgQIBAKCDIQ3zTBAgQIECAAAECBAS5GyBAgAABAgQIECAQCgjyEN80AQIECBAgQIAAAUHuBggQIECAAAECBAiEAoI8xDdNgAABAgQIECBAQJC7AQIECBAgQIAAAQKhgCAP8U0TIECAAAECBAgQEORugAABAgQIECBAgEAoIMhDfNMECBAgQIAAAQIEBLkbIECAAAECBAgQIBAKCPIQ3zQBAgQIECBAgAABQe4GCBAgQIAAAQIECIQCgjzEN02AAAECBAgQIEBAkLsBAgQIECBAgAABAqGAIA/xTRMgQIAAAQIECBAQ5G6AAAECBAgQIECAQCggyEN80wQIECBAgAABAgQEuRsgQIAAAQIECBAgEAoI8hDfNAECBAgQIECAAAFB7gYIECBAgAABAgQIhAKCPMQ3TYAAAQIECBAgQECQuwECBAgQIECAAAECoYAgD/FNEyBAgAABAgQIEBDkboAAAQIECBAgQIBAKCDIQ3zTBAgQIECAAAECBAS5GyBAgAABAgQIECAQCgjyEN80AQIECBAgQIAAAUHuBggQIECAAAECBAiEAoI8xDdNgAABAgQIECBAQJC7AQIECBAgQIAAAQKhgCAP8U0TIECAAAECBAgQEORugAABAgQIECBAgEAoIMhDfNMECBAgQIAAAQIEBLkbIECAAAECBAgQIBAKCPIQ3zQBAgQIECBAgAABQe4GCBAgQIAAAQIECIQCgjzEN02AAAECBAgQIEBAkLsBAgQIECBAgAABAqGAIA/xTRMgQIAAAQIECBAQ5G6AAAECBAgQIECAQCggyEN80wQIECBAgAABAgQEuRsgQIAAAQIECBAgEAoI8hDfNAECBAgQIECAAAFB7gYIECBAgAABAgQIhAKCPMQ3TYAAAQIECBAgQECQuwECBAgQIECAAAECoYAgD/FNEyBAgAABAgQIEBDkboAAAQIECBAgQIBAKCDIQ3zTBAgQIECAAAECBAS5GyBAgAABAgQIECAQCgjyEN80AQIECBAgQIAAAUHuBggQIECAAAECBAiEAoI8xDdNgAABAgQIECBAQJC7AQIECBAgQIAAAQKhgCAP8U0TIECAAAECBAgQEORugAABAgQIECBAgEAoIMhDfNMECBAgQIAAAQIEBLkbIECAAAECBAgQIBAKCPIQ3zQBAgQIECBAgAABQe4GCBAgQIAAAQIECIQCgjzEN02AAAECBAgQIEBAkLsBAgQIECBAgAABAqGAIA/xTRMgQIAAAQIECBAQ5G6AAAECBAgQIECAQCggyEN80wQIECBAgAABAgQEuRsgQIAAAQIECBAgEAoI8hDfNAECBAgQIECAAAFB7gYIECBAgAABAgQIhAKCPMQ3TYAAAQIECBAgQECQuwECBAgQIECAAAECoYAgD/FNEyBAgAABAgQIEBDkboAAAQIECBAgQIBAKCDIQ3zTBAgQIECAAAECBAS5GyBAgAABAgQIECAQCgjyEN80AQIECBAgQIAAAUHuBggQIECAAAECBAiEAoI8xDdNgAABAgQIECBAQJC7AQIECBAgQIAAAQKhgCAP8U0TIECAAAECBAgQEORugAABAgQIECBAgEAoIMhDfNMECBAgQIAAAQIEBLkbIECAAAECBAgQIBAKCPIQ3zQBAgQIECBAgAABQe4GCBAgQIAAAQIECIQCgjzEN02AAAECBAgQIEBAkLsBAgQIECBAgAABAqGAIA/xTRMgQIAAAQIECBAQ5G6AAAECBAgQIECAQCggyEN80wQIECBAgAABAgQEuRsgQIAAAQIECBAgEAoI8hDfNAECBAgQIECAAAFB7gYIECBAgAABAgQIhAKCPMQ3TYAAAQIECBAgQECQuwECBAgQIECAAAECoYAgD/FNEyBAgAABAgQIEBDkboAAAQIECBAgQIBAKCDIQ3zTBAgQIECAAAECBAS5GyBAgAABAgQIECAQCgjyEN80AQIECBAgQIAAAUHuBggQIECAAAECBAiEAoI8xDdNgAABAgQIECBAQJC7AQIECBAgQIAAAQKhgCAP8U0TIECAAAECBAgQEORugAABAgQIECBAgEAoIMhDfNMECBAgQIAAAQIEBLkbIECAAAECBAgQIBAKCPIQ3zQBAgQIECBAgAABQe4GCBAgQIAAAQIECIQCgjzEN02AAAECBAgQIEBAkLsBAgQIECBAgAABAqGAIA/xTRMgQIAAAQIECBAQ5G6AAAECBAgQIECAQCggyEN80wQIECBAgAABAgQEuRsgQIAAAQIECBAgEAoI8hDfNAECBAgQIECAAAFB7gYIECBAgAABAgQIhAKCPMQ3TYAAAQIECBAgQECQuwECBAgQIECAAAECoYAgD/FNEyBAgAABAgQIEBDkboAAAQIECBAgQIBAKCDIQ3zTBAgQIECAAAECBAS5GyBAgAABAgQIECAQCgjyEN80AQIECBAgQIAAAUHuBggQIECAAAECBAiEAoI8xDdNgAABAgQIECBAQJC7AQIECBAgQIAAAQKhgCAP8U0TIECAAAECBAgQEORugAABAgQIECBAgEAoIMhDfNMECBAgQIAAAQIEBLkbIECAAAECBAgQIBAKCPIQ3zQBAgQIECBAgAABQe4GCBAgQIAAAQIECIQCgjzEN02AAAECBAgQIEBAkLsBAgQIECBAgAABAqGAIA/xTRMgQIAAAQIECBAQ5G6AAAECBAgQIECAQCggyEN80wQIECBAgAABAgQEuRsgQIAAAQIECBAgEAoI8hDfNAECBAgQIECAAAFB7gYIECBAgAABAgQIhAKCPMQ3TYAAAQIECBAgQECQuwECBAgQIECAAAECoYAgD/FNEyBAgAABAgQIEBDkboAAAQIECBAgQIBAKCDIQ3zTBAgQIECAAAECBAS5GyBAgAABAgQIECAQCgjyEN80AQIECBAgQIAAAUHuBggQIECAAAECBAiEAoI8xDdNgAABAgQIECBAQJC7AQIECBAgQIAAAQKhgCAP8U0TIECAAAECBAgQEORugAABAgQIECBAgEAoIMhDfNMECBAgQIAAAQIEBLkbIECAAAECBAgQIBAKCPIQ3zQBAgQIECBAgAABQe4GCBAgQIAAAQIECIQCgjzEN02AAAECBAgQIEBAkLsBAgQIECBAgAABAqGAIA/xTRMgQIAAAQIECBAQ5G6AAAECBAgQIECAQCggyEN80wQIECBAgAABAgQEuRsgQIAAAQIECBAgEAoI8hDfNAECBAgQIECAAAFB7gYIECBAgAABAgQIhAKCPMQ3TYAAAQIECBAgQECQuwECBAgQIECAAAECoYAgD/FNEyBAgAABAgQIEBDkboAAAQIECBAgQIBAKCDIQ3zTBAgQIECAAAECBAS5GyBAgAABAgQIECAQCgjyEN80AQIECBAgQIAAAUHuBggQIECAAAECBAiEAoI8xDdNgAABAgQIECBAQJC7AQIECBAgQIAAAQKhgCAP8U0TIECAAAECBAgQEORugAABAgQIECBAgEAoIMhDfNMECBAgQIAAAQIEBLkbIECAAAECBAgQIBAKCPIQ3zQBAgQIECBAgAABQe4GCBAgQIAAAQIECIQCgjzEN02AAAECBAgQIEBAkLsBAgQIECBAgAABAqGAIA/xTRMgQIAAAQIECBAQ5G6AAAECBAgQIECAQCggyEN80wQIECBAgAABAgQEuRsgQIAAAQIECBAgEAoI8hDfNAECBAgQIECAAAFB7gYIECBAgAABAgQIhAKCPMQ3TYAAAQIECBAgQECQuwECBAgQIECAAAECoYAgD/FNEyBAgAABAgQIEBDkboAAAQIECBAgQIBAKCDIQ3zTBAgQIECAAAECBAS5GyBAgAABAgQIECAQCgjyEN80AQIECBAgQIAAAUHuBggQIECAAAECBAiEAoI8xDdNgAABAgQIECBAQJC7AQIECBAgQIAAAQKhgCAP8U0TIECAAAECBAgQEORugAABAgQIECBAgEAoIMhDfNMECBAgQIAAAQIEBLkbIECAAAECBAgQIBAKCPIQ3zQBAgQIECBAgAABQe4GCBAgQIAAAQIECIQCgjzEN02AAAECBAgQIEBAkLsBAgQIECBAgAABAqGAIA/xTRMgQIAAAQIECBAQ5G6AAAECBAgQIECAQCggyEN80wQIECBAgAABAgQEuRsgQIAAAQIECBAgEAoI8hDfNAECBAgQIECAAAFB7gYIECBAgAABAgQIhAKCPMQ3TYAAAQIECBAgQECQuwECBAgQIECAAAECoYAgD/FNEyBAgAABAgQIEBDkboAAAQIECBAgQIBAKCDIQ3zTBAgQIECAAAECBAS5GyBAgAABAgQIECAQCgjyEN80AQIECBAgQIAAAUHuBggQIECAAAECBAiEAoI8xDdNgAABAgQIECBAQJC7AQIECBAgQIAAAQKhgCAP8U0TIECAAAECBAgQEORugAABAgQIECBAgEAoIMhDfNMECBAgQIAAAQIEBLkbIECAAAECBAgQIBAKCPIQ3zQBAgQIECBAgAABQe4GCBAgQIAAAQIECIQCgjzEN02AAAECBAgQIEBAkLsBAgQIECBAgAABAqGAIA/xTRMgQIAAAQIECBAQ5G6AAAECBAgQIECAQCggyEN80wQIECBAgAABAgQEuRsgQIAAAQIECBAgEAoI8hDfNAECBAgQIECAAAFB7gYIECBAgAABAgQIhAKCPMQ3TYAAAQIECBAgQECQuwECBAgQIECAAAECoYAgD/FNEyBAgAABAgQIEBDkboAAAQIECBAgQIBAKCDIQ3zTBAgQIECAAAECBAS5GyBAgAABAgQIECAQCgjyEN80AQIECBAgQIAAAUHuBggQIECAAAECBAiEAoI8xDdNgAABAgQIECBAQJC7AQIECBAgQIAAAQKhgCAP8U0TIECAAAECBAgQEORugAABAgQIECBAgEAoIMhDfNMECBAgQIAAAQIEBLkbIECAAAECBAgQIBAKCPIQ3zQBAgQIECBAgAABQe4GCBAgQIAAAQIECIQCgjzEN02AAAECBAgQIEBAkLsBAgQIECBAgAABAqGAIA/xTRMgQIAAAQIECBAQ5G6AAAECBAgQIECAQCggyEN80wQIECBAgAABAgQu34F8H2NQEZYAAAAASUVORK5CYII=";


    public static ArrayList<DoctorsData> fillData() {
        doctors = new ArrayList<>();
        ArrayList<Reviews> reviews = new ArrayList<>();
        reviews.add(new Reviews("https://i.imgur.com/1Z0Z1XH.jpg",
                "John Doe", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed euismod, nisl nec ultricies lacinia, nisl nunc aliquet nisl, eget aliquet nunc nisl eu nunc. Sed euismod, nisl nec ultricies lacinia, nisl nunc aliquet nisl, eget aliquet nunc nisl eu nunc.", "4.5", "2 days ago"));
        reviews.add(new Reviews("https://i.imgur.com/1Z0Z1XH.jpg",
                "John Doe", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed euismod, nisl nec ultricies lacinia, nisl nunc aliquet nisl, eget aliquet nunc nisl eu nunc. Sed euismod, nisl nec ultricies lacinia, nisl nunc aliquet nisl, eget aliquet nunc nisl eu nunc.", "4.5", "2 days ago"));
        reviews.add(new Reviews("https://i.imgur.com/1Z0Z1XH.jpg",
                "John Doe", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed euismod, nisl nec ultricies lacinia, nisl nunc aliquet nisl, eget aliquet nunc nisl eu nunc. Sed euismod, nisl nec ultricies lacinia, nisl nunc aliquet nisl, eget aliquet nunc nisl eu nunc.", "4.5", "2 days ago"));
        reviews.add(new Reviews("https://i.imgur.com/1Z0Z1XH.jpg",
                "John Doe", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed euismod, nisl nec ultricies lacinia, nisl nunc aliquet nisl, eget aliquet nunc nisl eu nunc. Sed euismod, nisl nec ultricies lacinia, nisl nunc aliquet nisl, eget aliquet nunc nisl eu nunc.", "4.5", "2 days ago"));
        reviews.add(new Reviews("https://i.imgur.com/1Z0Z1XH.jpg",
                "John Doe", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed euismod, nisl nec ultricies lacinia, nisl nunc aliquet nisl, eget aliquet nunc nisl eu nunc. Sed euismod, nisl nec ultricies lacinia, nisl nunc aliquet nisl, eget aliquet nunc nisl eu nunc.", "4.5", "2 days ago"));
        reviews.add(new Reviews("https://i.imgur.com/1Z0Z1XH.jpg",
                "John Doe", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed euismod, nisl nec ultricies lacinia, nisl nunc aliquet nisl, eget aliquet nunc nisl eu nunc. Sed euismod, nisl nec ultricies lacinia, nisl nunc aliquet nisl, eget aliquet nunc nisl eu nunc.", "4.5", "2 days ago"));
        reviews.add(new Reviews("https://i.imgur.com/1Z0Z1XH.jpg",
                "John Doe", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed euismod, nisl nec ultricies lacinia, nisl nunc aliquet nisl, eget aliquet nunc nisl eu nunc. Sed euismod, nisl nec ultricies lacinia, nisl nunc aliquet nisl, eget aliquet nunc nisl eu nunc.", "4.5", "2 days ago"));

        ArrayList<String> doctorEmails = new ArrayList<>();
        doctorEmails.add("haider.khan4444@hotmail.com");

//        WorkTime workTime = new WorkTime(new Time(10, 10, 10),
//                new Time(20, 20, 20));


        ArrayList<String> specializations = new ArrayList<>();
        specializations.add("Dentist");
        specializations.add("Surgeon");
        specializations.add("Cardiologist");

        Address address = new Address("House 1", "Street 1", "City 1",
                "Country 1", "Postal Code 1");

        //create a list of doctors
//        doctors.add(new Doctors("1",
//                "Dr. Ahsan",
//                doctorEmails,
//                "male", "03345523523", "519",
//                new WorkTime(),
//                reviews, specializations, 4.3,
//                true, address));
//
//        doctors.add(new Doctors("1",
//                "Dr. Ahsan",
//                doctorEmails,
//                "male", "03345523523", "519",
//                new WorkTime(),
//                reviews, specializations, 4.3,
//                true, address));
//
//        doctors.add(new Doctors("1",
//                "Dr. Ahsan",
//                doctorEmails,
//                "male", "03345523523", "519",
//                new WorkTime(),
//                reviews, specializations, 4.3,
//                true, address));
//
//        doctors.add(new Doctors("1",
//                "Dr. Ahsan",
//                doctorEmails,
//                "male", "03345523523", "519",
//                new WorkTime(),
//                reviews, specializations, 4.3,
//                true, address));
//
//        doctors.add(new Doctors("1",
//                "Dr. Ahsan",
//                doctorEmails,
//                "male", "03345523523", "519",
//                new WorkTime(),
//                reviews, specializations, 4.3,
//                true, address));
//
//        doctors.add(new Doctors("1",
//                "Dr. Ahsan",
//                doctorEmails,
//                "male", "03345523523", "519",
//                new WorkTime(),
//                reviews, specializations, 4.3,
//                true, address));
//
//        doctors.add(new Doctors("1",
//                "Dr. Ahsan",
//                doctorEmails,
//                "male", "03345523523", "519",
//                new WorkTime(),
//                reviews, specializations, 4.3,
//                true, address));
//
//        doctors.add(new Doctors("1",
//                "Dr. Ahsan",
//                doctorEmails,
//                "male", "03345523523", "519",
//                new WorkTime(),
//                reviews, specializations, 4.3,
//                true, address));
//
//        doctors.add(new Doctors("1",
//                "Dr. Ahsan",
//                doctorEmails,
//                "male", "03345523523", "519",
//                new WorkTime(),
//                reviews, specializations, 4.3,
//                true, address));


        return doctors;
    }

//    public static ArrayList<Report> fillReport() {
//        reportList = new ArrayList<>();
//        Date date = new Date(2019, 12, 12);
//        Time time = new Time(12, 12, 12);
//        reportList.add(new Report("123456", "Haider", "Dr jhon", date,
//                time, "lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
//                "Sed euismod, nisl sed lacinia luctus, nisl nisl aliquam mauris, " +
//                "sit amet ultricies nisl nisl eu lectus. Sed euismod, nisl sed lacinia luctus, nisl nisl aliquam mauris, " +
//                "sit amet ultricies nisl nisl eu lectus. Sed euismod, nisl sed lacinia luctus, nisl nisl aliquam mauris, " +
//                "sit amet ultricies nisl nisl eu lectus. Sed euismod, nisl sed lacinia luctus, nisl nisl aliquam mauris, " +
//                "sit amet ultricies nisl nisl eu lectus. Sed euismod, nisl sed lacinia luctus, nisl nisl aliquam mauris, " +
//                "sit amet ultricies nisl nisl eu lectus. Sed euismod, nisl sed lacinia luctus, nisl nisl aliquam mauris, ",
//                BASE64));
//
//        reportList.add(new Report("123456", "Haider", "Dr jhon", date,
//                time, "lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
//                "Sed euismod, nisl sed lacinia luctus, nisl nisl aliquam mauris, " +
//                "sit amet ultricies nisl nisl eu lectus. Sed euismod, nisl sed lacinia luctus, nisl nisl aliquam mauris, " +
//                "sit amet ultricies nisl nisl eu lectus. Sed euismod, nisl sed lacinia luctus, nisl nisl aliquam mauris, " +
//                "sit amet ultricies nisl nisl eu lectus. Sed euismod, nisl sed lacinia luctus, nisl nisl aliquam mauris, " +
//                "sit amet ultricies nisl nisl eu lectus. Sed euismod, nisl sed lacinia luctus, nisl nisl aliquam mauris, " +
//                "sit amet ultricies nisl nisl eu lectus. Sed euismod, nisl sed lacinia luctus, nisl nisl aliquam mauris, ",
//                BASE64));
//
//        reportList.add(new Report("123456", "Haider", "Dr jhon", date,
//                time, "lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
//                "Sed euismod, nisl sed lacinia luctus, nisl nisl aliquam mauris, " +
//                "sit amet ultricies nisl nisl eu lectus. Sed euismod, nisl sed lacinia luctus, nisl nisl aliquam mauris, " +
//                "sit amet ultricies nisl nisl eu lectus. Sed euismod, nisl sed lacinia luctus, nisl nisl aliquam mauris, " +
//                "sit amet ultricies nisl nisl eu lectus. Sed euismod, nisl sed lacinia luctus, nisl nisl aliquam mauris, " +
//                "sit amet ultricies nisl nisl eu lectus. Sed euismod, nisl sed lacinia luctus, nisl nisl aliquam mauris, " +
//                "sit amet ultricies nisl nisl eu lectus. Sed euismod, nisl sed lacinia luctus, nisl nisl aliquam mauris, ",
//                BASE64));
//
//        reportList.add(new Report("123456", "Haider", "Dr jhon", date,
//                time, "lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
//                "Sed euismod, nisl sed lacinia luctus, nisl nisl aliquam mauris, " +
//                "sit amet ultricies nisl nisl eu lectus. Sed euismod, nisl sed lacinia luctus, nisl nisl aliquam mauris, " +
//                "sit amet ultricies nisl nisl eu lectus. Sed euismod, nisl sed lacinia luctus, nisl nisl aliquam mauris, " +
//                "sit amet ultricies nisl nisl eu lectus. Sed euismod, nisl sed lacinia luctus, nisl nisl aliquam mauris, " +
//                "sit amet ultricies nisl nisl eu lectus. Sed euismod, nisl sed lacinia luctus, nisl nisl aliquam mauris, " +
//                "sit amet ultricies nisl nisl eu lectus. Sed euismod, nisl sed lacinia luctus, nisl nisl aliquam mauris, ",
//                BASE64));
//
//        reportList.add(new Report("123456", "Haider", "Dr jhon", date,
//                time, "lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
//                "Sed euismod, nisl sed lacinia luctus, nisl nisl aliquam mauris, " +
//                "sit amet ultricies nisl nisl eu lectus. Sed euismod, nisl sed lacinia luctus, nisl nisl aliquam mauris, " +
//                "sit amet ultricies nisl nisl eu lectus. Sed euismod, nisl sed lacinia luctus, nisl nisl aliquam mauris, " +
//                "sit amet ultricies nisl nisl eu lectus. Sed euismod, nisl sed lacinia luctus, nisl nisl aliquam mauris, " +
//                "sit amet ultricies nisl nisl eu lectus. Sed euismod, nisl sed lacinia luctus, nisl nisl aliquam mauris, " +
//                "sit amet ultricies nisl nisl eu lectus. Sed euismod, nisl sed lacinia luctus, nisl nisl aliquam mauris, ",
//                BASE64));
//
//        reportList.add(new Report("123456", "Haider", "Dr jhon", date,
//                time, "lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
//                "Sed euismod, nisl sed lacinia luctus, nisl nisl aliquam mauris, " +
//                "sit amet ultricies nisl nisl eu lectus. Sed euismod, nisl sed lacinia luctus, nisl nisl aliquam mauris, " +
//                "sit amet ultricies nisl nisl eu lectus. Sed euismod, nisl sed lacinia luctus, nisl nisl aliquam mauris, " +
//                "sit amet ultricies nisl nisl eu lectus. Sed euismod, nisl sed lacinia luctus, nisl nisl aliquam mauris, " +
//                "sit amet ultricies nisl nisl eu lectus. Sed euismod, nisl sed lacinia luctus, nisl nisl aliquam mauris, " +
//                "sit amet ultricies nisl nisl eu lectus. Sed euismod, nisl sed lacinia luctus, nisl nisl aliquam mauris, ",
//                BASE64));
//        return reportList;
//    }

//    public static ArrayList<Medications> fillMedications() {
//        ArrayList<Medications> medicineList = new ArrayList<>();
//
//
//        medicineList.add(new Medications("Bruphen", "250 grams",
//                new Date(12, 12, 2021), new Date(12, 12, 2022), time,
//                new Time(12, 12, 12)));
//
//        medicineList.add(new Medications("Panadol", "250 grams",
//                new Date(12, 12, 2021), new Date(12, 12, 2022), time,
//                new Time(12, 12, 12)));
//
//        medicineList.add(new Medications("Disprin", "250 grams",
//                new Date(12, 12, 2021), new Date(12, 12, 2022), time,
//                new Time(12, 12, 12)));
//
//        medicineList.add(new Medications("Norvasc", "250 grams",
//                new Date(12, 12, 2021), new Date(12, 12, 2022), time,
//                new Time(12, 12, 12)));
//
//        medicineList.add(new Medications("Augmenteen", "250 grams",
//                new Date(12, 12, 2021), new Date(12, 12, 2022), time,
//                new Time(12, 12, 12)));
//
//        medicineList.add(new Medications("Loplat", "250 grams",
//                new Date(12, 12, 2021), new Date(12, 12, 2022), time,
//                new Time(12, 12, 12)));
//
//        medicineList.add(new Medications("Alp", "250 grams",
//                new Date(12, 12, 2021), new Date(12, 12, 2022), time,
//                new Time(12, 12, 12)));
//        return medicineList;
//    }


    public static boolean isAppointmentBooked(Time selectedTime, Date selectedDate) {
        for (Appointment appointment : appointments) {
            if (appointment.getAppointmentDate().equals(selectedDate) &&
                    appointment.getAppointmentTime().equals(selectedTime)) {
                return true;
            }
        }
        return false;
    }
}
