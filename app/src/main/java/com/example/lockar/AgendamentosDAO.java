package com.example.lockar;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AgendamentosDAO {
    private Conexao conexao;
    private SQLiteDatabase banco;

    public AgendamentosDAO(Context context){
        conexao = new Conexao(context);
        banco = conexao.getWritableDatabase();
    }

    public long inserir(Agendamento agen){
        ContentValues values = new ContentValues();
        values.put("cadastroId", agen.getCadastroId());
        values.put("datahr_inicio", agen.getDatahr_inicio().toString());
        values.put("datahr_fim", agen.getDatahr_fim().toString());

        return banco.insert("TB_AGENDAMENTO",null,values);
    }

    public List<Agendamento> GetALL(){
        List<Agendamento> agendamentos = new ArrayList<>();

        Cursor cursor = banco.query("TB_AGENDAMENTOS", new String[]{"Id","cadastroId","datahr_inicio","datahr_fim"}, null,null,null,null,null);

        while (cursor.moveToNext()){
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


            Agendamento a = new Agendamento();
            a.setId(cursor.getInt(0));
            a.setCadastroId(cursor.getInt(1));

            try {
                a.setDatahr_inicio(simpleDateFormat.parse(cursor.getString(2)));
            } catch(Exception e){
                throw new RuntimeException(e);
            }
            try {
                a.setDatahr_fim(simpleDateFormat.parse(cursor.getString(3)));
            } catch(Exception e){
                throw new RuntimeException(e);
            }


            agendamentos.add(a);
        }

        return agendamentos;
    }


    public void excluir(Agendamento a){
        banco.delete("TB_AGENDAMENTOS","id = ?",new String[]{a.getId().toString()});
    }


    public long atualizar(Agendamento agen){
        ContentValues values = new ContentValues();
        values.put("cadastroId", agen.getId());
        values.put("datahr_inicio", agen.getDatahr_inicio().toString());
        values.put("datahr_fim", agen.getDatahr_inicio().toString());


        return banco.update("TB_AGENDAMENTOS",values,"id = ?", new String[]{agen.getId().toString()});
    }
}
