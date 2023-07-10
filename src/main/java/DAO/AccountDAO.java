package DAO;

import Util.ConnectionUtil;
import Model.Account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class AccountDAO {
    public HashSet<Account> getAllAccounts(){
        Connection connection = ConnectionUtil.getConnection();
        HashSet<Account> accounts = new HashSet<>();
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

    public Account insertAccount(Account account){
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
               System.out.println("--" + generatedkey); 
               account.setAccount_id(generatedkey);
    }
            
    return account;
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }
}