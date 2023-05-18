package com.example.lockar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class agendamentos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agendamentos);
    }

    public void VoltarHome(View v){
        Intent home = new Intent(this, MainActivity.class );
        startActivity(home);
    }

    public void NewAgendamento(View v){
        Intent newAgend = new Intent(this, NewAgendamento.class );
        startActivity(newAgend);
    }
}