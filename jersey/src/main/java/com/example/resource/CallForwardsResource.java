package com.example.resource;

import static java.util.Arrays.asList;

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
	private long sourceId;
	
    @GET
    @Produces({"text/html",
    	       "application/vnd.myown.callforwards+json"})
    public MetaWrapper getCallForwards() {
    	CallForwards callForwards = new CallForwards("busy", new Target(sourceId+1, "fax", "Hoppe Fabian"));
    	return new MetaWrapper(callForwards, 
				Link.toCallForwards(sourceId), 
				asList(
						Link.toTarget("destination", callForwards.getDestination()),
						Link.toTarget("source", sourceId)
						));
    }
}
