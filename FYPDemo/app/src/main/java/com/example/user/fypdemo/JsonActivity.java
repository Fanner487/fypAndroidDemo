package com.example.user.fypdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonActivity extends AppCompatActivity {

    TextView tvJsonData;
    StringBuilder stringBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json);

        tvJsonData = findViewById(R.id.tvJsonData);
        stringBuilder = new StringBuilder();

        String url = "http://ec2-18-221-233-36.us-east-2.compute.amazonaws.com:8000/attendances/";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        // Process the JSON
                        try {
                            // Loop through the array elements
                            for (int i = 0; i < response.length(); i++) {
                                // Get current json object
                                JSONObject attendance = response.getJSONObject(i);

                                // Get the current student (json object) data
                                String eventName = attendance.getString("event_name");
                                String created = attendance.getString("created");
                                String organiser = attendance.getString("organiser");
                                String scheduled = attendance.getString("scheduled");

                                System.out.println("Event Name: " + eventName);
                                System.out.println("Created: " + created);
                                System.out.println("Organiser: " + organiser);
                                System.out.println("Scheduled: " + scheduled);

                                String result = "";

                                String event = "\n----------------" +
                                        "\nEvent Name: " + eventName +
                                        "\nCreated: " + created +
                                        "\nOrganiser: " + organiser +
                                        "\nScheduled: " + scheduled +
                                        "\n----------------";

                                stringBuilder.append(event);


                            }

                            tvJsonData.setText(stringBuilder.toString());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Do something when error occurred
                        Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
                        error.printStackTrace();
                    }
                }
        );


        // Add request to Request Queue from singleton
        RequestQueueInstance.getInstance(this).addToRequestQueue(jsonArrayRequest);

    }
}
