/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resouces;

import javax.ws.rs.Produces;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author simon
 */
@Path("/generic")
public class GenericResource {

    @GET
    @Path("/")
    @Produces(MediaType.TEXT_PLAIN)
    public String getText(@DefaultValue("12345") @QueryParam("zipcode") String zipcode,
            @HeaderParam("x-simon") String simon) {
        System.out.printf("in getText, zipcode is %s, simon is %s\n", zipcode, simon);
        return "Hello rest world from zipcode " + zipcode + " simon is " + simon;
    }

    @GET
    @Path("/")
    @Produces(MediaType.TEXT_HTML)
    public String getHtml() {
        return "<HTML><BODY><H1>Hello HTML rest world!</BODY></HTML>";
    }

    @GET
    @Path("/extra")
    public String getMoreInfo() {
        return "This is extra stuff";
    }
    
    @DELETE
    @Path("/extra")
    public String deleteSomeStuff() {
        return "ouch, you deleted something";
    }
    
    
    @GET
    @Path("/extra/{thePrimaryKey}")
    @Produces(MediaType.TEXT_PLAIN)
    public String get123(@PathParam("thePrimaryKey") String pk) {
        return String.format("Hello this is %s!", pk);
    }

}
