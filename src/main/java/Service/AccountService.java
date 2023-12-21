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
        return accountDAO.createAccount(account);
    }
}
 