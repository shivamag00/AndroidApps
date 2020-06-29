package com.example.pokedex;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;

public class PokemonActivity extends AppCompatActivity {
    private TextView nameTextView;
    private TextView numberTextView;
    private TextView type1TextView;
    private TextView type2TextView;
    private TextView descTextView;

    private String url;
    private String image_url;
    private String desc_url;
    private String name;
    private RequestQueue requestQueue;
    private Catcher preferences;
    Button button;
    private ImageView imageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon);

        button=findViewById(R.id.caught_button);
        name = getIntent().getStringExtra("name");
        preferences = new Catcher(this,name);
        if (preferences.isCaught()) {
            button.setText("Release");
        }
        else
            button.setText("Caught");


        requestQueue = Volley.newRequestQueue(getApplicationContext());
        url = getIntent().getStringExtra("url");


        nameTextView = findViewById(R.id.pokemon_name);
        numberTextView = findViewById(R.id.pokemon_number);
        type1TextView = findViewById(R.id.pokemon_type1);
        type2TextView = findViewById(R.id.pokemon_type2);
        descTextView = findViewById(R.id.pokemon_desc);

        imageView = findViewById(R.id.pokemon_image);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                performLogin();
            }
        });

        load();
    }

    public void load() {
        type1TextView.setText("");
        type2TextView.setText("");
        descTextView.setText("");

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    nameTextView.setText(response.getString("name"));
                    numberTextView.setText(String.format("#%03d", response.getInt("id")));

                    JSONArray typeEntries = response.getJSONArray("types");
                    for (int i = 0; i < typeEntries.length(); i++) {
                        JSONObject typeEntry = typeEntries.getJSONObject(i);
                        int slot = typeEntry.getInt("slot");
                        String type = typeEntry.getJSONObject("type").getString("name");

                        if (slot == 1) {
                            type1TextView.setText(type);
                        }
                        else if (slot == 2) {
                            type2TextView.setText(type);
                        }
                    }

                    JSONObject descEntry = response.getJSONObject("species");
                    desc_url = descEntry.getString("url");

                    JSONObject imageEntry = response.getJSONObject("sprites");
                    image_url = imageEntry.getString("front_default");
                    new DownloadSpriteTask().execute(image_url);
                    load_desc();
                } catch (JSONException e) {
                    Log.e("cs50", "Pokemon json error", e);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("cs50", "Pokemon details error", error);
            }
        });

        requestQueue.add(request);

    }

    public void load_desc() {


        JsonObjectRequest requestdesc = new JsonObjectRequest(Request.Method.GET, desc_url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    descTextView.setText(desc_url);
                    JSONArray descEntries = response.getJSONArray("flavor_text_entries");
                    for (int i = 0; i < 1; i++) {
                        JSONObject descEntry = descEntries.getJSONObject(i);
                        String descs = descEntry.getString("flavor_text");
                        descTextView.setText(descs);
                    }
                } catch (JSONException e) {
                    Log.e("cs50", "Pokemon json error", e);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("cs50", "Pokemon details error", error);
            }
        });

        requestQueue.add(requestdesc);
    }

    private void performLogin() {
        if (preferences.isCaught())
        {
            preferences.setCaught(false);
            button.setText("Caught");
        }
        else
        {
            preferences.setCaught(true);
            button.setText("Release");
        }

    }

    private class DownloadSpriteTask extends AsyncTask<String, Void, Bitmap> {
        @Override
        protected Bitmap doInBackground(String... strings) {
            try {
                URL url = new URL(strings[0]);
                return BitmapFactory.decodeStream(url.openStream());
            }
            catch (IOException e) {
                Log.e("cs50", "Download sprite error", e);
                return null;
            }
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            imageView.setImageBitmap(bitmap);
        }
    }
}
