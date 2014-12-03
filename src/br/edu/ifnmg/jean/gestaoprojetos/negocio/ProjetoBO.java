/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.jean.gestaoprojetos.negocio;

import br.edu.ifnmg.jean.gestaoprojetos.dados.DepartamentoDAO;
import br.edu.ifnmg.jean.gestaoprojetos.dados.ProjetoDAO;
import br.edu.ifnmg.jean.gestaoprojetos.entidades.Departamento;
import br.edu.ifnmg.jean.gestaoprojetos.entidades.Projeto;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author JEAN CARLOS
 */
public class ProjetoBO {

    public String validaDados(String nome, String descricao, String data_inicio, String data_terminio) {
        String mensagem = null;

        if (nome.length() < 3) {
            mensagem = "Nome muito curto! O nome deve conter mais de 3 caracteres.";
        }

        if (data_inicio.length() < 10) {
            mensagem = "Formato data de início inválido\nPadrão adotado DD/MM/AAAA";
        }

        if (data_terminio.length() < 10) {
            mensagem = "Formato data terminio inválido\nPadrão adotado DD/MM/AAAA";
        }

        if (nome.isEmpty() || descricao.isEmpty() || (data_inicio.length() == 2) || (data_terminio.length() == 2)) {
            mensagem = "Preencha todos os campos!";
        }

        return mensagem;
    }

    //Criar novo projeto
    public String CadastrarProjeto(Projeto projeto) throws SQLException {

        String mensagem = null;
        ProjetoDAO projetDAO = new ProjetoDAO();
        //Verifica se existe um projeto com o mesmo nome no departamento
        Projeto projetoExistente = new Projeto();
        projetoExistente = projetDAO.selecionarNomeprojeto(projeto);
        if (projetoExistente == null) {
            projetDAO.cadastrarProjeto(projeto);
        } else {
            mensagem = "Já existe um projeto cadastrado nesteb departamento com o mesmo nome!\n Por favor escolha outro nome.";
        }
        return mensagem;
    }

    //Selecionar departamento por nome
    public Departamento selecionaDepartamentoPorNome(String nome_departamento) throws SQLException {

        Departamento departamento = new Departamento();

        DepartamentoDAO depDAO = new DepartamentoDAO();

        departamento = depDAO.selecionarDepartamentoPorNome(nome_departamento);

        return departamento;
    }

    public ResultSet preencheTabela(String Departamento) throws SQLException {
        ProjetoDAO projet = new ProjetoDAO();
        ResultSet resultPreencherTabela = projet.preencherTabela(Departamento);

        return resultPreencherTabela;

    }

    //Configurar ComboBox
    public ArrayList<String> ComboBoxDepartamentos() throws SQLException {

        DepartamentoDAO depDAO = new DepartamentoDAO();
        ArrayList<String> Departamentos = new ArrayList<>();

        Departamentos = depDAO.comboBoxDepartamentos();

        return Departamentos;

    }

    //Atualizar projeto

    public void atualizarProjeto(Projeto projeto) throws SQLException {

        ProjetoDAO projetDAO = new ProjetoDAO();

        projetDAO.atualizarProjeto(projeto);

    }

    //Excluir Projeto
    public void excluirProjeto(int id_projeto) throws SQLException {

        ProjetoDAO projetoDAO = new ProjetoDAO();

        projetoDAO.excluirProjeto(id_projeto);
    }

    public int selectProjetoPorNome(String NomeProjeto) throws SQLException {

        ProjetoDAO projetDAO = new ProjetoDAO();
        Projeto projet = new Projeto();
        int id_projeto = projetDAO.selectProjetoPorNome(NomeProjeto);
        
        return id_projeto;

    }
}
