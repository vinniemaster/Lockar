package com.example.lockar;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Conexao extends SQLiteOpenHelper {

    private static final String name = "banco.db";
    private  static  final int version = 1;

    public  Conexao(Context context){
        super(context, name, null,version);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("CREATE TABLE TB_CADASTRO(Id integer primary key autoincrement," +
                "Nome varchar(50), CPF varchar(20), Telefone varchar(20), Modelo varchar(50), Placa varchar(50), Carroceria varchar(50), Cor varchar(50) )");

        db.execSQL("CREATE TABLE TB_AGENDAMENTOS(Id integer primary key autoincrement," +
                "cadastroId integer, datahr_inicio datetime, datahr_fim datetime )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1){

    }
}
