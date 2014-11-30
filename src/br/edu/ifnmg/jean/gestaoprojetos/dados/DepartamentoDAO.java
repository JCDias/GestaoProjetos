/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.jean.gestaoprojetos.dados;

import br.edu.ifnmg.jean.gestaoprojetos.entidades.Departamento;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author JEAN CARLOS
 */
public class DepartamentoDAO {

    private static final String SQL_SELECT_DEPARTAMENTO_POR_COD = "SELECT CODIGO, NOME FROM DEPARTAMENTOS WHERE DEPARTAMENTOS.ID_DEPARTAMENTO = ?";

    public Departamento selecionarDepartamentoPorCodigo(String ID_DEPARTAMENTO) throws SQLException {
        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet resultado = null;
        Departamento DEP = null;

        try {

            conexao = BancoDadosUtil.getConnection();

            comando = conexao.prepareStatement(SQL_SELECT_DEPARTAMENTO_POR_COD);
            comando.setString(1, ID_DEPARTAMENTO);

            resultado = comando.executeQuery();

            if (resultado.next()) {
                DEP = new Departamento();

                DEP.setNome(resultado.getString("NOME"));
                DEP.setCodigo(resultado.getString("CODIGO"));

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
        return DEP;
    }
    private static final String SQL_SELECT_DEPARTAMENTO = "SELECT NOME, CODIGO FROM DEPARTAMENTOS WHERE NOME = ? OR CODIGO = ?";

    //Verifica se já existe um departamento com o nome ou código digitado
    public Departamento selecDepartamento(String NOME, String CODIGO) throws SQLException {
        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet resultado = null;
        Departamento DEP = null;

        try {

            conexao = BancoDadosUtil.getConnection();

            comando = conexao.prepareStatement(SQL_SELECT_DEPARTAMENTO);
            comando.setString(1, NOME);
            comando.setString(2, CODIGO);

            resultado = comando.executeQuery();

            if (resultado.next()) {
                DEP = new Departamento();

                DEP.setNome(resultado.getString("NOME"));
                DEP.setCodigo(resultado.getString("CODIGO"));

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
        return DEP;
    }

    private static final String SQL_INSERT_DEPARTAMENTO = "INSERT INTO DEPARTAMENTOS(NOME, CODIGO)VALUES (?,?)";

    //Inserir um novo departamento
    public void criarDepartamento(Departamento Dep) throws SQLException {
        Connection conexao = null;
        PreparedStatement comando = null;

        try {

            conexao = BancoDadosUtil.getConnection();
            comando = conexao.prepareStatement(SQL_INSERT_DEPARTAMENTO);
            comando.setString(1, Dep.getNome());
            comando.setString(2, Dep.getCodigo());

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

    private static final String SQL_SELECT_TODOS_DEPARTAMENTOS = "SELECT CODIGO as Código, NOME as Departamento FROM DEPARTAMENTOS";

    //Preencher tabela 
    public ResultSet PreencheTabelaDepartamentos() throws SQLException {
        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet resultado = null;
        Departamento DEP = null;

        try {

            conexao = BancoDadosUtil.getConnection();

            comando = conexao.prepareStatement(SQL_SELECT_TODOS_DEPARTAMENTOS);

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

    //Atualizar departamentos
    private static final String SQL_UPDATE_DEPARTAMENTO = "UPDATE DEPARTAMENTOS SET CODIGO =  ?, NOME  = ? WHERE  CODIGO = ?";

    public void atualizarDepartamento(Departamento departamento, String codigo) throws SQLException {
        Connection conexao = null;
        PreparedStatement comando = null;

        try {

            conexao = BancoDadosUtil.getConnection();
            comando = conexao.prepareStatement(SQL_UPDATE_DEPARTAMENTO);

            comando.setString(1, departamento.getCodigo());
            comando.setString(2, departamento.getNome());
            comando.setString(3, codigo);

            comando.executeUpdate();
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

    //Deletar departamento
    private static final String SQL_DELETE_DEPARTAMENTO = "DELETE FROM DEPARTAMENTOS WHERE  CODIGO = ?";

    public void excluirDepartamento(String Cod_Departamento) throws SQLException {
        Connection conexao = null;
        PreparedStatement comando = null;

        try {

            conexao = BancoDadosUtil.getConnection();
            comando = conexao.prepareStatement(SQL_DELETE_DEPARTAMENTO);

            comando.setString(1, Cod_Departamento);

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
    
    //Selecionar todos os departamentos
     private static final String SQL_SELECT_TODOS = "SELECT CODIGO, NOME FROM DEPARTAMENTOS";
    public Departamento selecionarTodosDepartamentos() throws SQLException {
        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet resultado = null;
        Departamento DEP = null;

        try {

            conexao = BancoDadosUtil.getConnection();

            comando = conexao.prepareStatement(SQL_SELECT_TODOS);

            resultado = comando.executeQuery();

            if (resultado.next()) {
                DEP = new Departamento();

                DEP.setNome(resultado.getString("NOME"));
                DEP.setCodigo(resultado.getString("CODIGO"));

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
        return DEP;
    }
    
    //Retorna uma lista de departamentos
    public ArrayList<String> comboBoxDepartamentos() throws SQLException {
        ArrayList<String> Departamentos = new ArrayList<>();

        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet resultado = null;

        try {

            conexao = BancoDadosUtil.getConnection();
            comando = conexao.prepareStatement(SQL_SELECT_TODOS);

            resultado = comando.executeQuery();
            Departamentos.removeAll(Departamentos);

            while (resultado.next()) {
                Departamentos.add(resultado.getString("NOME"));
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
        return Departamentos;
    }
    
    //Selecionar departamento por nome
    private static final String SQL_SELECT_DEPARTAMENTO_POR_NOME = "SELECT CODIGO, NOME FROM DEPARTAMENTOS WHERE DEPARTAMENTOS.NOME LIKE ?";
    
    public Departamento selecionarDepartamentoPorNome(String NOME_DEPARTAMENTO) throws SQLException {
        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet resultado = null;
        Departamento DEP = null;

        try {

            conexao = BancoDadosUtil.getConnection();

            comando = conexao.prepareStatement(SQL_SELECT_DEPARTAMENTO_POR_NOME);
            comando.setString(1, NOME_DEPARTAMENTO);

            resultado = comando.executeQuery();

            if (resultado.next()) {
                DEP = new Departamento();

                DEP.setNome(resultado.getString("NOME"));
                DEP.setCodigo(resultado.getString("CODIGO"));

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
        return DEP;
    }
}
