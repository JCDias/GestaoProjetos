/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.jean.gestaoprojetos.apresentacao;

import br.edu.ifnmg.jean.gestaoprojetos.entidades.Usuario;
import br.edu.ifnmg.jean.gestaoprojetos.negocio.AtividadeBO;
import br.edu.ifnmg.jean.gestaoprojetos.negocio.ProjetoBO;
import br.edu.ifnmg.jean.gestaoprojetos.utilitarios.configuraLog;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;
import org.apache.log4j.Logger;

/**
 *
 * @author JEAN CARLOS
 */
public class GerarRelatorioAtividadeProjeto extends javax.swing.JFrame {

    static Logger logger = null;
    Usuario userLogado = new Usuario();
    
    public GerarRelatorioAtividadeProjeto(Usuario user) {
        initComponents();
        this.userLogado = user;
        this.setLocationRelativeTo(null);
        //Configuração do LOG4J
        try {
            Logger log = Logger.getLogger(this.getClass());
            logger = new configuraLog().configura(log);
        } catch (IOException ex) {

        }
        this.ConfigurarComboboxProjetos();
    }

    //Metodo que add todos os projetos cadastrados na ComboBox
    public void ConfigurarComboboxProjetos() {
        ArrayList<String> Projetos = new ArrayList<>();
        ProjetoBO projetoBO = new ProjetoBO();

        try {
            Projetos = projetoBO.ComboBoxEscolhaProjeto(userLogado.getDepartamento().getCodigo());
        } catch (SQLException ex) {
            logger.error("Erro ao preencher combo box projetos"+ex.getMessage());
        }

        ComboBoxProjetos.removeAllItems();
        ComboBoxProjetos.addItem("Selecione");
        for (String item : Projetos) {
            ComboBoxProjetos.addItem(item);
        }

    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        ComboBoxProjetos = new javax.swing.JComboBox();
        btnGerar = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setTitle("Gerar Relatório");
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Escolha um projeto", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 18))); // NOI18N

        ComboBoxProjetos.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnGerar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/edu/ifnmg/jean/gestaoprojetos/icones/PNG/clean.png"))); // NOI18N
        btnGerar.setText("Gerar");
        btnGerar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGerarActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/edu/ifnmg/jean/gestaoprojetos/icones/PNG/editdelete.png"))); // NOI18N
        jButton2.setText("Cancelar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(ComboBoxProjetos, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(btnGerar)
                        .addGap(57, 57, 57)
                        .addComponent(jButton2)))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(ComboBoxProjetos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGerar)
                    .addComponent(jButton2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btnGerarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGerarActionPerformed
        if(ComboBoxProjetos.getSelectedItem().toString().equals("Selecione")){
            JOptionPane.showMessageDialog(this,"Selecione um projeto", "Erro", JOptionPane.ERROR_MESSAGE);
        }else{
            AtividadeBO atividadeBO = new AtividadeBO();

            //chamar o relatorio
            try {
                String relatorio = System.getProperty("user.dir")+"/relatorios/RelatorioAtividadeProjeto.jasper";

                //criar fonte de dados
                JRBeanCollectionDataSource fonteDados = new JRBeanCollectionDataSource(atividadeBO.listaAtividade(ComboBoxProjetos.getSelectedItem().toString()));

                //gerar relatorio
                JasperPrint relatorioGerado = JasperFillManager.fillReport(relatorio, null, fonteDados);

                //exibir o relatorio na tela
                JasperViewer jasperViewer = new JasperViewer(relatorioGerado, false);
                jasperViewer.setVisible(true);

            } catch (JRException ex) {
                logger.error("Falha ao gerar Relatorio: " + ex.getMessage());
            } catch (SQLException ex) {
               logger.error("Falha ao gerar Relatorio: " + ex.getMessage());
            }
        }
    }//GEN-LAST:event_btnGerarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox ComboBoxProjetos;
    private javax.swing.JButton btnGerar;
    private javax.swing.JButton jButton2;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
