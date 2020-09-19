package com.enit.serving2.entity;


import org.springframework.stereotype.Component;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component

public class Ad implements Serializable {

	private String id;
	private String advertiserEmail;
	private List<String> category;
	private String title;
	private String description;
	private List<String> photosUrls = new ArrayList<String>();
	private int price;
	private int advertiserPhoneNumber;
	private String country;
	private String state;
	private String city;
	private String status;
	private Date postedOn;
	private String adImagesDirectory;
	private String condition;
	private String model;
	private String brand;
	private int views;
	private List<Float> rates = new ArrayList<Float>();
	private float rate;

	public void setRate(float rate) {
		this.rate = rate;
	}

	public float getRate() {
		return rate;
	}

	private Double[] location;


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}



//	public String getRecommandationId() {
//		return recommandationId;
//	}
//
//	public void setRecommandationId(String recommandationId) {
//		this.recommandationId = recommandationId;
//	}

	public String getAdvertiserEmail() {
		return advertiserEmail;
	}

	public void setAdvertiserEmail(String advertiserEmail) {
		this.advertiserEmail = advertiserEmail;
	}

	public List<String> getCategory() {
		return category;
	}

	public void setCategory(List<String> category) {
		this.category = category;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<String> getPhotosUrls() {
		return photosUrls;
	}

	public void setPhotosUrls(List<String> photosUrls) {
		this.photosUrls = photosUrls;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getAdvertiserPhoneNumber() {
		return advertiserPhoneNumber;
	}

	public void setAdvertiserPhoneNumber(int advertiserPhoneNumber) {
		this.advertiserPhoneNumber = advertiserPhoneNumber;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getPostedOn() {
		return postedOn;
	}

	public void setPostedOn(Date postedOn) {
		this.postedOn = postedOn;
	}

	public String getAdImagesDirectory() {
		return adImagesDirectory;
	}

	public void setAdImagesDirectory(String adImagesDirectory) {
		this.adImagesDirectory = adImagesDirectory;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public int getViews() {
		return views;
	}

	public void setViews(int views) {
		this.views = views;
	}

	public List<Float> getRates() {
		return rates;
	}

	public void setRates(List<Float> rates) {
		this.rates = rates;
	}


	public Double[] getLocation() {
		return location;
	}

	public void setLocation(Double[] location) {
		this.location = location;
	}



	public Ad() {
		super();
	}

	public Ad(String id,  String advertiserEmail, List<String> category,
			  String title, String description, List<String> photosUrls, int price, int advertiserPhoneNumber,
			  String country, String state, String city, String status, Date postedOn, String adImagesDirectory,
			  String condition, String model, String brand, int views, List<Float> rates, Double[] location) {
		super();
		this.id = id;

//		this.recommandationId = recommandationId;
		this.advertiserEmail = advertiserEmail;
		this.category = category;
		this.title = title;
		this.description = description;
		this.photosUrls = photosUrls;
		this.price = price;
		this.advertiserPhoneNumber = advertiserPhoneNumber;
		this.country = country;
		this.state = state;
		this.city = city;
		this.status = status;
		this.postedOn = postedOn;
		this.adImagesDirectory = adImagesDirectory;
		this.condition = condition;
		this.model = model;
		this.brand = brand;
		this.views = views;
		this.rates = rates;

		this.location = location;

	}

	@Override
	public String toString() {
		return "{\"id\": \"" + id + "\", \"advertiserEmail\":\"" + advertiserEmail + "\", \"category\":\"" + category
				+ "\", \"title\":\"" + title + "\", \"description\":\"" + description + "\", \"photosUrls\":\""
				+ photosUrls + "\", \"price\":\"" + price + "\", \"advertiserPhoneNumber\":\"" + advertiserPhoneNumber
				+ "\", \"country\":\"" + country + "\", \"state\":\"" + state + "\", \"city\":\"" + city
				+ "\", \"status\":\"" + status + "\", \"postedOn\":\"" + postedOn + "\", \"adImagesDirectory\":\""
				+ adImagesDirectory + "\", \"condition\":\"" + condition + "\", \"model\":\"" + model
				+ "\", \"brand\":\"" + brand + "\", \"views\":\"" + views + "\", \"rates\":\"" + rates
				+  "\", \"location\":\"" + location
				+  "}";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ad other = (Ad) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
