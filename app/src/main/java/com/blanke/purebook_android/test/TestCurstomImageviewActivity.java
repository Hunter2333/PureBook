package com.blanke.purebook_android.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.blanke.purebook_android.R;

public class TestCurstomImageviewActivity extends AppCompatActivity {

    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_imageview);
//        imageView = (ImageView) findViewById(R.id.activity_test_image);

    }
}
