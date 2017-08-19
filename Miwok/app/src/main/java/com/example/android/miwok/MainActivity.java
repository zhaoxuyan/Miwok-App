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
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set the content of the activity to use the activity_main.xml layout file
        setContentView(R.layout.activity_main);

        /**
         *  Button是TextView的子类
            new View.OnClickListener 是实例化接口onClickListener
            重写onClick(抽象)方法
         */
        TextView numbers = (TextView) findViewById(R.id.numbers);
        // NumbersClickListener clickListener = new NumbersClickListener();
        numbers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, NumbersActivity.class);
                startActivity(intent);
                Toast.makeText(view.getContext(), "open the list of numbers ", Toast.LENGTH_SHORT).show();
            }
        });

        TextView family = (TextView) findViewById(R.id.family);
        family.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, FamilyActivity.class);
                startActivity(intent);
            }
        });

        TextView color = (TextView) findViewById(R.id.colors);
        color.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ColorsActivity.class);
                startActivity(intent);
            }
        });

        TextView phrase = (TextView) findViewById(R.id.phrases);
        phrase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, PhrasesActivity.class);
                startActivity(intent);
            }
        });
    }

//    public void openNumberList(View view) {
//        Intent intent = new Intent(this, NumbersActivity.class);
//        startActivity(intent);
//    }
//
//    public void openFamilyList(View view) {
//        Intent intent = new Intent(this, FamilyActivity.class);
//        startActivity(intent);
//    }
//
//    public void openColorList(View view) {
//        Intent intent = new Intent(this, ColorsActivity.class);
//        startActivity(intent);
//    }
//
//    public void openPhraseList(View view) {
//        Intent intent = new Intent(this, PhrasesActivity.class);
//        startActivity(intent);
//    }
}
