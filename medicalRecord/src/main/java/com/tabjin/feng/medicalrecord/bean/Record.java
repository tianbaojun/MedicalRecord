package com.tabjin.feng.medicalrecord.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/2/10.
 */
public class Record  implements Serializable {

    private int id = 0;
    private String patientId = "";
    private String diagnosis = "",states = "",labels = "";

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getStates() {
        return states;
    }

    public void setStates(String states) {
        this.states = states;
    }

    public String getLabels() {
        return labels;
    }

    public void setLabels(String labels) {
        this.labels = labels;
    }
}
