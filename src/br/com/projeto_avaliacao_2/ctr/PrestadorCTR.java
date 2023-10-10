/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.projeto_avaliacao_2.ctr;
import java.sql.ResultSet;
import br.com.projeto_avaliacao_2.dto.PrestadorDTO;
import br.com.projeto_avaliacao_2.dao.PrestadorDAO;
import br.com.projeto_avaliacao_2.dao.ConexaoDAO;

/**
 *
 * @author Luan Souza
 */
public class PrestadorCTR {
    
    PrestadorDAO prestadorDAO = new PrestadorDAO();
    
    //metodo construtor
    public PrestadorCTR(){
        
    }
    
    public String inserirPrestador(PrestadorDTO prestadorDTO){
        try{
            if(prestadorDAO.inserirPrestador(prestadorDTO)) {
                return "Prestador Cadastrado com sucesso!!!";
            }else{
                return "Prestador NÃO cadastrado!!!";
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return "Fornecedor NÃO cadastrado!!!";
        }
    }//fecha metodo inserir fornecedor
    
    public String alterarPrestador(PrestadorDTO prestadorDTO){
        try{
            if(prestadorDAO.alterarPrestador(prestadorDTO)) {
                return "Prestador Alterado com sucesso!!!";
            }else{
                return "Prestador NÃO Alterado!!!";
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return "Fornecedor NÃO Alterado!!!";
        }
    }//fecha metodo alterar fornecedor
    
    public String excluirPrestador(PrestadorDTO prestadorDTO){
        try{
            if(prestadorDAO.excluirPrestador(prestadorDTO)) {
                return "Prestador Excluido com sucesso!!!";
            }else{
                return "Prestador NÃO Excluido!!!";
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return "Fornecedor NÃO Excluido!!!";
        }
    }//fecha metodo excluir fornecedor
    
    public ResultSet consultarPrestador(PrestadorDTO prestadorDTO, int opcao){
        ResultSet rs = null;
        
        rs = prestadorDAO.consultarPrestador(prestadorDTO, opcao);
        return rs;
    }//fecha metodo consultar prestador
    
    public void CloseDB(){
        ConexaoDAO.CloseDB();
    }//fecha metodo CLose
    
}
