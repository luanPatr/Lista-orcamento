/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.projeto_avaliacao_2.dao;

import java.sql.*;
import br.com.projeto_avaliacao_2.dto.ServicoDTO;
import br.com.projeto_avaliacao_2.dto.PrestadorDTO;

/**
 *
 * @author Luan Souza
 */
public class ServicoDAO {
    
        //metodo construtor da classe
    public ServicoDAO(){
        
    }
    
    //Atributo do tipo ResultSet utilizado para realizar consultas
    private ResultSet rs = null;
    //Manipular o banco de dados
    private Statement stmt = null;
    
    public boolean inserirServico(ServicoDTO servicoDTO, PrestadorDTO prestadorDTO) {
        try {
            //Chama o metodo que esta na classe ConexaoDAO para abrir o banco de dados
            ConexaoDAO.ConectDB();
            //Instancia o Statement que sera responsavel por executar alguma coisa no banco de dados
            stmt = ConexaoDAO.con.createStatement();
            //Comando SQL que sera executado no banco de dados
            String comando = "Insert into servico (nome_serv, desc_serv, cod_serv, "
                    + "p_custo_serv, id_prest) values ( "
                    + "'" + servicoDTO.getNome_serv()+ "', "
                    + "'" + servicoDTO.getDesc_serv()+ "', "
                    + "'" + servicoDTO.getCod_serv()+ "', "
                    + servicoDTO.getP_custo_serv()+ ", "
                    + prestadorDTO.getId_prest()+ ") ";
            
            //Executa o comando SQL no banco de Dados
            stmt.execute(comando.toUpperCase());
            //Da um commit no banco de dados
            ConexaoDAO.con.commit();
            //Fecha o statement
            stmt.close();
            return true;
        } //Caso tenha algum erro no codigo acima é enviado uma mensagem no 
          //console com o que esta acontecendo.
        catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        } //Independente de dar erro ou não ele vai fechar o banco de dados.
        finally {
            //Chama o metodo da classe ConexaoDAO para fechar o banco de dados
            ConexaoDAO.CloseDB();
        }
    }//Fecha o método inserirPServico   
    
    public boolean alterarServico(ServicoDTO servicoDTO, PrestadorDTO prestadorDTO) {
        try {
            //Chama o metodo que esta na classe ConexaoDAO para abrir o banco de dados
            ConexaoDAO.ConectDB();
            //Cria o Statement que responsavel por executar alguma coisa no banco de dados
            stmt = ConexaoDAO.con.createStatement();
            //Comando SQL que sera executado no banco de dados
            String comando = "Update servico set "
                    + "nome_serv = '" + servicoDTO.getNome_serv()+ "', "
                    + "desc_serv = '" + servicoDTO.getDesc_serv()+ "', "
                    + "cod_serv = '" + servicoDTO.getCod_serv()+ "', "
                    + "p_custo_serv = " + servicoDTO.getP_custo_serv()+ ", "
                    + "id_prest= " + prestadorDTO.getId_prest()+ " "
                    + "where id_serv = " + servicoDTO.getId_serv();
                       
            //Executa o comando SQL no banco de Dados
            stmt.execute(comando.toUpperCase());
            //Da um commit no banco de dados
            ConexaoDAO.con.commit();
            //Fecha o statement
            stmt.close();
            return true;
        } //Caso tenha algum erro no codigo acima é enviado uma mensagem 
          //no console com o que esta acontecendo.
        catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        } //Independente de dar erro ou não ele vai fechar o banco de dados.
        finally {
            //Chama o metodo da classe ConexaoDAO para fechar o banco de dados
            ConexaoDAO.CloseDB();
        }
    }//Fecha o método alterarServico
    
     public boolean excluirServico(ServicoDTO servicoDTO) {
        try {
            //Chama o metodo que esta na classe ConexaoDAO para abrir o banco de dados
            ConexaoDAO.ConectDB();
            //Instancia o Statement que responsavel por executar alguma coisa no banco de dados
            stmt = ConexaoDAO.con.createStatement();
            //Comando SQL que sera executado no banco de dados
            String comando = "Delete from servico where id_serv = " 
                             + servicoDTO.getId_serv();

            //Executa o comando SQL no banco de Dados
            stmt.execute(comando);
            //Da um commit no banco de dados
            ConexaoDAO.con.commit();
            //Fecha o statement
            stmt.close();
            return true;
        } //Caso tenha algum erro no codigo acima é enviado uma mensagem no 
          //console com o que esta acontecendo.
        catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        } //Independente de dar erro ou não ele vai fechar o banco de dados.
        finally {
            //Chama o metodo da classe ConexaoDAO para fechar o banco de dados
            ConexaoDAO.CloseDB();
        }
    }//Fecha o método excluirServico
     
     public ResultSet consultarServico(ServicoDTO servicoDTO, int opcao) {
        try {
            //Chama o metodo que esta na classe ConexaoDAO para abrir o banco de dados
            ConexaoDAO.ConectDB();
            //Cria o Statement que responsavel por executar alguma coisa no banco de dados
            stmt = ConexaoDAO.con.createStatement();
            //Comando SQL que sera executado no banco de dados
            String comando = "";
            switch (opcao){
                 case 1:
                    comando = "Select p.* "+
                              "from servico p "+
                              "where p.nome_serv ilike '" + servicoDTO.getNome_serv()+ "%' " +
                              "order by p.nome_serv";    
                break;
                case 2:
                    comando = "Select p. *, f.nome_prest, f.id_prest "+
                              "from servico p, prestador f " +
                              "where p.id_prest = f.id_prest and " +
                              "p.id_servico = " + servicoDTO.getId_servico();
                break;
                                
            }
            //Executa o comando SQL no banco de Dados
            rs = stmt.executeQuery(comando.toUpperCase());
            return rs;
        } //Caso tenha algum erro no codigo acima é enviado uma mensagem no 
          //console com o que esta acontecendo.
        catch (Exception e) {
            System.out.println(e.getMessage());
            return rs;
        }
    }//Fecha o método consultarServico
    
}//Fecha classe ServicoDAO
