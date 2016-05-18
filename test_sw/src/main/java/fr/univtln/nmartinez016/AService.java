package fr.univtln.nmartinez016;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

/**
 * Created by marti on 17/05/2016.
 */

@Path("/as")
public class AService {

    @POST
    @Consumes("application/json")
    public void post(String pJson){
        System.out.println("salut");
    }
}
