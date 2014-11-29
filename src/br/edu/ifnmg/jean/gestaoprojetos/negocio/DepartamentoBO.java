/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.jean.gestaoprojetos.negocio;

import br.edu.ifnmg.jean.gestaoprojetos.dados.DepartamentoDAO;
import br.edu.ifnmg.jean.gestaoprojetos.entidades.Departamento;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author JEAN CARLOS
 */
public class DepartamentoBO {
    
    public String validaDados(String codigo, String nome){
        String mensagem = null;
        
        if(codigo.length()!=3){
            mensagem = "O código do departamento deve conter 3 caracteres!";
        }
        
        if(codigo.isEmpty() || nome.isEmpty()){
            mensagem = "preencha todos os campos!";
        }
        
        return mensagem;
    }
    
    public Departamento VerificarDepartamento(Departamento DEP) throws SQLException {

        DepartamentoDAO depDAO = new DepartamentoDAO();
        Departamento DepExistente = null;

        DepExistente = depDAO.selecDepartamento(DEP.getNome(), DEP.getCodigo());
        
        return DepExistente;
    }
    
    public void cadastrarDepartamento(Departamento departamento) throws SQLException{
        
        DepartamentoDAO departamentoDAO = new DepartamentoDAO();
        departamentoDAO.criarDepartamento(departamento);
    }
    
    public ResultSet preencherTabela() throws SQLException {
        DepartamentoDAO departamentoDAO = new DepartamentoDAO();
        ResultSet resultadoPreencheTabela = departamentoDAO.PreencheTabelaDepartamentos();

        return resultadoPreencheTabela;
    }
}
