/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resouces;

import javax.ws.rs.Produces;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author simon
 */
@Path("generic")
public class GenericResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getText() {
        return "Hello rest world!";
    }

    @GET
    @Path("extra")
    public String getMoreInfo() {
        return "This is extra stuff";
    }
    
    @DELETE
    @Path("extra")
    public String deleteSomeStuff() {
        return "ouch, you deleted something";
    }
}
