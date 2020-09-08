package com.enit.authentication.controller;

import java.util.*;

import javax.validation.Valid;

import com.enit.authentication.events.Event;
import com.enit.authentication.events.LogInUserEvent;
import com.enit.authentication.events.RegisterUserEvent;
import com.enit.authentication.message.request.LoginForm;
import com.enit.authentication.message.request.SignUpForm;
import com.enit.authentication.message.response.JwtResponse;
import com.enit.authentication.message.response.ResponseMessage;
import com.enit.authentication.model.EventName;
import com.enit.authentication.model.Role;
import com.enit.authentication.model.RoleName;
import com.enit.authentication.model.User;
import com.enit.authentication.repository.RoleRepository;
import com.enit.authentication.repository.UserRepository;
import com.enit.authentication.security.jwt.JwtProvider;
import com.enit.authentication.service.RoleConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthRestAPIs {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtProvider jwtProvider;

	@Autowired
	KafkaTemplate<String, Event> kafkaTemplate;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginForm loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		String jwt = jwtProvider.generateJwtToken(authentication);
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
///////////////////////////////////////Add user to kafka topic/////////////////////////////////
		kafkaTemplate.send("login-logout", new LogInUserEvent(loginRequest.getUsername(),12.099,36.8));

		//List<String> preferences = userRepository.findByUsername(loginRequest.getUsername()).get().getPreferences();
//


		return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getUsername(), userDetails.getAuthorities()));

	}

	//////////////////////////// Mark user as logged
	//////////////////////////// out////////////////////////////////

	@GetMapping("/signout/{username}")
	public ResponseEntity<?> logOut(@PathVariable String username) {
//		kafkaTemplate.send("login-logout", new LogoutUserEvent(username));
		return ResponseEntity.ok("User Logged Out Successfully!");
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpForm signUpRequest) {
		System.out
				.println("************************** username from signup request is : " + signUpRequest.getUsername());
		System.out.println("************************** result of user exists in com.enit.randomrecommandationservice.repository is : "
				+ userRepository.findByUsername(signUpRequest.getUsername()).isPresent());
//		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
//			return new ResponseEntity<>(new ResponseMessage("Fail -> Username is already taken!"),
//					HttpStatus.BAD_REQUEST);
//		}
//
//		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
//			return new ResponseEntity<>(new ResponseMessage("Fail -> Email is already in use!"),
//					HttpStatus.BAD_REQUEST);
//		}

		if (userRepository.findByUsername(signUpRequest.getUsername()).isPresent()) {
			return new ResponseEntity<>(new ResponseMessage("Fail -> Username is already taken!"),
					HttpStatus.BAD_REQUEST);
		}
        System.out.println("after username validation");
		if (userRepository.findByEmail(signUpRequest.getEmail()).isPresent()) {
			return new ResponseEntity<>(new ResponseMessage("Fail -> Email is already in use!"),
					HttpStatus.BAD_REQUEST);
		}
		System.out.println("after email validation");

		// Creating user's account
		User user = new User(signUpRequest.getFirstName(), signUpRequest.getLastName(), signUpRequest.getGender(),
				signUpRequest.getUsername(), signUpRequest.getEmail(), encoder.encode(signUpRequest.getPassword()));
		System.out.println("after user creation");
        Set<String> a= new HashSet<>();
        a.add("admin1");
		Set<String> strRoles = signUpRequest.getRole();
		Set<Role> roles = RoleConverter.convertAllToRole(strRoles,roleRepository);
		;


//		strRoles.forEach(role -> {
//			switch (role) {
//			case "admin":
//				System.out.println(roleRepository.findByName(RoleName.ROLE_ADMIN));
//
//				Role adminRole = roleRepository.findByName(RoleName.ROLE_ADMIN)
//						.orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
//				roles.add(adminRole);
//				System.out.println("list: "+ roles);
//
//				break;
//			case "advertiser":
//				Role advertiserRole = roleRepository.findByName(RoleName.ROLE_ADVERTISER)
//						.orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
//				roles.add(advertiserRole);
//
//				break;
//			case "consumer":
//				Role consumerRole = roleRepository.findByName(RoleName.ROLE_CONSUMER)
//						.orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
//				roles.add(consumerRole);
//
//				break;
//			default:
//				Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
//						.orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
//				roles.add(userRole);
//			}
//		});
       		System.out.println("hello before role");

		user.setRoles(roles);
				System.out.println("hello after saving");

//		DateFormat fmt = new SimpleDateFormat("MMMM dd, yyyy", Locale.US);
//		try {
//			Date date = fmt.parse("June 27,  2007");
//			user.setSignupDate(date);
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		user.setSignupDate(new Date());
		System.out.println("hello before saving");
		userRepository.save(user);
				System.out.println("hello after saving");

		kafkaTemplate.send("userEvent", new RegisterUserEvent(signUpRequest.getUsername(),signUpRequest.getEmail(),signUpRequest.getRole(),signUpRequest.getFirstName(),signUpRequest.getLastName(),signUpRequest.getPassword()));


		return new ResponseEntity<>(new ResponseMessage("User registered successfully!"), HttpStatus.OK);
	}
}