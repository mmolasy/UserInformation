package pl.molasym.userinformation.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import pl.molasym.userinformation.entities.User;
import pl.molasym.userinformation.exceptions.UserNotFoundException;
import pl.molasym.userinformation.service.UserInformationService;

@Controller
public class HomeController {

	@Autowired
	private UserInformationService userInformationService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String homeView(HttpServletResponse response) {
		return "redirect:/users";
	}

	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public ModelAndView getAllCards(HttpServletResponse response) {
		List<User> list = userInformationService.getAllUsers();

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("allUsers");
		modelAndView.addObject("lista", list);

		return modelAndView;
	}

	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
	public ModelAndView getUserById(@PathVariable String id, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView();
		User user = null;

		try {
			user = userInformationService.getUserById(Long.valueOf(id).longValue());
			System.out.println(user);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UserNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.getMessage();
		}
		if (user == null) {
			response.setStatus(404);
			return new ModelAndView("userNotFound");
		}
		response.setStatus(200);
		modelAndView.addObject("user", user);
		modelAndView.setViewName("showUser");
		return modelAndView;
	}

	@RequestMapping(value = "/user/{id}", method = RequestMethod.POST)
	public ModelAndView updateUserById(@PathVariable String id, @RequestBody User user, HttpServletResponse response) {

		try {
			userInformationService.updateUserById(Long.valueOf(id).longValue(), user);
		} catch (UserNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.getMessage();
		}
		return new ModelAndView("redirect:/user/" + id);
	}

	@RequestMapping(value = "/user", method = RequestMethod.PUT)
	public ModelAndView addNewUser(@RequestBody User user, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView();

		try {
			userInformationService.addNewUser(user);
		} catch (Throwable e) {
			e.printStackTrace();
		}

		response.setStatus(201);
		return modelAndView;
	}

	@RequestMapping(value = "/deleteuser/{id}", method = RequestMethod.POST)
	public ModelAndView removeUser(@PathVariable String id, HttpServletResponse response) {

		try {
			userInformationService.removeUser(Long.valueOf(id).longValue());
		} catch (UserNotFoundException e) {
			e.printStackTrace();
		} catch (Throwable e) {
			e.printStackTrace();
		}

		response.setStatus(200);
		return new ModelAndView("redirect:/users");
	}

	@RequestMapping(value = "/userByAccountId", method = RequestMethod.GET)
	public ModelAndView getUserByAccountId(@RequestParam String id, HttpServletResponse response) {

		User user = null;
		try {
			user = userInformationService.getUserByAccountId(Long.valueOf(id).longValue());
		} catch (UserNotFoundException e) {
			e.printStackTrace();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		if (user == null) {
			response.setStatus(404);
			return new ModelAndView("userNotFound");
		}

		response.setStatus(200);
		return new ModelAndView("redirect:/user/" + user.getUserId());
	}

}
