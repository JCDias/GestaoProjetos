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
public class UsuarioDAO {
    
    //Selecionar gerente
    private static final String SQL_SELECT_GERENTE = "SELECT  NOME, CARGO, SENHA, EMAIL, ID_DEPARTAMENTO , ID_USUARIO FROM USUARIO WHERE NOME = ? AND CARGO = ?";
    public Usuario selecionarGerente(String Nome, String cargo) throws SQLException {
        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet resultado = null;
        Usuario user = null;

        try {

            conexao = BancoDadosUtil.getConnection();

            comando = conexao.prepareStatement(SQL_SELECT_GERENTE);
            comando.setString(1, Nome);
            comando.setString(2, cargo);
            resultado = comando.executeQuery();

            if (resultado.next()) {
                user = new Usuario();
                user.setNome(resultado.getString("NOME"));
                user.setCargo(resultado.getString("CARGO"));
                user.setSenha(resultado.getString("SENHA"));
                user.setEmail(resultado.getString("EMAIL"));
                user.setId_usuario(resultado.getInt("ID_USUARIO"));

                DepartamentoDAO depDAO = new DepartamentoDAO();
                user.setDepartamento(depDAO.selecionarDepartamentoPorCodigo(resultado.getString("CODIGO")));

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
    
    //Verificar se já existe gerente para o departamento escolhido
    private static final String SQL_SELECT_GERENTE_POR_DEPARTAMENTO = "SELECT  NOME, EMAIL, SENHA, CARGO, DEPARTAMENTOS.NOME, ID_USUARIO FROM USUARIO "
            + "inner join DEPARTAMENTOS on (DEPARTAMENTOS.CODIGO =  USUARIO.ID_DEPARTAMENTO)"
            + "WHERE USUARIO.CARGO =  ?  AND  USUARIO.ID_DEPARTAMENTO = ?";
    public Usuario selecionarGerentePorDepartamento(String CodDepartamento, String Tipo) throws SQLException {
        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet resultado = null;
        Usuario user = null;

        try {

            conexao = BancoDadosUtil.getConnection();

            comando = conexao.prepareStatement(SQL_SELECT_GERENTE_POR_DEPARTAMENTO);
            comando.setString(1, Tipo);
            comando.setString(2, CodDepartamento);
            resultado = comando.executeQuery();

            if (resultado.next()) {
                user = new Usuario();
                user.setNome(resultado.getString("NOME"));
                user.setCargo(resultado.getString("Cargo"));
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
    
    //Inserir usuario
    private static final String SQL_INSERT_USUARIO = "INSERT INTO USUARIO( NOME, CARGO, SENHA, EMAIL,ID_DEPARTAMENTO )VALUES (?,?,?,?,?)";
    public void CadastrarUsuario(Usuario user) throws SQLException {
        Connection conexao = null;
        PreparedStatement comando = null;
        System.out.println("Dento do cadastar usuario");
        try {

            conexao = BancoDadosUtil.getConnection();

            //verificar porque não esta commitando
                comando = conexao.prepareStatement(SQL_INSERT_USUARIO);

            comando.setString(1, user.getNome());
            comando.setString(2, user.getCargo());
            comando.setString(3, user.getSenha());
            comando.setString(4, user.getEmail());
            comando.setString(5, user.getDepartamento().getCodigo());

            comando.execute();
            conexao.commit();
            System.out.println("depois d comit");
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
}
