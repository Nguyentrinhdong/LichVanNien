package com.bhmedia.lichvannien2016.ui.model;

/**
 * Created by Van on 1/29/2015.
 */
public class Sukien {
    String id;
    String sukien;
    String diadiem;
    String chitiet;
    int time,spinner;
    int day_begin, month_begin, year_begin,hour_begin,minute_begin;
    int day_end, month_end, year_end,hour_end,minute_end;
    int day_show_dialog, month_show_dialog, year_show_dialog;
    public Sukien(){

    }

    public Sukien(String _id, String _sukien, String _diadiem, String _chitiet, int _day_begin, int _month_begin, int _year_begin,
                  int _hour_begin, int _minute_begin,int _day_end, int _month_end, int _year_end,int _hour_end,int _minute_end) {
        this.id = _id;
        this.sukien = _sukien;
        this.day_begin = _day_begin;
        this.month_begin = _month_begin;
        this.year_begin = _year_begin;
        this.hour_begin = _hour_begin;
        this.minute_begin = _minute_begin;
        this.day_end = _day_end;
        this.month_end = _month_end;
        this.year_end = _year_end;
        this.hour_end = _hour_end;
        this.minute_end = _minute_end;
        this.diadiem = _diadiem;
        this.chitiet = _chitiet;
    }

    public Sukien(String _id, String _sukien,String _diadiem, String _chitiet, int _day_begin, int _month_begin, int _year_begin,int _hour_begin, int _minute_begin,
                  int _day_end, int _month_end, int _year_end,int _hour_end,int _minute_end, int day_show_dialog,
                  int month_show_dialog, int year_show_dialog,int spinner,int time) {
        this.id = _id;
        this.sukien = _sukien;
        this.diadiem = _diadiem;
        this.chitiet = _chitiet;
        this.day_begin = _day_begin;
        this.month_begin = _month_begin;
        this.year_begin = _year_begin;
        this.hour_begin = _hour_begin;
        this.minute_begin = _minute_begin;
        this.day_end = _day_end;
        this.month_end = _month_end;
        this.year_end = _year_end;
        this.hour_end = _hour_end;
        this.minute_end = _minute_end;
        this.day_show_dialog = day_show_dialog;
        this.month_show_dialog = month_show_dialog;
        this.year_show_dialog = year_show_dialog;
        this.spinner = spinner;
        this.time = time;
    }

    public String getDiadiem() {
        return diadiem;
    }

    public void setDiadiem(String diadiem) {
        this.diadiem = diadiem;
    }

    public String getChitiet() {
        return chitiet;
    }

    public void setChitiet(String chitiet) {
        this.chitiet = chitiet;
    }

    public int getDay_show_dialog() {
        return day_show_dialog;
    }

    public void setDay_show_dialog(int day_show_dialog) {
        this.day_show_dialog = day_show_dialog;
    }

    public int getMonth_show_dialog() {
        return month_show_dialog;
    }

    public void setMonth_show_dialog(int month_show_dialog) {
        this.month_show_dialog = month_show_dialog;
    }

    public int getYear_show_dialog() {
        return year_show_dialog;
    }

    public void setYear_show_dialog(int year_show_dialog) {
        this.year_show_dialog = year_show_dialog;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSukien() {
        return sukien;
    }

    public void setSukien(String sukien) {
        this.sukien = sukien;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getDay_begin() {
        return day_begin;
    }

    public void setDay_begin(int day_begin) {
        this.day_begin = day_begin;
    }

    public int getMonth_begin() {
        return month_begin;
    }

    public void setMonth_begin(int month_begin) {
        this.month_begin = month_begin;
    }

    public int getYear_begin() {
        return year_begin;
    }

    public void setYear_begin(int year_begin) {
        this.year_begin = year_begin;
    }

    public int getDay_end() {
        return day_end;
    }

    public void setDay_end(int day_end) {
        this.day_end = day_end;
    }

    public int getMonth_end() {
        return month_end;
    }

    public void setMonth_end(int month_end) {
        this.month_end = month_end;
    }

    public int getYear_end() {
        return year_end;
    }

    public void setYear_end(int year_end) {
        this.year_end = year_end;
    }

    public int getHour_begin() {
        return hour_begin;
    }

    public void setHour_begin(int hour_begin) {
        this.hour_begin = hour_begin;
    }

    public int getMinute_begin() {
        return minute_begin;
    }

    public void setMinute_begin(int minute_begin) {
        this.minute_begin = minute_begin;
    }

    public int getHour_end() {
        return hour_end;
    }

    public void setHour_end(int hour_end) {
        this.hour_end = hour_end;
    }

    public int getMinute_end() {
        return minute_end;
    }

    public void setMinute_end(int minute_end) {
        this.minute_end = minute_end;
    }

    public int getSpinner() {
        return spinner;
    }

    public void setSpinner(int spinner) {
        this.spinner = spinner;
    }
}
