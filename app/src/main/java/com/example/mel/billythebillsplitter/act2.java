package com.example.mel.billythebillsplitter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.text.TextBlock;
import com.google.android.gms.vision.text.TextRecognizer;

import java.io.File;

import edmt.dev.androidcamera2api.R;

import static android.os.Environment.getExternalStorageDirectory;

public class act2 extends AppCompatActivity {

    ImageView i1;
    TextView t1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act2);

        i1 = (ImageView) findViewById(R.id.imageView);
        t1 = (TextView) findViewById(R.id.text_value);



    }

    public void getTextFromImage(View v)    {
//        Bitmap bitmap = BitmapFactory.decodeResource(getApplicationContext().getResources(), R.mipmap.rec);

        File imgFile = new File(Environment.getExternalStorageDirectory()+"/"+ "receiptPhoto" +".jpg");

        Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());

        if(imgFile.exists()){

            ImageView myImage = (ImageView) findViewById(R.id.imageView);

            myImage.setImageBitmap(myBitmap);

        }


        TextRecognizer textRecognizer = new TextRecognizer.Builder(getApplicationContext()).build();

        if(!textRecognizer.isOperational()) {
            Toast.makeText(getApplicationContext(),"Could not get Text",Toast.LENGTH_SHORT).show();
        }
        else{
            Frame frame = new Frame.Builder().setBitmap(myBitmap).build();

            SparseArray<TextBlock> items = textRecognizer.detect(frame);

            StringBuilder sb = new StringBuilder();

            for(int i=0; i<items.size();++i)    {
                TextBlock myItem = items.valueAt(i);
                sb.append(myItem.getValue());
                sb.append("\n");
            }

            t1.setText(sb.toString());
        }
    }

    //
}
