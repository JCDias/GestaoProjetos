/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.jean.gestaoprojetos.apresentacao;

import br.edu.ifnmg.jean.gestaoprojetos.entidades.Departamento;
import br.edu.ifnmg.jean.gestaoprojetos.entidades.Usuario;
import br.edu.ifnmg.jean.gestaoprojetos.utilitarios.Criptografia;
import br.edu.ifnmg.jean.gestaoprojetos.negocio.DepartamentoBO;
import br.edu.ifnmg.jean.gestaoprojetos.negocio.UsuarioBO;
import br.edu.ifnmg.jean.gestaoprojetos.utilitarios.configuraLog;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;
import org.apache.log4j.Appender;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

/**
 *
 * @author JEAN CARLOS
 */
public class ConsultarGerenteForm extends javax.swing.JFrame {
    static Logger logger = null;
    /**
     * Creates new form ConsultarGerenteForm
     */
    public ConsultarGerenteForm() {
        initComponents();
        this.preencherTabela();
        this.setLocationRelativeTo(null);
        //Configuração do LOG4J
        try {
            Logger log = Logger.getLogger(this.getClass());
            logger = new configuraLog().configura(log);
        } catch (IOException ex) {

        }

    }

    //Preencher tabela gerente
    public void preencherTabela() {
        this.jPanelEditar.setVisible(false);

        UsuarioBO gerente = new UsuarioBO();
        try {
            tabelaGerente.setModel(DbUtils.resultSetToTableModel(gerente.ConfigurarTabelaGerente()));
        } catch (SQLException ex) {
            logger.error("Erro ao preencher tabela "+ex.getMessage());
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnDemitir = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaGerente = new javax.swing.JTable();
        jPanelEditar = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtNome = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtCargo = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtSenha = new javax.swing.JPasswordField();
        jLabel6 = new javax.swing.JLabel();
        ComboBoxDepartamento = new javax.swing.JComboBox();
        btnSalvar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

        setTitle("Consultar Gerente");
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Consultar Gerente", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 18))); // NOI18N

        btnDemitir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/edu/ifnmg/jean/gestaoprojetos/icones/PNG/button_cancel.png"))); // NOI18N
        btnDemitir.setText("Demitir");
        btnDemitir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDemitirActionPerformed(evt);
            }
        });

        btnEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/edu/ifnmg/jean/gestaoprojetos/icones/PNG/kate.png"))); // NOI18N
        btnEditar.setText("   Editar");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        tabelaGerente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tabelaGerente);

        jPanelEditar.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Editar", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 0, 14))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel1.setText("Nome:");

        jLabel2.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel2.setText("E-mail: ");

        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel3.setText("Cargo:");

        txtCargo.setEnabled(false);

        jLabel4.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel4.setText("Código:");

        txtCodigo.setEnabled(false);

        jLabel5.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel5.setText("Senha:");

        jLabel6.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel6.setText("Departamento: ");

        ComboBoxDepartamento.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/edu/ifnmg/jean/gestaoprojetos/icones/PNG/kfloppy.png"))); // NOI18N
        btnSalvar.setText("Salvar");
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });

        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/edu/ifnmg/jean/gestaoprojetos/icones/PNG/editdelete.png"))); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelEditarLayout = new javax.swing.GroupLayout(jPanelEditar);
        jPanelEditar.setLayout(jPanelEditarLayout);
        jPanelEditarLayout.setHorizontalGroup(
            jPanelEditarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelEditarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelEditarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanelEditarLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelEditarLayout.createSequentialGroup()
                        .addGroup(jPanelEditarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanelEditarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCargo)
                            .addComponent(txtEmail))))
                .addGap(91, 91, 91)
                .addGroup(jPanelEditarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelEditarLayout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanelEditarLayout.createSequentialGroup()
                        .addGroup(jPanelEditarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanelEditarLayout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ComboBoxDepartamento, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelEditarLayout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(18, 18, 18)
                                .addComponent(txtSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelEditarLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnSalvar)
                .addGap(65, 65, 65)
                .addComponent(btnCancelar)
                .addGap(234, 234, 234))
        );
        jPanelEditarLayout.setVerticalGroup(
            jPanelEditarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelEditarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelEditarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelEditarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(txtSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelEditarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtCargo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(ComboBoxDepartamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addGroup(jPanelEditarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnSalvar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addComponent(jPanelEditar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 590, Short.MAX_VALUE)
                        .addComponent(btnEditar)
                        .addGap(18, 18, 18)
                        .addComponent(btnDemitir)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEditar)
                    .addComponent(btnDemitir))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
                .addGap(26, 26, 26)
                .addComponent(jPanelEditar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
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

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        int seleciona = tabelaGerente.getSelectedRow();
        if (seleciona >= 0) {
            this.jPanelEditar.setVisible(true);
            String codigo = tabelaGerente.getModel().getValueAt(seleciona, 0).toString();
            String nome = tabelaGerente.getModel().getValueAt(seleciona, 1).toString();
            String cargo = tabelaGerente.getModel().getValueAt(seleciona, 2).toString();
            String departamento = tabelaGerente.getModel().getValueAt(seleciona, 3).toString();
            String email = tabelaGerente.getModel().getValueAt(seleciona, 4).toString();
            //setar campos
            txtNome.setText(nome);
            txtCodigo.setText(codigo);
            txtEmail.setText(email);
            txtCargo.setText(cargo);
            this.PreencherComboBoxDepartamento(departamento);

        } else {
            JOptionPane.showMessageDialog(null, "Selecione uma linha antes de editar!", "Consultar Departamento", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        this.preencherTabela();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnDemitirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDemitirActionPerformed
        Usuario usuario = new Usuario();
        int seleciona = tabelaGerente.getSelectedRow();
        if (seleciona >= 0) {
            String codigo = tabelaGerente.getModel().getValueAt(seleciona, 0).toString();
            String cargo = tabelaGerente.getModel().getValueAt(seleciona, 2).toString();
            //Setando usuario
            usuario.setCargo(cargo);
            usuario.setId_usuario(Integer.parseInt(codigo));
            int resp = JOptionPane.showConfirmDialog(this, "Ao demitir um gerente os dados não podem mais serem recuperados.\nDeseja realmente demitir este gerente?", "Demitir", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (resp == 0) {
                UsuarioBO usuarioBO = new UsuarioBO();
                try {
                    usuarioBO.ExcluirUsuario(usuario);
                    JOptionPane.showMessageDialog(null, "Gerente demitido com sucesso!", "Consultar Gerente", JOptionPane.INFORMATION_MESSAGE);
                    logger.info("Gerente excluido com sucesso!");
                    this.preencherTabela();
                } catch (SQLException ex) {
                    logger.error("Erro ao excluir Gerente " + ex.getMessage());
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Selecione um gerente para demitir!", "Consultar Gerente", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnDemitirActionPerformed

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        Usuario usuario = new Usuario();
        UsuarioBO usuarioBO = new UsuarioBO();
        Departamento departamento = new Departamento();
        //Receber valor dos campos
        String nome = txtNome.getText().trim();
        int codigo = Integer.parseInt(txtCodigo.getText());
        String email = txtEmail.getText().trim();
        String senha = txtSenha.getText().trim();
        String cargo = txtCargo.getText().trim();

        //Selecionar o departamento do usuario pelo nome
        DepartamentoBO departamentoBO = new DepartamentoBO();
        try {
            String departamentoSelecionado = ComboBoxDepartamento.getSelectedItem().toString();
            departamento = departamentoBO.selecionarDepartamentoPorNome(departamentoSelecionado);
        } catch (SQLException ex) {
            logger.error("Erro ao preencher combo Box "+ex.getMessage());
        }
        usuario.setNome(nome);
        usuario.setEmail(email);
        usuario.setCargo(cargo);
        usuario.setDepartamento(departamento);
        usuario.setId_usuario(codigo);
        if (!senha.equals("")) {
            Criptografia criptografia = new Criptografia();
            String senhaCriprografada = criptografia.criptografar(senha);
            usuario.setSenha(senhaCriprografada);
        }

        String valida = usuarioBO.validaDadosSalvar(nome, email);
        if (valida == null) {
            try {
                String resultado = usuarioBO.atualizarUsuario(usuario);
                if (resultado == null) {
                    JOptionPane.showMessageDialog(null, "Gerente atualizado com sucesso!", "Consultar Gerente", JOptionPane.INFORMATION_MESSAGE);
                    logger.info("Gerente "+nome+" atualizado com sucesso!");
                    this.preencherTabela();
                } else {
                    JOptionPane.showMessageDialog(null, resultado, "Consultar Gerente", JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao atualizar Gerente!", "Consultar Gerente", JOptionPane.INFORMATION_MESSAGE);
                logger.error("Erro ao atualizar gerente! " +ex.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(null, valida, "Consultar Gerente", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnSalvarActionPerformed

    //Preencher comboBox
    public void PreencherComboBoxDepartamento(String departamento) {
        ArrayList<String> Departamentos = new ArrayList<>();
        DepartamentoBO departamentoBO = new DepartamentoBO();

        try {
            Departamentos = departamentoBO.ComboBoxDepartamentos();
        } catch (SQLException ex) {
            logger.error("Erro ao preencher PreencherComboBoxDepartamento "+ex.getMessage());
        }

        ComboBoxDepartamento.removeAllItems();
        ComboBoxDepartamento.addItem(departamento);
        for (String item : Departamentos) {
            ComboBoxDepartamento.addItem(item);
        }

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox ComboBoxDepartamento;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnDemitir;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanelEditar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabelaGerente;
    private javax.swing.JTextField txtCargo;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtNome;
    private javax.swing.JPasswordField txtSenha;
    // End of variables declaration//GEN-END:variables
}
