/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.miwok;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    private TextView colorsView;
    private TextView familyView;
    private TextView phrasesView;
    private TextView numbersView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        colorsView = findViewById(R.id.colors);
        familyView = findViewById(R.id.family);
        phrasesView =findViewById(R.id.phrases);
        numbersView =findViewById(R.id.numbers);

        colorsView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent colors = new Intent(getApplicationContext(),ColorsActivity.class);
                startActivity(colors);
            }
        });

        familyView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent family = new Intent(getApplicationContext(),FamilyActivity.class);
                startActivity(family);
            }
        });

        numbersView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent numbers = new Intent(getApplicationContext(),NumbersActivity.class);
                startActivity(numbers);
            }
        });

        phrasesView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent phrases = new Intent(getApplicationContext(),PhrasesActivity.class);
                startActivity(phrases);
            }
        });
    }
}
