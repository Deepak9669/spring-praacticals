package com.rays.dao;

import java.util.List;

import com.rays.dto.UserDTO;

public interface UserDAOInt {


	public long add(UserDTO dto);

	public void delete(Long pk);

	public void update(UserDTO dto);
	
	public UserDTO findByPk(Long pk);
	
	public UserDTO findByLogin(String login);
	
	public UserDTO authenticate(String login , String password);
	
	public List<UserDTO> search(UserDTO dto , int pageNo , int pageSize);
	

}
