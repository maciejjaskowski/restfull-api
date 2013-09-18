package com.example.resource;

import static org.junit.Assert.assertEquals;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

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
		String responseMsg = target.path("target")
				.request(MediaType.APPLICATION_JSON).get(String.class);
		assertEquals(responseMsg, "{\n" + "  \"id\" : 1,\n"
				+ "  \"type\" : \"name\",\n" + "  \"name\" : \"type\"\n" + "}");
	}

	@Test
	public void shouldRespondWithXmlV1() {
		String responseMsg = target.path("target")
				.request("application/vnd.myown.target+xml").get(String.class);
		assertEquals(
				responseMsg,
				"<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><metaWrapper><data xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"target\"><name>type</name><type>name</type></data><_links><rel>callforwards</rel><uri>api/target/callforwards</uri></_links><_self><rel>self</rel><uri>api/target</uri></_self></metaWrapper>");

	}

	@Test
	public void shouldRespondWithXmlV2() {
		String responseMsg = target.path("target")
				.request("application/vnd.myown.target.v2+xml").get(String.class);
		assertEquals(
				responseMsg,
				"<xml>new version</xml>");

	}
}
