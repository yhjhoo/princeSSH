package com.prince.action;

import com.opensymphony.xwork2.ActionSupport;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.*;

@Namespace("/person")
@ResultPath(value="/WEB-INF")
public class Struts2Action extends ActionSupport {
	
	private static final long serialVersionUID = -6101709746190617404L;
	private Logger log = Logger.getLogger(this.getClass());
	
	@Action(value="index", results={
			@Result(name="success", location="index.jsp")
	})
	public String index(){
		log.info("Test action");
		return SUCCESS;
	}
	
	
	@Action(value="add", results={
			@Result(name="success", location="add.jsp")
	})
	public String add(){
		log.info("Test action");
		return SUCCESS;
	}
}
