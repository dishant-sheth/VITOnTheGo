package com.example.dishant.vitonthego;

/**
 * Created by dishant on 6/5/17.
 */

public class Complaint {

    public String complaint_title;

    public String getComplaint_title() {
        return complaint_title;
    }

    public void setComplaint_title(String complaint_title) {
        this.complaint_title = complaint_title;
    }

    public String getConcerned_authority() {
        return concerned_authority;
    }

    public void setConcerned_authority(String concerned_authority) {
        this.concerned_authority = concerned_authority;
    }

    public String getComplaint_details() {
        return complaint_details;
    }

    public void setComplaint_details(String complaint_details) {
        this.complaint_details = complaint_details;
    }

    public String getComplaint_intensity() {
        return complaint_intensity;
    }

    public void setComplaint_intensity(String complaint_intensity) {
        this.complaint_intensity = complaint_intensity;
    }

    public String concerned_authority;
    public String complaint_details;
    public String complaint_intensity;

}
