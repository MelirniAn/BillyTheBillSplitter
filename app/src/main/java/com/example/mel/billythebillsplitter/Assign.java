package com.example.mel.billythebillsplitter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import edmt.dev.androidcamera2api.R;

public class Assign extends AppCompatActivity {

    ArrayList<Double> money = new ArrayList<Double>();
    int counter = 0;
    int max;
    String curr;


    Player p1 = new Player("1",0.0);
    Player p2 = new Player("2",0.0);
    Player p3 = new Player("3",0.0);
    Player p4 = new Player("4",0.0);

    TextView player1;
    TextView player2;
    TextView player3;
    TextView player4;
    TextView current;

    //Bundle bundle = getIntent().getExtras();
    //String bigString;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assign);

        //bigString="2.22 3.22 5.22 1.22";

        //bigString = bundle.getString("rec");
        //parseString(bigString);

        money.add(2.5);
        money.add(5.1);
        money.add(9.22);

        counter = 0;
        max = 3;//money.size();

        player1 = (TextView) findViewById(R.id.totalP1);
        player2 = (TextView) findViewById(R.id.totalP2);
        player3 = (TextView) findViewById(R.id.totalP3);
        player4 = (TextView) findViewById(R.id.totalP4);
        current = (TextView) findViewById(R.id.currentAmount);

/*        //setText();

        Button b1 = (Button) findViewById(R.id.buttonP1);
        Button b2 = (Button) findViewById(R.id.buttonP2);
        Button b3 = (Button) findViewById(R.id.buttonP3);
        Button b4 = (Button) findViewById(R.id.buttonP4);*/


/*        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                p1.add(money.get(counter-1));
                setText();
                if (counter == max+1) {
                    startActivity(new Intent(Assign.this, MainActivity.class));
                }

            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                p2.add(money.get(counter-1));
                setText();
                if (counter == max+1) {
                    startActivity(new Intent(Assign.this, MainActivity.class));
                }

            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                p3.add(money.get(counter-1));
                setText();
                if (counter == max+1) {
                    startActivity(new Intent(Assign.this, MainActivity.class));
                }

            }
        });

        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                p4.add(money.get(counter-1));
                setText();
                if (counter == max+1) {
                    startActivity(new Intent(Assign.this, MainActivity.class));
                }

            }
        });*/
    }


/*    public void setText() {
        Player[] players = {p1, p2, p3, p4};
        TextView[] pTotals = {player1, player2, player3, player4};
        for (int i = 0; i < 4; i++) {
            pTotals[i].setText(players[i].getTotalString());
        }
        if(counter<max)
            curr = ("$" + String.format("%.2f",money.get(counter)));
        else
            curr = "End";
        current.setText(curr);
        counter++;
    }*/

/*    public void parseString(String s)   {
        String[] parts = s.split(" ");
        double d;

        for(int n = 0; n < parts.length; n++) {
            try{
                d = Double.parseDouble(parts[n]);
                money.add(d);
            }
            catch (NumberFormatException e) {
            }
        }
    }*/

}
