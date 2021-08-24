package com.company.usuario.tipo;

import com.company.usuario.Usuario;

import java.util.Date;

public class Professor extends Usuario {

    public Professor(String nome, String cpf, String email, String senha, Date dataNascimento, String matricula, Date cargaHoraria) {
        super(nome, cpf, email, senha, dataNascimento);
        this.matricula = matricula;
        this.cargaHoraria = cargaHoraria;
    }

    private String matricula;
    private Date cargaHoraria;

    public Date getCargaHoraria() {
        return cargaHoraria;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public void setCargaHoraria(Date cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public void visulizarTurma(){

    }
}


