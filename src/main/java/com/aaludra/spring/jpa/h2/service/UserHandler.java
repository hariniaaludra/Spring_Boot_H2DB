package com.aaludra.spring.jpa.h2.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.aaludra.spring.jpa.h2.enum1.StatusEnum;
import com.aaludra.spring.jpa.h2.model.User;
import com.aaludra.spring.jpa.h2.repository.UserRepository;
import com.aaludra.spring.jpa.h2.util.DateUtil;
import com.aaludra.spring.jpa.h2.view.Userinput;

@Service
public class UserHandler {
	@Autowired
	UserRepository userRepository;

	public List<User> getAllUser() {
		List<User> list = new ArrayList<>();
		userRepository.findAll().forEach(list::add);
		return list;
	}

	public Optional<User> getUserById(long id) {
		return userRepository.findById((int) id);
	}

	public User createUser(User user) {
		return userRepository.save(new User(0, user.getUsername(), user.getDisplayname(), user.getPassword(),
				user.getDob(), user.getPhoneno(), user.getStatus(), user.getCreatedby(), DateUtil.getCurrentTimeStamp(),
				user.getUpdatedby(), user.getUpdateddate()));
	}

	public User updateUser(User userobj) {
		userRepository.save(userobj);

		return userobj;
	}

	public long deleteUserById(long id) {
		userRepository.deleteById((int) id);
		return id;
	}

	public ResponseEntity<HttpStatus> deleteAll() {
		userRepository.deleteAll();
		return null;
	}

	public void testXmlToObject() throws JAXBException, FileNotFoundException {
		File file = new File("userinput.xml");
		JAXBContext jaxbContext = JAXBContext.newInstance(Userinput.class);

		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		Userinput que = (Userinput) jaxbUnmarshaller.unmarshal(file);

		System.out.println(que.getId() + " " + que.getUsername() + " " + que.getDisplayname() + " " + que.getPassword()
				+ " " + que.getDob() + " " + que.getPhoneno());

		userRepository.save(new User(0, que.getUsername(), que.getDisplayname(), que.getPassword(), null,
				Long.valueOf(que.getPhoneno()), StatusEnum.Active.name(), "Admin", DateUtil.getCurrentTimeStamp(),
				"Admin", DateUtil.getCurrentTimeStamp()));

	}

}
