/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.jean.gestaoprojetos.negocio;

import br.edu.ifnmg.jean.gestaoprojetos.dados.AtividadeDAO;
import br.edu.ifnmg.jean.gestaoprojetos.dados.ProjetoDAO;
import br.edu.ifnmg.jean.gestaoprojetos.dados.UsuarioDAO;
import br.edu.ifnmg.jean.gestaoprojetos.entidades.Atividade;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author JEAN CARLOS
 */
public class AtividadeBO {

    //Valida campos
    public String validaDados(String nome, String projeto, String encarregado, double duracao) {
        String mensagem = null;

        if (nome.length() < 3) {
            mensagem = "Nome muito curto! O nome deve conter no mínimo 3 caracteres.";
        }

        if (duracao == 0) {
            mensagem = "Duração deve ser maior que 0";
        }

        if (nome.isEmpty() || projeto.equals("Selecione") || encarregado.equals("Selecione")) {
            mensagem = "Preencha todos os campos!";
        }
        return mensagem;
    }

    //Preencher combo box departamento
    public ArrayList<String> ComboBoxProjeto(String CodDepartamento) throws SQLException {

        ProjetoDAO projetoDAO = new ProjetoDAO();
        ArrayList<String> Projeto = new ArrayList<>();

        Projeto = projetoDAO.ComboBoxProjeto(CodDepartamento);

        return Projeto;

    }

    //Preencher combo box encarregado
    public ArrayList<String> ComboBoxEncarregadoPorDepartamento(String CodDepartamento) throws SQLException {

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        ArrayList<String> Encarregado = new ArrayList<>();

        Encarregado = usuarioDAO.ComboBoxEncarregadosPorDepartamento(CodDepartamento);

        return Encarregado;

    }

    public String InserirAtividade(Atividade atividade, String nome_departamento, int idprojeto) throws SQLException {
        String mensagem = null;
        AtividadeDAO atividadeDAO = new AtividadeDAO();

        Atividade atividadeExistente = null;
        atividadeExistente = atividadeDAO.SelectATividadePorNome(atividade.getNome(), nome_departamento);

        if (atividadeExistente == null) {
            atividadeDAO.inserirAtividade(atividade, idprojeto);
        } else {
            mensagem = "Já existe uma atividade com este nome cadastrada!";
        }
        return mensagem;
    }

    //Preencher tabela atividade
    public ResultSet preencherTabelaAtividade(String codDepartamento) throws SQLException {
        AtividadeDAO atividadeDAO = new AtividadeDAO();
        ResultSet resultPreencherTabela = atividadeDAO.preencherTabelaATividade(codDepartamento);

        return resultPreencherTabela;

    }

    //Excluir Atividade
    public void excluiAtividade(int id_atividade) throws SQLException {

        AtividadeDAO atividadeDAO = new AtividadeDAO();
        atividadeDAO.excluirAtividade(id_atividade);

    }

    //atualizar atividade
    public void AtualizarAtividade(Atividade atividade) throws SQLException {
        
        AtividadeDAO atividadeDAO = new AtividadeDAO();
        
        atividadeDAO.atualizarAtividade(atividade);

    }
    
    public ArrayList<String> ComboBoxAtividades(int id_usuario) throws SQLException {

        AtividadeDAO atividadeDAO = new AtividadeDAO();
        ArrayList<String> Atividades = new ArrayList<>();

        Atividades = atividadeDAO.cbAtividades(id_usuario);

        return Atividades;

    }
    
    //lençar horas
    public void LancarHoras(Atividade atividade, String atividadeSelecionada) throws SQLException {

        AtividadeDAO atividadeDAO = new AtividadeDAO();
        atividadeDAO.andamentoAtividade(atividade, atividadeSelecionada);

    }
}
