package com.fidelity.usermanager;

import com.fidelity.usermanager.Models.User;
import com.fidelity.usermanager.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@SpringBootApplication//(exclude = {HibernateJpaAutoConfiguration.class})
public class UserManagerApplication implements CommandLineRunner {

	@Autowired
	UserRepository userRepo;
	public static void main(String[] args) {
		SpringApplication.run(UserManagerApplication.class, args);
	}

	public void seedInitData (){
		List<User> users = new ArrayList<User>();
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH);

		User user1 = new User();
		user1.setId(1);
		user1.setEmail("dev.chukwudiumah@gmail.com");
		user1.setFirstName("Chukwudi");
		user1.setLastName("Umah");
		//user1.setDateOfBirth(LocalDate.of(1995, 4, 1));
		try {
			user1.setBirthDate(sdf.parse("04/1/1995"));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		User user2 = new User();
		user2.setId(2);
		user2.setEmail("chinaza@gmail.com");
		user2.setFirstName("Ebuka");
		user2.setLastName("John");
		//user2.setDateOfBirth(LocalDate.of(1988, 3, 21));
		try {
			user2.setBirthDate(sdf.parse("03/21/1988"));
		} catch (ParseException e) {
			e.printStackTrace();
		}


		User user3 = new User();
		user3.setId(3);
		user3.setEmail("dal@gmail.com");
		user3.setFirstName("Dalington");
		user3.setLastName("Joshua");
		//user3.setDateOfBirth(LocalDate.of(2001, 10, 15));
		try {
			user3.setBirthDate(sdf.parse("10/15/2001"));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		users.add(user1);
		users.add(user2);
		users.add(user3);

		userRepo.saveAll(users);
	}

	@Override
	public void run(String... args) throws Exception {
		seedInitData();
	}
}
