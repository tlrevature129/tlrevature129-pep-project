import Controller.SocialMediaController;
import DAO.AccountDAO;
import Model.Account;
import Model.Message;
import Service.AccountService;
import Service.MessageService;
import Util.ConnectionUtil;
import io.javalin.Javalin;

/**
 * This class is provided with a main method to allow you to manually run and test your application. This class will not
 * affect your program in any way and you may write whatever code you like here.
 */
public class Main {
    public static void main(String[] args) {
        //SocialMediaController controller = new SocialMediaController();
        //Javalin app = controller.startAPI();
        //app.start(8080);

        //tests
        ConnectionUtil.resetTestDatabase();
        Account acc = new Account("test1", "success");
        AccountService accountService = new AccountService();
        System.out.println("register: " + accountService.registerAccount(acc));
        System.out.println("login: " + accountService.login(acc));
        
        //create message
        //create success existing id,  0 < message length < 255
        Message message = new Message(1, "good text", 13216548);
        MessageService messageService = new MessageService();

        System.out.println("create message: " + messageService.createMessage(message));
    }
}
