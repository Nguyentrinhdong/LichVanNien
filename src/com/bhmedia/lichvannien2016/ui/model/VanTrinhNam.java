package com.bhmedia.lichvannien2016.ui.model;

/**
 * Created by Van on 1/30/2015.
 */
public class VanTrinhNam {
    String id;
    String namSinh;
    String noiDung;

    public VanTrinhNam(String id,String namSinh,String noiDung){
        this.namSinh = namSinh;
        this.noiDung = noiDung;
        this.id = id;
    }

    public VanTrinhNam(){
    
    }
    
    public String getNamSinh() {
        return namSinh;
    }

    public void setNamSinh(String namSinh) {
        this.namSinh = namSinh;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
