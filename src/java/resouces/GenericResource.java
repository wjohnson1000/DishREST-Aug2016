/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resouces;

import data.Student;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.PathSegment;
import javax.ws.rs.core.Response;
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
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response get123(@PathParam("thePrimaryKey") String pk) {
        Response.ResponseBuilder rb = Response.ok();
        int index = -1;
        try {
            index = Integer.parseInt(pk);
        } catch (NumberFormatException nfe) {
        }
        if (index > 0) {
            Student theStudent = new Student();
            theStudent.name = "Fred at primary key " + pk;
            theStudent.grade = "A++";
            rb.entity(theStudent).header("x-simon", "Simple");
        } else {
            rb.entity("ERROR the value" + pk + " isn't a valid primary key")
                    .status(Response.Status.NOT_FOUND)
                    .type(MediaType.TEXT_PLAIN);
        }
        return rb.build();
    }

    @GET
    @Path("/extra/{id}-{idx: \\d+}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getXandY(@PathParam("id") String id, @PathParam("idx") int idx) {
        doStuff(id, idx);
        return String.format("getXandY, id = %s, idx = %d\n", id, idx);
    }

    private void doStuff(String id, int idx) {
        if (idx > 99) {
            throw new WebApplicationException(
                    Response
                        .status(Response.Status.BAD_REQUEST)
                        .entity("That's a mess!")
                        .type(MediaType.TEXT_PLAIN)
                        .build()
            );
        }
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
    
    @POST
    @Path("/students")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces(MediaType.TEXT_PLAIN)
    public Response doCreateStudent(Student s) {
        System.out.println("In doCreateStudent, entity is: " + s);
        // fake db entry: 
        int pk = ThreadLocalRandom.current().nextInt(1000, 100_000);
        return Response.ok("Created student " + s)
                .header("location", "/generic/students/" + pk)
                .status(Response.Status.CREATED)
                .build();
    }
}
