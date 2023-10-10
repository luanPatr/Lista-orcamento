/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.projeto_avaliacao_2.ctr;

import javax.swing.JTable;
import br.com.projeto_avaliacao_2.dao.ConexaoDAO;
import br.com.projeto_avaliacao_2.dao.OrcamentoDAO;
import br.com.projeto_avaliacao_2.dto.OrcamentoDTO;
import br.com.projeto_avaliacao_2.dto.ClienteDTO;

/**
 *
 * @author Luan Souza
 */
public class OrcamentoCTR {
    
    OrcamentoDAO orcamentoDAO = new OrcamentoDAO();
    
    //metodo Construtor da classe
    public OrcamentoCTR(){
        
    }    
    
   public String inserirOrcamento(OrcamentoDTO orcamentoDTO, ClienteDTO clienteDTO, JTable servicos) {
        try {
            //Chama o metodo que esta na classe DAO aguardando uma resposta (true ou false)
            if (orcamentoDAO.inserirOrcamento(orcamentoDTO, clienteDTO, servicos)) {
                return "Orcamento Cadastrado com Sucesso!!!";
            } else {
                return "Orcamento NÃO Cadastrado!!!";
            }
        } //Caso tenha algum erro no codigo acima é enviado uma mensagem 
        //no console com o que esta acontecendo.		
        catch (Exception e) {
            System.out.println(e.getMessage());
            return "Orcamento NÃO Cadastrado!!!";
        }
    }//Fecha o método inserirVenda

    
    /**
     * Método utilizado para controlar o acesso ao método CloseDB da classe
     * ConexaoDAO
     */
    public void CloseDB() {
        ConexaoDAO.CloseDB();
    }//Fecha o método CloseDB
}
