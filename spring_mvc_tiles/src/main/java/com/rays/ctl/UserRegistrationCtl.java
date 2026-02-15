package com.rays.ctl;

import javax.persistence.Entity;
import javax.validation.Valid;

import org.hibernate.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rays.dto.UserDTO;
import com.rays.form.UserRegistrationForm;
import com.rays.service.UserServiceImpl;
import com.rays.service.UserServiceInt;
import com.rays.util.DataUtility;

@Controller
@RequestMapping(value = "Register")
public class UserRegistrationCtl {

	@Autowired
	UserServiceInt UserServiceImpl;

	@GetMapping
	public String display(@ModelAttribute("form") UserRegistrationForm form, Model model) {
		return "UserRegistrationView";

	}

	@PostMapping
	public String submit(@ModelAttribute("form") @Valid UserRegistrationForm form, BindingResult bindingResult,
			Model model) {

		if (bindingResult.hasErrors()) {
			return "UserRegistrationView";

		}

		UserDTO dto = new UserDTO();
		dto.setId(form.getId());
		dto.setFirstName(form.getFirstName());
		dto.setLastName(form.getLastName());
		dto.setLogin(form.getLogin());
		dto.setPassword(form.getPassword());
		dto.setDob(DataUtility.stringToDate(form.getDob()));
		dto.setAddress(form.getAddress());
		try {

			long pk = UserServiceImpl.add(dto);
			model.addAttribute("msg", "user registration successfully");
		} catch (Exception e) {
			model.addAttribute("emsg", e.getMessage());
		}
		return "UserRegistrationView";

	}

}
