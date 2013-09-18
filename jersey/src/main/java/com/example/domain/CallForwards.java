package com.example.domain;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.map.annotate.JsonRootName;

@XmlRootElement
@JsonRootName("target")
public class CallForwards {

	private long id;
	private String name;
	private Target target;
	
	CallForwards() {
		//JAX-B
	}
	
	public CallForwards(long id, String name, Target target) {
		this.id = id;
		this.name = name;
		this.target = target;
	}
	
	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Target getTarget() {
		return target;
	}

	public void setTarget(Target target) {
		this.target = target;
	}

	
}
