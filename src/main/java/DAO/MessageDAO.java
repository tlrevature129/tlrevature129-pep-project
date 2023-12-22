package DAO;

import java.sql.*;

import Model.Message;
import Util.ConnectionUtil;

public class MessageDAO {

    /**
     * inserts message into database
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
     
}
