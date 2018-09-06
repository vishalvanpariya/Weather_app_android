package com.example.vishal.whether_app_for_android.whether_data;

public interface WhetherServiceCallback {
    void servicesuccess(String location);
    void servicefailure(Exception exception);
}
