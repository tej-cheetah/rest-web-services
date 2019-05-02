package com.example.resfulwebservices.filering;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class FilteringController {
	
	private MappingJacksonValue filtering (SimpleBeanPropertyFilter filter, List<SomeBean> list, SomeBean obj) {
		FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);
		MappingJacksonValue mapping = new MappingJacksonValue(list == null ? obj : list);
		mapping.setFilters(filters);
		return mapping;
	}
	
//	private MappingJacksonValue filtering(SimpleBeanPropertyFilter filter, SomeBean obj) {
//		FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);
//		MappingJacksonValue mapping = new MappingJacksonValue(obj);
//		mapping.setFilters(filters);
//		return mapping;
//	}

	@GetMapping(path= "/filtering")
	public MappingJacksonValue retrieveSomeBean() {
		SomeBean someBean = new SomeBean("value1", "value2", "value3");
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1");
		return filtering(filter, null, someBean);
	}
	
	@GetMapping(path= "/filtering-list")
	public MappingJacksonValue retrieveListOfSomeBean() {
		List<SomeBean> list = Arrays.asList(
				new SomeBean("value1", "value2", "value3"),
				new SomeBean("value12", "value22", "value32"),
				new SomeBean("value13", "value23", "value33")
				);
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field2", "field3");
		return filtering(filter, list, null);
				
	}
}
