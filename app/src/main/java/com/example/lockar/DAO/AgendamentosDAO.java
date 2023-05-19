package com.example.lockar.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.lockar.Classes.Agendamento;
import com.example.lockar.Classes.Cadastro;
import com.example.lockar.Context.Conexao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AgendamentosDAO {
    private Conexao conexao;
    private SQLiteDatabase banco;

    public AgendamentosDAO(Context context){
        conexao = new Conexao(context);
        banco = conexao.getWritableDatabase();
    }

    public long inserir(Agendamento agen){
        SimpleDateFormat inputFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ContentValues values = new ContentValues();
        values.put("cadastroId", agen.getCadastroId());

        try {
            Date date = inputFormat.parse(agen.getDatahr_inicio().toString());
            values.put("datahr_inicio", simpleDateFormat.format(date));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        try {
            Date date = inputFormat.parse(agen.getDatahr_fim().toString());
            values.put("datahr_fim", simpleDateFormat.format(date));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        return banco.insert("TB_AGENDAMENTOS",null,values);
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
    public Agendamento GetByID(Integer id){
        Agendamento a = new Agendamento();
        String selectQuery = "SELECT * FROM TB_AGENDAMENTOS WHERE Id = '"+id+"'";
        Cursor cursor = banco.rawQuery(selectQuery, null);

        while (cursor.moveToNext()){
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
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
        }

        return a;

    }

    public void excluir(Integer id){
        banco.delete("TB_AGENDAMENTOS","id = ?",new String[]{id.toString()});
    }


    public long atualizar(Agendamento agen){
        SimpleDateFormat inputFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ContentValues values = new ContentValues();
        values.put("cadastroId", agen.getId());
        try {
            Date date = inputFormat.parse(agen.getDatahr_inicio().toString());
            values.put("datahr_inicio", simpleDateFormat.format(date));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        try {
            Date date = inputFormat.parse(agen.getDatahr_fim().toString());
            values.put("datahr_fim", simpleDateFormat.format(date));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        return banco.update("TB_AGENDAMENTOS",values,"id = ?", new String[]{agen.getId().toString()});
    }
}
