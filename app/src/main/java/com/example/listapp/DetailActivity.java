package com.example.listapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.widget.ImageView;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        int index = intent.getIntExtra("com.example.listapp.ITEM_INDEX", -1);

        if (index > -1) {
            int pic = getImage(index);
            ImageView view = findViewById(R.id.imageView);

            scaleImage(view, pic);
        }
    }

    private int getImage(int index) {
        switch (index) {
            case 0: return R.drawable.peachs_1327003;
            case 1: return R.drawable.tomato_1485233;
            case 2: return R.drawable.large_gourd_1173051_1919x1284;
            default: return -1;
        }
    }

    private void scaleImage(ImageView img, int pic) {
        Display screen = getWindowManager().getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        BitmapFactory.Options options = new BitmapFactory.Options();

        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(getResources(), pic, options);
        screen.getMetrics(metrics);

        int imgWidth = options.outWidth;
        int screenWidth = metrics.widthPixels;

        if (imgWidth > screenWidth) {

            options.inSampleSize = Math.round( (float)imgWidth / screenWidth);
        }
        options.inJustDecodeBounds = false;

        Bitmap scaledImage = BitmapFactory.decodeResource(getResources(), pic, options);

        img.setImageBitmap(scaledImage);
    }
}