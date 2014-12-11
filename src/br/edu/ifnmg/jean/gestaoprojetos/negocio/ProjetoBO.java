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
import br.edu.ifnmg.jean.gestaoprojetos.entidades.Usuario;
import br.edu.ifnmg.jean.gestaoprojetos.excecoes.CamposVaziosException;
import br.edu.ifnmg.jean.gestaoprojetos.excecoes.DataInvalidaException;
import br.edu.ifnmg.jean.gestaoprojetos.excecoes.NomeInvalidoException;
import br.edu.ifnmg.jean.gestaoprojetos.excecoes.ProjetoInvalidoException;
import br.edu.ifnmg.jean.gestaoprojetos.utilitarios.RelatorioProjeto;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author JEAN CARLOS
 */
public class ProjetoBO {

    public void validaDados(String nome, String descricao, String data_inicio, String data_terminio) {
        
        if (nome.length() < 3) {
            throw new NomeInvalidoException();
        }

        if (data_inicio.length() < 10) {
            throw new DataInvalidaException();
        }

        if (data_terminio.length() < 10) {
            throw new DataInvalidaException();
        }

        if (nome.isEmpty() || descricao.isEmpty() || (data_inicio.length() == 2) || (data_terminio.length() == 2)) {
            throw new CamposVaziosException();
        }
    }

    //Criar novo projeto
    public void CadastrarProjeto(Projeto projeto) throws SQLException {
        
        ProjetoDAO projetDAO = new ProjetoDAO();
        //Verifica se existe um projeto com o mesmo nome no departamento
        Projeto projetoExistente = new Projeto();
        projetoExistente = projetDAO.selecionarNomeprojeto(projeto);
        if (projetoExistente == null) {
            projetDAO.cadastrarProjeto(projeto);
        } else {
            throw new ProjetoInvalidoException();
        }
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
    
    //Preencher combo box projetos
    public ArrayList<String> ComboBoxEscolhaProjeto(String codDepartamento) throws SQLException {

        ProjetoDAO projetoDAO = new ProjetoDAO();
        ArrayList<String> Projeto = new ArrayList<>();

        Projeto = projetoDAO.ComboBoxProjeto(codDepartamento);

        return Projeto;

    }
    
    //listar projetos
    public ArrayList<RelatorioProjeto> listarProjeto(Usuario usuario) throws SQLException {

        ProjetoDAO projetoDAO = new ProjetoDAO();
        ArrayList<RelatorioProjeto> listaProjeto = new ArrayList<>();
        
        listaProjeto = projetoDAO.listaProjeto(usuario);

        return listaProjeto;

    }
}
