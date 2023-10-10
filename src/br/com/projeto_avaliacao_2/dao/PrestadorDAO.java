/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.projeto_avaliacao_2.dao;
import java.sql.*;
import br.com.projeto_avaliacao_2.dto.PrestadorDTO;
import java.text.SimpleDateFormat;

/**
 *
 * @author Luan Souza
 */
public class PrestadorDAO {
    
    public PrestadorDAO(){
        
    }
    
    SimpleDateFormat data_format= new SimpleDateFormat("dd/mm/yyyy");
    //Atributo do tipo ResultSet utilizado para realizar consultas
    private ResultSet rs = null;
    //Manipular o banco de dados
    private Statement stmt = null;
    
    public boolean inserirPrestador(PrestadorDTO prestadorDTO){
        try{
            ConexaoDAO.ConectDB();
            
            stmt = ConexaoDAO.con.createStatement();
            
            String comando = "Insert into prestador (nome_prest, cnpj_prest, "
                    + "tel_prest, data_cad_prest) values ( "
                    + "'" + prestadorDTO.getNome_prest()+ "', "
                    + "'" + prestadorDTO.getCnpj_prest()+ "', "
                    + "'" + prestadorDTO.getTel_prest()+ "', "
                    + "to_date('" + data_format.format(prestadorDTO.getData_cad_prest())+"','dd/mm/yyyy')) ";
            
            stmt.execute(comando.toUpperCase());
            
            ConexaoDAO.con.commit();
            
            stmt.close();
            return true;        
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }
        finally{
            ConexaoDAO.CloseDB();
        }
    }//fecha o metodo inserir prestador 
    
    public boolean alterarPrestador(PrestadorDTO prestadorDTO){
        try{
            ConexaoDAO.ConectDB();
            
            stmt = ConexaoDAO.con.createStatement();
            
            String comando = "Update prestador set "
                    + "nome_prest = '" + prestadorDTO.getNome_prest()+ "', "
                    + "cnpj_prest = '" + prestadorDTO.getCnpj_prest()+ "', "
                    + "tel_prest = '" + prestadorDTO.getTel_prest()+ "', "
                    + "data_cad_prest = to_date('" + data_format.format(prestadorDTO.getData_cad_prest())+"','dd/mm/yyyy') "
                    + "where id_prest = " + prestadorDTO.getId_prest();
            
            stmt.execute(comando.toUpperCase());
            
            ConexaoDAO.con.commit();
            
            stmt.close();
            return true;     
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }
        finally{
            ConexaoDAO.CloseDB();
        }
    }//fecha metodo alterar prestador
    
    public boolean excluirPrestador(PrestadorDTO prestadorDTO){
        try{
            
            ConexaoDAO.ConectDB();
            
            stmt = ConexaoDAO.con.createStatement();
            
            String comando = "Delete from prestador where id_prest = "
                    + prestadorDTO.getId_prest();
            
            stmt.execute(comando);
            
            ConexaoDAO.con.commit();
            
            stmt.close();
            return true;  
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }
        finally{
            ConexaoDAO.CloseDB();
        }
    }//fecha metodo excluir prestador 
    
    public ResultSet consultarPrestador(PrestadorDTO prestadorDTO, int opcao){
        try{
            
            ConexaoDAO.ConectDB();
            
            stmt = ConexaoDAO.con.createStatement();
            
            String comando = " ";
            
            switch (opcao){
                case 1:
                    comando = "Select f.id_prest, f.nome_prest "+
                              "from prestador f "+
                              "where f.nome_prest ilike '" + prestadorDTO.getNome_prest()+ "%' " +
                              "order by f.nome_prest";    
                break;
                case 2:
                    comando = "Select f.nome_prest, f.cnpj_prest, f.tel_prest, "+
                              "to_char(f.data_cad_prest, 'dd/mm/yyyy') as data_cad_prest "+
                              "from prestador f " +
                              "where f.id_prest = " + prestadorDTO.getId_prest();
                break;
            }
        rs = stmt.executeQuery(comando.toUpperCase());
        return rs;
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return rs;
        }
    }//fecha metodo consultar prestador
    
    
    
}
