package com.redhat.drupal.entities;

import com.google.gson.Gson;

public class TaxonomyVocabulary {
	private Integer vid;
	private String name;
	private String machine_name;
	private String description;
	
	public static TaxonomyVocabulary fromJson(String json) {
		Gson gson = new Gson();
		return gson.fromJson(json, TaxonomyVocabulary.class);
	}
	
	public String toJson() {
		Gson gson = new Gson();
		return gson.toJson(this);
	}
	
	public Integer getVid() {
		return vid;
	}
	public void setVid(Integer vid) {
		this.vid = vid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMachine_name() {
		return machine_name;
	}
	public void setMachine_name(String machine_name) {
		this.machine_name = machine_name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
