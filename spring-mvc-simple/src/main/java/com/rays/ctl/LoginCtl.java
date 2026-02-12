package com.rays.ctl;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.rays.dto.UserDTO;
import com.rays.form.LoginForm;
import com.rays.service.UserServiceImpl;
import com.rays.service.UserServiceInt;

@Controller
@RequestMapping(value = "Login")
public class LoginCtl {

	@Autowired
	UserServiceInt UserServiceImpl;

	@GetMapping
	public String display(@ModelAttribute("form") LoginForm form, HttpSession session,
			@RequestParam(required = false) String operation) {
		if (operation != null && operation.equals("logout")) {
			session.invalidate();
		}
		return "LoginView";

	}

	@PostMapping
	public String login(@ModelAttribute("form") LoginForm form, HttpSession session) {

		UserDTO dto = new UserDTO();
		dto = UserServiceImpl.authenticate(form.getLogin(), form.getPassword());

		if (dto != null) {
			session.setAttribute("user", dto);
			return "redirect:/Welcome";

		} else {
			return "LoginView";
		}

	}

}
