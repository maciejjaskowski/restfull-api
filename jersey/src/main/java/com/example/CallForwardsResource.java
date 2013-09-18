package com.example;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.example.domain.CallForwards;
import com.example.domain.Target;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("target/callforwards")
public class CallForwardsResource {

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    @Produces({"text/html",
    	       "application/vnd.myown.callforwards+json"})
    public CallForwards getCallForwards() {
        return new CallForwards(2, "busy", new Target(4, "fax", "Hoppe Fabian"));
    }
}
