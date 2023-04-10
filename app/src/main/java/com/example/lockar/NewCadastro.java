package com.example.lockar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class NewCadastro extends AppCompatActivity {
    private EditText nome;
    private EditText telefone;
    private EditText cpf;
    private EditText modelo;
    private EditText placa;
    private EditText carroceria;
    private EditText cor;

    private CadastroDAO dao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_cadastro);
        nome = findViewById(R.id.EditTxtName);
        telefone = findViewById(R.id.EditTxtTel);
        cpf = findViewById(R.id.EditTxtCPF);
        modelo = findViewById(R.id.EditTxtModel);
        placa = findViewById(R.id.EditTxtPlaca);
        carroceria = findViewById(R.id.EditTxtCarroceri);
        cor = findViewById(R.id.EditTxtCo);

        dao = new CadastroDAO(this);

    }
    public void backtohome(View v){
        Intent it_backtohome = new Intent(this, MainActivity.class);
        startActivity(it_backtohome);
    }

    public void Salvar(View v){
        Cadastro c = new Cadastro();
        c.setNome(nome.getText().toString());
        c.setCPF(Integer.parseInt(cpf.getText().toString()));
        c.setTelefone(Integer.parseInt(telefone.getText().toString()));
        c.setModelo(modelo.getText().toString());
        c.setPlaca(placa.getText().toString());
        c.setCarroceria(carroceria.getText().toString());
        c.setCor(cor.getText().toString());

        long id = dao.inserir(c);

        AlertDialog.Builder dialogo = new AlertDialog.Builder(NewCadastro.this);
        dialogo.setTitle("Cadastro Realizado com Sucesso!");
        dialogo.setMessage("Aluno criado com o ID : "+ id);
        dialogo.setNeutralButton("Ok", null);
        dialogo.show();


    }
}