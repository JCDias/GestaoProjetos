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
import br.edu.ifnmg.jean.gestaoprojetos.entidades.Departamento;
import br.edu.ifnmg.jean.gestaoprojetos.excecoes.AtividadeExistenteException;
import br.edu.ifnmg.jean.gestaoprojetos.excecoes.CamposVaziosException;
import br.edu.ifnmg.jean.gestaoprojetos.excecoes.DadoInvalidoException;
import br.edu.ifnmg.jean.gestaoprojetos.excecoes.NomeInvalidoException;
import br.edu.ifnmg.jean.gestaoprojetos.utilitarios.RelatorioAtividadeProjeto;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author JEAN CARLOS
 */
public class AtividadeBO {

    //Valida campos
    public void validaDados(String nome, String projeto, String encarregado, double duracao){
        
        if (nome.length() < 3) {
            throw new NomeInvalidoException();
        }

        if (duracao == 0) {
            throw new DadoInvalidoException();
        }

        if (nome.isEmpty() || projeto.equals("Selecione") || encarregado.equals("Selecione")) {
            throw new CamposVaziosException();
        }
        
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

    public void InserirAtividade(Atividade atividade, String nome_departamento, int idprojeto) throws SQLException {
        
        AtividadeDAO atividadeDAO = new AtividadeDAO();

        Atividade atividadeExistente = null;
        atividadeExistente = atividadeDAO.SelectATividadePorNome(atividade.getNome(), nome_departamento);

        if (atividadeExistente == null) {
            atividadeDAO.inserirAtividade(atividade, idprojeto);
        } else {
            throw new AtividadeExistenteException();
        }
        
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
        ArrayList<String> Atividades = new ArrayList<String>();

        Atividades = atividadeDAO.cbAtividades(id_usuario);

        return Atividades;

    }
    
    //validaLancar
    public String validaLancar(String atividade, double horasTrabalhadas, double conclusao){
        String mensagem = null;
        
        if(atividade.equals("Selecione") || horasTrabalhadas == 0 || conclusao == 0){
            mensagem = "Preencha todos os campos!";
        }
        
        return mensagem;
    }
    
    //lançar horas
    public void LancarHoras(Atividade atividade, String atividadeSelecionada) throws SQLException {

        AtividadeDAO atividadeDAO = new AtividadeDAO();
        atividadeDAO.andamentoAtividade(atividade, atividadeSelecionada);

    }
    
    //Verifica se existe alguma atividade em atraso
    /*public int VerificaAtraso(String cod_departamento) throws SQLException{
        AtividadeDAO atividadeDAO = new AtividadeDAO();
        
        int quantidade = atividadeDAO.VerificaAtraso(cod_departamento);
        System.out.println("BO"+quantidade);
        return quantidade;
    }
    */
    public ResultSet preencherTabelaAtividadeAtrasadas(String codDepartamento) throws SQLException {
        AtividadeDAO atividadeDAO = new AtividadeDAO();
        ResultSet resultPreencherTabela = atividadeDAO.preencherTabelaAtividadeAtrasadas(codDepartamento);

        return resultPreencherTabela;

    }
    
    //Selecionar horas e porcentagem da atividade por nome
    public Atividade HorasConclusao(String departamento) throws SQLException{
        Atividade atividade = new Atividade();
        
        AtividadeDAO atividadeDAO = new AtividadeDAO();
        
        atividade = atividadeDAO.HorasConclusao(departamento);
        
        return atividade;
    }
    
    
    //Listar atividades
    public ArrayList<RelatorioAtividadeProjeto> listaAtividade(String nomeProjeto) throws SQLException {

        AtividadeDAO atividadeDAO = new AtividadeDAO();
        ArrayList<RelatorioAtividadeProjeto> listaAtividade = new ArrayList<>();

        listaAtividade = atividadeDAO.SelecionarAtividade(nomeProjeto);

        return listaAtividade;

    }
}
