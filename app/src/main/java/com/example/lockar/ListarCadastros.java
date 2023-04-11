package com.example.lockar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ListarCadastros extends AppCompatActivity {
    private ListView listView;
    private CadastroDAO dao;
    private List<Cadastro> cadastros;
    private List<Cadastro> cadastrosFiltrados = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_cadastros);

        listView = findViewById(R.id.lista_cadastros);
        dao = new CadastroDAO(this);

        cadastros = dao.GetALL();

        cadastrosFiltrados.addAll(cadastros);

        ArrayAdapter<Cadastro> adaptador = new ArrayAdapter<Cadastro>(this, R.layout.linha,cadastros);

        listView.setAdapter(adaptador);

        registerForContextMenu(listView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent,View view, int position, long id){
                Cadastro cadshow = cadastros.get(position);
                Intent it = new Intent(getApplicationContext(),ShowCadastro.class);
                it.putExtra("cadastro", cadshow);
                startActivity(it);

            }
        });





    }



    public void backtohome(View v){
        Intent it_backtohome = new Intent(this, MainActivity.class);
        startActivity(it_backtohome);
    }


    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater i = getMenuInflater();
        i.inflate(R.menu.menu_contexto, menu);


    }

    public void excluir(MenuItem item){
        AdapterView.AdapterContextMenuInfo menuinfo =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        Cadastro cadexcluir = cadastros.get(menuinfo.position);

        AlertDialog.Builder dialogo = new AlertDialog.Builder(ListarCadastros.this);
        dialogo.setTitle("Atenção");
        dialogo.setMessage("Realmente deseja excluir o cadastro do(a) " + cadexcluir.getNome().toString() + "?");
        dialogo.setNegativeButton("NÃO", null);
        dialogo.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                cadastros.remove(cadexcluir);
                dao.excluir(cadexcluir);

                listView.invalidateViews();
            }
        }).create();
        dialogo.show();
    }

    public void atualizar(MenuItem item){
        AdapterView.AdapterContextMenuInfo menuinfo =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        Cadastro cadatualizar = cadastros.get(menuinfo.position);

        Intent it = new Intent(this,NewCadastro.class);
        it.putExtra("cadastro", cadatualizar);
        startActivity(it);
    }
}