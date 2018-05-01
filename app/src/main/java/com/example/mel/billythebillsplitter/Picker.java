package com.example.mel.billythebillsplitter;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.text.TextBlock;
import com.google.android.gms.vision.text.TextRecognizer;

import java.lang.reflect.Array;
import java.util.ArrayList;

import edmt.dev.androidcamera2api.R;

public class Picker extends AppCompatActivity {

    ArrayList<Double> money = new ArrayList<Double>();  //Lots of variables declared on this activity. All unprotected because the validation is through the GUI.
    int counter = 0;
    int max;
    String curr;
    double tipOld = 0;

    Player p1 = new Player("1",0.0);    //set up the players
    Player p2 = new Player("2",0.0);
    Player p3 = new Player("3",0.0);
    Player p4 = new Player("4",0.0);
    Player taxPlayer = new Player("tax", 0.0);

    TextView player1;   //set up the text views
    TextView player2;
    TextView player3;
    TextView player4;
    TextView current;
    TextView tax;
    TextView tip;
    SeekBar slider;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picker);


        Bundle bundle = getIntent().getExtras();

        String sb = bundle.getString("resultsData");    //pull in the google vision string

        //Using the Parser Class
        Parser moneyString = new Parser(sb);    //parse the string into an arraylist
        moneyString.makeList();
        money = moneyString.getCost();

//    money.add(12.50);
//    money.add(7.50);
//    money.add(10.50);


        counter = 0;
        //once the totals are all computer and assigned, goes to next screen
        max = money.size();

        player1 = (TextView) findViewById(R.id.totalP1);    //initialize the textViews and Buttons
        player2 = (TextView) findViewById(R.id.totalP2);
        player3 = (TextView) findViewById(R.id.totalP3);
        player4 = (TextView) findViewById(R.id.totalP4);
        current = (TextView) findViewById(R.id.currentAmount);
        tax = (TextView) findViewById(R.id.tax);
        tip = (TextView) findViewById(R.id.tip);

        Button b1 = (Button) findViewById(R.id.buttonP1);
        Button b2 = (Button) findViewById(R.id.buttonP2);
        Button b3 = (Button) findViewById(R.id.buttonP3);
        Button b4 = (Button) findViewById(R.id.buttonP4);
        Button taxButton = (Button) findViewById(R.id.buttonTax);
        Button delButton = (Button) findViewById(R.id.delete);
        Button doneButton = (Button) findViewById(R.id.Done);
        slider = (SeekBar) findViewById(R.id.tipSlider);

        setText();  //set all text boxes.


        /**
         * Slider to calulate tip
         */
        slider.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progressValue = 15;
            double tipAmount;

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressValue = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                tipAmount = (subtotal()*slider.getProgress()/100);
                tip.setText("Tip "+slider.getProgress()+ "%\t$"+String.format("%.2f",tipAmount));
                tipOld=tipAmount;
            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(counter<max+1) {                     //if there are still numbers unassigned, add that number to this player.
                    p1.add(money.get(counter - 1));
                    setText();
                }
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(counter<max+1) {
                    p2.add(money.get(counter - 1));
                    setText();
                }
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(counter<max+1) {
                    p3.add(money.get(counter - 1));
                    setText();
                }

            }
        });

        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(counter<max+1) {
                    p4.add(money.get(counter - 1));
                    setText();
                }
            }
        });

        taxButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(counter<max+1) {
                    taxPlayer.add(money.get(counter - 1));
                    setText();
                }
            }
        });

        delButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setText();
            }
        });

        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (taxPlayer.getTotal()==0)
                startActivity(new Intent(Picker.this, MainActivity.class));
                else {
                    Player[] players = {p1, p2, p3, p4};
                    for (int i = 0; i < 4; i++) {
                        players[i].add(taxPlayer.getTotal()*players[i].getTotal()/subtotal());  //evenly divide tax and tip based on %spent per person.
                        players[i].add(tipOld*players[i].getTotal()/subtotal());
                        slider.setProgress(0);
                    }
                    taxPlayer.setTotal(0.0);
                    setText();

                }

            }
        });
    }

    /**
     * setText will update all text fields on the screen
     */
    public void setText() {
        Player[] players = {p1, p2, p3, p4};
        TextView[] pTotals = {player1, player2, player3, player4};
        for (int i = 0; i < 4; i++) {
            pTotals[i].setText(players[i].getTotalString());
        }
        tax.setText(taxPlayer.getTotalString());
        double tipAmount = (subtotal()*slider.getProgress()/100);
        tip.setText("Tip "+slider.getProgress()+ "%\t$"+String.format("%.2f",tipAmount));
        if(counter<max) {
            curr = ("$" + String.format("%.2f", money.get(counter)));
        }
        else
            curr = "End";
        counter++;
        current.setText("Current amount: "+curr);
    }

    /**
     * subTotal sums all of the values on the screen.
     * @return subtotal
     */
    public double subtotal()   {
        Player[] players = {p1, p2, p3, p4};
        double st=0;
        for (int i = 0; i < 4; i++) {
            st+=players[i].getTotal();
        }
        st+=taxPlayer.getTotal();
        return st;
    }
}

