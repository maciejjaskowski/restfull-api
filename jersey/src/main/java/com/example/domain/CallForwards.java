package com.example.domain;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.map.annotate.JsonRootName;

@XmlRootElement
@JsonRootName("target")
public class CallForwards {

	private String name;
	private Target target;
	
	CallForwards() {
		//JAX-B
	}
	
	public CallForwards(String name, Target target) {
		this.name = name;
		this.target = target;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Target getDestination() {
		return target;
	}

	public void setTarget(Target target) {
		this.target = target;
	}

	
}
