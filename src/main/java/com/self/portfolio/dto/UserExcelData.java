package com.self.portfolio.dto;


public class UserExcelData {

    private String name;
    private String pan;
    private String mobile;
    private String dob;
    private String turnover;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPan() {
        return pan;
    }

    public void setPan(String pan) {
        this.pan = pan;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getTurnover() {
        return turnover;
    }

    public void setTurnover(String turnover) {
        this.turnover = turnover;
    }

    public UserExcelData(String name, String pan, String mobile, String dob, String turnover) {
        this.name = name;
        this.pan = pan;
        this.mobile = mobile;
        this.dob = dob;
        this.turnover = turnover;
    }

    public UserExcelData() {
    }

    @Override
    public String toString() {
        return "UserExcelData [dob=" + dob + ", mobile=" + mobile + ", name=" + name + ", pan=" + pan + ", turnover="
                + turnover + "]";
    }

}
