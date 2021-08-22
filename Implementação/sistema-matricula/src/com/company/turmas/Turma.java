package com.company.turmas;

import com.company.disciplinas.Disciplina;
import com.company.usuario.tipo.Aluno;
import com.company.usuario.tipo.Professor;

import java.util.ArrayList;

public class Turma {
    private Disciplina disciplina;
    private ArrayList<Aluno> alunos;
    private Professor professor;

    public Turma(Disciplina disciplina, ArrayList<Aluno> alunos, Professor professor) {
        this.disciplina = disciplina;
        this.alunos = alunos;
        this.professor = professor;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    public ArrayList<Aluno> getAlunos() {
        return alunos;
    }

    public void setAlunos(ArrayList<Aluno> alunos) {
        this.alunos = alunos;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public void atualizarDados(){

    }

    public void listarDados(){

    }
}
