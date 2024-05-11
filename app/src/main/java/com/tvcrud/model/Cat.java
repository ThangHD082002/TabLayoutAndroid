package com.tvcrud.model;

public class Cat {
    private int img;
    private String name;
    private double price;
    private String infor;

    public Cat(int img, String name, double price, String infor) {
        this.img = img;
        this.name = name;
        this.price = price;
        this.infor = infor;
    }

    public Cat() {
    }

    public void setImg(int img) {
        this.img = img;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setInfor(String infor) {
        this.infor = infor;
    }

    public int getImg() {
        return img;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getInfor() {
        return infor;
    }
}
