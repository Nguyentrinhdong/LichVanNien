package com.bhmedia.lichvannien2016.ui.model;

/**
 * Created by Van on 4/13/2015.
 */
public class Model {
    private int id;
    private String name;

    public Model(int _id, String _name){
        this.id = _id;
        this.name = _name;
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
}
