package com.example.bodywriters;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;

import com.example.resource.MetaWrapper;


@Provider
public class XMLMessageBodyWriterV2 implements MessageBodyWriter<Object> {

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
				&& mediaType.getSubtype().matches(
						"vnd\\.myown\\.target\\.v2\\+xml")) {
			return true;
		}
		return false;
	}

	@Override
	public void writeTo(Object object, Class<?> clazz, Type type,
			Annotation[] annotation, MediaType mediaType,
			MultivaluedMap<String, Object> map, OutputStream out)
			throws IOException, WebApplicationException {

		if (object instanceof MetaWrapper) {
			new OutputStreamWriter(out).append("<xml>new version</xml>").close();
		}
	}
}