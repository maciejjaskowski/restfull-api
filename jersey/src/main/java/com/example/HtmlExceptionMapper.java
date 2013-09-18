package com.example;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

//@Provider
public class HtmlExceptionMapper implements ExceptionMapper<Throwable>{

//	@Context
//	HttpR
	
	@Override
	public Response toResponse(Throwable exception) {
		return Response.ok().entity(exception).build();
	}

}
