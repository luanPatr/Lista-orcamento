/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.projeto_avaliacao_2.ctr;

import java.sql.ResultSet;
import br.com.projeto_avaliacao_2.dto.FuncionarioDTO;
import br.com.projeto_avaliacao_2.dao.FuncionarioDAO;
import br.com.projeto_avaliacao_2.dao.ConexaoDAO;

/**
 *
 * @author Luan Souza
 */
public class FuncionarioCTR {
    
    FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
    
    //metodo construtor 
    public FuncionarioCTR(){
        
    }
    
    public String inserirFuncionario(FuncionarioDTO funcionarioDTO) {
        try {
            //Chama o metodo que esta na classe DAO aguardando uma resposta (true ou false)
            if (funcionarioDAO.inserirFuncionario(funcionarioDTO)) {
                return "Funcionário Cadastrado com Sucesso!!!";
            } else {
                return "Funcinário NÃO Cadastrado!!!";
            }
        } //Caso tenha algum erro no codigo acima é enviado uma mensagem no 
          //console com o que esta acontecendo.		
        catch (Exception e) {
            System.out.println(e.getMessage());
            return "Funcionario NÃO Cadastrado";
        }
    }//Fecha o método inserirFuncionario
    
    public String alterarFuncionario(FuncionarioDTO funcionarioDTO) {
        try {
            //Chama o metodo que esta na classe DAO aguardando uma resposta (true ou false)
            if (funcionarioDAO.alterarFuncionario(funcionarioDTO)) {
                return "Funcionário Alterado com Sucesso!!!";
            } else {
                return "Funcionário NÃO Alterado!!!";
            }
        } //Caso tenha algum erro no codigo acima é enviado uma mensagem no 
          //console com o que esta acontecendo.
        catch (Exception e) {
            System.out.println(e.getMessage());
            return "Funcionário NÃO Alterado!!!";
        }
    }//Fecha o método alterarFuncionario
    
    public String excluirFuncionario(FuncionarioDTO funcionarioDTO) {
        try {
            //Chama o metodo que esta na classe DAO aguardando uma resposta (true ou false)
            if (funcionarioDAO.excluirFuncionario(funcionarioDTO)) {
                return "Funcionário Excluído com Sucesso!!!";
            } else {
                return "Funcionário NÃO Excluído!!!";
            }
        } //Caso tenha algum erro no codigo acima é enviado uma mensagem no 
          //console com o que esta acontecendo.
        catch (Exception e) {
            System.out.println(e.getMessage());
            return "Funcionário NÃO Excluído!!!";
        }
    }//Fecha o método excluirFuncionario
    
    public ResultSet consultarFuncionario(FuncionarioDTO funcionarioDTO, int opcao) {
        //É criado um atributo do tipo ResultSet, pois este método recebe o resultado de uma consulta.
        ResultSet rs = null;
        //O atributo rs recebe a consulta realizada pelo método da classe DAO
        rs = funcionarioDAO.consultarFuncionario(funcionarioDTO, opcao);
        return rs;
    }//Fecha o método consultarCliente
    
    public String logarFuncionario(FuncionarioDTO funcionarioDTO) {
        
        return funcionarioDAO.logarFuncionario(funcionarioDTO);

    }//Fecha o método logarFuncionario
    
    public void CloseDB() {
        ConexaoDAO.CloseDB();
    }//Fecha o método CloseDB
    
}//fecha classe  FuncionarioCTR
