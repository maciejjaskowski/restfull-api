package com.example.resource;

import static org.junit.Assert.assertEquals;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.example.Main;

public class TargetResourceTest {

	private HttpServer server;
	private WebTarget target;

	@Before
	public void setUp() throws Exception {
		// start the server
		server = Main.startServer("8081");
		// create the client
		Client c = ClientBuilder.newClient();

		target = c.target(String.format(Main.BASE_URI, "8081"));
	}

	@After
	public void tearDown() throws Exception {
		server.stop();
	}

	@Test
	public void shouldRespondWithJson() {
		String responseMsg = target.path("target/13")
				.request("application/vnd.myown.target+json").get(String.class);
		assertEquals(responseMsg, 
				"{\"data\":{\"id\":13,\"type\":\"name\",\"name\":\"type\"},\"_links\":[{\"link\":{\"uri\":\"http://localhost:8080/api/target/13/callforwards\",\"rel\":\"callforwards\"}}],\"_self\":{\"link\":{\"uri\":\"http://localhost:8080/api/target/13\",\"rel\":\"self\"}}}");
	}

	@Test
	public void shouldRespondWithXmlV1() {
		String responseMsg = target.path("target/13")
				.request("application/vnd.myown.target+xml").get(String.class);
		assertEquals(
				responseMsg,
				"<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><metaWrapper><data xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"target\"><name>type</name><type>name</type></data><_links><link><rel>callforwards</rel><uri>http://localhost:8080/api/target/13/callforwards</uri></link></_links><_self><link><rel>self</rel><uri>http://localhost:8080/api/target/13</uri></link></_self></metaWrapper>");

	}

	@Test
	public void shouldRespondWithXmlV2() {
		String responseMsg = target.path("target/13")
				.request("application/vnd.myown.target.v2+xml").get(String.class);
		assertEquals(
				responseMsg,
				"<xml>new version</xml>");

	}
}
