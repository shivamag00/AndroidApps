package com.example.weatherreport;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;

public class QueryUtils {

    private static final String LOG_TAG = "Network" ;
    private static final String API_KEY = "547c40b7111b0072aedabd5e13422b0b";
    private static String location = "Surat";
    private static final String OWM_REQUEST_URL = "api.openweathermap.org/data/2.5/weather?q="+location+"&appid="+API_KEY ;
    private static String JSONresponse;
    private static String temp;
    private static String city;

    /**
     * Create a private constructor because no one should ever create a {@link QueryUtils} object.
     * This class is only meant to hold static variables and methods, which can be accessed
     * directly from the class name QueryUtils (and an object instance of QueryUtils is not needed).
     */
    private QueryUtils(){
    }

    private static URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Problem building the URL ", e);
        }
        return url;
    }
    private static String makeHttpRequest() throws IOException {
        URL url = createUrl(OWM_REQUEST_URL);
        String jsonResponse = "";
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.connect();

            // If the request was successful (response code 200),
            // then read the input stream and parse the response.
            if (urlConnection.getResponseCode() ==  200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            }
            else
            {
                Log.e(LOG_TAG, "Error response code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            // TODO: Handle the exception
            Log.e(LOG_TAG, "Problem retrieving the weather JSON results.", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                // Closing the input stream could throw an IOException, which is why
                // the makeHttpRequest(URL url) method signature specifies that an IOException
                // could be thrown.
                inputStream.close();
            }
        }
        return jsonResponse;
    }
    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }
    //Parse inputStream
    private static Weather extractWeatherfromJSON(String responseFromAPI){
        try {
            JSONObject root = new JSONObject(responseFromAPI);
            city = root.optString("name");

            JSONObject main = root.getJSONObject("main");
            temp = main.optString("temp");
        }
        catch (JSONException e) {
            Log.e(LOG_TAG, "Error Parsing JSON");
        }

        return new Weather(city,temp);
    }

    public static Weather fetchWeather() {
        createUrl(OWM_REQUEST_URL);
        try {
            JSONresponse = makeHttpRequest();
        }
        catch (IOException e) {
            Log.e(LOG_TAG,"Error Fetching Response");
        }
        return extractWeatherfromJSON(JSONresponse);
    }
}