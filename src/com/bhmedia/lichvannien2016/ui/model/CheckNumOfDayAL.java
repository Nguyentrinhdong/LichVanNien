package com.bhmedia.lichvannien2016.ui.model;

/**
 * Created by Van on 5/14/2015.
 */
public class CheckNumOfDayAL {

    private int month;
    private int year;
    private int dayOfMonth;

    public CheckNumOfDayAL(int _month,int _year, int _dayOfMonth){
        this.month = _month;
        this.year = _year;
        this.dayOfMonth = _dayOfMonth;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getDayOfMonth() {
        return dayOfMonth;
    }

    public void setDayOfMonth(int dayOfMonth) {
        this.dayOfMonth = dayOfMonth;
    }
}
