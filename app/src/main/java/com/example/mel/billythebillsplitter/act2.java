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

    ImageView i1;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act2);

        i1 = (ImageView) findViewById(R.id.imageView);
        textView = (TextView) findViewById(R.id.text_value);




    }

    //onClickListener to move to the Picker class

  //  protected StringBuilder sb = new StringBuilder();

    private void configureNextButton(){
        Button nextButton = (Button) findViewById(R.id.nextButton);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(act2.this,Picker.class));
            }
        });

    }

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


            configureNextButton();
        }


       // myBitmap.recycle();
    }

//    public StringBuilder getResults(){
//        return sb;
//    }





}
