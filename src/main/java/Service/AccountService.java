package Service;

import DAO.AccountDAO;
import Model.Account;

public class AccountService {
    private AccountDAO accountDAO;

    /**
     * Constructor for AccountServices
     */
    public AccountService(){
        this.accountDAO = new AccountDAO();
    }

    /**
     * Constructor for AccountServices with accountDAO provided.
     * @param accountDAO 
     */
    public AccountService(AccountDAO accountDAO){
        this.accountDAO = accountDAO;
    }

    /**
     * 
     * @param account
     * @return The new account if registration is successful
     */
    public Account registerAccount(Account account){
        if(account.getUsername().equals("")) return null;
        if(account.getPassword().length() < 4) return null;
        return accountDAO.registerAccount(account);
    }

    /**
     * 
     * @param account
     * @return
     */
    public Account login(Account account){
        return accountDAO.login(account);
    }

    public Account getAccount(int account_id){        
        return accountDAO.getAccountById(account_id);
    }
}
 