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

/**
 *
 * @author JEAN CARLOS
 */
public class DepartamentoDAO {
    
    private static final String SQL_SELECT_DEPARTAMENTO_POR_COD = "SELECT CODIGO, NOME FROM DEPARTAMENTOS WHERE DEPARTAMENTOS.ID_DEPARTAMENTO = ?";
    
    public Departamento selecionarDepartamentoPorCodigo(String COD_DEPARTAMENTO) throws SQLException {
        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet resultado = null;
        Departamento DEP = null;

        try {

            conexao = BancoDadosUtil.getConnection();

            comando = conexao.prepareStatement(SQL_SELECT_DEPARTAMENTO_POR_COD);
            comando.setString(1, COD_DEPARTAMENTO);

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
}
