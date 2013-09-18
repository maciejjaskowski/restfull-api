package com.example.bodywriters;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import com.example.domain.Target;
import com.example.resource.Link;
import com.example.resource.MetaWrapper;

@Provider
public class XMLMessageBodyWriter implements MessageBodyWriter<Object> {

	@Override
	public long getSize(Object arg0, Class<?> arg1, Type arg2,
			Annotation[] arg3, MediaType arg4) {
		return -1;
	}

	@Override
	public boolean isWriteable(Class<?> clazz, Type type,
			Annotation[] annotations, MediaType mediaType) {
		if (MetaWrapper.class.equals(clazz)
				&& mediaType.getType().equals("application")
				&& mediaType.getSubtype().matches("vnd\\.myown\\.target\\+xml")) {
			return true;
		}
		return false;
	}

	@Override
	public void writeTo(Object object, Class<?> clazz, Type type,
			Annotation[] annotation, MediaType mediaType,
			MultivaluedMap<String, Object> map, OutputStream out)
			throws IOException, WebApplicationException {
		try {
			
			JAXBContext jaxbContext = JAXBContext.newInstance(Target.class, MetaWrapper.class, Link.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			if (object instanceof MetaWrapper) {				
				jaxbMarshaller.marshal(object, out);
			}
		} catch (JAXBException e) {
			throw new RuntimeException(e);
		}
	}
}