package Service;


import DAO.AccountDAO;
import Model.Account;

import java.util.List;
public class AccountService {
    private AccountDAO accountDAO;
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
        
        List<Account> list = accountDAO.getAllAccounts();
        boolean ans = list.contains(account);
      
        if (ans || account.username=="" || account.password.length() < 4) {
            return null;
        } else {
            Account name = accountDAO.insertAccount(account);
           
            return name;
        }
        
    }
}
