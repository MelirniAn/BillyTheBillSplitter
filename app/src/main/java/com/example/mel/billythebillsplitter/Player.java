package com.example.mel.billythebillsplitter;

/**
 * Created by shollabaugh on 4/27/2018.
 */

public class Player {

    protected double total;
    protected String name;      //name field to be entered once venmo capabilities are added.

    /**
     * Builds a player object
     * @param n the name
     * @param t the total amount this player owes
     */
    public Player(String n, double t)  {
        this.total=t;
        this.name=n;
    }

    /**
     * get the amount the player owes
     * @return total
     */
    public double getTotal() {
        return total;
    }

    /**
     * convert the total into a formatted string
     * @return the total in $x.xx format
     */
    public String getTotalString()  {
        String r = String.format("%.2f",getTotal());    //format to two decimal places
        return ("$"+r);
    }

    /**
     * get the name of the player
     * @return the name of the player
     */
    public String getName() {
        return name;
    }

    /**
     * set the name of the player
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * set the total owed
     * @param total
     */
    public void setTotal(double total) {
        this.total = total;
    }

    /**
     * update the total
     * @param a
     */
    public void add(double a) {
        setTotal(a+this.getTotal());
    }

    /**
     * print the name and how much the player owes.
     * @return
     */
    @Override
    public String toString() {
        return (getName()+" owes "+getTotal());
    }
}
