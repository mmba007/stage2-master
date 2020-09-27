package com.enit.authentication.controller;

import java.util.Date;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.enit.authentication.config.EventService;
import com.enit.authentication.events.DeleteUserEvent;
import com.enit.authentication.events.LogInUserEvent;
import com.enit.authentication.events.RegisterUserEvent;
import com.enit.authentication.message.request.LoginForm;
import com.enit.authentication.message.request.SignUpForm;
import com.enit.authentication.message.response.JwtResponse;
import com.enit.authentication.message.response.ResponseMessage;
import com.enit.authentication.model.Role;
import com.enit.authentication.model.User;
import com.enit.authentication.repository.RoleRepository;
import com.enit.authentication.repository.UserRepository;
import com.enit.authentication.security.jwt.JwtProvider;
import com.enit.authentication.service.RoleConverter;

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


	final public EventService  kafkaTemplate;

	public AuthRestAPIs(EventService eventService){
		this.kafkaTemplate=eventService;
	}

	@DeleteMapping("/delete/user/{username}")
	@Transactional
	public String test(@PathVariable String username) {
		if (userRepository.findByUsername(username).isPresent()) {
//			System.out.println("\n ***** User "+username+" found :)");
			userRepository.deleteByUsername(username);
			kafkaTemplate.sendUserEvent(new DeleteUserEvent(username));
			return "\n ******** User deleted successfully";
		}else{
				return "\n ******* user does'nt exist !!";
		}
	}

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginForm loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		String jwt = jwtProvider.generateJwtToken(authentication);
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
///////////////////////////////////////Add user to kafka topic/////////////////////////////////
		kafkaTemplate.sendLoginEvent(new LogInUserEvent(loginRequest.getUsername(),12.099,36.8));
System.out.println("\n************** login was sent from JWTAuth service with username: "+loginRequest.getUsername()+"  ************************\n");
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
	public ResponseEntity<?> registerUser( @RequestBody SignUpForm signUpRequest) {
		System.out
				.println("\n************************** username from signup request is : " + signUpRequest.getUsername());
//		System.out.println("\n************************** result of user exists in db is : "
//				+ userRepository.findByUsername(signUpRequest.getUsername()).isPresent());
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
        System.out.println("\n********* after username validation");
//		if (userRepository.findByEmail(signUpRequest.getEmail()).isPresent()) {
//			return new ResponseEntity<>(new ResponseMessage("Fail -> Email is already in use!"),
//					HttpStatus.BAD_REQUEST);
//		}
//		System.out.println("\n ******** after email validation");

		// Creating user's account
		User user = new User(signUpRequest.getFirstName(), signUpRequest.getLastName(), signUpRequest.getGender(),
				signUpRequest.getUsername(), signUpRequest.getEmail(), encoder.encode(signUpRequest.getPassword()));
		System.out.println("\n********* after user creation");
//        Set<String> a= new HashSet<>();
//        a.add("admin1");
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
//       		System.out.println("\n *******hello before role");

		user.setRoles(roles);
//				System.out.println("\n ******** hello after saving");

//		DateFormat fmt = new SimpleDateFormat("MMMM dd, yyyy", Locale.US);
//		try {
//			Date date = fmt.parse("June 27,  2007");
//			user.setSignupDate(date);
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		user.setSignupDate(new Date());
//		System.out.println("\n ******** hello before saving");
		userRepository.save(user);
//				System.out.println("\n ******** hello after saving");

		// kafkaTemplate.sendUserEvent( new RegisterUserEvent(signUpRequest.getUsername(),signUpRequest.getEmail(),signUpRequest.getRole(),signUpRequest.getFirstName(),signUpRequest.getLastName(),signUpRequest.getPassword()));
		kafkaTemplate.sendUserEvent( new RegisterUserEvent(signUpRequest.getUsername()));

		return new ResponseEntity<>(new ResponseMessage("User registered successfully!"), HttpStatus.OK);
	}
}
