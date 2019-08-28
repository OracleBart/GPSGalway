package com.example.galwaycyclegps;

import android.util.Log;

import org.json.JSONObject;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.security.Timestamp;


public class APIConn {

    String currentLng;
    String currentLat;
    String username;
    String code;

    public String getLng(){return currentLng;}

    public void setCurrentLng(String currentLng){

        this.currentLng = currentLng;

    }

    public String getLat(){return currentLat;}


    public void setCurrentLat(String currentLat){

        this.currentLat = currentLat;

    }


    public void setCode(String code){

        this.code = code;

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
  /*             try{
                    URL url2 = new URL("http://132.145.33.66:8080/RESTfulExample/json/product/post");
                    URLConnection con = url2.openConnection();
                    con.connect();

                }
                catch(MalformedURLException e) {
                    e.printStackTrace();
                }
                catch (IOException e) {
                    e.printStackTrace();
                    Log.i("ERROR", "Failed to connect to server");
                    setCode("5");
                }

*/
                try {

                    String input = "{\n" + " \"long\": \"" + getLng() + "\",\n" +
                            "    \"lat\": \"" + getLat() + "\",\n" +
                            "    \"name\": \"" + getusername() + "\"\n" +
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

                    String status = String.valueOf(conn.getResponseCode());

                    Integer respoonseCode = conn.getResponseCode();

                    if (respoonseCode == HttpURLConnection.HTTP_CREATED){
                        Log.i("STATUS0", status);
                    setCode("0");
                     }

/*
                    if (status.equals("201") == true){
                        Log.i("CODE", status);
                        setCode("1");
                    }

                    if (status.equals("201") == false || status == null){
                        Log.i("CODE", status);
                        setCode("0");
                    }
*/

                    //Log.i("STATUSwow", getCode());
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
    public String getCode(){
        return code;}
}

