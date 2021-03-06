/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.jean.gestaoprojetos.apresentacao;

import br.edu.ifnmg.jean.gestaoprojetos.entidades.Atividade;
import br.edu.ifnmg.jean.gestaoprojetos.entidades.Projeto;
import br.edu.ifnmg.jean.gestaoprojetos.entidades.Usuario;
import br.edu.ifnmg.jean.gestaoprojetos.excecoes.AtividadeExistenteException;
import br.edu.ifnmg.jean.gestaoprojetos.excecoes.CamposVaziosException;
import br.edu.ifnmg.jean.gestaoprojetos.excecoes.DadoInvalidoException;
import br.edu.ifnmg.jean.gestaoprojetos.excecoes.NomeInvalidoException;
import br.edu.ifnmg.jean.gestaoprojetos.negocio.AtividadeBO;
import br.edu.ifnmg.jean.gestaoprojetos.negocio.ProjetoBO;
import br.edu.ifnmg.jean.gestaoprojetos.negocio.UsuarioBO;
import br.edu.ifnmg.jean.gestaoprojetos.utilitarios.configuraLog;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import org.apache.log4j.Appender;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

/**
 *
 * @author JEAN CARLOS
 */
public class CadatroAtividadeForm extends javax.swing.JFrame {

    static Logger logger = null;
    Usuario userLogado = new Usuario();

    public CadatroAtividadeForm(Usuario user) {
        initComponents();
        this.userLogado = user;
        this.setLocationRelativeTo(null);
        this.configuraComboBoxProjeto();
        this.configuraComboBoxEncarregado();
        //Configuração do LOG4J
        try {
            Logger log = Logger.getLogger(this.getClass());
            logger = new configuraLog().configura(log);
        } catch (IOException ex) {

        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        txtNome = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        ComboBoxProjeto = new javax.swing.JComboBox();
        jPanel4 = new javax.swing.JPanel();
        ComboBoxEncarregado = new javax.swing.JComboBox();
        jPanel5 = new javax.swing.JPanel();
        SpinnerDuracao = new javax.swing.JSpinner();
        btnCadastrar = new javax.swing.JButton();
        btnLimpar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

        setTitle("Cadastro Atividade");
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Cadastrar Atividade", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 18))); // NOI18N

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Nome da Atividade", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 0, 14))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtNome)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Projeto", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 0, 14))); // NOI18N

        ComboBoxProjeto.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ComboBoxProjeto, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(13, Short.MAX_VALUE)
                .addComponent(ComboBoxProjeto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Encarregado", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 0, 14))); // NOI18N

        ComboBoxEncarregado.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ComboBoxEncarregado, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
                .addComponent(ComboBoxEncarregado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, " Duração em horas", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 0, 14))); // NOI18N

        SpinnerDuracao.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        SpinnerDuracao.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(0), Integer.valueOf(0), null, Integer.valueOf(1)));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(SpinnerDuracao, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(SpinnerDuracao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(32, Short.MAX_VALUE))
        );

        btnCadastrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/edu/ifnmg/jean/gestaoprojetos/icones/PNG/kfloppy.png"))); // NOI18N
        btnCadastrar.setText("Cadastrar");
        btnCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadastrarActionPerformed(evt);
            }
        });

        btnLimpar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/edu/ifnmg/jean/gestaoprojetos/icones/clear-256.png"))); // NOI18N
        btnLimpar.setText("Limpar");
        btnLimpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimparActionPerformed(evt);
            }
        });

        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/edu/ifnmg/jean/gestaoprojetos/icones/PNG/editdelete.png"))); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnCadastrar)
                        .addGap(33, 33, 33)
                        .addComponent(btnLimpar)
                        .addGap(36, 36, 36)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                    .addComponent(btnLimpar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCadastrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimparActionPerformed
        txtNome.setText("");
        SpinnerDuracao.setValue(0);
        this.configuraComboBoxProjeto();
        this.configuraComboBoxEncarregado();
    }//GEN-LAST:event_btnLimparActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        ConsultarAtividadeForm consAtividade = new ConsultarAtividadeForm(userLogado);
        consAtividade.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastrarActionPerformed
        //Objheto de atividade
        Atividade atividade = new Atividade();

        //receber parâmetros da tela
        String nome = txtNome.getText().trim();
        String projetoSelecionado = ComboBoxProjeto.getSelectedItem().toString();
        String usuario = ComboBoxEncarregado.getSelectedItem().toString();
        double duracao = Double.parseDouble(SpinnerDuracao.getValue().toString());

        AtividadeBO atividadeBO = new AtividadeBO();
        try{
        atividadeBO.validaDados(nome, projetoSelecionado, usuario, duracao);

            //selecionar encarregado pelo nome
            Usuario encarregado = null;

            try {
                UsuarioBO EncarregadoBO = new UsuarioBO();
                encarregado = EncarregadoBO.SelecionarUsuario(usuario, "Encarregado");
            } catch (SQLException ex) {
                logger.error("Erro ao selecionar encarregado " + ex.getMessage());
            }

            //selecionar um projeto pelo nome
            int id_projeto = 0;

            try {
                ProjetoBO projetoBO = new ProjetoBO();
                id_projeto = projetoBO.selectProjetoPorNome(projetoSelecionado);
            } catch (SQLException ex) {
                logger.error("Erro ao selecionar projeto " + ex.getMessage());
            }

            //Setando Atividade
            atividade.setNome(nome);
            atividade.setDuracao(duracao);
            atividade.setEncarregado(encarregado);

            try {
                try  {
                    atividadeBO.InserirAtividade(atividade, userLogado.getDepartamento().getCodigo(), id_projeto);
                    JOptionPane.showMessageDialog(null, "Atividade cadastrada com sucesso!", "Cadastrar Atividade", JOptionPane.INFORMATION_MESSAGE);
                    logger.info("Atividade " + nome + " cadastrada com sucesso");
                    //Verificar se quer cadastrar outra atividade
                    int resp = JOptionPane.showConfirmDialog(this, "Deseja cadastrar outra Atividade?", "Cadastrar Atividade", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if (resp == 0) {
                        btnLimparActionPerformed(evt);
                    } else {
                        ConsultarAtividadeForm consAtividade = new ConsultarAtividadeForm(userLogado);
                        consAtividade.setVisible(true);
                        this.dispose();
                    }
                }catch(AtividadeExistenteException ex){
                    JOptionPane.showMessageDialog(null, "Já existe uma atividade com este nome cadastrada!", "Cadastrar Atividade", JOptionPane.INFORMATION_MESSAGE);
                }
                
            } catch (SQLException ex) {
                logger.error("Erro ao cadastrar atividade " + ex.getMessage());
            }
        }catch(CamposVaziosException ex){
            JOptionPane.showMessageDialog(null, "Preencha todos os campos", "Cadastrar Atividade", JOptionPane.INFORMATION_MESSAGE);
        }catch(DadoInvalidoException ex){
            JOptionPane.showMessageDialog(null, "A duração deve ser maior que zero.", "Cadastrar Atividade", JOptionPane.INFORMATION_MESSAGE);
        }catch(NomeInvalidoException ex){
            JOptionPane.showMessageDialog(null, "Nome muito curto o nome deve conter no minimo 3 caracteres", "Cadastrar Atividade", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnCadastrarActionPerformed

    //Preencher combo box projeto
    public void configuraComboBoxProjeto() {
        ArrayList<String> Projetos = new ArrayList<>();
        AtividadeBO atividadeBO = new AtividadeBO();

        try {
            Projetos = atividadeBO.ComboBoxProjeto(userLogado.getDepartamento().getCodigo());
        } catch (SQLException ex) {
            logger.error("Erro ao popular o projetos " + ex.getMessage());
        }

        ComboBoxProjeto.removeAllItems();
        ComboBoxProjeto.addItem("Selecione");
        for (String item : Projetos) {
            ComboBoxProjeto.addItem(item);
        }

    }

    //Preencher combo Box encarregado por departamento
    public void configuraComboBoxEncarregado() {
        ArrayList<String> Encarregado = new ArrayList<>();
        AtividadeBO atividadeBO = new AtividadeBO();

        try {
            Encarregado = atividadeBO.ComboBoxEncarregadoPorDepartamento(this.userLogado.getDepartamento().getCodigo());
        } catch (SQLException ex) {
            logger.error("Erro ao preencher combo box encarregado " + ex.getMessage());
        }

        ComboBoxEncarregado.removeAllItems();
        ComboBoxEncarregado.addItem("Selecione");
        for (String item : Encarregado) {
            ComboBoxEncarregado.addItem(item);
        }

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox ComboBoxEncarregado;
    private javax.swing.JComboBox ComboBoxProjeto;
    private javax.swing.JSpinner SpinnerDuracao;
    private javax.swing.JButton btnCadastrar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnLimpar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JTextField txtNome;
    // End of variables declaration//GEN-END:variables
}
