/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package br.com.projeto_avaliacao_2.view;

import java.awt.Dimension;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;
import br.com.projeto_avaliacao_2.dto.PrestadorDTO;
import br.com.projeto_avaliacao_2.ctr.PrestadorCTR;
import br.com.projeto_avaliacao_2.dto.ServicoDTO;
import br.com.projeto_avaliacao_2.ctr.ServicoCTR;

/**
 *
 * @author Luan Souza
 */
public class ServicoVIEW extends javax.swing.JInternalFrame {
    
    PrestadorDTO prestadorDTO = new PrestadorDTO();
    PrestadorCTR prestadorCTR = new PrestadorCTR();
    ServicoDTO servicoDTO = new ServicoDTO();
    ServicoCTR servicoCTR = new ServicoCTR();
    
    int gravar_alterar;
    
    ResultSet rs;
    DefaultTableModel modelo_jtl_consultar_servico;
    DefaultTableModel modelo_jtl_consultar_prestador;
    
     public void setPosicao() {
        Dimension d = this.getDesktopPane().getSize();
        this.setLocation((d.width - this.getSize().width) / 2, (d.height - this.getSize().height) / 2); 
    }//Fecha método setPosicao()

    /**
     * Creates new form ServicoVIEW
     */
    public ServicoVIEW() {
        initComponents();
        
        //Chama todos os métodos liberaCampos
        liberaCampos(false);
        //Chama o método liberaBotoes
        liberaBotoes(true, false, false, false, true);
        modelo_jtl_consultar_servico = (DefaultTableModel) jtl_consultar_servico.getModel();
        modelo_jtl_consultar_prestador = (DefaultTableModel) jtl_consultar_prestador.getModel();
    }

    
    private void alterar(){
        try{
            servicoDTO.setNome_serv(nome_serv.getText());
            servicoDTO.setDesc_serv(desc_serv.getText());
            servicoDTO.setCod_serv(cod_serv.getText());
            servicoDTO.setP_custo_serv(Double.parseDouble(p_custo_serv.getText()));
            prestadorDTO.setId_prest(Integer.parseInt(String.valueOf(
                    jtl_consultar_prestador.getValueAt(
                    jtl_consultar_prestador.getSelectedRow(), 0))));
            
            JOptionPane.showMessageDialog(null,
                    servicoCTR.alterarServico(servicoDTO, prestadorDTO)
            );
        }
        catch(Exception e){
            System.out.println("Erro ao Alterar" + e.getMessage());
        }
    }//Fecha método alterar()
    
     private void excluir(){
       if(JOptionPane.showConfirmDialog(null, "Deseja Realmente excluir o Servico?","Aviso", 
            JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
            JOptionPane.showMessageDialog(null,
                    servicoCTR.excluirServico(servicoDTO)
            );
       }
    }//Fecha método excluir()
     
     private void liberaCampos(boolean a){
        nome_serv.setEnabled(a);
        desc_serv.setEnabled(a);
        cod_serv.setEnabled(a);
        p_custo_serv.setEnabled(a);
        pesquisa_nome_prestador.setEnabled(a);
        btnPesquisarPrestador.setEnabled(a);
        jtl_consultar_prestador.setEnabled(a);
    }//Fecha método liberaCampos(boolean a)
     
    private void liberaBotoes(boolean a, boolean b, boolean c, boolean d, boolean e){
        btnNovo.setEnabled(a);
        btnSalvar.setEnabled(b);
        btnCancelar.setEnabled(c);
        btnExcluir.setEnabled(d);
        btnSair.setEnabled(e);
    }//Fecha método liberaBotoes(boolean a, boolean b, boolean c, boolean d, boolean e)
    
    private void limpaCampos(){
        nome_serv.setText("");
        desc_serv.setText("");
        cod_serv.setText("");
        p_custo_serv.setText("");
        pesquisa_nome_prestador.setText("");
        modelo_jtl_consultar_prestador.setNumRows(0);
    }//Fecha método limpaCampos()
    
    private void preencheTabelaServico(String nome_serv){
        try{
            //Limpa todas as linhas
            modelo_jtl_consultar_servico.setNumRows(0);
            //Enquanto tiver linhas - faça
            servicoDTO.setNome_serv(nome_serv);
            rs = servicoCTR.consultarServico(servicoDTO, 1); //1 = é a pesquisa por nome na classe DAO
            while(rs.next()){
                modelo_jtl_consultar_servico.addRow(new Object[]{
                  rs.getString("id_serv"),
                  rs.getString("nome_serv"),
                });
            }        
        }
        catch(Exception erTab){
            System.out.println("Erro11 SQL: "+erTab);
        } 
        finally{
            servicoCTR.CloseDB();
        }
    }//Fecha método preencheTabelaServico(String nome_serv)
    
     private void preencheCamposServico(int id_serv){
        try{
            servicoDTO.setId_serv(id_serv);
            rs = servicoCTR.consultarServico(servicoDTO, 2); //2 = é a pesquisa no id na classe DAO
            if(rs.next()){
                limpaCampos();
                
                nome_serv.setText(rs.getString("nome_serv"));
                desc_serv.setText(rs.getString("desc_serv"));
                cod_serv.setText(rs.getString("cod_serv"));
                p_custo_serv.setText(rs.getString("p_custo_serv"));
               
                /////Colocando os dados do fornecedor
                modelo_jtl_consultar_prestador.setNumRows(0);
                modelo_jtl_consultar_prestador.addRow(new Object[]{rs.getInt("id_prest"), rs.getString("nome_prest"),});
                jtl_consultar_prestador.setRowSelectionInterval(0, 0);
                
                gravar_alterar = 2;
                liberaCampos(true);
            }//fecha if(rs.next)
        }//fecha try
        catch(Exception erTab){
            System.out.println("Erro2 SQL: "+erTab);
        }  
        finally{
            servicoCTR.CloseDB();
        }
    }//Fecha método preencheCamposServico(int id_ser)
     
     private void preencheTabelaPrestador(String nome_prest){
        try{
            //Limpa todas as linhas
            modelo_jtl_consultar_prestador.setNumRows(0);
            //Enquanto tiver linhas - faça
            prestadorDTO.setNome_prest(nome_prest);
            rs = prestadorCTR.consultarPrestador(prestadorDTO, 1); //1 = é a pesquisa por nome na classe DAO
            while(rs.next()){
                modelo_jtl_consultar_prestador.addRow(new Object[]{
                  rs.getString("id_prest"),
                  rs.getString("nome_prest"),
                });
            }        
        }
        catch(Exception erTab){
            System.out.println("Erro SQL: "+erTab);
        } 
        finally{
            servicoCTR.CloseDB();
        }
    }//Fecha método preencheTabelaFPrestador(String nome_prest)
     
    private void gravar(){
        try{           
            servicoDTO.setNome_serv(nome_serv.getText());
            servicoDTO.setDesc_serv(desc_serv.getText());
            servicoDTO.setCod_serv(cod_serv.getText());
            servicoDTO.setP_custo_serv(Double.parseDouble(p_custo_serv.getText()));
            prestadorDTO.setId_prest(Integer.parseInt(String.valueOf(
                    jtl_consultar_prestador.getValueAt(
                    jtl_consultar_prestador.getSelectedRow(), 0))));

            JOptionPane.showMessageDialog(null,
                    servicoCTR.inserirServico(servicoDTO, prestadorDTO)
            );
        }
        catch(Exception e){
            System.out.println("Erro ao Gravar" + e.getMessage());
        }
    }//Fecha método gravar()
    
    
    
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        nome_serv = new javax.swing.JTextField();
        desc_serv = new javax.swing.JTextField();
        cod_serv = new javax.swing.JTextField();
        p_custo_serv = new javax.swing.JTextField();
        pesquisa_nome_prestador = new javax.swing.JTextField();
        btnPesquisarPrestador = new javax.swing.JButton();
        btnNovo = new javax.swing.JButton();
        btnSalvar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        btnSair = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtl_consultar_prestador = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        pesquisa_nome_servico = new javax.swing.JTextField();
        btnPesquisarServico = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtl_consultar_servico = new javax.swing.JTable();

        jLabel1.setText("Serviço");

        jLabel2.setText("Nome:");

        jLabel3.setText("Descrição:");

        jLabel4.setText("Codigo:");

        jLabel5.setText("P. custo:");

        jLabel6.setText("Prestador:");

        nome_serv.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        nome_serv.setNextFocusableComponent(desc_serv);

        desc_serv.setNextFocusableComponent(cod_serv);

        cod_serv.setNextFocusableComponent(p_custo_serv);

        p_custo_serv.setNextFocusableComponent(pesquisa_nome_prestador);

        pesquisa_nome_prestador.setNextFocusableComponent(btnPesquisarPrestador);
        pesquisa_nome_prestador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pesquisa_nome_prestadorActionPerformed(evt);
            }
        });

        btnPesquisarPrestador.setText("OK");
        btnPesquisarPrestador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisarPrestadorActionPerformed(evt);
            }
        });

        btnNovo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/projeto_avaliacao_2/view/imagens/novo.png"))); // NOI18N
        btnNovo.setText("Novo");
        btnNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoActionPerformed(evt);
            }
        });

        btnSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/projeto_avaliacao_2/view/imagens/salvar.png"))); // NOI18N
        btnSalvar.setText("Salvar");
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });

        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/projeto_avaliacao_2/view/imagens/cancelar.png"))); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/projeto_avaliacao_2/view/imagens/excluir.png"))); // NOI18N
        btnExcluir.setText("Excluir");
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });

        btnSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/projeto_avaliacao_2/view/imagens/sair.png"))); // NOI18N
        btnSair.setText("Sair");
        btnSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSairActionPerformed(evt);
            }
        });

        jtl_consultar_prestador.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null}
            },
            new String [] {
                "ID", "Nome"
            }
        ));
        jScrollPane1.setViewportView(jtl_consultar_prestador);

        jLabel7.setText("Consultar");

        jLabel8.setText("Nome:");

        btnPesquisarServico.setText("OK");
        btnPesquisarServico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisarServicoActionPerformed(evt);
            }
        });

        jtl_consultar_servico.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nome"
            }
        ));
        jtl_consultar_servico.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtl_consultar_servicoMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jtl_consultar_servico);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnNovo)
                        .addGap(101, 101, 101)
                        .addComponent(btnSalvar)
                        .addGap(118, 118, 118)
                        .addComponent(btnCancelar)
                        .addGap(112, 112, 112)
                        .addComponent(btnExcluir))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 608, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel6))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(nome_serv)
                                    .addComponent(desc_serv)
                                    .addComponent(p_custo_serv, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
                                    .addComponent(cod_serv)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(pesquisa_nome_prestador, javax.swing.GroupLayout.PREFERRED_SIZE, 436, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                                        .addComponent(btnPesquisarPrestador)))))
                        .addGap(87, 87, 87)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(18, 18, 18)
                                .addComponent(pesquisa_nome_servico, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnPesquisarServico))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 425, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(131, 131, 131)
                        .addComponent(btnSair)))
                .addContainerGap(19, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(391, 391, 391)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addGap(220, 220, 220))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel7))
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(nome_serv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(pesquisa_nome_servico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPesquisarServico))
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(desc_serv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(cod_serv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(p_custo_serv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(35, 35, 35)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(pesquisa_nome_prestador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnPesquisarPrestador))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNovo)
                    .addComponent(btnSalvar)
                    .addComponent(btnCancelar)
                    .addComponent(btnExcluir)
                    .addComponent(btnSair))
                .addGap(45, 45, 45))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void pesquisa_nome_prestadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pesquisa_nome_prestadorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pesquisa_nome_prestadorActionPerformed

    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoActionPerformed
        liberaCampos(true);
        liberaBotoes(false, true, true, false, true);
        gravar_alterar = 1;
    }//GEN-LAST:event_btnNovoActionPerformed

    private void btnPesquisarServicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisarServicoActionPerformed
        preencheTabelaServico(pesquisa_nome_servico.getText());
    }//GEN-LAST:event_btnPesquisarServicoActionPerformed

    private void btnPesquisarPrestadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisarPrestadorActionPerformed
         preencheTabelaPrestador(pesquisa_nome_prestador.getText());
    }//GEN-LAST:event_btnPesquisarPrestadorActionPerformed

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        if(gravar_alterar == 1){
            gravar();
            gravar_alterar = 0;
        }
        else{
            if(gravar_alterar == 2){
                alterar();
                gravar_alterar = 0;
            }
            else{
                JOptionPane.showMessageDialog(null, "Erro no Sistema!!!");
            }
        }
          
        limpaCampos();
        liberaCampos(false);
        liberaBotoes(true, false, false, false, true);
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void jtl_consultar_servicoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtl_consultar_servicoMouseClicked
        //Pega o id do servico selecionado e chama preencheCampos
        preencheCamposServico(Integer.parseInt(String.valueOf(
                jtl_consultar_servico.getValueAt(
                jtl_consultar_servico.getSelectedRow(), 0))));
        liberaBotoes(false, true, true, true, true);
    }//GEN-LAST:event_jtl_consultar_servicoMouseClicked

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
         excluir();
        limpaCampos();
        liberaCampos(false);
        liberaBotoes(true, false, false, false, true);
        modelo_jtl_consultar_servico.setNumRows(0);
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        limpaCampos();
        liberaCampos(false);
        modelo_jtl_consultar_servico.setNumRows(0);
        liberaBotoes(true, false, false, false, true);
        gravar_alterar=0;
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSairActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnSairActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnNovo;
    private javax.swing.JButton btnPesquisarPrestador;
    private javax.swing.JButton btnPesquisarServico;
    private javax.swing.JButton btnSair;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JTextField cod_serv;
    private javax.swing.JTextField desc_serv;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jtl_consultar_prestador;
    private javax.swing.JTable jtl_consultar_servico;
    private javax.swing.JTextField nome_serv;
    private javax.swing.JTextField p_custo_serv;
    private javax.swing.JTextField pesquisa_nome_prestador;
    private javax.swing.JTextField pesquisa_nome_servico;
    // End of variables declaration//GEN-END:variables

   
}
