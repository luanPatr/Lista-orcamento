/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.projeto_avaliacao_2.dao;
import java.sql.*;

/**
 *
 * @author Luan Souza
 */
public class ConexaoDAO {
    
    public static Connection con = null;


    public ConexaoDAO() {
    }

   
    public static void ConectDB() {
        try {
           
            String dsn = "projeto_avaliacao_2"; //nome do banco de dados(igual ao criado no Postgres)
            String user = "postgres"; //nome do usuario utilizado para se conectar
            String senha = "postdba"; //senha do usuario acima informado
          
            DriverManager.registerDriver(new org.postgresql.Driver());

            String url = "jdbc:postgresql://localhost:5432/" + dsn;
           
            con = DriverManager.getConnection(url, user, senha);

            con.setAutoCommit(false);
            if (con == null) {
                System.out.println("erro ao abrir o banco");
            }
        }//fecha o try
        //Caso ocorra problemas para abrir o banco de dados é emitido a mensagem no console.
        catch (Exception e) {
            System.out.println("Problema ao abrir a base de dados! " + e.getMessage());
        }//fecha o catch
    }//Fecha o método ConectDB

    
    public static void CloseDB() {
        try {
            con.close();
        }//fecha o try
        //Caso ocorra problemas para fechar o banco de dados é emitido a mensagem no console.
        catch (Exception e) {
            System.out.println("Problema ao fechar a base de dados! " + 
                e.getMessage());
        }//fecha o catch
    }//Fecha o método CloseDB
    
}//fecha classe conexao dao
