package com.example.lockar;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

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

    public List<Cadastro> GetALL(){
        List<Cadastro> cadastros = new ArrayList<>();

        Cursor cursor = banco.query("TB_CADASTRO", new String[]{"Id","Nome","CPF","Telefone","Modelo","Placa","Carroceria","Cor"}, null,null,null,null,null);

        while (cursor.moveToNext()){
            Cadastro c = new Cadastro();
            c.setId(cursor.getInt(0));
            c.setNome(cursor.getString(1));
            c.setCPF(cursor.getString(2));
            c.setTelefone(cursor.getString(3));
            c.setModelo(cursor.getString(4));
            c.setPlaca(cursor.getString(5));
            c.setCarroceria(cursor.getString(6));
            c.setCor(cursor.getString(7));

            cadastros.add(c);
        }

        return cadastros;
    }

    public void excluir(Cadastro c){
        banco.delete("TB_CADASTRO","id = ?",new String[]{c.getId().toString()});
    }

}
