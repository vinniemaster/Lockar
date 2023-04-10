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
                "Nome varchar(50), CPF integer, Telefone integer, Modelo varchar(50), Placa varchar(50), Carroceria varchar(50), Cor varchar(50) )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1){

    }
}
