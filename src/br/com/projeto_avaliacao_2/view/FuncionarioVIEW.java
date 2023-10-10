/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package br.com.projeto_avaliacao_2.view;

import java.awt.Dimension;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;
import br.com.projeto_avaliacao_2.dto.FuncionarioDTO;
import br.com.projeto_avaliacao_2.ctr.FuncionarioCTR;

/**
 *
 * @author Luan Souza
 */
public class FuncionarioVIEW extends javax.swing.JInternalFrame {

    FuncionarioDTO funcionarioDTO = new FuncionarioDTO();
    FuncionarioCTR funcionarioCTR = new FuncionarioCTR();

    int gravar_alterar;

    ResultSet rs = null;
    DefaultTableModel modelo_jtl_consultar_funcionario;

    public void setPosicao() {
        Dimension d = this.getDesktopPane().getSize();
        this.setLocation((d.width - this.getSize().width) / 2, (d.height - this.getSize().height) / 2);
    }//Fecha método setPosicao()

    private void gravar() {
        try {
            funcionarioDTO.setNome_fun(nome_fun.getText());
            funcionarioDTO.setCpf_fun(cpf_fun.getText());
            funcionarioDTO.setLogin_fun(login_fun.getText());
            funcionarioDTO.setSenha_fun(String.valueOf(senha_fun.getPassword()));
            funcionarioDTO.setTipo_fun(tipo_fun.getSelectedItem().toString());

            JOptionPane.showMessageDialog(null,
                    funcionarioCTR.inserirFuncionario(funcionarioDTO)
            );
        } catch (Exception e) {
            System.out.println("Erro ao Gravar" + e.getMessage());
        }
    }//Fecha método gravar()

    private void alterar() {
        try {
            funcionarioDTO.setNome_fun(nome_fun.getText());
            funcionarioDTO.setCpf_fun(cpf_fun.getText());
            funcionarioDTO.setLogin_fun(login_fun.getText());
            funcionarioDTO.setSenha_fun(String.valueOf(senha_fun.getPassword()));
            funcionarioDTO.setTipo_fun(tipo_fun.getSelectedItem().toString());

            if ((CheckAlterarSenha.isSelected()) && (senha_fun.getPassword().length != 0)) {
                funcionarioDTO.setSenha_fun(String.valueOf(senha_fun.getPassword()));
            } else {
                funcionarioDTO.setSenha_fun(null);
            }

            JOptionPane.showMessageDialog(null,
                    funcionarioCTR.alterarFuncionario(funcionarioDTO)
            );
        } catch (Exception e) {
            System.out.println("Erro ao Alterar" + e.getMessage());
        }
    }//Fecha método alterar())

    private void excluir() {
        if (JOptionPane.showConfirmDialog(null, "Deseja Realmente excluir o Funcionario?", "Aviso",
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(null,
                    funcionarioCTR.excluirFuncionario(funcionarioDTO)
            );
        }
    }//Fecha método excluir()

    private void liberaCampos(boolean a) {
        nome_fun.setEnabled(a);
        cpf_fun.setEnabled(a);
        login_fun.setEnabled(a);
        tipo_fun.setEnabled(a);
        senha_fun.setEnabled(a);
        CheckAlterarSenha.setEnabled(a);
    }//Fecha método liberaCampos(boolean a)

    private void liberaBotoes(boolean a, boolean b, boolean c, boolean d, boolean e) {
        btnNovo.setEnabled(a);
        btnSalvar.setEnabled(b);
        btnCancelar.setEnabled(c);
        btnExcluir.setEnabled(d);
        btnSair.setEnabled(e);
    }//Fecha método liberaBotoes(boolean a, boolean b, boolean c, boolean d, boolean e)

    private void limpaCampos() {
        nome_fun.setText("");
        cpf_fun.setText("");
        login_fun.setText("");
        senha_fun.setText("");
        CheckAlterarSenha.setSelected(false);
    }//Fecha método limpaCampos()

    private void preencheTabela(String nome_fun) {
        try {
            //Limpa todas as linhas
            modelo_jtl_consultar_funcionario.setNumRows(0);
            //Enquanto tiver linhas - faça
            funcionarioDTO.setNome_fun(nome_fun);
            rs = funcionarioCTR.consultarFuncionario(funcionarioDTO, 1); //1 = é a pesquisa por nome na classe DAO
            while (rs.next()) {
                modelo_jtl_consultar_funcionario.addRow(new Object[]{
                    rs.getString("id_fun"),
                    rs.getString("nome_fun"),});
            }
        } catch (Exception erTab) {
            System.out.println("Erro SQL: " + erTab);
        } finally {
            funcionarioCTR.CloseDB();
        }
    }//Fecha método preencheTabela(String nome_for)

    private void preencheCampos(int id_fun) {
        try {
            funcionarioDTO.setId_fun(id_fun);
            rs = funcionarioCTR.consultarFuncionario(funcionarioDTO, 2); //2 = é a pesquisa no id na classe DAO
            if (rs.next()) {
                limpaCampos();

                nome_fun.setText(rs.getString("nome_fun"));
                cpf_fun.setText(rs.getString("cpf_fun"));
                login_fun.setText(rs.getString("login_fun"));
                tipo_fun.setSelectedItem(rs.getString("tipo_fun"));

                gravar_alterar = 2;
                liberaCampos(true);
                senha_fun.setEnabled(false);
                CheckAlterarSenha.setSelected(false);
            }//fecha if(rs.next)
        }//fecha try
        catch (Exception erTab) {
            System.out.println("Erro SQL: " + erTab);
        } finally {
            funcionarioCTR.CloseDB();
        }
    }//Fecha método preencheCampos(int id_for)

    /**
     * Creates new form FuncionarioVIEW
     */
    public FuncionarioVIEW() {
        initComponents();

        //Chama todos os métodos liberaCampos
        liberaCampos(false);
        //Chama o método liberaBotoes
        liberaBotoes(true, false, false, false, true);
        modelo_jtl_consultar_funcionario = (DefaultTableModel) jtl_consultar_funcionario.getModel();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jCheckBox1 = new javax.swing.JCheckBox();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        nome_fun = new javax.swing.JTextField();
        cpf_fun = new javax.swing.JTextField();
        login_fun = new javax.swing.JTextField();
        tipo_fun = new javax.swing.JComboBox<>();
        CheckAlterarSenha = new javax.swing.JCheckBox();
        jLabel8 = new javax.swing.JLabel();
        pesquisa_nome_fun = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtl_consultar_funcionario = new javax.swing.JTable();
        btnPesquisar = new javax.swing.JButton();
        btnNovo = new javax.swing.JButton();
        btnSalvar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        btnSair = new javax.swing.JButton();
        senha_fun = new javax.swing.JPasswordField();

        jCheckBox1.setText("jCheckBox1");

        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("Funcionario");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(318, 28, -1, -1));

        jLabel2.setText("Consultar");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(944, 28, -1, -1));

        jLabel3.setText("Nome:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(36, 85, -1, -1));

        jLabel4.setText("CPF:");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(48, 130, -1, -1));

        jLabel5.setText("Login:");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(39, 181, -1, -1));

        jLabel6.setText("Senha:");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(37, 230, -1, -1));

        jLabel7.setText("Tipo:");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(46, 282, -1, -1));

        nome_fun.setNextFocusableComponent(cpf_fun);
        getContentPane().add(nome_fun, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 82, 550, -1));

        cpf_fun.setNextFocusableComponent(login_fun);
        getContentPane().add(cpf_fun, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 127, 286, -1));

        login_fun.setText(" ");
        login_fun.setNextFocusableComponent(senha_fun);
        getContentPane().add(login_fun, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 178, 550, -1));

        tipo_fun.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Administrador", "Comum" }));
        tipo_fun.setNextFocusableComponent(btnSalvar);
        getContentPane().add(tipo_fun, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 279, 278, -1));

        CheckAlterarSenha.setText("Alterar a senha ");
        CheckAlterarSenha.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CheckAlterarSenhaMouseClicked(evt);
            }
        });
        CheckAlterarSenha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CheckAlterarSenhaActionPerformed(evt);
            }
        });
        getContentPane().add(CheckAlterarSenha, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 230, -1, -1));

        jLabel8.setText("Nome:");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(712, 85, -1, -1));
        getContentPane().add(pesquisa_nome_fun, new org.netbeans.lib.awtextra.AbsoluteConstraints(766, 82, 278, -1));

        jtl_consultar_funcionario.setModel(new javax.swing.table.DefaultTableModel(
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
        jtl_consultar_funcionario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtl_consultar_funcionarioMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jtl_consultar_funcionario);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 120, 461, 196));

        btnPesquisar.setText("OK");
        btnPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisarActionPerformed(evt);
            }
        });
        getContentPane().add(btnPesquisar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1062, 82, -1, -1));

        btnNovo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/projeto_avaliacao_2/view/imagens/novo.png"))); // NOI18N
        btnNovo.setText("Novo");
        btnNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoActionPerformed(evt);
            }
        });
        getContentPane().add(btnNovo, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 340, -1, -1));

        btnSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/projeto_avaliacao_2/view/imagens/salvar.png"))); // NOI18N
        btnSalvar.setText("Salvar");
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });
        getContentPane().add(btnSalvar, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 340, -1, -1));

        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/projeto_avaliacao_2/view/imagens/cancelar.png"))); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        getContentPane().add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 340, -1, -1));

        btnExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/projeto_avaliacao_2/view/imagens/excluir.png"))); // NOI18N
        btnExcluir.setText("Excluir");
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });
        getContentPane().add(btnExcluir, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 340, -1, -1));

        btnSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/projeto_avaliacao_2/view/imagens/sair.png"))); // NOI18N
        btnSair.setText("Sair");
        btnSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSairActionPerformed(evt);
            }
        });
        getContentPane().add(btnSair, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 340, -1, -1));

        senha_fun.setNextFocusableComponent(tipo_fun);
        getContentPane().add(senha_fun, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 230, 280, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoActionPerformed
        liberaCampos(true);
        CheckAlterarSenha.setEnabled(false);
        liberaBotoes(false, true, true, false, true);
        gravar_alterar = 1;
    }//GEN-LAST:event_btnNovoActionPerformed

    private void btnPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisarActionPerformed
        preencheTabela(pesquisa_nome_fun.getText());
    }//GEN-LAST:event_btnPesquisarActionPerformed

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        if (gravar_alterar == 1) {
            gravar();
            gravar_alterar = 0;
        } else {
            if (gravar_alterar == 2) {
                alterar();
                gravar_alterar = 0;
            } else {
                JOptionPane.showMessageDialog(null, "Erro no Sistema!!!");
            }
        }

        limpaCampos();
        liberaCampos(false);
        liberaBotoes(true, false, false, false, true);
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void jtl_consultar_funcionarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtl_consultar_funcionarioMouseClicked
        preencheCampos(Integer.parseInt(String.valueOf(
                jtl_consultar_funcionario.getValueAt(
                        jtl_consultar_funcionario.getSelectedRow(), 0))));
        liberaBotoes(false, true, true, true, true);
    }//GEN-LAST:event_jtl_consultar_funcionarioMouseClicked

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        excluir();
        limpaCampos();
        liberaCampos(false);
        liberaBotoes(true, false, false, false, true);
        modelo_jtl_consultar_funcionario.setNumRows(0);
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        limpaCampos();
        liberaCampos(false);
        modelo_jtl_consultar_funcionario.setNumRows(0);
        liberaBotoes(true, false, false, false, true);
        gravar_alterar = 0;
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSairActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnSairActionPerformed

    private void CheckAlterarSenhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CheckAlterarSenhaActionPerformed
      /*  if (CheckAlterarSenha.isSelected()) {
            senha_fun.setEditable(true);
        } else {
            senha_fun.setEditable(false);
            senha_fun.setText(null);
        }*/
    }//GEN-LAST:event_CheckAlterarSenhaActionPerformed

    private void CheckAlterarSenhaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CheckAlterarSenhaMouseClicked
        if (CheckAlterarSenha.isSelected()) {
            senha_fun.setEditable(true);
        } else {
            senha_fun.setEditable(false);
            senha_fun.setText(null);
        }
    }//GEN-LAST:event_CheckAlterarSenhaMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox CheckAlterarSenha;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnNovo;
    private javax.swing.JButton btnPesquisar;
    private javax.swing.JButton btnSair;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JTextField cpf_fun;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jtl_consultar_funcionario;
    private javax.swing.JTextField login_fun;
    private javax.swing.JTextField nome_fun;
    private javax.swing.JTextField pesquisa_nome_fun;
    private javax.swing.JPasswordField senha_fun;
    private javax.swing.JComboBox<String> tipo_fun;
    // End of variables declaration//GEN-END:variables
}
