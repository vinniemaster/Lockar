package com.example.lockar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.lockar.Activities.Agendamentos.agendamentos;
import com.example.lockar.Activities.Cadastros.ListarCadastros;
import com.example.lockar.Activities.Cadastros.NewCadastro;

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

    public void ListarCadastros(View v){
        Intent it_list_cadastros = new Intent(this, ListarCadastros.class );
        startActivity(it_list_cadastros);
    }

    public void ListarAgendamentos(View v){
        Intent it_list_cadastros = new Intent(this, agendamentos.class );
        startActivity(it_list_cadastros);
    }
}