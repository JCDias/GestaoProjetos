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

    //Selecionar usuario que tem  email
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
    private static final String SQL_SELECT_GERENTE_POR_DEPARTAMENTO = "SELECT NOME, EMAIL, CARGO FROM USUARIO JOIN DEPARTAMENTOS ON USUARIO.ID_DEPARTAMENTO = DEPARTAMENTOS.ID_DEPARTAMENTO WHERE USUARIO.ID_DEPARTAMENTO = ? AND USUARIO.CARGO = ? AND USUARIO.ID_USUARIO <> ?";

    public Usuario selecionarGerentePorDepartamento(String CodDepartamento, String cargo, int id_usuario) throws SQLException {
        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet resultado = null;
        Usuario user = null;

        int id_departamento = selecionarIdDepartamento(CodDepartamento);

        try {

            conexao = BancoDadosUtil.getConnection();

            comando = conexao.prepareStatement(SQL_SELECT_GERENTE_POR_DEPARTAMENTO);
            comando.setInt(1, id_departamento);
            comando.setString(2, cargo);
            comando.setInt(2, id_usuario);
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

    //Preencher a tabela de gerentes
    private static final String SQL_SELECT_TODOS_GERENTE = "SELECT ID_USUARIO AS Código, NOME ,CARGO AS Cargo,DEPARTAMENTOS.NOME AS Departamento, EMAIL AS Email FROM USUARIO join DEPARTAMENTOS on (USUARIO.ID_DEPARTAMENTO =  DEPARTAMENTOS.ID_DEPARTAMENTO) WHERE CARGO = 'Gerente' ORDER BY NOME";

    public ResultSet preencherTabelaGerente() throws SQLException {

        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet resultado = null;

        try {

            conexao = BancoDadosUtil.getConnection();

            comando = conexao.prepareStatement(SQL_SELECT_TODOS_GERENTE);

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

    //Excluir usuarios ou encarregados
    private static final String SQL_DELETE = "DELETE FROM USUARIO WHERE ID_USUARIO = ?";

    public void ExcluirUsuario(Usuario usuario) throws SQLException {
        Connection conexao = null;
        PreparedStatement comando = null;

        try {

            conexao = BancoDadosUtil.getConnection();
            comando = conexao.prepareStatement(SQL_DELETE);
            comando.setInt(1, usuario.getId_usuario());

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

    //Selecionar usuario que tem  email
    private static final String SQL_SELECT_USUARIO_EMAIL_ATUALIZAR = "SELECT  NOME, CARGO, SENHA, EMAIL, ID_DEPARTAMENTO , ID_USUARIO FROM USUARIO WHERE EMAIL = ? AND ID_USUARIO <> ?";

    public Usuario selecionarUsuarioEmailAtualizar(String email, int id_usuario) throws SQLException {
        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet resultado = null;
        Usuario user = null;

        try {

            conexao = BancoDadosUtil.getConnection();

            comando = conexao.prepareStatement(SQL_SELECT_USUARIO_EMAIL_ATUALIZAR);
            comando.setString(1, email);
            comando.setInt(1, id_usuario);
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

    //Atualizar usuario
    private static final String SQL_ATUALIZAR_COM_SENHA = "UPDATE USUARIO SET USUARIO.NOME = ?, USUARIO.EMAIL = ?, USUARIO.CARGO = ?, USUARIO.ID_DEPARTAMENTO = ?, USUARIO.SENHA = ? WHERE ID_USUARIO = ?";
    private static final String SQL_ATUALIZAR_SEM_SENHA = "UPDATE USUARIO SET USUARIO.NOME = ?, USUARIO.EMAIL = ?, USUARIO.CARGO = ?, USUARIO.ID_DEPARTAMENTO = ? WHERE ID_USUARIO = ?";

    public void AtulizaUsuario(Usuario user) throws SQLException {
        Connection conexao = null;
        PreparedStatement comando = null;

        int id_departamento = selecionarIdDepartamento(user.getDepartamento().getCodigo());

        try {

            conexao = BancoDadosUtil.getConnection();
            if (user.getSenha() == null) {
                comando = conexao.prepareStatement(SQL_ATUALIZAR_SEM_SENHA);
                comando.setInt(5, user.getId_usuario());
            } else {
                comando = conexao.prepareStatement(SQL_ATUALIZAR_COM_SENHA);
                comando.setString(5, user.getSenha());
                comando.setInt(6, user.getId_usuario());
            }

            comando.setString(1, user.getNome());
            comando.setString(2, user.getEmail());
            comando.setString(3, user.getCargo());
            comando.setInt(4, id_departamento);

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

    //Atualizar perfil 
    private static final String SQL_ATUALIZAR_PERFIL = "UPDATE USUARIO SET USUARIO.NOME = ?, USUARIO.EMAIL = ?, USUARIO.SENHA = ? WHERE ID_USUARIO = ?";

    public void AtulizaPerfil(Usuario user) throws SQLException {
        Connection conexao = null;
        PreparedStatement comando = null;

        try {

            conexao = BancoDadosUtil.getConnection();

            comando = conexao.prepareStatement(SQL_ATUALIZAR_PERFIL);

            comando.setString(1, user.getNome());
            comando.setString(2, user.getEmail());
            comando.setString(3, user.getSenha());
            comando.setInt(4, user.getId_usuario());
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

    //Preencher tabela encarragado
    private static final String SQL_SELECT_TODOS_ENCARREGADO_TABELA = "SELECT ID_USUARIO AS Código, NOME ,CARGO, DEPARTAMENTOS.NOME AS Departamento, EMAIL AS Email FROM USUARIO "
            + "join DEPARTAMENTOS on (USUARIO.ID_DEPARTAMENTO =  DEPARTAMENTOS.ID_DEPARTAMENTO)"
            + "WHERE CARGO = 'Encarregado' AND DEPARTAMENTOS.NOME = ? ORDER BY NOME";

    public ResultSet preencherTabelaEncarregado(String Departamento) throws SQLException {

        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet resultado = null;

        try {

            conexao = BancoDadosUtil.getConnection();

            comando = conexao.prepareStatement(SQL_SELECT_TODOS_ENCARREGADO_TABELA);
            comando.setString(1, Departamento);

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
}
