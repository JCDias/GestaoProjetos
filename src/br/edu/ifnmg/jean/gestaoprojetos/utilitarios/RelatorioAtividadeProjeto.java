/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.jean.gestaoprojetos.utilitarios;

/**
 *
 * @author JEAN CARLOS
 */
public class RelatorioAtividadeProjeto {
    private int idAtividade;
    private String nome;
    private Float duracao;
    private Float conclusao;
    private Float horasTrabalhadas;
    private String encarregado;
    private String projeto;
    private int totalAtividades;
    private int atividadesNaoIniciadas;
    private int atividadesIniciadas;
    private int atividadesConcluidas;

    public int getIdAtividade() {
        return idAtividade;
    }

    public void setIdAtividade(int idAtividade) {
        this.idAtividade = idAtividade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Float getDuracao() {
        return duracao;
    }

    public void setDuracao(Float duracao) {
        this.duracao = duracao;
    }

    public Float getConclusao() {
        return conclusao;
    }

    public void setConclusao(Float conclusao) {
        this.conclusao = conclusao;
    }

    public Float getHorasTrabalhadas() {
        return horasTrabalhadas;
    }

    public void setHorasTrabalhadas(Float horasTrabalhadas) {
        this.horasTrabalhadas = horasTrabalhadas;
    }

    public String getEncarregado() {
        return encarregado;
    }

    public void setEncarregado(String encarregado) {
        this.encarregado = encarregado;
    }

    public String getProjeto() {
        return projeto;
    }

    public void setProjeto(String projeto) {
        this.projeto = projeto;
    }

    public int getTotalAtividades() {
        return totalAtividades;
    }

    public void setTotalAtividades(int totalAtividades) {
        this.totalAtividades = totalAtividades;
    }

    public int getAtividadesNaoIniciadas() {
        return atividadesNaoIniciadas;
    }

    public void setAtividadesNaoIniciadas(int atividadesNaoIniciadas) {
        this.atividadesNaoIniciadas = atividadesNaoIniciadas;
    }

    public int getAtividadesIniciadas() {
        return atividadesIniciadas;
    }

    public void setAtividadesIniciadas(int atividadesIniciadas) {
        this.atividadesIniciadas = atividadesIniciadas;
    }

    public int getAtividadesConcluidas() {
        return atividadesConcluidas;
    }

    public void setAtividadesConcluidas(int atividadesConcluidas) {
        this.atividadesConcluidas = atividadesConcluidas;
    }
    
}
