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

    public Message getMessageByMessageId(int messageId){
        return messageDAO.getMessageByMessageId(messageId);
    }

    public Message deleteMessageById(int messageId){
        Message msg = messageDAO.getMessageByMessageId(messageId);
        if(msg != null){
            messageDAO.deleteMessageById(messageId);
            return msg;
        }
        return null;
    }

    public Message updateMessage(int messageId, String newMessage){
        Message oldMsg = messageDAO.getMessageByMessageId(messageId);
        if(oldMsg != null){
            messageDAO.updatMessage(messageId, newMessage);
            return messageDAO.getMessageByMessageId(messageId);
        }
        return null;
    }

    public List<Message> getAllMessagesFromId(int accountId){
        return messageDAO.getAllMessageFromId(accountId);
    }
}
