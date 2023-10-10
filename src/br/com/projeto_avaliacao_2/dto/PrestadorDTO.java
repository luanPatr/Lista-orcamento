/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.projeto_avaliacao_2.dto;

import java.util.Date;
import javax.swing.JTextField;

/**
 *
 * @author Luan Souza
 */
public class PrestadorDTO {
    
    private String nome_prest, cnpj_prest, tel_prest;
    private Date data_cad_prest;
    private int id_prest; 

    public String getNome_prest() {
        return nome_prest;
    }

    public void setNome_prest(String nome_prest) {
        this.nome_prest = nome_prest;
    }

    public String getCnpj_prest() {
        return cnpj_prest;
    }

    public void setCnpj_prest(String cnpj_prest) {
        this.cnpj_prest = cnpj_prest;
    }

    public String getTel_prest() {
        return tel_prest;
    }

    public void setTel_prest(String tel_prest) {
        this.tel_prest = tel_prest;
    }

    public Date getData_cad_prest() {
        return data_cad_prest;
    }

    public void setData_cad_prest(Date data_cad_prest) {
        this.data_cad_prest = data_cad_prest;
    }

    public int getId_prest() {
        return id_prest;
    }

    public void setId_prest(int id_prest) {
        this.id_prest = id_prest;
    }

    public void setNome_prest(JTextField nome_prest) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
