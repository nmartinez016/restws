package fr.univtln.nmartinez016;

import org.glassfish.grizzly.http.server.HttpHandler;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.server.ServerConfiguration;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpContainer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ContainerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.core.UriBuilder;
import java.io.IOException;
import java.net.URI;

/**
 * Created by marti on 17/05/2016.
 */
public class Main {

    private static int getPort(int defaultPort) {
        //grab port from environment, otherwise fall back to default port 9998
        String httpPort = System.getProperty("jersey.test.port");
        if (null != httpPort) {
            try {
                return Integer.parseInt(httpPort);
            } catch (NumberFormatException e) {
            }
        }
        return defaultPort;
    }

    private static URI getBaseURI() {
        return UriBuilder.fromUri("http://0.0.0.0/").port(getPort(9998)).build();
    }

    public static final URI BASE_URI = getBaseURI();

    protected static HttpServer startServer() throws IOException {

        ResourceConfig config = new ResourceConfig().packages("fr.univtln.nmartinez016");

        System.out.println("Starting grizzly2...");
        HttpHandler handler = ContainerFactory.createContainer(GrizzlyHttpContainer.class, config);
        HttpServer server = GrizzlyHttpServerFactory.createHttpServer(BASE_URI);
        ServerConfiguration configuration = server.getServerConfiguration();
        configuration.addHttpHandler(handler, "/");
        return server;
    }

    public static void main(String[] args) throws IOException {
        // Grizzly 2 initialization
        HttpServer httpServer = startServer();
        System.out.println(String.format("Jersey app started with WADL available at "
                        + "%sapplication.wadl\nHit enter to stop it...",
                BASE_URI));
        while(true);
    }
}
