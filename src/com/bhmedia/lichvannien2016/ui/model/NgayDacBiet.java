package com.bhmedia.lichvannien2016.ui.model;

/**
 * Created by Van on 3/2/2015.
 */
public class NgayDacBiet {
    private String dayDuong;
    private String monthDuong;
    private String yearDuong;
    private String dayAm;
    private String monthAm;
    private String yearAm;

    public NgayDacBiet(String _dayDuong,String _monthDuong,String _yearDuong){
        this.dayDuong = _dayDuong;
        this.monthDuong = _monthDuong;
        this.yearDuong = _yearDuong;
    }
    public boolean NgayDacBietDuong(String _dayDuong,String _monthDuong,String _yearDuong){
        this.dayDuong= _dayDuong;
        this.monthDuong = _monthDuong;
        this.yearDuong =_yearDuong;
        return true;
    }

    public boolean NgayDacBietAm(String _dayAm,String _monthAm,String _yearAm){
        this.dayAm = _dayAm;
        this.monthAm = _monthAm;
        this.yearAm =_yearAm;
        return true;
    }
    public String getDayDuong() {
        return dayDuong;
    }

    public void setDayDuong(String dayDuong) {
        this.dayDuong = dayDuong;
    }

    public String getMonthDuong() {
        return monthDuong;
    }

    public void setMonthDuong(String monthDuong) {
        this.monthDuong = monthDuong;
    }

    public String getYearDuong() {
        return yearDuong;
    }

    public void setYearDuong(String yearDuong) {
        this.yearDuong = yearDuong;
    }

    public String getDayAm() {
        return dayAm;
    }

    public void setDayAm(String dayAm) {
        this.dayAm = dayAm;
    }

    public String getMonthAm() {
        return monthAm;
    }

    public void setMonthAm(String monthAm) {
        this.monthAm = monthAm;
    }

    public String getYearAm() {
        return yearAm;
    }

    public void setYearAm(String yearAm) {
        this.yearAm = yearAm;
    }
}
