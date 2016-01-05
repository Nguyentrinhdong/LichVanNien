package com.bhmedia.lichvannien2016.ui.model;

/**
 * Created by Van on 4/21/2015.
 */
public class TinhThanh {
    private int id;
    private String name;
    private String latlong;

    public TinhThanh(int _id, String _name, String _latlong){
        this.id = _id;
        this.name = _name;
        this.latlong = _latlong;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLatlong() {
        return latlong;
    }

    public void setLatlong(String latlong) {
        this.latlong = latlong;
    }
}
