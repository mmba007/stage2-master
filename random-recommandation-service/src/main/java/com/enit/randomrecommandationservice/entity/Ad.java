





package com.enit.randomrecommandationservice.entity;
//


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.data.annotation.Id;

import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "advertiser")
public class Ad {
	@Id
	private String id;
	private String advertiserEmail;
	private String category;
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
	@GeoSpatialIndexed
	private Double[] location;


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

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
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

	public Ad(String id,String category, String title, String description, int price, int advertiserPhoneNumber, String country, String state, String city, Status status, String adImagesDirectory, String condition, String model, String brand, int views, float rate, Double[] location) {
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

	public Double[] getLocation() {
		return location;
	}

	public void setLocation(Double[] location) {
		this.location = location;
	}



	public Ad() {

	}

	public Ad(String advertiserEmail, List<String> photosUrls, String category, int price, String title, String country,
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




































//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//import org.springframework.data.elasticsearch.core.geo.GeoPoint;
//import javax.persistence.ElementCollection;
//import javax.persistence.Entity;
//import javax.persistence.FetchType;
//import javax.persistence.Id;
//import javax.persistence.Table;
//
//import org.hibernate.annotations.Fetch;
//import org.hibernate.annotations.FetchMode;
//import org.springframework.stereotype.Component;
//
//import com.fasterxml.jackson.annotation.JsonFormat;
//
//
//@Table(name = "Advertisement")
//@Component
//@Entity
//public class Ad {
//
//	@Id
//	private String id;
//	private String requestId;
//	private String recommandationId;
//	private String advertiserEmail;
//	private String category;
//	private String title;
//	private String description;
////	@ElementCollection
////	@JsonSerialize(using = ListOfStringsSerializer.class)
//	@ElementCollection(fetch = FetchType.EAGER)
//	@Fetch(value = FetchMode.SUBSELECT)
////	@JoinTable(name = "ad_photosUrls", joinColumns = @JoinColumn(name = "ad_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
//	private List<String> photosUrls = new ArrayList<String>();
//	private int price;
//	private int advertiserPhoneNumber;
//	private String country;
//	private String state;
//	private String city;
//	private String status;
//	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
////	@JsonDeserialize(using = DateHandler.class)
//	private Date postedOn;
//	private String adImagesDirectory;
//	private String condition;
//	private String model;
//	private String brand;
//	private int views;
//
////	@JsonSerialize(using = ListOfStringsSerializer.class)
//	@ElementCollection(fetch = FetchType.EAGER)
//	@Fetch(value = FetchMode.SUBSELECT)
//	private List<Float> rates = new ArrayList<Float>();
//	private float rate;
//	private GeoP location;
//
//	public String getId() {
//		return id;
//	}
//
//	public void setId(String id) {
//		this.id = id;
//	}
//
//	public String getRequestId() {
//		return requestId;
//	}
//
//	public void setRequestId(String requestId) {
//		this.requestId = requestId;
//	}
//
//	public String getRecommandationId() {
//		return recommandationId;
//	}
//
//	public void setRecommandationId(String recommandationId) {
//		this.recommandationId = recommandationId;
//	}
//
//	public String getAdvertiserEmail() {
//		return advertiserEmail;
//	}
//
//	public void setAdvertiserEmail(String advertiserEmail) {
//		this.advertiserEmail = advertiserEmail;
//	}
//
//	public String getCategory() {
//		return category;
//	}
//
//	public void setCategory(String category) {
//		this.category = category;
//	}
//
//	public String getTitle() {
//		return title;
//	}
//
//	public void setTitle(String title) {
//		this.title = title;
//	}
//
//	public String getDescription() {
//		return description;
//	}
//
//	public void setDescription(String description) {
//		this.description = description;
//	}
//
//	public List<String> getPhotosUrls() {
//		return photosUrls;
//	}
//
//	public void setPhotosUrls(List<String> photosUrls) {
//		this.photosUrls = photosUrls;
//	}
//
//	public int getPrice() {
//		return price;
//	}
//
//	public void setPrice(int price) {
//		this.price = price;
//	}
//
//	public int getAdvertiserPhoneNumber() {
//		return advertiserPhoneNumber;
//	}
//
//	public void setAdvertiserPhoneNumber(int advertiserPhoneNumber) {
//		this.advertiserPhoneNumber = advertiserPhoneNumber;
//	}
//
//	public String getCountry() {
//		return country;
//	}
//
//	public void setCountry(String country) {
//		this.country = country;
//	}
//
//	public String getState() {
//		return state;
//	}
//
//	public void setState(String state) {
//		this.state = state;
//	}
//
//	public String getCity() {
//		return city;
//	}
//
//	public void setCity(String city) {
//		this.city = city;
//	}
//
//	public String getStatus() {
//		return status;
//	}
//
//	public void setStatus(String status) {
//		this.status = status;
//	}
//
//	public Date getPostedOn() {
//		return postedOn;
//	}
//
//	public void setPostedOn(Date postedOn) {
//		this.postedOn = postedOn;
//	}
//
//	public String getAdImagesDirectory() {
//		return adImagesDirectory;
//	}
//
//	public void setAdImagesDirectory(String adImagesDirectory) {
//		this.adImagesDirectory = adImagesDirectory;
//	}
//
//	public String getCondition() {
//		return condition;
//	}
//
//	public void setCondition(String condition) {
//		this.condition = condition;
//	}
//
//	public String getModel() {
//		return model;
//	}
//
//	public void setModel(String model) {
//		this.model = model;
//	}
//
//	public String getBrand() {
//		return brand;
//	}
//
//	public void setBrand(String brand) {
//		this.brand = brand;
//	}
//
//	public int getViews() {
//		return views;
//	}
//
//	public void setViews(int views) {
//		this.views = views;
//	}
//
//	public List<Float> getRates() {
//		return rates;
//	}
//
//	public void setRates(List<Float> rates) {
//		this.rates = rates;
//	}
//
//	public float getRate() {
//		return rate;
//	}
//
//	public void setRate(float rate) {
//		this.rate = rate;
//	}
//
//	public String getLocation() {
//		return location;
//	}
//
//	public void setLocation(String location) {
//		this.location = location;
//	}
//
//	public Ad() {
//		super();
//	}
//
//	public Ad(String id, String requestId, String recommandationId, String advertiserEmail, String category,
//			  String title, String description, List<String> photosUrls, int price, int advertiserPhoneNumber,
//			  String country, String state, String city, String status, Date postedOn, String adImagesDirectory,
//			  String condition, String model, String brand, int views, List<Float> rates, float rate, String location) {
//		super();
//		this.id = id;
//		this.requestId = requestId;
//		this.recommandationId = recommandationId;
//		this.advertiserEmail = advertiserEmail;
//		this.category = category;
//		this.title = title;
//		this.description = description;
//		this.photosUrls = photosUrls;
//		this.price = price;
//		this.advertiserPhoneNumber = advertiserPhoneNumber;
//		this.country = country;
//		this.state = state;
//		this.city = city;
//		this.status = status;
//		this.postedOn = postedOn;
//		this.adImagesDirectory = adImagesDirectory;
//		this.condition = condition;
//		this.model = model;
//		this.brand = brand;
//		this.views = views;
//		this.rates = rates;
//		this.rate = rate;
//		this.location = location;
//	}
//
//	@Override
//	public String toString() {
//		return "{\"id\": \"" + id + "\", \"requestId\":\"" + requestId + "\", \"recommandationId\":\""
//				+ recommandationId + "\", \"advertiserEmail\":\"" + advertiserEmail + "\", \"category\":\"" + category
//				+ "\", \"title\":\"" + title + "\", \"description\":\"" + description + "\", \"photosUrls\":\""
//				+ photosUrls + "\", \"price\":\"" + price + "\", \"advertiserPhoneNumber\":\"" + advertiserPhoneNumber
//				+ "\", \"country\":\"" + country + "\", \"state\":\"" + state + "\", \"city\":\"" + city
//				+ "\", \"status\":\"" + status + "\", \"postedOn\":\"" + postedOn + "\", \"adImagesDirectory\":\""
//				+ adImagesDirectory + "\", \"condition\":\"" + condition + "\", \"model\":\"" + model
//				+ "\", \"brand\":\"" + brand + "\", \"views\":\"" + views + "\", \"rates\":\"" + rates
//				+ "\", \"rate\":\"" + rate + "\", \"location\":\"" + location + "\", \"longitude\":" + longitude
//				+ ", \"latitude\":" + latitude + "}";
//	}
//
//}
