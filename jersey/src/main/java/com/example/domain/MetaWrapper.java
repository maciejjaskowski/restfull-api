package com.example.domain;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.map.annotate.JsonRootName;

@XmlRootElement
@JsonRootName("d")
public class MetaWrapper {

	private Object data;
	private List<LinkWrapper> _links;
	private LinkWrapper _self;
	
	MetaWrapper() {
		//JAX-B
	}

	public MetaWrapper(Object object, Link _self, List<Link> links) {
		this.data = object;
		this._self = new LinkWrapper(_self);
		this._links = new ArrayList<LinkWrapper>();
		for (Link link : links) {
			this._links.add(new LinkWrapper(link));
		}
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public List<LinkWrapper> get_links() {
		return _links;
	}

	public void set_links(List<LinkWrapper> _links) {
		this._links = _links;
	}

	public LinkWrapper get_self() {
		return _self;
	}

	public void set_self(LinkWrapper _self) {
		this._self = _self;
	}

}
