package Service;

import java.util.List;

import DAO.MessageDAO;
import Model.Message;

public class MessageService {
    private MessageDAO messageDAO;

    public MessageService(){
        this.messageDAO = new MessageDAO();
    }

    public Message createMessage(Message message){
        AccountService accountService = new AccountService();
        if(accountService.getAccount(message.getPosted_by()) != null){
            return messageDAO.createMessage(message);
        }
        return null;
    }

    public List<Message> getAllMessage(){
        return messageDAO.getAllMessage();
    }
}
