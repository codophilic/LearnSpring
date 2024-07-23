package com.simple.AnnotationBasedConfiguration;

import java.util.*;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Student {
	
	
	//Primitive Data types
	@Value("123")
	private String ID;
	
	@Value("Harsh")
	private String Name;
	
	@Value("Mumbai")
	private String Address;
	
	
	
	// List , Sets , Maps and Properties
	
	@Value("#{myList}")
	private List<Integer> list;
	
	@Value("#{myMap}")
	private Map<String,Integer> map;
	
	@Value("#{myProps}")
	private Properties MyProps;
	
	
	public Student() { // This is a default constructor , which is require for setters injection
		super();
		// TODO Auto-generated constructor stub
	}
	public Student(String iD, String name, String address) {
		super();
		ID = iD;
		Name = name;
		Address = address;
	}

	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
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
		return "Student [ID=" + ID + ", Name=" + Name + ", Address=" + Address + ", list=" + list
				+ ", map=" + map + ", MyProps=" + MyProps + "]";
	}
}