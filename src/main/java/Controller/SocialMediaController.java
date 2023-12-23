package Controller;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import Model.Account;
import Model.Message;
import Service.AccountService;
import Service.MessageService;
import io.javalin.Javalin;
import io.javalin.http.Context;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller. The endpoints you will need can be
 * found in readme.md as well as the test cases. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
public class SocialMediaController {

    AccountService accountService;
    MessageService messageService;

    public SocialMediaController(){
        this.accountService = new AccountService();
        this.messageService = new MessageService();
    }
    
    /**
     * In order for the test cases to work, you will need to write the endpoints in the startAPI() method, as the test
     * suite must receive a Javalin object from this method.
     * @return a Javalin app object which defines the behavior of the Javalin controller.
     */
    public Javalin startAPI() {
        Javalin app = Javalin.create();
        app.get("example-endpoint", this::exampleHandler);
        app.post("/register", this::registrationHandler);
        app.post("/login", this::loginHandler);
        app.post("/messages", this::createMessageHandler);
        app.get("/messages", this::getAllMessageHandler);
        app.get("messages/{message_id}", this::getSingleMessageHandler);
        app.delete("messages/{message_id}", this::deleteMessageByIdHandler);
        return app;
    }

    /**
     * This is an example handler for an example endpoint.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    private void exampleHandler(Context context) {
        context.json("sample text");
    }

     /*
      * Handler for registering account
      */
    private void registrationHandler(Context context) throws JsonProcessingException{
        ObjectMapper mapper = new ObjectMapper();
        Account account = mapper.readValue(context.body(), Account.class);
        Account addedAccount = accountService.registerAccount(account);
        if(addedAccount != null){
            context.status(200);
            context.json(mapper.writeValueAsString(addedAccount));
        }else{
            context.status(400);
        }
    }


    /**
     * Handler for login
     */
    private void loginHandler(Context context) throws JsonProcessingException{
        ObjectMapper mapper = new ObjectMapper();
        Account account = mapper.readValue(context.body(), Account.class);
        Account loginAcc = accountService.login(account);
        if(loginAcc != null){
            context.json(mapper.writeValueAsString(loginAcc));
        }else{
            context.status(401);
        }

    }

    /**
     * creates a message if and only if 
     * the message_text is not blank,
     * is under 255 characters, 
     * and posted_by refers to a real, existing user.
     * 
     * responds with Json of the message including its message_id
     * 400 respons on fail
     */
    private void createMessageHandler(Context context) throws JsonProcessingException{
        ObjectMapper mapper = new ObjectMapper();

        Message message = mapper.readValue(context.body(), Message.class);
        int messageLength = message.getMessage_text().length();
        Message newMessage = messageService.createMessage(message);

        //check message length
        if(messageLength > 0 && messageLength <= 255 && newMessage != null){    
            context.json(mapper.writeValueAsString(newMessage));
        }else{
            context.status(400);
        }
    }

    /*
     * respons with a JSON representation of a list containing all messages retrieved from the database
     * 
     */
    private void getAllMessageHandler(Context context) throws JsonProcessingException{
        List<Message> messages = messageService.getAllMessage();
        context.json(messages);
    }

    private void getSingleMessageHandler(Context context) throws JsonProcessingException{
        int messageId = Integer.parseInt(context.pathParam("message_id"));
        Message message = messageService.getMessageByMessageId(messageId);
        if(message != null){
            context.json(messageService.getMessageByMessageId(messageId));
        }else {
            context.status(200);
        }
    }

    private void deleteMessageByIdHandler(Context context) throws JsonProcessingException{
        int messageId = Integer.parseInt(context.pathParam("message_id"));
        Message msg = messageService.deleteMessageById(messageId);
        if(msg != null){
            context.json(msg);
        }else {
            context.status(200);
        }
    }

    
}