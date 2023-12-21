import Controller.SocialMediaController;
import DAO.AccountDAO;
import Model.Account;
import Service.AccountService;
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

        
        ConnectionUtil.resetTestDatabase();
        Account acc = new Account("test1", "success");
        AccountService service = new AccountService();
        System.out.println(service.registerAccount(acc));
        System.out.println("login: " + service.login(acc));
    }
}
