/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import controle.NavegacaoMediator;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import model.Tarefa;

/**
 *
 * @author Willighan
 */
public class CardTarefaIndividual extends javax.swing.JPanel {

    /**
     * Creates new form CardTarefaIndividual
     */
    private NavegacaoMediator mediator;
    private Tarefa tarefa;
    
    public CardTarefaIndividual(Tarefa tarefa, NavegacaoMediator mediator) {
        
        initComponents();
        
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    mediator.abrirVisualizacaoTarefa(tarefa);
                }
            }
        });
        
    }

    public void setDados(Tarefa tarefa){
        
        this.lblNomeTarefa.setText(tarefa.getTitulo());
        this.lblNomeResponsável.setText(tarefa.getUsuarioResponsavel().getNome());
        this.lblDescricaoTarefa.setText(tarefa.getDescricao());
        this.lblStatusTarefa.setText(tarefa.getStatus().getDescricao());
        
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblNomeTarefa = new javax.swing.JLabel();
        lblDescricaoTarefa = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        lblStatusTarefa = new javax.swing.JLabel();
        lblNomeResponsável = new javax.swing.JLabel();

        lblNomeTarefa.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblNomeTarefa.setText("jLabel1");

        lblDescricaoTarefa.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblDescricaoTarefa.setText("jLabel1");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("Status:");

        lblStatusTarefa.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblStatusTarefa.setText("jLabel2");

        lblNomeResponsável.setText("jLabel2");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblNomeTarefa)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblNomeResponsável))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblStatusTarefa))
                            .addComponent(lblDescricaoTarefa))))
                .addContainerGap(279, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNomeTarefa)
                    .addComponent(lblNomeResponsável))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblDescricaoTarefa)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(lblStatusTarefa))
                .addContainerGap(16, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lblDescricaoTarefa;
    private javax.swing.JLabel lblNomeResponsável;
    private javax.swing.JLabel lblNomeTarefa;
    private javax.swing.JLabel lblStatusTarefa;
    // End of variables declaration//GEN-END:variables
}
