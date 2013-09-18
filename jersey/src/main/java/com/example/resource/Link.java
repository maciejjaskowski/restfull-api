package com.example.resource;

import javax.xml.bind.annotation.XmlRootElement;

import com.example.domain.Target;

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
	
	public static Link toTarget(String name, Target target) {
		return toTarget(name, target.getId());
	}
	
	public static Link toTarget(String name, long id) {
		return new Link(name, "http://localhost:8080/api/target/" + id);
	}

	public static Link toCallForwards(Target obj) {
		return toCallForwards(obj.getId());
	}
	
	public static Link toCallForwards(long id) {
		return new Link("callforwards",
				"http://localhost:8080/api/target/" + id +"/callforwards");
	}



}
