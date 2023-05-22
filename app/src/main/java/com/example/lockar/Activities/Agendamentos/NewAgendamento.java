package com.example.lockar.Activities.Agendamentos;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.lockar.Classes.Agendamento;
import com.example.lockar.DAO.AgendamentosDAO;
import com.example.lockar.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class NewAgendamento extends AppCompatActivity {
    EditText cadastroId;
    TextView DtInicio;
    TextView DtFim;

    TextView HrInicio;
    TextView HrFim;

    TextView title;

    Button btn;
    Agendamento agen;
    private AgendamentosDAO dao;

    String[] agd = {"1", "2", "3"};

    AutoCompleteTextView autoCompleteAgd;

    ArrayAdapter<String> adapteragd;

    DatePickerDialog datePickDialog;

    TimePickerDialog timePickerDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_agendamento);

        autoCompleteAgd = findViewById(R.id.auto_complete_agd);

        adapteragd = new ArrayAdapter<String>(this, R.layout.list_item, agd);
        autoCompleteAgd.setAdapter(adapteragd);
        autoCompleteAgd.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String agendamentos = parent.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(), "Agendamentos"+agendamentos,Toast.LENGTH_SHORT).show();
            }
        });


        dao = new AgendamentosDAO(this);
        cadastroId = findViewById(R.id.auto_complete_agd);
        DtInicio = findViewById(R.id.textView31);
        DtFim = findViewById(R.id.textView33);
        HrInicio = findViewById(R.id.textView32);
        HrFim = findViewById(R.id.textView34);

        Intent it = getIntent();

        if(it.hasExtra("agID")){
            title = findViewById(R.id.textViewTitle2);
            btn = findViewById(R.id.button9);

            title.setText("Atualizar Agendamento");
            btn.setText("Atualizar");
            String agId = it.getStringExtra("agID");
            agen = dao.GetByID(Integer.parseInt(agId));

            cadastroId.setText(agen.getCadastroId().toString());
            Date dtinicio = agen.getDatahr_inicio();
            Date dtfim = agen.getDatahr_fim();
            SimpleDateFormat getDate = new SimpleDateFormat("dd-MM-yyyy ");
            SimpleDateFormat getHour = new SimpleDateFormat("HH:mm");
            DtInicio.setText(getDate.format(dtinicio).replace("-","/"));
            HrInicio.setText(getHour.format(dtinicio));
            DtFim.setText(getDate.format(dtfim).replace("-","/"));
            HrFim.setText(getHour.format(dtfim));

            Calendar c = Calendar.getInstance();
            Date datetoday = c.getTime();
            DtInicio.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    datePickDialog = new DatePickerDialog(NewAgendamento.this, new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                            DtInicio.setText(day+"/"+month+"/"+year);
                        }
                    },dtinicio.getYear(),dtinicio.getMonth(),dtinicio.getDay());
                    datePickDialog.show();
                }

            });

            HrInicio.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    timePickerDialog = new TimePickerDialog(NewAgendamento.this, new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                            HrInicio.setText(hour+":"+minute);
                        }
                    },dtinicio.getHours(), dtinicio.getMinutes(), DateFormat.is24HourFormat(NewAgendamento.this));
                    timePickerDialog.show();
                }
            });

            DtFim.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    datePickDialog = new DatePickerDialog(NewAgendamento.this, new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                            DtFim.setText(day+"/"+month+"/"+year);
                        }
                    },dtfim.getYear(),dtfim.getMonth(),dtfim.getDay());
                    datePickDialog.show();
                }

            });

            HrFim.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    timePickerDialog = new TimePickerDialog(NewAgendamento.this, new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                            HrFim.setText(hour+":"+minute);
                        }
                    },dtfim.getHours(), dtfim.getMinutes(), DateFormat.is24HourFormat(NewAgendamento.this));
                    timePickerDialog.show();
                }
            });
        }
        else{
            Calendar c = Calendar.getInstance();

            final int day = c.get(Calendar.DAY_OF_MONTH);
            final int month = c.get(Calendar.MONTH);
            final int year = c.get(Calendar.YEAR);
            final int hour = c.get(Calendar.HOUR_OF_DAY);
            final int minute = c.get(Calendar.MINUTE);

            DtInicio.setText(day+"/"+month+"/"+year);
            HrInicio.setText(hour+":"+minute);
            DtFim.setText(day+"/"+month+"/"+year);
            HrFim.setText(hour+1+":"+minute);

            DtInicio.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    datePickDialog = new DatePickerDialog(NewAgendamento.this, new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                            DtInicio.setText(day+"/"+month+"/"+year);
                        }
                    },year,month,day);
                    datePickDialog.show();
                }

            });

            HrInicio.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    timePickerDialog = new TimePickerDialog(NewAgendamento.this, new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                            HrInicio.setText(hour+":"+minute);
                        }
                    },hour, minute, DateFormat.is24HourFormat(NewAgendamento.this));
                    timePickerDialog.show();
                }
            });

            DtFim.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    datePickDialog = new DatePickerDialog(NewAgendamento.this, new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                            DtFim.setText(day+"/"+month+"/"+year);
                        }
                    },year,month,day);
                    datePickDialog.show();
                }

            });

            HrFim.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    timePickerDialog = new TimePickerDialog(NewAgendamento.this, new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                            HrFim.setText(hour+":"+minute);
                        }
                    },hour, minute, DateFormat.is24HourFormat(NewAgendamento.this));
                    timePickerDialog.show();
                }
            });
        }



    }

    public void SalvarAgendamento(View v){
        if(agen == null){
            dao = new AgendamentosDAO(this);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
            Agendamento a = new Agendamento();
            a.setCadastroId(Integer.parseInt(cadastroId.getText().toString()));
            try {
                String date = DtInicio.getText().toString().replace("/","-") + " " + HrInicio.getText().toString() ;
                a.setDatahr_inicio(simpleDateFormat.parse(date));
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            try {
                String date = DtFim.getText().toString().replace("/","-") + " " + HrFim.getText().toString() ;
                a.setDatahr_fim(simpleDateFormat.parse(date));
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }

            long id = dao.inserir(a);

            AlertDialog.Builder dialogo = new AlertDialog.Builder(NewAgendamento.this);
            dialogo.setTitle("Agendamento Realizado com Sucesso!");
            dialogo.setMessage("Agendamento criado com o ID : "+ id);
            dialogo.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Intent consulta = new Intent(getApplicationContext(), agendamentos.class);
                    startActivity(consulta);
                }
            });
            dialogo.show();
        }
        else{
            dao = new AgendamentosDAO(this);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
            Agendamento a = new Agendamento();
            a.setId(agen.getId());
            a.setCadastroId(Integer.parseInt(cadastroId.getText().toString()));
            try {
                String date = DtInicio.getText().toString().replace("/","-") + " " + HrInicio.getText().toString() ;
                a.setDatahr_inicio(simpleDateFormat.parse(date));
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            try {
                String date = DtFim.getText().toString().replace("/","-") + " " + HrFim.getText().toString() ;
                a.setDatahr_fim(simpleDateFormat.parse(date));
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }

            long id = dao.atualizar(a);

            AlertDialog.Builder dialogo = new AlertDialog.Builder(NewAgendamento.this);
            dialogo.setTitle("Agendamento Atualizado com Sucesso!");
            dialogo.setMessage("Agendamento Atualizado com o ID : "+ id);
            dialogo.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Intent consulta = new Intent(getApplicationContext(), agendamentos.class);
                    startActivity(consulta);
                }
            });
            dialogo.show();
        }
    }


    public void VoltarConsulta(View v){
        Intent consulta = new Intent(this, agendamentos.class );
        startActivity(consulta);
    }
}