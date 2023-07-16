package DAO;

import Util.ConnectionUtil;
import Model.Account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;


public class AccountDAO {
    public ArrayList<Account> getAllAccounts(){
        Connection connection = ConnectionUtil.getConnection();
        ArrayList<Account> accounts = new ArrayList<>();
        try {
            //Write SQL logic here
            String sql = "Select * from Account";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                Account account = new Account(rs.getInt("account_id"),
                        rs.getString("username"),
                        rs.getString("password"));
                accounts.add(account);
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return accounts;
    }
    public static Account getAccount(Account account){
        Connection connection = ConnectionUtil.getConnection();
        Account result = new Account();
        
        // int generatedkey = 0;
        try {
            //Write SQL logic here
            String sql = "select * from Account where username=? AND password=?";
            // int generatedkey=0;
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            //write preparedStatement's setInt method here.
            // preparedStatement.setInt(1, account.account_id);
            // preparedStatement.setInt(1, account.getAccount_id());
            preparedStatement.setString(1, account.username);
            preparedStatement.setString(2, account.password);
            
            
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                System.out.println("--"+rs.getString("username") + ", " + rs.getString("password"));
                result = new Account(rs.getInt("account_id"),
                rs.getString("username"),
                rs.getString("password"));
            }
                     
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return result;
    }

    public static Account insertAccount(Account account){
        Connection connection = ConnectionUtil.getConnection();
        try {
            
            //Write SQL logic here
            String sql = "Insert into Account (username, password) values (?,?)" ;
            int generatedkey=0;
            PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            //write preparedStatement's setString and setInt methods here.
            // preparedStatement.setInt(1, account.getAccount_id());
            preparedStatement.setString(1, account.getUsername());
            preparedStatement.setString(2, account.getPassword());
            preparedStatement.executeUpdate();
           ResultSet rs = preparedStatement.getGeneratedKeys();
    if(rs.next()) {
    //In this exp, the autoKey val is in 1st col
    generatedkey=rs.getInt(1);   
            //    System.out.println("--" + generatedkey); 
               account.setAccount_id(generatedkey);
    }
            
    return account;
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }
    public Account getAccountById(int id2){
            Connection connection = ConnectionUtil.getConnection();
            
            Account result = new Account();
            String sql = "select * from Account where account_id=?";
            try {
                //Write SQL logic here
                
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
    
                //write preparedStatement's setInt method here.
                preparedStatement.setInt(1, id2);
                ResultSet rs = preparedStatement.executeQuery();
               
                while(rs.next()){
                    result = new Account(rs.getInt("account_id"),
                            rs.getString("username"),
                            rs.getString("password"));
                    
                }
               return result; 
            }catch(SQLException e){
                System.out.println(e.getMessage());
            }
            return null;
        }
}