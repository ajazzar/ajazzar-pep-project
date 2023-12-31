package Controller;
import io.javalin.Javalin;
import io.javalin.http.Context;
import Model.Account;
import Model.Message;
import Service.AccountService;
import Service.MessageService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Objects;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller. The endpoints you will need can be
 * found in readme.md as well as the test cases. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
public class SocialMediaController {
    MessageService messageService;
    AccountService accountService;

    public SocialMediaController(){
        this.messageService = new MessageService();
        this.accountService = new AccountService();
    }
    /**
     * In order for the test cases to work, you will need to write the endpoints in the startAPI() method, as the test
     * suite must receive a Javalin object from this method.
     * @return a Javalin app object which defines the behavior of the Javalin controller.
     */
    public Javalin startAPI() {
        Javalin app = Javalin.create();
        app.get("/register", this::getAllAccountsHandler);
        app.post("/register", this::accountHandler);
        app.post("/login", this::loginHandler);
        app.post("/messages", this::messageHandler);
        app.get("/messages", this::getAllMessagesHandler);
        app.get("/messages/{message_id}", this::getMessageById);
        app.delete("/messages/{message_id}", this::deleteMessageById);
        app.patch("/messages/{message_id}", this::updateMessage);
        app.get("/accounts/{account_id}/messages", this::getUserMessages);
        return app;
    }

    /**
     * This is an example handler for an example endpoint.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     * @throws JsonProcessingException
     * @throws JsonMappingException
     */
    private void accountHandler(Context context) throws JsonMappingException, JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        
        Account book = mapper.readValue(context.body(), Account.class);
        Account addedAccount = accountService.addAccount(book);
        
        if(addedAccount!=null){
            context.status(200).json(mapper.writeValueAsString(addedAccount));
        }else{
            context.status(400);
        }

    }
    private void getAllAccountsHandler(Context ctx) {
        ArrayList<Account> authors = accountService.getAllAccounts();
        ctx.json(authors);
    }
    private void loginHandler(Context context) throws JsonMappingException, JsonProcessingException  {
        ObjectMapper mapper = new ObjectMapper();
        Account book = mapper.readValue(context.body(), Account.class);
        Account addedBook = accountService.getAccount(book);
        if(addedBook.username != null){
            context.status(200).json(addedBook);
        }else{
            context.status(401);
        }
    }
    private void messageHandler(Context context) throws JsonMappingException, JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Message message = mapper.readValue(context.body(), Message.class);
        Message addedMessage = messageService.addMessage(message);
        if(addedMessage != null){
            context.json(mapper.writeValueAsString(addedMessage));
        }
            else{
                context.status(400);   
    }}
    private void getAllMessagesHandler(Context ctx) {
        ArrayList<Message> messages = messageService.getAllMessages();
        ctx.json(messages);
    }
    private String getMessageById(Context context) throws URISyntaxException, IOException {
        int id = Integer.parseInt(Objects.requireNonNull(( context.pathParam("message_id"))));
        Message addedMessage = messageService.retrieveMessage(id);
        if(addedMessage!=null){
        context.status(200).json(addedMessage);        
        }
        return null;
    }
    private void deleteMessageById(Context context) throws URISyntaxException, IOException {
        int id = Integer.parseInt(Objects.requireNonNull(( context.pathParam("message_id"))));
        Message addedMessage = messageService.retrieveMessage(id);
        if(addedMessage!=null){
        context.status(200).json(addedMessage);
        messageService.deleteMessage(id);       
        }
        if(addedMessage==null){
        context.status(200).json("");
    }}
    private void updateMessage(Context context) throws URISyntaxException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        Message message = mapper.readValue(context.body(), Message.class);
        int id = Integer.parseInt(Objects.requireNonNull(( context.pathParam("message_id"))));
        Message addedMessage = messageService.retrieveMessage(id);
        Message addedMessage2=(messageService.updateMessage(addedMessage, message));
        if(addedMessage2 != null){
        context.status(200);
        context.json(mapper.writeValueAsString(addedMessage2)); 
        }
        if(addedMessage2 == null){
            context.status(400); 
            }
      }
      private void getUserMessages(Context context) throws URISyntaxException, IOException {
            int id = Integer.parseInt(Objects.requireNonNull(( context.pathParam("account_id"))));
            ArrayList<Message> addedMessage = new ArrayList<>();
            if(messageService.retrieveMessageByID(id) != null){
            for(Message i: messageService.retrieveMessageByID(id)){
            if(i.posted_by == id){
                addedMessage.add(i);
            }
        }}
        context.status(200).json(addedMessage); 
        }
        }