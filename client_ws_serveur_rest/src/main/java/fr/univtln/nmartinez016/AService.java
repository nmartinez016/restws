package fr.univtln.nmartinez016;

import org.glassfish.tyrus.client.ClientManager;

import javax.websocket.DeploymentException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by marti on 17/05/2016.
 */

@Path("as")
public class AService {

    @GET
    @Path("/text")
    public String get() {
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
                    URI.create("ws://" + Client.SERVER_IP + ":" + Client.SERVER_PORT + "/echo")
            );
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        } catch (DeploymentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "salut grizzly!";
    }
}
