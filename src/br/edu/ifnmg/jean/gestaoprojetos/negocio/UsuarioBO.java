/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.jean.gestaoprojetos.negocio;

import br.edu.ifnmg.jean.gestaoprojetos.dados.UsuarioDAO;
import br.edu.ifnmg.jean.gestaoprojetos.entidades.Departamento;
import br.edu.ifnmg.jean.gestaoprojetos.entidades.Usuario;
import java.sql.ResultSet;
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

    //Verificar gerente
    public String validarGerente(Usuario Gerente) throws SQLException {
        String mensagem = null;
        UsuarioDAO userDAO = new UsuarioDAO();

        Usuario GerenteExistente = userDAO.selecionarUsuarioEmail(Gerente.getEmail());

        //Verificar se já exirte usuario cadastrardo com o mesmo email
        if (GerenteExistente == null) {

            Usuario gerentePorDepartmento = userDAO.selecionarGerentePorDepartamento(Gerente.getDepartamento().getCodigo(), Gerente.getCargo(), Gerente.getId_usuario());

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

            if (usuarioLogado.getCargo().equals("Diretor")) {
                userDAO.CadastrarUsuario(encarregado);
            }

        } else {
            mensagem = "Já existe um usuario cadastrado com este email!";
        }

        return mensagem;
    }

    //preencher tabela gerente
    public ResultSet ConfigurarTabelaGerente() throws SQLException {
        UsuarioDAO Gerente = new UsuarioDAO();
        ResultSet resultPreencherTabela = Gerente.preencherTabelaGerente();

        return resultPreencherTabela;

    }

    //Excluir usuário
    public void ExcluirUsuario(Usuario usuario) throws SQLException {

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        usuarioDAO.ExcluirUsuario(usuario);

    }

    //Valida dados  btnSalvar
    public String validaDadosSalvar(String nome, String email) {
        String mensagem = null;

        if (validaEmail(email) == false) {
            mensagem = "E-mail inválido";
        }

        if (nome.length() < 3) {
            mensagem = "Nome muito curto";
        }

        if (nome.isEmpty() || email.isEmpty()) {
            mensagem = "Prrencha todos os campos!";
        }

        return mensagem;
    }

    //Atualizar usuario
    public String atualizarUsuario(Usuario user) throws SQLException {
        String mensagem = null;

        UsuarioDAO userDAO = new UsuarioDAO();

        //Verifica se existe um usuario com o email difitado
        Usuario usuarioExistente = userDAO.selecionarUsuarioEmailAtualizar(user.getEmail(), user.getId_usuario());

        if (usuarioExistente == null) {
            System.out.println("id "+user.getId_usuario());
            Usuario usuarioPorDepartmento = userDAO.selecionarGerentePorDepartamento(user.getDepartamento().getCodigo(), user.getCargo(), user.getId_usuario());
            if (usuarioPorDepartmento == null) {
                UsuarioDAO usuarioDAO = new UsuarioDAO();
                usuarioDAO.AtulizaUsuario(user);
            } else {
                mensagem = "Este departamento já possui um gerente!";
            }
        } else {
            mensagem = "Já existe um usuario cadastrado com este email!";
        }
        return mensagem;
    }
    
    //Atualizar usuario
    public String atualizarEncarregado(Usuario user) throws SQLException {
        String mensagem = null;

        UsuarioDAO userDAO = new UsuarioDAO();

        //Verifica se existe um usuario com o email difitado
        Usuario usuarioExistente = userDAO.selecionarUsuarioEmailAtualizar(user.getEmail(), user.getId_usuario());

        if (usuarioExistente == null) {
           
                UsuarioDAO usuarioDAO = new UsuarioDAO();
                usuarioDAO.AtulizaUsuario(user);
            
        } else {
            mensagem = "Já existe um usuario cadastrado com este email!";
        }
        return mensagem;
    }

    //cadastar alteraçao no perfil
    public String atualizaPerfil(Usuario user) throws SQLException {
        String mensagem = null;

        UsuarioDAO userDAO = new UsuarioDAO();
        //Verifica se existe um usuario com o email difitado
        Usuario usuarioExistente = userDAO.selecionarUsuarioEmailAtualizar(user.getEmail(), user.getId_usuario());
        if (usuarioExistente == null) {
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            usuarioDAO.AtulizaPerfil(user);
        } else {
            mensagem = "Já existe um usuario cadastrado com este email!";
        }
        return mensagem;
    }
    
    public ResultSet preencheTabelaEncarregado(String Departamento) throws SQLException {
        
        UsuarioDAO Encarregado = new UsuarioDAO();
        ResultSet resultPreencherTabela = Encarregado.preencherTabelaEncarregado(Departamento);

        return resultPreencherTabela;

    }
}
