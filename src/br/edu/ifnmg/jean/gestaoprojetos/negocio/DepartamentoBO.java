/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.jean.gestaoprojetos.negocio;

import br.edu.ifnmg.jean.gestaoprojetos.dados.DepartamentoDAO;
import br.edu.ifnmg.jean.gestaoprojetos.entidades.Departamento;
import br.edu.ifnmg.jean.gestaoprojetos.excecoes.CamposVaziosException;
import br.edu.ifnmg.jean.gestaoprojetos.excecoes.DadoInvalidoException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author JEAN CARLOS
 */
public class DepartamentoBO {
    
    public void validaDados(String codigo, String nome){
        
        if(codigo.length()!=3){
            throw new DadoInvalidoException();
        }
        
        if(codigo.isEmpty() || nome.isEmpty()){
            throw new CamposVaziosException();
        }
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
    
    public String atualizarDpto(Departamento DEP) throws SQLException {
        String mensagem = null;
                
        DepartamentoDAO departamentoDAO = new DepartamentoDAO();
        Departamento DepExistente = null;

        DepExistente = departamentoDAO.selecDepartamento(DEP.getNome(), DEP.getCodigo());

        if (DepExistente == null || DepExistente.getCodigo().equals(DEP.getCodigo())) {
            departamentoDAO.atualizarDepartamento(DEP, DEP.getCodigo());
            
            mensagem = "Departamento alterado com sucesso!";
        } else {
           mensagem = "Erro ao alterar o depertamento!";
        }
        return mensagem;
    }
    
     public void excluirDepartamento(String codigo) throws SQLException{
        DepartamentoDAO departamentoDAO = new DepartamentoDAO();
        departamentoDAO.excluirDepartamento(codigo);

    }
     
    public Departamento selecionarTodosDepratamentos() throws SQLException{
        DepartamentoDAO departamentoDAO = new DepartamentoDAO();
        Departamento departamento = null;
        
        departamento = departamentoDAO.selecionarTodosDepartamentos();
        
        return departamento;
    }
    
    //Configurar ComboBox
    public ArrayList<String> ComboBoxDepartamentos() throws SQLException {

        DepartamentoDAO depDAO = new DepartamentoDAO();
        ArrayList<String> Departamentos = new ArrayList<>();

        Departamentos = depDAO.comboBoxDepartamentos();

        return Departamentos;

    }
    
    //Selecionar departamento por nome
    public Departamento selecionarDepartamentoPorNome(String NomeDepartamento) throws SQLException {

        DepartamentoDAO depDAO = new DepartamentoDAO();
        Departamento departamento = new Departamento();

        departamento = depDAO.selecionarDepartamentoPorNome(NomeDepartamento);

        return departamento;

    }
}
