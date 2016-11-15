package pl.molasym.userinformation.repository;

import java.util.List;

import pl.molasym.userinformation.entities.User;
import pl.molasym.userinformation.exceptions.UserNotFoundException;

public interface UserRepository {
	
	
	public User getUserById (long id) throws UserNotFoundException;
	public List<User> getAllUsers();
	public User getUserByAccountId(long id) throws UserNotFoundException;
	public void updateUserById(User old, User user) throws UserNotFoundException;
	public void addNewUser(User user);
	public void removeUser(long id) throws UserNotFoundException;
}