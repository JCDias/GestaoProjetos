/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.jean.gestaoprojetos.apresentacao;

import br.edu.ifnmg.jean.gestaoprojetos.entidades.Usuario;
import br.edu.ifnmg.jean.gestaoprojetos.negocio.AtividadeBO;
import br.edu.ifnmg.jean.gestaoprojetos.utilitarios.configuraLog;
import java.io.IOException;
import java.sql.SQLException;
import net.proteanit.sql.DbUtils;
import org.apache.log4j.Logger;

/**
 *
 * @author PC1
 */
public class ConsultarAtividadesAtrasadas extends javax.swing.JFrame {
    Usuario userLogado = null;
     static Logger logger =  null;
    /**
     * Creates new form ConsultarAtividadesAtrasadas
     */
    public ConsultarAtividadesAtrasadas(Usuario user) {
        initComponents();
        this.userLogado = user;
        this.setLocationRelativeTo(null);
        //Configuração do LOG4J
        try {
            Logger log = Logger.getLogger(this.getClass());
            logger = new configuraLog().configura(log);
        } catch (IOException ex) {

        }
        this.AtividadesAtrasadas();
    }

    public void AtividadesAtrasadas() {
        AtividadeBO atividade = new AtividadeBO();

        try {
            tabelaAtividadesAtrasadas.setModel(DbUtils.resultSetToTableModel(atividade.preencherTabelaAtividadeAtrasadas(userLogado.getDepartamento().getCodigo())));
        } catch (SQLException ex) {

        }

    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaAtividadesAtrasadas = new javax.swing.JTable();

        setTitle("Atividades em atraso");
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Atividades Atrasadas", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 18))); // NOI18N

        tabelaAtividadesAtrasadas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Atividade", "Projeto", "Encarregado", "Duração", "Horas trabalhadas", "Conclusao"
            }
        ));
        jScrollPane1.setViewportView(tabelaAtividadesAtrasadas);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 739, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 418, Short.MAX_VALUE)
                .addGap(20, 20, 20))
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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabelaAtividadesAtrasadas;
    // End of variables declaration//GEN-END:variables
}
