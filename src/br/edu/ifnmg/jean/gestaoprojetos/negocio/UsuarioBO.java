/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.jean.gestaoprojetos.negocio;

import br.edu.ifnmg.jean.gestaoprojetos.dados.UsuarioDAO;
import br.edu.ifnmg.jean.gestaoprojetos.entidades.Usuario;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author JEAN CARLOS
 */
public class UsuarioBO {

    public String validaDados(String nome, String email, String senha, String connfirmaSenha, String departamento) {
        String mensagem = null;

        if (departamento.equals("Selecione")) {
            mensagem = "Selecione um departamento!";
        }

        if (validaSenha(senha, connfirmaSenha) == false) {
            mensagem = "As senhas digitadas não conferem!!";
        }

        if (validaEmail(email) == false) {
            mensagem = "E-mail inválido";
        }

        if (nome.length() < 3) {
            mensagem = "Nome muito curto";
        }

        if (nome.isEmpty() || email.isEmpty() || senha.isEmpty() || connfirmaSenha.isEmpty() || departamento.equals("Selecione")) {
            mensagem = "Prrencha todos os campos!";
        }

        return mensagem;
    }

    //Validar se as senhas digitadas são iguais
    public boolean validaSenha(String senha, String confirmaSenha) {

        if (senha.equals(confirmaSenha)) {
            return true;
        }
        return false;
    }

    public boolean validaEmail(String email) {

        // Validar E-mail
        Pattern p = Pattern.compile("^[\\w-]+(\\.[\\w-]+)*@([\\w-]+\\.)+[a-zA-Z]{2,7}$");
        Matcher m = p.matcher(email);
        if (m.find()) {
            return true;
        }
        return false;
    }

    //Vericar gerente
    public String validarGerente(Usuario Gerente) throws SQLException {
        String mensagem = null;
        UsuarioDAO userDAO = new UsuarioDAO();

        Usuario GerenteExistente = userDAO.selecionarUsuarioEmail(Gerente.getEmail());

        //Verificar se já exirte usuario cadastrardo com o mesmo email
        if (GerenteExistente == null) {

            Usuario gerentePorDepartmento = userDAO.selecionarGerentePorDepartamento(Gerente.getDepartamento().getCodigo(), Gerente.getCargo());

            //Verificar se o departamento selecionado não possui um gerente
            if (gerentePorDepartmento == null) {

                userDAO.CadastrarUsuario(Gerente);

            } else {
                mensagem = "Já existe um gerente cadastrado para o departamento selecionado!";
            }
        } else {
            mensagem = "Já existe um usuario cadastrado com este email!";
        }
        return mensagem;
    }

    //Verifica encarregado
    public String validaEncarregado(Usuario encarregado, Usuario usuarioLogado) throws SQLException {
        String mensagem = null;
        UsuarioDAO userDAO = new UsuarioDAO();

        //Verifica se existe um usuario com o email difitado
        Usuario EncarregadoExistente = userDAO.selecionarUsuarioEmail(encarregado.getEmail());

        if (EncarregadoExistente == null) {
            if (usuarioLogado.getCargo().equals("Gerente")) {
                if (usuarioLogado.getDepartamento().getCodigo().equals(encarregado.getDepartamento().getCodigo())) {
                  userDAO.CadastrarUsuario(encarregado);
                }
            }
            
            if(usuarioLogado.getCargo().equals("Diretor")){
                userDAO.CadastrarUsuario(encarregado);
            }

        } else {
            mensagem = "Já existe um usuario cadastrado com este email!";
        }

        return mensagem;
    }
}
