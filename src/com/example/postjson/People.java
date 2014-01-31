package com.example.postjson;

public class People {
	private String name;
	private Integer age;
	private boolean developer;
	
	People(String nama, Integer age, boolean developer){
		this.name = nama;
		this.age = age;
		this.developer = developer;
	}
	
	public String getNama() {
		return name;
	}

	public void setNama(String nama) {
		this.name = nama;
	}

	public Integer getUmur() {
		return age;
	}

	public void setUmur(Integer umur) {
		this.age = umur;
	}

	public boolean isDeveloper() {
		return developer;
	}

	public void setDeveloper(boolean developer) {
		this.developer = developer;
	}
	
}
