package fr.univtln.nmartinez016;

import org.glassfish.tyrus.client.ClientManager;

import javax.websocket.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by marti on 17/05/2016.
 */


@ClientEndpoint(encoders = {PayloadBean.PayloadBeanCode.class},
        decoders = {PayloadBean.PayloadBeanCode.class})
public class Client {
    public final static String SERVER_IP;
    public final static int SERVER_PORT;
    private Personne sender;

    public Client(Personne sender) {
        this.sender = sender;
    }

    static {
        String ip = null;
        int port = 8025;
        try {
            ip = System.getProperty("fr.univtln.bruno.test.simple.websocket.server.ip");
        } catch (NullPointerException e) {
        }

        try {
            port = Integer.parseInt(System.getProperty("fr.univtln.bruno.test.simple.websocket.server.port"));
        } catch (NullPointerException e) {
        } catch (NumberFormatException e) {
        }
        if (ip == null) ip = "localhost";
        SERVER_IP = ip;
        SERVER_PORT = port;
        System.out.println("Server IP:" + SERVER_IP + " Port: " + SERVER_PORT);
    }

    private Session session;

    @OnOpen
    public void onOpen(final Session session, EndpointConfig endpointConfig) throws IOException, EncodeException {
        this.session = session;
        System.out.println("I am " + session.getId());
        System.out.println("Sending Hello message to server");
        //session.getBasicRemote().sendObject(new PayloadBean(new Date(),sender,"Hello"));
        //session.getBasicRemote().sendObject(new PayloadBean.PayloadBeanBuilder().date(new Date()).sender(sender).message("Hello").build());

    }

    @OnMessage
    public void OnMessage(PayloadBean bean) {
        System.out.println("RECU !");
        System.out.println(bean.getDate() + " (" //+ bean.getmSender()
                + ") " + bean.getMessage());

        System.out.println("A -> " + bean.getSender().getAs());
        sender = bean.getSender();

    }

    @OnClose
    public void OnClose(final Session session, EndpointConfig endpointConfig) {
        System.out.println("Session closed");
    }

    public void closeSession() throws IOException {
        if (session.isOpen())
            session.close(new CloseReason(CloseReason.CloseCodes.NORMAL_CLOSURE, "OK"));
    }

    public void sendMessage(A pA) {
        //PayloadBean bean = new PayloadBean(new Date(), sender, message);
        PayloadBean bean = new PayloadBean.PayloadBeanBuilder().sender(sender).date(new Date()).a(pA).build();

        System.out.println("j envoie : " + bean);

        /*        System.out.println("The PayloadBean toString(): "+bean);

        //To print the JSON encoded version
        try {
            StringWriter sw = new StringWriter();
            new PayloadBean.PayloadBeanCode().encode(bean, sw);
            System.out.println("The JSON from the Payload: "+sw.toString());
        } catch (EncodeException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
          */
        try {
            session.getBasicRemote().sendObject(bean);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (EncodeException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        A lA1 = new A.ABuilder(50).name("numero 50").energy(100).build();
        A lA2 = new A.ABuilder(55).name("numero 55").energy(100).build();
        List<A> lAs = new ArrayList<A>();
        lAs.add(lA1);
        lAs.add(lA2);
        Client beanClient = new Client(new Personne(1, "John", "Doe", new Team(1, "rouge"), lAs));
        try {
            final ClientManager client = ClientManager.createClient();
            client.connectToServer(
                    beanClient,
                    URI.create("ws://" + SERVER_IP + ":" + SERVER_PORT + "/echo")
            );

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Send empty line to stop the client.");
            String line;
            do {
                line = reader.readLine();
                if (!"".equals(line))
                    beanClient.sendMessage(lA1);
            } while (!"".equals(line));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                beanClient.closeSession();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
