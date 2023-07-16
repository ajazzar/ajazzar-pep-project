package Service;

import DAO.MessageDAO;
import DAO.AccountDAO;
import Model.Account;
import Model.Message;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;

public class AccountService {
    private AccountDAO accountDAO;
    private MessageDAO messageDAO;
    public AccountService(){
        accountDAO = new AccountDAO();
        messageDAO = new MessageDAO();
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
    public Account retrieveMessage(int id) {        
        ArrayList<Account> list = new ArrayList<>();
        list = accountDAO.getAllAccounts();
        // ArrayList<Message> list2 = messageDAO.getAllMessages();
        Account name = new Account();    
        // boolean ans = list.contains(message);
        for(Account i : list){
            if(i.account_id==id){
                name = accountDAO.getAccountById(id);
            }
            if(i.account_id!=id){
                return null;
            }
        }       
                return name;
    }           
    }

