package com.aaludra.spring.jpa.h2.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.AdditionalAnswers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.aaludra.spring.jpa.h2.model.User;
import com.aaludra.spring.jpa.h2.repository.UserRepository;
import com.aaludra.spring.jpa.h2.view.UserInputView;
import com.aaludra.spring.jpa.h2.view.UserOutputView;

@ExtendWith(MockitoExtension.class)
class UserHandlerTest2 {

	@Mock
	private UserRepository userRepository;

	@InjectMocks
	private UserHandler userhandler;
	private User user;
	private List<User> userList;
	private UserInputView userinputview;

	@BeforeEach
	public void setUp() {
		userList = new ArrayList<>();
		user = new User("aswathy", "achu", "abcd", null, 123456789l, "Active", "admin", null, "admin", null);
	}

	@Test
	public void getAllUserTest() {
		userList.add(user);
		when(userRepository.findAll()).thenReturn(userList);
		List<UserOutputView> useroutputview = userhandler.getAllUser();
		assertEquals(useroutputview.get(0).getUsername(), userList.get(0).getUsername());
	}

	@Test
	public void getUserByIdTest() {

		Mockito.when(userRepository.findById(Mockito.anyInt())).thenReturn(Optional.ofNullable(user));
		Optional<UserOutputView> useroutput = userhandler.getUserById(user.getId());
		assertEquals(useroutput.get().getDisplayname(), Optional.ofNullable(user).get().getDisplayname());
	}

	@Test
	public void createUser() {
		UserInputView userinputview = new UserInputView("kunju", "achuz", "abab", null, "1234567819l", "Active",
				"admin", null, "admin", null);
		when(userRepository.save(Mockito.any())).thenReturn(user);
		UserOutputView useroutputview = userhandler.createUser(userinputview);
		assertThat(userhandler.createUser(userinputview).getUsername()).isEqualTo(useroutputview.getUsername());
	}

	@Test
	public void deleteUserById() {
		userhandler.deleteUserById(1);
		verify(userRepository).deleteById(1);
	}

	@Test
	public void deleteAll() {
		userhandler.deleteAll();
		verify(userRepository).deleteAll();

	}

	@Test
	public void updateUser() {
		UserInputView userinputview = new UserInputView("vichu", "achuz", "abab", null, "1234567819l", "Active",
				"admin", null, "admin", null);
		when(userRepository.findById(any())).thenReturn(Optional.ofNullable(user));
		when(userRepository.save(any())).thenAnswer(AdditionalAnswers.returnsFirstArg());
		Optional<UserOutputView> useroutputview = Optional
				.ofNullable(userhandler.updateUser(userinputview, user.getId()));
		assertThat(userhandler.updateUser(userinputview, user.getId()).getUsername())
				.isEqualTo(useroutputview.get().getUsername());
	}

	@Test
	public void testXmlToObject() {

	}

	@Test
	public void testJsonToObject() {
	}

}
