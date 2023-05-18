package com.example.lockar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class NewAgendamento extends AppCompatActivity {
    EditText cadastroId;
    TextView DtInicio;
    TextView DtFim;

    TextView HrInicio;
    TextView HrFim;

    DatePickerDialog datePickDialog;

    TimePickerDialog timePickerDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_agendamento);

        cadastroId = findViewById(R.id.editTxtCadastroId5);
        DtInicio = findViewById(R.id.textView31);
        DtFim = findViewById(R.id.textView33);
        HrInicio = findViewById(R.id.textView32);
        HrFim = findViewById(R.id.textView34);


        Calendar c = Calendar.getInstance();

        final int day = c.get(Calendar.DAY_OF_MONTH);
        final int month = c.get(Calendar.MONTH);
        final int year = c.get(Calendar.YEAR);
        final int hour = c.get(Calendar.HOUR_OF_DAY) + 1;
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



    public void VoltarConsulta(View v){
        Intent consulta = new Intent(this, agendamentos.class );
        startActivity(consulta);
    }
}