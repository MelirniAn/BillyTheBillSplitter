package com.example.mel.billythebillsplitter;

/**
 * Created by shollabaugh on 4/27/2018.
 */

public class Player {

    protected double total;
    protected String name;

    public Player(String n, double t)  {
        this.total=t;
        this.name=n;
    }

    public double getTotal() {
        return total;
    }

    public String getTotalString()  {
        String r = String.format("%.2f",getTotal());
        return ("$"+r);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public void add(double a) {
        setTotal(a+this.getTotal());
    }

    @Override
    public String toString() {
        return (getName()+" owes "+getTotal());
    }
}
