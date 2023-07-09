package Service;


import DAO.AccountDAO;
import Model.Account;

import java.util.List;
public class AccountService {
    public AccountDAO accountDAO;
    public AccountService(){
        accountDAO = new AccountDAO();
    }
    public AccountService(AccountDAO accountDAO){
        this.accountDAO = accountDAO;
    }

    public List<Account> getAllAccounts() {
        return accountDAO.getAllAccounts();
    }
    
    public Account addAccount(Account account) {        
        var list = accountDAO.getAllAccounts();
        
        boolean ans = list.contains(account);
        if (ans) {
            return null;
        } else {
            accountDAO.insertAccount(account);
            return account;
        }
        
    }
}
