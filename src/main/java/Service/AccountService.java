package Service;


import DAO.AccountDAO;
import Model.Account;

import java.util.HashSet;

public class AccountService {
    private AccountDAO accountDAO;
    public AccountService(){
        accountDAO = new AccountDAO();
    }
    public AccountService(AccountDAO accountDAO){
        this.accountDAO = accountDAO;
    }

    public HashSet<Account> getAllAccounts() {
        return accountDAO.getAllAccounts();
    }
    
    public Account addAccount(Account account) {        
        HashSet<Account> list = new HashSet<>();
        list = accountDAO.getAllAccounts();
        boolean ans = list.contains(account);
      
        if (ans || account.username=="" || account.password.length() < 4) {
            return null;
        } else {
            Account name = accountDAO.insertAccount(account);
           
            return name;
        }
        
    }
}
