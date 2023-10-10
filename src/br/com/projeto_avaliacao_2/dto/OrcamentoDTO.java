/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.projeto_avaliacao_2.dto;

import java.util.Date;

/**
 *
 * @author Luan Souza
 */
public class OrcamentoDTO {

    public int getId_orcamento() {
        return id_orcamento;
    }

    public void setId_orcamento(int id_orcamento) {
        this.id_orcamento = id_orcamento;
    }

    public double getVal_orcamento() {
        return val_orcamento;
    }

    public void setVal_orcamento(double val_orcamento) {
        this.val_orcamento = val_orcamento;
    }

    public Date getDat_orcamento() {
        return dat_orcamento;
    }

    public void setDat_orcamento(Date dat_orcamento) {
        this.dat_orcamento = dat_orcamento;
    }
    
    private int id_orcamento;
    private double val_orcamento;
    private Date dat_orcamento;
    
}
