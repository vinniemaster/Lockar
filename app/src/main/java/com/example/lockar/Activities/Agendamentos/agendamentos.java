package com.example.lockar.Activities.Agendamentos;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.lockar.Activities.Cadastros.ListarCadastros;
import com.example.lockar.Activities.Cadastros.NewCadastro;
import com.example.lockar.Activities.Cadastros.ShowCadastro;
import com.example.lockar.Classes.Agendamento;
import com.example.lockar.Classes.Cadastro;
import com.example.lockar.DAO.AgendamentosDAO;
import com.example.lockar.DAO.CadastroDAO;
import com.example.lockar.Classes.ListAgendamentos;
import com.example.lockar.MainActivity;
import com.example.lockar.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class agendamentos extends AppCompatActivity {
    private AgendamentosDAO agenDao;
    private CadastroDAO cadstroDao;
    private List<Cadastro> cadastros;
    private List<Agendamento> agendamentos ;

    private ListView listView;
    public List<ListAgendamentos> listAgendamentos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agendamentos);
        listAgendamentos = new ArrayList<ListAgendamentos>();
        agenDao = new AgendamentosDAO(this);
        cadstroDao = new CadastroDAO(this);
        agendamentos = agenDao.GetALL();
        cadastros = cadstroDao.GetALL();
        listView = findViewById(R.id.listAgendamentos);

        Calendar c = Calendar.getInstance();

        final int day = c.get(Calendar.DAY_OF_MONTH);
        final int month = c.get(Calendar.MONTH);
        final int year = c.get(Calendar.YEAR);
        final int hour = c.get(Calendar.HOUR_OF_DAY);
        final int minute = c.get(Calendar.MINUTE);
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        SimpleDateFormat outputFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
        String datestr = year + "-"+ month + "-" + day + " " + hour + ":" + minute;
        Date datetoday;
        try {
            Date datedbformat = inputFormat.parse(datestr);
            datetoday = outputFormat.parse(datedbformat.toString());

        } catch (ParseException e) {
            throw new RuntimeException(e);
        }


        for(Agendamento a: agendamentos){
            Cadastro cad;
            cad = cadstroDao.GetByID(a.getCadastroId());
            if(cad != null){
                ListAgendamentos ag = new ListAgendamentos();
                ag.setId(a.getId());
                ag.setNome(cad.getNome());
                ag.setModelo(cad.getModelo());
                if(datetoday.compareTo(a.getDatahr_fim()) < 0){
                    ag.setStatus("NÃO ATRASADO");
                }
                else{
                    ag.setStatus("ATRASADO");
                }


                listAgendamentos.add(ag);
            }

        }
        if(listAgendamentos != null){
            ArrayAdapter<ListAgendamentos> adaptador = new ArrayAdapter<ListAgendamentos>(this,R.layout.linha ,listAgendamentos);

            listView.setAdapter(adaptador);

            registerForContextMenu(listView);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                @Override
                public void onItemClick(AdapterView<?> parent,View view, int position, long id){
                    ListAgendamentos agenShow = listAgendamentos.get(position);
                    Intent it = new Intent(getApplicationContext(), ShowAgendamento.class);
                    it.putExtra("agId", agenShow.getId().toString());
                    startActivity(it);

                }
            });
        }


    }
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater i = getMenuInflater();
        i.inflate(R.menu.menu_contexto, menu);


    }
    public void excluir(MenuItem item) {
        AdapterView.AdapterContextMenuInfo menuinfo =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        ListAgendamentos agendexcluir = listAgendamentos.get(menuinfo.position);

        AlertDialog.Builder dialogo = new AlertDialog.Builder(agendamentos.this);
        dialogo.setTitle("Atenção");
        dialogo.setMessage("Realmente deseja excluir o agendamento do(a) ?");
        dialogo.setNegativeButton("NÃO", null);
        dialogo.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                listAgendamentos.remove(agendexcluir);
                agenDao.excluir(agendexcluir.getId());

                listView.invalidateViews();
            }
        }).create();
        dialogo.show();


    }
    public void atualizar(MenuItem item){
        AdapterView.AdapterContextMenuInfo menuinfo =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        ListAgendamentos agendatt = listAgendamentos.get(menuinfo.position);


        Intent it = new Intent(this, NewAgendamento.class);
        it.putExtra("agID",agendatt.getId().toString());
        startActivity(it);
    }
    public void VoltarHome(View v){
        Intent home = new Intent(this, MainActivity.class );
        startActivity(home);
    }

    public void NewAgendamento(View v){
        Intent newAgend = new Intent(this, NewAgendamento.class );
        startActivity(newAgend);
    }
}