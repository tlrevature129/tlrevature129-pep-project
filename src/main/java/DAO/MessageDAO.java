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
                int message_id = rs.getInt("message_id");
                int posted_by = rs.getInt("posted_by");
                String message_text = rs.getString("message_text");
                Long time_posted_epoch = rs.getLong("time_posted_epoch");
                return new Message(message_id, posted_by, message_text, time_posted_epoch);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        
        return null;
     }
     
}
