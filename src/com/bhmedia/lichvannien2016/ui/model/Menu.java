package com.bhmedia.lichvannien2016.ui.model;

/**
 * Created by Van on 3/10/2015.
 */
public class Menu {
    private int id;
    private int imgMenu;
    private String nameMenu;

    public Menu(int imgMenu,String nameMenu,int id){
        this.id = id;
        this.imgMenu = imgMenu;
        this.nameMenu = nameMenu;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getImgMenu() {
        return imgMenu;
    }

    public void setImgMenu(int imgMenu) {
        this.imgMenu = imgMenu;
    }

    public String getNameMenu() {
        return nameMenu;
    }

    public void setNameMenu(String nameMenu) {
        this.nameMenu = nameMenu;
    }
}
