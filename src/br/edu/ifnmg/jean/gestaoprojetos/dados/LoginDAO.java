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
public class LoginDAO {
    
    private static final String SQL_SELECT_LOGIN = "SELECT NOME, CARGO, SENHA, EMAIL, ID_DEPARTAMENTO, ID_USUARIO FROM USUARIO WHERE EMAIL LIKE ? AND SENHA LIKE ?";

    //Selecionar usuario
    public Usuario selecionarLogin(String login, String Senha) throws SQLException {
        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet resultado = null;
        Usuario user = null;

        try {

            conexao = BancoDadosUtil.getConnection();

            comando = conexao.prepareStatement(SQL_SELECT_LOGIN);
            comando.setString(1, login);
            comando.setString(2, Senha);

            resultado = comando.executeQuery();

            if (resultado.next()) {
                user = new Usuario();

                user.setNome(resultado.getString("NOME"));
                user.setCargo(resultado.getString("CARGO"));
                user.setSenha(resultado.getString("SENHA"));
                user.setEmail(resultado.getString("EMAIL"));
                user.setId_usuario(resultado.getInt("ID_USUARIO"));
            

                
                DepartamentoDAO depDAO = new DepartamentoDAO();
                user.setDepartamento(depDAO.selecionarDepartamentoPorCodigo(resultado.getString("ID_DEPARTAMENTO")));

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
        return user;

    }
}
