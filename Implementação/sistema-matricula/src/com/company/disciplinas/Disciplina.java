package com.company.disciplinas;

public class Disciplina {
    private String nome;
    private TipoDisciplina tipo;
    private String codigo;
    private int creditos;

    public Disciplina(String nome, TipoDisciplina tipo, String codigo, int creditos) {
        this.nome = nome;
        this.tipo = tipo;
        this.codigo = codigo;
        this.creditos = creditos;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public TipoDisciplina getTipo() {
        return tipo;
    }

    public void setTipo(TipoDisciplina tipo) {
        this.tipo = tipo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public int getCreditos() {
        return creditos;
    }

    public void setCreditos(int creditos) {
        this.creditos = creditos;
    }

    public void listaDados(){

    }
}
