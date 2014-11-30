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
    private static final String SQL_SELECT_USUARIO_EMAIL = "SELECT  NOME, CARGO, SENHA, EMAIL, ID_DEPARTAMENTO , ID_USUARIO FROM USUARIO WHERE EMAIL = ?";
    public Usuario selecionarUsuarioEmail(String email) throws SQLException {
        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet resultado = null;
        Usuario user = null;

        try {

            conexao = BancoDadosUtil.getConnection();

            comando = conexao.prepareStatement(SQL_SELECT_USUARIO_EMAIL);
            comando.setString(1, email);
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
    private static final String SQL_SELECT_GERENTE_POR_DEPARTAMENTO = "SELECT NOME, EMAIL, CARGO FROM USUARIO JOIN DEPARTAMENTOS ON USUARIO.ID_DEPARTAMENTO = DEPARTAMENTOS.ID_DEPARTAMENTO WHERE USUARIO.ID_DEPARTAMENTO = ? AND USUARIO.CARGO = ?";
    public Usuario selecionarGerentePorDepartamento(String CodDepartamento, String cargo) throws SQLException {
        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet resultado = null;
        Usuario user = null;
        System.out.println("Código departamento "+CodDepartamento);
        int id_departamento = selecionarIdDepartamento(CodDepartamento);
        System.out.println("Valor do Id_departamento "+id_departamento);
        try {

            conexao = BancoDadosUtil.getConnection();

            comando = conexao.prepareStatement(SQL_SELECT_GERENTE_POR_DEPARTAMENTO);
            comando.setInt(1, id_departamento);
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
        
        int id_departamento = selecionarIdDepartamento(user.getDepartamento().getCodigo());
        
        try {

            conexao = BancoDadosUtil.getConnection();

            
                comando = conexao.prepareStatement(SQL_INSERT_USUARIO);

            comando.setString(1, user.getNome());
            comando.setString(2, user.getCargo());
            comando.setString(3, user.getSenha());
            comando.setString(4, user.getEmail());
            comando.setInt(5, id_departamento);

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
    
    private static final String SQL_SELECT_ID_DEPARTAMENTO_CODIGO = "SELECT ID_DEPARTAMENTO FROM DEPARTAMENTOS WHERE CODIGO = ?";
    private int selecionarIdDepartamento(String codigo) throws SQLException{
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
}
