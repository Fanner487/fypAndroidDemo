package com.example.user.fypdemo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    EditText text;
    Button btnQrCode;
    Button btnGetJson;
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text = findViewById(R.id.text);
        btnQrCode = findViewById(R.id.gen_btn);
        image = findViewById(R.id.imageQrCode);
        btnGetJson = findViewById(R.id.btnGetJson);

        btnQrCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
                String encodeMessage = text.getText().toString().trim();

                try {

                    int width = 200;
                    int height = 200;

                    BitMatrix bitMatrix = multiFormatWriter.encode(encodeMessage, BarcodeFormat.QR_CODE, width, height);
                    BarcodeEncoder encoder = new BarcodeEncoder();
                    Bitmap bitmap = encoder.createBitmap(bitMatrix);

                    image.setImageBitmap(bitmap);
                } catch (WriterException e) {
                    e.printStackTrace();
                }
            }
        });

        btnGetJson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), JsonActivity.class);
                startActivity(intent);
            }
        });
    }
}
