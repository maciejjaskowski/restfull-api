package com.example;

import static java.util.Arrays.asList;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Arrays;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.apache.commons.io.IOUtils;

import com.example.domain.CallForwards;
import com.example.domain.Link;
import com.example.domain.MetaWrapper;
import com.example.domain.Target;

@Provider
public class HtmlMessageBodyWriter implements MessageBodyWriter<Object> {

	@Override
	public long getSize(Object arg0, Class<?> arg1, Type arg2,
			Annotation[] arg3, MediaType arg4) {
		return -1;
	}

	@Override
	public boolean isWriteable(Class<?> clazz, Type type,
			Annotation[] annotations, MediaType mediaType) {
		if ("com.example.domain".equals(clazz.getPackage().getName())
				&& mediaType.getType().equals("text")
				&& mediaType.getSubtype().matches("html")) {
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
			InputStream inputStream = HtmlMessageBodyWriter.class.getClassLoader().getResourceAsStream("html/test.html");
			IOUtils.copy(inputStream, out);
		}
		if (object instanceof CallForwards) {
			InputStream inputStream = HtmlMessageBodyWriter.class.getClassLoader().getResourceAsStream("html/callforwards.html");
			IOUtils.copy(inputStream, out);
		}
	}
}