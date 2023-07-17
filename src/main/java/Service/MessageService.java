package Service;

import DAO.MessageDAO;
import Model.Message;
import java.util.ArrayList;


public class MessageService {
    private MessageDAO messageDAO;
    public MessageService(){
        messageDAO = new MessageDAO();
    }
    public MessageService(MessageDAO messageDAO){
        this.messageDAO = messageDAO;
    }

    public ArrayList<Message> getAllMessages() {
        return messageDAO.getAllMessages();
    }
    
    public Message addMessage(Message message) {        
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
    if( !list.contains(message) || newMessage.message_text == "" ||  newMessage.message_text.length() >= 255){
        return null;
    }
        if(list.contains(message) && newMessage.message_text != "" && newMessage.message_text.length() < 255){
            return (MessageDAO.updateMessageById(message, newMessage));
        }   
    return null;
}
public ArrayList<Message> retrieveMessageByID(int id) {        
    ArrayList<Message> list = new ArrayList<>();
    list = messageDAO.getAllMessages();
    ArrayList<Message> name = new ArrayList<>();    
    for(Message i : list){
        if(i.posted_by == id){
            name.addAll(messageDAO.getMessageByUserId(i.posted_by));  
        }
        if(i.posted_by != id){
            return null;
        }               
}
return name;}
}