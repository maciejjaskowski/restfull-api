package com.example;

import static java.util.Arrays.asList;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;

import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig.Feature;

import com.example.domain.CallForwards;
import com.example.domain.Link;
import com.example.domain.MetaWrapper;
import com.example.domain.Target;

@Provider
public class JSONMessageBodyWriter implements MessageBodyWriter<Object> {

	@Override
	public long getSize(Object arg0, Class<?> arg1, Type arg2,
			Annotation[] arg3, MediaType arg4) {
		return -1;
	}

	@Override
	public boolean isWriteable(Class<?> clazz, Type type,
			Annotation[] annotations, MediaType mediaType) {
		if ("com.example.domain".equals(clazz.getPackage().getName())
				&& mediaType.getType().equals("application")
				&& mediaType.getSubtype()
						.matches("vnd\\.myown\\.[a-z]+\\+json")) {
			return true;
		}
		return false;
	}

	@Override
	public void writeTo(Object object, Class<?> clazz, Type type,
			Annotation[] annotation, MediaType mediaType,
			MultivaluedMap<String, Object> map, OutputStream out)
			throws IOException, WebApplicationException {

		if (object instanceof Target) {
			Target obj = (Target) object;
			MetaWrapper metaWrapper = new MetaWrapper(object, new Link("self",
					"http://localhost:8080/api/target/" + obj.getId()), asList(new Link("callforwards",
							"http://localhost:8080/api/target/" + obj.getId() +"/callforwards")));
			ObjectMapper mapper = new ObjectMapper();
			new JacksonJsonProvider(mapper).writeTo(metaWrapper, clazz, type, annotation, mediaType, map, out);
		}
		
		if (object instanceof CallForwards) {
			CallForwards obj = (CallForwards) object;
			long targetId = obj.getTarget().getId();
			MetaWrapper metaWrapper = new MetaWrapper(object, new Link("self",
					"http://localhost:8080/api/target/"+ targetId +"/callforwards"), asList(new Link("source",
							"http://localhost:8080/api/target/" + targetId)));
			ObjectMapper mapper = new ObjectMapper();
			new JacksonJsonProvider(mapper).writeTo(metaWrapper, clazz, type, annotation, mediaType, map, out);
		}
	}
}