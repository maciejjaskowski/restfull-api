package com.example.domain;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Link {

	private String uri;
	private String rel;
	
	Link() {
		//JAX-B
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getRel() {
		return rel;
	}

	public void setRel(String rel) {
		this.rel = rel;
	}

	public Link(String rel, String uri) {
		this.uri = uri;
		this.rel = rel;
	}

}
