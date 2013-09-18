package com.example.resource;

import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class LinkWrapper {

	private Link link;
	
	LinkWrapper() {
		//JAX-B
	}

	public LinkWrapper(Link link) {
		this.setLink(link);
	}

	public Link getLink() {
		return link;
	}

	public void setLink(Link link) {
		this.link = link;
	}

}
