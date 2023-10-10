/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.projeto_avaliacao_2.dao;

import java.sql.*;
import java.text.SimpleDateFormat;
import javax.swing.JTable;
import br.com.projeto_avaliacao_2.dto.ClienteDTO;
import br.com.projeto_avaliacao_2.dto.OrcamentoDTO;

/**
 *
 * @author Luan Souza
 */
public class OrcamentoDAO {
    
    //metodo construtor da classe
    public OrcamentoDAO(){
        
    }
    
    //Atributo do tipo ResultSet utilizado para realizar consultas
    private ResultSet rs = null;
    //Manipular o banco de dados
    Statement stmt = null;
    Statement stmt1 = null;
    SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");
    
    
    public boolean inserirOrcamento(OrcamentoDTO orcamentoDTO, ClienteDTO clienteDTO, JTable servico) {
        try {  
            //Chama o metodo que esta na classe ConexaoDAO para abrir o banco de dados
            ConexaoDAO.ConectDB();
            //Cria o Statement que responsavel por executar alguma coisa no banco de dados
            stmt = ConexaoDAO.con.createStatement();
            stmt1 = ConexaoDAO.con.createStatement();
            //Comando SQL que sera executado no banco de dados
            String comando1 = "Insert into orcamento (dat_orcamento, val_orcamento, "
                    + "id_cli) values ( "
                    + "to_date('" + date.format(orcamentoDTO.getDat_orcamento()) + "', 'DD/MM/YYYY'), "
                    + orcamentoDTO.getVal_orcamento() + ", "
                    + clienteDTO.getId_cli()+ ")";
     
            //Executa o comando SQL no banco de Dados
            stmt.execute(comando1.toUpperCase(), Statement.RETURN_GENERATED_KEYS);
            rs = stmt.getGeneratedKeys();
            rs.next();
            
            for(int cont=0; cont < servico.getRowCount(); cont++){
                String comando2 = "Insert into servico_orcamento (id_orcamento, id_serv, " 
                    + "val_serv, qtd_serv) values ( "
                    + rs.getInt("id_orcamento") + ", "
                    + servico.getValueAt(cont, 0) + ", "
                    + servico.getValueAt(cont, 2) + ", "
                    + servico.getValueAt(cont, 3) + "); ";
                
                stmt1.execute(comando2);
            }
            //Da um commit no banco de dados
            ConexaoDAO.con.commit();
            //Fecha o statement
            stmt.close();
            stmt1.close();
            rs.close();
            return true;
        } //Caso tenha algum erro no codigo acima é enviado uma mensagem no console com o que esta acontecendo.
        catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        } //Independente de dar erro ou não ele vai fechar o banco de dados.
        finally {
            //Chama o metodo da classe ConexaoDAO para fechar o banco de dados
            ConexaoDAO.CloseDB();
        }
    }//Fecha o método inserirVenda
    
}
