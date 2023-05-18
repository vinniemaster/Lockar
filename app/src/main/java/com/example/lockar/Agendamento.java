package com.example.lockar;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

public class Agendamento implements Serializable {

    private Integer Id;
    private Integer cadastroId;

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public Date getDatahr_inicio() {
        return datahr_inicio;
    }

    public void setDatahr_inicio(Date datahr_inicio) {
        this.datahr_inicio = datahr_inicio;
    }

    public Date getDatahr_fim() {
        return datahr_fim;
    }

    public void setDatahr_fim(Date datahr_fim) {
        this.datahr_fim = datahr_fim;
    }

    private Date datahr_inicio;
    private Date datahr_fim;

    public Integer getCadastroId() {
        return cadastroId;
    }

    public void setCadastroId(Integer cadastroId) {
        this.cadastroId = cadastroId;
    }


}
