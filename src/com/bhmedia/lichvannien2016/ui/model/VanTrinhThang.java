package com.bhmedia.lichvannien2016.ui.model;

/**
 * Created by Van on 1/30/2015.
 */
public class VanTrinhThang {
    int id;
    String thangAm;
    String tuoiAm;
    String noiDung;
    public VanTrinhThang(int _id,String _thangAm,String _tuoiAm,String _noiDung){
        this.id = _id;
        this.thangAm = _thangAm;
        this.tuoiAm = _tuoiAm;
        this.noiDung = _noiDung;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getThangAm() {
        return thangAm;
    }

    public void setThangAm(String thangAm) {
        this.thangAm = thangAm;
    }

    public String getTuoiAm() {
        return tuoiAm;
    }

    public void setTuoiAm(String tuoiAm) {
        this.tuoiAm = tuoiAm;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }
}
