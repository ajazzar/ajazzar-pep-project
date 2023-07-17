package Service;

import DAO.AccountDAO;
import Model.Account;

import java.util.ArrayList;

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
    public Account retrieveMessage(int id) {        
        ArrayList<Account> list = new ArrayList<>();
        list = accountDAO.getAllAccounts();
        Account name = new Account();    
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

