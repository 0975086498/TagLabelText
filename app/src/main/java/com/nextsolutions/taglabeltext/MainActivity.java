package com.nextsolutions.taglabeltext;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.nextsolutions.myapplication.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TagLabelText tagLabelText = findViewById(R.id.tagLabel);

        tagLabelText.mSetText("LẠC TRÔI | OFFICIAL MUSIC VIDEO | SƠN TÙNG M-TP");
        tagLabelText.tagText = "Sơn Tùng M-TP";
        tagLabelText.setSpan();//apply properties of tagLabelText
    }
}