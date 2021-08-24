package com.company.usuario.tipo;

import com.company.disciplinas.Disciplina;
import com.company.usuario.Usuario;

import java.util.ArrayList;
import java.util.Date;

public class Aluno extends Usuario {

    private String matricula;
    private ArrayList<Disciplina> disciplinaCursadas;

    public Aluno(String nome, String cpf, String email, String senha, Date dataNascimento, String matricula, ArrayList<Disciplina> disciplinaCursadas) {
        super(nome, cpf, email, senha, dataNascimento);
        this.matricula = matricula;
        this.disciplinaCursadas = disciplinaCursadas;
    }

    public void matricularEmDiscplinas (){

    }

    public void cancelarMatriculaEmDisciplinas(){

    }

    public void listarDisciplinas(){

    }
}
