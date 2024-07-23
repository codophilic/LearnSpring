package com.simple.XMLBasedConfiguration;

import java.util.*;

public class Employee {
	
	public Employee() { // This is a default constructor , which is require for setters injection
		super();
		// TODO Auto-generated constructor stub
	}

	
	// List , Sets , Maps and Properties
	
	List<Integer> list;
		
	Map<String,Integer> map;
	
	Properties MyProps;


	public Properties getMyProps() {
		return MyProps;
	}
	public void setMyProps(Properties myProps) {
		MyProps = myProps;
	}
	public List<Integer> getList() {
		return list;
	}
	public void setList(List<Integer> list) {
		this.list = list;
	}

	public Map<String, Integer> getMap() {
		return map;
	}
	public void setMap(Map<String, Integer> map) {
		this.map = map;
	}
	@Override
	public String toString() {
		return "Employee [list=" + list + ", map=" + map + ", MyProps=" + MyProps + "]";
	}

	
}