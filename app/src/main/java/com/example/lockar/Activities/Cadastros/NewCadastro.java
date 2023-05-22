package com.example.lockar.Activities.Cadastros;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.lockar.Classes.Cadastro;
import com.example.lockar.DAO.CadastroDAO;
import com.example.lockar.MainActivity;
import com.example.lockar.R;

public class NewCadastro extends AppCompatActivity {
    private TextView txtvtitle;
    private EditText nome;
    private EditText telefone;
    private EditText cpf;
    private EditText modelo;
    private EditText placa;
   // private EditText carroceria;
   // private EditText cor;
    private CadastroDAO dao;
    private Cadastro cad = null;

    String[] items = {"Onix Sedan", "Golf", "Jeep Renegage"};

    AutoCompleteTextView autoCompleteTxt;

    ArrayAdapter<String> adapteritems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_cadastro);

        autoCompleteTxt = findViewById(R.id.auto_complete_txt);

        adapteritems = new ArrayAdapter<String>(this, R.layout.list_item, items);
        autoCompleteTxt.setAdapter(adapteritems);
        autoCompleteTxt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
            }
        });

        //classes
        nome = findViewById(R.id.EditTxtName);
        telefone = findViewById(R.id.EditTxtTel);
        cpf = findViewById(R.id.EditTxtCPF);
        placa = findViewById(R.id.EditTxtPlaca);
        //DBs
        dao = new CadastroDAO(this);
        //textViews
        txtvtitle = findViewById(R.id.textViewTitle);


        Intent it = getIntent();

        if (it.hasExtra("cadastro")) {
            cad = (Cadastro) it.getSerializableExtra("cadastro");
            txtvtitle.setText("Atualizar Cadastro");

            nome.setText(cad.getNome().toString());
            telefone.setText(cad.getTelefone().toString());
            cpf.setText(cad.getCPF().toString());
            autoCompleteTxt.setText(cad.getModelo());
            placa.setText(cad.getPlaca().toString());
            //carroceria.setText(cad.getCarroceria().toString());
            //cor.setText(cad.getCor().toString());

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
            c.setModelo(autoCompleteTxt.getText().toString());
            c.setPlaca(placa.getText().toString());
           // c.setCarroceria(carroceria.getText().toString());
           // c.setCor(cor.getText().toString());


            long id = dao.inserir(c);


            AlertDialog.Builder dialogo = new AlertDialog.Builder(NewCadastro.this);
            dialogo.setTitle("Cadastro Realizado com Sucesso!");
            dialogo.setMessage("Aluno criado com o ID : "+ id);
            dialogo.setNeutralButton("Ok", null);
            dialogo.show();

            nome.setText("");
            cpf.setText("");
            telefone.setText("");
            autoCompleteTxt.setText("");
            placa.setText("");
           // carroceria.setText("");
           // cor.setText("");
        }
        else{

            Cadastro c = new Cadastro();
            c.setId(Integer.parseInt(cad.getId().toString()));
            c.setNome(nome.getText().toString());
            c.setCPF(cpf.getText().toString());
            c.setTelefone(telefone.getText().toString());
            c.setModelo(autoCompleteTxt.getText().toString());
            c.setPlaca(placa.getText().toString());
           // c.setCarroceria(carroceria.getText().toString());
           // c.setCor(cor.getText().toString());


            long id = dao.atualizar(c);


            AlertDialog.Builder dialogo = new AlertDialog.Builder(NewCadastro.this);
            dialogo.setTitle("Cadastro Atualizado com Sucesso!");
            dialogo.setMessage("Cadastro Atualizado com o ID : "+ id);
            dialogo.setNeutralButton("Ok", null);
            dialogo.show();



        }

            }
        }



