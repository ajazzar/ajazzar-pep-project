package Service;

import DAO.AccountDAO;
import DAO.MessageDAO;
import Model.Account;
import Model.Message;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;

public class MessageService {
    private MessageDAO messageDAO;
    private AccountDAO accountDAO;
    public MessageService(){
        messageDAO = new MessageDAO();
    }
    public MessageService(MessageDAO messageDAO){
        this.messageDAO = messageDAO;
    }

    public ArrayList<Message> getAllMessages() {
        return messageDAO.getAllMessages();
    }
//     public Message getAccount(Message message) {
//         ArrayList<Message> list = new ArrayList<>();
//         Message object = new Message();
//         list.addAll(messageDAO.getAllMessages());
//         boolean ans = list.contains(message);
//         if (ans==false && message.username=="" && message.password.length() < 4) {
//             return null;
//         }
//         else{
//             object=(MessageDAO.getMessage(message));   
//         }
//             return object;           
// }

    
    public Message addMessage(Message message) {        
        ArrayList<Account> list = new ArrayList<>();
        list = accountDAO.getAllAccounts();
        Integer user = message.posted_by;
        boolean ans = list.contains(user);
        Message name = new Message();
        if (ans || message.message_text!="" || message.message_text.length() < 255) {
            name = MessageDAO.insertMessage(message);
            return name;
        } else {
           return null;
        }
        
    }
}
