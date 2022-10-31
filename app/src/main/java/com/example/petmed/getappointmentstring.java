package com.example.petmed;

public class getappointmentstring {




    String name;
    String type;
    String reason;
    String symptoms;
    String date;
    String time;


    String uid;


    public getappointmentstring(String name, String type, String reason, String symptoms, String date, String time,String uid) {
        this.name = name;
        this.date = date;
        this.type = type;
        this.reason = reason;
        this.symptoms = symptoms;
        this.time = time;
        this.uid = uid;

    }


    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getReason() {
        return reason;
    }

    public String getSymptoms() {
        return symptoms;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getUid() {
        return uid;
    }



}
