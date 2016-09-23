package com.prince.action;

import com.opensymphony.xwork2.ActionSupport;

import java.util.stream.Stream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.convention.annotation.*;

@Namespace("/person")
@ResultPath(value="/WEB-INF")
public class Struts2Action extends ActionSupport {
	
	private static final long serialVersionUID = -6101709746190617404L;
	private final Log log = LogFactory.getLog(this.getClass());
	
	@Action(value="index", results={
			@Result(name="success", location="index.jsp")
	})
	public String index(){
		//test for java8 support
		Stream.of("Hello", "Hello1, Hello2, Hello3").forEach(System.out::println);
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
