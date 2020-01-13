package com.example.sih2020.model;

import java.util.Date;

public class PastRecord {

    String serviceDate;
    String serviceMan;
    String description;
    boolean done;
    boolean expanded;


    public boolean isExpanded() {
        return expanded;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }

    public PastRecord(String serviceDate, String serviceMan, String description, boolean isDone) {
        this.serviceDate = serviceDate;
        this.serviceMan = serviceMan;
        this.description = description;
        this.done = isDone;
        this.expanded = false;
    }
    public PastRecord(){}


    public String getServiceDate() {
        return serviceDate;
    }

    public void setServiceDate(String serviceDate) {
        this.serviceDate = serviceDate;
    }

    public String getServiceMan() {
        return serviceMan;
    }



    public void setServiceMan(String serviceMan) {
        this.serviceMan = serviceMan;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

}
