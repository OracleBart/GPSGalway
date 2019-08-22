package com.example.galwaycyclegps;

import android.util.Log;

import org.json.JSONObject;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.Timestamp;

public class APIConn {

    String currentLng;
    String currentLat;
    String username;


    public String getLng(){return currentLng;}

    public void setCurrentLng(String currentLng){

        this.currentLng = currentLng;

    }

    public String getLat(){return currentLat;}

    public void setCurrentLat(String currentLat){

        this.currentLat = currentLat;

    }

    public String getusername(){return username;}

    public void setusername(String username){

        this.username = username;

    }

    public void getme(){


        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {





                JSONObject dData = new JSONObject();
              /*
              dData = dataObj(currentLat,currentLng);
              String currentData = String.valueOf(dData);
              */
                JSONObject parent = new JSONObject();
                JSONObject jsonObject = new JSONObject();
                //Get timestamp

                try {

                    String input = "{\n" +" \"long\": \""+getLng()+"\",\n" +
                            "    \"lat\": \""+getLat()+"\",\n" +
                            "    \"name\": \""+getusername()+"\"\n" +
                            "}";

                    jsonObject.put("lat", currentLat);
                    jsonObject.put("lng", currentLng);
                    jsonObject.put("cyclist", username);
                    parent.put("", jsonObject);
                    Log.d("output", parent.toString(2));
                    URL url = new URL("http://132.145.33.66:8080/RESTfulExample/json/product/post");
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                    conn.setRequestProperty("Accept", "application/json");
                    conn.setDoOutput(true);
                    conn.setDoInput(true);

                    DataOutputStream os = new DataOutputStream(conn.getOutputStream());
                    // os.writeBytes(URLEncoder.encode(parent.toString(2), "UTF-8"));
                    os.writeBytes(input);

                    os.flush();
                    os.close();

                    Log.i("STATUS", String.valueOf(conn.getResponseCode()));
                    Log.i("MSG", conn.getResponseMessage());

                    conn.disconnect();
                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        });

        thread.start();
    }
}

