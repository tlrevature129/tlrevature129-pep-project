package DAO;

import java.sql.*;

import Model.Account;
import Util.ConnectionUtil;

public class AccountDAO {
    /*
     * create account
     * TODO: handle duplicate username
     */

     public Account createAccount(Account account){
        Connection connection = ConnectionUtil.getConnection();
        
        try{
            String sql = "INSERT INTO account (username, password) VALUES (?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            
            preparedStatement.setString(1, account.getUsername());
            preparedStatement.setString(2, account.getPassword());

            preparedStatement.executeUpdate();

            //for checking
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if(rs.next()){
                 int account_id = (int) rs.getLong(1);
                 return new Account(account_id, account.getUsername(), account.getPassword());
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

        return null;
     }

     public Account getUsername(String username){
        Connection connection = ConnectionUtil.getConnection();
        try{
            String sql = "SELECT * FROM account WHERE username = ?; ";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            
            preparedStatement.setString(1, username);

            preparedStatement.executeUpdate();

            //for checking
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if(rs.next()){
                 int account_id = (int) rs.getLong(1);
                 String account_username = rs.getString(2);
                 String account_password = rs.getString(3);
                 return new Account(account_id, account_username, account_password);
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

        return null;
     }
}
