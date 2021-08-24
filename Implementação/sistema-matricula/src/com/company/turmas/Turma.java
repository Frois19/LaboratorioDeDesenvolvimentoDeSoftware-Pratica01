package com.company.turmas;

import com.company.disciplinas.Disciplina;
import com.company.usuario.tipo.Aluno;
import com.company.usuario.tipo.Professor;

import java.util.ArrayList;

public class Turma {
    private Disciplina disciplina;
    private ArrayList<Aluno> alunos;
    private Professor professor;
    private int ano;
    private TipoTurno turno;
    private TipoSemestre semestre;

    public Turma(Disciplina disciplina, ArrayList<Aluno> alunos, Professor professor, int ano, TipoTurno turno) {
        this.disciplina = disciplina;
        this.alunos = alunos;
        this.professor = professor;
        this.ano = ano;
        this.turno = turno;
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

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public TipoTurno getTurno() {
        return turno;
    }

    public void setTurno(TipoTurno turno) {
        this.turno = turno;
    }

    public TipoSemestre getSemestre() {
        return semestre;
    }

    public void setSemestre(TipoSemestre semestre) {
        this.semestre = semestre;
    }

    public void atualizarDados(){

    }

    public void listarDados(){

    }
}
