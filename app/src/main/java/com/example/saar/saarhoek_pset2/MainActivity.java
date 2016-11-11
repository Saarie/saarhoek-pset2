package com.example.saar.saarhoek_pset2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void toForm (View view) {
        Intent goToForm = new Intent(this, WordInsertActivity.class);
        startActivity(goToForm);
    }
}
