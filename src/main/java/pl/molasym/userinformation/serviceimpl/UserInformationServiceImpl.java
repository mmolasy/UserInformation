package pl.molasym.userinformation.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.molasym.userinformation.entities.User;
import pl.molasym.userinformation.exceptions.UserNotFoundException;
import pl.molasym.userinformation.repository.UserRepository;
import pl.molasym.userinformation.service.UserInformationService;

@Service
public class UserInformationServiceImpl implements UserInformationService {

	@Autowired
	private UserRepository userRepository;

	public UserRepository getUserRepository() {
		return userRepository;
	}

	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public User getUserById(long id) throws UserNotFoundException {
		try {
			return userRepository.getUserById(id);
		} catch (UserNotFoundException e) {
			throw e;
		}
	}

	public List<User> getAllUsers() {
		return userRepository.getAllUsers();
	}

	public User getUserByAccountId(long id) throws UserNotFoundException {
		try {
			return userRepository.getUserByAccountId(id);
		} catch (UserNotFoundException e) {
			throw e;
		}
	}

	public void updateUserById(long id, User user) throws UserNotFoundException {
		try {
			userRepository.updateUserById(id, user);
		} catch (UserNotFoundException e) {
			throw e;
		}
	}

	public void addNewUser(User user) {
		userRepository.addNewUser(user);
	}

	public void removeUser(long id) throws UserNotFoundException {
		try {
			userRepository.removeUser(id);
		} catch (UserNotFoundException e) {
			throw e;
		}
	}

}
