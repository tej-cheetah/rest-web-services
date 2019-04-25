package com.example.resfulwebservices.helloworld;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWordController {
	
	@Autowired private MessageSource messageSource;

	@RequestMapping(method = RequestMethod.GET, path = "hello-world")
	public String helloWorld() {
		return "Hello world";
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "hello-world-bean")
	public HelloWorldBean helloWorldBean() {
		return new HelloWorldBean("Hello World");
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "hello-world-internationalized")
	public String helloWorldInternationalized() {
		return messageSource.getMessage("googd.morning.message", null, LocaleContextHolder.getLocale());
	}
}
