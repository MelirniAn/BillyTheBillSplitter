package com.example.mel.billythebillsplitter;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.text.TextBlock;
import com.google.android.gms.vision.text.TextRecognizer;

import java.io.File;

import edmt.dev.androidcamera2api.R;

public class act2 extends AppCompatActivity {

    ImageView i1;           //Declare an imageview and TextView. Outside of methods to carry over inbetween methods.
    TextView textView;

    /**
     * When the screen is started, intialize the text and image classes with those identified in the .xml file.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act2);

        i1 = (ImageView) findViewById(R.id.imageView);          //initialize the ImageView and TextView as the one indentified in the .xml
        textView = (TextView) findViewById(R.id.text_value);





    }

    //onClickListener to move to the Picker class





    private String carry;   //Declare a variable outside of methods to carry the string from Act2 to Picker class.


    /**
     * When the button is clicked, this method will read the text using google vision, and separate it into lines.
     * It will then put those lines into a string and display it on screen as confirmation that it was read.
     * It will set the carry variable so that the next activity can pull the string into it.
     * @param v
     */
    public void getTextFromImage(View v)    {


        File imgFile = new File(Environment.getExternalStorageDirectory()+"/"+ "receiptPhoto" +".jpg");

        Bitmap myBitmap = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory()+"/"+ "receiptPhoto" +".jpg");

        if(imgFile.exists()){

            ImageView myImage = (ImageView) findViewById(R.id.imageView);

            myImage.setImageBitmap(myBitmap);

        }



        TextRecognizer textRecognizer = new TextRecognizer.Builder(getApplicationContext()).build();

        StringBuilder sb = new StringBuilder();

        if(!textRecognizer.isOperational()) {
            Toast.makeText(getApplicationContext(),"Could not get Text",Toast.LENGTH_SHORT).show();
        }
        else{
            Frame frame = new Frame.Builder().setBitmap(myBitmap).build();

            SparseArray<TextBlock> items = textRecognizer.detect(frame);


            for(int i=0; i<items.size();++i)    {
                TextBlock myItem = items.valueAt(i);
                sb.append(myItem.getValue());
                sb.append("\n");
            }

            textView.setText(sb.toString());

            carry = sb.toString();


            configureNextButton();
        }


       // myBitmap.recycle();
    }

    /**
     * Method to get the results from google vision
     * @return the String built from google vision
     */
    public String getResults(){
        return carry;
    }

    /**
     * Go to the next activity and carry the string with it.
     */
    private void configureNextButton(){
        Button nextButton = (Button) findViewById(R.id.nextButton);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(act2.this, Picker.class);

                String x = getResults();


                Bundle bundle = new Bundle();

                bundle.putString("resultsData", x);

                i.putExtras(bundle);

                startActivity(i);
               // startActivity(new Intent(act2.this,Picker.class));
            }
        });

    }




}
