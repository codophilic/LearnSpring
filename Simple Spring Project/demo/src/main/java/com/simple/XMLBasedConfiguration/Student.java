package com.simple.XMLBasedConfiguration;

import java.util.*;

public class Student {
	
	
	//Primitive Data types
	private String ID;
	private String Name;
	private String Address;
	
	
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
	
	
	// List , Sets , Maps and Properties
	
	List<Integer> list=new ArrayList<Integer>();
	
	Set<Integer> set = new HashSet<Integer>();
	
	Map<String,Integer> map = new HashMap<String,Integer>();
	
	Properties MyProps = new Properties();


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
	public Set<Integer> getSet() {
		return set;
	}
	public void setSet(Set<Integer> set) {
		this.set = set;
	}
	public Map<String, Integer> getMap() {
		return map;
	}
	public void setMap(Map<String, Integer> map) {
		this.map = map;
	}

	
	@Override
	public String toString() {
		return "Student [ID=" + ID + ", Name=" + Name + ", Address=" + Address + ", list=" + list + ", set=" + set
				+ ", map=" + map + ", MyProps=" + MyProps + "]";
	}
}