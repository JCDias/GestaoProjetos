/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.jean.gestaoprojetos.entidades;

/**
 *
 * @author JEAN CARLOS
 */
public class Atividade {
    
    private int id_atividade;
    private String nome;
    private double duracao;
    private Projeto projeto;
    private Usuario encarregado;
    private double hora_trabalhadas;
    private double conclusao;

    public int getId_atividade() {
        return id_atividade;
    }

    public void setId_atividade(int id_atividade) {
        this.id_atividade = id_atividade;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getDuracao() {
        return duracao;
    }

    public void setDuracao(double duracao) {
        this.duracao = duracao;
    }

    public Projeto getProjeto() {
        return projeto;
    }

    public void setProjeto(Projeto projeto) {
        this.projeto = projeto;
    }

    public Usuario getEncarregado() {
        return encarregado;
    }

    public void setEncarregado(Usuario encarregado) {
        this.encarregado = encarregado;
    }

    public double getHora_trabalhadas() {
        return hora_trabalhadas;
    }

    public void setHora_trabalhadas(double hora_trabalhadas) {
        this.hora_trabalhadas = hora_trabalhadas;
    }

    public double getConclusao() {
        return conclusao;
    }

    public void setConclusao(double conclusao) {
        this.conclusao = conclusao;
    }
    
}
