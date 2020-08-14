//package com.enit.authentication.com.enit.randomrecommandationservice.controller;
//
//import java.io.File;
//import java.io.IOException;
//import java.util.List;
//import java.util.stream.Collectors;
//
//import com.enit.authentication.com.enit.randomrecommandationservice.events.DeleteUserEvent;
//import com.enit.authentication.com.enit.randomrecommandationservice.events.Event;
//import com.enit.authentication.com.enit.randomrecommandationservice.events.RegisterUserEvent;
//import com.enit.authentication.com.enit.randomrecommandationservice.events.UpdateUserEvent;
//import com.enit.authentication.model.Role;
//import com.enit.authentication.model.User;
//import com.enit.authentication.com.enit.randomrecommandationservice.repository.RoleRepository;
//import com.enit.authentication.com.enit.randomrecommandationservice.repository.UserRepository;
//import org.apache.tomcat.util.http.fileupload.FileUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.client.RestTemplate;
//
//
//
//@CrossOrigin(origins = "*", maxAge = 3600)
//@RestController
//public class TestRestAPIs {
//
//	private static final String URL_PROFILE_PHOTO_DIRECTORY = "http://192.168.99.100:8080/sendProfilePhotoPath";
//
//	@Autowired
//	UserRepository userRepository;
//
//	@Autowired
//	RoleRepository roleRepository;
//
//	@Autowired
//	PasswordEncoder encoder;
//	@Autowired
//	KafkaTemplate<String, Event> kafkaTemplate;
//
////	@GetMapping("/api/users")
////	public Iterable<User> getAllUsers(){
////	Iterable<User> users=userServiceImpl.findAllUsers();
////	for(User usr:users) {
////		System.out.println(usr);
////	}return users;
////	}
////
////	@GetMapping("/api/user/{username}")
////	public Optional<User> getUser(@PathVariable String username){
////	Optional<User> user=userServiceImpl.findByUserName(username);
////
////		System.out.println(user.get());
////	return user;
////	}
//
//	@PostMapping(value = "/user/save")
//	public String saveUser(@RequestBody User usr) {
//		userRepository.save(usr);
//		return "User saved successfully";
//	}
//
//	@PostMapping(value = "/role/save")
//	public String saveRole(@RequestBody Role role) {
//		roleRepository.save(role);
//		return "Role saved successfully";
//	}
//
//	@PutMapping("/api/user/{username}/update/password")
//	@PreAuthorize("hasRole('USER')")
//	public String updateUserPassword(@PathVariable String username, @RequestBody String newPassword) {
//		if (userRepository.findByUsername(username).isPresent()) {
//			User user = userRepository.findByUsername(username).get();
//			System.out.println(user.getPassword());
//			String encodedPassword = encoder.encode(newPassword);
//			user.setPassword(encodedPassword);
//			userRepository.save(user);
//
//			return "User's Password updated successfully";
//		} else {
//			System.out.println("User Not Found");
//			return "User not found by username: " + username;
//		}
//	}
//
////	@PutMapping("/api/user/{username}/update/email")
////	@PreAuthorize("hasRole('USER')")
////	public String updateUserEmail(@PathVariable String username, @RequestBody String newEmail) {
////		if (!userRepository.findByUsername(username).isEmpty()) {
////			User user = userRepository.findByUsername(username).get();
////			System.out.println(user.getEmail());
////			user.setEmail(newEmail);
////			userRepository.save(user);
////
////			return "Email updated successfully";
////		} else {
////			System.out.println("User Not Found");
////			return "User not found by username: " + username;
////		}
////	}
//
//	@PutMapping("/api/user/{username}/update/firstName")
//	@PreAuthorize("hasRole('USER')")
//	@Transactional
//	public String updateUserFirstName(@RequestBody String newFirstName, @PathVariable String username) {
//		if (userRepository.findByUsername(username).isPresent()) {
//			User user = userRepository.findByUsername(username).get();
//			System.out.println("Your user first name is " + user.getFirstName());
//			user.setFirstName(newFirstName);
//			userRepository.save(user);
//			kafkaTemplate.send("userEvent", new UpdateUserEvent(newFirstName,user.getEmail(),user.getRoles().stream().map(r -> r.getName().toString()).collect(Collectors.toSet()) ,user.getFirstName(),user.getLastName(),user.getPassword()));
//			//String username, String email, Set<String> role,  String firstName, String lastName
//			return "User's First Name updated successfully";
//		} else {
//			System.out.println("User Not Found");
//			return "User not found by username: " + username;
//		}
//
//	}
//
//	@PutMapping("/api/user/{username}/update/lastName")
//	@PreAuthorize("hasRole('USER')")
//	public String updateUserLastName(@RequestBody String newLastName, @PathVariable String username) {
//		if (userRepository.findByUsername(username).isPresent()) {
//			User user = userRepository.findByUsername(username).get();
//			System.out.println("Your user last name is " + user.getLastName());
//			user.setLastName(newLastName);
//			userRepository.save(user);
//			kafkaTemplate.send("userEvent", new UpdateUserEvent(user.getUsername(),user.getEmail(),user.getRoles().stream().map(r -> r.getName().toString()).collect(Collectors.toSet()) ,user.getFirstName(),user.getLastName(),user.getPassword()));
//			return "User's Last Name updated successfully";
//		} else {
//			System.out.println("User Not Found");
//			return "User not found by username: " + username;
//		}
//	}
//
////	@PutMapping("/api/user/{username}/update/location")
////	@PreAuthorize("hasRole('USER')")
////	public String updateUserLocation(@RequestBody String[] newLocation, @PathVariable String username) {
////		if (!userRepository.findByUsername(username).isEmpty()) {
////			User user = userRepository.findByUsername(username).get();
////			System.out.println("Your user location is :" + user.getCountry() + ", " + user.getState() + ", "
////					+ user.getCity() + ", " + user.getZipCode() + ", " + user.getAddress());
////			user.setCountry(newLocation[0]);
////			user.setState(newLocation[1]);
////			user.setCity(newLocation[2]);
////			user.setZipCode(newLocation[3]);
////			user.setAddress(newLocation[4]);
//////			if (!newLocation[3].isBlank()) {
//////				user.setZipCode(newLocation[3]);
//////			}
//////			else {
//////				user.setZipCode("");
//////			}
////			userRepository.save(user);
////
////			return "User's Location updated successfully";
////		} else {
////			System.out.println("User Not Found");
////			return "User not found by username: " + username;
////		}
////	}
////
////	@PutMapping("/api/user/{username}/update/birthDate")
////	@PreAuthorize("hasRole('USER')")
////	public String updateUserBirthDate(@RequestBody String newDateOfBirth, @PathVariable String username) {
////		if (!userRepository.findByUsername(username).isEmpty()) {
////			User user = userRepository.findByUsername(username).get();
////			System.out.println("Your user birth date is :" + user.getDateOfBirth());
////			user.setDateOfBirth(newDateOfBirth);
////			userRepository.save(user);
////			return "User's Birth Date updated successfully";
////		} else {
////			System.out.println("User Not Found");
////			return "User not found by username: " + username;
////		}
////	}
////
////	@PutMapping("/api/user/{username}/update/phoneNumber")
////	@PreAuthorize("hasRole('USER')")
////	public String updateUserPhoneNumber(@RequestBody String newPhoneNumber, @PathVariable String username) {
////		if (!userRepository.findByUsername(username).isEmpty()) {
////			User user = userRepository.findByUsername(username).get();
////			System.out.println("Your user birth date is :" + user.getPhoneNumber());
////			user.setPhoneNumber(newPhoneNumber);
////			userRepository.save(user);
////			return "User's Phone Number updated successfully";
////		} else {
////			System.out.println("User Not Found");
////			return "User not found by username: " + username;
////		}
////	}
////
////	@PutMapping("/api/user/{username}/update/photoUrl")
////	@PreAuthorize("hasRole('USER')")
////	public String updateUserPhotoUrl(@RequestBody String newPhootoUrl, @PathVariable String username) {
////		if (!userRepository.findByUsername(username).isEmpty()) {
////			User user = userRepository.findByUsername(username).get();
//////			System.out.println("Your user photo url is :" + user.getPhotoUrl());
////			if (!newPhootoUrl.isBlank()) {
////				user.setPhotoUrl(newPhootoUrl);
////
////				System.out.println("Your user photo url is : " + user.getPhotoUrl());
////
////				RestTemplate restTemplate = new RestTemplate();
////				String profilePhotoRootPath = restTemplate.getForObject(URL_PROFILE_PHOTO_DIRECTORY, String.class);
////
////				System.out.println("URL_PROFILE_PHOTO_DIRECTORY is: " + profilePhotoRootPath);
////
////				StringBuilder photoUrl = new StringBuilder(user.getPhotoUrl());
////				String profilePhotoAppDirectory = photoUrl.substring(0, photoUrl.lastIndexOf("/"));
////				user.setPhotoDirectory(profilePhotoRootPath + profilePhotoAppDirectory);
////
////				System.out.println("you user photo full directory is : " + user.getPhotoDirectory());
////
////				userRepository.save(user);
////				return "User's Photo Url updated successfully";
////			} else {
////				return "Url is empty";
////			}
////		} else {
////			System.out.println("User Not Found");
////			return "User not found by username: " + username;
////		}
////	}
////
////	@PutMapping("/api/user/{username}/update/ratedAds")
////	@PreAuthorize("hasRole('USER')")
////	public String updateUserRatedAds(@RequestBody String newRatedAdId, @PathVariable String username) {
////		if (!userRepository.findByUsername(username).isEmpty()) {
////			User user = userRepository.findByUsername(username).get();
////			System.out.println("Your user rated ads are : " + user.getRatedAds());
////			user.getRatedAds().add(newRatedAdId);
//////			List<String> ratedAds=user.getRatedAds();
//////			ratedAds.add(newRatedAd);
//////			user.setRatedAds(ratedAds);
////			userRepository.save(user);
////
////			return "User's rated ads updated successfully";
////		} else {
////			System.out.println("User Not Found");
////			return "User not found by username: " + username;
////		}
////	}
////
////	@PutMapping("/api/user/{username}/update/preferences")
////	@PreAuthorize("hasRole('USER')")
////	public String updateuserEvent(@RequestBody List<String> newPreferences, @PathVariable String username) {
////		if (!userRepository.findByUsername(username).isEmpty()) {
////			User user = userRepository.findByUsername(username).get();
////			System.out.println(
////					" /*************************************************/ Your user preferences before updating are : ");
////			for (String preference : user.getPreferences()) {
////				System.out.println("-----------current preference : " + preference);
////			}
////
////			user.setPreferences(newPreferences);
////			userRepository.save(user);
////			System.out.println("##################### User's preferences updated successfully");
////			return "User's preferences updated successfully";
////		} else {
////			System.out.println("User Not Found");
////			return "User not found by username: " + username;
////		}
////	}
//
////	@PutMapping("/api/user/{username}/update/publishedAds/add")
////	@PreAuthorize("hasRole('USER')")
////	public String addAdToUserPublishedAds(@RequestBody String newPublishedAdId, @PathVariable String username) {
////		if (!userRepository.findByUsername(username).isEmpty()) {
////			User user = userRepository.findByUsername(username).get();
////			System.out.println(
////					" /*************************************************/ Your user published ads before updating are : ");
////			for (String id : user.getPublishedAds()) {
////				System.out.println("-----------published ad's id: " + id);
////			}
////
////			user.getPublishedAds().add(newPublishedAdId);
////			userRepository.save(user);
////			System.out.println("##################### Ad's added to User's published ads successfully");
////			return "Ad's added to User's published ads successfully";
////		} else {
////			System.out.println("User Not Found");
////			return "User not found by username: " + username;
////		}
////	}
////
////	@PutMapping("/api/user/{username}/update/publishedAds/remove")
////	@PreAuthorize("hasRole('USER')")
////	public String removeAdFromUserPublishedAds(@RequestBody String newPublishedAdId, @PathVariable String username) {
////		if (!userRepository.findByUsername(username).isEmpty()) {
////			User user = userRepository.findByUsername(username).get();
////			System.out.println(
////					" /*************************************************/ Your user published ads before updating are : ");
////			for (String id : user.getPublishedAds()) {
////				System.out.println("-----------published ad's id: " + id);
////			}
////			if (user.getPublishedAds().contains(newPublishedAdId)) {
////				user.getPublishedAds().remove(newPublishedAdId);
////				userRepository.save(user);
////				System.out.println("##################### Ad's removed from User's published ads successfully");
////				return "Ad's removed from User's published ads successfully";
////			} else {
////				System.out.println("##################### Ad does not exist in User's published ads!!");
////				return "Ad does not exist in User's published ads!!";
////			}
////		} else {
////			System.out.println("User Not Found");
////			return "User not found by username: " + username;
////		}
////	}
////
//////	@GetMapping("/api/user/{username}/update/publishedAds/{newPublishedAdId}")
//////	@PreAuthorize("hasRole('USER')")
//////	public String updateUserPublishedAds2(@PathVariable String newPublishedAdId, @PathVariable String username) {
//////		if (!userRepository.findByUsername(username).isEmpty()) {
//////			User user = userRepository.findByUsername(username).get();
//////			System.out.println("Your user published ads are : " + user.getPublishedAds());
//////			user.getPublishedAds().add(newPublishedAdId);
//////			userRepository.save(user);
//////
//////			return "User's published ads updated successfully";
//////		} else {
//////			System.out.println("User Not Found");
//////			return "User not found by username: " + username;
//////		}
//////	}
//
//	@GetMapping("/api/user/{username}/checkPassMatch/{oldPassword}")
//	@PreAuthorize("hasRole('USER')")
//	public String checkIfPasswordMatches(@PathVariable String username, @PathVariable String oldPassword) {
//		if (userRepository.findByUsername(username).isPresent()) {
//			User user = userRepository.findByUsername(username).get();
//			System.out.println(user.getPassword());
//			String encodedPassword = user.getPassword();
//			if (encoder.matches(oldPassword, encodedPassword)) {
//				return "match";
//			} else {
//				return "dont match";
//			}
//
//		} else {
//			System.out.println("User Not Found");
//			return "User not found";
//		}
//	}
//
////	@DeleteMapping("/api/user/{username}/deleteAccountAndPublishedAds")
////	@Transactional
////	@PreAuthorize("hasRole('USER')")
////	public String deleteUserAccountAndAllPublishedAds(@PathVariable String username) {
////		if (!userRepository.findByUsername(username).isPresent()) {
////			//deleteAllUserPublishedAds(username);
//////			deleteUserProfilePhoto(username);
////			userRepository.deleteByUsername(username);
////			System.out.println("User with username " + username
////					+ " deleted successfully. And all user's published ads have been deleted successfully");
////			return "User with username " + username
////					+ " deleted successfully. And all user's published ads have been deleted successfully";
////		} else {
////			System.out.println("User Not Found");
////			return "User not found";
////		}
////	}
//
//	@DeleteMapping("/api/user/{username}/deleteAccount")
//	@Transactional
//	@PreAuthorize("hasRole('USER')")
//	public String deleteUserAccount(@PathVariable String username) {
//		if (userRepository.findByUsername(username).isPresent()) {
////			deleteAllUserPublishedAds(username);
////			deleteUserProfilePhoto(username);
//			userRepository.deleteByUsername(username);
//			kafkaTemplate.send("userEvent",new DeleteUserEvent(username));
//			System.out.println("User with username " + username + " deleted successfully");
//			return "User with username " + username + " deleted successfully";
//		} else {
//			System.out.println("User Not Found");
//			return "User not found";
//		}
//	}
////
////	@DeleteMapping("/api/user/{username}/delete/allPublishedAds")
////	@PreAuthorize("hasRole('USER')")
////	public String deleteAllUserPublishedAds(@PathVariable String username) {
////		int count = 0;
////		String publisherFolderDirectory = "";
////		if (!userRepository.findByUsername(username).isEmpty()) {
////			User user = userRepository.findByUsername(username).get();
////			if (!user.getPublishedAds().isEmpty()) {
////				for (String adId : user.getPublishedAds()) {
////					if (count < 1) {
////						final String URL_GET_ADS_PUBLISHER_FOLDER_BY_SOME_AD_ID = "http://localhost:8091/ads/deletePhotosPublisherDirectory/"
////								+ adId;
////						publisherFolderDirectory = new RestTemplate()
////								.getForObject(URL_GET_ADS_PUBLISHER_FOLDER_BY_SOME_AD_ID, String.class);
////						count++;
////					}
////					final String URL_DELETE_AD_BY_ID = "http://localhost:8091/ad/delete/" + adId;
////					new RestTemplate().getForObject(URL_DELETE_AD_BY_ID, String.class);
////				}
////
////				try {
////
////					System.out.println("***************** publisherFolderDirectory : " + publisherFolderDirectory);
////					File directory = new File(publisherFolderDirectory);
////					if (directory.exists()) {
////						FileUtils.cleanDirectory(directory);
////						FileUtils.deleteDirectory(directory);
////						System.out.println("///////publisher ads photos folder deleted ");
////
////					}
////
////				} catch (IOException e) {
////					System.out.println(e);
////
////				}
////
////				return "All published ads have been deleted successfully";
////			}
////			return "User have no published ads!!";
////		} else
////
////		{
////			System.out.println("User Not Found");
////			return "User not found by username: " + username;
////		}
////	}
//
////	@DeleteMapping("/api/user/{username}/delete/profilePhoto")
////	@PreAuthorize("hasRole('USER')")
////	public String deleteUserProfilePhoto(@PathVariable String username) {
////
////		try {
////			if (!userRepository.findByUsername(username).isEmpty()) {
////				User user = userRepository.findByUsername(username).get();
////				if (user.getPhotoDirectory() != null) {
////					File directory = new File(user.getPhotoDirectory());
////					if (directory.exists()) {
////						FileUtils.cleanDirectory(directory);
////						FileUtils.deleteDirectory(directory);
////						System.out.println("///////directory deleted ");
////					}
////				}
////				user.setPhotoUrl("");
////				user.setPhotoDirectory("");
////
////				userRepository.save(user);
////				return "User's Photo deleted successfully";
////
////			} else {
////				System.out.println("User Not Found");
////				return "User not found by username: " + username;
////			}
////		} catch (IOException e) {
////			return (e.toString());
////		}
////	}
//
////	@PutMapping("/api/user/{username}/update/photoDirectory")
////	@PreAuthorize("hasRole('USER')")
////	public String updateUserPhotoDirectory(@RequestBody String newPhotoDirectory,@PathVariable String username) {
////		if (!userRepository.findByUsername(username).isEmpty()) {
////			User user = userRepository.findByUsername(username).get();
////			System.out.println("Your user birth date is :" + user.getPhotoDirectory());
////			user.setPhotoDirectory(newPhotoDirectory);
////			userRepository.save(user);
////			return "User's Photo Directory updated successfully";
////		} else {
////			System.out.println("User Not Found");
////			return "User not found by username: " + username;
////		}
////	}
//
//	@GetMapping("/api/user/allDetails/{username}")
//	@PreAuthorize("hasRole('USER')")
//	public User getUserDetailsByUsername(@PathVariable String username) {
//		if (userRepository.findByUsername(username).isPresent()) {
//			User user = userRepository.findByUsername(username).get();
//			System.out.println(user.toString());
//			return user;
//		} else {
//			System.out.println("User Not Found");
//			return null;
//		}
//	}
//
////	@GetMapping("/api/user/signupDate/{username}")
////	@PreAuthorize("hasRole('USER')")
////	public String getSignupDateOfUser(@PathVariable String username) {
////		if (!userRepository.findByUsername(username).isEmpty()) {
////			User user = userRepository.findByUsername(username).get();
////			System.out.println(user.getSignupDate());
////			return user.getSignupDate().toString();
////		} else {
////			System.out.println("User Not Found");
////			return "User not found by username: " + username;
////		}
////	}
////
////	@GetMapping("/api/user/firstName/{username}")
////	@PreAuthorize("hasRole('USER')")
////	public String getFirstNameOfUser(@PathVariable String username) {
////		if (!userRepository.findByUsername(username).isEmpty()) {
////			User user = userRepository.findByUsername(username).get();
////			System.out.println(user.getUsername());
////			return user.getFirstName();
////		} else {
////			System.out.println("User Not Found");
////			return "User not found by username: " + username;
////		}
////	}
////
////	@GetMapping("/api/user/lastName/{username}")
////	@PreAuthorize("hasRole('USER')")
////	public String getLastNameOfUser(@PathVariable String username) {
////		if (!userRepository.findByUsername(username).isEmpty()) {
////			User user = userRepository.findByUsername(username).get();
////			System.out.println(user.getUsername());
////			return user.getLastName();
////		} else {
////			System.out.println("User Not Found");
////			return "User not found by username: " + username;
////		}
////	}
////
////	@GetMapping("/api/user/email/{username}")
////	@PreAuthorize("hasRole('USER')")
////	public String getEmailOfUser(@PathVariable String username) {
////		if (!userRepository.findByUsername(username).isEmpty()) {
////			User user = userRepository.findByUsername(username).get();
////			System.out.println(user.getEmail());
////			return user.getEmail();
////		} else {
////			System.out.println("User Not Found");
////			return "User not found by username: " + username;
////		}
////	}
//
//	@GetMapping("/api/test/user")
//	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
//	public String userAccess() {
////		User user = userRepository.findByUsername("mmba007").get();
////		return user.getName();
//		return ">>> User Contents!";
//	}
//
//	@GetMapping("/api/test/advertiser")
//	@PreAuthorize("hasRole('ADVERTISER') or hasRole('ADMIN')")
//	public String advertiserAccess() {
//		return ">>> Advertiser Board";
//	}
//
//	@GetMapping("/api/test/consumer")
//	@PreAuthorize("hasRole('CONSUMER') or hasRole('ADMIN')")
//	public String consumerAccess() {
//		return ">>> Consumer Board";
//	}
//
//	@GetMapping("/api/test/admin")
//	@PreAuthorize("hasRole('ADMIN')")
//	public String adminAccess() {
//		return ">>> Admin Contents";
//	}
//}