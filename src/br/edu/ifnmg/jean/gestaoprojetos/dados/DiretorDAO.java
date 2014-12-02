/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.jean.gestaoprojetos.dados;

import br.edu.ifnmg.jean.gestaoprojetos.entidades.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author JEAN CARLOS
 */
public class DiretorDAO {

    //SQL Inserir diretor no banco de dados
    private static final String SQL_INSERT_DIRETOR = "INSERT INTO USUARIO( NOME, CARGO, SENHA, EMAIL)VALUES (?,?,?,?)";

    public void criaDiretor(Usuario user) throws SQLException {
        Connection conexao = null;
        PreparedStatement comando = null;

        try {

            conexao = BancoDadosUtil.getConnection();
            comando = conexao.prepareStatement(SQL_INSERT_DIRETOR);

            comando.setString(1, user.getNome());
            comando.setString(2, user.getCargo());
            comando.setString(3, user.getSenha());
            comando.setString(4, user.getEmail());

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
    // Fim SQL Inserir diretor no banco de dados
    
    //SQL Consultar se existe diretor cadastrado
    private static final String SQL_SELECT_DIRETOR = "SELECT  NOME, CARGO, SENHA, EMAIL FROM USUARIO WHERE CARGO LIKE ?";
    
    public Usuario selecionarDiretor() throws SQLException {
        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet resultado = null;
        Usuario user = null;

        try {

            conexao = BancoDadosUtil.getConnection();

            comando = conexao.prepareStatement(SQL_SELECT_DIRETOR);
            comando.setString(1, "Diretor");
            resultado = comando.executeQuery();

            if (resultado.next()) {
                user = new Usuario();
                user.setNome(resultado.getString("NOME"));
                user.setCargo(resultado.getString("CARGO"));
                user.setSenha(resultado.getString("SENHA"));
                user.setEmail(resultado.getString("EMAIL"));

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
        return user;
    }
    //Fim Consultar se existe diretor cadastrado 
}
