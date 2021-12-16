package com.aaludra.spring.jpa.h2.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.aaludra.spring.jpa.h2.model.User;
import com.aaludra.spring.jpa.h2.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
class UserHandlerTest2 {

	@Mock
	private UserRepository userRepository;

	@InjectMocks
	private UserHandler userhandler;
	private User user;
	private User user1;
	private User user2;
	List<User> userList;

	@BeforeEach
	public void setUp() {
		userList = new ArrayList<>();
		user = new User(0, "kunju", "achuz", "abab", null, null, "Active", "admin", null, "admin", null);
		user1 = new User(1, "achu", "visruth", "abcd", null, null, "Active", "admin", null, "admin", null);
		user2 = new User(2, "abi", "abishek", "xyz", null, null, "Active", "admin", null, "admin", null);
		userList.add(user);
		userList.add(user1);
		userList.add(user2);
	}

	@Test
	public void getAllUserTest() {
		when(userRepository.findAll()).thenReturn(userList);

		List<User> userlist = userhandler.getAllUser();

		assertThat(userlist.size()).isGreaterThan(1);
		System.out.println(userlist);

	}

	@Test
	public void getUserByIdTest() {
		Mockito.when(userRepository.findById(1)).thenReturn(Optional.ofNullable(user1));
		assertThat(userhandler.getUserById(user1.getId())).isEqualTo(Optional.ofNullable(user1));
	}

	@Test
	public void createUser() {
		when(userRepository.save(Mockito.any())).thenReturn(user);
		userhandler.createUser(user);
		verify(userRepository, times(1)).save(Mockito.any());

	}

	@Test
	public void deleteUserById() {
		userhandler.deleteUserById(1);
		verify(userRepository).deleteById(1);
	}
	
	@Test
	public void  deleteAll() {
		userhandler.deleteAll();
		verify(userRepository).deleteAll();

	}
	
	@Test
	public void updateUser() {
		when(userRepository.save(Mockito.any())).thenReturn(user1);
		userhandler.updateUser(user);
		verify(userRepository, times(1)).save(Mockito.any());
		}
	
	@Test
	public void testXmlToObject() {
		
	}
	

}
