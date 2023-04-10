package com.example.lockar;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class CadastroDAO {
    private Conexao conexao;
    private SQLiteDatabase banco;

    public CadastroDAO(Context context){
        conexao = new Conexao(context);
        banco = conexao.getWritableDatabase();
    }

    public long inserir(Cadastro cad){
        ContentValues values = new ContentValues();
        values.put("Nome", cad.getNome());
        values.put("CPF", cad.getCPF());
        values.put("Telefone", cad.getTelefone());
        values.put("Modelo", cad.getModelo());
        values.put("Placa", cad.getPlaca());
        values.put("Carroceria", cad.getCarroceria());
        values.put("Cor", cad.getCor());

        return banco.insert("TB_CADASTRO",null,values);
    }
}
