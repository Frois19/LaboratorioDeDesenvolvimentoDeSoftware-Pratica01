package com.company.usuario.tipo;

import com.company.usuario.Usuario;

import java.util.Date;

public class Secretaria extends Usuario {


    public Secretaria(String nome, String cpf, String email, String senha, Date dataNascimento, Date cargaHoraria) {
        super(nome, cpf, email, senha, dataNascimento);
        this.cargaHoraria = cargaHoraria;
    }

    private Date cargaHoraria;


    public Date getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(Date cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public void gerarCurriculo(){

    }

    public void adiconarAluno(){

    }

    public void consultarAluno(){

    }

    public void cancelarAluno(){

    }

    public void atualizarAluno(){

    }

    public void adiconarProfessor(){

    }

    public void consultarProfessor(){

    }

    public void cancelarProfessor(){

    }

    public void atualizarProfessor(){

    }
    public void adiconarDisciplinas(){

    }

    public void consultarDisciplinas(){

    }

    public void cancelarDisciplinas(){

    }

    public void atualizarDiscplinas(){

    }
    public void adiconarTurma(){

    }

    public void consultarTurma(){

    }

    public void cancelarTurma(){

    }

    public void atualizarTurma(){

    }

}
