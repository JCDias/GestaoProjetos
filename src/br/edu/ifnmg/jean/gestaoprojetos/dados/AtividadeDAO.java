/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.jean.gestaoprojetos.dados;

import br.edu.ifnmg.jean.gestaoprojetos.entidades.Atividade;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author JEAN CARLOS
 */
public class AtividadeDAO {
    
    //Selecionar atividade por nome
    private static final String SQL_SELECT_ATIVIDADE = "SELECT ID_ATIVIDADE, NOME , DURACAO FROM ATIVIDADE WHERE ATIVIDADE.NOME = ? AND ID_DEPARTAMENTO = ?";
    public Atividade SelectATividadePorNome(String NomeAtividade, String codigo) throws SQLException {

        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet resultado = null;
        Atividade atividade = null;
        
        int id_departamento = selecionarIdDepartamento(codigo);
        
        try {

            conexao = BancoDadosUtil.getConnection();

            comando = conexao.prepareStatement(SQL_SELECT_ATIVIDADE);
            comando.setString(1, NomeAtividade);
            comando.setInt(2, id_departamento);

            resultado = comando.executeQuery();

            if (resultado.next()) {
                atividade = new Atividade();
                atividade.setNome(resultado.getString("NOME"));
                atividade.setDuracao(resultado.getFloat("DURACAO"));
                atividade.setId_atividade(resultado.getInt("ID_ATIVIDADE"));
            }

            conexao.commit();

        } catch (Exception e) {
            if (conexao != null) {
                conexao.rollback();
            }

        } finally {
            if (comando != null && !comando.isClosed()) {
                comando.close();
            }
            if (conexao != null && !conexao.isClosed()) {
                conexao.close();
            }
        }

        return atividade;

    }
    
    //inserir atividade
    private static final String SQL_INSERT_ATIVIDADE = "INSERT INTO ATIVIDADE (NOME, DURACAO, ID_PROJETO, ID_USUARIO ) VALUES (?,?,?,?)";
    
    public void inserirAtividade(Atividade atividade, int idprojeto) throws SQLException {
        Connection conexao = null;
        PreparedStatement comando = null;

        try {

            conexao = BancoDadosUtil.getConnection();
            comando = conexao.prepareStatement(SQL_INSERT_ATIVIDADE);
            
            comando.setString(1, atividade.getNome());
            comando.setDouble(2, atividade.getDuracao());
            comando.setInt(3, idprojeto);
            comando.setInt(4, atividade.getEncarregado().getId_usuario());

            comando.execute();
            conexao.commit();

        } catch (Exception e) {
            if (conexao != null) {
                conexao.rollback();
            }

        } finally {
            if (comando != null && !comando.isClosed()) {
                comando.close();
            }
            if (conexao != null && !conexao.isClosed()) {
                conexao.close();
            }
        }
    }
    
    //selecionar o id do departamento
    private static final String SQL_SELECT_ID_DEPARTAMENTO_CODIGO = "SELECT ID_DEPARTAMENTO FROM DEPARTAMENTOS WHERE CODIGO = ?";

    private int selecionarIdDepartamento(String codigo) throws SQLException {
        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet resultado = null;
        int id_departamento = -1;

        try {

            conexao = BancoDadosUtil.getConnection();

            comando = conexao.prepareStatement(SQL_SELECT_ID_DEPARTAMENTO_CODIGO);

            comando.setString(1, codigo);

            resultado = comando.executeQuery();

            if (resultado.next()) {
                id_departamento = resultado.getInt("ID_DEPARTAMENTO");
            }

            conexao.commit();

        } catch (Exception e) {
            if (conexao != null) {
                conexao.rollback();
            }
        } finally {
            if (comando != null && !comando.isClosed()) {
                comando.close();
            }
            if (conexao != null && !conexao.isClosed()) {
                conexao.close();
            }
        }
        return id_departamento;

    }
    
    //preencher a tabela atividades
    private static final String SQL_SELECT_TABELA = "SELECT ATIVIDADE.ID_ATIVIDADE AS Código, ATIVIDADE.NOME AS ATIVIDADE, PROJETO.NOME  AS PROJETO, USUARIO.NOME AS ENCARREGADO, ATIVIDADE.DURACAO AS DURAÇÃO FROM ATIVIDADE JOIN PROJETO ON (ATIVIDADE.ID_PROJETO = PROJETO.ID_PROJETO) JOIN USUARIO ON (ATIVIDADE.ID_USUARIO = USUARIO.ID_USUARIO) WHERE PROJETO.ID_DEPARTAMENTO = ?";
    
    public ResultSet preencherTabelaATividade(String codigo) throws SQLException {

        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet resultado = null;
        
        int id_departamento = selecionarIdDepartamento(codigo);
        
        try {

            conexao = BancoDadosUtil.getConnection();

            comando = conexao.prepareStatement(SQL_SELECT_TABELA);
            comando.setInt(1, id_departamento);

            resultado = comando.executeQuery();

            conexao.commit();

        } catch (Exception e) {
            if (conexao != null) {
                conexao.rollback();
            }

        } finally {
            if (comando != null && !comando.isClosed()) {
                comando.close();
            }
            if (conexao != null && !conexao.isClosed()) {
                conexao.close();
            }
        }

        return resultado;

    }
    
    //Excluir atividade
    private static final String SQL_DELETE_ATIVIDADE = "DELETE FROM ATIVIDADE WHERE ATIVIDADE.ID_ATIVIDADE = ?";
    public void excluirAtividade(int id_atividade) throws SQLException {

        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet resultado = null;

        try {

            conexao = BancoDadosUtil.getConnection();
            comando = conexao.prepareStatement(SQL_DELETE_ATIVIDADE);
            comando.setInt(1, id_atividade);

            comando.execute();

            conexao.commit();

        } catch (Exception e) {
            if (conexao != null) {
                conexao.rollback();
            }
            throw new RuntimeException(e);

        } finally {
            if (comando != null && !comando.isClosed()) {
                comando.close();
            }
            if (conexao != null && !conexao.isClosed()) {
                conexao.close();
            }
        }

    }
    
    //atualizar atividade
    private static final String SQL_UPDATE_ATIVIDADE = "UPDATE ATIVIDADE SET ATIVIDADE.NOME = ?, ATIVIDADE.DURACAO = ?, ATIVIDADE.ID_USUARIO = ? WHERE ATIVIDADE.ID_ATIVIDADE = ?";
    public void atualizarAtividade(Atividade atividade) throws SQLException {
        Connection conexao = null;
        PreparedStatement comando = null;

        try {

            conexao = BancoDadosUtil.getConnection();
            comando = conexao.prepareStatement(SQL_UPDATE_ATIVIDADE);

            comando.setString(1, atividade.getNome());
            comando.setDouble(2, atividade.getDuracao());
            comando.setInt(3, atividade.getEncarregado().getId_usuario());
            comando.setInt(4, atividade.getId_atividade());

            comando.execute();
            conexao.commit();

        } catch (Exception e) {
            if (conexao != null) {
                conexao.rollback();
            }
            throw new RuntimeException(e);

        } finally {
            if (comando != null && !comando.isClosed()) {
                comando.close();
            }
            if (conexao != null && !conexao.isClosed()) {
                conexao.close();
            }
        }
    }
}
