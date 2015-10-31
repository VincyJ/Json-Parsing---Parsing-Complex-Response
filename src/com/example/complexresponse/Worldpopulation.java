package com.example.complexresponse;

public class Worldpopulation {

	private String rank;
	private String country;
	private String population;
	private String image;
	
	public Worldpopulation() {
		// TODO Auto-generated constructor stub
	}
	public Worldpopulation(String rank, String country, String population,
			String image) {
		super();
		this.rank = rank;
		this.country = country;
		this.population = population;
		this.image = image;
	}
	
	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPopulation() {
		return population;
	}

	public void setPopulation(String population) {
		this.population = population;
	}
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}


}
