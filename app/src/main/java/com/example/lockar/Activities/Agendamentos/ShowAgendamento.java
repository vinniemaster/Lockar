package com.example.lockar.Activities.Agendamentos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.lockar.Classes.Agendamento;
import com.example.lockar.Classes.Cadastro;
import com.example.lockar.Classes.ListAgendamentos;
import com.example.lockar.DAO.AgendamentosDAO;
import com.example.lockar.DAO.CadastroDAO;
import com.example.lockar.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

public class ShowAgendamento extends AppCompatActivity {
    private TextView nome;
    private TextView telefone;
    private TextView cpf;
    private TextView modelo;
    private TextView placa;
    //private TextView cor;

    private TextView DtInicio;
    private TextView DtFim;
    private TextView Status;

    private Agendamento agendamento;
    private Cadastro cadastro;

    private AgendamentosDAO agenDAO;
    private CadastroDAO cadastroDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_agendamento);

        nome = findViewById(R.id.textViewAgendamentoName);
        telefone = findViewById(R.id.textViewAgendamentoTelefone);
        cpf = findViewById(R.id.textViewAgendamentoCPF);
        placa = findViewById(R.id.textViewAgendamentoPlaca);
        // cor = findViewById(R.id.textViewAgendamentoCor);
        modelo = findViewById(R.id.textViewAgendamentoModelo);
        DtInicio = findViewById(R.id.textViewAgendamentoDTInicio);
        DtFim = findViewById(R.id.textViewAgendamentoDtFim);
        Status = findViewById(R.id.textViewAgendamentoStatus);
        agenDAO = new AgendamentosDAO(this);
        cadastroDAO = new CadastroDAO(this);


        Intent it = getIntent();

        String agId =  it.getStringExtra("agId");

        agendamento = agenDAO.GetByID(Integer.parseInt(agId));
        cadastro = cadastroDAO.GetByID(agendamento.getCadastroId());

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");

        nome.setText(cadastro.getNome());
        telefone.setText(cadastro.getTelefone());
        cpf.setText(cadastro.getCPF());
        placa.setText(cadastro.getPlaca());
        modelo.setText(cadastro.getModelo());

        DtInicio.setText(simpleDateFormat.format(agendamento.getDatahr_inicio()).replace("-","/"));
        DtFim.setText(simpleDateFormat.format(agendamento.getDatahr_fim()).replace("-","/"));

        Calendar c = Calendar.getInstance();
        Date datetoday = c.getTime();


        if(datetoday.compareTo(agendamento.getDatahr_fim()) < 0){
            Status.setText("NÃO ATRASADO");
        }
        else{
            Status.setText("ATRASADO");
        }
    }

    public void voltarHome(View v){
        Intent it = new Intent(this, agendamentos.class);
        startActivity(it);
    }
}