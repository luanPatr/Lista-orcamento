/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package br.com.projeto_avaliacao_2.view;

import java.awt.Dimension;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import br.com.projeto_avaliacao_2.dto.OrcamentoDTO;
import br.com.projeto_avaliacao_2.ctr.OrcamentoCTR;
import br.com.projeto_avaliacao_2.dto.ServicoDTO;
import br.com.projeto_avaliacao_2.ctr.ServicoCTR;
import br.com.projeto_avaliacao_2.dto.ClienteDTO;
import br.com.projeto_avaliacao_2.ctr.ClienteCTR;
import java.util.Date;

/**
 *
 * @author Luan Souza
 */
public class OrcamentoVIEW extends javax.swing.JInternalFrame {
    
    OrcamentoCTR orcamentoCTR = new OrcamentoCTR();
    OrcamentoDTO orcamentoDTO = new OrcamentoDTO();
    ServicoCTR servicoCTR = new ServicoCTR();
    ServicoDTO servicoDTO = new ServicoDTO();
    ClienteCTR clienteCTR = new ClienteCTR();
    ClienteDTO clienteDTO = new ClienteDTO();
    
    ResultSet rs;
    int gravar_alterar;
    DefaultTableModel modelo_jtl_consultar_cli;
    DefaultTableModel modelo_jtl_consultar_serv;
    DefaultTableModel modelo_jtl_consultar_serv_selecionado;
    
    public void setPosicao() {
        Dimension d = this.getDesktopPane().getSize();
        this.setLocation((d.width - this.getSize().width) / 2, (d.height - this.getSize().height) / 2); 
    }//Fecha método setPosicao()
    
    private void gravar(){
        orcamentoDTO.setDat_orcamento(new Date());
        orcamentoDTO.setVal_orcamento(Double.parseDouble(totalorcamento.getText()));
        clienteDTO.setId_cli(Integer.parseInt(String.valueOf(
            jtl_consultar_cli.getValueAt(jtl_consultar_cli.getSelectedRow(), 0))));

        JOptionPane.showMessageDialog(null,
                orcamentoCTR.inserirOrcamento(orcamentoDTO, clienteDTO, jtl_consultar_serv_selecionado)
        );
    }//Fecha método gravar()
    
    private void preencheTabelaCliente(String nome_cliente){
        try{
            //Limpa todas as linhas
            modelo_jtl_consultar_cli.setNumRows(0);

            clienteDTO.setNome_cli(nome_cliente);
            rs = clienteCTR.consultarCliente(clienteDTO, 1);
            //Enquanto tiver linhas - faça
            while(rs.next()){
                modelo_jtl_consultar_cli.addRow(new Object[]{
                    rs.getString("id_cli"),
                    rs.getString("nome_cli")
                });
            }        
        }
        catch(Exception erTab){
            System.out.println("Erro SQL: "+erTab);
        }  
    }//Fecha método preencheTabelaCliente()
    
    private void preencheTabelaServico(String nome_serv){
        try{
            //Limpa todas as linhas
            modelo_jtl_consultar_serv.setNumRows(0);
            servicoDTO.setNome_serv(nome_serv);
            rs = servicoCTR.consultarServico(servicoDTO, 1);
            //Enquanto tiver linhas - faça
            while(rs.next()){
                modelo_jtl_consultar_serv.addRow(new Object[]{
                  rs.getString("id_serv"),
                  rs.getString("nome_serv"),
                  rs.getDouble("p_custo_serv")
                });
            }        
        }
        catch(Exception erTab){
            System.out.println("Erro SQL: "+erTab);
        }  
    }//Fecha método preencheTabelaServico(String nome_serv)
    
    
    private void adicionaServicoSelecionado(int id_serv, String nome_serv, double p_venda_serv){
        try{
            modelo_jtl_consultar_serv_selecionado.addRow(new Object[]{
                id_serv,
                nome_serv,
                p_venda_serv
            });
        }
        catch(Exception erTab){
            System.out.println("Erro SQL 22: "+erTab);
        }  
    }//Fecha método adicionaServicoSelecionado()
    
     private void removeServicoSelecionado(int linha_selecionada){
        try{
            if(linha_selecionada >= 0){
                modelo_jtl_consultar_serv_selecionado.removeRow(linha_selecionada);
                calculaTotalOrcamento();
            } 
        }
        catch(Exception erTab){
            System.out.println("Erro SQL: "+erTab);
        }  
    }//Fecha método removeServicoSelecionado()
    
     private void calculaTotalOrcamento(){
        try{
                double total = 0;
                for(int cont=0; cont<jtl_consultar_serv_selecionado.getRowCount(); cont++){
                    total += (Double.parseDouble(String.valueOf(
                              jtl_consultar_serv_selecionado.getValueAt(cont, 2))) * 
                              Integer.parseInt(String.valueOf(
                              jtl_consultar_serv_selecionado.getValueAt(cont, 3))));
                }
                totalorcamento.setText(String.valueOf(total));
        }
        catch(Exception erTab){
            System.out.println("Erro SQL: "+erTab);
        }  
    }//Fecha método calculaTotalOrcamento()
     
    private void liberaCampos(boolean a){
        pesquisa_nome_cli.setEnabled(a);
        btnPesquisarCli.setEnabled(a);
        jtl_consultar_cli.setEnabled(a);
        pesquisa_nome_serv.setEnabled(a);
        btnPesquisaServ.setEnabled(a);
        jtl_consultar_serv.setEnabled(a);
        btnServAdd.setEnabled(a);
        btnServRem.setEnabled(a);
        jtl_consultar_serv_selecionado.setEnabled(a);
        totalorcamento.setText("0.00");
    }//Fecha método liberaCampos(boolean a)
    
    private void limpaCampos(){
        pesquisa_nome_cli.setText("");
        modelo_jtl_consultar_cli.setNumRows(0);
        pesquisa_nome_serv.setText("");
        modelo_jtl_consultar_serv.setNumRows(0);
        modelo_jtl_consultar_serv_selecionado.setNumRows(0);
    }//Fecha método limpaCampos()
    
    private void liberaBotoes(boolean a, boolean b, boolean c, boolean d){
        btnNovo.setEnabled(a);
        btnSalvar.setEnabled(b);
        btnCancelar.setEnabled(c);
        btnSair.setEnabled(d);
    }//Fecha método liberaBotoes(boolean a, boolean b, boolean c, boolean d)
    
    
    private boolean verificaPreenchimento() { 
        if(jtl_consultar_cli.getSelectedRowCount() <= 0){
            JOptionPane.showMessageDialog(null, "Deve ser selecionado um Cliente");
            jtl_consultar_cli.requestFocus();
            return false;
        }
        else{
            if(jtl_consultar_serv_selecionado.getRowCount() <= 0){
                JOptionPane.showMessageDialog(null, "É necessário adicionar pelo menos um servico no pedido");
                jtl_consultar_serv_selecionado.requestFocus();
                return false;
            }
            else{
                int verifica=0;
                for(int cont=0; cont<jtl_consultar_serv_selecionado.getRowCount(); cont++){
                    if(String.valueOf(jtl_consultar_serv_selecionado.getValueAt(
                        cont, 3)).equalsIgnoreCase("null")){
                        verifica++;
                    }
                }
                if(verifica > 0){                                            
                        JOptionPane.showMessageDialog(null, 
                        "A quantidade de cada servico vendido deve ser informado");
                        jtl_consultar_serv_selecionado.requestFocus();
                        return false;
                }
                else{
                    return true;
                }//Fecha else da quantidade de cada produto vendido
            }//Fecha else jtl_consultar_pro_selecionado
        }//Fecha else jtl_consultar_cli
    }//Fecha método verificaPreenchimento()
    

    /**
     * Creates new form OrcamentoVIEW
     */
    public OrcamentoVIEW() {
        initComponents();
        
        initComponents();
        //Chama o metodo liberaCampos
        liberaCampos(false);
        //Chama o método lieraBotoes
        liberaBotoes(true, false, false, true);
        //Atribui um modelo para manipular a tabela 
        modelo_jtl_consultar_cli = (DefaultTableModel) jtl_consultar_cli.getModel();
        modelo_jtl_consultar_serv = (DefaultTableModel) jtl_consultar_serv.getModel();
        modelo_jtl_consultar_serv_selecionado = (DefaultTableModel) jtl_consultar_serv_selecionado.getModel();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFrame1 = new javax.swing.JFrame();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 32767));
        jOptionPane1 = new javax.swing.JOptionPane();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        pesquisa_nome_cli = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtl_consultar_cli = new javax.swing.JTable();
        btnPesquisarCli = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        pesquisa_nome_serv = new javax.swing.JTextField();
        btnPesquisaServ = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtl_consultar_serv = new javax.swing.JTable();
        btnServAdd = new javax.swing.JButton();
        btnServRem = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jtl_consultar_serv_selecionado = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        totalorcamento = new javax.swing.JLabel();
        btnNovo = new javax.swing.JButton();
        btnSalvar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnSair = new javax.swing.JButton();

        javax.swing.GroupLayout jFrame1Layout = new javax.swing.GroupLayout(jFrame1.getContentPane());
        jFrame1.getContentPane().setLayout(jFrame1Layout);
        jFrame1Layout.setHorizontalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jFrame1Layout.setVerticalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        jLabel1.setText("Orcamento");

        jLabel2.setText("Cliente:");

        jtl_consultar_cli.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "ID", "Nome"
            }
        ));
        jScrollPane1.setViewportView(jtl_consultar_cli);

        btnPesquisarCli.setText("OK");
        btnPesquisarCli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisarCliActionPerformed(evt);
            }
        });

        jLabel3.setText("Dados:");

        jLabel4.setText("Servicos");

        jLabel5.setText("Descrição:");

        btnPesquisaServ.setText("OK");
        btnPesquisaServ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisaServActionPerformed(evt);
            }
        });

        jtl_consultar_serv.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID", "Nome", "Valor"
            }
        ));
        jScrollPane2.setViewportView(jtl_consultar_serv);

        btnServAdd.setText("Add");
        btnServAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnServAddActionPerformed(evt);
            }
        });

        btnServRem.setText("Rem");
        btnServRem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnServRemActionPerformed(evt);
            }
        });

        jtl_consultar_serv_selecionado.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nome", "Valor", "QTD"
            }
        ));
        jtl_consultar_serv_selecionado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtl_consultar_serv_selecionadoKeyReleased(evt);
            }
        });
        jScrollPane3.setViewportView(jtl_consultar_serv_selecionado);

        jLabel6.setBackground(new java.awt.Color(255, 0, 51));
        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 51, 51));
        jLabel6.setText("Total Orçamento:");

        totalorcamento.setBackground(new java.awt.Color(255, 51, 51));
        totalorcamento.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        totalorcamento.setForeground(new java.awt.Color(0, 255, 0));
        totalorcamento.setText("0.00");

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

        btnSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/projeto_avaliacao_2/view/imagens/sair.png"))); // NOI18N
        btnSair.setText("Sair");
        btnSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSairActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(218, 218, 218)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addGap(265, 265, 265))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(471, 471, 471)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 417, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(pesquisa_nome_cli, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnPesquisarCli))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(58, 58, 58)
                                .addComponent(jLabel6)
                                .addGap(66, 66, 66)
                                .addComponent(totalorcamento, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(274, 274, 274)
                                .addComponent(btnServAdd)
                                .addGap(18, 18, 18)
                                .addComponent(btnServRem))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(125, 125, 125)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabel5)
                                            .addGap(18, 18, 18)
                                            .addComponent(pesquisa_nome_serv, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(btnPesquisaServ))
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(261, 261, 261)
                        .addComponent(btnNovo)
                        .addGap(68, 68, 68)
                        .addComponent(btnSalvar)
                        .addGap(77, 77, 77)
                        .addComponent(btnCancelar)
                        .addGap(67, 67, 67)
                        .addComponent(btnSair)))
                .addContainerGap(51, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(pesquisa_nome_cli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPesquisarCli)
                    .addComponent(jLabel5)
                    .addComponent(pesquisa_nome_serv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPesquisaServ))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(52, 52, 52)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(totalorcamento, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnServAdd)
                            .addComponent(btnServRem))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNovo)
                    .addComponent(btnSalvar)
                    .addComponent(btnCancelar)
                    .addComponent(btnSair))
                .addGap(47, 47, 47))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoActionPerformed
        liberaCampos(true);
        liberaBotoes(false, true, true, true);
    }//GEN-LAST:event_btnNovoActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        limpaCampos();
        liberaCampos(false);
        modelo_jtl_consultar_cli.setNumRows(0);
        modelo_jtl_consultar_serv.setNumRows(0);
        liberaBotoes(true, false, false, true);
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSairActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnSairActionPerformed

    private void btnPesquisarCliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisarCliActionPerformed
        preencheTabelaCliente(pesquisa_nome_cli.getText());
    }//GEN-LAST:event_btnPesquisarCliActionPerformed

    private void btnPesquisaServActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisaServActionPerformed
         preencheTabelaServico(pesquisa_nome_serv.getText());
    }//GEN-LAST:event_btnPesquisaServActionPerformed

    private void btnServAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnServAddActionPerformed
        adicionaServicoSelecionado(
            Integer.parseInt(String.valueOf(jtl_consultar_serv.getValueAt(
                    jtl_consultar_serv.getSelectedRow(), 0))),
            String.valueOf(jtl_consultar_serv.getValueAt(jtl_consultar_serv.getSelectedRow(), 1)),
            Double.parseDouble(String.valueOf(jtl_consultar_serv.getValueAt(
                    jtl_consultar_serv.getSelectedRow(), 2)))
        );
    }//GEN-LAST:event_btnServAddActionPerformed

    private void btnServRemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnServRemActionPerformed
        removeServicoSelecionado(jtl_consultar_serv_selecionado.getSelectedRow());
    }//GEN-LAST:event_btnServRemActionPerformed

    private void jtl_consultar_serv_selecionadoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtl_consultar_serv_selecionadoKeyReleased
        //Testa se a tecla enter foi pressionada
        if(evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
	    calculaTotalOrcamento();
	}  
    }//GEN-LAST:event_jtl_consultar_serv_selecionadoKeyReleased

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        if(verificaPreenchimento()){    
            gravar();
            limpaCampos();
            liberaCampos(false);
            liberaBotoes(true, false, false, true);
        }
    }//GEN-LAST:event_btnSalvarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnNovo;
    private javax.swing.JButton btnPesquisaServ;
    private javax.swing.JButton btnPesquisarCli;
    private javax.swing.JButton btnSair;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JButton btnServAdd;
    private javax.swing.JButton btnServRem;
    private javax.swing.Box.Filler filler1;
    private javax.swing.JFrame jFrame1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JOptionPane jOptionPane1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jtl_consultar_cli;
    private javax.swing.JTable jtl_consultar_serv;
    private javax.swing.JTable jtl_consultar_serv_selecionado;
    private javax.swing.JTextField pesquisa_nome_cli;
    private javax.swing.JTextField pesquisa_nome_serv;
    private javax.swing.JLabel totalorcamento;
    // End of variables declaration//GEN-END:variables

}
