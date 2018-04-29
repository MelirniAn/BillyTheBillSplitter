package com.example.mel.billythebillsplitter;

import java.util.ArrayList;

public class Parser extends act2{
    //Data
    private StringBuilder results;
    private ArrayList<Double> cost = new ArrayList<>();

    //Constructor
    public Parser(StringBuilder results){
        this.results = results;
    }


    //methods

    /*
        The makeList()method will go through the long String returned by Google Visions and parse through it.
        First it will throwaway the non numbers, non decimals, and non \n. Once we cut it down to the information
        that we need, we will then begin parsing through to add onto our "cost" ArrayList.
     */
    public void makeList(){

        CharSequence readThru = results;
        StringBuilder sb = new StringBuilder();

        //throwaway StringBuilder
        StringBuilder dump =  new StringBuilder();
        ArrayList<StringBuilder> inStringForm = new ArrayList<>();

        //take results and remove any non digits or decimals
        for(int i = 0; i < readThru.length(); i++){
            final char c = readThru.charAt(i);

            //If character is 0-9 or . or \n
            if((c>47 && c <58) || c == 46 || c == 10){
                sb.append(c);
            }
        }//now sb should only read something like "12.34\n56.78\n91.23"

        //Now to parse through sb
        for(int c = 0; c < sb.length(); c++){

            //if \n, add to  StringBuilder("dump") to ArrayList(cost) then destroy the throwaway
            if(sb.codePointAt(c) != 10){
                dump.append(c);
            }
            else{
                inStringForm.add(dump);
                dump.delete(0,dump.length()-1);
                }
            }

        //Convert the Strings to Doubles
        for(int a = 0; a < inStringForm.size(); a++){
            cost.add(Double.parseDouble(inStringForm.get(a).toString()));
        }

    }//End of method

    public ArrayList<Double> getCost(){
        return cost;
    }

}


