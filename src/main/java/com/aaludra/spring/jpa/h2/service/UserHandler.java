package com.aaludra.spring.jpa.h2.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
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
import com.aaludra.spring.jpa.h2.util.Dateconvert;
import com.aaludra.spring.jpa.h2.view.UserJsonToObj;
import com.aaludra.spring.jpa.h2.view.UserObjToJson;
import com.aaludra.spring.jpa.h2.view.UserOutputView;
import com.aaludra.spring.jpa.h2.view.UserInputView;
import com.aaludra.spring.jpa.h2.view.UserXmlToObj;
import com.aaludra.spring.jpa.h2.view.UserObjToXml;
import com.aaludra.spring.jpa.h2.view.Userslist;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class UserHandler {
	@Autowired
	UserRepository userRepository;

//Get  Mapping
	public List<UserOutputView> getAllUser() {
		List<User> list = userRepository.findAll();
		List<UserOutputView> getalluserout = new ArrayList();
		for (User u : list) {
			UserOutputView userview = this.buildUserOut(u);
			getalluserout.add(userview);
		}
		return getalluserout;
	}

//Get Mapping
	public Optional<UserOutputView> getUserById(int id) {
		Optional<User> user1 = userRepository.findById(id);
		UserOutputView getuserbyid = this.buildUserOut(user1.get());
		return Optional.of(getuserbyid);
	}

//Post Mapping
	public UserOutputView createUser(UserInputView userview) {
		User user = this.buildUserIn(userview);
		UserOutputView createuserout = this.buildUserOut(userRepository.save(user));
		return createuserout;
	}

// Put Mapping
	public UserOutputView updateUser(UserInputView userview, Integer id) {
		Optional<User> userData = userRepository.findById(id);
		if(userData.isPresent()) {
			User user=this.buildUserIn(userview);
			user.setId(id);
			UserOutputView useroutputview = this.buildUserOut(userRepository.save(user));
		return useroutputview;
		}
		return null;
	}
	

// Delete Mapping
	
	public long deleteUserById(long id) {
		userRepository.deleteById((int) id);
		return id;
		
	}

//Delete All 
	public ResponseEntity<HttpStatus> deleteAll() {
		userRepository.deleteAll();
		return null;
	}

//XML To Object
	public void testXmlToObject() throws JAXBException, FileNotFoundException {
		File file = new File("E:\\Git\\new\\Spring_Boot_H2DB\\userinput.xml");
		JAXBContext jaxbContext = JAXBContext.newInstance(UserXmlToObj.class);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		UserXmlToObj que = (UserXmlToObj) jaxbUnmarshaller.unmarshal(file);
		System.out.println(que.getId());
		List<Userslist> list = que.getUserslist();
		List<Userslist> ulist = new ArrayList<>();
		for (Userslist usr : list) {
			System.out.println(usr.getUsername() + " " + usr.getDisplayname() + " " + usr.getPassword() + " "
					+ usr.getDob() + " " + usr.getPhoneno() + " " + usr.getStatus() + " " + usr.getCreatedby() + " "
					+ usr.getCreateddate() + " " + usr.getUpdatedby() + " " + usr.getUpdateddate());
			ulist.add(usr);
			userRepository.save(new User(usr.getUsername(), usr.getDisplayname(), usr.getPassword(),
					DateUtil.convertStringToDate(usr.getDob()), Long.valueOf(usr.getPhoneno()),
					StatusEnum.Active.name(), "Admin", DateUtil.getCurrentTimeStamp(), "Admin",
					DateUtil.getCurrentTimeStamp()));
		}

	}

// Object To Xml
	public void testObjectToXml() throws JAXBException {
		UserObjToXml list = new UserObjToXml(getAllUser());
		JAXBContext jaxbContext = JAXBContext.newInstance(UserObjToXml.class);
		Marshaller marshaller = jaxbContext.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		marshaller.marshal(list, new File("E:\\Git\\new\\Spring_Boot_H2DB\\src\\main\\resources\\userxmlinput.xml"));
	}

//Json To Object

	public void testjsonToObject() throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper objectmapper = new ObjectMapper();
		UserJsonToObj userview = objectmapper.readValue(new File("user.json"), UserJsonToObj.class);
		List<Userslist> ulist = new ArrayList<>();
		List<Userslist> jsonlist = userview.getUserslist();
		System.out.println("ulist" + jsonlist.size());
		for (Userslist usr : jsonlist) {
			System.out.println(usr.getUsername() + " " + usr.getDisplayname() + " " + usr.getPassword() + " "
					+ usr.getDob() + " " + usr.getPhoneno() + " " + usr.getStatus() + " " + usr.getCreatedby() + " "
					+ usr.getCreateddate() + " " + usr.getUpdatedby() + " " + usr.getUpdateddate());
			ulist.add(usr);
			userRepository.save(new User(usr.getUsername(), usr.getDisplayname(), usr.getPassword(),
					DateUtil.convertStringToDate(usr.getDob()), Long.valueOf(usr.getPhoneno()),
					StatusEnum.Active.name(), "Admin", DateUtil.getCurrentTimeStamp(), "Admin",
					DateUtil.getCurrentTimeStamp()));
		}
	}

//Object To Json
	public void testObjectToJson() throws JsonParseException, JsonMappingException, IOException {
		List<UserOutputView> getalluserout =this.getAllUser();
		File jsonfile = new File ("E:\\Git\\new\\Spring_Boot_H2DB\\src\\main\\resources\\userjsoninput.json");
	        ObjectMapper objectmapper = new ObjectMapper();
	        objectmapper.writeValue(jsonfile, getalluserout);
	}
	

	private User buildUserIn(UserInputView userview) {
		User tbluser = new User();
		tbluser.setUsername(userview.getUsername());
		tbluser.setDisplayname(userview.getDisplayname());
		tbluser.setPassword(userview.getPassword());
		tbluser.setDob(DateUtil.convertStringToDate(userview.getDob()));
		tbluser.setStatus(StatusEnum.Active.name());
		tbluser.setCreatedby("Admin");
		tbluser.setUpdatedby("Admin");
		tbluser.setCreateddate(DateUtil.getCurrentTimeStamp());
		tbluser.setUpdateddate(DateUtil.getCurrentTimeStamp());
		//tbluser.setPhoneno(Long.parseLong(userview.getPhoneno()));
		return tbluser;
	}

	private UserOutputView buildUserOut(User user) {
		UserOutputView tbluser1 = new UserOutputView();
		tbluser1.setUsername(user.getUsername());
		tbluser1.setDisplayname(user.getDisplayname());
		tbluser1.setPassword(user.getPassword());
        tbluser1.setPhoneno(Long.toString(user.getPhoneno()));
		tbluser1.setDob(Dateconvert.convertStringToDate(user.getDob()));
		tbluser1.setStatus(StatusEnum.Active.name());
		tbluser1.setCreatedby("Admin");
		tbluser1.setUpdatedby("Admin");
		//tbluser1.setCreateddate(user.getCreateddate().toString());
		//tbluser1.setUpdateddate(user.getUpdateddate().toString());
		return tbluser1;
	}
}

/*
 * public void testjsonToObject() { ObjectMapper objectmapper = new
 * ObjectMapper();
 * 
 * UserView userview = null;
 * 
 * try { userview = objectmapper.readValue(new File("user.json"),
 * UserView.class); User user = this.buildUser(userview);
 * user.setStatus(StatusEnum.Active.name()); user.setCreatedby("Admin");
 * user.setUpdatedby("Admin");
 * user.setCreateddate(DateUtil.getCurrentTimeStamp());
 * user.setUpdateddate(DateUtil.getCurrentTimeStamp());
 * System.out.println(user); userRepository.save(user); } catch (IOException e)
 * { e.printStackTrace(); } System.out.println(userview);
 * 
 * }
 */
