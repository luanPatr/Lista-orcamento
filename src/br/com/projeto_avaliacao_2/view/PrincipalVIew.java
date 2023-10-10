/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/MDIApplication.java to edit this template
 */
package br.com.projeto_avaliacao_2.view;

import javax.swing.JOptionPane;
import br.com.projeto_avaliacao_2.dto.FuncionarioDTO;
import java.awt.Image;
import java.awt.Graphics;
import javax.swing.ImageIcon;

/**
 *
 * @author Luan Souza
 */
public class PrincipalVIew extends javax.swing.JFrame {
    

    /**
     * Creates new form PrincipalVIew
     */
    public PrincipalVIew(FuncionarioDTO funcionarioDTO) {
        initComponents();
        this.setLocationRelativeTo(null);
        if(funcionarioDTO.getTipo_fun().equalsIgnoreCase("COMUM")){
            itemMenuFuncionario.setVisible(false);
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

        ImageIcon imageicon = new ImageIcon(getClass().getResource("imagens/tela_inicial.jpg"));
        Image image = imageicon.getImage();
        desktopPane = new javax.swing.JDesktopPane(){
            public void paintComponent(Graphics graphics){
                graphics.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
        }
        ;
        menuBar = new javax.swing.JMenuBar();
        menuCadastro = new javax.swing.JMenu();
        itemMenuCliente = new javax.swing.JMenuItem();
        itemMenuPrestador = new javax.swing.JMenuItem();
        itemMenuServico = new javax.swing.JMenuItem();
        itemMenuFuncionario = new javax.swing.JMenuItem();
        menuOrcamento = new javax.swing.JMenu();
        itemMenuOrcamento = new javax.swing.JMenuItem();
        menuSair = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        menuCadastro.setMnemonic('f');
        menuCadastro.setText("Cadastro");

        itemMenuCliente.setMnemonic('o');
        itemMenuCliente.setText("Cliente");
        itemMenuCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemMenuClienteActionPerformed(evt);
            }
        });
        menuCadastro.add(itemMenuCliente);

        itemMenuPrestador.setMnemonic('s');
        itemMenuPrestador.setText("Prestador");
        itemMenuPrestador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemMenuPrestadorActionPerformed(evt);
            }
        });
        menuCadastro.add(itemMenuPrestador);

        itemMenuServico.setMnemonic('a');
        itemMenuServico.setText("Servicos");
        itemMenuServico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemMenuServicoActionPerformed(evt);
            }
        });
        menuCadastro.add(itemMenuServico);

        itemMenuFuncionario.setText("Funcionario");
        itemMenuFuncionario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemMenuFuncionarioActionPerformed(evt);
            }
        });
        menuCadastro.add(itemMenuFuncionario);

        menuBar.add(menuCadastro);

        menuOrcamento.setMnemonic('e');
        menuOrcamento.setText("Orcamento");

        itemMenuOrcamento.setMnemonic('t');
        itemMenuOrcamento.setText("Criar novo Orçamento");
        itemMenuOrcamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemMenuOrcamentoActionPerformed(evt);
            }
        });
        menuOrcamento.add(itemMenuOrcamento);

        menuBar.add(menuOrcamento);

        menuSair.setMnemonic('h');
        menuSair.setText("Sair");
        menuSair.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuSairMouseClicked(evt);
            }
        });
        menuBar.add(menuSair);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktopPane, javax.swing.GroupLayout.DEFAULT_SIZE, 1502, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktopPane, javax.swing.GroupLayout.DEFAULT_SIZE, 636, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void itemMenuPrestadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemMenuPrestadorActionPerformed
        abrePrestadorVIEW();
    }//GEN-LAST:event_itemMenuPrestadorActionPerformed

    private void menuSairMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuSairMouseClicked
        sair();
    }//GEN-LAST:event_menuSairMouseClicked

    private void itemMenuServicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemMenuServicoActionPerformed
        abreServicoVIEW();
    }//GEN-LAST:event_itemMenuServicoActionPerformed

    private void itemMenuClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemMenuClienteActionPerformed
        abreClienteVIEW();
    }//GEN-LAST:event_itemMenuClienteActionPerformed

    private void itemMenuOrcamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemMenuOrcamentoActionPerformed
        abreOrcamentoVIEW();
    }//GEN-LAST:event_itemMenuOrcamentoActionPerformed

    private void itemMenuFuncionarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemMenuFuncionarioActionPerformed
        abreFuncionarioVIEW();
    }//GEN-LAST:event_itemMenuFuncionarioActionPerformed
    
    //Metodo para fechar o sistema 
    private void sair(){
        Object[] options = { "Sair", "Cancelar" };
        if(JOptionPane.showOptionDialog(null, "Deseja Sair do Sistema", "Informação", 
                JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]) == 0){
            System.exit(0);
        } 
    } 
    
     private void abrePrestadorVIEW(){
        PrestadorVIEW prestadorVIEW = new PrestadorVIEW();
        this.desktopPane.add(prestadorVIEW);
        prestadorVIEW.setVisible(true); 
        prestadorVIEW.setPosicao();
    }
     
    private void abreServicoVIEW(){
        ServicoVIEW servicoVIEW = new ServicoVIEW();
        this.desktopPane.add(servicoVIEW);
        servicoVIEW.setVisible(true); 
        servicoVIEW.setPosicao();
    }
    
    private void abreClienteVIEW(){
        ClienteVIEW clienteVIEW = new ClienteVIEW();
        this.desktopPane.add(clienteVIEW);
        clienteVIEW.setVisible(true); 
        clienteVIEW.setPosicao();
    }
    
    private void abreOrcamentoVIEW(){
        OrcamentoVIEW orcamentoVIEW = new OrcamentoVIEW();
        this.desktopPane.add(orcamentoVIEW);
        orcamentoVIEW.setVisible(true); 
        orcamentoVIEW.setPosicao();
    }
    
    private void abreFuncionarioVIEW(){
        FuncionarioVIEW funcionarioVIEW = new FuncionarioVIEW();
        this.desktopPane.add(funcionarioVIEW);
        funcionarioVIEW.setVisible(true); 
        funcionarioVIEW.setPosicao();
    }
    
    /**
     * @param args the command line arguments
     */
    //public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
       /* try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PrincipalVIew.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PrincipalVIew.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PrincipalVIew.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PrincipalVIew.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }*/
        //</editor-fold>

        /* Create and display the form */
       /*java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PrincipalVIew().setVisible(true);
            }
        });
    }*/

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane desktopPane;
    private javax.swing.JMenuItem itemMenuCliente;
    private javax.swing.JMenuItem itemMenuFuncionario;
    private javax.swing.JMenuItem itemMenuOrcamento;
    private javax.swing.JMenuItem itemMenuPrestador;
    private javax.swing.JMenuItem itemMenuServico;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenu menuCadastro;
    private javax.swing.JMenu menuOrcamento;
    private javax.swing.JMenu menuSair;
    // End of variables declaration//GEN-END:variables

}
