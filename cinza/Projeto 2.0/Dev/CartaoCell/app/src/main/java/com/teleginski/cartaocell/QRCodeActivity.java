package com.teleginski.cartaocell;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

/**
 * Created by Igor on 09/10/2016.
 */
public class QRCodeActivity extends AppCompatActivity {



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode);

        ImageView iv = (ImageView)findViewById(R.id.imageView);
        iv.setImageResource(R.mipmap.static_qr_code_without_logo);
    }
}
