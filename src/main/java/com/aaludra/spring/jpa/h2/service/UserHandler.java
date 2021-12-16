package com.aaludra.spring.jpa.h2.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.aaludra.spring.jpa.h2.enum1.StatusEnum;
import com.aaludra.spring.jpa.h2.model.User;
import com.aaludra.spring.jpa.h2.repository.UserRepository;
import com.aaludra.spring.jpa.h2.util.DateUtil;
import com.aaludra.spring.jpa.h2.view.UserJsoninput;
import com.aaludra.spring.jpa.h2.view.UserView;
import com.aaludra.spring.jpa.h2.view.Userinput;
import com.aaludra.spring.jpa.h2.view.Userslist;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class UserHandler {
	@Autowired
	UserRepository userRepository;

	public List<User> getAllUser() {
		List<User> list = new ArrayList<>();
		userRepository.findAll().forEach(list::add);
		return list;
		
	}

	public Optional<User> getUserById(int id) {
		return userRepository.findById(id);
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
		File file = new File("E:\\Git\\new\\Spring_Boot_H2DB\\userinput.xml");
		JAXBContext jaxbContext = JAXBContext.newInstance(Userinput.class);

		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		Userinput que = (Userinput) jaxbUnmarshaller.unmarshal(file);

		System.out.println(que.getId());

		List<Userslist> list = que.getUserslist();
		List<Userslist> ulist = new ArrayList<>();

		for (Userslist usr : list) {
			System.out.println(usr.getUsername() + " " + usr.getDisplayname() + " " + usr.getPassword() + " "
					+ usr.getDob() + " " + usr.getPhoneno() + " " + usr.getStatus() + " " + usr.getCreatedby() + " "
					+ usr.getCreateddate() + " " + usr.getUpdatedby() + " " + usr.getUpdateddate());

			ulist.add(usr);

			userRepository.save(new User(0, usr.getUsername(), usr.getDisplayname(), usr.getPassword(),DateUtil.convertStringToDate(usr.getDob()),
					Long.valueOf(usr.getPhoneno()), StatusEnum.Active.name(), "Admin", DateUtil.getCurrentTimeStamp(),
					"Admin", DateUtil.getCurrentTimeStamp()));
		}

	}

	public void testjsonToObject() throws JsonParseException,JsonMappingException,IOException {
		ObjectMapper objectmapper = new ObjectMapper();
		
		UserJsoninput userview = objectmapper.readValue(new File("E:\\Git\\new\\Spring_Boot_H2DB\\user.json"),UserJsoninput.class);
		List<Userslist> ulist = new ArrayList<>();
		List<Userslist> jsonlist = userview.getUserslist();
		
		System.out.println("ulist" + jsonlist.size());
		

		for (Userslist usr : jsonlist) {
			System.out.println(usr.getUsername() + " " + usr.getDisplayname() + " " + usr.getPassword() + " "
					+ usr.getDob() + " " + usr.getPhoneno() + " " + usr.getStatus() + " " + usr.getCreatedby() + " "
					+ usr.getCreateddate() + " " + usr.getUpdatedby() + " " + usr.getUpdateddate());

			ulist.add(usr);

			userRepository.save(new User(0, usr.getUsername(), usr.getDisplayname(), usr.getPassword(),DateUtil.convertStringToDate(usr.getDob()),
					Long.valueOf(usr.getPhoneno()), StatusEnum.Active.name(), "Admin", DateUtil.getCurrentTimeStamp(),
					"Admin", DateUtil.getCurrentTimeStamp()));
		}

	}

	private User buildUser(UserView userview) {
		User tbluser = new User();
		tbluser.setUsername(userview.getUsername());
		tbluser.setDisplayname(userview.getDisplayname());
		tbluser.setPassword(userview.getPassword());
		tbluser.setPhoneno(Long.valueOf(userview.getPhoneno()));
		tbluser.setDob(DateUtil.convertStringToDate(userview.getDob()));
		tbluser.setStatus(userview.getStatus());
		tbluser.setCreatedby(userview.getCreatedby());
		tbluser.setCreateddate(DateUtil.convertStringToTimestamp(userview.getCreateddate()));
		tbluser.setUpdatedby(userview.getUpdatedby());
		tbluser.setUpdateddate(DateUtil.convertStringToTimestamp(userview.getUpdateddate()));

		return tbluser;
	}

	/*public void testjsonToObject() {
		ObjectMapper objectmapper = new ObjectMapper();

		UserView userview = null;
			
		try {
			userview = objectmapper.readValue(new File("user.json"), UserView.class);
			User user = this.buildUser(userview);
			user.setStatus(StatusEnum.Active.name());
			user.setCreatedby("Admin");
			user.setUpdatedby("Admin");
			user.setCreateddate(DateUtil.getCurrentTimeStamp());
			user.setUpdateddate(DateUtil.getCurrentTimeStamp());
			System.out.println(user);
			userRepository.save(user);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(userview);

	}*/
	
	
	
	/* public Optional<User> getUserById(int id) {
		Optional<User> findById = userRepository.findById(id);
		if(findById==null) {
			System.out.println("Result null");
		}
		else {
		System.out.println("result: "+findById);
		}
		return findById;
		return userRepository.findById(id);
	}*/
}
