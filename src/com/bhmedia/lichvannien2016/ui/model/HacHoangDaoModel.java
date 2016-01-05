package com.bhmedia.lichvannien2016.ui.model;

/**
 * Created by Van on 1/23/2015.
 */
public class HacHoangDaoModel {
    private String hacDao;
    private String hoangDao;
    private String thang;

    public HacHoangDaoModel(String thang, String hoangDao, String hacDao) {
        this.thang = thang;
        this.hoangDao = hoangDao;
        this.hacDao = hacDao;
    }

    public String getHacDao() {
        return hacDao;
    }

    public void setHacDao(String hacDao) {
        this.hacDao = hacDao;
    }

    public String getHoangDao() {
        return hoangDao;
    }

    public void setHoangDao(String hoangDao) {
        this.hoangDao = hoangDao;
    }

    public String getThang() {
        return thang;
    }

    public void setThang(String thang) {
        this.thang = thang;
    }
}
