package com.enit.randomrecommandationservice.events;



import com.enit.randomrecommandationservice.entity.Ad;
import com.enit.randomrecommandationservice.entity.Status;

import java.util.*;

public class SaveAdEvent extends Event {

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
	private Status status;
	private Date postedOn;
	private String adImagesDirectory;
	private String condition;
	private String model;
	private String brand;
	private int views;
	private List<Float> rates = new ArrayList<Float>();
	private float rate;
	private Map<String,Double> location;


	public SaveAdEvent(Ad ad) {
        super(EventName.CREATE_AD);
        this.id = ad.getId();
        this.advertiserEmail = ad.getAdvertiserEmail();
        this.category = ad.getCategory();
        this.title = ad.getTitle();
        this.description = ad.getDescription();
        this.photosUrls = ad.getPhotosUrls();
        this.price = ad.getPrice();
        this.advertiserPhoneNumber = ad.getAdvertiserPhoneNumber();
        this.country = ad.getCountry();
        this.state = ad.getState();
        this.city = ad.getCity();
        this.status = ad.getStatus();
        this.postedOn = ad.getPostedOn();
        this.adImagesDirectory = ad.getAdImagesDirectory();
        this.condition = ad.getCondition();
        this.model = ad.getModel();
        this.brand = ad.getBrand();
        this.views = ad.getViews();
        this.rates = ad.getRates();
        this.rate = ad.getRate();
//        this.location = ad.getLocation();
    }

	public SaveAdEvent() {

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}





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

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
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


	public float getRate() {
		return rate;
	}

	public void setRate(float rate) {
		this.rate = rate;
	}

	public Map<String, Double> getLocation() {
		return location;
	}

	public void setLocation(Map<String,Double> location) {
		this.location = location;
	}



}
