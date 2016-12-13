package chat;

import accounts.AccountService;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;

@SuppressWarnings("UnusedDeclaration")
@WebSocket
public class ChatWebSocket {
    private ChatService chatService;
    private Session session;
    private AccountService accountService;
   // private String sessionId;

    public ChatWebSocket(ChatService chatService, AccountService accountService, String sessionId) {
        this.chatService = chatService;
        this.accountService = accountService;
    //    this.sessionId = sessionId;
        String s = "users:"+accountService.getAllUsersBySession();
        chatService.sendMessage("users:/n"+accountService.getAllUsersBySession());
    }

    @OnWebSocketConnect
    public void onOpen(Session session) {
        chatService.add(this);
        this.session = session;

    }

    @OnWebSocketMessage
    public void onMessage(String data) {
        chatService.sendMessage(data);
    }

    @OnWebSocketClose
    public void onClose(int statusCode, String reason) {
        chatService.remove(this);
    }

    public void sendString(String data) {
        try {
            session.getRemote().sendString(data);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
