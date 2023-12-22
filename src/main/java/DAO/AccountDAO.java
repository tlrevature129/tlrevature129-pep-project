package DAO;

import java.sql.*;

import Model.Account;
import Util.ConnectionUtil;

public class AccountDAO {
    /**
     * create account by inserting username and password
     * @param account
     * @return Account if registration is successful, else return null
     */

     public Account registerAccount(Account account){

        Connection connection = ConnectionUtil.getConnection();

        try{
            String sql = "INSERT INTO account (username, password) VALUES (?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            
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
            e.printStackTrace();
        }

        return null;
     }


     /**
      *checks login credentials
      *@param account 
      *@return Account if exist and matches, else return null
      */
     public Account login(Account account){

        Connection connection = ConnectionUtil.getConnection();

        try{
            String sql = "SELECT * FROM account WHERE username = ? AND password = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            
            preparedStatement.setString(1, account.getUsername());
            preparedStatement.setString(2, account.getPassword());

            preparedStatement.executeQuery();

            ResultSet rs = preparedStatement.executeQuery();

            if(rs.next()){
                 int account_id = (int) rs.getLong(1);
                 String account_username = rs.getString(2);
                 String account_password = rs.getString(3);

                 return new Account(account_id, account_username, account_password);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return null;
     }

     public Account getAccountById(int id){

        Connection connection = ConnectionUtil.getConnection();

        try{
            String sql = "SELECT * FROM account WHERE account_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            
            preparedStatement.setInt(1, id);

            preparedStatement.executeQuery();
            ResultSet rs = preparedStatement.executeQuery();

            if(rs.next()){
                 int account_id = (int) rs.getLong(1);
                 String account_username = rs.getString(2);
                 String account_password = rs.getString(3);

                 return new Account(account_id, account_username, account_password);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return null;
     }
}
