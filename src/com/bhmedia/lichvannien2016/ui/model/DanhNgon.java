package com.bhmedia.lichvannien2016.ui.model;

/**
 * Created by Van on 1/29/2015.
 */
public class DanhNgon {
    String id;
    String title;
    String tacgia;

    public DanhNgon(String id,String title,String tacgia){
        this.id = id;
        this.title = title;
        this.tacgia = tacgia;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTacgia() {
        return tacgia;
    }

    public void setTacgia(String tacgia) {
        this.tacgia = tacgia;
    }
}
