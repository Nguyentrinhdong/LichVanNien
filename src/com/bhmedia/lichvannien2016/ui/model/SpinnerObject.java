package com.bhmedia.lichvannien2016.ui.model;

import android.widget.Spinner;

/**
 * Created by Van on 3/11/2015.
 */
public class SpinnerObject {
    private String name;
    private int time;

    public SpinnerObject(String _name, int _time) {
        this.name = _name;
        this.time = _time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}
