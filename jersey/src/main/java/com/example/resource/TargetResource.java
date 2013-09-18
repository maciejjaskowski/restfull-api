package com.example.resource;

import static java.util.Arrays.asList;

import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.example.domain.Target;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("target")
public class TargetResource {

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET @Path("{id}")
    @Produces({"application/vnd.myown.target+xml;qs=0.9",
    	       "application/vnd.myown.target.v2+xml;qs=0.5",
    	       "application/vnd.myown.target+html;qs=0.3",
    	       "text/html;qs=0.1",
    	       "application/vnd.myown.target+json;qs=1"})
    
    public MetaWrapper get(@PathParam(value="id") long id) {
    	if (id > 15) {
    		throw new NotFoundException();
    	}
    	Target target = new Target(id, "name", "type");
    	return new MetaWrapper(target, 
				Link.toTarget("self", target), 
				asList(
						Link.toCallForwards(target))
					);
    }
}
