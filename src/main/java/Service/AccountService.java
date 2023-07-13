package Service;


import DAO.AccountDAO;
import Model.Account;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;

public class AccountService {
    private AccountDAO accountDAO;
    public AccountService(){
        accountDAO = new AccountDAO();
    }
    public AccountService(AccountDAO accountDAO){
        this.accountDAO = accountDAO;
    }

    public ArrayList<Account> getAllAccounts() {
        return accountDAO.getAllAccounts();
    }
    public Account getAccount(Account account) {
        ArrayList<Account> list = new ArrayList<>();
        Account object = new Account();
        list.addAll(accountDAO.getAllAccounts());
        boolean ans = list.contains(account);
        if (ans==false && account.username=="" && account.password.length() < 4) {
            return null;
        }
        else{
            object=(AccountDAO.getAccount(account));   
        }
            return object;           
}

    
    public Account addAccount(Account account) {        
        ArrayList<Account> list = new ArrayList<>();
        list = accountDAO.getAllAccounts();
        boolean ans = list.contains(account);
      
        if (ans || account.username=="" || account.password.length() < 4) {
            return null;
        } else {
            Account name = AccountDAO.insertAccount(account);
           
            return name;
        }
        
    }
}
