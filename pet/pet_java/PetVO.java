package com.test.sku.pet;

import java.sql.Date;

public class PetVO {
    private int no;
    private String name;
    private String origin;
    private double weight;
    private java.util.Date birth;
    private int price;
    private String pic;

    // Getters and Setters

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public java.util.Date getBirth() {
        return birth;
    }

    public void setBirth(java.util.Date date) {
        this.birth = date;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    @Override
    public String toString() {
        return "PetVO{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", origin='" + origin + '\'' +
                ", weight=" + weight +
                ", birth=" + birth +
                ", price=" + price +
                ", pic='" + pic + '\'' +
                '}';
    }
}
