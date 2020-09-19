package com.enit.adscrud.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.GeoPointField;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;
import org.springframework.stereotype.Component;

@Component
@Document(indexName = "advertiser")
public class Ad {
	@Id
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
	@GeoPointField
	private GeoPoint location;

//	private Set<String> availablePreferences= new HashSet<String>(Arrays.asList("Electronics","Real Estate","Vehicles","House and Garden","Leisure & Entertainment","Shoppings","Pets","Services","Jobs","Enterprise","Others"));

//	private String fullDescription;

//	public Set<String> getAvailablePreferences() {
//		return availablePreferences;
//	}
//
//	public void setAvailablePreferences(Set<String> availablePreferences) {
//		this.availablePreferences = availablePreferences;
//	}

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

	public Ad(String id,List<String> category ,String title, String description, int price, int advertiserPhoneNumber, String country, String state, String city, Status status, String adImagesDirectory, String condition, String model, String brand, int views, float rate, GeoPoint location) {
		this.category = category;
		this.title = title;
		this.description = description;
		this.id=id;
		this.price = price;
		this.advertiserPhoneNumber = advertiserPhoneNumber;
		this.country = country;
		this.state = state;
		this.city = city;
		this.status = status;

		this.adImagesDirectory = adImagesDirectory;
		this.condition = condition;
		this.model = model;
		this.brand = brand;
		this.views = views;

		this.rate = rate;
		this.location = location;
	}

	public float getRate() {
		return rate;
	}

	public void setRate(float rate) {
		this.rate = rate;
	}

	public GeoPoint getLocation() {
		return location;
	}

	public void setLocation(GeoPoint location) {
		this.location = location;
	}



	public Ad() {

	}

	public Ad(String advertiserEmail, List<String> photosUrls, List<String> category, int price, String title, String country,
			  String state, String city) {
		super();
		this.advertiserEmail = advertiserEmail;
		this.photosUrls = photosUrls;
		this.category = category;
		this.title = title;
		this.country = country;
		this.state = state;
		this.price = price;
		this.city = city;
		this.status = Status.ACTIVE;
//		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
//		Date date = new Date();
//		System.out.println(dateFormat.format(date)); //2016/11/16 12:08:43
		this.postedOn = new Date();
	}

//	@Override
//	public String toString() {
//		return "Ad [id=" + id + ", requestId=" + requestId + ", recommandationId=" + recommandationId
//				+ ", advertiserEmail=" + advertiserEmail + ", category=" + category + ", title=" + title
//				+ ", description=" + description + ", photosUrls=" + photosUrls + ", price=" + price
//				+ ", advertiserPhoneNumber=" + advertiserPhoneNumber + ", country=" + country + ", state=" + state
//				+ ", city=" + city + ", status=" + status + ", postedOn=" + postedOn + ", adImagesDirectory="
//				+ adImagesDirectory + ", condition=" + condition + ", model=" + model + ", brand=" + brand + ", views="
//				+ views + ", rate=" + rate + ", location=" + location + ", longitude=" + longitude + ", latitude="
//				+ latitude + "]";
//	}

	@Override
	public String toString() {
		return "{\"id\": \"" + id +  "\", \"advertiserEmail\":\"" + advertiserEmail + "\", \"category\":\"" + category
				+ "\", \"title\":\"" + title + "\", \"description\":\"" + description + "\", \"photosUrls\":\""
				+ photosUrls + "\", \"price\":\"" + price + "\", \"advertiserPhoneNumber\":\"" + advertiserPhoneNumber
				+ "\", \"country\":\"" + country + "\", \"state\":\"" + state + "\", \"city\":\"" + city
				+ "\", \"status\":\"" + status + "\", \"postedOn\":\"" + postedOn + "\", \"adImagesDirectory\":\""
				+ adImagesDirectory + "\", \"condition\":\"" + condition + "\", \"model\":\"" + model
				+ "\", \"brand\":\"" + brand + "\", \"views\":\"" + views + "\", \"rate\":\"" + rate
				+ "\", \"location\":\"" + location + "\", \"longitude\":"
				+ "}";
	}

}
