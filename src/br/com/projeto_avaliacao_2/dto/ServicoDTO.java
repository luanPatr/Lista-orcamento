/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.projeto_avaliacao_2.dto;



/**
 *
 * @author Luan Souza
 */
public class ServicoDTO {
    
    private String nome_serv, desc_serv, cod_serv;
    private double p_custo_serv;
    private int id_servico; 

    public String getNome_serv() {
        return nome_serv;
    }

    public void setNome_serv(String nome_serv) {
        this.nome_serv = nome_serv;
    }

    public String getDesc_serv() {
        return desc_serv;
    }

    public void setDesc_serv(String desc_serv) {
        this.desc_serv = desc_serv;
    }

    public String getCod_serv() {
        return cod_serv;
    }

    public void setCod_serv(String cod_serv) {
        this.cod_serv = cod_serv;
    }

    public double getP_custo_serv() {
        return p_custo_serv;
    }

    public void setP_custo_serv(double p_custo_serv) {
        this.p_custo_serv = p_custo_serv;
    }

    public int getId_servico() {
        return id_servico;
    }

    public void setId_servico(int id_servico) {
        this.id_servico = id_servico;
    }

    public String getId_serv() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void setId_serv(int id_serv) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
}
