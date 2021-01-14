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

        tagLabelText.mSetText("SỢ RẰNG EM BIẾT ANH CÒN YÊU EM (Afraid You Know I’m Still In Love) (OFFICIAL MV)");
        tagLabelText.setTagText("JUUN D");
    }
}