/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.projeto_avaliacao_2.ctr;

import java.sql.ResultSet;
import br.com.projeto_avaliacao_2.dto.ClienteDTO;
import br.com.projeto_avaliacao_2.dao.ClienteDAO;
import br.com.projeto_avaliacao_2.dao.ConexaoDAO;

/**
 *
 * @author Luan Souza
 */
public class ClienteCTR {
    
     ClienteDAO clienteDAO = new ClienteDAO();
     
     //metodo construtor da classe
     public ClienteCTR(){
         
     }
    
     
     public String inserirCliente(ClienteDTO clienteDTO) {
        try {
            //Chama o metodo que esta na classe DAO aguardando uma resposta (true ou false)
            if (clienteDAO.inserirCliente(clienteDTO)) {
                return "Cliente Cadastrado com Sucesso!!!";
            } else {
                return "Cliente NÃO Cadastrado!!!";
            }
        } //Caso tenha algum erro no codigo acima é enviado uma mensagem no 
          //console com o que esta acontecendo.		
        catch (Exception e) {
            System.out.println(e.getMessage());
            return "Cliente NÃO Cadastrado";
        }
    }//Fecha o método inserirCliente
     
     public String alterarCliente(ClienteDTO clienteDTO) {
        try {
            //Chama o metodo que esta na classe DAO aguardando uma resposta (true ou false)
            if (clienteDAO.alterarCliente(clienteDTO)) {
                return "Cliente Alterado com Sucesso!!!";
            } else {
                return "Cliente NÃO Alterado!!!";
            }
        } //Caso tenha algum erro no codigo acima é enviado uma mensagem no 
          //console com o que esta acontecendo.
        catch (Exception e) {
            System.out.println(e.getMessage());
            return "Cliente NÃO Alterado!!!";
        }
    }//Fecha o método alterarCliente
     
     public String excluirCliente(ClienteDTO clienteDTO) {
        try {
            //Chama o metodo que esta na classe DAO aguardando uma resposta (true ou false)
            if (clienteDAO.excluirCliente(clienteDTO)) {
                return "Cliente Excluído com Sucesso!!!";
            } else {
                return "Cliente NÃO Excluído!!!";
            }
        } //Caso tenha algum erro no codigo acima é enviado uma mensagem no 
          //console com o que esta acontecendo.
        catch (Exception e) {
            System.out.println(e.getMessage());
            return "Cliente NÃO Excluído!!!";
        }
    }//Fecha o método excluirCliente
     
     public ResultSet consultarCliente(ClienteDTO clienteDTO, int opcao) {
        //É criado um atributo do tipo ResultSet, pois este método recebe o resultado de uma consulta.
        ResultSet rs = null;
        //O atributo rs recebe a consulta realizada pelo método da classe DAO
        rs = clienteDAO.consultarCliente(clienteDTO, opcao);
        return rs;
    }//Fecha o método consultarCliente
     
     public void CloseDB() {
        ConexaoDAO.CloseDB();
    }//Fecha o método CloseDB
     
}//fecha classe ClienteCTR
