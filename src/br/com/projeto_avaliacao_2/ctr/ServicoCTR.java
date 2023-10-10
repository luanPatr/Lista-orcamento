/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.projeto_avaliacao_2.ctr;

import java.sql.ResultSet;
import br.com.projeto_avaliacao_2.dto.PrestadorDTO;
import br.com.projeto_avaliacao_2.dto.ServicoDTO;
import br.com.projeto_avaliacao_2.dao.ServicoDAO;
import br.com.projeto_avaliacao_2.dao.ConexaoDAO;

/**
 *
 * @author Luan Souza
 */
public class ServicoCTR {
    
    ServicoDAO servicoDAO = new ServicoDAO();
    
    //metodo construtor da classe
    public ServicoCTR(){
        
    }
    
    public String inserirServico(ServicoDTO servicoDTO, PrestadorDTO prestadorDTO) {
        try {
            //Chama o metodo que esta na classe DAO aguardando uma resposta (true ou false)
            if (servicoDAO.inserirServico(servicoDTO, prestadorDTO)) {
                return "Servico Cadastrado com Sucesso!!!";
            } else {
                return "Servico NÃO Cadastrado!!!";
            }
        } //Caso tenha algum erro no codigo acima é enviado uma mensagem no 
          //console com o que esta acontecendo.		
        catch (Exception e) {
            System.out.println(e.getMessage());
            return "Servico NÃO Cadastrado";
        }
    }//Fecha o método inserirServico
    
     public String alterarServico(ServicoDTO servicoDTO, PrestadorDTO prestadorDTO) {
        try {
            //Chama o metodo que esta na classe DAO aguardando uma resposta (true ou false)
            if (servicoDAO.alterarServico(servicoDTO, prestadorDTO)) {
                return "Servico Alterado com Sucesso!!!";
            } else {
                return "Servico NÃO Alterado!!!";
            }
        } //Caso tenha algum erro no codigo acima é enviado uma mensagem no 
          //console com o que esta acontecendo.
        catch (Exception e) {
            System.out.println(e.getMessage());
            return "Servico NÃO Alterado!!!";
        }
    }//Fecha o método alterarServico
    
     public String excluirServico(ServicoDTO servicoDTO) {
        try {
            //Chama o metodo que esta na classe DAO aguardando uma resposta (true ou false)
            if (servicoDAO.excluirServico(servicoDTO)) {
                return "Servico Excluído com Sucesso!!!";
            } else {
                return "Servico NÃO Excluído!!!";
            }
        } //Caso tenha algum erro no codigo acima é enviado uma mensagem no 
          //console com o que esta acontecendo.
        catch (Exception e) {
            System.out.println(e.getMessage());
            return "Servico NÃO Excluído!!!";
        }
    }//Fecha o método excluirServico
     
     public ResultSet consultarServico(ServicoDTO servicoDTO, int opcao) {
        //É criado um atributo do tipo ResultSet, pois este método recebe o resultado de uma consulta.
        ResultSet rs = null;
        //O atributo rs recebe a consulta realizada pelo método da classe DAO
        rs = servicoDAO.consultarServico(servicoDTO, opcao);
        return rs;
    }//Fecha o método consultarServico
     
     public void CloseDB() {
        ConexaoDAO.CloseDB();
    }//Fecha o método CloseDB
    
}//fecha classe ServicoCTR
