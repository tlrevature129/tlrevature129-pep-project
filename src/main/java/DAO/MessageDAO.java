package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Model.Message;
import Util.ConnectionUtil;

/*
 * message_id int primary key auto_increment,
 * posted_by int,
 * message_text varchar(255),
 * time_posted_epoch bigint,
 * foreign key (posted_by) references  account(account_id)
 */


public class MessageDAO {

    /**
     * Create New Message
     * @param message
     * @return Message is one is created successfully
     */
    public Message createMessage(Message message){
        Connection connection = ConnectionUtil.getConnection();

        try{
            String sql = "INSERT INTO message (posted_by, message_text, time_posted_epoch) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setInt(1, message.getPosted_by());
            preparedStatement.setString(2, message.getMessage_text());
            preparedStatement.setLong(3, message.getTime_posted_epoch());

            preparedStatement.executeUpdate();

            ResultSet rs = preparedStatement.getGeneratedKeys();        
            if(rs.next()){
                int message_id = (int) rs.getLong(1);
                int posted_by = message.getPosted_by();
                String message_text = message.getMessage_text();
                Long time_posted_epoch = message.getTime_posted_epoch();
                return new Message(message_id, posted_by, message_text, time_posted_epoch);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        
        return null;
     }
     
     /**
      * returns all messages in the database
      * @return list of messages in the database
      */
     public List<Message> getAllMessage() {
        Connection connection = ConnectionUtil.getConnection();
        List<Message> messages = new ArrayList<>();
        
        try{
            String sql = "SELECT * FROM message";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
                     
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                int message_id = rs.getInt(1);
                int posted_by = rs.getInt(2);
                String message_text = rs.getString(3);
                long time_posted_epoch = rs.getLong(4);               
                Message message = new Message(message_id, posted_by, message_text, time_posted_epoch);

                messages.add(message);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }

        return messages;
     }

     public Message getMessageByMessageId(int messageId){
        Connection connection = ConnectionUtil.getConnection();

        try{
            String sql = "SELECT * FROM message WHERE message_id = ? ";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, messageId);

            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()){
                int message_id = rs.getInt(1);
                int posted_by = rs.getInt(2);
                String message_text = rs.getString(3);
                long time_posted_epoch = rs.getLong(4);

                return new Message(message_id, posted_by, message_text, time_posted_epoch);
            }
        }catch (SQLException e){
            e.getStackTrace();
        }

        return null;
     }

     public Message deleteMessageById(int messageId){
        return null;
     }
}
