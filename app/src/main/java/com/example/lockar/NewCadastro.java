package com.example.lockar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class NewCadastro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_cadastro);
    }
    public void backtohome(View v){
        Intent it_backtohome = new Intent(this, MainActivity.class);
        startActivity(it_backtohome);
    }
}