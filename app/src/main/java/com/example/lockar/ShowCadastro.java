package com.example.lockar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class ShowCadastro extends AppCompatActivity {
    private TextView nome;
    private TextView telefone;
    private TextView cpf;
    private TextView modelo;
    private TextView placa;
    private TextView carroceria;
    private TextView cor;
    private CadastroDAO dao;
    private  Cadastro cad = null;
    public int total = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_cadastro);

        nome = findViewById(R.id.textViewName);
        telefone = findViewById(R.id.textViewTel);
        cpf = findViewById(R.id.textViewCPF);
        modelo = findViewById(R.id.textViewModel);
        placa = findViewById(R.id.textViewPlaca);
        carroceria = findViewById(R.id.textViewCarroce);
        cor = findViewById(R.id.textViewCor);
        //DBs
        dao = new CadastroDAO(this);


        Intent it = getIntent();

        cad = (Cadastro) it.getSerializableExtra("cadastro");

        nome.setText(cad.getNome().toString());
        telefone.setText(cad.getTelefone().toString());
        cpf.setText(cad.getCPF().toString());
        modelo.setText(cad.getModelo().toString());
        placa.setText(cad.getPlaca().toString());
        carroceria.setText(cad.getCarroceria().toString());
        cor.setText(cad.getCor().toString());
    }

    public void backtolist(View v){
        Intent it_backtolist = new Intent(this,  ListarCadastros.class);
        startActivity(it_backtolist);
    }
}