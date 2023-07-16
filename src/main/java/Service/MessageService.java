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
        // ArrayList<Message> list = new ArrayList<>();
        // list = messageDAO.getAllMessages();
        // Message user = new Message();
        // boolean ans = list.contains(message);
            Message name = new Message();
    if (message.message_text =="" || message.message_text.length() >= 255){
        return null;
    }
    if (message.message_text !="" && message.message_text.length() < 255){
        name = MessageDAO.insertMessage(message);
   }return name;
}
public Message retrieveMessage(int id) {        
    ArrayList<Message> list = new ArrayList<>();
    list = messageDAO.getAllMessages();
    Message name = new Message();    
    // boolean ans = list.contains(message);
    for(Message i : list){
        if(i.message_id==id){
            name = messageDAO.getMessageById(id);
        }
        if(i.message_id!=id){
            return null;
        }
    }       
            return name;
}
public Message deleteMessage(int id) {        
    ArrayList<Message> list = new ArrayList<>();
    list = messageDAO.getAllMessages();
    Message name = new Message();    
    // boolean ans = list.contains(message);
    for(Message i : list){
        if(i.message_id==id){
            name = messageDAO.deleteMessageById(id);
        }
        if(i.message_id!=id){
            return null;
        }
    }       
            return name;
}
public Message updateMessage(Message message, Message newMessage) {        
    ArrayList<Message> list = new ArrayList<>();
    list = messageDAO.getAllMessages();
    // Message name = new Message();    
    
    // boolean ans = list.contains(id);
    
    if( !list.contains(message) || newMessage.message_text == "" ||  newMessage.message_text.length() >= 255){
        return null;
    }
        if(list.contains(message) && newMessage.message_text != "" && newMessage.message_text.length() < 255){
            return (MessageDAO.updateMessageById(message, newMessage));
        }
        
    
    return null;
}}