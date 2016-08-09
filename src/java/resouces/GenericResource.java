/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resouces;

import data.Student;
import java.util.List;
import javax.ws.rs.Produces;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.PathSegment;
import javax.ws.rs.core.UriInfo;

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
    @Produces(MediaType.APPLICATION_XML)
    public Student get123(@PathParam("thePrimaryKey") String pk) {
        Student rv = new Student();
        rv.name = "Fred at primary key " + pk;
        rv.grade = "A++";
        return rv;
    }

    @GET
    @Path("/extra/{id}-{idx: \\d+}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getXandY(@PathParam("id")String id, @PathParam("idx") int idx) {
        return String.format("getXandY, id = %s, idx = %d\n", id, idx);
    }
    
    @GET
    @Path("/recursive/{id: [a-zA-Z0-9/]+}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getRecurse(@PathParam("id") String id, @Context UriInfo uriInfo) {
        List<PathSegment> lps = uriInfo.getPathSegments();
        for (PathSegment ps : lps) {
            System.out.println("> " + ps);
        }
        return "recursive path is " + id;
    }
}
