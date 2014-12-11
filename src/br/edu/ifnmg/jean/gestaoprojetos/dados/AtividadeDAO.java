/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.jean.gestaoprojetos.dados;

import br.edu.ifnmg.jean.gestaoprojetos.entidades.Atividade;
import br.edu.ifnmg.jean.gestaoprojetos.utilitarios.RelatorioAtividadeProjeto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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

    //Preencher combo Box atividades
    private static final String SQL_TODAS_ATIVIDADE_POR_USUARIO = "SELECT NOME,ID_ATIVIDADE FROM ATIVIDADE WHERE ID_USUARIO = ? ";

    public ArrayList<String> cbAtividades(int id_usuario) throws SQLException {
        ArrayList<String> Atividade = new ArrayList<String>();

        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet resultado = null;

        try {

            conexao = BancoDadosUtil.getConnection();
            comando = conexao.prepareStatement(SQL_TODAS_ATIVIDADE_POR_USUARIO);
            comando.setInt(1, id_usuario);

            resultado = comando.executeQuery();
            Atividade.removeAll(Atividade);

            while (resultado.next()) {
                Atividade.add(resultado.getString("NOME"));
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
        return Atividade;
    }

    //lançar horas
    private static final String SQL_UPDATE_ANDAMENTO_ATIVIDADE = "UPDATE ATIVIDADE SET ATIVIDADE.CONCLUSAO = ?, ATIVIDADE.HORAS_TRABALHADAS = ? WHERE ATIVIDADE.NOME = ?";

    public void andamentoAtividade(Atividade atividade, String atividadeSelecionada) throws SQLException {
        Connection conexao = null;
        PreparedStatement comando = null;

        try {

            conexao = BancoDadosUtil.getConnection();
            comando = conexao.prepareStatement(SQL_UPDATE_ANDAMENTO_ATIVIDADE);

            comando.setDouble(1, atividade.getConclusao());
            comando.setDouble(2, atividade.getHora_trabalhadas());
            comando.setString(3, atividadeSelecionada);

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

    
    //Preencher tabela
    private static final String SQL_SELECT_TODAS_ATIVIDADE_ATRASADAS = "SELECT ID_ATIVIDADE AS CÓDIGO ,NOME AS ATIVIDADE ,"
            + "PROJETO.NOME AS PROJETO,USUARIO.NOME AS ENCARREGADO, DURACAO AS DURAÇÃO, HORAS_TRABALHADAS, CONCLUSAO AS CONCLUSÃO FROM ATIVIDADE "
            + "JOIN USUARIO ON (USUARIO.ID_USUARIO =  ATIVIDADE.ID_USUARIO)"
            + "JOIN PROJETO ON (PROJETO.ID_PROJETO =  ATIVIDADE.ID_PROJETO)"
            + " WHERE HORAS_TRABALHADAS >= DURACAO AND CONCLUSAO < '100' AND USUARIO.ID_DEPARTAMENTO = ?";

    public ResultSet preencherTabelaAtividadeAtrasadas(String codigo) throws SQLException {

        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet resultado = null;

        int id_departamento = selecionarIdDepartamento(codigo);

        try {

            conexao = BancoDadosUtil.getConnection();

            comando = conexao.prepareStatement(SQL_SELECT_TODAS_ATIVIDADE_ATRASADAS);
            comando.setInt(1, id_departamento);

            resultado = comando.executeQuery();

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

        return resultado;

    }

    //Selecionar horas e conclusao
    private static final String SQL_SELECT_HORAS_CONCLUSAO = "SELECT ID_ATIVIDADE, CONCLUSAO, HORAS_TRABALHADAS FROM ATIVIDADE WHERE NOME = ?";

    public Atividade HorasConclusao(String nomeAtividade) throws SQLException {
        Atividade atividade = null;

        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet resultado = null;

        try {

            conexao = BancoDadosUtil.getConnection();

            comando = conexao.prepareStatement(SQL_SELECT_HORAS_CONCLUSAO);

            comando.setString(1, nomeAtividade);

            resultado = comando.executeQuery();

            if (resultado.next()) {
                atividade = new Atividade();
                atividade.setId_atividade(resultado.getInt("ID_ATIVIDADE"));
                atividade.setConclusao(resultado.getDouble("CONCLUSAO"));
                atividade.setHora_trabalhadas(resultado.getDouble("HORAS_TRABALHADAS"));
                
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
    
    //Selecionar atividades para relatório
    private static final String SQL_SELECT_RELATORIO_ATIVIDADE_PROJETO = "SELECT ID_ATIVIDADE,NOME, DURACAO,HORAS_TRABALHADAS,CONCLUSAO, USUARIO.NOME, PROJETO.NOME,"
            + "(SELECT COUNT(ID_ATIVIDADE) FROM ATIVIDADE WHERE ATIVIDADE.ID_PROJETO = PROJETO.ID_PROJETO ) AS QTD_ATIVIDADE_PROJETO, "
            + "(SELECT COUNT(ID_ATIVIDADE) FROM ATIVIDADE WHERE PROJETO.ID_PROJETO = ATIVIDADE.ID_PROJETO AND CONCLUSAO = '100') AS QTD_ATIVIDADE_CONCLUIDA, "
            + "(SELECT COUNT(ID_ATIVIDADE) FROM ATIVIDADE WHERE PROJETO.ID_PROJETO = ATIVIDADE.ID_PROJETO AND HORAS_TRABALHADAS > '0'AND CONCLUSAO < '100') AS QTD_ATIVIDADE_INICIADA, "
            + "(SELECT COUNT(ID_ATIVIDADE) FROM ATIVIDADE WHERE PROJETO.ID_PROJETO = ATIVIDADE.ID_PROJETO AND CONCLUSAO IS NULL) AS QTD_ATIVIDADE_NAO_CONCLUIDA "
            + "FROM ATIVIDADE "
            + "JOIN PROJETO ON (PROJETO.ID_PROJETO =  ATIVIDADE.ID_PROJETO)"
            + "JOIN DEPARTAMENTOS ON (DEPARTAMENTOS.ID_DEPARTAMENTO =  PROJETO.ID_DEPARTAMENTO)"
            + "JOIN USUARIO ON (ATIVIDADE.ID_USUARIO =  USUARIO.ID_USUARIO)"
            + "WHERE PROJETO.NOME = ?";
    public ArrayList<RelatorioAtividadeProjeto> SelecionarAtividade(String nomeProjeto) throws SQLException {
        ArrayList<RelatorioAtividadeProjeto> listaTodasAtividadeProjeto = new ArrayList<>();
        RelatorioAtividadeProjeto relatorioAtividadeProjeto = null;

        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet resultado = null;

        try {

            conexao = BancoDadosUtil.getConnection();

            comando = conexao.prepareStatement(SQL_SELECT_RELATORIO_ATIVIDADE_PROJETO);
            comando.setString(1, nomeProjeto);

            resultado = comando.executeQuery();
            listaTodasAtividadeProjeto.removeAll(listaTodasAtividadeProjeto);

            //percorrendo os registros encontrados  
            if (resultado.next()) {
                listaTodasAtividadeProjeto = new ArrayList<RelatorioAtividadeProjeto>();
                do {
                    //instanciando objeto   
                    relatorioAtividadeProjeto = new RelatorioAtividadeProjeto();


                    /*setando atributos de acordo com os seus tipos primitivos*/
                    relatorioAtividadeProjeto.setIdAtividade(resultado.getInt("ID_ATIVIDADE"));
                    relatorioAtividadeProjeto.setNome(resultado.getString("NOME"));
                    relatorioAtividadeProjeto.setDuracao(resultado.getFloat("DURACAO"));
                    relatorioAtividadeProjeto.setConclusao(resultado.getFloat("CONCLUSAO"));
                    relatorioAtividadeProjeto.setHorasTrabalhadas(resultado.getFloat("HORAS_TRABALHADAS"));
                    relatorioAtividadeProjeto.setEncarregado(resultado.getString("USUARIO.NOME"));
                    relatorioAtividadeProjeto.setProjeto(resultado.getString("PROJETO.NOME"));
                    relatorioAtividadeProjeto.setTotalAtividades(resultado.getInt("QTD_ATIVIDADE_PROJETO"));
                    relatorioAtividadeProjeto.setAtividadesConcluidas(resultado.getInt("QTD_ATIVIDADE_CONCLUIDA"));
                    relatorioAtividadeProjeto.setAtividadesNaoIniciadas(resultado.getInt("QTD_ATIVIDADE_NAO_CONCLUIDA"));
                    relatorioAtividadeProjeto.setAtividadesIniciadas(resultado.getInt("QTD_ATIVIDADE_INICIADA"));

                    //add a lista de objetos encontrados e setados  
                    listaTodasAtividadeProjeto.add(relatorioAtividadeProjeto);
                } while (resultado.next());
            }

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
        return listaTodasAtividadeProjeto;
    }
}
