package com.rays.autowire;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	
	@Autowired // @Autowired annotation inject dependency by type
	private UserDAOInt userDao;
	
	public void add() {
		userDao.add();
		
	}

}
