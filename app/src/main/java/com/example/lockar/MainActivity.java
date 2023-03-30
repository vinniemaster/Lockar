package com.example.lockar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void NewCadastro(View v){
        Intent it_newcadastro = new Intent(this, NewCadastro.class );
        startActivity(it_newcadastro);
    }
}