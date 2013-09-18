package com.example;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.example.domain.CallForwards;
import com.example.domain.Target;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("target/{id}/callforwards")
public class CallForwardsResource {

	@PathParam("id") 
	private long id;
	
    @GET
    @Produces({"text/html",
    	       "application/vnd.myown.callforwards+json"})
    public CallForwards getCallForwards() {
        return new CallForwards(2, "busy", new Target(id, "fax", "Hoppe Fabian"));
    }
}
