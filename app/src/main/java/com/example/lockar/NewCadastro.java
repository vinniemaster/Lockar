package com.example.lockar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class NewCadastro extends AppCompatActivity {
    private TextView txtvtitle;
    private EditText nome;
    private EditText telefone;
    private EditText cpf;
    private EditText modelo;
    private EditText placa;
    private EditText carroceria;
    private EditText cor;
    private CadastroDAO dao;
    private  Cadastro cad = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_cadastro);
        //classes
        nome = findViewById(R.id.EditTxtName);
        telefone = findViewById(R.id.EditTxtTel);
        cpf = findViewById(R.id.EditTxtCPF);
        modelo = findViewById(R.id.EditTxtModel);
        placa = findViewById(R.id.EditTxtPlaca);
        carroceria = findViewById(R.id.EditTxtCarroceri);
        cor = findViewById(R.id.EditTxtCo);
        //DBs
        dao = new CadastroDAO(this);
        //textViews
        txtvtitle = findViewById(R.id.textViewTitle);


        Intent it = getIntent();

        if(it.hasExtra("cadastro")){
            cad = (Cadastro) it.getSerializableExtra("cadastro");
            txtvtitle.setText("Atualizar Cadastro");

            nome.setText(cad.getNome().toString());
            telefone.setText(cad.getTelefone().toString());
            cpf.setText(cad.getCPF().toString());
            modelo.setText(cad.getModelo().toString());
            placa.setText(cad.getPlaca().toString());
            carroceria.setText(cad.getCarroceria().toString());
            cor.setText(cad.getCor().toString());

        }

    }
    public void backtohome(View v){
        Intent it_backtohome = new Intent(this, MainActivity.class);
        startActivity(it_backtohome);
    }

    public void Salvar(View v){
        if(cad == null){
            Cadastro c = new Cadastro();
            c.setNome(nome.getText().toString());
            c.setCPF(cpf.getText().toString());
            c.setTelefone(telefone.getText().toString());
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

            nome.setText("");
            cpf.setText("");
            telefone.setText("");
            modelo.setText("");
            placa.setText("");
            carroceria.setText("");
            cor.setText("");
        }
        else{

            Cadastro c = new Cadastro();
            c.setId(Integer.parseInt(cad.getId().toString()));
            c.setNome(nome.getText().toString());
            c.setCPF(cpf.getText().toString());
            c.setTelefone(telefone.getText().toString());
            c.setModelo(modelo.getText().toString());
            c.setPlaca(placa.getText().toString());
            c.setCarroceria(carroceria.getText().toString());
            c.setCor(cor.getText().toString());


            long id = dao.atualizar(c);


            AlertDialog.Builder dialogo = new AlertDialog.Builder(NewCadastro.this);
            dialogo.setTitle("Cadastro Atualizado com Sucesso!");
            dialogo.setMessage("Cadastro Atualizado com o ID : "+ id);
            dialogo.setNeutralButton("Ok", null);
            dialogo.show();



        }
    }

}